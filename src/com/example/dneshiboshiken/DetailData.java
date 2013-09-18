package com.example.dneshiboshiken;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class DetailData extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_data);
		
		Intent intent = getIntent();
		
		String[] plan = intent.getStringArrayExtra("plan");
		String[] startTime = intent.getStringArrayExtra("startTime");
		String[] stopTime = intent.getStringArrayExtra("stopTime");
		String[] textOfPlan = new String[plan.length];
		
		TextView plan1 = (TextView) findViewById(R.id.textView1);
		TextView plan2 = (TextView) findViewById(R.id.textView2);		
		
		for(int i= 1;i < plan.length;i++) {
			textOfPlan[i-1] = "予定 : " +  plan[i] + "\n";
			textOfPlan[i-1] += "開始時刻 : " + startTime[i-1] + "\n";
			textOfPlan[i-1] += "終了時刻 : " + stopTime[i-1] + "\n";
		}
		plan1.setText(textOfPlan[0]);
		plan2.setText(textOfPlan[1]);		
	}
}
