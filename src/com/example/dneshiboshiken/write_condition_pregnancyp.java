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



public class write_condition_pregnancyp extends Activity{

	//目次の項目だけボタンを定義

	private EditText[] editText_day1 = new EditText[editText_day.length];
	private Button[] dayin = new Button[editText_day.length];
	private DatePickerDialog.OnDateSetListener varDateSetListener_day1 = null;
	private static int daytext = 0;

	private Button button_Write_cancel;
	private Button button_Write_save;
	private Button button_Write_gallery;
	private Button button_Write_camera;
  //ここはクラス内で実行
	static int REQUEST_ACTION_PICK = 1;
	static final int REQUEST_CAPTURE_IMAGE = 100;
	private static int REQUEST_CAMERA = 1;
	private static Uri mImageUri; // インスタンス変数

	private Spinner[] tvParam_Spinner = new Spinner[item_checkup_Spinner_10.length];
	private String[] spinner_text = new String[item_checkup_Spinner_10.length];
	final boolean[] checkedItems = new boolean[10];

	private String[] item_checkup_EditText_10tag = {
			"EditText_write_condition_pregnancy1",
			"EditText_write_condition_pregnancy4",
			"EditText_write_condition_pregnancy5",
			"EditText_write_condition_pregnancy6",
			"EditText_write_condition_pregnancy7",
			"EditText_write_condition_pregnancy8",
			"EditText_write_condition_pregnancy9",
			"EditText_write_condition_pregnancy10",
			"EditText_write_condition_pregnancy11"};
	private String[] item_checkup_Spinner_10tag = {
			"Spinner_write_condition_pregnancy2",
			"Spinner_write_condition_pregnancy11"
			};
	private String[] item_checkup_CheckBox_10tag = {
			
			};
	public static int[] item_checkup_EditText_10 = {
		R.id.EditText_write_condition_pregnancy1,
		R.id.EditText_write_condition_pregnancy4,
		R.id.EditText_write_condition_pregnancy5,
		R.id.EditText_write_condition_pregnancy6,
		R.id.EditText_write_condition_pregnancy7,
		R.id.EditText_write_condition_pregnancy8,
		R.id.EditText_write_condition_pregnancy9,
		R.id.EditText_write_condition_pregnancy10,
		R.id.EditText_write_condition_pregnancy11,
};
	public static int[] item_checkup_Spinner_10 = {
		R.id.Spinner_write_condition_pregnancy2,
		R.id.Spinner_write_condition_pregnancy11,};
	public static int[] item_checkup_label_10 = {
		R.array.feeding_label,
		R.array.crowded_label,
		};

	public static int[] editText_day = {
		R.id.EditText_pregnancy_home_situation_before_start,
		R.id.EditText_pregnancy_home_situation_after_start,
		R.id.EditText_pregnancy_home_situation_leave_start1,
		R.id.EditText_pregnancy_home_situation_leave_finish1,
		R.id.EditText_pregnancy_home_situation_leave_start2,
		R.id.EditText_pregnancy_home_situation_leave_finish2,
	};
	public static int[] Button_day = {
		R.id.Button_pregnancy_home_situation_before_start,
		R.id.Button_pregnancy_home_situation_after_start,
		R.id.Button_pregnancy_home_situation_leave_start1,
		R.id.Button_pregnancy_home_situation_leave_finish1,
		R.id.Button_pregnancy_home_situation_leave_start2,
		R.id.Button_pregnancy_home_situation_leave_finish2,
	};

