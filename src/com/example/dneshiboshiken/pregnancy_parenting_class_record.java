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

public class pregnancy_parenting_class_record extends Activity {

	//目次の項目だけボタンを定義
	private Button button_Write_child_9_12_1_back;
	private Button button_Write_child_9_12_1_home;
	private Button button_Write_child_9_12_1_next;
	final boolean[] checkedItems = new boolean[10];

	private String[] item_checkup_8tag = {"EditText_pregnancy_class_examination1",
			"EditText_pregnancy_class_examination2",
			"EditText_pregnancy_class_examination3",
			"EditText_pregnancy_class_examination4",
			"EditText_pregnancy_class_examination5",
			"EditText_pregnancy_class_examination6",
			"EditText_pregnancy_class_examination7",
			"EditText_pregnancy_class_examination8",
			"EditText_pregnancy_class_topic1",
			"EditText_pregnancy_class_topic2",
			"EditText_pregnancy_class_topic3",
			"EditText_pregnancy_class_topic4",
			"EditText_pregnancy_class_topic5",
			"EditText_pregnancy_class_topic6",
			"EditText_pregnancy_class_topic7",
			"EditText_pregnancy_class_topic8",
			"EditText_pregnancy_class_other1",
			"EditText_pregnancy_class_other2",
			"EditText_pregnancy_class_other3",
			"EditText_pregnancy_class_other4",
			"EditText_pregnancy_class_other5",
			"EditText_pregnancy_class_other6",
			"EditText_pregnancy_class_other7",
			"EditText_pregnancy_class_other8"
			};
	public static int[] item_checkup_8 = {R.id.TextView_pregnancy_class_examination1,
		R.id.TextView_pregnancy_class_examination2,
		R.id.TextView_pregnancy_class_examination3,
		R.id.TextView_pregnancy_class_examination4,
		R.id.TextView_pregnancy_class_examination5,
		R.id.TextView_pregnancy_class_examination6,
		R.id.TextView_pregnancy_class_examination7,
		R.id.TextView_pregnancy_class_examination8,
		R.id.TextView_pregnancy_class_topic1,
		R.id.TextView_pregnancy_class_topic2,
		R.id.TextView_pregnancy_class_topic3,
		R.id.TextView_pregnancy_class_topic4,
		R.id.TextView_pregnancy_class_topic5,
		R.id.TextView_pregnancy_class_topic6,
		R.id.TextView_pregnancy_class_topic7,
		R.id.TextView_pregnancy_class_topic8,
		R.id.TextView_pregnancy_class_other1,
		R.id.TextView_pregnancy_class_other2,
		R.id.TextView_pregnancy_class_other3,
		R.id.TextView_pregnancy_class_other4,
		R.id.TextView_pregnancy_class_other5,
		R.id.TextView_pregnancy_class_other6,
		R.id.TextView_pregnancy_class_other7,
		R.id.TextView_pregnancy_class_other8,};
	public static int[] item_checkup_check_textview_8 = {};
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pregnancy_parenting_class_record);	//画面レイアウトを指定(res/layout/index_read.xml)
        TextView[] tvParam = new TextView[item_checkup_8.length];

        // 読み込み
        //xmlファイル読み込み
          File dir = new File(Environment.getExternalStorageDirectory().getPath());
          if(dir.exists()){

              File file = new File(dir.getAbsolutePath()+"/Yukari/Write/Pregnancy/parenting_class_recordfile.xml");
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
        Intent intent_read_2 = new Intent(getApplicationContext(),pregnancy_parenting_class_recordp.class);
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
    



  //バックキーのアクション


}
