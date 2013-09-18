package com.example.dneshiboshiken;

import java.io.InputStream;
import java.util.Calendar;

import org.simpleframework.xml.core.Persister;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

@SuppressLint("NewApi")
public class DisplayCalender extends FragmentActivity {

	private final int NUMOFWEEK = 5;
	private final int NUMOFDATE = 7;

	@SuppressWarnings("deprecation")
	private final int FP = ViewGroup.LayoutParams.FILL_PARENT;
	private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
	@SuppressWarnings("deprecation")
	private final int LFP = LinearLayout.LayoutParams.FILL_PARENT;
	private final int LWC = LinearLayout.LayoutParams.WRAP_CONTENT;

	private TableRow[] mWeek = new TableRow[NUMOFWEEK];
	private TextView[][] mDate = new TextView[NUMOFWEEK][NUMOFDATE];
	private String[][] datePlan;
	private String[] mStartTime;
	private String[] mEndTime;
	private String[] mAlerm;
	private Plan[] mList;
	private PlanShelf mPlan;
	private TextView mMonthLabel;
	private int mMonth;
	private int mYear;
	private int height;
	private boolean check = true;
	ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar_main);

		InputStream is = getResources().openRawResource(R.raw.bookmanager);
		Persister persister = new Persister();
		
		try {
			mPlan = persister.read(PlanShelf.class, is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mList = new Plan[mPlan.plan.size()];
		datePlan = new String[mPlan.plan.size()][];
		mStartTime = new String[mPlan.plan.size()];
		mEndTime = new String[mPlan.plan.size()];
		mAlerm = new String[mPlan.plan.size()];

		for (int i = 0; i < mList.length; i++) {
			mList[i] = mPlan.plan.get(i);
			datePlan[i] = mList[i].date.split("\\.");
		}
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////
	// /////////////// データの入った日にちをタッチすると詳細画面に飛ぶ //////////////////////////
	class selectData implements OnClickListener {
		public int row;
		public int column;

		public selectData(int i, int j) {
			row = i;
			column = j;
		}

		@Override
		public void onClick(View v) {
			Calendar now = Calendar.getInstance();
			int y = now.get(now.YEAR);
			int m = now.get(now.MONTH) + 1;
			int d = now.get(now.DATE);
			int h = now.get(now.HOUR_OF_DAY);// 時を取得

			String currentDate = y + "." + m + "." + d;
			Intent intentOfDetailData = new Intent(DisplayCalender.this, DetailData.class);

			String[] plan = mDate[row][column].getText().toString().split("\n");
			String[] sendStartTime = mStartTime[mDate[row][column].getId()].split("\n");
			String[] sendEndTime = mEndTime[mDate[row][column].getId()].split("\n");
			String[] alermTime = mAlerm[mDate[row][column].getId()].split("\n");
			intentOfDetailData.putExtra("plan", plan);
			intentOfDetailData.putExtra("startTime", sendStartTime);
			intentOfDetailData.putExtra("stopTime", sendEndTime);

			CountDate cd = new CountDate();
			String[] divi = sendStartTime[0].split(":");
			int countDate = cd.count(currentDate,
					mList[mDate[row][column].getId()].date);
			long alermMillTime = Integer.parseInt(alermTime[0]) * 60 * 1000;
			long startTime = (countDate * 24 * 3600 * 1000);
			startTime += (Integer.parseInt(divi[0]) - h) * 3600 * 1000;
			startTime += Integer.parseInt(divi[1]) * 60000;

//			final MyCountDownTimer mc = new MyCountDownTimer(20000, 1000, 10000);
//			mc.start();
			startActivity(intentOfDetailData);
		}
	}

	// ///////////////////////////////////////////////////////////////////////////////////////////////

	// ///////////////////////////////////////////////////////////////////////////////////////////////
	// ///////////////////////// 月変更ボタンをおすとここにはいる
	// //////////////////////////////////
	class MonthButton implements OnClickListener {

		@Override
		public void onClick(View v) {
			View button = v;
			switch (button.getId()) {
			case R.id.next:
				if (mMonth == 11) {
					mYear = mYear + 1;
					mMonth = 0;
				} else {
					mMonth++;
				}
				displayMonth(mMonth, mYear, 1);
				break;
			case R.id.previous:
				if (mMonth == 0) {
					mYear = mYear - 1;
					mMonth = 11;
				} else {
					mMonth--;
				}
				displayMonth(mMonth, mYear, 1);
				break;
			}
			Log.d("month", String.valueOf(mMonth));
			Log.d("year", String.valueOf(mYear));
		}
	}

	// //////////////////////////////////////////////////////////////////////////////////////////

	// /////////////////////////////////////////////////////////////////////////////////////////
	// //////////////// month, year を入力するとその月のカレンダーを表示する //////////
	public void displayMonth(int month, int year, int mode) {
		Calendar calendar = Calendar.getInstance();
		mMonthLabel = (TextView) findViewById(R.id.textView1);
		calendar.set(year, month, 1);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

		mMonthLabel.setText(String.valueOf(String.valueOf(year + "年"
				+ (month + 1) + "月")));
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DATE, -1);

		int lastDate = calendar.get(Calendar.DATE);
		for (int i = 0; i < mDate.length; i++) {
			for (int j = 0; j < mDate[i].length; j++) {
				mDate[i][j].setText("");
			}
		}
		Log.d("week", String.valueOf(dayOfWeek));
		for (int i = 0; i < NUMOFWEEK; i++) {
			for (int j = dayOfWeek - 1; j < NUMOFDATE; j++) {
				mDate[i][j].setText(String.valueOf(day));
				mDate[i][j].append("\n");
				if (day == lastDate)
					break;
				day = day + 1;
			}
			dayOfWeek = 1;
		}
		writePlan(lastDate, day, dayOfWeek, mode);
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////

	// /////////////////////////////////////////////////////////////////////////////////////////////////
	// /////////////displayMonthで表示されたカレンダーにでーたを書き込む //////////////////////
	public void writePlan(int lastDate, int day, int dayOfWeek, int mode) {
		int dummy = 0;
      
		String[] span = { "\n", "", "\n" };
		for (int k = 0; k < lastDate; k++) {
			for (int i = 0; i < mDate.length; i++) {
				for (int j = 0; j < mDate[i].length; j++) {
					int dateNum = mDate[i][j].getText().toString().indexOf("\n");
					if (dateNum != -1) {
						if (dummy == mList.length)
							break;
						String date = mDate[i][j].getText().toString()
								.substring(0, dateNum);
						if (checkDate(dummy, date)) {
							if (mDate[i][j].getId() > 0) {
								mDate[i][j].append(span[mode]
										+ mList[dummy].plan + span[mode + 1]);
								mStartTime[mDate[i][j].getId()] += "\n"
										+ mList[dummy].startTime;
								mEndTime[mDate[i][j].getId()] += "\n"
										+ mList[dummy].endTime;
								mAlerm[mDate[i][j].getId()] += "\n"
										+ mList[dummy].alerm;
							} else {
								mDate[i][j].setOnClickListener(new selectData(
										i, j));
								mDate[i][j].append(mList[dummy].plan);
								mStartTime[dummy] = mList[dummy].startTime;
								mEndTime[dummy] = mList[dummy].endTime;
								mAlerm[dummy] = mList[dummy].alerm;
								mDate[i][j].setId(dummy);
							}
							dummy++;

						} else if (Integer.parseInt(date) == lastDate
								&& datePlan[dummy][1].equals(String
										.valueOf(mMonth + 1)) == false) {
							dummy++;
						} else {

						}
					}
				}
			}
		}
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////

	// ///////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////予定を書き込んでもいいかのチェックをおこなう ///////////////////////////////
	public boolean checkDate(int dummy, String date) {
		if (datePlan[dummy][0].equals(String.valueOf(mYear))
				&& datePlan[dummy][1].equals(String.valueOf(mMonth + 1))
				&& datePlan[dummy][2].equals(date))
			return true;
		else
			return false;
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////

	// ////////////////////////////////////////////////////////////////////////////////////////////////
	@SuppressLint("NewApi")
	public void alerm() {
		Intent intent = new Intent(this, DisplayCalender.class);
		PendingIntent contentIntent = PendingIntent.getActivity(
				DisplayCalender.this, 0, intent, 0);

		Notification notification;
		try {
			Class.forName("android.app.Notification$Builder");
			notification = new Notification.Builder(DisplayCalender.this)
					.setContentIntent(contentIntent)
					.setSmallIcon(R.drawable.ic_launcher).setTicker("lasf")
					.setContentTitle("afsf").setContentText("contentText")
					.setWhen(System.currentTimeMillis()).getNotification();
		} catch (ClassNotFoundException e) {
			notification = new Notification(R.drawable.ic_launcher,
					"tickerText", System.currentTimeMillis());
			notification.setLatestEventInfo(DisplayCalender.this, "contentTitle",
					"contentText", contentIntent);
		}
		NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		nm.notify(12345, notification);
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////

	// /////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////一番始めにカレンダーの枠組みをつくる /////////////////////////////////
	public void createTable() {
		TableLayout table = (TableLayout) findViewById(R.id.table);
		Log.d("korenihaitteru",String.valueOf(height));		
		
		for (int i = 0; i < mWeek.length; i++) {
			mWeek[i] = new TableRow(this);
			mWeek[i].setGravity(1);
			TableRow.LayoutParams lp = new TableRow.LayoutParams(LWC, height/5);
			lp.weight = 1;
			lp.setMargins(1, 1, 1, 1);
			for (int j = 0; j < NUMOFDATE; j++) {
				mDate[i][j] = new TextView(this);
				mDate[i][j].setWidth(getResources().getInteger(R.integer.textWidth));
				mDate[i][j].setTextSize(9);
				mDate[i][j].setLayoutParams(lp);
				if (j == 0) {
					mDate[i][j].setBackgroundColor(getResources().getColor(R.color.sunday));
				} else if (j == 6) {
					mDate[i][j].setBackgroundColor(getResources().getColor(	R.color.suturday));
				} else {
					mDate[i][j].setBackgroundColor(Color.WHITE);
				}
				mWeek[i].addView(mDate[i][j]);
			}
			table.addView(mWeek[i]);
		}
	}
	// ////////////////////////////////////////////////////////////////////////////////
	
    public void onWindowFocusChanged(boolean hasFocus) {  
      super.onWindowFocusChanged(hasFocus);
      
      if(check) {
  		LinearLayout linear = (LinearLayout) findViewById(R.id.linear);
        LinearLayout outline = (LinearLayout) findViewById(R.id.outline);
        TableRow tablerow = (TableRow) findViewById(R.id.tableRow);
         Button next = (Button) findViewById(R.id.next);
  		Button previous = (Button) findViewById(R.id.previous);
        height = linear.getHeight() - outline.getHeight() - tablerow.getHeight();
        
  		previous.setOnClickListener(new MonthButton());
  		next.setOnClickListener(new MonthButton());

  		createTable();
  		Calendar calendar = Calendar.getInstance();
  		mYear = calendar.get(Calendar.YEAR);
  		mMonth = calendar.get(Calendar.MONTH);

  		displayMonth(mMonth, mYear, 0);
  		check = false;
      }else {
    	  
      }
    }  
}
