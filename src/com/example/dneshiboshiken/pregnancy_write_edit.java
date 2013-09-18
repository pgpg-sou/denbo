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
import android.app.ProgressDialog;
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
import android.os.AsyncTask;
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



public class pregnancy_write_edit extends Activity{

	//目次の項目だけボタンを定義
	private String datapath = "/Yukari/Write/Pregnancy/Course/Pregnancyfile1.xml";
	private int photopath;

	private EditText editText_day1;
	private DatePickerDialog.OnDateSetListener varDateSetListener_day1;

	private Button button_Write_cancel;
	private Button button_Write_save;
	private Button button_Write_gallery;
	private Button button_Write_camera;
  //ここはクラス内で実行
	static int REQUEST_ACTION_PICK = 1;
	static final int REQUEST_CAPTURE_IMAGE = 100;
	private static final String APPLICATION_NAME = "PATOM";
	private static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/Yukari/Photo/" + APPLICATION_NAME;

	private Spinner[] tvParam_Spinner = new Spinner[item_checkup_Spinner_9.length];
	private String[] spinner_text = new String[item_checkup_Spinner_9.length];
	final boolean[] checkedItems = new boolean[10];

	private static int REQUEST_CAMERA = 1;
	private static Uri mImageUri; // インスタンス変数
    private static String xmlFileName="Pregnancyfile1.xml";
    private static String imageFileName;


    private SharedPreferences pref;// Drive関係
    private Activity activity;// Drive関係

