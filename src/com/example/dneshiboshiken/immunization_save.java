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
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


public class immunization_save extends Activity {

	private EditText editText_day1;
	private DatePickerDialog.OnDateSetListener varDateSetListener_day1;
	private Button button_Write_cancel;
	private Button button_Write_save;

	private String immunization_place = immunization_main.immunization_flag;

	private String filepath = "/Yukari/Write/immunization";
	private String filename = "/Yukari/Write/immunization/"+immunization_place+immunization_main.immunization_number+"file.xml";
	private String datapath = "/Yukari/Write/immunization/"+immunization_place+"1file.xml";

	private String[] item_immunization_write_tag = {"EditText_immunization_write_ymd",
			"EditText_immunization_write_lot",
			"EditText_immunization_write_name",
			"EditText_immunization_write_other",};

	public static int[] item_immunization_write = {R.id.EditText_immunization_write_ymd,
		R.id.EditText_immunization_write_lot,
		R.id.EditText_immunization_write_name,
		R.id.EditText_immunization_write_other,};

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.immunization_save);

		EditText[] tvParam_EditText = new EditText[item_immunization_write.length];

		//初期値読み込み
		File dir = new File(Environment.getExternalStorageDirectory().getPath());
		if(dir.exists()){

			File file = new File(dir.getAbsolutePath()+filename);
			if (file.exists()) {
				try {
					XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
					factory.setNamespaceAware(true);
					XmlPullParser parser = factory.newPullParser();
					FileInputStream fis = new FileInputStream(file);
					parser.setInput(new InputStreamReader(fis));

					// EditTextインスタンスの取得
					for (int i1 = 0; i1 < item_immunization_write.length; i1++) {
						tvParam_EditText[i1] = (EditText)findViewById(item_immunization_write[i1]);
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
								if(tag.equals(item_immunization_write_tag[0])) {tvParam_EditText[0].setText(value);}
								else if(tag.equals(item_immunization_write_tag[1])) {tvParam_EditText[1].setText(value);}
								else if(tag.equals(item_immunization_write_tag[2])) {tvParam_EditText[2].setText(value);}
								else if(tag.equals(item_immunization_write_tag[3])) {tvParam_EditText[3].setText(value);}}
							break;
						case XmlPullParser.END_TAG: // 終了タグ
							break;
						}

					}
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "エラー発生", Toast.LENGTH_SHORT);
				}
			}

		//日付出力
		editText_day1 = (EditText) findViewById(R.id.EditText_immunization_write_ymd);
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
		}
	}
	private void button_Write_cancel() {

		finish();
	}

	private void button_Write_save() {
		Spinner spinner;
		Text text;

		//ディレクトリの作成
		String target_path = Environment.getExternalStorageDirectory().getAbsolutePath() + filepath;
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
			for (int i1 = 0; i1 < item_immunization_write_tag.length; i1++) {
				Element element1 = document.createElement(item_immunization_write_tag[i1]);
				String string1 = ((EditText) findViewById(item_immunization_write[i1])).getText().toString();
				text = document.createTextNode(string1);
				element1.appendChild(text);
				root.appendChild(element1);
			}

			document.appendChild(root);

			TransformerFactory tffactory = TransformerFactory.newInstance();
			Transformer transformer = tffactory.newTransformer();

			//保存ファイルの作成
			//編集がかかった時は条件式でflag_numberで上書きする
			//flag_number=1の時はこのままで

				datapath= filepath +"/" + immunization_place+(immunization_main.immunization_number)+"file.xml";

			String filePath = Environment.getExternalStorageDirectory() + datapath;
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

}
