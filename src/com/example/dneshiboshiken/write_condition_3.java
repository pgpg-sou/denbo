//教育機能の目次
package com.example.dneshiboshiken;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import android.widget.ViewFlipper;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.dneshiboshiken.R;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.EditText;
import android.content.Context;

public class write_condition_3 extends Activity {
	//目次の項目だけボタンを定義
	private int imagepath = R.id.imageview_write_condition_day_record;
	private String filename = "imageView_write_condition3.png";
	private Button button_Write_child_9_12_1_back;
	private Button button_Write_child_9_12_1_home;
	private Button button_Write_child_9_12_1_next;
	final boolean[] checkedItems = new boolean[10];

	private String filepath = "/Yukari/Write/condition/write_condition3.xml";

	private String[] item_pregnancy_writetag = {"EditText_write_condition_day_record",
			"EditText_write_condition1",
			"EditText_write_condition2",
			"EditText_write_condition_other",
			"radio_write_condition3",
			"radio_write_condition4",
			"radio_write_condition5",
			"radio_write_condition6",
			"radio_write_condition7",
			"radio_write_condition8",
			"radio_write_condition9",
			"radio_write_condition10",
			"radio_write_condition11",
	};
	public static int[] item_checkup_8 = {
		R.id.EditText_write_condition_day_record,
		R.id.EditText_write_condition1,
		R.id.EditText_write_condition2,
		R.id.EditText_write_condition_other,
		R.id.radio_write_condition3,
		R.id.radio_write_condition4,
		R.id.radio_write_condition5,
		R.id.radio_write_condition6,
		R.id.radio_write_condition7,
		R.id.radio_write_condition8,
		R.id.radio_write_condition9,
		R.id.radio_write_condition10,
		R.id.radio_write_condition11,};
	public static int[] item_checkup_check_textview_8 = {};
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.write_condition_3);	//画面レイアウトを指定(res/layout/index_read.xml)
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
								if(tag.equals(item_pregnancy_writetag[0])) {tvParam[0].setText(value);}
								else if(tag.equals(item_pregnancy_writetag[1])) {tvParam[1].setText(value);}
								else if(tag.equals(item_pregnancy_writetag[2])) {tvParam[2].setText(value);}
								else if(tag.equals(item_pregnancy_writetag[3])) {tvParam[3].setText(value);}
								else if(tag.equals(item_pregnancy_writetag[4])) {tvParam[4].setText(value);}
								else if(tag.equals(item_pregnancy_writetag[5])) {tvParam[5].setText(value);}
								else if(tag.equals(item_pregnancy_writetag[6])) {tvParam[6].setText(value);}
								else if(tag.equals(item_pregnancy_writetag[7])) {tvParam[7].setText(value);}
								else if(tag.equals(item_pregnancy_writetag[8])) {tvParam[8].setText(value);}
								else if(tag.equals(item_pregnancy_writetag[9])) {tvParam[9].setText(value);}
								else if(tag.equals(item_pregnancy_writetag[10])) {tvParam[10].setText(value);}
								else if(tag.equals(item_pregnancy_writetag[11])) {tvParam[11].setText(value);}
								else if(tag.equals(item_pregnancy_writetag[12])) {tvParam[12].setText(value);}

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
			//日付読みーっ！(」*ﾟﾛﾟ)」*ﾟﾛﾟ)」

			//画像読み込み
			//画像読み込み
			File view = new File(dir.getAbsolutePath()+"/Yukari/Photo/"+filename);
			if (view.exists()) {
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inJustDecodeBounds = true;
				Bitmap bmp1 = BitmapFactory.decodeFile(view.getPath(), options );

				int reqWidth = 0;
				int reqHeight = 0;
				options.inSampleSize = WriteCheckup_13.calculateInSampleSize(options, reqWidth, reqHeight);
				int scale = Math.max(1, 1);
				options.inJustDecodeBounds = false;
				options.inSampleSize = scale;
				Bitmap image = BitmapFactory.decodeFile(view.getPath(), options);

				((ImageView)findViewById(imagepath)).setImageBitmap(image);
			}else{
				//存在しない
			}
			//日付読み込む
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
	Intent intent_read_2 = new Intent(getApplicationContext(),write_condition_3p.class);
	startActivity(intent_read_2);

	finish();
}


//バックキーのアクション

}
