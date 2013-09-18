package com.example.dneshiboshiken;

import java.io.IOException;

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
public class ProgressTask extends AsyncTask<Activity, Integer, String> {
	private Context mContext;
	private ProgressDialog mProgressDialog;
	private Boolean isShowProgress;
	private SharedPreferences pref;// GoogleDriveアカウント名保存用
	private SharedPreferences.Editor editor;// GoogleDriveアカウント名保存用
	private static boolean network=true;
	public static final String[] FilePath = { "Write/Pregnancy","Write/Checkup", "Photo","Write/immunization" };

	/**
	 * コンストラクタ
	 *
	 * @param context
	 */
	public ProgressTask(Context context) {
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

		pref = activity[0].getSharedPreferences("prefs", Activity.MODE_PRIVATE);
		boolean yukari=false;
		publishProgress(1);
		try {
			// DriveModule.OAuth(GoogleDriveBuild.credential);
			while (MainActivity.get == false) {
				SystemClock.sleep(1000);
//				DriveModule.OAuth(GoogleDriveBuild.credential, activity[0]);
				Log.d("SKIPP", "NOW");
			}
			publishProgress(20);
			String a=pref.getString("RootFolder", null);
			DriveModule.OAuth(GoogleDriveBuild.credential, activity[0]);
			if (a==null) {
				Log.e("in","a=null");
				publishProgress(25);
				MainActivity.listData = DriveModule.retrieveAllFiles(
						GoogleDriveBuild.service, GoogleDriveBuild.credential,activity[0]);
				Log.e("DriveModule.retrieveAllFiles","DriveModule.retrieveAllFiles");
				publishProgress(30);
				if(MainActivity.listData.size()!=0){
				for (final File item : MainActivity.listData) {
					Log.i("FOLDER NAME",item.getTitle());
					if (item.getTitle().toString().equals("Yukari")==true) {
						Log.i("for filer inaadsfasdfads ", "yukari aru e in");
						yukari=true;
						String path = Environment.getExternalStorageDirectory()
								.getPath() + "/Yukari";
						publishProgress(40);
						java.io.File f = new java.io.File(path);
						publishProgress(50);
						Log.i("filedl","dileddd");
						Log.i("Activity","activity"+activity[0].toString());
						//ファイルの一括ダウンロード
						DriveModule.fDownloader(item, f,GoogleDriveBuild.service, activity[0]);
						Log.i("filedl","222222");
						publishProgress(85);
						MainActivity.diff=false;
						Log.i("diff","false");
						MainActivity.id = item.getId();
						publishProgress(87);
						onSaveData("RootFolder", MainActivity.id);
						publishProgress(90);
						folderCreate(activity[0]);
						MainActivity.folderRun = true;
						publishProgress(100);
						Log.i("step1","true");
						return item.getId();
					}

				}
				}else{
					yukari=false;
				}
				Log.d("OUT	", "OUT OF DATA");
				if(yukari==false){
					publishProgress(70);
					MainActivity.id = DriveModule.firstFolderCreate(GoogleDriveBuild.credential, GoogleDriveBuild.service, "Yukari").getId();
					String path = Environment.getExternalStorageDirectory()
							.getPath() + "/Yukari";
					java.io.File f = new java.io.File(path);
//					DriveModule.OAuth(GoogleDriveBuild.credential, activity[0]);
					Log.e("fdownload","fdownloadにはいるお");
					DriveModule.fDownloader(GoogleDriveBuild.service.files().get(MainActivity.id).execute(), f,GoogleDriveBuild.service, activity[0]);
					Log.e("F DOWNLOADER","DriveModule.fDownloader(GoogleDriveBuild.service.files().get(MainActivity.id).execute(), f,GoogleDriveBuild.service, activity[0]);");
					onSaveData("RootFolder", MainActivity.id);
					publishProgress(90);
					MainActivity.diff=false;
					Log.i("diff","false");
				MainActivity.folderRun = true;
				folderCreate(activity[0]);
				publishProgress(100);
				Log.i("step1","true");
				
				return MainActivity.id ;
				}
			}else{
				publishProgress(50);
			MainActivity.id = pref.getString("RootFolder", null);
			publishProgress(70);
			Log.i("FILE ID   ","FILE ID"+MainActivity.id);
			publishProgress(80);
			MainActivity.diff=true;
			Log.i("diff","true");
			MainActivity.folderRun = true;
			publishProgress(100);
			return MainActivity.id ;
			}
		} catch (Exception e) {
			Log.e("ERROR GET LIST", e.toString());
		}
		publishProgress(0);
		return null;

	}

	private void onSaveData(String Text, String data) {
		// TODO 自動生成されたメソッド・スタブ
		editor = pref.edit();
		editor.putString(Text, data);
		// Toast.makeText(MainActivity.this, data, Toast.LENGTH_LONG).show();
		editor.commit();
	}
	private void folderCreate(Activity activity){
		try {

			for (int i = 0; i < FilePath.length; i++) {
				DriveModule.folderCreator(MainActivity.id,
						GoogleDriveBuild.credential,
						GoogleDriveBuild.service, FilePath[i],activity);
			}
			pref = activity.getSharedPreferences("prefs", Activity.MODE_PRIVATE);
			editor=pref.edit();
			editor.putString("First start","CLEAR");
			editor.commit();
			Log.i("FF", "CLEAR");
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			Log.e("FOLDER CREATE ERROR",
					"FOLDER CREATE ERROR" + e.toString());
		}
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
		MainActivity.folderdialog=true;
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
		mProgressDialog.setMessage("しばらくお待ちください...");
		mProgressDialog.setTitle("設定を取得しています...");
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
