//教育機能の目次
package com.example.dneshiboshiken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
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
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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
import android.widget.Toast;

public class pregnancy_birth_registrationp extends Activity {

	//目次の項目だけボタンを定義

	private String filepath = "/Yukari/Write/Pregnancy/pregnancy_birth_registrationfile.xml";
	private EditText[] editText_day1 = new EditText[editText_day.length];
	private Button[] dayin = new Button[editText_day.length];
	private DatePickerDialog.OnDateSetListener varDateSetListener_day1 = null;

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

	private static int daytext = 0;
	final boolean[] checkedItems = new boolean[10];

	private String[] item_checkup_EditText_8tag = {"EditText_birth_registration_name",
			"EditText_birth_registration_place",
			"EditText_birth_registration_birthday"
	};
	public static int[] item_checkup_EditText_8 = {R.id.EditText_birth_registration_name,
		R.id.EditText_birth_registration_place,
		R.id.EditText_birth_registration_birthday,};

	public static int[] Button_day = {R.id.Button_birth_registration_birthday,
	};

	public static int[] editText_day = {
		R.id.EditText_birth_registration_birthday,
	};
	private String[] item_checkup_Spinner_8tag = {
			"Spinner_birth_registration_sex",
	};
	public static int[] item_checkup_Spinner_8 = {R.id.Spinner_birth_registration_sex,
	};
	public static int[] item_checkup_label_8 = {
		R.array.sex_label,
	};
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pregnancy_birth_registrationp);	//画面レイアウトを指定(res/layout/index_read.xml)

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
								else if(tag.equals(item_checkup_EditText_8tag[2])) {tvParam_EditText[2].setText(value);}}
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

		if(dir.exists()){
			File view = new File(dir.getAbsolutePath()+"/Yukari/Photo/pregnancy_birth_registrationp.png");
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

				((ImageView)findViewById(R.id.imageview_pregnancy_birth_registrationp)).setImageBitmap(image);
			}else{
				//存在しない
			}
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

		button_Write_camera = (Button) findViewById(R.id.Button_write_checkup_7p_camera);
		button_Write_camera.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				button_Write_camera();
			}
		});

		button_Write_gallery = (Button) findViewById(R.id.Button_write_checkup_7p_gallery);
		button_Write_gallery.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				button_Write_gallery();
			}
		});

		for (int i1 = 0; i1 < Button_day.length; i1++) {
			final int n = i1 ;
			dayin[i1].setOnClickListener(new View.OnClickListener(){
				public void onClick(View v) {
					button_day(n);
				}
			});
		}
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
					((ImageView)findViewById(R.id.imageview_pregnancy_birth_registrationp)).setImageBitmap(imageBitmap);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				String filename = "pregnancy_birth_registrationp.png";
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
		finish();
	}

	private void button_Write_save() {
		Spinner spinner;
		Text text;

		//ディレクトリの作成
		String target_path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Yukari/Write/Pregnancy";
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
		Intent intent_cancel = new Intent(getApplicationContext(),pregnancy_birth_registration.class);
		startActivity(intent_cancel);

		finish();
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

	//バックキー
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){
			Intent intent_cancel = new Intent(getApplicationContext(),pregnancy_birth_registration.class);
			startActivity(intent_cancel);
			finish();
			return true;
		}
		return false;
	}
}