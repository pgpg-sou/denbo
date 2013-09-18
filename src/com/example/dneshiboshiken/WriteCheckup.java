package com.example.dneshiboshiken;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.R.id;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class WriteCheckup extends ListActivity {

	private String[] mTitles = {
			"生まれた時",
			"生後１週間",
			"生後４週間",
			"１ヶ月頃",
			"３〜４ヶ月頃",
			"６〜７ヶ月頃",
			"９〜１０ヶ月頃",
			"１歳の頃",
			"１歳６ヶ月の頃",
			"２歳の頃",
			"３歳の頃",
			"４歳の頃",
			"５歳の頃",
			"６歳の頃"};

	private String[] filepath = {
			"/Yukari/Photo/imageView_write_checkup_0p.png",
			"/Yukari/Photo/imageView_write_checkup_1p.png",
			"/Yukari/Photo/imageView_write_checkup_2p.png",
			"/Yukari/Photo/imageView_write_checkup_3p.png",
			"/Yukari/Photo/imageView_write_checkup_4p.png",
			"/Yukari/Photo/imageView_write_checkup_5p.png",
			"/Yukari/Photo/imageView_write_checkup_6p.png",
			"/Yukari/Photo/imageView_write_checkup_7p.png",
			"/Yukari/Photo/imageView_write_checkup_8p.png",
			"/Yukari/Photo/imageView_write_checkup_9p.png",
			"/Yukari/Photo/imageView_write_checkup_10p.png",
			"/Yukari/Photo/imageView_write_checkup_11p.png",
			"/Yukari/Photo/imageView_write_checkup_12p.png",
			"/Yukari/Photo/imageView_write_checkup_13p.png",};

	private Bitmap[] bitmap = new Bitmap[filepath.length];

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.write_checkup);

		for(int i = 0; i < filepath.length; i++) {
			File view = new File(Environment.getExternalStorageDirectory().getPath() + filepath[i]);
			if (view.exists()) {
				BitmapFactory.Options options = new BitmapFactory.Options();
            	options.inJustDecodeBounds = true;
            	Bitmap bmp1 = BitmapFactory.decodeFile(view.getPath(), options );

            	int reqWidth = 0;
            	int reqHeight = 0;
            	options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
            	int scale = Math.max(4, 4);
            	options.inJustDecodeBounds = false;
            	options.inSampleSize = scale;
            	bitmap[i] = BitmapFactory.decodeFile(view.getPath(), options);
				//bitmap[i] = BitmapFactory.decodeFile(view.getPath());
			}
			else {
				bitmap[i] = BitmapFactory.decodeResource(getResources(), R.drawable.noimage);
			}
		}

		List<BindData> objects = new ArrayList<BindData>();
		for(int i = 0; i < mTitles.length; i++) {
			BindData data = new BindData(mTitles[i], bitmap[i]);
			objects.add(data);
		}
		setListAdapter(new WriteCheckupAdapter(this, objects));
		final ListView listView = (ListView) findViewById(id.list);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
            	//リスト内で、どこかタッチされたら（position=タッチされた位置 0~）
            	//listView.setAdapter(null);
            	if (position==0){
            		Intent intent_write0 = new Intent(getApplicationContext(),WriteCheckup_0.class);
            		startActivity(intent_write0);
            	}
            	if (position==1){
            		Intent intent_write1 = new Intent(getApplicationContext(),WriteCheckup_1.class);
            		startActivity(intent_write1);
            	}
            	if (position==2){
            		Intent intent_write2 = new Intent(getApplicationContext(),WriteCheckup_2.class);
            		startActivity(intent_write2);
            	}
            	if (position==3){
            		Intent intent_write3 = new Intent(getApplicationContext(),WriteCheckup_3.class);
            		startActivity(intent_write3);
            	}
            	if (position==4){
            		Intent intent_write4 = new Intent(getApplicationContext(),WriteCheckup_4.class);
            		startActivity(intent_write4);
            	}
            	if (position==5){
            		Intent intent_write5 = new Intent(getApplicationContext(),WriteCheckup_5.class);
            		startActivity(intent_write5);
            	}
            	if (position==6){
            		Intent intent_write6 = new Intent(getApplicationContext(),WriteCheckup_6.class);
            		startActivity(intent_write6);
            	}
            	if (position==7){
            		Intent intent_write7 = new Intent(getApplicationContext(),WriteCheckup_7.class);
            		startActivity(intent_write7);
            	}
            	if (position==8){
            		Intent intent_write8 = new Intent(getApplicationContext(),WriteCheckup_8.class);
            		startActivity(intent_write8);
            	}
            	if (position==9){
            		Intent intent_write9 = new Intent(getApplicationContext(),WriteCheckup_9.class);
            		startActivity(intent_write9);
            	}
            	if (position==10){
            		Intent intent_write10 = new Intent(getApplicationContext(),WriteCheckup_10.class);
            		startActivity(intent_write10);
            	}
            	if (position==11){
            		Intent intent_write11 = new Intent(getApplicationContext(),WriteCheckup_11.class);
            		startActivity(intent_write11);
            	}
            	if (position==12){
            		Intent intent_write12 = new Intent(getApplicationContext(),WriteCheckup_12.class);
            		startActivity(intent_write12);
            	}
            	if (position==13){
            		Intent intent_write13 = new Intent(getApplicationContext(),WriteCheckup_13.class);
            		startActivity(intent_write13);
            	}

            }
        });}
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

}