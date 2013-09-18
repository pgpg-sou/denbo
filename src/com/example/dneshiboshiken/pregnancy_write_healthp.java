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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Environment;



public class pregnancy_write_healthp extends Activity{

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

	private Spinner[] tvParam_Spinner = new Spinner[item_pregnancy_healthp_Spinner.length];
	private String[] spinner_text = new String[item_pregnancy_healthp_Spinner.length];
	private String[] radio_text = new String[item_pregnancy_healthp_RadioButton.length];
	final boolean[] checkedItems = new boolean[10];

	private String[] EditText_pregnancy_write_healthp_tag = {"EditText_write_pregnancy_write_weight",
			"EditText_write_pregnancy_write_height",
			"EditText_write_pregnancy_write_marriage",
			"EditText_write_pregnancy_write_illnesses",
			"EditText_write_pregnancy_write_illnesses_other",
			"EditText_pregnancy_write_infectious_rubella_old",
			"EditText_pregnancy_write_infectious_measles_old",
			"EditText_pregnancy_write_infectious_pox_old",
			"EditText_pregnancy_write_operation_for",
			"EditText_pregnancy_write_medicine",
			"EditText_pregnancy_write_anxiety_other",
			"EditText_pregnancy_write_anxiety_other",
			"EditText_pregnancy_write_smoke_cigarettes",
			"EditText_pregnancy_write_smoke_same_cigarettes",
			"EditText_pregnancy_write_alcohol_glasses",
			"EditText_pregnancy_write_spouse",
			"EditText_pregnancy_write_spouse_poor",
			};
	private String[] Spinner_pregnancy_write_healthp_tag = {
			"Spinner_pregnancy_write_infectious_rubella",
			"Spinner_pregnancy_write_infectious_measles",
			"Spinner_pregnancy_write_infectious_pox",
			};
	private String[] RadioGroup_pregnancy_write_healthp_tag = {
			"radio_pregnancy_write_operation",
			"radio_pregnancy_write_stress",
			"radio_pregnancy_write_anxiety",
			"radio_pregnancy_write_smoke",
			"radio_pregnancy_write_smoke_same",
			"radio_pregnancy_write_alcohol",
			};
	private String[] RadioButton_pregnancy_write_healthp_tag = {
			"radio_pregnancy_write_operation_no",
			"radio_pregnancy_write_stress_no",
			"radio_pregnancy_write_anxiety_no",
			"radio_pregnancy_write_smoke_no",
			"radio_pregnancy_write_smoke_same_no",
			"radio_pregnancy_write_alcohol_no",
			};
	private String[] item_checkup_CheckBox_8tag = {
			};
	public static int[] item_pregnancy_healthp_EditText = {R.id.EditText_write_pregnancy_write_weight,
		R.id.EditText_write_pregnancy_write_height,
		R.id.EditText_write_pregnancy_write_marriage,
		R.id.EditText_write_pregnancy_write_illnesses,
		R.id.EditText_write_pregnancy_write_illnesses_other,
		R.id.EditText_pregnancy_write_infectious_rubella_old,
		R.id.EditText_pregnancy_write_infectious_measles_old,
		R.id.EditText_pregnancy_write_infectious_pox_old,
		R.id.EditText_pregnancy_write_operation_for,
		R.id.EditText_pregnancy_write_medicine,
		R.id.EditText_pregnancy_write_anxiety_other,
		R.id.EditText_pregnancy_write_anxiety_other,
		R.id.EditText_pregnancy_write_smoke_cigarettes,
		R.id.EditText_pregnancy_write_smoke_same_cigarettes,
		R.id.EditText_pregnancy_write_alcohol_glasses,
		R.id.EditText_pregnancy_write_spouse,
		R.id.EditText_pregnancy_write_spouse_poor,};
	public static int[] item_pregnancy_healthp_Spinner = {R.id.Spinner_pregnancy_write_infectious_rubella,
		R.id.Spinner_pregnancy_write_infectious_measles,
		R.id.Spinner_pregnancy_write_infectious_pox,};
	public static int[] item_pregnancy_healthp_RadioGroup = {R.id.radio_pregnancy_write_operation,
		R.id.radio_pregnancy_write_stress,
		R.id.radio_pregnancy_write_anxiety,
		R.id.radio_pregnancy_write_smoke,
		R.id.radio_pregnancy_write_smoke_same,
		R.id.radio_pregnancy_write_alcohol,};
	public static int[] item_pregnancy_healthp_RadioButton = {R.id.radio_pregnancy_write_operation_no,
		R.id.radio_pregnancy_write_stress_no,
		R.id.radio_pregnancy_write_anxiety_no,
		R.id.radio_pregnancy_write_smoke_no,
		R.id.radio_pregnancy_write_smoke_same_no,
		R.id.radio_pregnancy_write_alcohol_no,};
	public static int[] item_pregnancy_healthp_RadioButton_yes = {R.id.radio_pregnancy_write_operation_yes,
		R.id.radio_pregnancy_write_stress_yes,
		R.id.radio_pregnancy_write_anxiety_yes,
		R.id.radio_pregnancy_write_smoke_yes,
		R.id.radio_pregnancy_write_smoke_same_yes,
		R.id.radio_pregnancy_write_alcohol_yes,};
	public static int[] item_pregnancy_healthp_label = {
		R.array.infectious_label,
		R.array.infectious_label,
		R.array.infectious_label,
		};

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pregnancy_write_healthp);	//画面レイアウトを指定(res/layout/index_read.xml)

        EditText[] tvParam_EditText = new EditText[item_pregnancy_healthp_EditText.length];

        //初期値読み込み
        File dir = new File(Environment.getExternalStorageDirectory().getPath());
        if(dir.exists()){

            File file = new File(dir.getAbsolutePath()+"/Yukari/Write/Pregnancy/healthfile.xml");
            if (file.exists()) {
            	try {
            		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            		factory.setNamespaceAware(true);
            		XmlPullParser parser = factory.newPullParser();
            		FileInputStream fis = new FileInputStream(file);
            		parser.setInput(new InputStreamReader(fis));

            		// EditTextインスタンスの取得
            		for (int i1 = 0; i1 < item_pregnancy_healthp_EditText.length; i1++) {
            			tvParam_EditText[i1] = (EditText)findViewById(item_pregnancy_healthp_EditText[i1]);
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
                          	  if(tag.equals(EditText_pregnancy_write_healthp_tag[0])) {tvParam_EditText[0].setText(value);}
                          	else if(tag.equals(EditText_pregnancy_write_healthp_tag[1])) {tvParam_EditText[1].setText(value);}
                          	else if(tag.equals(EditText_pregnancy_write_healthp_tag[2])) {tvParam_EditText[2].setText(value);}
                          	else if(tag.equals(EditText_pregnancy_write_healthp_tag[3])) {tvParam_EditText[3].setText(value);}
                          	else if(tag.equals(EditText_pregnancy_write_healthp_tag[4])) {tvParam_EditText[4].setText(value);}
                          	else if(tag.equals(EditText_pregnancy_write_healthp_tag[5])) {tvParam_EditText[5].setText(value);}
                          	else if(tag.equals(EditText_pregnancy_write_healthp_tag[6])) {tvParam_EditText[6].setText(value);}
                          	else if(tag.equals(EditText_pregnancy_write_healthp_tag[7])) {tvParam_EditText[7].setText(value);}
                          	else if(tag.equals(EditText_pregnancy_write_healthp_tag[8])) {tvParam_EditText[8].setText(value);}
                          	else if(tag.equals(EditText_pregnancy_write_healthp_tag[9])) {tvParam_EditText[9].setText(value);}
                          	else if(tag.equals(EditText_pregnancy_write_healthp_tag[10])) {tvParam_EditText[10].setText(value);}
                          	else if(tag.equals(EditText_pregnancy_write_healthp_tag[11])) {tvParam_EditText[11].setText(value);}
                          	else if(tag.equals(EditText_pregnancy_write_healthp_tag[12])) {tvParam_EditText[12].setText(value);}
                          	else if(tag.equals(EditText_pregnancy_write_healthp_tag[13])) {tvParam_EditText[13].setText(value);}
                          	else if(tag.equals(EditText_pregnancy_write_healthp_tag[14])) {tvParam_EditText[14].setText(value);}
                          	else if(tag.equals(EditText_pregnancy_write_healthp_tag[15])) {tvParam_EditText[15].setText(value);}
                          	else if(tag.equals(EditText_pregnancy_write_healthp_tag[16])) {tvParam_EditText[16].setText(value);}
                          	else if(tag.equals(Spinner_pregnancy_write_healthp_tag[0])) {spinner_text[0] = parser.getText();}
                          	else if(tag.equals(Spinner_pregnancy_write_healthp_tag[1])) {spinner_text[1] = parser.getText();}
                          	else if(tag.equals(Spinner_pregnancy_write_healthp_tag[2])) {spinner_text[2] = parser.getText();}
                          	else if(tag.equals(RadioGroup_pregnancy_write_healthp_tag[0])) {radio_text[0] = parser.getText();}
                          	else if(tag.equals(RadioGroup_pregnancy_write_healthp_tag[1])) {radio_text[1] = parser.getText();}
                          	else if(tag.equals(RadioGroup_pregnancy_write_healthp_tag[2])) {radio_text[2] = parser.getText();}
                          	else if(tag.equals(RadioGroup_pregnancy_write_healthp_tag[3])) {radio_text[3] = parser.getText();}
                          	else if(tag.equals(RadioGroup_pregnancy_write_healthp_tag[4])) {radio_text[4] = parser.getText();}
                          	else if(tag.equals(RadioGroup_pregnancy_write_healthp_tag[5])) {radio_text[5] = parser.getText();}
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
        //スピナーへの入力
        execSpinners();
       //ラジオボタン初期値
        execRadio();

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
		for(int i2 = 0; i2 < item_pregnancy_healthp_RadioGroup.length; i2++) {//初期値設定
			RadioGroup group = (RadioGroup)findViewById(item_pregnancy_healthp_RadioGroup[i2]);
			if (radio_text[i2]==null){
				group.check(item_pregnancy_healthp_RadioButton[i2]);
			}else if (radio_text[i2].equals("はい")){
				group.check(item_pregnancy_healthp_RadioButton_yes[i2]);
			}else group.check(item_pregnancy_healthp_RadioButton[i2]);
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
    	Intent intent_cancel = new Intent(getApplicationContext(),pregnancy_write_health.class);
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

    	     //radiobuttonの中身を保存
    	     for (int i1 = 0; i1 < item_pregnancy_healthp_RadioGroup.length; i1++) {
    	    	 String string1;
    	    	 RadioButton radio = (RadioButton)findViewById(item_pregnancy_healthp_RadioButton[i1]);
    	    	 	  if(radio.isChecked() == true) {
    	    	 		  string1 = "いいえ";
    	    	 	  }else{
    	    	 		  string1 = "はい";
    	    	 	  }
    	    	 Element element1 = document.createElement(RadioGroup_pregnancy_write_healthp_tag[i1]);
    	    	 text = document.createTextNode(string1);
    	    	 element1.appendChild(text);
    	    	 root.appendChild(element1);
    	     }

    	     document.appendChild(root);

    	     TransformerFactory tffactory = TransformerFactory.newInstance();
    	     Transformer transformer = tffactory.newTransformer();

    	//保存ファイルの作成
    	String filePath = Environment.getExternalStorageDirectory() + "/Yukari/Write/Pregnancy/healthfile.xml";
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
    	Intent intent_cancel = new Intent(getApplicationContext(),pregnancy_write_health.class);
        startActivity(intent_cancel);
        finish();
    }

  //バックキー
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
      if(keyCode==KeyEvent.KEYCODE_BACK){
    	  Intent intent_cancel = new Intent(getApplicationContext(),pregnancy_write_health.class);
          startActivity(intent_cancel);
          finish();
          return true;
      }
      return false;
    }
}
