//教育機能の目次
package com.example.dneshiboshiken;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class diary_main extends Activity {

	//目次の項目だけボタンを定義
	private String datapath = "/Yukari/Diary/file1.xml";
	private int photopath;
	private Button button_Write_child_9_12_1_back;
	private Button button_Write_child_9_12_1_home;
	private Button button_Write_child_9_12_1_next;
	final boolean[] checkedItems = new boolean[10];

	private VideoView videoView;
	static String mode="eeeee";

	private String[] item_checkup_10tag = {"EditText_diary_day",
			"EditText_diary_text",};
	public static int[] item_checkup_10 = {R.id.TextView_diary_day,
		R.id.TextView_diary_text,};

	public void onStart(){
		super.onStart();
		TextView[] tvParam = new TextView[item_checkup_10.length];
		File dir1 = new File(Environment.getExternalStorageDirectory().getPath());
		if(dir1.exists()){
			int i1 = 1;
			File file = new File(dir1.getAbsolutePath()+"/" + "Yukari/Diary/file"+i1+".xml");
			while(file!=null) {
				file = new File(dir1.getAbsolutePath()+"/" + "Yukari/Diary/file"+i1+".xml");
				if (file.exists()) {
					datapath="/Yukari/Diary/file"+i1+".xml";
					photopath = i1 ;
				}else break;
				i1++;
			}
		}


		// 読み込み
		//xmlファイル読み込み
		File dir = new File(Environment.getExternalStorageDirectory().getPath());
		if(dir.exists()){

			File file = new File(dir.getAbsolutePath()+datapath);
			if (file.exists()) {
				try {
					XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
					factory.setNamespaceAware(true);
					XmlPullParser parser = factory.newPullParser();
					FileInputStream fis = new FileInputStream(file);
					parser.setInput(new InputStreamReader(fis));

					// TextViewインスタンスの取得
					for (int i1 = 0; i1 < item_checkup_10.length; i1++) {
						tvParam[i1] = (TextView)findViewById(item_checkup_10[i1]);
					}


					// タグ名
					String tag = "";
					// 値
					String value = "";
					// XMLの解析
					for (int type = parser.getEventType(); type != XmlPullParser.END_DOCUMENT;
							type = parser.next()) {
						switch(type) {
						case XmlPullParser.START_TAG: // 開始タグ
						tag = parser.getName();
						break;
						case XmlPullParser.TEXT: // タグの内容
							value = parser.getText();
							if(value.trim().length() != 0) {
								// 空白で取得したものは全て処理対象外とする
								if(tag.equals(item_checkup_10tag[0])) {tvParam[0].setText(value);}
								else if(tag.equals(item_checkup_10tag[1])) {tvParam[1].setText(value);}}
							break;
						case XmlPullParser.END_TAG: // 終了タグ
							break;
						}

					}
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "エラー発生", Toast.LENGTH_SHORT);
				}
			}
			//画像読み込み
			if(dir.exists()){
				if(dir.exists()){
					File view = new File(dir.getAbsolutePath()+"/Yukari/Photo/imageview_diary"+photopath+".png");
					if (view.exists()) {
						BitmapFactory.Options options = new BitmapFactory.Options();
						options.inJustDecodeBounds = true;
						Bitmap bmp1 = BitmapFactory.decodeFile(view.getPath(), options );

						int reqWidth = 0;
						int reqHeight = 0;
						options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
						int scale = Math.max(1, 1);
						options.inJustDecodeBounds = false;
						options.inSampleSize = scale;
						Bitmap image = BitmapFactory.decodeFile(view.getPath(), options);

						((ImageView)findViewById(R.id.imageview_diary)).setImageBitmap(image);
					}else{
						//存在しない
					}
				}}


			//moview
			videoView = (VideoView)findViewById(R.id.videoview_diary);
			videoView.setMediaController(new MediaController(this));
			videoView.setVisibility(View.GONE);
			//videoが存在しているか
			File videoFile = new File(Environment.getExternalStorageDirectory().getPath()+"/Yukari/Video/dialy"+photopath+".mp4");
			if(videoFile.exists()){
				Log.d("video", "exists");
				videoView.setVisibility(View.VISIBLE);
				//存在している場合再生準備 SDカード上のファイルを再生
				videoView.setVideoPath(Environment.getExternalStorageDirectory().getPath()+"/Yukari/Video/dialy"+photopath+".mp4");
			}
		}
	}

		/** Called when the activity is first created. */
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.diary_main);	//画面レイアウトを指定(res/layout/index_read.xml)




			//それぞれのボタンにクリック時の処理を表示
			button_Write_child_9_12_1_back = (Button) findViewById(R.id.Button_checkup_back);
			button_Write_child_9_12_1_back.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v) {
					button_Write_child_9_12_1_back_onClick();
				}
			});

			button_Write_child_9_12_1_home = (Button) findViewById(R.id.Button_checkup_edit);
			button_Write_child_9_12_1_home.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v) {
					button_Write_child_9_12_1_home_onClick();
				}
			});

			button_Write_child_9_12_1_next = (Button) findViewById(R.id.Button_checkup_next);
			button_Write_child_9_12_1_next.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v) {
					button_Write_child_9_12_1_next_onClick();
				}
			});

	}
	//ボタンクリックによって呼び出される処理
	//classの呼び出し(EMCHH.java)で行った内容と同様
	private void button_Write_child_9_12_1_back_onClick() {

		TextView[] tvParam = new TextView[item_checkup_10.length];

		int photopathT = photopath-1;
		datapath="/Yukari/Diary/file"+photopathT+".xml";

		// 読み込み
		//xmlファイル読み込み
		File dir = new File(Environment.getExternalStorageDirectory().getPath());
		if(dir.exists()){
			File file = new File(dir.getAbsolutePath()+datapath);
			if (file.exists()) {
				ImageView iv = (ImageView)findViewById(R.id.imageview_diary);
				iv.setImageDrawable(null);
				photopath--;
				try {
					XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
					factory.setNamespaceAware(true);
					XmlPullParser parser = factory.newPullParser();
					FileInputStream fis = new FileInputStream(file);
					parser.setInput(new InputStreamReader(fis));

					// TextViewインスタンスの取得
					for (int i1 = 0; i1 < item_checkup_10.length; i1++) {
						tvParam[i1] = (TextView)findViewById(item_checkup_10[i1]);
					}
					// タグ名
					String tag = "";
					// 値
					String value = "";
					// XMLの解析
					for (int type = parser.getEventType(); type != XmlPullParser.END_DOCUMENT;
							type = parser.next()) {
						switch(type) {
						case XmlPullParser.START_TAG: // 開始タグ
						tag = parser.getName();
						break;
						case XmlPullParser.TEXT: // タグの内容
							value = parser.getText();
							if(value.trim().length() != 0) {
								// 空白で取得したものは全て処理対象外とする
								if(tag.equals(item_checkup_10tag[0])) {tvParam[0].setText(value);}
								else if(tag.equals(item_checkup_10tag[1])) {tvParam[1].setText(value);}}
							break;
						case XmlPullParser.END_TAG: // 終了タグ
							break;
						}

					}
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "エラー発生", Toast.LENGTH_SHORT);
				}
				//画像読み込み

				if(dir.exists()){
					File view = new File(dir.getAbsolutePath()+"/Yukari/Photo/imageview_diary"+photopath+".png");
					if (view.exists()) {
						BitmapFactory.Options options = new BitmapFactory.Options();
						options.inJustDecodeBounds = true;
						Bitmap bmp1 = BitmapFactory.decodeFile(view.getPath(), options );

						int reqWidth = 0;
						int reqHeight = 0;
						options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
						int scale = Math.max(1, 1);
						options.inJustDecodeBounds = false;
						options.inSampleSize = scale;
						Bitmap image = BitmapFactory.decodeFile(view.getPath(), options);

						((ImageView)findViewById(R.id.imageview_diary)).setImageBitmap(image);
					}else{
						//存在しない
					}
				}
				//moview
				videoView = (VideoView)findViewById(R.id.videoview_diary);
				videoView.setMediaController(new MediaController(this));
				videoView.setVisibility(View.GONE);
				//videoが存在しているか
				File videoFile = new File(Environment.getExternalStorageDirectory().getPath()+"/Yukari/Video/dialy"+photopath+".mp4");
				if(videoFile.exists()){
					Log.d("video", "exists");
					videoView.setVisibility(View.VISIBLE);
					//存在している場合再生準備 SDカード上のファイルを再生
					videoView.setVideoPath(Environment.getExternalStorageDirectory().getPath()+"/Yukari/Video/dialy"+photopath+".mp4");
				}
			}else Toast.makeText(this, "これ以上ありません", Toast.LENGTH_LONG).show();

		}
	}


	private void button_Write_child_9_12_1_home_onClick() {
		Intent intent_read_2 = new Intent(getApplicationContext(),diary_mainp.class);
		startActivity(intent_read_2);
		ImageView iv = (ImageView)findViewById(R.id.imageview_diary);
		iv.setImageDrawable(null);
		finish();
	}

	private void button_Write_child_9_12_1_next_onClick() {
		TextView[] tvParam = new TextView[item_checkup_10.length];

		int photopathT = photopath+1;
		datapath="/Yukari/Diary/file"+photopathT+".xml";

		// 読み込み
		//xmlファイル読み込み
		File dir = new File(Environment.getExternalStorageDirectory().getPath());
		if(dir.exists()){
			File file = new File(dir.getAbsolutePath()+datapath);
			if (file.exists()) {
				ImageView iv = (ImageView)findViewById(R.id.imageview_diary);
				iv.setImageDrawable(null);

				photopath++;
				try {
					XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
					factory.setNamespaceAware(true);
					XmlPullParser parser = factory.newPullParser();
					FileInputStream fis = new FileInputStream(file);
					parser.setInput(new InputStreamReader(fis));

					// TextViewインスタンスの取得
					for (int i1 = 0; i1 < item_checkup_10.length; i1++) {
						tvParam[i1] = (TextView)findViewById(item_checkup_10[i1]);
					}
					// タグ名
					String tag = "";
					// 値
					String value = "";
					// XMLの解析
					for (int type = parser.getEventType(); type != XmlPullParser.END_DOCUMENT;
							type = parser.next()) {
						switch(type) {
						case XmlPullParser.START_TAG: // 開始タグ
						tag = parser.getName();
						break;
						case XmlPullParser.TEXT: // タグの内容
							value = parser.getText();
							if(value.trim().length() != 0) {
								// 空白で取得したものは全て処理対象外とする
								if(tag.equals(item_checkup_10tag[0])) {tvParam[0].setText(value);}
								else if(tag.equals(item_checkup_10tag[1])) {tvParam[1].setText(value);}}
							break;
						case XmlPullParser.END_TAG: // 終了タグ
							break;
						}

					}
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "エラー発生", Toast.LENGTH_SHORT);
				}
				//画像読み込み

				if(dir.exists()){
					File view = new File(dir.getAbsolutePath()+"/Yukari/Photo/imageview_diary"+photopath+".png");
					if (view.exists()) {
						BitmapFactory.Options options = new BitmapFactory.Options();
						options.inJustDecodeBounds = true;
						Bitmap bmp1 = BitmapFactory.decodeFile(view.getPath(), options );

						int reqWidth = 0;
						int reqHeight = 0;
						options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
						int scale = Math.max(1, 1);
						options.inJustDecodeBounds = false;
						options.inSampleSize = scale;
						Bitmap image = BitmapFactory.decodeFile(view.getPath(), options);

						((ImageView)findViewById(R.id.imageview_diary)).setImageBitmap(image);
					}else{
						//存在しない
					}
				}
				//moview
				videoView = (VideoView)findViewById(R.id.videoview_diary);
				videoView.setMediaController(new MediaController(this));
				videoView.setVisibility(View.GONE);
				//videoが存在しているか
				File videoFile = new File(Environment.getExternalStorageDirectory().getPath()+"/Yukari/Video/dialy"+photopath+".mp4");
				if(videoFile.exists()){
					Log.d("video", "exists");
					videoView.setVisibility(View.VISIBLE);
					//存在している場合再生準備 SDカード上のファイルを再生
					videoView.setVideoPath(Environment.getExternalStorageDirectory().getPath()+"/Yukari/Video/dialy"+photopath+".mp4");
				}
			}else Toast.makeText(this, "これ以上ありません", Toast.LENGTH_LONG).show();

		}
	}

	//注射済みの色を買えます


	//onOPtionsItemSelectedメソッド(メニューアイテム選択処理)
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
		case 0:
			Intent intent1 = new Intent();
			intent1.setClass(diary_main.this, MainActivity.class);
			startActivity(intent1);
			return true;
		case 1:
			Intent intent2 = new Intent();
			intent2.setClass(diary_main.this, MainActivity.class);
			startActivity(intent2);
			return true;
		}
		return true;
	}
	public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

		// 画像の元サイズ
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			if (width > height) {
				inSampleSize = Math.round((float)height / (float)reqHeight);
			} else {
				inSampleSize = Math.round((float)width / (float)reqWidth);
			}
		}
		return inSampleSize;
	}

	private float actDownX;
	private float actDownY;
	private float actUpX;
	private float actUpY;

	@Override
	public boolean onTouchEvent(MotionEvent event){
		Log.d("TouchEvent", "X:" + event.getX() + ",Y:" + event.getY());
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			actDownX = event.getX();
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:
			actUpX = event.getX();
			//左から右
			if(actUpX-actDownX > 100){
				button_Write_child_9_12_1_back_onClick();
			}else if(actDownX-actUpX > 100){//右から左
				button_Write_child_9_12_1_next_onClick();
			}
			break;
		}
		return false;

	}


	//バックキーのアクション


}
