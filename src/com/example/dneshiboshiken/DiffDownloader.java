package com.example.dneshiboshiken;

import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;

import com.google.api.services.drive.model.File;

/*
 * AsyncTask<型1, 型2,型3>
 *
 *   型1 … Activityからスレッド処理へ渡したい変数の型
 *          ※ Activityから呼び出すexecute()の引数の型
 *          ※ doInBackground()の引数の型
 *
 *   型2 … 進捗度合を表示する時に利用したい型
 *          ※ onProgressUpdate()の引数の型
 *
 *   型3 … バックグラウンド処理完了時に受け取る型
 *          ※ doInBackground()の戻り値の型
 *          ※ onPostExecute()の引数の型
 *
 *   ※ それぞれ不要な場合は、Voidを設定すれば良い
 */
public class DiffDownloader extends AsyncTask<Activity, Integer, String> {
	private Context mContext;
	private ProgressDialog mProgressDialog;
	private Boolean isShowProgress;
	private SharedPreferences pref;// GoogleDriveアカウント名保存用
	private SharedPreferences.Editor editor;// GoogleDriveアカウント名保存用
	private static boolean network=true;

	/**
	 * コンストラクタ
	 *
	 * @param context
	 */
	public DiffDownloader(Context context) {
		mContext = context;
	}

	/**
	 * getIsShowProgress プログレスダイアログが表示中かどうかを返します。
	 *
	 * @return
	 */
	public Boolean getIsShowProgress() {
		return isShowProgress;
	}

	/**
	 * onPreExecute 最初にUIスレッドで呼び出されます。 UIに関わる処理をします。
	 */
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		while(MainActivity.diff==false){
			SystemClock.sleep(100);
		}
		isShowProgress = true;
		showDialog();
	}

	/**
	 * doInBackground ワーカースレッド上で実行されます。
	 * このメソッドに渡されるパラメータの型はAsyncTaskの一つ目のパラメータです。 このメソッドの戻り値は
	 * AsyncTaskの三つ目のパラメータです。
	 *
	 * @return
	 */
	protected String doInBackground(Activity... activity) {
		try{
			DriveModule.OAuth(GoogleDriveBuild.credential, activity[0]);
			//ファイルの作成やyukariの検索が終了している場合かつ差分を取る場合
			//ファイルのIDを保存している設定データを呼び出す
			SharedPreferences fileIdpref=activity[0].getSharedPreferences("fileIds", Activity.MODE_PRIVATE);
			//MAPにすべてのfileId(ファイル名とIDを入れる
			Map<String, String> fileIdkey=(Map<String, String>) fileIdpref.getAll();
			publishProgress(5);
			Log.i("fileIdpref","fileIdpref"+fileIdpref.getAll().toString());
			//ファイルのパスを保存している設定データを呼び出す
			SharedPreferences filePathpref=activity[0].getSharedPreferences("filePath", Activity.MODE_PRIVATE);
			//MAPにすべてのfilePath(ファイル名とパス)を入れる
			SharedPreferences pref=activity[0].getSharedPreferences("prefs", Activity.MODE_PRIVATE);
			Log.e("oops","oops");
			publishProgress(10);
			Map<String, String> filePathkey=(Map<String, String>) filePathpref.getAll();
			Log.e("ups","ups");
			Log.i("filePathpref","filePathpref"+filePathpref.getAll().toString());
			String path1 = Environment.getExternalStorageDirectory()
					.getPath() + "/Yukari";
			publishProgress(15);

			File item = GoogleDriveBuild.service.files().get(pref.getString("RootFolder", "NO ID")).execute();
			java.io.File f1 = new java.io.File(path1);
			for(int i=20; i<71;i++){
				publishProgress(i);
				SystemClock.sleep(5);
			}
			DriveModule.fDownloader(item,f1,GoogleDriveBuild.service,activity[0]);
			for(int i=71; i<101;i++){
				publishProgress(i);
				SystemClock.sleep(5);
			}

			}catch (Exception e){
				Log.e("ID OR DATE NOT FOUND",e.toString());
			}
	return null;

	}

	private void onSaveData(String Text, String data) {
		// TODO 自動生成されたメソッド・スタブ
		editor = pref.edit();
		editor.putString(Text, data);
		// Toast.makeText(MainActivity.this, data, Toast.LENGTH_LONG).show();
		editor.commit();
	}

	/**
	 * onProgressUpdate doInBackground内でpublishProgressメソッドが呼ばれると、
	 * UIスレッド上でこのメソッドが呼ばれます。 このメソッドの引数の型はAsyncTaskの二つ目のパラメータです。
	 *
	 * @param values
	 */
	 @Override
	 protected void onProgressUpdate(Integer... values) {
	 super.onProgressUpdate(values);
	 mProgressDialog.setProgress(values[0]);
	 }

	/**
	 * onPostExecute doInBackground が終わるとそのメソッドの戻り値をパラメータとして渡して onPostExecute
	 * が呼ばれます。 このパラメータの型は AsyncTask を extends するときの三つめのパラメータです。
	 * バックグラウンド処理が終了し、メインスレッドに反映させる処理をここに書きます。
	 *
	 * @param result
	 */
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		dismissDialog();
		isShowProgress = false;
//		pref.edit();
//		editor.putString("RootFolder", result);
//		editor.commit();
		Log.d("PROGRESSTF3", "b");

	}

	/**
	 * onCancelled cancelメソッドが呼ばれるとonCancelledメソッドが呼ばれます。
	 */
	@Override
	protected void onCancelled() {
		super.onCancelled();
		dismissDialog();
		isShowProgress = false;
	}

	/**
	 * showDialog プログレスダイアログを表示します
	 */
	public void showDialog() {
		mProgressDialog = new ProgressDialog(mContext);
		mProgressDialog.setMessage("Driveと同期しています\nしばらくお待ちください...");
		mProgressDialog.setTitle("同期しています...");
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		mProgressDialog.setMax(100);
		mProgressDialog.setIndeterminate(false);
		mProgressDialog.setCancelable(false);
		mProgressDialog.show();
	}

	/**
	 * dismissDialog ダイアログを終了する。
	 */
	public void dismissDialog() {
		mProgressDialog.dismiss();
		mProgressDialog = null;
	}

}
