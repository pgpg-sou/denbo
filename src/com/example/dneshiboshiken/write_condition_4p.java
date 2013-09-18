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

import com.example.dneshiboshiken.R.string;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.util.Log;
import android.util.Xml;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Environment;


public class write_condition_4p extends Activity{
	private EditText editText_day1;
	private DatePickerDialog.OnDateSetListener varDateSetListener_day1;
	private EditText editText_day2;
	private DatePickerDialog.OnDateSetListener varDateSetListener_day2;
	private String filename = "imageView_write_condition4.png";
	private static int REQUEST_CAMERA = 1;
	private static Uri mImageUri; // インスタンス変数
	private int imagepath = R.id.imageview_write_condition_day_record;
	private Button button_Write_cancel;
	private Button button_Write_save;
	private Button button_Write_gallery;
	private Button button_Write_camera;

	private String filepath = "/Yukari/Write/condition/write_condition4.xml";
	private String datapath = "/Yukari/Write/condition";
	//ここはクラス内で実行
	static int REQUEST_ACTION_PICK = 1;
	static final int REQUEST_CAPTURE_IMAGE = 100;
	private static final String APPLICATION_NAME = "PATOM";
	private static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/Yukari/Photo/" + APPLICATION_NAME;

	private Spinner[] tvParam_Spinner = new Spinner[item_pregnancy_healthp_Spinner.length];
	private String[] spinner_text = new String[item_pregnancy_healthp_Spinner.length];
	private String[] radio_text = new String[item_pregnancy_healthp_RadioGroup.length];
	final boolean[] checkedItems = new boolean[10];

	private String[] EditText_pregnancy_write_healthp_tag = {
			"EditText_write_condition_day_record",
			"EditText_write_condition_message",
			"EditText_write_condition1",
			"EditText_write_condition7",
			"EditText_write_condition_other"};

	private String[] Spinner_pregnancy_write_healthp_tag = {

	};
	private String[] RadioGroup_pregnancy_write_healthp_tag = {
			"radio_write_condition2",
			"radio_write_condition3",
			"radio_write_condition4",
			"radio_write_condition5",
			"radio_write_condition6",
			"radio_write_condition8",
			"radio_write_condition9",
			"radio_write_condition10",
			"radio_write_condition11",
	};

	private String[] item_checkup_CheckBox_8tag = {
	};
	public static int[] item_pregnancy_healthp_EditText = {
		R.id.EditText_write_condition_day_record,
		R.id.EditText_write_condition_message,
		R.id.EditText_write_condition1,
		R.id.EditText_write_condition7,
		R.id.EditText_write_condition_other,};
	public static int[] item_pregnancy_healthp_Spinner = {};
	public static int[] item_pregnancy_healthp_RadioGroup = {
		R.id.radio_write_condition2,
		R.id.radio_write_condition3,
		R.id.radio_write_condition4,
		R.id.radio_write_condition5,
		R.id.radio_write_condition6,
		R.id.radio_write_condition8,
		R.id.radio_write_condition9,
		R.id.radio_write_condition10,
		R.id.radio_write_condition11,
		};
	public static int[] item_pregnancy_healthp_RadioButton_no = {
		R.id.radio_write_condition2_no,
		R.id.radio_write_condition3_no,
		R.id.radio_write_condition4_no,
		R.id.radio_write_condition5_no,
		R.id.radio_write_condition6_no,
		R.id.radio_write_condition8_no,
		R.id.radio_write_condition9_no,
		R.id.radio_write_condition10_no,
		R.id.radio_write_condition11_no,};
	public static int[] item_pregnancy_healthp_RadioButton_yes = {
		R.id.radio_write_condition2_yes,
		R.id.radio_write_condition3_yes,
		R.id.radio_write_condition4_yes,
		R.id.radio_write_condition5_yes,
		R.id.radio_write_condition6_yes,
		R.id.radio_write_condition8_yes,
		R.id.radio_write_condition9_yes,
		R.id.radio_write_condition10_yes,
		R.id.radio_write_condition11_yes,};
	public static int[] item_pregnancy_healthp_RadioButton_none = {
		R.id.radio_write_condition11_none,};
	public static int[] item_pregnancy_healthp_label = {
	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.write_condition_4p);	//画面レイアウトを指定(res/layout/index_read.xml)

		EditText[] tvParam_EditText = new EditText[item_pregnancy_healthp_EditText.length];
		Log.d("aaa","ここに文章をfして下さい");

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
					Log.d("aaa","ここに文章をfして下さい");

