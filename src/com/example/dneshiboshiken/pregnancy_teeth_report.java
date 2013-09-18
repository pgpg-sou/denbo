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

public class pregnancy_teeth_report extends Activity {


	//目次の項目だけボタンを定義
	private String filepath = "/Yukari/Write/Pregnancy/pregnancy_teeth_report.xml";
	private Button button_Write_child_9_12_1_back;
	private Button button_Write_child_9_12_1_home;
	private Button button_Write_child_9_12_1_next;
	final boolean[] checkedItems = new boolean[10];

	private String[] item_checkup_8tag = {"EditText_teeth_report_examination1",
			"EditText_teeth_report_week1",
			"EditText_teeth_report_need_number1",
			"EditText_teeth_report_other1",
			"EditText_teeth_report_name1",
			"EditText_teeth_report_week2",
			"EditTextteeth_report_top1_1",
			"EditTextteeth_report_top2_1",
			"EditTextteeth_report_top3_1",
			"EditTextteeth_report_top4_1",
			"EditTextteeth_report_top5_1",
			"EditTextteeth_report_top6_1",
			"EditTextteeth_report_top7_1",
			"EditTextteeth_report_top8_1",
			"EditTextteeth_report_top9_1",
			"EditTextteeth_report_top10_1",
			"EditTextteeth_report_top11_1",
			"EditTextteeth_report_top12_1",
			"EditTextteeth_report_top13_1",
			"EditTextteeth_report_top14_1",
			"EditTextteeth_report_top15_1",
			"EditTextteeth_report_top16_1",
			"EditText_teeth_report_bottom1_1",
			"EditText_teeth_report_bottom2_1",
			"EditText_teeth_report_bottom3_1",
			"EditText_teeth_report_bottom4_1",
			"EditText_teeth_report_bottom5_1",
			"EditText_teeth_report_bottom6_1",
			"EditText_teeth_report_bottom7_1",
			"EditText_teeth_report_bottom8_1",
			"EditText_teeth_report_bottom9_1",
			"EditText_teeth_report_bottom10_1",
			"EditText_teeth_report_bottom11_1",
			"EditText_teeth_report_bottom12_1",
			"EditText_teeth_report_bottom13_1",
			"EditText_teeth_report_bottom14_1",
			"EditText_teeth_report_bottom15_1",
			"EditText_teeth_report_bottom16_1",
			"EditText_teeth_report_other2",
			"EditText_teeth_report_examination2",
			"EditText_teeth_report_name2",
			"EditText_teeth_report_week3",
			"EditTextteeth_report_top1_2",
			"EditTextteeth_report_top2_2",
			"EditTextteeth_report_top3_2",
			"EditTextteeth_report_top4_2",
			"EditTextteeth_report_top5_2",
			"EditTextteeth_report_top6_2",
			"EditTextteeth_report_top7_2",
			"EditTextteeth_report_top8_2",
			"EditTextteeth_report_top9_2",
			"EditTextteeth_report_top10_2",
			"EditTextteeth_report_top11_2",
			"EditTextteeth_report_top12_2",
			"EditTextteeth_report_top13_2",
			"EditTextteeth_report_top14_2",
			"EditTextteeth_report_top15_2",
			"EditTextteeth_report_top16_2",
			"EditText_teeth_report_bottom1_2",
			"EditText_teeth_report_bottom2_2",
			"EditText_teeth_report_bottom3_2",
			"EditText_teeth_report_bottom4_2",
			"EditText_teeth_report_bottom5_2",
			"EditText_teeth_report_bottom6_2",
			"EditText_teeth_report_bottom7_2",
			"EditText_teeth_report_bottom8_2",
			"EditText_teeth_report_bottom9_2",
			"EditText_teeth_report_bottom10_2",
			"EditText_teeth_report_bottom11_2",
			"EditText_teeth_report_bottom12_2",
			"EditText_teeth_report_bottom13_2",
			"EditText_teeth_report_bottom14_2",
			"EditText_teeth_report_bottom15_2",
			"EditText_teeth_report_bottom16_2",
			"EditText_teeth_report_other3",
			"EditText_teeth_report_examination3",
			"EditText_teeth_report_name3",
			"Spinner_teeth_report_need1",
			"Spinner_teeth_report_calculus1",
			"Spinner_teeth_report_inflammation1",
			"Spinner_teeth_report_pregnancy_or_after1",
			"Spinner_teeth_report_calculus2",
			"Spinner_teeth_report_inflammation2",
			"Spinner_teeth_report_pregnancy_or_after2",
			"Spinner_teeth_report_calculus3",
			"Spinner_teeth_report_inflammation3"
			};
	public static int[] item_checkup_8 = {R.id.TextView_teeth_report_examination1,
		R.id.TextView_teeth_report_week1,
		R.id.TextView_teeth_report_need_number1,
		R.id.TextView_teeth_report_other1,
		R.id.TextView_teeth_report_name1,
		R.id.TextView_teeth_report_week2,
		R.id.TextView_teeth_report_top1_1,
		R.id.TextView_teeth_report_top2_1,
		R.id.TextView_teeth_report_top3_1,
		R.id.TextView_teeth_report_top4_1,
		R.id.TextView_teeth_report_top5_1,
		R.id.TextView_teeth_report_top6_1,
		R.id.TextView_teeth_report_top7_1,
		R.id.TextView_teeth_report_top8_1,
		R.id.TextView_teeth_report_top9_1,
		R.id.TextView_teeth_report_top10_1,
		R.id.TextView_teeth_report_top11_1,
		R.id.TextView_teeth_report_top12_1,
		R.id.TextView_teeth_report_top13_1,
		R.id.TextView_teeth_report_top14_1,
		R.id.TextView_teeth_report_top15_1,
		R.id.TextView_teeth_report_top16_1,
		R.id.TextView_teeth_report_bottom1_1,
		R.id.TextView_teeth_report_bottom2_1,
		R.id.TextView_teeth_report_bottom3_1,
		R.id.TextView_teeth_report_bottom4_1,
		R.id.TextView_teeth_report_bottom5_1,
		R.id.TextView_teeth_report_bottom6_1,
		R.id.TextView_teeth_report_bottom7_1,
		R.id.TextView_teeth_report_bottom8_1,
		R.id.TextView_teeth_report_bottom9_1,
		R.id.TextView_teeth_report_bottom10_1,
		R.id.TextView_teeth_report_bottom11_1,
		R.id.TextView_teeth_report_bottom12_1,
		R.id.TextView_teeth_report_bottom13_1,
		R.id.TextView_teeth_report_bottom14_1,
		R.id.TextView_teeth_report_bottom15_1,
		R.id.TextView_teeth_report_bottom16_1,
		R.id.TextView_teeth_report_other2,
		R.id.TextView_teeth_report_examination2,
		R.id.TextView_teeth_report_name2,
		R.id.TextView_teeth_report_week3,
		R.id.TextView_teeth_report_top1_2,
		R.id.TextView_teeth_report_top2_2,
		R.id.TextView_teeth_report_top3_2,
		R.id.TextView_teeth_report_top4_2,
		R.id.TextView_teeth_report_top5_2,
		R.id.TextView_teeth_report_top6_2,
		R.id.TextView_teeth_report_top7_2,
		R.id.TextView_teeth_report_top8_2,
		R.id.TextView_teeth_report_top9_2,
		R.id.TextView_teeth_report_top10_2,
		R.id.TextView_teeth_report_top11_2,
		R.id.TextView_teeth_report_top12_2,
		R.id.TextView_teeth_report_top13_2,
		R.id.TextView_teeth_report_top14_2,
		R.id.TextView_teeth_report_top15_2,
		R.id.TextView_teeth_report_top16_2,
		R.id.TextView_teeth_report_bottom1_2,
		R.id.TextView_teeth_report_bottom2_2,
		R.id.TextView_teeth_report_bottom3_2,
		R.id.TextView_teeth_report_bottom4_2,
		R.id.TextView_teeth_report_bottom5_2,
		R.id.TextView_teeth_report_bottom6_2,
		R.id.TextView_teeth_report_bottom7_2,
		R.id.TextView_teeth_report_bottom8_2,
		R.id.TextView_teeth_report_bottom9_2,
		R.id.TextView_teeth_report_bottom10_2,
		R.id.TextView_teeth_report_bottom11_2,
		R.id.TextView_teeth_report_bottom12_2,
		R.id.TextView_teeth_report_bottom13_2,
		R.id.TextView_teeth_report_bottom14_2,
		R.id.TextView_teeth_report_bottom15_2,
		R.id.TextView_teeth_report_bottom16_2,
		R.id.TextView_teeth_report_other3,
		R.id.TextView_teeth_report_examination3,
		R.id.TextView_teeth_report_name3,
		R.id.TextView_teeth_report_need1,
		R.id.TextView_teeth_report_calculus1,
		R.id.TextView_teeth_report_inflammation1,
		R.id.TextView_teeth_report_pregnancy_or_after1,
		R.id.TextView_teeth_report_calculus2,
		R.id.TextView_teeth_report_inflammation2,
		R.id.TextView_teeth_report_pregnancy_or_after2,
		R.id.TextView_teeth_report_calculus3,
		R.id.TextView_teeth_report_inflammation3,};

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pregnancy_teeth_report);	//画面レイアウトを指定(res/layout/index_read.xml)
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
                            	  else if(tag.equals(item_checkup_8tag[26])) {tvParam[26].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[27])) {tvParam[27].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[28])) {tvParam[28].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[29])) {tvParam[29].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[30])) {tvParam[30].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[31])) {tvParam[31].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[32])) {tvParam[32].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[33])) {tvParam[33].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[34])) {tvParam[34].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[35])) {tvParam[35].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[36])) {tvParam[36].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[37])) {tvParam[37].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[38])) {tvParam[38].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[39])) {tvParam[39].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[40])) {tvParam[40].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[41])) {tvParam[41].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[42])) {tvParam[42].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[43])) {tvParam[43].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[44])) {tvParam[44].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[45])) {tvParam[45].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[46])) {tvParam[46].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[47])) {tvParam[47].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[48])) {tvParam[48].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[49])) {tvParam[49].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[50])) {tvParam[50].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[51])) {tvParam[51].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[52])) {tvParam[52].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[53])) {tvParam[53].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[54])) {tvParam[54].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[55])) {tvParam[55].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[56])) {tvParam[56].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[57])) {tvParam[57].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[58])) {tvParam[58].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[59])) {tvParam[59].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[60])) {tvParam[60].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[61])) {tvParam[61].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[62])) {tvParam[62].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[63])) {tvParam[63].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[64])) {tvParam[64].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[65])) {tvParam[65].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[66])) {tvParam[66].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[67])) {tvParam[67].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[68])) {tvParam[68].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[69])) {tvParam[69].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[70])) {tvParam[70].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[71])) {tvParam[71].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[72])) {tvParam[72].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[73])) {tvParam[73].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[74])) {tvParam[74].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[75])) {tvParam[75].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[76])) {tvParam[76].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[77])) {tvParam[77].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[78])) {tvParam[78].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[79])) {tvParam[79].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[80])) {tvParam[80].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[81])) {tvParam[81].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[82])) {tvParam[82].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[83])) {tvParam[83].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[84])) {tvParam[84].setText(value);}
                            	  else if(tag.equals(item_checkup_8tag[85])) {tvParam[85].setText(value);}
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
        Intent intent_read_2 = new Intent(getApplicationContext(),pregnancy_teeth_reportp.class);
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


  //バックキーのアクション


}

