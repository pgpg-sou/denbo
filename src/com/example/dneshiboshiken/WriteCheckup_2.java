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

public class WriteCheckup_2 extends Activity {

	//目次の項目だけボタンを定義
	private Button button_Write_child_9_12_1_back;
	private Button button_Write_child_9_12_1_home;
	private Button button_Write_child_9_12_1_next;

	private String[] item_checkup_2tag = {"write_checkup_2p_age1","write_checkup_2p_weight1","write_checkup_2p_nursing1",
			"write_checkup_2p_feeding1","write_checkup_2p_name1","write_checkup_2p_age2","write_checkup_2p_weight2",
			"write_checkup_2p_nursing2","write_checkup_2p_feeding2","write_checkup_2p_name2","write_checkup_2p_newborn_day",
			"write_checkup_2p_age3","write_checkup_2p_weight3","write_checkup_2p_height","write_checkup_2p_chest","write_checkup_2p_head",
			"write_checkup_2p_feeding3","write_checkup_2p_name3","write_checkup_2p_other1","write_checkup_2p_congenital_day",
			"write_checkup_2p_congenital_other","write_checkup_2p_here_day","write_checkup_2p_here_other",
			"write_checkup_2p_other2"};

	public static int[] item_checkup_2 = {R.id.TextView_write_checkup_2_age1,
		R.id.TextView_write_checkup_2_weight1,
		R.id.TextView_write_checkup_2_nursing1,
		R.id.TextView_write_checkup_2_feeding1,
		R.id.TextView_write_checkup_2_name1,
		R.id.TextView_write_checkup_2_age2,
		R.id.TextView_write_checkup_2_weight2,
		R.id.TextView_write_checkup_2_nursing2,
		R.id.TextView_write_checkup_2_feeding2,
		R.id.TextView_write_checkup_2_name2,
		R.id.TextView_write_checkup_2_newborn_day,
		R.id.TextView_write_checkup_2_age3,
		R.id.TextView_write_checkup_2_weight3,
		R.id.TextView_write_checkup_2_height,
		R.id.TextView_write_checkup_2_chest,
		R.id.TextView_write_checkup_2_head,
		R.id.TextView_write_checkup_2_feeding3,
		R.id.TextView_write_checkup_2_name3,
		R.id.TextView_write_checkup_2_other1,
		R.id.TextView_write_checkup_2_congenital_day,
		R.id.TextView_write_checkup_2_congenital_other,
		R.id.TextView_write_checkup_2_here_day,
		R.id.TextView_write_checkup_2_here_other,
		R.id.TextView_write_checkup_2_other2,};

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_checkup_2);	//画面レイアウトを指定(res/layout/index_read.xml)
        TextView[] tvParam = new TextView[item_checkup_2.length];


        // 読み込み
        //xmlファイル読み込み
          File dir = new File(Environment.getExternalStorageDirectory().getPath());
          if(dir.exists()){

              File file = new File(dir.getAbsolutePath()+"/Yukari/Write/Checkup/WriteCheckup_2file.xml");
              if (file.exists()) {
              	try {
              		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
              		factory.setNamespaceAware(true);
              		XmlPullParser parser = factory.newPullParser();
              		FileInputStream fis = new FileInputStream(file);
              		parser.setInput(new InputStreamReader(fis));

                      // TextViewインスタンスの取得
              		for (int i1 = 0; i1 < item_checkup_2.length; i1++) {
                    	tvParam[i1] = (TextView)findViewById(item_checkup_2[i1]);
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
                            	  if(tag.equals(item_checkup_2tag[0])) {tvParam[0].setText(value);}
                            	  else if(tag.equals(item_checkup_2tag[1])) {tvParam[1].setText(value);}
                            	  else if(tag.equals(item_checkup_2tag[2])) {tvParam[2].setText(value);}
                            	  else if(tag.equals(item_checkup_2tag[3])) {tvParam[3].setText(value);}
                            	  else if(tag.equals(item_checkup_2tag[4])) {tvParam[4].setText(value);}
                            	  else if(tag.equals(item_checkup_2tag[5])) {tvParam[5].setText(value);}
                            	  else if(tag.equals(item_checkup_2tag[6])) {tvParam[6].setText(value);}
                            	  else if(tag.equals(item_checkup_2tag[7])) {tvParam[7].setText(value);}
                            	  else if(tag.equals(item_checkup_2tag[8])) {tvParam[8].setText(value);}
                            	  else if(tag.equals(item_checkup_2tag[9])) {tvParam[9].setText(value);}
                            	  else if(tag.equals(item_checkup_2tag[10])) {tvParam[10].setText(value);}
                            	  else if(tag.equals(item_checkup_2tag[11])) {tvParam[11].setText(value);}
                            	  else if(tag.equals(item_checkup_2tag[12])) {tvParam[12].setText(value);}
                            	  else if(tag.equals(item_checkup_2tag[13])) {tvParam[13].setText(value);}
                            	  else if(tag.equals(item_checkup_2tag[14])) {tvParam[14].setText(value);}
                            	  else if(tag.equals(item_checkup_2tag[15])) {tvParam[15].setText(value);}
                            	  else if(tag.equals(item_checkup_2tag[16])) {tvParam[16].setText(value);}
                            	  else if(tag.equals(item_checkup_2tag[17])) {tvParam[17].setText(value);}
                            	  else if(tag.equals(item_checkup_2tag[18])) {tvParam[18].setText(value);}
                            	  else if(tag.equals(item_checkup_2tag[19])) {tvParam[19].setText(value);}
                            	  else if(tag.equals(item_checkup_2tag[20])) {tvParam[20].setText(value);}
                            	  else if(tag.equals(item_checkup_2tag[21])) {tvParam[21].setText(value);}
                            	  else if(tag.equals(item_checkup_2tag[22])) {tvParam[22].setText(value);}
                            	  else if(tag.equals(item_checkup_2tag[23])) {tvParam[23].setText(value);}
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
                  File view = new File(dir.getAbsolutePath()+"/Yukari/Photo/imageView_write_checkup_2p.png");
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

                  	((ImageView)findViewById(R.id.imageview_write_checkup_2)).setImageBitmap(image);
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
    private void button_Write_child_9_12_1_back_onClick() {
        Intent intent_read_1 = new Intent(getApplicationContext(),WriteCheckup_1.class);
        startActivity(intent_read_1);
        ImageView iv = (ImageView)findViewById(R.id.imageview_write_checkup_2);
        iv.setImageDrawable(null);
    }

    private void button_Write_child_9_12_1_home_onClick() {
        Intent intent_read_2 = new Intent(getApplicationContext(),WriteCheckup_2p.class);
        startActivity(intent_read_2);
        ImageView iv = (ImageView)findViewById(R.id.imageview_write_checkup_2);
        iv.setImageDrawable(null);
        finish();
    }

    private void button_Write_child_9_12_1_next_onClick() {
        Intent intent_read_3 = new Intent(getApplicationContext(),WriteCheckup_3.class);
        startActivity(intent_read_3);
        ImageView iv = (ImageView)findViewById(R.id.imageview_write_checkup_2);
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
    		 intent1.setClass(WriteCheckup_2.this, MainActivity.class);
    		 startActivity(intent1);
    		 return true;
    	 case 1:
    		 Intent intent2 = new Intent();
    		 intent2.setClass(WriteCheckup_2.this, MainActivity.class);
    		 startActivity(intent2);
    		 return true;
    	 }
    	 return true;
    }



  //バックキーのアクション


}