					// EditTextインスタンスの取得
					for (int i1 = 0; i1 < item_pregnancy_healthp_EditText.length; i1++) {
						tvParam_EditText[i1] = (EditText)findViewById(item_pregnancy_healthp_EditText[i1]);
					}
					Log.d("aaa","ここに文章をfして下さい");

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
								if(tag.equals(EditText_pregnancy_write_healthp_tag[0])) {tvParam_EditText[0].setText(value);Log.d("aaa","1ここに文章をfして下さい");}
								else if(tag.equals(EditText_pregnancy_write_healthp_tag[1])) {tvParam_EditText[1].setText(value);Log.d("aaa","こ2こに文章をfして下さい");}
								else if(tag.equals(EditText_pregnancy_write_healthp_tag[2])) {tvParam_EditText[2].setText(value);Log.d("aaa","こ2こに文章をfして下さい");}
								else if(tag.equals(EditText_pregnancy_write_healthp_tag[3])) {tvParam_EditText[3].setText(value);Log.d("aaa","こ2こに文章をfして下さい");}
								else if(tag.equals(EditText_pregnancy_write_healthp_tag[4])) {tvParam_EditText[4].setText(value);Log.d("aaa","こ2こに文章をfして下さい");}
								else if(tag.equals(RadioGroup_pregnancy_write_healthp_tag[0])) {radio_text[0] = parser.getText();Log.d("aaa","ここ3に文章をfして下さい");}
								else if(tag.equals(RadioGroup_pregnancy_write_healthp_tag[1])) {radio_text[1] = parser.getText();Log.d("aaa","ここに4文章をfして下さい");}
								else if(tag.equals(RadioGroup_pregnancy_write_healthp_tag[2])) {radio_text[2] = parser.getText();Log.d("aaa","ここに文5章をfして下さい");}
								else if(tag.equals(RadioGroup_pregnancy_write_healthp_tag[3])) {radio_text[3] = parser.getText();Log.d("aaa","ここに文章6をfして下さい");}
								else if(tag.equals(RadioGroup_pregnancy_write_healthp_tag[4])) {radio_text[4] = parser.getText();Log.d("aaa","ここに文章を7fして下さい");}
								else if(tag.equals(RadioGroup_pregnancy_write_healthp_tag[5])) {radio_text[5] = parser.getText();Log.d("aaa","ここに文章をf8して下さい");}
								else if(tag.equals(RadioGroup_pregnancy_write_healthp_tag[6])) {radio_text[6] = parser.getText();Log.d("aaa","ここに文章をf8して下さい");}
								else if(tag.equals(RadioGroup_pregnancy_write_healthp_tag[7])) {radio_text[7] = parser.getText();Log.d("aaa","ここに文章をf8して下さい");}
								else if(tag.equals(RadioGroup_pregnancy_write_healthp_tag[8])) {radio_text[8] = parser.getText();Log.d("aaa","ここに文章をf8して下さい");}
							}
							break;
						case XmlPullParser.END_TAG: // 終了タグ
							break;
						}

					}
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "エラー発生", Toast.LENGTH_SHORT);
				}
			}
			//画像読み込み
            File view = new File(dir.getAbsolutePath()+"/Yukari/Photo/"+filename);
            if (view.exists()) {
            	BitmapFactory.Options options = new BitmapFactory.Options();
            	options.inJustDecodeBounds = true;
            	Bitmap bmp1 = BitmapFactory.decodeFile(view.getPath(), options );

            	int reqWidth = 0;
            	int reqHeight = 0;
            	options.inSampleSize = WriteCheckup_13.calculateInSampleSize(options, reqWidth, reqHeight);
            	int scale = Math.max(1, 1);
            	options.inJustDecodeBounds = false;
            	options.inSampleSize = scale;
            	Bitmap image = BitmapFactory.decodeFile(view.getPath(), options);

            	((ImageView)findViewById(imagepath)).setImageBitmap(image);
            }else{
                //存在しない
            }
            //日付読み込む

		}
		//日付読み込むめうーっ！(」*ﾟﾛﾟ)」*ﾟﾛﾟ)」

		Log.d("aada","ここに文章をfして下さい");
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
		button_Write_camera =
				(Button) findViewById(R.id.Button_write_checkup_9p_camera);
		button_Write_camera.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				button_Write_camera();
			}
		});

		button_Write_gallery = (Button) findViewById(R.id.Button_write_checkup_9p_gallery);
		button_Write_gallery.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				button_Write_gallery();
			}
		});
		//スピナーへの入力
		execSpinners();


		//ラジオボタン初期値
		execRadio();

		editText_day1 = (EditText) findViewById(R.id.EditText_write_condition1);
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
					((ImageView)findViewById(imagepath)).setImageBitmap(imageBitmap);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}


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

	//スピナー入出力関数

	private void execSpinners() {
		String[][] label = new String[item_pregnancy_healthp_Spinner.length][];
		// Spinnerインスタンスの取得
		for (int i1 = 0; i1 < item_pregnancy_healthp_Spinner.length; i1++) {
			tvParam_Spinner[i1] = (Spinner)findViewById(item_pregnancy_healthp_Spinner[i1]);
			label[i1] = getResources().getStringArray(item_pregnancy_healthp_label[i1]);

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
	//ラジオ初期値関数
	private void execRadio() {
		int none_check=0;
		for(int i2 = 0; i2 < item_pregnancy_healthp_RadioGroup.length; i2++) {//初期値設定
			RadioGroup group = (RadioGroup)findViewById(item_pregnancy_healthp_RadioGroup[i2]);
			Log.d("aada","ここに文章をfして下さい");
			if (radio_text[i2]==null){
				group.check(item_pregnancy_healthp_RadioButton_no[i2]);
			}else if (radio_text[i2].equals("はい")){
				group.check(item_pregnancy_healthp_RadioButton_yes[i2]);
			}else if (radio_text[i2].equals("いいえ")){
				group.check(item_pregnancy_healthp_RadioButton_no[i2]);
			}else if (radio_text[i2].equals("何とも言えない")){

				group.check(item_pregnancy_healthp_RadioButton_none[none_check]);
				none_check++;
			}else group.check(item_pregnancy_healthp_RadioButton_no[i2]);
		}


	}

	//チェックボックスダイアログ
	public void alertDialogShow_immunization(View v) {

		final TextView varTextView;
		varTextView = (TextView) findViewById(R.id.EditText_write_pregnancy_write_illnesses);

		//必要であればクラス内で宣言
		final String[] items;
		items = getResources().getStringArray(R.array.pregnancy_write_illnesses_label);
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


	private void button_Write_cancel() {
		ImageView iv = (ImageView)findViewById(imagepath);
        iv.setImageDrawable(null);
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
			for (int i1 = 0; i1 < item_pregnancy_healthp_EditText.length; i1++) {
				Element element1 = document.createElement(EditText_pregnancy_write_healthp_tag[i1]);
				String string1 = ((EditText) findViewById(item_pregnancy_healthp_EditText[i1])).getText().toString();
				text = document.createTextNode(string1);
				element1.appendChild(text);
				root.appendChild(element1);
			}

			//spinnerの中身を保存
			for (int i1 = 0; i1 < item_pregnancy_healthp_Spinner.length; i1++) {
				spinner = (Spinner) findViewById(item_pregnancy_healthp_Spinner[i1]);
				Element element1 = document.createElement(Spinner_pregnancy_write_healthp_tag[i1]);
				String string1 = spinner.getSelectedItem().toString();
				text = document.createTextNode(string1);
				element1.appendChild(text);
				root.appendChild(element1);
			}
Log.d("aaa","ここに文章を入力して下さい");
			//radiobuttonの中身を保存
			for (int i1 = 0; i1 < item_pregnancy_healthp_RadioGroup.length; i1++) {
				String string1;
				RadioButton radio = (RadioButton)findViewById(item_pregnancy_healthp_RadioButton_no[i1]);
				RadioButton radio2 = (RadioButton)findViewById(item_pregnancy_healthp_RadioButton_yes[i1]);
				if(radio.isChecked() == true) {
					string1 = "いいえ";
				}else if(radio2.isChecked() == true) {
					string1 = "はい";
				}else  string1 = "何とも言えない";
				Element element1 = document.createElement(RadioGroup_pregnancy_write_healthp_tag[i1]);
				text = document.createTextNode(string1);
				element1.appendChild(text);
				root.appendChild(element1);
			}
			Log.d("aaa","ここに文章を入力して下さい");

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
		Intent intent_cancel = new Intent(getApplicationContext(),write_condition_4.class);
		startActivity(intent_cancel);
		ImageView iv = (ImageView)findViewById(imagepath);
        iv.setImageDrawable(null);
		finish();
	}

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

	//バックキー
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){
			Intent intent_cancel = new Intent(getApplicationContext(),write_condition_4.class);
			startActivity(intent_cancel);
			ImageView iv = (ImageView)findViewById(imagepath);
	        iv.setImageDrawable(null);
			finish();
			return true;
		}
		return false;
	}
}

