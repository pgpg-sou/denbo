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

public class pregnancy_inspection_recordp extends Activity {

	//目次の項目だけボタンを定義

	private String filepath = "/Yukari/Write/Pregnancy/pregnancy_inspection_recordfile.xml";
	private EditText[] editText_day1 = new EditText[editText_day.length];
	private Button[] dayin = new Button[editText_day.length];
	private DatePickerDialog.OnDateSetListener varDateSetListener_day1 = null;

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

	private static int daytext = 0;
	final boolean[] checkedItems = new boolean[10];

	private String[] item_checkup_EditText_8tag = {"EditText_pregnancy_inspection_name1",
			"EditText_pregnancy_inspection_name2",
			"EditText_pregnancy_inspection_name3",
			"EditText_pregnancy_inspection_name4",
			"EditText_pregnancy_inspection_name5",
			"EditText_pregnancy_inspection_name6",
			"EditText_pregnancy_inspection_name7",
			"EditText_pregnancy_inspection_name8",
			"EditText_pregnancy_inspection_bloodtype_examination",
			"EditText_pregnancy_inspection_irregular_antibody_examination",
			"EditText_pregnancy_inspection_cervical_cancer_examination",
			"EditText_pregnancy_inspection_syphilis_examination",
			"EditText_pregnancy_inspection_hbs_examination",
			"EditText_pregnancy_inspection_hcv_examination",
			"EditText_pregnancy_inspection_hiv_examination",
			"EditText_pregnancy_inspection_rubella_examination",
			"EditText_pregnancy_inspection_htlv1_examination",
			"EditText_pregnancy_inspection_chlamydia_examination",
			"EditText_pregnancy_inspection_groupb_examination",
			"EditText_pregnancy_inspection_name1_examination",
			"EditText_pregnancy_inspection_name2_examination",
			"EditText_pregnancy_inspection_name3_examination",
			"EditText_pregnancy_inspection_name4_examination",
			"EditText_pregnancy_inspection_name5_examination",
			"EditText_pregnancy_inspection_name6_examination",
			"EditText_pregnancy_inspection_name7_examination",
			"EditText_pregnancy_inspection_name8_examination",
			"EditText_pregnancy_inspection_bloodtype_other",
			"EditText_pregnancy_inspection_irregular_antibody_other",
			"EditText_pregnancy_inspection_cervical_cancer_other",
			"EditText_pregnancy_inspection_syphilis_other",
			"EditText_pregnancy_inspection_hbs_other",
			"EditText_pregnancy_inspection_hcv_other",
			"EditText_pregnancy_inspection_hiv_other",
			"EditText_pregnancy_inspection_rubella_other",
			"EditText_pregnancy_inspection_htlv1_other",
			"EditText_pregnancy_inspection_chlamydia_other",
			"EditText_pregnancy_inspection_groupb_other",
			"EditText_pregnancy_inspection_name1_other",
			"EditText_pregnancy_inspection_name2_other",
			"EditText_pregnancy_inspection_name3_other",
			"EditText_pregnancy_inspection_name4_other",
			"EditText_pregnancy_inspection_name5_other",
			"EditText_pregnancy_inspection_name6_other",
			"EditText_pregnancy_inspection_name7_other",
			"EditText_pregnancy_inspection_name8_other"
	};
	public static int[] item_checkup_EditText_8 = {R.id.EditText_pregnancy_inspection_name1,
		R.id.EditText_pregnancy_inspection_name2,
		R.id.EditText_pregnancy_inspection_name3,
		R.id.EditText_pregnancy_inspection_name4,
		R.id.EditText_pregnancy_inspection_name5,
		R.id.EditText_pregnancy_inspection_name6,
		R.id.EditText_pregnancy_inspection_name7,
		R.id.EditText_pregnancy_inspection_name8,
		R.id.EditText_pregnancy_inspection_bloodtype_examination,
		R.id.EditText_pregnancy_inspection_irregular_antibody_examination,
		R.id.EditText_pregnancy_inspection_cervical_cancer_examination,
		R.id.EditText_pregnancy_inspection_syphilis_examination,
		R.id.EditText_pregnancy_inspection_hbs_examination,
		R.id.EditText_pregnancy_inspection_hcv_examination,
		R.id.EditText_pregnancy_inspection_hiv_examination,
		R.id.EditText_pregnancy_inspection_rubella_examination,
		R.id.EditText_pregnancy_inspection_htlv1_examination,
		R.id.EditText_pregnancy_inspection_chlamydia_examination,
		R.id.EditText_pregnancy_inspection_groupb_examination,
		R.id.EditText_pregnancy_inspection_name1_examination,
		R.id.EditText_pregnancy_inspection_name2_examination,
		R.id.EditText_pregnancy_inspection_name3_examination,
		R.id.EditText_pregnancy_inspection_name4_examination,
		R.id.EditText_pregnancy_inspection_name5_examination,
		R.id.EditText_pregnancy_inspection_name6_examination,
		R.id.EditText_pregnancy_inspection_name7_examination,
		R.id.EditText_pregnancy_inspection_name8_examination,
		R.id.EditText_pregnancy_inspection_bloodtype_other,
		R.id.EditText_pregnancy_inspection_irregular_antibody_other,
		R.id.EditText_pregnancy_inspection_cervical_cancer_other,
		R.id.EditText_pregnancy_inspection_syphilis_other,
		R.id.EditText_pregnancy_inspection_hbs_other,
		R.id.EditText_pregnancy_inspection_hcv_other,
		R.id.EditText_pregnancy_inspection_hiv_other,
		R.id.EditText_pregnancy_inspection_rubella_other,
		R.id.EditText_pregnancy_inspection_htlv1_other,
		R.id.EditText_pregnancy_inspection_chlamydia_other,
		R.id.EditText_pregnancy_inspection_groupb_other,
		R.id.EditText_pregnancy_inspection_name1_other,
		R.id.EditText_pregnancy_inspection_name2_other,
		R.id.EditText_pregnancy_inspection_name3_other,
		R.id.EditText_pregnancy_inspection_name4_other,
		R.id.EditText_pregnancy_inspection_name5_other,
		R.id.EditText_pregnancy_inspection_name6_other,
		R.id.EditText_pregnancy_inspection_name7_other,
		R.id.EditText_pregnancy_inspection_name8_other,};

