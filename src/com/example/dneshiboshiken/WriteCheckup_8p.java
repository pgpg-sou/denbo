//教育機能の目次
package com.example.dneshiboshiken;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
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
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.util.Log;
import android.util.Xml;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Environment;



public class WriteCheckup_8p extends Activity{

	//目次の項目だけボタンを定義
	private EditText editText_day1;
	private DatePickerDialog.OnDateSetListener varDateSetListener_day1;
	private EditText editText_day2;
	private DatePickerDialog.OnDateSetListener varDateSetListener_day2;
	private EditText editText_day3;
	private DatePickerDialog.OnDateSetListener varDateSetListener_day3;

	private Button button_Write_cancel;
	private Button button_Write_save;
	private Button button_Write_gallery;
	private Button button_Write_camera;
	//ここはクラス内で実行
	static int REQUEST_ACTION_PICK = 1;
	static final int REQUEST_CAPTURE_IMAGE = 100;
	private static final String APPLICATION_NAME = "PATOM";
	private static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/Yukari/Photo/" + APPLICATION_NAME;

	private static int REQUEST_CAMERA = 1;
	private static Uri mImageUri; // インスタンス変数

	private Spinner[] tvParam_Spinner = new Spinner[item_checkup_Spinner_8.length];
	private String[] spinner_text = new String[item_checkup_Spinner_8.length];
	final boolean[] checkedItems = new boolean[10];

