package com.example.dneshiboshiken;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class WriteActivity extends Activity {

	public static int[] write_button =  {R.id.Button_write_condition1,
		R.id.Button_write_condition2,
		R.id.Button_write_condition3,
		R.id.Button_write_condition4,
		R.id.Button_write_condition5,
		R.id.Button_write_condition6,
		R.id.Button_write_condition7,
		R.id.Button_write_condition8,
		R.id.Button_write_condition9,
		R.id.Button_write_condition10,
		R.id.Button_write_condition12,
		R.id.Button_write_condition13,
		R.id.Button_write_condition14,
		R.id.Button_write_condition15,
		R.id.Button_write_condition16,
		R.id.Button_write_condition17,
		R.id.Button_write_condition18,
		R.id.Button_write_condition19,
		R.id.Button_write_condition20,};
	private Button[] write_button_0_1= new Button[write_button.length];


	Boolean _first = true;
	private AccordionSet _as1;
	private AccordionSet _as2;
	private AccordionSet _as3;
	private AccordionSet _as4;
	private AccordionSet _as5;

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		if (_first) {
			_first = false;
			_as1 = new AccordionSet((LinearLayout)findViewById(R.id.btn1), (LinearLayout)findViewById(R.id.content1));
			_as2 = new AccordionSet((LinearLayout)findViewById(R.id.btn2), (LinearLayout)findViewById(R.id.content2));
			_as3 = new AccordionSet((LinearLayout)findViewById(R.id.btn3), (LinearLayout)findViewById(R.id.content3));
			_as4 = new AccordionSet((LinearLayout)findViewById(R.id.btn4), (LinearLayout)findViewById(R.id.content4));
			_as5 = new AccordionSet((LinearLayout)findViewById(R.id.btn5), (LinearLayout)findViewById(R.id.content5));
		}
		super.onWindowFocusChanged(hasFocus);
	}

	@Override
	protected void onDestroy() {
		if (!_first) {
			_as1.deleteAccordion();
			_as2.deleteAccordion();
			_as3.deleteAccordion();
			_as4.deleteAccordion();
			_as5.deleteAccordion();
		}
		super.onDestroy();

	}


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("a","ここに文章を入力して下さxい");
		setContentView(R.layout.write_activity);
		Log.d("a","ここに文章を入力して下さい");

		int i;
		for(i=0;i<write_button_0_1.length;i++){
			write_button_0_1[i]=(Button) findViewById(write_button[i]);
		}
		write_button_0_1[0].setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Intent intent_cancel = new Intent(getApplicationContext(),pregnancy_about_parents.class);
				startActivity(intent_cancel);
			}
		});

		write_button_0_1[1].setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Intent intent_cancel = new Intent(getApplicationContext(),pregnancy_birth_registration.class);
				startActivity(intent_cancel);
			}});
		write_button_0_1[2].setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Intent intent_cancel = new Intent(getApplicationContext(),pregnancy_write_health.class);
				startActivity(intent_cancel);
			}});
		write_button_0_1[3].setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Intent intent_cancel = new Intent(getApplicationContext(),MainActivity.class);
				startActivity(intent_cancel);
			}});
		write_button_0_1[4].setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Intent intent_cancel = new Intent(getApplicationContext(),pregnancy_home_situation.class);
				startActivity(intent_cancel);
			}});
		write_button_0_1[5].setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Intent intent_cancel = new Intent(getApplicationContext(),MainActivity.class);
				startActivity(intent_cancel);
			}});
		write_button_0_1[6].setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Intent intent_cancel = new Intent(getApplicationContext(),pregnancy_write.class);
				startActivity(intent_cancel);
			}});
		write_button_0_1[7].setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Intent intent_cancel = new Intent(getApplicationContext(),pregnancy_inspection_record.class);
				startActivity(intent_cancel);
			}});
		write_button_0_1[8].setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Intent intent_cancel = new Intent(getApplicationContext(),pregnancy_parenting_class_record.class);
				startActivity(intent_cancel);
			}});
		write_button_0_1[9].setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Intent intent_cancel = new Intent(getApplicationContext(),pregnancy_teeth_report.class);
				startActivity(intent_cancel);
			}});
		write_button_0_1[10].setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Intent intent_cancel = new Intent(getApplicationContext(),pregnancy_parenting_record_of_delivery.class);
				startActivity(intent_cancel);
			}});
		write_button_0_1[11].setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Intent intent_cancel = new Intent(getApplicationContext(),MainActivity.class);
				startActivity(intent_cancel);
			}});
		write_button_0_1[12].setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Intent intent_cancel = new Intent(getApplicationContext(),MainActivity.class);
				startActivity(intent_cancel);
			}});
		write_button_0_1[13].setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Intent intent_cancel = new Intent(getApplicationContext(),WriteCheckup.class);
				startActivity(intent_cancel);
			}});
		write_button_0_1[14].setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Intent intent_cancel = new Intent(getApplicationContext(),write_condition.class);
				startActivity(intent_cancel);
			}});
		write_button_0_1[15].setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Intent intent_cancel = new Intent(getApplicationContext(),MainActivity.class);
				startActivity(intent_cancel);
			}});
		write_button_0_1[16].setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Intent intent_cancel = new Intent(getApplicationContext(),MainActivity.class);
				startActivity(intent_cancel);
			}});
		write_button_0_1[17].setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Intent intent_cancel = new Intent(getApplicationContext(),MainActivity.class);
				startActivity(intent_cancel);
			}});
		write_button_0_1[18].setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Intent intent_cancel = new Intent(getApplicationContext(),immunization_main.class);
				startActivity(intent_cancel);
			}});

	}



	private float actDownX;
	private float actDownY;
	private float actUpX;
	private float actUpY;


	@Override
	public boolean onTouchEvent(MotionEvent event){
		Log.d("TouchEvent", "X:" + event.getX() + ",Y:" + event.getY());
		//右方向に指をスライドさせた場合に画面遷移
		//あとで消す
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			actDownX = event.getX();
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:
			actUpX = event.getX();
			if(actUpX-actDownX > 100){
				finish();
			}
			break;
		}
		return false;

	}


}