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
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class WriteCheckup_7 extends Activity {

	//目次の項目だけボタンを定義
	private Button button_Write_child_9_12_1_back;
	private Button button_Write_child_9_12_1_home;
	private Button button_Write_child_9_12_1_next;

	private String[] item_checkup_7tag = {"EditText_write_checkup_7p_examination",
			"EditText_write_checkup_7p_weight",
			"EditText_write_checkup_7p_height",
			"EditText_write_checkup_7p_chest",
			"EditText_write_checkup_7p_head",
			"Spinner_write_checkup_7p_diet",
			"Spinner_write_checkup_7p_milk",
			"EditText_write_checkup_7p_meals_day",
			"EditText_write_checkup_7p_meals_other",
			"Spinner_write_checkup_7p_eye",
			"EditText_write_checkup_7p_mouth_other",
			"EditText_write_checkup_7p_health",
			"EditText_write_checkup_7p_tooth_top1",
			"EditText_write_checkup_7p_tooth_top2",
			"EditText_write_checkup_7p_tooth_top3",
			"EditText_write_checkup_7p_tooth_top4",
			"EditText_write_checkup_7p_tooth_top5",
			"EditText_write_checkup_7p_tooth_top6",
			"EditText_write_checkup_7p_tooth_top7",
			"EditText_write_checkup_7p_tooth_top8",
			"EditText_write_checkup_7p_tooth_top9",
			"EditText_write_checkup_7p_tooth_top10",
			"EditText_write_checkup_7p_tooth_bottom1",
			"EditText_write_checkup_7p_tooth_bottom2",
			"EditText_write_checkup_7p_tooth_bottom3",
			"EditText_write_checkup_7p_tooth_bottom4",
			"EditText_write_checkup_7p_tooth_bottom5",
			"EditText_write_checkup_7p_tooth_bottom6",
			"EditText_write_checkup_7p_tooth_bottom7",
			"EditText_write_checkup_7p_tooth_bottom8",
			"EditText_write_checkup_7p_tooth_bottom9",
			"EditText_write_checkup_7p_tooth_bottom10",
			"Spinner_write_checkup_7p_tooth_need",
			"EditText_write_checkup_7p_tooth_need_number",
			"Spinner_write_checkup_7p_tooth_hygiene",
			"Spinner_write_checkup_7p_tooth_gums",
			"EditText_write_checkup_7p_tooth_gums_other",
			"Spinner_write_checkup_7p_tooth_occlusion",
			"EditText_write_checkup_7p_tooth_examination",
			"EditText_write_checkup_7p_other",
			"EditText_write_checkup_7p_name",};

	public static int[] item_checkup_7 = {R.id.TextView_write_checkup_7_examination,
		R.id.TextView_write_checkup_7_weight,
		R.id.TextView_write_checkup_7_height,
		R.id.TextView_write_checkup_7_chest,
		R.id.TextView_write_checkup_7_head,
		R.id.TextView_write_checkup_7_diet,
		R.id.TextView_write_checkup_7_milk,
		R.id.TextView_write_checkup_7_meals_day,
		R.id.TextView_write_checkup_7_meals_other,
		R.id.TextView_write_checkup_7_eye,
		R.id.TextView_write_checkup_7_mouth_other,
		R.id.TextView_write_checkup_7_health,
		R.id.TextView_write_checkup_7_tooth_top1,
		R.id.TextView_write_checkup_7_tooth_top2,
		R.id.TextView_write_checkup_7_tooth_top3,
		R.id.TextView_write_checkup_7_tooth_top4,
		R.id.TextView_write_checkup_7_tooth_top5,
		R.id.TextView_write_checkup_7_tooth_top6,
		R.id.TextView_write_checkup_7_tooth_top7,
		R.id.TextView_write_checkup_7_tooth_top8,
		R.id.TextView_write_checkup_7_tooth_top9,
		R.id.TextView_write_checkup_7_tooth_top10,
		R.id.TextView_write_checkup_7_tooth_bottom1,
		R.id.TextView_write_checkup_7_tooth_bottom2,
		R.id.TextView_write_checkup_7_tooth_bottom3,
		R.id.TextView_write_checkup_7_tooth_bottom4,
		R.id.TextView_write_checkup_7_tooth_bottom5,
		R.id.TextView_write_checkup_7_tooth_bottom6,
		R.id.TextView_write_checkup_7_tooth_bottom7,
		R.id.TextView_write_checkup_7_tooth_bottom8,
		R.id.TextView_write_checkup_7_tooth_bottom9,
		R.id.TextView_write_checkup_7_tooth_bottom10,
		R.id.TextView_write_checkup_7_tooth_need,
		R.id.TextView_write_checkup_7_tooth_need_number,
		R.id.TextView_write_checkup_7_tooth_hygiene,
		R.id.TextView_write_checkup_7_tooth_gums,
		R.id.TextView_write_checkup_7_tooth_gums_other,
		R.id.TextView_write_checkup_7_tooth_occlusion,
		R.id.TextView_write_checkup_7_tooth_examination,
		R.id.TextView_write_checkup_7_other,
		R.id.TextView_write_checkup_7_name,
		};

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_checkup_7);	//画面レイアウトを指定(res/layout/index_read.xml)
        TextView[] tvParam = new TextView[item_checkup_7.length];


        // 読み込み
        //xmlファイル読み込み
          File dir = new File(Environment.getExternalStorageDirectory().getPath());
          if(dir.exists()){

              File file = new File(dir.getAbsolutePath()+"/Yukari/Write/Checkup/Writecheckup_7file.xml");
              if (file.exists()) {
              	try {
              		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
              		factory.setNamespaceAware(true);
              		XmlPullParser parser = factory.newPullParser();
              		FileInputStream fis = new FileInputStream(file);
              		parser.setInput(new InputStreamReader(fis));

                      // TextViewインスタンスの取得
              		for (int i1 = 0; i1 < item_checkup_7.length; i1++) {
                    	tvParam[i1] = (TextView)findViewById(item_checkup_7[i1]);
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
                            	  if(tag.equals(item_checkup_7tag[0])) {tvParam[0].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[1])) {tvParam[1].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[2])) {tvParam[2].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[3])) {tvParam[3].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[4])) {tvParam[4].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[5])) {tvParam[5].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[6])) {tvParam[6].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[7])) {tvParam[7].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[8])) {tvParam[8].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[9])) {tvParam[9].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[10])) {tvParam[10].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[11])) {tvParam[11].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[12])) {tvParam[12].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[13])) {tvParam[13].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[14])) {tvParam[14].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[15])) {tvParam[15].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[16])) {tvParam[16].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[17])) {tvParam[17].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[18])) {tvParam[18].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[19])) {tvParam[19].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[20])) {tvParam[20].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[21])) {tvParam[21].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[22])) {tvParam[22].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[23])) {tvParam[23].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[24])) {tvParam[24].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[25])) {tvParam[25].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[26])) {tvParam[26].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[27])) {tvParam[27].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[28])) {tvParam[28].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[29])) {tvParam[29].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[30])) {tvParam[30].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[31])) {tvParam[31].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[32])) {tvParam[32].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[33])) {tvParam[33].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[34])) {tvParam[34].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[35])) {tvParam[35].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[36])) {tvParam[36].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[37])) {tvParam[37].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[38])) {tvParam[38].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[39])) {tvParam[39].setText(value);}
                            	  else if(tag.equals(item_checkup_7tag[40])) {tvParam[40].setText(value);}
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
                      File view = new File(dir.getAbsolutePath()+"/Yukari/Photo/imageView_write_checkup_7p.png");
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

                      	((ImageView)findViewById(R.id.imageview_write_checkup_7)).setImageBitmap(image);
                      }else{
                          //存在しない
                      }
              }}
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

    }
  //ボタンクリックによって呼び出される処理
    //classの呼び出し(EMCHH.java)で行った内容と同様
    private void button_Write_child_9_12_1_back_onClick() {
        Intent intent_read_1 = new Intent(getApplicationContext(),WriteCheckup_6.class);
        startActivity(intent_read_1);
        ImageView iv = (ImageView)findViewById(R.id.imageview_write_checkup_7);
        iv.setImageDrawable(null);
    }

    private void button_Write_child_9_12_1_home_onClick() {
        Intent intent_read_2 = new Intent(getApplicationContext(),WriteCheckup_7p.class);
        startActivity(intent_read_2);
        ImageView iv = (ImageView)findViewById(R.id.imageview_write_checkup_7);
        iv.setImageDrawable(null);
        finish();
    }

    private void button_Write_child_9_12_1_next_onClick() {
        Intent intent_read_3 = new Intent(getApplicationContext(),WriteCheckup_8.class);
        startActivity(intent_read_3);
        ImageView iv = (ImageView)findViewById(R.id.imageview_write_checkup_7);
        iv.setImageDrawable(null);
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
    		 intent1.setClass(WriteCheckup_7.this, MainActivity.class);
    		 startActivity(intent1);
    		 return true;
    	 case 1:
    		 Intent intent2 = new Intent();
    		 intent2.setClass(WriteCheckup_7.this, MainActivity.class);
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
