package com.example.dneshiboshiken;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class Album_ShowList extends Activity implements View.OnClickListener {
    //LayoutParams////////
    private final static int FP = ViewGroup.LayoutParams.FILL_PARENT;
    private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;

    private LinearLayout baseLayout;
    private Button goToList;
    private ListView list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /////A///v/////////////////////\////////////////
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        /////x///[///X/////////C///A///E///g///////////////////////z///u
        baseLayout = new LinearLayout(this);
        setContentView(baseLayout);
        baseLayout.setOrientation(LinearLayout.VERTICAL);
        baseLayout.setBackgroundColor(Color.rgb(0, 0, 0));

        ///////////X///g///r/////////[///////////////////////z///u
        list = new ListView(this);
        baseLayout.addView(list, new LinearLayout.LayoutParams(FP, 700));
        list.setBackgroundColor(Color.rgb(0, 0, 0));

        /////T///u/////////C///A///E///g///////////{///^////////////z///u
        goToList = new Button(this);
        baseLayout.addView(goToList, new LinearLayout.LayoutParams(FP, FP));
        goToList.setTextSize(16.0f);
        goToList.setText("bbb");
        goToList.setOnClickListener(this);

        ///////////X///g///r/////////[/////A///_///v///^///////////
        list.setAdapter(new baseAdapter(this));

        ///////////X///g///r/////////[/////R///[/////////o///b///N/////////X///i///[///////////
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent
                    , View view, int position, long id) {
                goToDetails(position);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v==goToList) {
            /////A///N///e///B///r///e///B/////I//////
            finish();
        }
    }

    public class baseAdapter extends BaseAdapter {
        private LayoutInflater inflater;

        public baseAdapter(Context context) {
            inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return Album_Main.fileList.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            /////\//////////////////r/////////[////////////////////////////////////////////
            if(convertView == null){
                convertView = inflater.inflate(R.layout.row, null);
            }

            /////C/////////[///W///r/////////[//////////////\//////
            ImageView imageView = (ImageView) convertView.findViewById(R.id.photo);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(120, 120));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            BitmapFactory.Options sizeOption = new BitmapFactory.Options();
            sizeOption.inSampleSize = 4; ///////////T///C///Y//////1/4//////////////
            String fileName = Album_Main.fileList[position].toString();
            Bitmap bmp = BitmapFactory.decodeFile(fileName, sizeOption);
            imageView.setImageBitmap(bmp);

            /////e///L///X///g///r/////////[///////////////////////\//////
            TextView textView = (TextView) convertView.findViewById(R.id.filename);
            textView.setPadding(10, 0, 0, 0);
            textView.setTextColor(Color.rgb(255, 255, 255));
            fileName = fileName.replace(Album_Main.imageFolder.toString(), "");
            //fileName = fileName.substring(0, fileName.indexOf('.'));
            textView.setText(fileName);

            return convertView;
        }
    }

    ///////////R///[///h/////////\/////////////////\//////
    private void goToDetails(int position) {

        /////////////////I/////C/////////e/////////g//////////////
        Intent intent = new Intent(this,Album_ShowDetails.class);

        /////C/////////e/////////g/////n/////////p///////////////[///^//////////
        intent.putExtra("filepath", Album_Main.fileList[position].toString());

        /////A///N///e///B///r///e///B/////////o//////
        startActivityForResult(intent, 0);
    }
}
