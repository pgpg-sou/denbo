package com.example.dneshiboshiken;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;

public class CountDate extends Activity {

	public int count(String past, String current) {

		String[] pastDate = past.split("\\.");
		String[] currentDate = current.split("\\.");

		Calendar cal1, cal2;
		Date date1, date2;
		cal1 = Calendar.getInstance(); // カレンダーオブジェクトの生成
		cal1.set(Integer.parseInt(currentDate[0]),
				Integer.parseInt(currentDate[1]),
				Integer.parseInt(currentDate[2]));

		cal2 = Calendar.getInstance(); // カレンダーオブジェクトの生成
		cal2.set(Integer.parseInt(pastDate[0]), Integer.parseInt(pastDate[1]),
				Integer.parseInt(pastDate[2]));

		// CalendarクラスをDateクラスに変換
		date1 = cal1.getTime();
		date2 = cal2.getTime();

		// Dateクラスをlongに変換
		long d1, d2;
		d1 = date1.getTime();
		d2 = date2.getTime();

		// 日付の間隔を計算
		long mean = (d1 - d2) / (24 * 60 * 60 * 1000);
		
		return (int)mean;
	}
}