	public void onStart(){
		super.onStart();


        EditText[] tvParam_EditText = new EditText[item_checkup_EditText_10.length];

        //初期値読み込み
        File dir = new File(Environment.getExternalStorageDirectory().getPath());
        if(dir.exists()){

            File file = new File(dir.getAbsolutePath()+"/Yukari/Write/Pregnancy/pregnancy_home_situationp.xml");
            if (file.exists()) {
            	try {
            		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            		factory.setNamespaceAware(true);
            		XmlPullParser parser = factory.newPullParser();
            		FileInputStream fis = new FileInputStream(file);
            		parser.setInput(new InputStreamReader(fis));

            		// EditTextインスタンスの取得
            		for (int i1 = 0; i1 < item_checkup_EditText_10.length; i1++) {
            			tvParam_EditText[i1] = (EditText)findViewById(item_checkup_EditText_10[i1]);
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
                          	  if(tag.equals(item_checkup_EditText_10tag[0])) {tvParam_EditText[0].setText(value);}
                          	else if(tag.equals(item_checkup_EditText_10tag[1])) {tvParam_EditText[1].setText(value);}
                          	else if(tag.equals(item_checkup_EditText_10tag[2])) {tvParam_EditText[2].setText(value);}
                          	else if(tag.equals(item_checkup_EditText_10tag[3])) {tvParam_EditText[3].setText(value);}
                          	else if(tag.equals(item_checkup_EditText_10tag[4])) {tvParam_EditText[4].setText(value);}
                          	else if(tag.equals(item_checkup_EditText_10tag[5])) {tvParam_EditText[5].setText(value);}
                          	else if(tag.equals(item_checkup_EditText_10tag[6])) {tvParam_EditText[6].setText(value);}
                          	else if(tag.equals(item_checkup_EditText_10tag[7])) {tvParam_EditText[7].setText(value);}
                          	else if(tag.equals(item_checkup_EditText_10tag[8])) {tvParam_EditText[8].setText(value);}
                          	else if(tag.equals(item_checkup_EditText_10tag[9])) {tvParam_EditText[9].setText(value);}
                          	else if(tag.equals(item_checkup_EditText_10tag[10])) {tvParam_EditText[10].setText(value);}
                          	else if(tag.equals(item_checkup_EditText_10tag[11])) {tvParam_EditText[11].setText(value);}
                          	else if(tag.equals(item_checkup_EditText_10tag[12])) {tvParam_EditText[12].setText(value);}
                          	else if(tag.equals(item_checkup_EditText_10tag[13])) {tvParam_EditText[13].setText(value);}
                          	else if(tag.equals(item_checkup_EditText_10tag[14])) {tvParam_EditText[14].setText(value);}
                          	else if(tag.equals(item_checkup_EditText_10tag[15])) {tvParam_EditText[15].setText(value);}
                          	else if(tag.equals(item_checkup_EditText_10tag[16])) {tvParam_EditText[16].setText(value);}
                          	else if(tag.equals(item_checkup_EditText_10tag[17])) {tvParam_EditText[17].setText(value);}
                          	else if(tag.equals(item_checkup_EditText_10tag[18])) {tvParam_EditText[18].setText(value);}
                          	else if(tag.equals(item_checkup_EditText_10tag[19])) {tvParam_EditText[19].setText(value);}
                          	else if(tag.equals(item_checkup_EditText_10tag[20])) {tvParam_EditText[20].setText(value);}
                          	else if(tag.equals(item_checkup_EditText_10tag[21])) {tvParam_EditText[21].setText(value);}
                          	else if(tag.equals(item_checkup_EditText_10tag[22])) {tvParam_EditText[22].setText(value);}
                          	else if(tag.equals(item_checkup_EditText_10tag[23])) {tvParam_EditText[23].setText(value);}
                          	else if(tag.equals(item_checkup_EditText_10tag[24])) {tvParam_EditText[24].setText(value);}
                          	else if(tag.equals(item_checkup_EditText_10tag[25])) {tvParam_EditText[25].setText(value);}
                          	else if(tag.equals(item_checkup_Spinner_10tag[0])) {spinner_text[0] = parser.getText();}
                          	else if(tag.equals(item_checkup_Spinner_10tag[1])) {spinner_text[1] = parser.getText();}
                          	else if(tag.equals(item_checkup_Spinner_10tag[2])) {spinner_text[2] = parser.getText();}
                          	else if(tag.equals(item_checkup_Spinner_10tag[3])) {spinner_text[3] = parser.getText();}
                          	else if(tag.equals(item_checkup_Spinner_10tag[4])) {spinner_text[4] = parser.getText();}
                          	else if(tag.equals(item_checkup_CheckBox_10tag[0])) {checkedItems[0] = Boolean.valueOf( value );}
                          	else if(tag.equals(item_checkup_CheckBox_10tag[1])) {checkedItems[1] = Boolean.valueOf( value );}
                          	else if(tag.equals(item_checkup_CheckBox_10tag[2])) {checkedItems[2] = Boolean.valueOf( value );}
                          	else if(tag.equals(item_checkup_CheckBox_10tag[3])) {checkedItems[3] = Boolean.valueOf( value );}
                          	else if(tag.equals(item_checkup_CheckBox_10tag[4])) {checkedItems[4] = Boolean.valueOf( value );}}
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
        //画像なし


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

        //スピナーへの入力
        execSpinners();
	}
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.pregnancy_home_situationp);	//画面レイアウトを指定(res/layout/index_read.xml)

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
		String[][] label = new String[item_checkup_Spinner_10.length][];
		// Spinnerインスタンスの取得
		for (int i1 = 0; i1 < item_checkup_Spinner_10.length; i1++) {
			tvParam_Spinner[i1] = (Spinner)findViewById(item_checkup_Spinner_10[i1]);
			label[i1] = getResources().getStringArray(item_checkup_label_10[i1]);

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


	//チェックボックスダイアログ
	public void alertDialogShow_immunization(View v) {

		final TextView varTextView;
		varTextView = (TextView) findViewById(R.id.EditText_pregnancy_home_situation_with);

		//必要であればクラス内で宣言
		final String[] items;
		items = getResources().getStringArray(R.array.pregnancy_write_home);
/*
		for(int i1 = 0; i1 < items.length; i1++) {
			checkedItems[i1]=false;
		}
*/
		AlertDialog.Builder alertDialogBuilder =
				new AlertDialog.Builder(this);
		alertDialogBuilder
				.setTitle("一緒に住んでいる人にチェック")
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
    	Intent intent_cancel = new Intent(getApplicationContext(),write_condition_pregnancyp.class);
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
    	     for (int i1 = 0; i1 < item_checkup_EditText_10.length; i1++) {
    	    	 Element element1 = document.createElement(item_checkup_EditText_10tag[i1]);
    	    	 String string1 = ((EditText) findViewById(item_checkup_EditText_10[i1])).getText().toString();
    	    	 text = document.createTextNode(string1);
    	    	 element1.appendChild(text);
    	    	 root.appendChild(element1);
    	     }

    	     //spinnerの中身を保存
    	     for (int i1 = 0; i1 < item_checkup_Spinner_10.length; i1++) {
    	   		 spinner = (Spinner) findViewById(item_checkup_Spinner_10[i1]);
    	    	 Element element1 = document.createElement(item_checkup_Spinner_10tag[i1]);
    	    	 String string1 = spinner.getSelectedItem().toString();
    	    	 text = document.createTextNode(string1);
    	    	 element1.appendChild(text);
    	    	 root.appendChild(element1);
    	     }

    	   //CheckBoxの中身を保存
    	     for (int i1 = 0; i1 < item_checkup_CheckBox_10tag.length; i1++) {
    	    	 Element element1 = document.createElement(item_checkup_CheckBox_10tag[i1]);
    	    	 String string1 =  String.valueOf( checkedItems[i1] );//booleanとstring変換
    	    	 text = document.createTextNode(string1);
    	    	 element1.appendChild(text);
    	    	 root.appendChild(element1);
    	     }

    	     document.appendChild(root);

    	     TransformerFactory tffactory = TransformerFactory.newInstance();
    	     Transformer transformer = tffactory.newTransformer();

    	//保存ファイルの作成
    	String filePath = Environment.getExternalStorageDirectory() + "/Yukari/Write/Pregnancy/pregnancy_home_situationp.xml";
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
    	Intent intent_cancel = new Intent(getApplicationContext(),pregnancy_home_situation.class);
        startActivity(intent_cancel);

        finish();
    }

  //バックキー
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
      if(keyCode==KeyEvent.KEYCODE_BACK){
    	  Intent intent_cancel = new Intent(getApplicationContext(),pregnancy_home_situation.class);
          startActivity(intent_cancel);

          finish();
          return true;
      }
      return false;
    }
}
