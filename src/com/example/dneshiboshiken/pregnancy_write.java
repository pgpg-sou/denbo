package com.example.dneshiboshiken;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.R.id;
import android.app.DatePickerDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class pregnancy_write extends ListActivity {
	ArrayAdapter<String> adapter;
	static int pregnancy_write_flag;
	private String datapath = "/Yukari/Write/Pregnancy/Course/Pregnancyfile1.xml";
	private int photopath=0;

	private Button button_Write_edit;

	private String[] mTitles = new String[50];

	private String[] item_pregnancy_writetag = {"pregnancy_write_edit_examination",
			"pregnancy_write_edit_uterus",
			"pregnancy_write_edit_abdomen",
			"pregnancy_write_edit_weight",
			"pregnancy_write_edit_blood_top",
			"pregnancy_write_edit_blood_bottom",
			"pregnancy_write_edit_edema",
			"pregnancy_write_edit_protein",
			"pregnancy_write_edit_sugar",
			"pregnancy_write_edit_other",
			"pregnancy_write_edit_instructions",
			"pregnancy_write_edit_name",};

	private String[] item_pregnancy_text = {
			"検査日　　　：",
			"子宮底長　　：",
			"腹囲　　　　：",
			"体重　　　　：",
			"血圧（上）　：",
			"血圧（下）　：",
			"浮腫　　　　：",
			"尿蛋白　　　：",
			"尿糖　　　　：",
			"その他の検査：",
			"特記事項　　：",
			"施設名又は担当者：",
			};

    private String[] tvParam_EditText = new String[12];

	private Bitmap[] bitmap = new Bitmap[20];

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.pregnancy_write);

		//初期値読み込み
        File dir = new File(Environment.getExternalStorageDirectory().getPath());
        if(dir.exists()){
	    	 for(int i1 = 1; i1 < 50; i1++) {
	    		 File file = new File(dir.getAbsolutePath()+"/Yukari/Write/Pregnancy/Course/Pregnancyfile"+i1+".xml");
	    		 if (file.exists()) {
	    			 datapath="/Yukari/Write/Pregnancy/Course/Pregnancyfile"+(i1)+".xml";
	    			 photopath = i1;
	    			 Log.d("tag", datapath);
	    			 tvParam_EditText = new String[12];
	    			 try {
	             		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
	             		factory.setNamespaceAware(true);
	             		XmlPullParser parser = factory.newPullParser();
	             		FileInputStream fis = new FileInputStream(file);
	             		parser.setInput(new InputStreamReader(fis));

	             		// EditTextインスタンスの取得

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
	                             if(value==null) value = "未記入";
	                             // 空白で取得したものは全て処理対象外とする
	                           	     if(tag.equals(item_pregnancy_writetag[0])) {tvParam_EditText[11]=value;}
	                           	     else if(tag.equals(item_pregnancy_writetag[1])) {tvParam_EditText[10]=value+" cm";}
	                           	     else if(tag.equals(item_pregnancy_writetag[2])) {tvParam_EditText[9]=value+" cm";}
	                           	     else if(tag.equals(item_pregnancy_writetag[3])) {tvParam_EditText[8]=value+" kg";}
	                           	     else if(tag.equals(item_pregnancy_writetag[4])) {tvParam_EditText[7]=value;}
	                           	     else if(tag.equals(item_pregnancy_writetag[5])) {tvParam_EditText[6]=value;}
	                           	     else if(tag.equals(item_pregnancy_writetag[6])) {tvParam_EditText[5]=value;}
	                           	     else if(tag.equals(item_pregnancy_writetag[7])) {tvParam_EditText[4]=value;}
	                           	     else if(tag.equals(item_pregnancy_writetag[8])) {tvParam_EditText[3]=value;}
	                           	     else if(tag.equals(item_pregnancy_writetag[9])) {tvParam_EditText[2]=value;}
	                           	     else if(tag.equals(item_pregnancy_writetag[10])) {tvParam_EditText[1] = value;}
	                           	     else if(tag.equals(item_pregnancy_writetag[11])) {tvParam_EditText[0] = value;}

	                             break;
	                         case XmlPullParser.END_TAG: // 終了タグ
	                             break;
	                         }

	                     }
	                 } catch (Exception e) {
	                     Toast.makeText(getApplicationContext(), "エラー発生", Toast.LENGTH_SHORT);
	                 }
	    			 mTitles[i1] = "";
	    			 for(int i = 0; i < tvParam_EditText.length; i++) {
	    					if(tvParam_EditText[i]==null) tvParam_EditText[i]="未記入";
	    					mTitles[i1] = item_pregnancy_text[11-i] + tvParam_EditText[i] + "\n" + mTitles[i1] + "\n";
	    				}

	    					File view = new File(Environment.getExternalStorageDirectory().getPath() + "/Yukari/Photo/" + "imageView_Pregnancy" + (photopath-1) +".png");
	    					if (view.exists()) {
	    						bitmap[i1] = BitmapFactory.decodeFile(view.getPath());
	    					}
	    					else {
	    						bitmap[i1] = BitmapFactory.decodeResource(getResources(), R.drawable.noimage);
	    					}
	    		 }
	    	 }
	     }

        if(photopath==0){
        	TextView textView = (TextView) findViewById(R.id.text_pregnansy_check);
        	textView.setText("記録されているデータがありません。\n"+"検査日　　　：\n"+
        			"子宮底長　　：\n"+
        			"腹囲　　　　：\n"+
        			"体重　　　　：\n"+
        			"血圧（上）　：\n"+
        			"血圧（下）　：\n"+
        			"浮腫　　　　：\n"+
        			"尿蛋白　　　：\n"+
        			"尿糖　　　　：\n"+
        			"その他の検査：\n"+
        			"特記事項　　：\n"+
        			"施設名又は担当者：");
        }


		List<BindData2> objects = new ArrayList<BindData2>();
		for(int i = 1; i < photopath+1; i++) {
			BindData2 data = new BindData2(mTitles[i], bitmap[i]);
			objects.add(data);
		}
		setListAdapter(new WriteCheckupAdapter2(this, objects));
		ListView listView = (ListView) findViewById(id.list);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
            	//リスト内で、どこかタッチされたら（position=タッチされた位置 0~）
            	for(int i = 0; i < photopath; i++) {
            	if (position==i){
            		Intent intent_write0 = new Intent(getApplicationContext(),pregnancy_write_editedit.class);
            		startActivity(intent_write0);
            		pregnancy_write_flag = i;
            		finish();
            	}
            	}
            }

        });

		button_Write_edit = (Button) findViewById(R.id.Button_checkup_edit);
		button_Write_edit.setOnClickListener(new View.OnClickListener(){
	        public void onClick(View v) {
	        	Intent intent_cancel = new Intent(getApplicationContext(),pregnancy_writep.class);
	            startActivity(intent_cancel);
	            finish();
	        }
	    });


	}


	}

