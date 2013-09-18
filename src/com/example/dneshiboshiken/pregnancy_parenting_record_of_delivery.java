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

public class pregnancy_parenting_record_of_delivery extends Activity {

	//目次の項目だけボタンを定義
	private String filepath = "/Yukari/Write/Pregnancy/pregnancy_parenting_record_of_delivery.xml";
	private Button button_Write_child_9_12_1_back;
	private Button button_Write_child_9_12_1_home;
	private Button button_Write_child_9_12_1_next;
	final boolean[] checkedItems = new boolean[10];

	private String[] item_checkup_8tag = {"EditText_pregnancy_delivery_length_week",
			"EditText_pregnancy_delivery_length_day",
			"EditText_pregnancy_delivery_date",
			"EditText_pregnancy_delivery_date_time",
			"EditText_pregnancy_delivery_type_other",
			"EditText_pregnancy_delivery_way",
			"EditText_pregnancy_delivery_time",
			"EditText_pregnancy_delivery_bleeding_other",
			"EditText_pregnancy_delivery_blood_transfusion_other",
			"EditText_pregnancy_delivery_number_other",
			"EditText_pregnancy_delivery_weight",
			"EditText_pregnancy_delivery_height",
			"EditText_pregnancy_delivery_chest",
			"EditText_pregnancy_delivery_head",
			"EditText_pregnancy_delivery_special",
			"EditText_pregnancy_delivery_place",
			"EditText_pregnancy_delivery_doctor",
			"EditText_pregnancy_delivery_midwife",
			"EditText_pregnancy_delivery_other",
			"Spinner_pregnancy_delivery_type_position",
			"Spinner_pregnancy_delivery_bleeding",
			"Spinner_pregnancy_delivery_blood_transfusion",
			"Spinner_pregnancy_delivery_sex",
			"Spinner_pregnancy_delivery_number",
			"Spinner_pregnancy_delivery_certificate",
			};
	public static int[] item_checkup_8 = {R.id.TextView_pregnancy_delivery_length_week,
		R.id.TextView_pregnancy_delivery_length_day,
		R.id.TextView_pregnancy_delivery_date,
		R.id.TextView_pregnancy_delivery_date_time,
		R.id.TextView_pregnancy_delivery_type_other,
		R.id.TextView_pregnancy_delivery_way,
		R.id.TextView_pregnancy_delivery_time,
		R.id.TextView_pregnancy_delivery_bleeding_other,
		R.id.TextView_pregnancy_delivery_blood_transfusion_other,
		R.id.TextView_pregnancy_delivery_number_other,
		R.id.TextView_pregnancy_delivery_weight,
		R.id.TextView_pregnancy_delivery_height,
		R.id.TextView_pregnancy_delivery_chest,
		R.id.TextView_pregnancy_delivery_head,
		R.id.TextView_pregnancy_delivery_special,
		R.id.TextView_pregnancy_delivery_place,
		R.id.TextView_pregnancy_delivery_doctor,
		R.id.TextView_pregnancy_delivery_midwife,
		R.id.TextView_pregnancy_delivery_other,
		R.id.TextView_pregnancy_delivery_type_position,
		R.id.TextView_pregnancy_delivery_bleeding,
		R.id.TextView_pregnancy_delivery_blood_transfusion,
		R.id.TextView_pregnancy_delivery_sex,
		R.id.TextView_pregnancy_delivery_number,
		R.id.TextView_pregnancy_delivery_certificate,};

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pregnancy_record_of_delivery);	//画面レイアウトを指定(res/layout/index_read.xml)
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
                            	  else if(tag.equals(item_checkup_8tag[4])) {tvParam[4].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[5])) {tvParam[5].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[6])) {tvParam[6].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[7])) {tvParam[7].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[8])) {tvParam[8].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[9])) {tvParam[9].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[10])) {tvParam[10].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[11])) {tvParam[11].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[12])) {tvParam[12].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[13])) {tvParam[13].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[14])) {tvParam[14].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[15])) {tvParam[15].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[16])) {tvParam[16].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[17])) {tvParam[17].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[18])) {tvParam[18].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[19])) {tvParam[19].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[20])) {tvParam[20].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[21])) {tvParam[21].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[22])) {tvParam[22].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[23])) {tvParam[23].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[24])) {tvParam[24].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[25])) {tvParam[25].setText(value);}
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
        Intent intent_read_2 = new Intent(getApplicationContext(),pregnancy_parenting_record_of_deliveryp.class);
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

  //onOPtionsItemSelectedメソッド(メニューアイテム選択処理)
    @Override
     public boolean onOptionsItemSelected(MenuItem item){
    	 switch (item.getItemId()){
    	 case 0:
    		 Intent intent1 = new Intent();
    		 intent1.setClass(pregnancy_parenting_record_of_delivery.this, MainActivity.class);
    		 startActivity(intent1);
    		 return true;
    	 case 1:
    		 Intent intent2 = new Intent();
    		 intent2.setClass(pregnancy_parenting_record_of_delivery.this, MainActivity.class);
    		 startActivity(intent2);
    		 return true;
    	 }
    	 return true;
    }



  //バックキーのアクション


}
