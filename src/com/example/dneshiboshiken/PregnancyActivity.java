package com.example.dneshiboshiken;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class PregnancyActivity extends Activity {

	private Button write_button_0_1;
	private Button write_button_0_2;
	private Button write_button_0_3;
	private Button write_button_0_4;
	private Button write_button_0_5;
	private Button write_button_0_6;
	private Button write_button_0_7;
	private Button write_button_0_8;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pregnancy_activity);

        write_button_0_1 = (Button) findViewById(R.id.Button_pregnancy_health);
        write_button_0_1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent_write = new Intent(getApplicationContext(),pregnancy_write_health.class);
                startActivity(intent_write);
            }
        });

        write_button_0_2 = (Button) findViewById(R.id.Button_pregnancy_write);
        write_button_0_2.setOnClickListener(new View.OnClickListener(){

        	public void onClick(View v){
                Intent intent_writecheckup = new Intent(getApplicationContext(),pregnancy_write.class);
                startActivity(intent_writecheckup);
        	}
        });

        write_button_0_3 = (Button) findViewById(R.id.Button_pregnancy_inspection_record);
        write_button_0_3.setOnClickListener(new View.OnClickListener(){

        	public void onClick(View v){
                Intent intent_write = new Intent(getApplicationContext(),pregnancy_inspection_record.class);
                startActivity(intent_write);
        	}
        });

        write_button_0_4 = (Button) findViewById(R.id.Button_pregnancy_parenting_class_record);
        write_button_0_4.setOnClickListener(new View.OnClickListener(){

        	public void onClick(View v){
                Intent intent_write = new Intent(getApplicationContext(),pregnancy_parenting_class_record.class);
                startActivity(intent_write);
        	}
        });

        write_button_0_5 = (Button) findViewById(R.id.Button_pregnancy_teeth_report);
        write_button_0_5.setOnClickListener(new View.OnClickListener(){

        	public void onClick(View v){
                Intent intent_write = new Intent(getApplicationContext(),pregnancy_teeth_report.class);
                startActivity(intent_write);
        	}
        });

        write_button_0_6 = (Button) findViewById(R.id.Button_pregnancy_parenting_record_of_delivery);
        write_button_0_6.setOnClickListener(new View.OnClickListener(){

        	public void onClick(View v){
                Intent intent_write = new Intent(getApplicationContext(),pregnancy_parenting_record_of_delivery.class);
                startActivity(intent_write);
        	}
        });

        write_button_0_8 = (Button) findViewById(R.id.Button_pregnancy_about_parents);
        write_button_0_8.setOnClickListener(new View.OnClickListener(){

        	public void onClick(View v){
                Intent intent_write = new Intent(getApplicationContext(),pregnancy_about_parents.class);
                startActivity(intent_write);
        	}
        });

        write_button_0_7 = (Button) findViewById(R.id.Button_birth_registrationp);
        write_button_0_7.setOnClickListener(new View.OnClickListener(){

        	public void onClick(View v){
                Intent intent_write = new Intent(getApplicationContext(),pregnancy_birth_registration.class);
                startActivity(intent_write);
        	}
        });



	}


 }