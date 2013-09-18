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

public class write_condition extends ListActivity {

	private String[] mTitles = {
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
			"/Yukari/Photo/imageView_write_condition0.png",
			"/Yukari/Photo/imageView_write_condition1.png",
			"/Yukari/Photo/imageView_write_condition2.png",
			"/Yukari/Photo/imageView_write_condition3.png",
			"/Yukari/Photo/imageView_write_condition4.png",
			"/Yukari/Photo/imageView_write_condition5.png",
			"/Yukari/Photo/imageView_write_condition6.png",
			"/Yukari/Photo/imageView_write_condition7.png",
			"/Yukari/Photo/imageView_write_condition8.png",
			"/Yukari/Photo/imageView_write_condition9.png",
			"/Yukari/Photo/imageView_write_condition10.png"};

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
            		Intent intent_write0 = new Intent(getApplicationContext(),write_condition_0.class);
            		startActivity(intent_write0);
            	}
            	if (position==1){
            		Intent intent_write1 = new Intent(getApplicationContext(),write_condition_1.class);
            		startActivity(intent_write1);
            	}
            	if (position==2){
            		Intent intent_write2 = new Intent(getApplicationContext(),write_condition_2.class);
            		startActivity(intent_write2);
            	}
            	if (position==3){
            		Intent intent_write3 = new Intent(getApplicationContext(),write_condition_3.class);
            		startActivity(intent_write3);
            	}
            	if (position==4){
            		Intent intent_write4 = new Intent(getApplicationContext(),write_condition_4.class);
            		startActivity(intent_write4);
            	}
            	if (position==5){
            		Intent intent_write5 = new Intent(getApplicationContext(),write_condition_5.class);
            		startActivity(intent_write5);
            	}
            	if (position==6){
            		Intent intent_write6 = new Intent(getApplicationContext(),write_condition_6.class);
            		startActivity(intent_write6);
            	}
            	if (position==7){
            		Intent intent_write7 = new Intent(getApplicationContext(),write_condition_7.class);
            		startActivity(intent_write7);
            	}
            	if (position==8){
            		Intent intent_write8 = new Intent(getApplicationContext(),write_condition_8.class);
            		startActivity(intent_write8);
            	}
            	if (position==9){
            		Intent intent_write9 = new Intent(getApplicationContext(),write_condition_9.class);
            		startActivity(intent_write9);
            	}
            	if (position==10){
            		Intent intent_write10 = new Intent(getApplicationContext(),write_condition_10.class);
            		startActivity(intent_write10);
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