	private String[] item_checkup_EditText_8tag = {"EditText_write_checkup_8p_examination",
			"EditText_write_checkup_8p_weight",
			"EditText_write_checkup_8p_height",
			"EditText_write_checkup_8p_chest",
			"EditText_write_checkup_8p_head",
			"EditText_write_checkup_8p_fully_milk_other",
			"EditText_write_checkup_8p_eye_other",
			"EditText_write_checkup_8p_ears_other",
			"EditText_write_checkup_8p_immunization_text",
			"EditText_write_checkup_8p_health",
			"EditText_write_checkup_8p_tooth_top1",
			"EditText_write_checkup_8p_tooth_top2",
			"EditText_write_checkup_8p_tooth_top3",
			"EditText_write_checkup_8p_tooth_top4",
			"EditText_write_checkup_8p_tooth_top5",
			"EditText_write_checkup_8p_tooth_top6",
			"EditText_write_checkup_8p_tooth_top7",
			"EditText_write_checkup_8p_tooth_top8",
			"EditText_write_checkup_8p_tooth_top9",
			"EditText_write_checkup_8p_tooth_top10",
			"EditText_write_checkup_8p_tooth_bottom1",
			"EditText_write_checkup_8p_tooth_bottom2",
			"EditText_write_checkup_8p_tooth_bottom3",
			"EditText_write_checkup_8p_tooth_bottom4",
			"EditText_write_checkup_8p_tooth_bottom5",
			"EditText_write_checkup_8p_tooth_bottom6",
			"EditText_write_checkup_8p_tooth_bottom7",
			"EditText_write_checkup_8p_tooth_bottom8",
			"EditText_write_checkup_8p_tooth_bottom9",
			"EditText_write_checkup_8p_tooth_bottom10",
			"EditText_write_checkup_8p_tooth_need_number",
			"EditText_write_checkup_8p_tooth_gums_other",
			"EditText_write_checkup_8p_tooth_examination",
			"EditText_write_checkup_8p_other",
			"EditText_write_checkup_8p_name",};
	private String[] item_checkup_Spinner_8tag = {
			"Spinner_write_checkup_8p_diet",
			"Spinner_write_checkup_8p_mother_milk",
			"Spinner_write_checkup_8p_fully_milk",
			"Spinner_write_checkup_8p_eye",
			"Spinner_write_checkup_8p_ears",
			"Spinner_write_checkup_8p_tooth_pattern",
			"Spinner_write_checkup_8p_tooth_need",
			"Spinner_write_checkup_8p_tooth_hygiene",
			"Spinner_write_checkup_8p_tooth_occlusion",
			"Spinner_write_checkup_8p_tooth_gums",
	};
	private String[] item_checkup_CheckBox_8tag = {
			"CheckBox_write_checkup_8p_immunization_diphteria",
			"CheckBox_write_checkup_8p_immunization_pertussis",
			"CheckBox_write_checkup_8p_immunization_tetanus",
			"CheckBox_write_checkup_8p_immunization_polio",
			"CheckBox_write_checkup_8p_immunization_bcg",
			"CheckBox_write_checkup_8p_immunization_measles",
			"CheckBox_write_checkup_8p_immunization_rubella",
	};
	public static int[] item_checkup_EditText_8 = {R.id.EditText_write_checkup_8p_examination,
		R.id.EditText_write_checkup_8p_weight,
		R.id.EditText_write_checkup_8p_height,
		R.id.EditText_write_checkup_8p_chest,
		R.id.EditText_write_checkup_8p_head,
		R.id.EditText_write_checkup_8p_fully_milk_other,
		R.id.EditText_write_checkup_8p_eye_other,
		R.id.EditText_write_checkup_8p_ears_other,
		R.id.EditText_write_checkup_8p_immunization_text,
		R.id.EditText_write_checkup_8p_health,
		R.id.EditText_write_checkup_8p_tooth_top1,
		R.id.EditText_write_checkup_8p_tooth_top2,
		R.id.EditText_write_checkup_8p_tooth_top3,
		R.id.EditText_write_checkup_8p_tooth_top4,
		R.id.EditText_write_checkup_8p_tooth_top5,
		R.id.EditText_write_checkup_8p_tooth_top6,
		R.id.EditText_write_checkup_8p_tooth_top7,
		R.id.EditText_write_checkup_8p_tooth_top8,
		R.id.EditText_write_checkup_8p_tooth_top9,
		R.id.EditText_write_checkup_8p_tooth_top10,
		R.id.EditText_write_checkup_8p_tooth_bottom1,
		R.id.EditText_write_checkup_8p_tooth_bottom2,
		R.id.EditText_write_checkup_8p_tooth_bottom3,
		R.id.EditText_write_checkup_8p_tooth_bottom4,
		R.id.EditText_write_checkup_8p_tooth_bottom5,
		R.id.EditText_write_checkup_8p_tooth_bottom6,
		R.id.EditText_write_checkup_8p_tooth_bottom7,
		R.id.EditText_write_checkup_8p_tooth_bottom8,
		R.id.EditText_write_checkup_8p_tooth_bottom9,
		R.id.EditText_write_checkup_8p_tooth_bottom10,
		R.id.EditText_write_checkup_8p_tooth_need_number,
		R.id.EditText_write_checkup_8p_tooth_gums_other,
		R.id.EditText_write_checkup_8p_tooth_examination,
		R.id.EditText_write_checkup_8p_other,
		R.id.EditText_write_checkup_8p_name,};
	public static int[] item_checkup_Spinner_8 = {R.id.Spinner_write_checkup_8p_diet,
		R.id.Spinner_write_checkup_8p_mother_milk,
		R.id.Spinner_write_checkup_8p_fully_milk,
		R.id.Spinner_write_checkup_8p_eye,
		R.id.Spinner_write_checkup_8p_ears,
		R.id.Spinner_write_checkup_8p_tooth_pattern,
		R.id.Spinner_write_checkup_8p_tooth_need,
		R.id.Spinner_write_checkup_8p_tooth_hygiene,
		R.id.Spinner_write_checkup_8p_tooth_occlusion,
		R.id.Spinner_write_checkup_8p_tooth_gums,};
	public static int[] item_checkup_label_8 = {
		R.array.type_label,
		R.array.mother_milk_label,
		R.array.fully_milk_label,
		R.array.eye_label,
		R.array.eye_label,
		R.array.tooth_pattern_label,
		R.array.none_label,
		R.array.tooth_hygiene_label,
		R.array.tooth_occlusion_label,
		R.array.tooth_gums_label,
	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.write_checkup_8p);	//画面レイアウトを指定(res/layout/index_read.xml)

		EditText[] tvParam_EditText = new EditText[item_checkup_EditText_8.length];

