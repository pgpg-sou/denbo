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
import android.util.Log;
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

public class pregnancy_parenting_record_of_deliveryp extends Activity {

	//目次の項目だけボタンを定義

	private String filepath = "/Yukari/Write/Pregnancy/pregnancy_parenting_record_of_delivery.xml";
	private String datapath = "/Yukari/Write/Pregnancy";
	private EditText editText_day1;
	private DatePickerDialog.OnDateSetListener varDateSetListener_day1;
	private TimePickerDialog.OnTimeSetListener varDateSetListener_time1;

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

	private String[] item_checkup_EditText_8tag = {"EditText_pregnancy_delivery_length_week",
			"EditText_pregnancy_delivery_length_day",
			"EditText_pregnancy_delivery_date",
			"EditText_pregnancy_delivery_date_time",
			"EditText_pregnancy_delivery_type_other",
			"EditText_pregnancy_delivery_way",
			"EditText_pregnancy_delivery_time",
			"EditText_pregnancy_delivery_bleeding_other",
			"EditText_pregnancy_delivery_blood_transfusion_other",
			"EditText_pregnancy_delivery_number_other",
			"EditText_pregnancy_delivery_weight",
			"EditText_pregnancy_delivery_height",
			"EditText_pregnancy_delivery_chest",
			"EditText_pregnancy_delivery_head",
			"EditText_pregnancy_delivery_special",
			"EditText_pregnancy_delivery_place",
			"EditText_pregnancy_delivery_doctor",
			"EditText_pregnancy_delivery_midwife",
	"EditText_pregnancy_delivery_other"};

	private String[] item_checkup_Spinner_8tag = {
			"Spinner_pregnancy_delivery_type_position",
			"Spinner_pregnancy_delivery_bleeding",
			"Spinner_pregnancy_delivery_blood_transfusion",
			"Spinner_pregnancy_delivery_sex",
			"Spinner_pregnancy_delivery_number",
			"Spinner_pregnancy_delivery_certificate",
	};
	public static int[] item_checkup_EditText_8 = {
		R.id.EditText_pregnancy_delivery_length_week,
		R.id.EditText_pregnancy_delivery_length_day,
		R.id.EditText_pregnancy_delivery_date,
		R.id.EditText_pregnancy_delivery_date_time,
		R.id.EditText_pregnancy_delivery_type_other,
		R.id.EditText_pregnancy_delivery_way,
		R.id.EditText_pregnancy_delivery_time,
		R.id.EditText_pregnancy_delivery_bleeding_other,
		R.id.EditText_pregnancy_delivery_blood_transfusion_other,
		R.id.EditText_pregnancy_delivery_number_other,
		R.id.EditText_pregnancy_delivery_weight,
		R.id.EditText_pregnancy_delivery_height,
		R.id.EditText_pregnancy_delivery_chest,
		R.id.EditText_pregnancy_delivery_head,
		R.id.EditText_pregnancy_delivery_special,
		R.id.EditText_pregnancy_delivery_place,
		R.id.EditText_pregnancy_delivery_doctor,
		R.id.EditText_pregnancy_delivery_midwife,
		R.id.EditText_pregnancy_delivery_other,};

	public static int[] item_checkup_Spinner_8 = {R.id.Spinner_pregnancy_delivery_type_position,
		R.id.Spinner_pregnancy_delivery_bleeding,
		R.id.Spinner_pregnancy_delivery_blood_transfusion,
		R.id.Spinner_pregnancy_delivery_sex,
		R.id.Spinner_pregnancy_delivery_number,
		R.id.Spinner_pregnancy_delivery_certificate,};

	public static int[] item_checkup_label_8 = {
		R.array.diet_label,
		R.array.bleeding_label,
		R.array.blood_transfusion_label,
		R.array.sex_label,
		R.array.delivery_number_label,
		R.array.certificate_label,
	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pregnancy_record_of_deliveryp);	//画面レイアウトを指定(res/layout/index_read.xml)

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
								else if(tag.equals(item_checkup_Spinner_8tag[0])) {spinner_text[0] = parser.getText();}
								else if(tag.equals(item_checkup_Spinner_8tag[1])) {spinner_text[1] = parser.getText();}
								else if(tag.equals(item_checkup_Spinner_8tag[2])) {spinner_text[2] = parser.getText();}
								else if(tag.equals(item_checkup_Spinner_8tag[3])) {spinner_text[3] = parser.getText();}
								else if(tag.equals(item_checkup_Spinner_8tag[4])) {spinner_text[4] = parser.getText();}
								else if(tag.equals(item_checkup_Spinner_8tag[5])) {spinner_text[5] = parser.getText();}}
							break;
						case XmlPullParser.END_TAG: // 終了タグ
							break;
						}

					}
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "エラー発生", Toast.LENGTH_SHORT);
				}
			}
		}

		//日付出力
		editText_day1 = (EditText) findViewById(R.id.EditText_pregnancy_delivery_date);
		varDateSetListener_day1
		= new DatePickerDialog.OnDateSetListener() {
			public void onDateSet(
					DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				editText_day1.setText(year + "年"
						+ (monthOfYear + 1) + "月"
						+ dayOfMonth + "日");
			}
		};
		//時間出力



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
	public void datePickerShow1(View v) {
		Calendar calendar = Calendar.getInstance();
		DatePickerDialog dateDialog	= new DatePickerDialog(
				this,
				varDateSetListener_day1,
				calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH));
		dateDialog.show();
	}

	public void timePickerShow1(View v) {
		// TODO Auto-generated method stub
		new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				// 時間が設定されたときの処理
				EditText editText_day2 = (EditText) findViewById(R.id.EditText_pregnancy_delivery_date_time);
				editText_day2.setText(String.format("%02d:%02d", hourOfDay, minute));
			}

		} , 13, 0, true)
		.show();

	}

	private void button_Write_cancel() {
		Intent intent_cancel = new Intent(getApplicationContext(),pregnancy_parenting_record_of_delivery.class);
		startActivity(intent_cancel);
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
		Intent intent_cancel = new Intent(getApplicationContext(),pregnancy_parenting_record_of_delivery.class);
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
