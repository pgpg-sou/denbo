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

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Environment;


public class WriteCheckup_0p extends Activity{

	//目次の項目だけボタンを定義
	private Button button_Write_cancel;
	private Button button_Write_save;
	private Button button_Write_gallery;
	private Button button_Write_camera;
  //ここはクラス内で実行
	static int REQUEST_ACTION_PICK = 1;
	static final int REQUEST_CAPTURE_IMAGE = 1;

	private static final String APPLICATION_NAME = "PATOM";
	private static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/Yukari/Photo/" + APPLICATION_NAME;

	Spinner spin[];
	String labels[][];

	private static int REQUEST_CAMERA = 1;
	private static Uri mImageUri; // インスタンス変数

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_checkup_0p);	//画面レイアウトを指定(res/layout/index_read.xml)


        //xmlファイル読み込み
        File dir = new File(Environment.getExternalStorageDirectory().getPath());
        if(dir.exists()){
            File file = new File(dir.getAbsolutePath()+"/Yukari/Write/Checkup/WriteCheckup_0file.xml");
            if (file.exists()) {
            	try {
            		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            		factory.setNamespaceAware(true);
            		XmlPullParser parser = factory.newPullParser();
            		FileInputStream fis = new FileInputStream(file);
            		parser.setInput(new InputStreamReader(fis));

                    // TextViewインスタンスの取得
            		EditText tvParam1 = (EditText)findViewById(R.id.EditText_write_checkup_0p_weight);
            		EditText tvParam2 = (EditText)findViewById(R.id.EditText_write_checkup_0p_height);
            		EditText tvParam3 = (EditText)findViewById(R.id.EditText_write_checkup_0p_chest);
            		EditText tvParam4 = (EditText)findViewById(R.id.EditText_write_checkup_0p_head);

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
                            // 空白で取得したものは全て処理対象外とする
                            if(value.trim().length() != 0) {
                                // 取得した結果をTextViewに設定
                                if(tag.equals("write_checkup_0_weight")) {
                                    tvParam1.setText(value);
                                } else if(tag.equals("write_checkup_0_height")) {
                                    tvParam2.setText(value);
                                }else if(tag.equals("write_checkup_0_chest")) {
                                    tvParam3.setText(value);
                                }else if(tag.equals("write_checkup_0_head")) {
                                    tvParam4.setText(value);
                                }
                            }
                            break;
                        case XmlPullParser.END_TAG: // 終了タグ
                            break;
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "エラー発生", Toast.LENGTH_SHORT);
                }
            	//画像読み込み
                if(dir.exists()){
                	if(dir.exists()){
                        File view = new File(dir.getAbsolutePath()+"/Yukari/Photo/imageView_write_checkup_0p.png");
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

                        	((ImageView)findViewById(R.id.imageview_write_checkup_0p)).setImageBitmap(image);
                        }else{
                            //存在しない
                        }
                }}
        }


        //それぞれのボタンにクリック時の処理を表示
       button_Write_camera = (Button) findViewById(R.id.Button_write_checkup_0p_camera);
            button_Write_camera.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                button_Write_camera();
                }
            });

        button_Write_gallery = (Button) findViewById(R.id.Button_write_checkup_0p_gallery);
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
        }

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

    //プレビューの画像処理
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
	    		    ((ImageView)findViewById(R.id.imageview_write_checkup_0p)).setImageBitmap(imageBitmap);
	    		} catch (FileNotFoundException e) {
	    			e.printStackTrace();
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}

	    		String filename = "imageView_write_checkup_0p.png";
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
    	Intent intent_cancel = new Intent(getApplicationContext(),WriteCheckup_0.class);
        startActivity(intent_cancel);
        ImageView iv = (ImageView)findViewById(R.id.imageview_write_checkup_0p);
        iv.setImageDrawable(null);
        finish();
    }

    private void button_Write_save() {
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

    	     //要素を作成 体重
    	     Element weight_0 = document.createElement("write_checkup_0_weight");//要素名（体重）
    	     String weight = ((EditText) findViewById(R.id.EditText_write_checkup_0p_weight)).getText().toString();
    	     Text text = document.createTextNode(weight);
    	     weight_0.appendChild(text);

    	     //身長
    	     Element height_0 = document.createElement("write_checkup_0_height");
    	     String height = ((EditText) findViewById(R.id.EditText_write_checkup_0p_height)).getText().toString();
    	     text = document.createTextNode(height);
    	     height_0.appendChild(text);

    	     //胸囲
    	     Element chest_0 = document.createElement("write_checkup_0_chest");
    	     String chest = ((EditText) findViewById(R.id.EditText_write_checkup_0p_chest)).getText().toString();
    	     text = document.createTextNode(chest);
    	     chest_0.appendChild(text);

    	     //頭囲
    	     Element head_0 = document.createElement("write_checkup_0_head");
    	     String head = ((EditText) findViewById(R.id.EditText_write_checkup_0p_head)).getText().toString();
    	     text = document.createTextNode(head);
    	     head_0.appendChild(text);


    	     //各要素を親ノードへ追加
    	     root.appendChild(weight_0);
    	     root.appendChild(height_0);
    	     root.appendChild(chest_0);
    	     root.appendChild(head_0);

    	     document.appendChild(root);

    	     TransformerFactory tffactory = TransformerFactory.newInstance();
    	     Transformer transformer = tffactory.newTransformer();

    	//保存ファイルの作成
    	String filePath = Environment.getExternalStorageDirectory() + "/Yukari/Write/Checkup/WriteCheckup_0file.xml";
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

        Intent intent_cancel = new Intent(getApplicationContext(),WriteCheckup_0.class);
        startActivity(intent_cancel);
        ImageView iv = (ImageView)findViewById(R.id.imageview_write_checkup_0p);
        iv.setImageDrawable(null);
        finish();
    }


    //バックキー
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
      if(keyCode==KeyEvent.KEYCODE_BACK){
    	  Intent intent_cancel = new Intent(getApplicationContext(),WriteCheckup_0.class);
          startActivity(intent_cancel);
          finish();
          return true;
      }
      return false;
    }

}
