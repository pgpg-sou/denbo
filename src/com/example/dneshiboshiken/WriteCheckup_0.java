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

public class WriteCheckup_0 extends Activity {

	//目次の項目だけボタンを定義
	private Button button_Write_child_9_1_1_back;
	private Button button_Write_child_9_1_1_home;
	private Button button_Write_child_9_1_1_next;
    private static final String PREFERRENCES_FILE_NAME = "PrefrencesFile";


	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_checkup_0);	//画面レイアウトを指定(res/layout/index_read.xml)

     // 読み込み
      //xmlファイル読み込み
        File dir = new File(Environment.getExternalStorageDirectory().getPath());
        if(dir.exists()){
            File file = new File(dir.getAbsolutePath()+"/Yukari/Write/Checkup/WriteCheckup_0file.xml");
            if (file.exists()) {
            	try {
            		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            		factory.setNamespaceAware(true);
            		XmlPullParser parser = factory.newPullParser();
            		FileInputStream fis = new FileInputStream(file);
            		parser.setInput(new InputStreamReader(fis));

                    // TextViewインスタンスの取得
            		TextView tvParam1 = (TextView)findViewById(R.id.TextView_write_checkup_0_weight);
            		TextView tvParam2 = (TextView)findViewById(R.id.TextView_write_checkup_0_height);
            		TextView tvParam3 = (TextView)findViewById(R.id.TextView_write_checkup_0_chest);
            		TextView tvParam4 = (TextView)findViewById(R.id.TextView_write_checkup_0_head);

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
                            // 空白で取得したものは全て処理対象外とする
                            if(value.trim().length() != 0) {
                                // 取得した結果をTextViewに設定
                                if(tag.equals("write_checkup_0_weight")) {
                                    tvParam1.setText(value);
                                } else if(tag.equals("write_checkup_0_height")) {
                                    tvParam2.setText(value);
                                }else if(tag.equals("write_checkup_0_chest")) {
                                    tvParam3.setText(value);
                                }else if(tag.equals("write_checkup_0_head")) {
                                    tvParam4.setText(value);
                                }
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
                File view = new File(dir.getAbsolutePath()+"/Yukari/Photo/imageView_write_checkup_0p.png");
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

                	((ImageView)findViewById(R.id.imageview_write_checkup_0)).setImageBitmap(image);
                }else{
                    //存在しない
                }
            }



        //それぞれのボタンにクリック時の処理を表示
        button_Write_child_9_1_1_back = (Button) findViewById(R.id.Button_checkup_back);
        button_Write_child_9_1_1_back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
            button_Write_child_9_1_1_back_onClick();
            }
        });

        button_Write_child_9_1_1_home = (Button) findViewById(R.id.Button_checkup_edit);
        button_Write_child_9_1_1_home.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
            	button_Write_child_9_1_1_home_onClick();
            }
        });

        button_Write_child_9_1_1_next = (Button) findViewById(R.id.Button_checkup_next);
        button_Write_child_9_1_1_next.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
            	button_Write_child_9_1_1_next_onClick();
            }
        });
        }
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
    //ボタンクリックによって呼び出される処理
    //classの呼び出し(EMCHH.java)で行った内容と同様
    private void button_Write_child_9_1_1_back_onClick() {
    	Toast.makeText(this, "検診がありません。", Toast.LENGTH_LONG).show();
    }

    private void button_Write_child_9_1_1_home_onClick() {
        Intent intent_read_2 = new Intent(getApplicationContext(),WriteCheckup_0p.class);
        startActivity(intent_read_2);
        ImageView iv = (ImageView)findViewById(R.id.imageview_write_checkup_0);
        iv.setImageDrawable(null);
        finish();
    }

    private void button_Write_child_9_1_1_next_onClick() {
        Intent intent_read_3 = new Intent(getApplicationContext(),WriteCheckup_1.class);
        ImageView iv = (ImageView)findViewById(R.id.imageview_write_checkup_0);
        iv.setImageDrawable(null);
        startActivity(intent_read_3);
    }


    //バックキーのアクション

}
