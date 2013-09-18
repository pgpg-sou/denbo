//教育機能の目次
package com.example.dneshiboshiken;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class pregnancy_teeth_reportp extends Activity {

	//目次の項目だけボタンを定義

		private String filepath = "/Yukari/Write/Pregnancy/pregnancy_teeth_report.xml";
		private String datapath = "/Yukari/Write/Pregnancy";
		private TimePickerDialog.OnTimeSetListener varDateSetListener_time1;

		private EditText[] editText_day1 = new EditText[editText_day.length];
		private Button[] dayin = new Button[editText_day.length];
		private DatePickerDialog.OnDateSetListener varDateSetListener_day1 = null;
		private static int daytext = 0;


		private Button button_Write_cancel;
		private Button button_Write_save;
		private Button button_Write_gallery;
		private Button button_Write_camera;
		private Spinner[] tvParam_Spinner = new Spinner[item_checkup_Spinner_8.length];
		private String[] spinner_text = new String[item_checkup_Spinner_8.length];

		//ここはクラス内で実行
		static int REQUEST_ACTION_PICK = 1;
		static final int REQUEST_CAPTURE_IMAGE = 100;
		private static final String APPLICATION_NAME = "PATOM";
		private static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/Yukari/Photo/" + APPLICATION_NAME;

		private static int REQUEST_CAMERA = 1;
		private static Uri mImageUri; // インスタンス変数

		final boolean[] checkedItems = new boolean[10];

		private String[] item_checkup_EditText_8tag = {"EditText_teeth_report_examination1",
				"EditText_teeth_report_week1",
				"EditText_teeth_report_need_number1",
				"EditText_teeth_report_other1",
				"EditText_teeth_report_name1",
				"EditText_teeth_report_week2",
				"EditTextteeth_report_top1_1",
				"EditTextteeth_report_top2_1",
				"EditTextteeth_report_top3_1",
				"EditTextteeth_report_top4_1",
				"EditTextteeth_report_top5_1",
				"EditTextteeth_report_top6_1",
				"EditTextteeth_report_top7_1",
				"EditTextteeth_report_top8_1",
				"EditTextteeth_report_top9_1",
				"EditTextteeth_report_top10_1",
				"EditTextteeth_report_top11_1",
				"EditTextteeth_report_top12_1",
				"EditTextteeth_report_top13_1",
				"EditTextteeth_report_top14_1",
				"EditTextteeth_report_top15_1",
				"EditTextteeth_report_top16_1",
				"EditText_teeth_report_bottom1_1",
				"EditText_teeth_report_bottom2_1",
				"EditText_teeth_report_bottom3_1",
				"EditText_teeth_report_bottom4_1",
				"EditText_teeth_report_bottom5_1",
				"EditText_teeth_report_bottom6_1",
				"EditText_teeth_report_bottom7_1",
				"EditText_teeth_report_bottom8_1",
				"EditText_teeth_report_bottom9_1",
				"EditText_teeth_report_bottom10_1",
				"EditText_teeth_report_bottom11_1",
				"EditText_teeth_report_bottom12_1",
				"EditText_teeth_report_bottom13_1",
				"EditText_teeth_report_bottom14_1",
				"EditText_teeth_report_bottom15_1",
				"EditText_teeth_report_bottom16_1",
				"EditText_teeth_report_other2",
				"EditText_teeth_report_examination2",
				"EditText_teeth_report_name2",
				"EditText_teeth_report_week3",
				"EditTextteeth_report_top1_2",
				"EditTextteeth_report_top2_2",
				"EditTextteeth_report_top3_2",
				"EditTextteeth_report_top4_2",
				"EditTextteeth_report_top5_2",
				"EditTextteeth_report_top6_2",
				"EditTextteeth_report_top7_2",
				"EditTextteeth_report_top8_2",
				"EditTextteeth_report_top9_2",
				"EditTextteeth_report_top10_2",
				"EditTextteeth_report_top11_2",
				"EditTextteeth_report_top12_2",
				"EditTextteeth_report_top13_2",
				"EditTextteeth_report_top14_2",
				"EditTextteeth_report_top15_2",
				"EditTextteeth_report_top16_2",
				"EditText_teeth_report_bottom1_2",
				"EditText_teeth_report_bottom2_2",
				"EditText_teeth_report_bottom3_2",
				"EditText_teeth_report_bottom4_2",
				"EditText_teeth_report_bottom5_2",
				"EditText_teeth_report_bottom6_2",
				"EditText_teeth_report_bottom7_2",
				"EditText_teeth_report_bottom8_2",
				"EditText_teeth_report_bottom9_2",
				"EditText_teeth_report_bottom10_2",
				"EditText_teeth_report_bottom11_2",
				"EditText_teeth_report_bottom12_2",
				"EditText_teeth_report_bottom13_2",
				"EditText_teeth_report_bottom14_2",
				"EditText_teeth_report_bottom15_2",
				"EditText_teeth_report_bottom16_2",
				"EditText_teeth_report_other3",
				"EditText_teeth_report_examination3",
				"EditText_teeth_report_name3"};

		private String[] item_checkup_Spinner_8tag = {
				"Spinner_teeth_report_need1",
				"Spinner_teeth_report_calculus1",
				"Spinner_teeth_report_inflammation1",
				"Spinner_teeth_report_pregnancy_or_after1",
				"Spinner_teeth_report_calculus2",
				"Spinner_teeth_report_inflammation2",
				"Spinner_teeth_report_pregnancy_or_after2",
				"Spinner_teeth_report_calculus3",
				"Spinner_teeth_report_inflammation3"
		};
		public static int[] item_checkup_EditText_8 = {
			R.id.EditText_teeth_report_examination1,
			R.id.EditText_teeth_report_week1,
			R.id.EditText_teeth_report_need_number1,
			R.id.EditText_teeth_report_other1,
			R.id.EditText_teeth_report_name1,
			R.id.EditText_teeth_report_week2,
			R.id.EditTextteeth_report_top1_1,
			R.id.EditTextteeth_report_top2_1,
			R.id.EditTextteeth_report_top3_1,
			R.id.EditTextteeth_report_top4_1,
			R.id.EditTextteeth_report_top5_1,
			R.id.EditTextteeth_report_top6_1,
			R.id.EditTextteeth_report_top7_1,
			R.id.EditTextteeth_report_top8_1,
			R.id.EditTextteeth_report_top9_1,
			R.id.EditText_teeth_report_top10_1,
			R.id.EditText_teeth_report_top11_1,
			R.id.EditText_teeth_report_top12_1,
			R.id.EditText_teeth_report_top13_1,
			R.id.EditText_teeth_report_top14_1,
			R.id.EditText_teeth_report_top15_1,
			R.id.EditText_teeth_report_top16_1,
			R.id.EditText_teeth_report_bottom1_1,
			R.id.EditText_teeth_report_bottom2_1,
			R.id.EditText_teeth_report_bottom3_1,
			R.id.EditText_teeth_report_bottom4_1,
			R.id.EditText_teeth_report_bottom5_1,
			R.id.EditText_teeth_report_bottom6_1,
			R.id.EditText_teeth_report_bottom7_1,
			R.id.EditText_teeth_report_bottom8_1,
			R.id.EditText_teeth_report_bottom9_1,
			R.id.EditText_teeth_report_bottom10_1,
			R.id.EditText_teeth_report_bottom11_1,
			R.id.EditText_teeth_report_bottom12_1,
			R.id.EditText_teeth_report_bottom13_1,
			R.id.EditText_teeth_report_bottom14_1,
			R.id.EditText_teeth_report_bottom15_1,
			R.id.EditText_teeth_report_bottom16_1,
			R.id.EditText_teeth_report_other2,
			R.id.EditText_teeth_report_examination2,
			R.id.EditText_teeth_report_name2,
			R.id.EditText_teeth_report_week3,
			R.id.EditText_teeth_report_top1_2,
			R.id.EditText_teeth_report_top2_2,
			R.id.EditText_teeth_report_top3_2,
			R.id.EditText_teeth_report_top4_2,
			R.id.EditText_teeth_report_top5_2,
			R.id.EditText_teeth_report_top6_2,
			R.id.EditText_teeth_report_top7_2,
			R.id.EditText_teeth_report_top8_2,
			R.id.EditText_teeth_report_top9_2,
			R.id.EditText_teeth_report_top10_2,
			R.id.EditText_teeth_report_top11_2,
			R.id.EditText_teeth_report_top12_2,
			R.id.EditText_teeth_report_top13_2,
			R.id.EditText_teeth_report_top14_2,
			R.id.EditText_teeth_report_top15_2,
			R.id.EditText_teeth_report_top16_2,
			R.id.EditText_teeth_report_bottom1_2,
			R.id.EditText_teeth_report_bottom2_2,
			R.id.EditText_teeth_report_bottom3_2,
			R.id.EditText_teeth_report_bottom4_2,
			R.id.EditText_teeth_report_bottom5_2,
			R.id.EditText_teeth_report_bottom6_2,
			R.id.EditText_teeth_report_bottom7_2,
			R.id.EditText_teeth_report_bottom8_2,
			R.id.EditText_teeth_report_bottom9_2,
			R.id.EditText_teeth_report_bottom10_2,
			R.id.EditText_teeth_report_bottom11_2,
			R.id.EditText_teeth_report_bottom12_2,
			R.id.EditText_teeth_report_bottom13_2,
			R.id.EditText_teeth_report_bottom14_2,
			R.id.EditText_teeth_report_bottom15_2,
			R.id.EditText_teeth_report_bottom16_2,
			R.id.EditText_teeth_report_other3,
			R.id.EditText_teeth_report_examination3,
			R.id.EditText_teeth_report_name3,};

		public static int[] item_checkup_Spinner_8 = {R.id.Spinner_teeth_report_need1,
			R.id.Spinner_teeth_report_calculus1,
			R.id.Spinner_teeth_report_inflammation1,
			R.id.Spinner_teeth_report_pregnancy_or_after1,
			R.id.Spinner_teeth_report_calculus2,
			R.id.Spinner_teeth_report_inflammation2,
			R.id.Spinner_teeth_report_pregnancy_or_after2,
			R.id.Spinner_teeth_report_calculus3,
			R.id.Spinner_teeth_report_inflammation3,};

		public static int[] item_checkup_label_8 = {
			R.array.tooth_calculus_label,
			R.array.tooth_calculus_label,
			R.array.inflammation_label,
			R.array.pregnancy_after_label,
			R.array.tooth_calculus_label,
			R.array.inflammation_label,
			R.array.pregnancy_after_label,
			R.array.tooth_calculus_label,
			R.array.inflammation_label,
		};

		public static int[] Button_day = {
			R.id.Button_teeth_report_examination1,
			R.id.Button_teeth_report_examination2,
			R.id.Button_teeth_report_examination3,
		};

		public static int[] editText_day = {
			R.id.EditText_teeth_report_examination1,
			R.id.EditText_teeth_report_examination2,
			R.id.EditText_teeth_report_examination3,
		};

		/** Called when the activity is first created. */
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.pregnancy_teeth_reportp);	//画面レイアウトを指定(res/layout/index_read.xml)

			EditText[] tvParam_EditText = new EditText[item_checkup_EditText_8.length];

			//初期値読み込み
			File dir = new File(Environment.getExternalStorageDirectory().getPath());
			if(dir.exists()){

				File file = new File(dir.getAbsolutePath()+filepath);
				if (file.exists()) {
					try {
						XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
						factory.setNamespaceAware(true);
						XmlPullParser parser = factory.newPullParser();
						FileInputStream fis = new FileInputStream(file);
						parser.setInput(new InputStreamReader(fis));

						// EditTextインスタンスの取得
						for (int i1 = 0; i1 < item_checkup_EditText_8.length; i1++) {
							tvParam_EditText[i1] = (EditText)findViewById(item_checkup_EditText_8[i1]);
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
									if(tag.equals(item_checkup_EditText_8tag[0])) {tvParam_EditText[0].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[1])) {tvParam_EditText[1].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[2])) {tvParam_EditText[2].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[3])) {tvParam_EditText[3].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[4])) {tvParam_EditText[4].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[5])) {tvParam_EditText[5].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[6])) {tvParam_EditText[6].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[7])) {tvParam_EditText[7].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[8])) {tvParam_EditText[8].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[9])) {tvParam_EditText[9].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[10])) {tvParam_EditText[10].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[11])) {tvParam_EditText[11].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[12])) {tvParam_EditText[12].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[13])) {tvParam_EditText[13].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[14])) {tvParam_EditText[14].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[15])) {tvParam_EditText[15].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[16])) {tvParam_EditText[16].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[17])) {tvParam_EditText[17].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[18])) {tvParam_EditText[18].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[19])) {tvParam_EditText[19].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[20])) {tvParam_EditText[20].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[21])) {tvParam_EditText[21].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[22])) {tvParam_EditText[22].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[23])) {tvParam_EditText[23].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[24])) {tvParam_EditText[24].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[25])) {tvParam_EditText[25].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[26])) {tvParam_EditText[26].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[27])) {tvParam_EditText[27].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[28])) {tvParam_EditText[28].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[29])) {tvParam_EditText[29].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[30])) {tvParam_EditText[30].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[31])) {tvParam_EditText[31].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[32])) {tvParam_EditText[32].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[33])) {tvParam_EditText[33].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[34])) {tvParam_EditText[34].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[35])) {tvParam_EditText[35].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[36])) {tvParam_EditText[36].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[37])) {tvParam_EditText[37].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[38])) {tvParam_EditText[38].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[39])) {tvParam_EditText[39].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[40])) {tvParam_EditText[40].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[41])) {tvParam_EditText[41].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[42])) {tvParam_EditText[42].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[43])) {tvParam_EditText[43].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[44])) {tvParam_EditText[44].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[45])) {tvParam_EditText[45].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[46])) {tvParam_EditText[46].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[47])) {tvParam_EditText[47].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[48])) {tvParam_EditText[48].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[49])) {tvParam_EditText[49].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[50])) {tvParam_EditText[50].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[51])) {tvParam_EditText[51].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[52])) {tvParam_EditText[52].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[53])) {tvParam_EditText[53].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[54])) {tvParam_EditText[54].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[55])) {tvParam_EditText[55].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[56])) {tvParam_EditText[56].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[57])) {tvParam_EditText[57].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[58])) {tvParam_EditText[58].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[59])) {tvParam_EditText[59].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[60])) {tvParam_EditText[60].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[61])) {tvParam_EditText[61].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[62])) {tvParam_EditText[62].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[63])) {tvParam_EditText[63].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[64])) {tvParam_EditText[64].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[65])) {tvParam_EditText[65].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[66])) {tvParam_EditText[66].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[67])) {tvParam_EditText[67].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[68])) {tvParam_EditText[68].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[69])) {tvParam_EditText[69].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[70])) {tvParam_EditText[70].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[71])) {tvParam_EditText[71].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[72])) {tvParam_EditText[72].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[73])) {tvParam_EditText[73].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[74])) {tvParam_EditText[74].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[75])) {tvParam_EditText[75].setText(value);}
									else if(tag.equals(item_checkup_EditText_8tag[76])) {tvParam_EditText[76].setText(value);}
									else if(tag.equals(item_checkup_Spinner_8tag[0])) {spinner_text[0] = parser.getText();}
									else if(tag.equals(item_checkup_Spinner_8tag[1])) {spinner_text[1] = parser.getText();}
									else if(tag.equals(item_checkup_Spinner_8tag[2])) {spinner_text[2] = parser.getText();}
									else if(tag.equals(item_checkup_Spinner_8tag[3])) {spinner_text[3] = parser.getText();}
									else if(tag.equals(item_checkup_Spinner_8tag[4])) {spinner_text[4] = parser.getText();}
									else if(tag.equals(item_checkup_Spinner_8tag[5])) {spinner_text[5] = parser.getText();}
									else if(tag.equals(item_checkup_Spinner_8tag[6])) {spinner_text[6] = parser.getText();}
									else if(tag.equals(item_checkup_Spinner_8tag[7])) {spinner_text[7] = parser.getText();}
									else if(tag.equals(item_checkup_Spinner_8tag[8])) {spinner_text[8] = parser.getText();}}
								break;
							case XmlPullParser.END_TAG: // 終了タグ
								break;
							}

						}
					} catch (Exception e) {
						Toast.makeText(getApplicationContext(), "エラー発生", Toast.LENGTH_SHORT);
					}
				}
				for (int i1 = 0; i1 < Button_day.length; i1++) {
					dayin[i1] = (Button) findViewById(Button_day[i1]);
					editText_day1[i1] = (EditText) findViewById(editText_day[i1]);
				}

				varDateSetListener_day1
				= new DatePickerDialog.OnDateSetListener() {
					public void onDateSet(
							DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						editText_day1[daytext].setText(year + "年"
								+ (monthOfYear + 1) + "月"
								+ dayOfMonth + "日");
					}
				};

			}

			for (int i1 = 0; i1 < Button_day.length; i1++) {
				final int n = i1 ;
				dayin[i1].setOnClickListener(new View.OnClickListener(){
					public void onClick(View v) {
						button_day(n);
					}
				});
			}

			//それぞれのボタンにクリック時の処理を表示


			button_Write_cancel = (Button) findViewById(R.id.Button_checkup_cancel);
			button_Write_cancel.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v) {
					button_Write_cancel();
				}
			});

			button_Write_save = (Button) findViewById(R.id.Button_checkup_save);
			button_Write_save.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v) {
					button_Write_save();
				}
			});

			execSpinners();
		}

		private void execSpinners() {
			String[][] label = new String[item_checkup_Spinner_8.length][];
			// Spinnerインスタンスの取得
			for (int i1 = 0; i1 < item_checkup_Spinner_8.length; i1++) {
				tvParam_Spinner[i1] = (Spinner)findViewById(item_checkup_Spinner_8[i1]);
				label[i1] = getResources().getStringArray(item_checkup_label_8[i1]);

				ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, label[i1]);
				adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				tvParam_Spinner[i1].setAdapter(adapter1);

				for(int i2 = 0; i2 < label[i1].length; i2++) {//初期値設定
					if (label[i1][i2].equals(spinner_text[i1]))  {
						tvParam_Spinner[i1].setSelection(i2);
					}
				}
				}
		}

		//日付出力関数
		public void button_day(int i1){
			daytext = i1;
			datePickerShow1();
			//日付出
		}

		//日付出力関数
		public void datePickerShow1() {
			Calendar calendar = Calendar.getInstance();
			DatePickerDialog dateDialog	= new DatePickerDialog(
					this,
					varDateSetListener_day1,
					calendar.get(Calendar.YEAR),
					calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH));
			dateDialog.show();
		}


		private void button_Write_cancel() {
			finish();
		}

		private void button_Write_save() {
			Spinner spinner;
			Text text;

			//ディレクトリの作成
			String target_path = Environment.getExternalStorageDirectory().getAbsolutePath() + datapath;
			File dir = new File(target_path);
			if(!dir.exists()){
				dir.mkdirs();
			}
			DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
			try {
				DocumentBuilder dbuilder = dbfactory.newDocumentBuilder();
				Document document = dbuilder.newDocument();
				Element root = document.createElement("members");

				//edittextの中身を保存
				for (int i1 = 0; i1 < item_checkup_EditText_8.length; i1++) {
					Element element1 = document.createElement(item_checkup_EditText_8tag[i1]);
					String string1 = ((EditText) findViewById(item_checkup_EditText_8[i1])).getText().toString();
					text = document.createTextNode(string1);
					element1.appendChild(text);
					root.appendChild(element1);
				}
				//spinnerの中身を保存
				for (int i1 = 0; i1 < item_checkup_Spinner_8.length; i1++) {
					spinner = (Spinner) findViewById(item_checkup_Spinner_8[i1]);
					Element element1 = document.createElement(item_checkup_Spinner_8tag[i1]);
					String string1 = spinner.getSelectedItem().toString();
					text = document.createTextNode(string1);
					element1.appendChild(text);
					root.appendChild(element1);
				}

				document.appendChild(root);

				TransformerFactory tffactory = TransformerFactory.newInstance();
				Transformer transformer = tffactory.newTransformer();

				//保存ファイルの作成
				String filePath = Environment.getExternalStorageDirectory() + filepath;
				File file = new File(filePath);
				file.getParentFile().mkdir();
				transformer.transform(new DOMSource(document), new StreamResult(file));


			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Toast.makeText(this, "保存が完了しました", Toast.LENGTH_LONG).show();
			Intent intent_cancel = new Intent(getApplicationContext(),pregnancy_teeth_report.class);
			startActivity(intent_cancel);

			finish();
		}

		//バックキー
		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if(keyCode==KeyEvent.KEYCODE_BACK){

				finish();
				return true;
			}
			return false;
		}
	}
