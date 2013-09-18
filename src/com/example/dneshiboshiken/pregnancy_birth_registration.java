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
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class pregnancy_birth_registration extends Activity {

	//目次の項目だけボタンを定義
	private String filepath = "/Yukari/Write/Pregnancy/pregnancy_birth_registrationfile.xml";
	private Button button_Write_child_9_12_1_back;
	private Button button_Write_child_9_12_1_home;
	private Button button_Write_child_9_12_1_next;
	final boolean[] checkedItems = new boolean[10];

	private String[] item_checkup_8tag = {"EditText_birth_registration_name",
			"EditText_birth_registration_place",
			"EditText_birth_registration_birthday",
			"Spinner_birth_registration_sex",
	};
	public static int[] item_checkup_8 = {R.id.TextView_birth_registration_name,
		R.id.TextView_birth_registration_place,
		R.id.TextView_birth_registration_birthday,
		R.id.TextView_birth_registration_sex,};
	public static int[] item_checkup_check_textview_8 = {};
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pregnancy_birth_registration);	//画面レイアウトを指定(res/layout/index_read.xml)
		TextView[] tvParam = new TextView[item_checkup_8.length];

		// 読み込み
		//xmlファイル読み込み
		File dir = new File(Environment.getExternalStorageDirectory().getPath());
		if(dir.exists()){

			File file = new File(dir.getAbsolutePath()+filepath);
			if (file.exists()) {
				try {
					XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
					factory.setNamespaceAware(true);
					XmlPullParser parser = factory.newPullParser();
					FileInputStream fis = new FileInputStream(file);
					parser.setInput(new InputStreamReader(fis));

					// TextViewインスタンスの取得
					for (int i1 = 0; i1 < item_checkup_8.length; i1++) {
						tvParam[i1] = (TextView)findViewById(item_checkup_8[i1]);
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
								if(tag.equals(item_checkup_8tag[0])) {tvParam[0].setText(value);}
								else if(tag.equals(item_checkup_8tag[1])) {tvParam[1].setText(value);}
								else if(tag.equals(item_checkup_8tag[2])) {tvParam[2].setText(value);}
								else if(tag.equals(item_checkup_8tag[3])) {tvParam[3].setText(value);}
							}

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
					File view = new File(dir.getAbsolutePath()+"/Yukari/Photo/pregnancy_birth_registrationp.png");
					if (view.exists()) {
						BitmapFactory.Options options = new BitmapFactory.Options();
						options.inJustDecodeBounds = true;
						Bitmap bmp1 = BitmapFactory.decodeFile(view.getPath(), options );

						int reqWidth = 0;
						int reqHeight = 0;
						options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
						int scale = Math.max(2, 2);
						options.inJustDecodeBounds = false;
						options.inSampleSize = scale;
						Bitmap image = BitmapFactory.decodeFile(view.getPath(), options);

						((ImageView)findViewById(R.id.imageview_birth_registration)).setImageBitmap(image);
					}else{
						//存在しない
					}
				}
			}
		}


		//それぞれのボタンにクリック時の処理を表示

		button_Write_child_9_12_1_home = (Button) findViewById(R.id.Button_checkup_edit);
		button_Write_child_9_12_1_home.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				button_Write_child_9_12_1_home_onClick();
			}
		});


	}
	//ボタンクリックによって呼び出される処理
	//classの呼び出し(EMCHH.java)で行った内容と同様
	private void button_Write_child_9_12_1_home_onClick() {
		Intent intent_read_2 = new Intent(getApplicationContext(),pregnancy_birth_registrationp.class);
		startActivity(intent_read_2);
		finish();
	}
	//onCreateOptionsMenuメソッド(オプションメニュー生成)
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		super.onCreateOptionsMenu(menu);

		//メニューアイテムの追加
		MenuItem item1=menu.add(0,0,0,"編集");
		item1.setIcon(android.R.drawable.ic_menu_edit);

		MenuItem item2=menu.add(0,1,0,"タイトル");
		item2.setIcon(R.drawable.ic_menu_home);

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



	//バックキーのアクション


}