		//初期値読み込み
		File dir = new File(Environment.getExternalStorageDirectory().getPath());
		if(dir.exists()){

			File file = new File(dir.getAbsolutePath()+"/Yukari/Write/Checkup/WriteCheckup_8file.xml");
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
								else if(tag.equals(item_checkup_Spinner_8tag[0])) {spinner_text[0] = parser.getText();}
								else if(tag.equals(item_checkup_Spinner_8tag[1])) {spinner_text[1] = parser.getText();}
								else if(tag.equals(item_checkup_Spinner_8tag[2])) {spinner_text[2] = parser.getText();}
								else if(tag.equals(item_checkup_Spinner_8tag[3])) {spinner_text[3] = parser.getText();}
								else if(tag.equals(item_checkup_Spinner_8tag[4])) {spinner_text[4] = parser.getText();}
								else if(tag.equals(item_checkup_Spinner_8tag[5])) {spinner_text[5] = parser.getText();}
								else if(tag.equals(item_checkup_Spinner_8tag[6])) {spinner_text[6] = parser.getText();}
								else if(tag.equals(item_checkup_Spinner_8tag[7])) {spinner_text[7] = parser.getText();}
								else if(tag.equals(item_checkup_Spinner_8tag[8])) {spinner_text[8] = parser.getText();}
								else if(tag.equals(item_checkup_Spinner_8tag[9])) {spinner_text[9] = parser.getText();}
								else if(tag.equals(item_checkup_CheckBox_8tag[0])) {checkedItems[0] = Boolean.valueOf( value );}
								else if(tag.equals(item_checkup_CheckBox_8tag[1])) {checkedItems[1] = Boolean.valueOf( value );}
								else if(tag.equals(item_checkup_CheckBox_8tag[2])) {checkedItems[2] = Boolean.valueOf( value );}
								else if(tag.equals(item_checkup_CheckBox_8tag[3])) {checkedItems[3] = Boolean.valueOf( value );}
								else if(tag.equals(item_checkup_CheckBox_8tag[4])) {checkedItems[4] = Boolean.valueOf( value );}
								else if(tag.equals(item_checkup_CheckBox_8tag[5])) {checkedItems[5] = Boolean.valueOf( value );}
								else if(tag.equals(item_checkup_CheckBox_8tag[6])) {checkedItems[6] = Boolean.valueOf( value );}}
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
		//画像読み込み
		if(dir.exists()){
			File view = new File(dir.getAbsolutePath()+"/Yukari/Photo/imageView_write_checkup_8p.png");
			if (view.exists()) {
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inJustDecodeBounds = true;
				Bitmap bmp1 = BitmapFactory.decodeFile(view.getPath(), options );

				int reqWidth = 0;
				int reqHeight = 0;
				options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
				int scale = Math.max(1, 1);
				options.inJustDecodeBounds = false;
				options.inSampleSize = scale;
				Bitmap image = BitmapFactory.decodeFile(view.getPath(), options);

				((ImageView)findViewById(R.id.imageview_write_checkup_8p)).setImageBitmap(image);
			}else{
				//存在しない
			}
		}


		//日付出力
		editText_day1 = (EditText) findViewById(R.id.EditText_write_checkup_8p_examination);
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
		editText_day2 = (EditText) findViewById(R.id.EditText_write_checkup_8p_tooth_examination);
		varDateSetListener_day2
		= new DatePickerDialog.OnDateSetListener() {
			public void onDateSet(
					DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				editText_day2.setText(year + "年"
						+ (monthOfYear + 1) + "月"
						+ dayOfMonth + "日");
			}
		};


