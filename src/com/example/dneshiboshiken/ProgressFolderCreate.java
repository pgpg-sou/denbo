package com.example.dneshiboshiken;

import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;

public class ProgressFolderCreate extends AsyncTask<Activity, Integer, String> {
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
	public ProgressFolderCreate(Context context) {
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
		
		// TODO 自動生成されたメソッド・スタブ
		// Log.d("a","b");
		while (MainActivity.folderRun == false) {
			SystemClock.sleep(1000);
			// DriveModule.OAuth(GoogleDriveBuild.credential, activity[0]);
			Log.e("FOLDER CREATOR", "WAITING.....");
		}

		try {

			for (int i = 0; i < FilePath.length; i++) {
				DriveModule.folderCreator(MainActivity.id,
						GoogleDriveBuild.credential,
						GoogleDriveBuild.service, FilePath[i],activity[0]);
			}
			pref = activity[0].getSharedPreferences("prefs", Activity.MODE_PRIVATE);
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
		return null;
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
		mProgressDialog.setMessage("しばらくお待ちください...");
		mProgressDialog.setTitle("フォルダを作成しています...");
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