	private String[] item_pregnancy_writetag = {"pregnancy_write_edit_examination",
			"pregnancy_write_edit_uterus",
			"pregnancy_write_edit_abdomen",
			"pregnancy_write_edit_weight",
			"pregnancy_write_edit_blood_top",
			"pregnancy_write_edit_blood_bottom",
			"pregnancy_write_edit_other",
			"pregnancy_write_edit_instructions",
			"pregnancy_write_edit_name",};
    private String[] item_pregnancy_Spinner_9tag = {
			"pregnancy_write_edit_edema",
			"pregnancy_write_edit_protein",
			"pregnancy_write_edit_sugar",
			};
	private String[] item_checkup_CheckBox_9tag = {
			};
	public static int[] item_checkup_EditText_9 = {R.id.pregnancy_write_edit_examination,
		R.id.pregnancy_write_edit_uterus,
		R.id.pregnancy_write_edit_abdomen,
		R.id.pregnancy_write_edit_weight,
		R.id.pregnancy_write_edit_blood_top,
		R.id.pregnancy_write_edit_blood_bottom,
		R.id.pregnancy_write_edit_other,
		R.id.pregnancy_write_edit_instructions,
		R.id.pregnancy_write_edit_name,};
	public static int[] item_checkup_Spinner_9 = {R.id.pregnancy_write_edit_edema,
		R.id.pregnancy_write_edit_protein,
		R.id.pregnancy_write_edit_sugar,};
	public static int[] item_checkup_label_9 = {
		R.array.plusminus_label,
		R.array.plusminus_label,
		R.array.plusminus_label,
		};

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pregnancy_write_edit);	//画面レイアウトを指定(res/layout/index_read.xml)
        activity=this;

        EditText[] tvParam_EditText = new EditText[item_checkup_EditText_9.length];

        //初期値読み込み
        File dir = new File(Environment.getExternalStorageDirectory().getPath());
        if(dir.exists()){
	    	 for(int i1 = 1; i1 < 20; i1++) {
	    		 File file = new File(dir.getAbsolutePath()+"/Yukari/Write/Pregnancy/Course/Pregnancyfile"+i1+".xml");
	    		 if (file.exists()) {
	    			 datapath="/Yukari/Write/Pregnancy/Course/Pregnancyfile"+(i1)+".xml";
	    			 photopath = i1;

	    		 }
	    	 }
	     }
        if(dir.exists()){

            File file = new File(dir.getAbsolutePath()+datapath);
            if (file.exists()) {
            	try {
            		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            		factory.setNamespaceAware(true);
            		XmlPullParser parser = factory.newPullParser();
            		FileInputStream fis = new FileInputStream(file);
            		parser.setInput(new InputStreamReader(fis));

            		// EditTextインスタンスの取得
            		for (int i1 = 0; i1 < item_checkup_EditText_9.length; i1++) {
            			tvParam_EditText[i1] = (EditText)findViewById(item_checkup_EditText_9[i1]);
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
                          	  if(tag.equals(item_pregnancy_writetag[0])) {tvParam_EditText[0].setText(value);}
                          	else if(tag.equals(item_pregnancy_writetag[1])) {tvParam_EditText[1].setText(value);}
                          	else if(tag.equals(item_pregnancy_writetag[2])) {tvParam_EditText[2].setText(value);}
                          	else if(tag.equals(item_pregnancy_writetag[3])) {tvParam_EditText[3].setText(value);}
                          	else if(tag.equals(item_pregnancy_writetag[4])) {tvParam_EditText[4].setText(value);}
                          	else if(tag.equals(item_pregnancy_writetag[5])) {tvParam_EditText[5].setText(value);}
                          	else if(tag.equals(item_pregnancy_writetag[6])) {tvParam_EditText[6].setText(value);}
                          	else if(tag.equals(item_pregnancy_writetag[7])) {tvParam_EditText[7].setText(value);}
                          	else if(tag.equals(item_pregnancy_writetag[8])) {tvParam_EditText[8].setText(value);}
                          	else if(tag.equals(item_pregnancy_Spinner_9tag[0])) {spinner_text[0] = parser.getText();}
                          	else if(tag.equals(item_pregnancy_Spinner_9tag[1])) {spinner_text[1] = parser.getText();}
                          	else if(tag.equals(item_pregnancy_Spinner_9tag[2])) {spinner_text[2] = parser.getText();}
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

        //日付出力
        editText_day1 = (EditText) findViewById(R.id.pregnancy_write_edit_examination);
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

        //それぞれのボタンにクリック時の処理を表示
       button_Write_camera = (Button) findViewById(R.id.pregnancy_write_edit_camera);
            button_Write_camera.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                button_Write_camera();
                }
            });

        button_Write_gallery = (Button) findViewById(R.id.pregnancy_write_edit_gallery);
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

    //スピナー入出力関数

	private void execSpinners() {
		String[][] label = new String[item_checkup_Spinner_9.length][];
		// Spinnerインスタンスの取得
		for (int i1 = 0; i1 < item_checkup_Spinner_9.length; i1++) {
			tvParam_Spinner[i1] = (Spinner)findViewById(item_checkup_Spinner_9[i1]);
			label[i1] = getResources().getStringArray(item_checkup_label_9[i1]);

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
	    		    ((ImageView)findViewById(R.id.imageview_pregnancy_write)).setImageBitmap(imageBitmap);
	    		} catch (FileNotFoundException e) {
	    			e.printStackTrace();
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}

	        	String filename = "imageView_Pregnancy" + photopath +".png";
                imageFileName="imageView_Pregnancy" + photopath +".png";
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
    	Intent intent_cancel = new Intent(getApplicationContext(),pregnancy_write.class);
        startActivity(intent_cancel);
        finish();
    }

    private void button_Write_save() {
    	Spinner spinner;
		Text text;

    	//ディレクトリの作成
    	String target_path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Yukari/Write/Pregnancy/Course";
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
    	     for (int i1 = 0; i1 < item_checkup_EditText_9.length; i1++) {
    	    	 Element element1 = document.createElement(item_pregnancy_writetag[i1]);
    	    	 String string1 = ((EditText) findViewById(item_checkup_EditText_9[i1])).getText().toString();
    	    	 text = document.createTextNode(string1);
    	    	 element1.appendChild(text);
    	    	 root.appendChild(element1);
    	     }

    	     //spinnerの中身を保存
    	     for (int i1 = 0; i1 < item_checkup_Spinner_9.length; i1++) {
    	   		 spinner = (Spinner) findViewById(item_checkup_Spinner_9[i1]);
    	    	 Element element1 = document.createElement(item_pregnancy_Spinner_9tag[i1]);
    	    	 String string1 = spinner.getSelectedItem().toString();
    	    	 text = document.createTextNode(string1);
    	    	 element1.appendChild(text);
    	    	 root.appendChild(element1);
    	     }

    	     document.appendChild(root);

    	     TransformerFactory tffactory = TransformerFactory.newInstance();
    	     Transformer transformer = tffactory.newTransformer();

    	//保存ファイルの作成
    	     File dir1 = new File(Environment.getExternalStorageDirectory().getPath());
    	     if(dir1.exists()){
    	    	 for(int i1 = 1; i1 < 20; i1++) {
    	    		 File file = new File(dir1.getAbsolutePath()+"/" +
    	    		 		"Yukari/Write/Pregnancy/Course/Pregnancyfile"+i1+".xml");
    	    		 if (file.exists()) {
    	    			 datapath="/Yukari/Write/Pregnancy/Course/Pregnancyfile"+(i1+1)+".xml";
                         xmlFileName="Pregnancyfile"+(i1+1)+".xml";
    	    		 }
    	    	 }
    	     }
            String filePath = Environment.getExternalStorageDirectory() + datapath;
        File file = new File(filePath);
        file.getParentFile().mkdir();
        transformer.transform(new DOMSource(document), new StreamResult(file));




        /**
         * ここからGoogleDriveへのアップロード 個々によって変更するところ
         * */
        try {
            DriveModule.OAuth(GoogleDriveBuild.credential, activity);
            pref = getSharedPreferences("prefs", Activity.MODE_PRIVATE);
            final String str = pref.getString("RootFolder",
                    "PERF NOT FOUND!!");
            Log.i("PREF", str);

            AsyncTask<Void, ProgressDialog, Void> task = new AsyncTask<Void, ProgressDialog, Void>() {
                protected void onPostExecute() {
                    // UIスレッド
                    //Toast.makeText(WriteCheckup_12p.this, "UPLOAD OK",
                    //	Toast.LENGTH_LONG);
                }

                protected Void doInBackground(Void... params) {
                    // TODO 自動生成されたメソッド・スタブ
                    boolean a = false;
                    try {
                        com.google.api.services.drive.model.File file1 = GoogleDriveBuild.service
                                .files()
                                .get(pref.getString("RootFolder", null))
                                .execute();
                        if (file1 != null) {
                            String filePath =  Environment.getExternalStorageDirectory() + datapath;
                            String imagefilePath= Environment.getExternalStorageDirectory().getPath() + "/Yukari/Photo/" + imageFileName;
                            if (DriveModule.checkFile(file1, "Checkup",xmlFileName,GoogleDriveBuild.service,GoogleDriveBuild.credential, filePath,"application/xml", activity) == true) {
                            } else {
                            }

                            if (DriveModule.checkFile(file1, "Checkup",imageFileName,GoogleDriveBuild.service,GoogleDriveBuild.credential, imagefilePath,"application/xml", activity) == true) {
                            } else {
                            }

                            a = true;
                        }
                    } catch (IOException e) {
                        // TODO 自動生成された catch ブロック
                        Log.e("ERROR a", e.toString());
                    }
                    return null;

                }

            };
            task.execute(); // 実行

        } catch (Exception e) {
            Log.e("DRIVE ERROR", e.toString());
        }

        if(GoogleDriveBuild.netWorkCheck(this)==false){
            Toast.makeText(this, "インターネットに接続されていません", Toast.LENGTH_LONG).show();
            Log.i("NETWORK","false")	;
        }


        /**
         * ここまでがアップロード
         * */
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
    	Intent intent_cancel = new Intent(getApplicationContext(),pregnancy_write.class);
        startActivity(intent_cancel);
        finish();
    }

  //バックキー
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
      if(keyCode==KeyEvent.KEYCODE_BACK){
    	  Intent intent_cancel = new Intent(getApplicationContext(),pregnancy_write.class);
          startActivity(intent_cancel);
          finish();
          return true;
      }
      return false;
    }
}