	public static int[] Button_day = {R.id.Button_pregnancy_inspection_bloodtype_examination,
		R.id.Button_pregnancy_inspection_irregular_antibody_examination,
		R.id.Button_pregnancy_inspection_cervical_cancer_examination,
		R.id.Button_pregnancy_inspection_syphilis_examination,
		R.id.Button_pregnancy_inspection_hbs_examination,
		R.id.Button_pregnancy_inspection_hcv_examination,
		R.id.Button_pregnancy_inspection_hiv_examination,
		R.id.Button_pregnancy_inspection_rubella_examination,
		R.id.Button_pregnancy_inspection_htlv1_examination,
		R.id.Button_pregnancy_inspection_chlamydia_examination,
		R.id.Button_pregnancy_inspection_groupb_examination,
		R.id.Button_pregnancy_inspection_name1_examination,
		R.id.Button_pregnancy_inspection_name2_examination,
		R.id.Button_pregnancy_inspection_name3_examination,
		R.id.Button_pregnancy_inspection_name4_examination,
		R.id.Button_pregnancy_inspection_name5_examination,
		R.id.Button_pregnancy_inspection_name6_examination,
		R.id.Button_pregnancy_inspection_name7_examination,
		R.id.Button_pregnancy_inspection_name8_examination,
	};

	public static int[] editText_day = {
		R.id.EditText_pregnancy_inspection_bloodtype_examination,
		R.id.EditText_pregnancy_inspection_irregular_antibody_examination,
		R.id.EditText_pregnancy_inspection_cervical_cancer_examination,
		R.id.EditText_pregnancy_inspection_syphilis_examination,
		R.id.EditText_pregnancy_inspection_hbs_examination,
		R.id.EditText_pregnancy_inspection_hcv_examination,
		R.id.EditText_pregnancy_inspection_hiv_examination,
		R.id.EditText_pregnancy_inspection_rubella_examination,
		R.id.EditText_pregnancy_inspection_htlv1_examination,
		R.id.EditText_pregnancy_inspection_chlamydia_examination,
		R.id.EditText_pregnancy_inspection_groupb_examination,
		R.id.EditText_pregnancy_inspection_name1_examination,
		R.id.EditText_pregnancy_inspection_name2_examination,
		R.id.EditText_pregnancy_inspection_name3_examination,
		R.id.EditText_pregnancy_inspection_name4_examination,
		R.id.EditText_pregnancy_inspection_name5_examination,
		R.id.EditText_pregnancy_inspection_name6_examination,
		R.id.EditText_pregnancy_inspection_name7_examination,
		R.id.EditText_pregnancy_inspection_name8_examination,
	};
	/** Called when the activity is first created. */

	public void onStart(){
		super.onStart();
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
								else if(tag.equals(item_checkup_EditText_8tag[22])) {tvParam_EditText[22].setText(value);}}
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

		for (int i1 = 0; i1 < Button_day.length; i1++) {
			final int n = i1 ;
			dayin[i1].setOnClickListener(new View.OnClickListener(){
				public void onClick(View v) {
					button_day(n);
				}
			});
		}


	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pregnancy_inspection_recordp);	//画面レイアウトを指定(res/layout/index_read.xml)

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


	private void button_Write_cancel() {
		Intent intent_cancel = new Intent(getApplicationContext(),pregnancy_inspection_record.class);
		startActivity(intent_cancel);
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
		Intent intent_cancel = new Intent(getApplicationContext(),pregnancy_inspection_record.class);
		startActivity(intent_cancel);

		finish();
	}

	//バックキー
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){
			Intent intent_cancel = new Intent(getApplicationContext(),pregnancy_inspection_record.class);
			startActivity(intent_cancel);
			finish();
			return true;
		}
		return false;
	}
}