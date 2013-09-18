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

public class pregnancy_write_health extends Activity {

	//目次の項目だけボタンを定義
	private Button button_Write_child_9_12_1_back;
	private Button button_Write_child_9_12_1_home;
	private Button button_Write_child_9_12_1_next;
	final boolean[] checkedItems = new boolean[10];

	private String[] item_pregnancy_writetag = {"EditText_write_pregnancy_write_weight",
			"EditText_write_pregnancy_write_height",
			"EditText_write_pregnancy_write_marriage",
			"EditText_write_pregnancy_write_illnesses",
			"EditText_write_pregnancy_write_illnesses_other",
			"EditText_pregnancy_write_infectious_rubella_old",
			"EditText_pregnancy_write_infectious_measles_old",
			"EditText_pregnancy_write_infectious_pox_old",
			"EditText_pregnancy_write_operation_for",
			"EditText_pregnancy_write_medicine",
			"EditText_pregnancy_write_anxiety_other",
			"EditText_pregnancy_write_anxiety_other",
			"EditText_pregnancy_write_smoke_cigarettes",
			"EditText_pregnancy_write_smoke_same_cigarettes",
			"EditText_pregnancy_write_alcohol_glasses",
			"EditText_pregnancy_write_spouse",
			"EditText_pregnancy_write_spouse_poor",
			"Spinner_pregnancy_write_infectious_rubella",
			"Spinner_pregnancy_write_infectious_measles",
			"Spinner_pregnancy_write_infectious_pox",
			"radio_pregnancy_write_operation",
			"radio_pregnancy_write_stress",
			"radio_pregnancy_write_anxiety",
			"radio_pregnancy_write_smoke",
			"radio_pregnancy_write_smoke_same",
			"radio_pregnancy_write_alcohol",
			};
	public static int[] item_checkup_8 = {R.id.TextView_pregnancy_write_weight,
		R.id.TextView_pregnancy_write_height,
		R.id.TextView_pregnancy_write_marriage,
		R.id.TextView_pregnancy_write_illnesses,
		R.id.TextView_pregnancy_write_illnesses_other,
		R.id.TextView_pregnancy_write_infectious_rubella_old,
		R.id.TextView_pregnancy_write_infectious_measles_old,
		R.id.TextView_pregnancy_write_infectious_pox_old,
		R.id.TextView_pregnancy_write_operation_for,
		R.id.TextView_pregnancy_write_medicine,
		R.id.TextView_pregnancy_write_anxiety_other,
		R.id.TextView_pregnancy_write_anxiety_other,
		R.id.TextView_pregnancy_write_smoke_cigarettes,
		R.id.TextView_pregnancy_write_smoke_same_cigarettes,
		R.id.TextView_pregnancy_write_alcohol_glasses,
		R.id.TextView_pregnancy_write_spouse,
		R.id.TextView_pregnancy_write_spouse_poor,
		R.id.TextView_pregnancy_write_infectious_rubella,
		R.id.TextView_pregnancy_write_infectious_measles,
		R.id.TextView_pregnancy_write_infectious_pox,
		R.id.TextView_pregnancy_write_operation,
		R.id.TextView_pregnancy_write_stress,
		R.id.TextView_pregnancy_write_anxiety,
		R.id.TextView_pregnancy_write_smoke,
		R.id.TextView_pregnancy_write_smoke_same,
		R.id.TextView_pregnancy_write_alcohol,};
	public static int[] item_checkup_check_textview_8 = {};
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pregnancy_write_health);	//画面レイアウトを指定(res/layout/index_read.xml)
        TextView[] tvParam = new TextView[item_checkup_8.length];

        // 読み込み
        //xmlファイル読み込み
          File dir = new File(Environment.getExternalStorageDirectory().getPath());
          if(dir.exists()){

              File file = new File(dir.getAbsolutePath()+"/Yukari/Write/Pregnancy/healthfile.xml");
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
                            	  else if(tag.equals(item_pregnancy_writetag[13])) {tvParam[13].setText(value);}
                            	  else if(tag.equals(item_pregnancy_writetag[14])) {tvParam[14].setText(value);}
                            	  else if(tag.equals(item_pregnancy_writetag[15])) {tvParam[15].setText(value);}
                            	  else if(tag.equals(item_pregnancy_writetag[16])) {tvParam[16].setText(value);}
                            	  else if(tag.equals(item_pregnancy_writetag[17])) {tvParam[17].setText(value);}
                            	  else if(tag.equals(item_pregnancy_writetag[18])) {tvParam[18].setText(value);}
                            	  else if(tag.equals(item_pregnancy_writetag[19])) {tvParam[19].setText(value);}
                            	  else if(tag.equals(item_pregnancy_writetag[20])) {tvParam[20].setText(value);}
                            	  else if(tag.equals(item_pregnancy_writetag[21])) {tvParam[21].setText(value);}
                            	  else if(tag.equals(item_pregnancy_writetag[22])) {tvParam[22].setText(value);}
                            	  else if(tag.equals(item_pregnancy_writetag[23])) {tvParam[23].setText(value);}
                            	  else if(tag.equals(item_pregnancy_writetag[24])) {tvParam[24].setText(value);}
                            	  else if(tag.equals(item_pregnancy_writetag[25])) {tvParam[25].setText(value);}
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
        Intent intent_read_2 = new Intent(getApplicationContext(),pregnancy_write_healthp.class);
        startActivity(intent_read_2);
        finish();
    }



  //バックキーのアクション


}
