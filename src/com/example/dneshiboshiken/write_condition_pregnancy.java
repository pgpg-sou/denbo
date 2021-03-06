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

public class write_condition_pregnancy extends Activity {

	//目次の項目だけボタンを定義
	private Button button_Write_child_9_12_1_back;
	private Button button_Write_child_9_12_1_home;
	private Button button_Write_child_9_12_1_next;
	final boolean[] checkedItems = new boolean[10];

	private String[] item_checkup_10tag = {"EditText_write_condition_pregnancy1",
			"EditText_write_condition_pregnancy4",
			"EditText_write_condition_pregnancy5",
			"EditText_write_condition_pregnancy6",
			"EditText_write_condition_pregnancy7",
			"EditText_write_condition_pregnancy8",
			"EditText_write_condition_pregnancy9",
			"EditText_write_condition_pregnancy10",
			"EditText_write_condition_pregnancy11"
			};
	public static int[] item_checkup_10 = {
		R.id.TextView_pregnancy_home_situation_occupation,
		R.id.TextView_pregnancy_home_situation_environment,
		R.id.TextView_pregnancy_home_situation_hours,
		R.id.TextView_pregnancy_home_situation_time_start,
		R.id.TextView_pregnancy_home_situation_time_finish,
		R.id.TextView_pregnancy_home_situation_how,
		R.id.TextView_pregnancy_home_situation_length,
		R.id.TextView_pregnancy_home_situation_condition_week1,
		R.id.TextView_pregnancy_home_situation_condition_month1,
		R.id.TextView_pregnancy_home_situation_condition_week2,
		R.id.TextView_pregnancy_home_situation_condition_month2,
		R.id.TextView_pregnancy_home_situation_condition_week3,
		R.id.TextView_pregnancy_home_situation_condition_month3,
		R.id.TextView_pregnancy_home_situation_condition_other,
		R.id.TextView_pregnancy_home_situation_before_start,
		R.id.TextView_pregnancy_home_situation_before_day,
		R.id.TextView_pregnancy_home_situation_after_start,
		R.id.TextView_pregnancy_home_situation_after_day,
		R.id.TextView_pregnancy_home_situation_leave_start1,
		R.id.TextView_pregnancy_home_situation_leave_finish1,
		R.id.TextView_pregnancy_home_situation_leave_start2,
		R.id.TextView_pregnancy_home_situation_leave_finish2,
		R.id.TextView_pregnancy_home_situation_living_other,
		R.id.TextView_pregnancy_home_situation_with,
		R.id.TextView_pregnancy_home_situation_with_children,
		R.id.TextView_pregnancy_home_situation_with_other,
		R.id.TextView_pregnancy_home_situation_irregular,
		R.id.TextView_pregnancy_home_situation_crowded,
		R.id.TextView_pregnancy_home_situation_living,
		R.id.TextView_pregnancy_home_situation_noise,
		R.id.TextView_pregnancy_home_situation_sun,
		};
	public static int[] item_checkup_check_textview_10 = {};
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pregnancy_home_situation);	//画面レイアウトを指定(res/layout/index_read.xml)
        TextView[] tvParam = new TextView[item_checkup_10.length];
        // 読み込み
        //xmlファイル読み込み
          File dir = new File(Environment.getExternalStorageDirectory().getPath());
          if(dir.exists()){

              File file = new File(dir.getAbsolutePath()+"/Yukari/Write/Pregnancy/pregnancy_home_situationp.xml");
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
                            	  else if(tag.equals(item_checkup_10tag[1])) {tvParam[1].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[2])) {tvParam[2].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[3])) {tvParam[3].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[4])) {tvParam[4].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[5])) {tvParam[5].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[6])) {tvParam[6].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[7])) {tvParam[7].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[8])) {tvParam[8].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[9])) {tvParam[9].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[10])) {tvParam[10].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[11])) {tvParam[11].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[12])) {tvParam[12].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[13])) {tvParam[13].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[14])) {tvParam[14].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[15])) {tvParam[15].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[16])) {tvParam[16].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[17])) {tvParam[17].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[18])) {tvParam[18].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[19])) {tvParam[19].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[20])) {tvParam[20].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[21])) {tvParam[21].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[22])) {tvParam[22].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[23])) {tvParam[23].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[24])) {tvParam[24].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[25])) {tvParam[25].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[26])) {tvParam[26].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[27])) {tvParam[27].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[28])) {tvParam[28].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[29])) {tvParam[29].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[30])) {tvParam[30].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[31])) {tvParam[31].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[32])) {tvParam[32].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[33])) {tvParam[33].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[34])) {tvParam[34].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[35])) {tvParam[35].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[36])) {tvParam[36].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[37])) {tvParam[37].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[38])) {tvParam[38].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[39])) {tvParam[39].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[40])) {tvParam[40].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[41])) {tvParam[41].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[42])) {tvParam[42].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[43])) {tvParam[43].setText(value);}
                            	  else if(tag.equals(item_checkup_10tag[44])) {tvParam[44].setText(value);}
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
        execCheckBox();

    }
  //ボタンクリックによって呼び出される処理
    //classの呼び出し(EMCHH.java)で行った内容と同様
    private void button_Write_child_9_12_1_back_onClick() {
        Intent intent_read_1 = new Intent(getApplicationContext(),WriteCheckup_9.class);
        startActivity(intent_read_1);
    }

    private void button_Write_child_9_12_1_home_onClick() {
        Intent intent_read_2 = new Intent(getApplicationContext(),pregnancy_home_situationp.class);
        startActivity(intent_read_2);
        finish();
    }

    private void button_Write_child_9_12_1_next_onClick() {
        Intent intent_read_3 = new Intent(getApplicationContext(),WriteCheckup_11.class);
        startActivity(intent_read_3);
    }

    //注射済みの色を買えます
	private void execCheckBox() {
		for (int i1 = 0; i1 < item_checkup_check_textview_10.length; i1++) {
			TextView edittext1=(TextView)findViewById(item_checkup_check_textview_10[i1]);
			if(checkedItems[i1]==true)
			edittext1.setTextColor(Color.BLACK);
		}
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





  //バックキーのアクション


}
