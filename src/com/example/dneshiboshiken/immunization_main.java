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

//import android.R;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Environment;



public class immunization_main extends Activity implements View.OnClickListener{

	static String immunization_flag;
	static int immunization_number;

	private Button button_dpt1;
  //ここはクラス内で実行
	static int REQUEST_ACTION_PICK = 1;
	static final int REQUEST_CAPTURE_IMAGE = 100;
	private static final String APPLICATION_NAME = "PATOM";
	private static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/Yukari/Photo/" + APPLICATION_NAME;

	final boolean[] checkedItems = new boolean[50];

	public static int[] burtton_immunization_dpt = {R.id.button_immunization_dpt1,
		R.id.button_immunization_dpt2,
		R.id.button_immunization_dpt3,
		R.id.button_immunization_dpt4,
		R.id.button_immunization_bcg1,
		R.id.button_immunization_mr1,
		R.id.button_immunization_mr2,
		R.id.button_immunization_japa1,
		R.id.button_immunization_japa2,
		R.id.button_immunization_japa3,
		R.id.button_immunization_japa4,
		R.id.button_immunization_dt1,
		R.id.button_immunization_hib1,
		R.id.button_immunization_hib2,
		R.id.button_immunization_hib3,
		R.id.button_immunization_hib4,
		R.id.button_immunization_seven1,
		R.id.button_immunization_seven2,
		R.id.button_immunization_seven3,
		R.id.button_immunization_seven4,
		R.id.button_immunization_hpv1,
		R.id.button_immunization_hpv2,
		R.id.button_immunization_hpv3,
		R.id.button_immunization_varicella1,
		R.id.button_immunization_varicella2,
		R.id.button_immunization_mumps1,
		R.id.button_immunization_mumps2,
		R.id.button_immunization_b1,
		R.id.button_immunization_b2,
		R.id.button_immunization_b3,};

	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.immunization_main);
	    }

	 //アコーディオン読み込み
	 Boolean _first = true;
	 private AccordionSet _as1;
	 private AccordionSet _as2;
	 private AccordionSet _as3;
	 private AccordionSet _as4;
	 private AccordionSet _as5;
	 private AccordionSet _as6;
	 private AccordionSet _as7;
	 private AccordionSet _as8;
	 private AccordionSet _as9;
	 private AccordionSet _as10;
	 private AccordionSet _as11;


	 @Override
		public void onWindowFocusChanged(boolean hasFocus) {
			if (_first) {
				_first = false;
				_as1 = new AccordionSet((LinearLayout)findViewById(R.id.btn1), (LinearLayout)findViewById(R.id.content1));
				_as2 = new AccordionSet((LinearLayout)findViewById(R.id.btn2), (LinearLayout)findViewById(R.id.content2));
				_as3 = new AccordionSet((LinearLayout)findViewById(R.id.btn3), (LinearLayout)findViewById(R.id.content3));
				_as4 = new AccordionSet((LinearLayout)findViewById(R.id.btn4), (LinearLayout)findViewById(R.id.content4));
				_as5 = new AccordionSet((LinearLayout)findViewById(R.id.btn5), (LinearLayout)findViewById(R.id.content5));
				_as6 = new AccordionSet((LinearLayout)findViewById(R.id.btn6), (LinearLayout)findViewById(R.id.content6));
				_as7 = new AccordionSet((LinearLayout)findViewById(R.id.btn7), (LinearLayout)findViewById(R.id.content7));
				_as8 = new AccordionSet((LinearLayout)findViewById(R.id.btn8), (LinearLayout)findViewById(R.id.content8));
				_as9 = new AccordionSet((LinearLayout)findViewById(R.id.btn9), (LinearLayout)findViewById(R.id.content9));
				_as10 = new AccordionSet((LinearLayout)findViewById(R.id.btn10), (LinearLayout)findViewById(R.id.content10));
				_as11 = new AccordionSet((LinearLayout)findViewById(R.id.btn11), (LinearLayout)findViewById(R.id.content11));
			}
			super.onWindowFocusChanged(hasFocus);
		}

		@Override
		protected void onDestroy() {
			if (!_first) {
				_as1.deleteAccordion();
				_as2.deleteAccordion();
				_as3.deleteAccordion();
				_as4.deleteAccordion();
				_as5.deleteAccordion();
				_as6.deleteAccordion();
				_as7.deleteAccordion();
				_as8.deleteAccordion();
				_as9.deleteAccordion();
				_as10.deleteAccordion();
				_as11.deleteAccordion();
			}
			super.onDestroy();

		}



	/** Called when the activity is first created. */
	 public void onStart(){
	    	super.onStart();	//画面レイアウトを指定(res/layout/index_read.xml)
	    	Log.d("a", "が動かない");
	    	int i;
	    	for(i=0;i<burtton_immunization_dpt.length;i++){
	    		button_dpt1=(Button)findViewById(burtton_immunization_dpt[i]);
	    		button_dpt1.setOnClickListener(this);
	    	}

	 TextView[] tvParam = new TextView[immunization_dpt.item_immunization_mumps_tag.length];
     for (int i11 = 0; i11 < immunization_dpt.item_immunization_mumps_tag.length; i11++) {
       	tvParam[i11] = (TextView)findViewById(immunization_dpt.item_immunization_mumps_tag[i11]);
 		}
     a.onStart(tvParam);

	 TextView[] tvParam1 = new TextView[immunization_bcg.item_immunization_mumps_tag.length];
     for (int i11 = 0; i11 < immunization_bcg.item_immunization_mumps_tag.length; i11++) {
       	tvParam1[i11] = (TextView)findViewById(immunization_bcg.item_immunization_mumps_tag[i11]);
 		}
     b.onStart(tvParam1);

	 TextView[] tvParam11 = new TextView[immunization_mr.item_immunization_mumps_tag.length];
     for (int i11 = 0; i11 < immunization_mr.item_immunization_mumps_tag.length; i11++) {
       	tvParam11[i11] = (TextView)findViewById(immunization_mr.item_immunization_mumps_tag[i11]);
 		}
     c.onStart(tvParam11);

	 TextView[] tvParam111 = new TextView[immunization_japan.item_immunization_mumps_tag.length];
     for (int i11 = 0; i11 < immunization_japan.item_immunization_mumps_tag.length; i11++) {
       	tvParam111[i11] = (TextView)findViewById(immunization_japan.item_immunization_mumps_tag[i11]);
 		}
     d.onStart(tvParam111);
     TextView[] tvParam1111 = new TextView[immunization_dt.item_immunization_mumps_tag.length];
     for (int i11 = 0; i11 < immunization_dt.item_immunization_mumps_tag.length; i11++) {
       	tvParam1111[i11] = (TextView)findViewById(immunization_dt.item_immunization_mumps_tag[i11]);
 		}
     e.onStart(tvParam1111);
     TextView[] tvParam11111 = new TextView[immunization_hib.item_immunization_mumps_tag.length];
     for (int i11 = 0; i11 < immunization_hib.item_immunization_mumps_tag.length; i11++) {
       	tvParam11111[i11] = (TextView)findViewById(immunization_hib.item_immunization_mumps_tag[i11]);
 		}
     f.onStart(tvParam11111);
     TextView[] tvParam111111 = new TextView[immunization_seven.item_immunization_mumps_tag.length];
     for (int i11 = 0; i11 < immunization_seven.item_immunization_mumps_tag.length; i11++) {
       	tvParam111111[i11] = (TextView)findViewById(immunization_seven.item_immunization_mumps_tag[i11]);
 		}
     g.onStart(tvParam111111);
     TextView[] tvParam1111111 = new TextView[immunization_hpv.item_immunization_mumps_tag.length];
     for (int i11 = 0; i11 < immunization_hpv.item_immunization_mumps_tag.length; i11++) {
       	tvParam1111111[i11] = (TextView)findViewById(immunization_hpv.item_immunization_mumps_tag[i11]);
 		}
     h.onStart(tvParam1111111);
     TextView[] tvParam11111111 = new TextView[immunization_varicella.item_immunization_mumps_tag.length];
     for (int i11 = 0; i11 < immunization_varicella.item_immunization_mumps_tag.length; i11++) {
       	tvParam11111111[i11] = (TextView)findViewById(immunization_varicella.item_immunization_mumps_tag[i11]);
 		}
     ii.onStart(tvParam11111111);
     TextView[] tvParam111111111 = new TextView[immunization_mumps.item_immunization_mumps_tag.length];
     for (int i11 = 0; i11 < immunization_mumps.item_immunization_mumps_tag.length; i11++) {
       	tvParam111111111[i11] = (TextView)findViewById(immunization_mumps.item_immunization_mumps_tag[i11]);
 		}
     j.onStart(tvParam111111111);
     TextView[] tvParam11111111111 = new TextView[immunization_b.item_immunization_mumps_tag.length];
     for (int i11 = 0; i11 < immunization_b.item_immunization_mumps_tag.length; i11++) {
       	tvParam11111111111[i11] = (TextView)findViewById(immunization_b.item_immunization_mumps_tag[i11]);
 		}
     l.onStart(tvParam11111111111);




	 }
	 immunization_dpt a = new immunization_dpt();
	 immunization_bcg b = new immunization_bcg();
	 immunization_mr c = new immunization_mr();
	 immunization_japan d = new immunization_japan();
	 immunization_dt e = new immunization_dt();
	 immunization_hib f = new immunization_hib();
	 immunization_seven g = new immunization_seven();
	 immunization_hpv h = new immunization_hpv();
	 immunization_varicella ii= new immunization_varicella();
	 immunization_mumps j = new immunization_mumps();
	 immunization_japan k = new immunization_japan();
	 immunization_b l = new immunization_b();


    //詳細ボタン
    public void onClick(View v){
    	Intent intent=new Intent();
    	switch(v.getId()){
    	case R.id.button_immunization_dpt1:
    		immunization_main.immunization_flag="immunization_dpt";
    		immunization_number=1;
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_save2");
	        break;
    	case R.id.button_immunization_dpt2:
    		immunization_main.immunization_flag="immunization_dpt";
    		immunization_number=2;
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_save2");
	        break;
    	case R.id.button_immunization_dpt3:
    		immunization_main.immunization_flag="immunization_dpt";
    		immunization_number=3;
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_save2");
	        break;
    	case R.id.button_immunization_dpt4:
    		immunization_main.immunization_flag="immunization_dpt";
    		immunization_number=4;
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_save2");
	        break;
    	case R.id.button_immunization_bcg1:
    		immunization_main.immunization_flag="immunization_bcg";
    		immunization_number=1;
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_save");
	        break;
    	case R.id.button_immunization_mr1:
    		immunization_main.immunization_flag="immunization_mr";
    		immunization_number=1;
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_save");
	        break;
    	case R.id.button_immunization_mr2:
    		immunization_main.immunization_flag="immunization_mr";
    		immunization_number=2;
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_save");
	        break;
    	case R.id.button_immunization_japa1:
    		immunization_main.immunization_flag="immunization_japan";
    		immunization_number=1;
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_save");
	        break;
    	case R.id.button_immunization_japa2:
    		immunization_main.immunization_flag="immunization_japan";
    		immunization_number=2;
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_save");
	        break;
    	case R.id.button_immunization_japa3:
    		immunization_main.immunization_flag="immunization_japan";
    		immunization_number=3;
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_save");
	        break;
    	case R.id.button_immunization_japa4:
    		immunization_main.immunization_flag="immunization_japan";
    		immunization_number=4;
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_save");
	        break;
    	case R.id.button_immunization_dt1:
    		immunization_main.immunization_flag="immunization_dt";
    		immunization_number=1;
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_save");
	        break;
    	case R.id.button_immunization_hib1:
    		immunization_main.immunization_flag="immunization_hib";
    		immunization_number=1;
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_save");
	        break;
    	case R.id.button_immunization_hib2:
    		immunization_main.immunization_flag="immunization_hib";
    		immunization_number=2;
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_save");
	        break;
    	case R.id.button_immunization_hib3:
    		immunization_main.immunization_flag="immunization_hib";
    		immunization_number=3;
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_save");
	        break;
    	case R.id.button_immunization_hib4:
    		immunization_main.immunization_flag="immunization_hib";
    		immunization_number=4;
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_save");
	        break;
    	case R.id.button_immunization_seven1:
    		immunization_main.immunization_flag="immunization_seven";
    		immunization_number=1;
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_save");
	        break;
    	case R.id.button_immunization_seven2:
    		immunization_main.immunization_flag="immunization_seven";
    		immunization_number=2;
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_save");
	        break;
    	case R.id.button_immunization_seven3:
    		immunization_main.immunization_flag="immunization_seven";
    		immunization_number=3;
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_save");
	        break;
    	case R.id.button_immunization_seven4:
    		immunization_main.immunization_flag="immunization_seven";
    		immunization_number=4;
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_save");
	        break;
    	case R.id.button_immunization_hpv1:
    		immunization_main.immunization_flag="immunization_hpv";
    		immunization_number=1;
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_save");
	        break;
    	case R.id.button_immunization_hpv2:
    		immunization_main.immunization_flag="immunization_hpv";
    		immunization_number=2;
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_save");
	        break;
    	case R.id.button_immunization_hpv3:
    		immunization_main.immunization_flag="immunization_hpv";
    		immunization_number=3;
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_save");
    	case R.id.button_immunization_varicella1:
    		immunization_main.immunization_flag="immunization_varicella";
    		immunization_number=1;
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_save");
	        break;
    	case R.id.button_immunization_varicella2:
    		immunization_main.immunization_flag="immunization_varicella";
    		immunization_number=2;
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_save");
	        break;
	        case R.id.button_immunization_mumps1:
    		immunization_main.immunization_flag="immunization_mumps";
    		immunization_number=1;
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_save");
	        break;
    	case R.id.button_immunization_mumps2:
    		immunization_main.immunization_flag="immunization_mumps";
    		immunization_number=2;
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_save");
	        break;
    	case R.id.button_immunization_b1:
    		immunization_main.immunization_flag="immunization_b";
    		immunization_number=1;
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_save");
	        break;
    	case R.id.button_immunization_b2:
    		immunization_main.immunization_flag="immunization_b";
    		immunization_number=2;
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_save");
	        break;
    	case R.id.button_immunization_b3:
    		immunization_main.immunization_flag="immunization_b";
    		immunization_number=3;
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_save");
    	


	        /*
    	case R.id.button_immunization_bcg:
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_bcg");
	        break;

    	case R.id.button_immunization_mr:
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_mr");
	        break;

    	case R.id.button_immunization_japan:
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_japan");
	        break;

    	case R.id.button_immunization_dt:
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_dt");
	        break;

    	case R.id.button_immunization_hib:
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_hib");
	        break;

    	case R.id.button_immunization_seven:
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_seven");
	        break;

    	case R.id.button_immunization_hpv:
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_hpv");
	        break;

    	case R.id.button_immunization_varicella:
	        intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_varicella");
	        break;

    	case R.id.button_immunization_mumps:
    		intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_mumps");
	        break;*/

    	//case R.id.button_immunization_b:
	     //   intent.setClassName("com.example.dneshiboshiken", "com.example.dneshiboshiken.immunization_b");
	       // break;

    	}
    	startActivity(intent);


    }

	//チェックボックスダイアログ


    	//Toast.makeText(this, "保存が完了しました", Toast.LENGTH_LONG).show();
	}



  //バックキー


