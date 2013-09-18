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

public class WriteCheckup_13 extends Activity {

	//目次の項目だけボタンを定義
	private Button button_Write_child_9_12_1_back;
	private Button button_Write_child_9_12_1_home;
	private Button button_Write_child_9_12_1_next;
	final boolean[] checkedItems = new boolean[10];

	private String[] item_checkup_13tag = {"EditText_write_checkup_13p_examination",
			"EditText_write_checkup_13p_weight",
			"EditText_write_checkup_13p_height",
			"EditText_write_checkup_13p_head",
			"EditText_write_checkup_13p_eye_other",
			"EditText_write_checkup_13p_eyelevel_right",
			"EditText_write_checkup_13p_eyelevel_left",
			"EditText_write_checkup_13p_ears_other",
			"EditText_write_checkup_13p_immunization_text",
			"EditText_write_checkup_13p_health",
			"EditText_write_checkup_13p_tooth_number_top0",
			"EditText_write_checkup_13p_tooth_number_top1",
			"EditText_write_checkup_13p_tooth_number_top2",
			"EditText_write_checkup_13p_tooth_number_top3",
			"EditText_write_checkup_13p_tooth_number_top4",
			"EditText_write_checkup_13p_tooth_number_top5",
			"EditText_write_checkup_13p_tooth_number_top6",
			"EditText_write_checkup_13p_tooth_number_top7",
			"EditText_write_checkup_13p_tooth_number_top8",
			"EditText_write_checkup_13p_tooth_number_top9",
			"EditText_write_checkup_13p_tooth_number_top10",
			"EditText_write_checkup_13p_tooth_number_top11",
			"EditText_write_checkup_13p_tooth_top0",
			"EditText_write_checkup_13p_tooth_top1",
			"EditText_write_checkup_13p_tooth_top2",
			"EditText_write_checkup_13p_tooth_top3",
			"EditText_write_checkup_13p_tooth_top4",
			"EditText_write_checkup_13p_tooth_top5",
			"EditText_write_checkup_13p_tooth_top6",
			"EditText_write_checkup_13p_tooth_top7",
			"EditText_write_checkup_13p_tooth_top8",
			"EditText_write_checkup_13p_tooth_top9",
			"EditText_write_checkup_13p_tooth_top10",
			"EditText_write_checkup_13p_tooth_top11",
			"EditText_write_checkup_13p_tooth_bottom0",
			"EditText_write_checkup_13p_tooth_bottom1",
			"EditText_write_checkup_13p_tooth_bottom2",
			"EditText_write_checkup_13p_tooth_bottom3",
			"EditText_write_checkup_13p_tooth_bottom4",
			"EditText_write_checkup_13p_tooth_bottom5",
			"EditText_write_checkup_13p_tooth_bottom6",
			"EditText_write_checkup_13p_tooth_bottom7",
			"EditText_write_checkup_13p_tooth_bottom8",
			"EditText_write_checkup_13p_tooth_bottom9",
			"EditText_write_checkup_13p_tooth_bottom10",
			"EditText_write_checkup_13p_tooth_bottom11",
			"EditText_write_checkup_13p_tooth_number_bottom0",
			"EditText_write_checkup_13p_tooth_number_bottom1",
			"EditText_write_checkup_13p_tooth_number_bottom2",
			"EditText_write_checkup_13p_tooth_number_bottom3",
			"EditText_write_checkup_13p_tooth_number_bottom4",
			"EditText_write_checkup_13p_tooth_number_bottom5",
			"EditText_write_checkup_13p_tooth_number_bottom6",
			"EditText_write_checkup_13p_tooth_number_bottom7",
			"EditText_write_checkup_13p_tooth_number_bottom8",
			"EditText_write_checkup_13p_tooth_number_bottom9",
			"EditText_write_checkup_13p_tooth_number_bottom10",
			"EditText_write_checkup_13p_tooth_number_bottom11",
			"EditText_write_checkup_13p_tooth_need_number_child",
			"EditText_write_checkup_13p_tooth_need_number_young",
			"EditText_write_checkup_13p_tooth_gums_other",
			"EditText_write_checkup_13p_tooth_shippei",
			"EditText_write_checkup_13p_tooth_examination",
			"EditText_write_checkup_13p_other",
			"EditText_write_checkup_13p_name",
			};
	public static int[] item_checkup_13 = {R.id.TextView_write_checkup_13_examination,
		R.id.TextView_write_checkup_13_weight,
		R.id.TextView_write_checkup_13_height,
		R.id.TextView_write_checkup_13_head,
		R.id.TextView_write_checkup_13_eye_other,
		R.id.TextView_write_checkup_13_eyelevel_right,
		R.id.TextView_write_checkup_13_eyelevel_left,
		R.id.TextView_write_checkup_13_ears_other,
		R.id.TextView_write_checkup_13_health,
		R.id.TextView_write_checkup_13_health,
		R.id.TextView_write_checkup_13_tooth_number_top0,
		R.id.TextView_write_checkup_13_tooth_number_top1,
		R.id.TextView_write_checkup_13_tooth_number_top2,
		R.id.TextView_write_checkup_13_tooth_number_top3,
		R.id.TextView_write_checkup_13_tooth_number_top4,
		R.id.TextView_write_checkup_13_tooth_number_top5,
		R.id.TextView_write_checkup_13_tooth_number_top6,
		R.id.TextView_write_checkup_13_tooth_number_top7,
		R.id.TextView_write_checkup_13_tooth_number_top8,
		R.id.TextView_write_checkup_13_tooth_number_top9,
		R.id.TextView_write_checkup_13_tooth_number_top10,
		R.id.TextView_write_checkup_13_tooth_number_top11,
		R.id.TextView_write_checkup_13_tooth_top0,
		R.id.TextView_write_checkup_13_tooth_top1,
		R.id.TextView_write_checkup_13_tooth_top2,
		R.id.TextView_write_checkup_13_tooth_top3,
		R.id.TextView_write_checkup_13_tooth_top4,
		R.id.TextView_write_checkup_13_tooth_top5,
		R.id.TextView_write_checkup_13_tooth_top6,
		R.id.TextView_write_checkup_13_tooth_top7,
		R.id.TextView_write_checkup_13_tooth_top8,
		R.id.TextView_write_checkup_13_tooth_top9,
		R.id.TextView_write_checkup_13_tooth_top10,
		R.id.TextView_write_checkup_13_tooth_top11,
		R.id.TextView_write_checkup_13_tooth_bottom0,
		R.id.TextView_write_checkup_13_tooth_bottom1,
		R.id.TextView_write_checkup_13_tooth_bottom2,
		R.id.TextView_write_checkup_13_tooth_bottom3,
		R.id.TextView_write_checkup_13_tooth_bottom4,
		R.id.TextView_write_checkup_13_tooth_bottom5,
		R.id.TextView_write_checkup_13_tooth_bottom6,
		R.id.TextView_write_checkup_13_tooth_bottom7,
		R.id.TextView_write_checkup_13_tooth_bottom8,
		R.id.TextView_write_checkup_13_tooth_bottom9,
		R.id.TextView_write_checkup_13_tooth_bottom10,
		R.id.TextView_write_checkup_13_tooth_bottom11,
		R.id.TextView_write_checkup_13_tooth_number_bottom0,
		R.id.TextView_write_checkup_13_tooth_number_bottom1,
		R.id.TextView_write_checkup_13_tooth_number_bottom2,
		R.id.TextView_write_checkup_13_tooth_number_bottom3,
		R.id.TextView_write_checkup_13_tooth_number_bottom4,
		R.id.TextView_write_checkup_13_tooth_number_bottom5,
		R.id.TextView_write_checkup_13_tooth_number_bottom6,
		R.id.TextView_write_checkup_13_tooth_number_bottom7,
		R.id.TextView_write_checkup_13_tooth_number_bottom8,
		R.id.TextView_write_checkup_13_tooth_number_bottom9,
		R.id.TextView_write_checkup_13_tooth_number_bottom10,
		R.id.TextView_write_checkup_13_tooth_number_bottom11,
		R.id.TextView_write_checkup_13_tooth_need_number_child,
		R.id.TextView_write_checkup_13_tooth_need_number_young,
		R.id.TextView_write_checkup_13_tooth_gums_other,
		R.id.TextView_write_checkup_13_tooth_shippei,
		R.id.TextView_write_checkup_13_tooth_examination,
		R.id.TextView_write_checkup_13_other,
		R.id.TextView_write_checkup_13_name,};
	public static int[] item_checkup_check_textview_13 = {R.id.TextView_write_checkup_13_immunization_diphteria,
		R.id.TextView_write_checkup_13_immunization_pertussis,
		R.id.TextView_write_checkup_13_immunization_tetanus,
		R.id.TextView_write_checkup_13_immunization_polio,
		R.id.TextView_write_checkup_13_immunization_bcg,
		R.id.TextView_write_checkup_13_immunization_measles,
		R.id.TextView_write_checkup_13_immunization_rubella,};
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_checkup_13);	//画面レイアウトを指定(res/layout/index_read.xml)
        TextView[] tvParam = new TextView[item_checkup_13.length];

        // 読み込み
        //xmlファイル読み込み
          File dir = new File(Environment.getExternalStorageDirectory().getPath());
          if(dir.exists()){

              File file = new File(dir.getAbsolutePath()+"/Yukari/Write/Checkup/Writecheckup_13file.xml");
              if (file.exists()) {
              	try {
              		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
              		factory.setNamespaceAware(true);
              		XmlPullParser parser = factory.newPullParser();
              		FileInputStream fis = new FileInputStream(file);
              		parser.setInput(new InputStreamReader(fis));

                      // TextViewインスタンスの取得
              		for (int i1 = 0; i1 < item_checkup_13.length; i1++) {
                    	tvParam[i1] = (TextView)findViewById(item_checkup_13[i1]);
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
                            	  if(tag.equals(item_checkup_13tag[0])) {tvParam[0].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[1])) {tvParam[1].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[2])) {tvParam[2].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[3])) {tvParam[3].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[4])) {tvParam[4].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[5])) {tvParam[5].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[6])) {tvParam[6].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[7])) {tvParam[7].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[8])) {tvParam[8].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[9])) {tvParam[9].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[10])) {tvParam[10].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[11])) {tvParam[11].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[12])) {tvParam[12].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[13])) {tvParam[13].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[14])) {tvParam[14].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[15])) {tvParam[15].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[16])) {tvParam[16].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[17])) {tvParam[17].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[18])) {tvParam[18].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[19])) {tvParam[19].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[20])) {tvParam[20].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[21])) {tvParam[21].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[22])) {tvParam[22].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[23])) {tvParam[23].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[24])) {tvParam[24].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[25])) {tvParam[25].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[26])) {tvParam[26].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[27])) {tvParam[27].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[28])) {tvParam[28].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[29])) {tvParam[29].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[30])) {tvParam[30].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[31])) {tvParam[31].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[32])) {tvParam[32].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[33])) {tvParam[33].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[34])) {tvParam[34].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[35])) {tvParam[35].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[36])) {tvParam[36].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[37])) {tvParam[37].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[38])) {tvParam[38].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[39])) {tvParam[39].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[40])) {tvParam[40].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[41])) {tvParam[41].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[42])) {tvParam[42].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[43])) {tvParam[43].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[44])) {tvParam[44].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[45])) {tvParam[45].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[46])) {tvParam[46].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[47])) {tvParam[47].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[48])) {tvParam[48].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[49])) {tvParam[49].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[50])) {tvParam[50].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[51])) {tvParam[51].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[52])) {tvParam[52].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[53])) {tvParam[53].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[54])) {tvParam[54].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[55])) {tvParam[55].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[56])) {tvParam[56].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[57])) {tvParam[57].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[58])) {tvParam[58].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[59])) {tvParam[59].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[60])) {tvParam[60].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[61])) {tvParam[61].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[62])) {tvParam[62].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[63])) {tvParam[63].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[64])) {tvParam[64].setText(value);}
                            	  else if(tag.equals(item_checkup_13tag[44])) {checkedItems[0] = Boolean.valueOf( value );}
                            	  else if(tag.equals(item_checkup_13tag[45])) {checkedItems[1] = Boolean.valueOf( value );}
                            	  else if(tag.equals(item_checkup_13tag[46])) {checkedItems[2] = Boolean.valueOf( value );}
                            	  else if(tag.equals(item_checkup_13tag[47])) {checkedItems[3] = Boolean.valueOf( value );}
                            	  else if(tag.equals(item_checkup_13tag[48])) {checkedItems[4] = Boolean.valueOf( value );}
                            	  else if(tag.equals(item_checkup_13tag[49])) {checkedItems[5] = Boolean.valueOf( value );}
                            	  else if(tag.equals(item_checkup_13tag[50])) {checkedItems[6] = Boolean.valueOf( value );}
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
              File view = new File(dir.getAbsolutePath()+"/Yukari/Photo/imageView_write_checkup_13p.png");
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

              	((ImageView)findViewById(R.id.imageview_write_checkup_13)).setImageBitmap(image);
              }else{
                  //存在しない
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
        Intent intent_read_1 = new Intent(getApplicationContext(),WriteCheckup_12.class);
        startActivity(intent_read_1);
        ImageView iv = (ImageView)findViewById(R.id.imageview_write_checkup_13);
        iv.setImageDrawable(null);
    }

    private void button_Write_child_9_12_1_home_onClick() {
        Intent intent_read_2 = new Intent(getApplicationContext(),WriteCheckup_13p.class);
        startActivity(intent_read_2);
        ImageView iv = (ImageView)findViewById(R.id.imageview_write_checkup_13);
        iv.setImageDrawable(null);
        finish();
    }

    private void button_Write_child_9_12_1_next_onClick() {
    	Toast.makeText(this, "ここで終わりです", Toast.LENGTH_LONG).show();
    }

    //注射済みの色を買えます
	private void execCheckBox() {
		for (int i1 = 0; i1 < item_checkup_check_textview_13.length; i1++) {
			TextView edittext1=(TextView)findViewById(item_checkup_check_textview_13[i1]);
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

  //onOPtionsItemSelectedメソッド(メニューアイテム選択処理)
    @Override
     public boolean onOptionsItemSelected(MenuItem item){
    	 switch (item.getItemId()){
    	 case 0:
    		 Intent intent1 = new Intent();
    		 intent1.setClass(WriteCheckup_13.this, MainActivity.class);
    		 startActivity(intent1);
    		 return true;
    	 case 1:
    		 Intent intent2 = new Intent();
    		 intent2.setClass(WriteCheckup_13.this, MainActivity.class);
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



  //バックキーのアクション


}