		//それぞれのボタンにクリック時の処理を表示
		button_Write_camera = (Button) findViewById(R.id.Button_write_checkup_8p_camera);
		button_Write_camera.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				button_Write_camera();
			}
		});

		button_Write_gallery = (Button) findViewById(R.id.Button_write_checkup_8p_gallery);
		button_Write_gallery.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				button_Write_gallery();
			}
		});


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
		//スピナーへの入力
		execSpinners();

	}
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
	//スピナー入出力関数

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
	public void datePickerShow2(View v) {
		Calendar calendar = Calendar.getInstance();
		DatePickerDialog dateDialog	= new DatePickerDialog(
				this,
				varDateSetListener_day2,
				calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH));
		dateDialog.show();
	}

	//チェックボックスダイアログ
	public void alertDialogShow_immunization(View v) {

		final TextView varTextView;
		varTextView = (TextView) findViewById(R.id.EditText_write_checkup_8p_immunization_text);

		//必要であればクラス内で宣言
		final String[] items;
		items = getResources().getStringArray(R.array.write_checkup_immunization_label);
		/*
		for(int i1 = 0; i1 < items.length; i1++) {
			checkedItems[i1]=false;
		}
		 */
		AlertDialog.Builder alertDialogBuilder =
				new AlertDialog.Builder(this);
		alertDialogBuilder
		.setTitle("受けているものにチェックする")
		.setMultiChoiceItems(items, checkedItems,
				new DialogInterface.OnMultiChoiceClickListener() {
			public void onClick(DialogInterface dialog,
					int which, boolean isChecked) {
				// 項目が選択されたときの処理
				checkedItems[which] = isChecked;
			}
		})
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which ) {
				// OKボタンが押されたときの処理
				varTextView.setText("");
				for(int i1 = 0; i1 < items.length; i1++) {
					if(checkedItems[i1]==true){
						varTextView.append(items[i1] +",");
					}
				}
			}
		}).show();
	}

	//ボタンクリックによって呼び出される処理
	//classの呼び出し(EMCHH.java)で行った内容と同様
	private void button_Write_gallery() {

		//実行フロー
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		//これだとギャラリー専門が開きます。
		//Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setType("image/*");
		//createChooserを使うと選択ダイアログのタイトルを変更する事ができます。
		startActivityForResult(Intent.createChooser(intent,"select"), REQUEST_ACTION_PICK);
		//デフォルトで「アプリ選択」と出ます。
		//startActivityForResult(intent, REQUEST_ACTION_PICK);

	}
	private void button_Write_camera() {
		Intent intent = new Intent();
		ContentValues values = new ContentValues();
		values.put(MediaStore.Images.Media.TITLE,"aaaaa");//任意のタイトル（拡張子は付けない）
		values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
		//URIの取得
		mImageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
		// mImageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory().getPath()  + System.currentTimeMillis() +"."));
		intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
		startActivityForResult(intent, REQUEST_CAMERA);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == RESULT_OK){
			Bitmap imageBitmap = null;
			if(requestCode == REQUEST_ACTION_PICK){
				Uri uri;
				try {
					if(data == null){//カメラの時
						uri = mImageUri;
					}else uri = data.getData();//ギャラリーの時

					ContentResolver contentResolver = getContentResolver();
					InputStream inputStream;
					BitmapFactory.Options imageOptions;

					// メモリ上に画像を読み込まず、画像サイズ情報のみを取得する
					inputStream = contentResolver.openInputStream(uri);
					imageOptions = new BitmapFactory.Options();

					imageOptions.inJustDecodeBounds = true;
					BitmapFactory.decodeStream(inputStream, null, imageOptions);
					inputStream.close();
					// もし読み込む画像が大きかったら縮小して読み込む
					inputStream = contentResolver.openInputStream(uri);
					if (imageOptions.outWidth > 512 && imageOptions.outHeight > 512) {
						imageOptions = new BitmapFactory.Options();
						imageOptions.inSampleSize = 4;
						imageBitmap = BitmapFactory.decodeStream(inputStream, null, imageOptions);
						//Toast.makeText(this, "圧縮しました。", Toast.LENGTH_LONG).show();

					} else {
						imageBitmap = BitmapFactory.decodeStream(inputStream, null, null);

					}
					inputStream.close();
					//Bitmapで普通に利用ができます。
					((ImageView)findViewById(R.id.imageview_write_checkup_8p)).setImageBitmap(imageBitmap);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				String filename = "imageView_write_checkup_8p.png";
				String path = Environment.getExternalStorageDirectory().getPath() + "/Yukari/Photo/" + filename;
				File file = new File(path);

				// 上の階層(アプリ名のディレクトリ)が存在しなかったら作成
				if(!file.getParentFile().exists()){
					file.getParentFile().mkdir();
				}

				try {
					OutputStream os = new FileOutputStream(file);
					imageBitmap.compress(CompressFormat.PNG, 50, os); // 拡張子、品質、出力先
					os.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}
	private void button_Write_cancel() {
		Intent intent_cancel = new Intent(getApplicationContext(),WriteCheckup_8.class);
		startActivity(intent_cancel);
		ImageView iv = (ImageView)findViewById(R.id.imageview_write_checkup_8p);
		iv.setImageDrawable(null);
		finish();
	}

	private void button_Write_save() {
		Spinner spinner;
		Text text;

		//ディレクトリの作成
		String target_path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Yukari/Write/Checkup";
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

			//CheckBoxの中身を保存
			for (int i1 = 0; i1 < item_checkup_CheckBox_8tag.length; i1++) {
				Element element1 = document.createElement(item_checkup_CheckBox_8tag[i1]);
				String string1 =  String.valueOf( checkedItems[i1] );//booleanとstring変換
				text = document.createTextNode(string1);
				element1.appendChild(text);
				root.appendChild(element1);
			}

			document.appendChild(root);

			TransformerFactory tffactory = TransformerFactory.newInstance();
			Transformer transformer = tffactory.newTransformer();

			//保存ファイルの作成
			String filePath = Environment.getExternalStorageDirectory() + "/Yukari/Write/Checkup/WriteCheckup_8file.xml";
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
		Intent intent_cancel = new Intent(getApplicationContext(),WriteCheckup_8.class);
		startActivity(intent_cancel);
		ImageView iv = (ImageView)findViewById(R.id.imageview_write_checkup_8p);
		iv.setImageDrawable(null);
		finish();
	}

	//バックキー
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){
			Intent intent_cancel = new Intent(getApplicationContext(),WriteCheckup_8.class);
			startActivity(intent_cancel);
			ImageView iv = (ImageView)findViewById(R.id.imageview_write_checkup_8p);
			iv.setImageDrawable(null);
			finish();
			return true;
		}
		return false;
	}
}
