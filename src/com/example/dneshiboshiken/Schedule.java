//教育機能の目次
package com.example.dneshiboshiken;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import com.example.dneshiboshiken.R;
import com.example.dneshiboshiken.R.id;
import com.example.dneshiboshiken.R.layout;

public class Schedule extends Activity {

	//目次の項目だけボタンを定義
	private Button button_schedule_back;
	private Button button_schedule_next;

	EditText et[];
	String fileName;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule);	//画面レイアウトを指定(res/layout/index_read.xml)


        //それぞれのボタンにクリック時の処理を表示
        button_schedule_back = (Button) findViewById(R.id.Button_schedule_back);
        button_schedule_back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
            button_schedule_back_onClick();
            }
        });


        button_schedule_next = (Button) findViewById(R.id.Button_schedule_next);
        button_schedule_next.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
            	button_schedule_next_onClick();
            }
        });
        
        fileName="write_8.txt";

		//エディットテキストを初期化し空欄にする
		et=new EditText[4];
		et[0]=(EditText)findViewById(R.id.EditText_schedule_01);
		et[1]=(EditText)findViewById(R.id.EditText_schedule_02);
		et[2]=(EditText)findViewById(R.id.EditText_schedule_03);
		et[3]=(EditText)findViewById(R.id.EditText_schedule_04);
		for(int i=0;i<et.length;i++){
			et[i].setText("");
		}
        
		
		
		//受講年月日記入欄に現在の日付を表示
		Calendar cld=Calendar.getInstance();
		et[0].setText(String.valueOf(cld.get(Calendar.YEAR)));
		et[1].setText(String.valueOf(cld.get(Calendar.MONTH)+1));
		et[2].setText(String.valueOf(cld.get(Calendar.DAY_OF_MONTH)));
		
		
		
		
        
        
        
    }
    //ボタンクリックによって呼び出される処理
    //classの呼び出し(EMCHH.java)で行った内容と同様
    public void button_schedule_back_onClick() {
        Intent intent_read_1 = new Intent(getApplicationContext(),Calendar.class);
       startActivity(intent_read_1);
    }



    public void button_schedule_next_onClick() {
        Intent intent_read_3 = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent_read_3);
    }
    
   



  //onCreateOptionsMenuメソッド(オプションメニュー生成)
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu){
    	super.onCreateOptionsMenu(menu);

    //メニューアイテムの追加
    	MenuItem item1=menu.add(0,0,0,"編集");
    	item1.setIcon(android.R.drawable.ic_menu_edit);

    	MenuItem item2=menu.add(0,1,0,"タイトル");
    	item2.setIcon(R.drawable.ic_menu_home);

    	return true;
    }*/
    
  //onOPtionsItemSelectedメソッド(メニューアイテム選択処理)
   /* @Override
     public boolean onOptionsItemSelected(MenuItem item){
    	 switch (item.getItemId()){
    	 case 0:
    		 Intent intent1 = new Intent();
    		 intent1.setClass(Schedule.this, MainActivity.class);
    		 startActivity(intent1);
    		 return true;
    	 }
    	 return true;
    }*/

    //バックキーのアクション
    public boolean onKeyDown(int keyCode, KeyEvent event) {
   if(keyCode==KeyEvent.KEYCODE_BACK){
   	
   
   Intent intent_back = new Intent(getApplicationContext(),MainActivity.class);
   startActivity(intent_back);
   setResult(RESULT_OK, intent_back);
   finish();
   }
   return false;
}

}


