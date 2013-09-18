//XML関係のデータ操作をまとめたもの
package com.example.dneshiboshiken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;
import android.widget.AdapterView.OnItemClickListener;

public class XmlMyFunc {
    final String sdcard = Environment.getExternalStorageDirectory().getPath();
    final String fileName=sdcard+"/data.xml";	//ファイルは共通して1つを使用
    ArrayAdapter<String> stringList;
    ListView list;
    LinearLayout questionBase;
    LinearLayout base;
    LayoutInflater inflater;

    //トーストの作成と表示
    public void msgbox(Context base,String displayData){
    	Toast.makeText(base, displayData, Toast.LENGTH_LONG).show();
    }

    //文字列が整数値なら整数tに変換して返す
    //変換できない倍は0を返す
    public int getInteger(String data){
    	try {
			int output=Integer.parseInt(data);
			return output;
		} catch (Exception e) {
			return 0;
		}
    }

    //引数が真なら表示，偽なら非表示
    public int setVisiblity(boolean judge){
    	if(judge){
    		return View.VISIBLE;
    	}else{
    		return View.GONE;
    	}
    }
    //judge	判定に利用する変数
    //condition 判定の基準
    //一致すれば表示，しなければ非表示
    public int setVisiblity(int judge,int condition){
    	if(judge==condition){
    		return View.VISIBLE;
    	}else{
    		return View.GONE;
    	}
    }

    //データの変更
    public void changeTableData(String destination,String[] newData){
		try {
        	deleteTag(destination+"/data");
    		
			String position[]=destination.split("/");
			String[] nowPos=new String[position.length];	//現在の位置を取得
			for(int i=0;i<nowPos.length;i++){
				nowPos[i]="";
			}
			int depth=0;
			//入力データ
	        final XmlPullParser xpp = Xml.newPullParser();
	        BufferedReader reader =new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
	        xpp.setInput(reader);

			//出力データ
	        //コピー先はcopy.xml
			BufferedWriter writer =	new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sdcard+"/copy.xml"),"UTF-8"));
	        XmlSerializer serializer = Xml.newSerializer();
	        StringWriter swriter = new StringWriter();
            serializer.setOutput(writer);
            
            //入力から出力へコピー
            int e=xpp.getEventType();
            while (e != XmlPullParser.END_DOCUMENT) {
            	if(e == XmlPullParser.START_DOCUMENT) {
                    serializer.startDocument("UTF-8", true);
            	} else if(e == XmlPullParser.END_DOCUMENT) {
                    serializer.endDocument();
            	} else if(e == XmlPullParser.START_TAG) {
                    serializer.startTag("", xpp.getName());
            		if(depth<nowPos.length){
	            		nowPos[depth]=xpp.getName();	//タグがあれば現在地に追加
	            		if(isRightPos(position,nowPos)){	//所望のポイントに入ったか
	            			//データの挿入
	            			for(int i=0;i<newData.length;i++){
	            				Log.e("debug", newData[i]);
	            				serializer.startTag("", "data");
	            				serializer.text(newData[i]);
	            				serializer.endTag("", "data");
	            			}
	        			}
        			}
            		//深さを一つ上げる
            		depth++;
            	} else if(e == XmlPullParser.END_TAG) {
					serializer.endTag("", xpp.getName());
            		//深さを一つ下げる
            		depth--;
            		if(depth<nowPos.length){
	            		nowPos[depth]="";	            	//タグから出たのでそのデータを消去
            		}
            	} else if(e == XmlPullParser.TEXT) {
					serializer.text(xpp.getText());
            	}
        		e=xpp.next();
            }

            //終了処理
            writer.append(swriter.toString());
            writer.close();
            reader.close();
   		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		renameFile(sdcard+"/copy.xml", fileName);
    }

    
    public void deleteTableData(String destination, String data){
    	//表のデータの削除
    	//destination	目標とするタブの位置(/は区切り文字)
    	//data	消去する対象の文字列
    	try {
    		//対称の表にする部分のデータを取得
    		String[] oldData=getTableData(destination+"/data");
    		ArrayList<String> newDataList=new ArrayList<String>();
    		String[] newData;
    		
    		//ここから削除対象の情報を検索する
    		//削除対象のデータ以外を配列に代入
    		for(int i=0;i<oldData.length;i++){
    			if(!oldData[i].equals(data)){
    				newDataList.add(oldData[i]);
    			}
    		}
    		newData=new String[newDataList.size()];
    		for(int i=0;i<newData.length;i++){
				newData[i]=newDataList.get(i);
    		}

        	deleteTag(destination+"/data");
    		
			String position[]=destination.split("/");
			String[] nowPos=new String[position.length];	//現在の位置を取得
			for(int i=0;i<nowPos.length;i++){
				nowPos[i]="";
			}
			int depth=0;
			//入力データ
	        final XmlPullParser xpp = Xml.newPullParser();
	        BufferedReader reader =new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
	        xpp.setInput(reader);

			//出力データ
	        //コピー先はcopy.xml
			BufferedWriter writer =	new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sdcard+"/copy.xml"),"UTF-8"));
	        XmlSerializer serializer = Xml.newSerializer();
	        StringWriter swriter = new StringWriter();
            serializer.setOutput(writer);
            
            //入力から出力へコピー
            int e=xpp.getEventType();
            while (e != XmlPullParser.END_DOCUMENT) {
            	if(e == XmlPullParser.START_DOCUMENT) {
                    serializer.startDocument("UTF-8", true);
            	} else if(e == XmlPullParser.END_DOCUMENT) {
                    serializer.endDocument();
            	} else if(e == XmlPullParser.START_TAG) {
                    serializer.startTag("", xpp.getName());
            		if(depth<nowPos.length){
	            		nowPos[depth]=xpp.getName();	//タグがあれば現在地に追加
	            		if(isRightPos(position,nowPos)){	//所望のポイントに入ったか
	            			//データの挿入
	            			for(int i=0;i<newData.length;i++){
	            				Log.e("debug", newData[i]);
	            				serializer.startTag("", "data");
	            				serializer.text(newData[i]);
	            				serializer.endTag("", "data");
	            			}
	        			}
        			}
            		//深さを一つ上げる
            		depth++;
            	} else if(e == XmlPullParser.END_TAG) {
					serializer.endTag("", xpp.getName());
            		//深さを一つ下げる
            		depth--;
            		if(depth<nowPos.length){
	            		nowPos[depth]="";	            	//タグから出たのでそのデータを消去
            		}
            	} else if(e == XmlPullParser.TEXT) {
					serializer.text(xpp.getText());
            	}
        		e=xpp.next();
            }

            //終了処理
            writer.append(swriter.toString());
            writer.close();
            reader.close();
   		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		renameFile(sdcard+"/copy.xml", fileName);
    }


    //データの保存
    //存在しないタグがある場合は引数の位置にしたがってタグを追加
    public void appendDataAndTag(String destination,String tagName,String data){
    	//destination	タグの位置
    	//data	保存するデータ
		try {
			String position[]=destination.split("/");
			//タグの存在確認(なければ追加)
			for(int i=1;i<position.length;i++){
				String[] distance;
				distance=new String[i+1];
				for(int j=0;j<distance.length;j++){
					distance[j]=position[j];
				}
				if(!searchTag(distance)){
					Log.e("tag","見つからないので追加します");
					insertTag(distance);
				}
			}

	        String[] nowPos=new String[position.length];	//現在の位置を取得
			int depth=0;
			boolean isWriteTXT;

			//入力データ
	        final XmlPullParser xpp = Xml.newPullParser();
	        BufferedReader reader =new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
	        xpp.setInput(reader);

			//出力データ
	        //コピー先はcopy.xml
			BufferedWriter writer =	new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sdcard+"/copy.xml"),"UTF-8"));
	        XmlSerializer serializer = Xml.newSerializer();
	        StringWriter swriter = new StringWriter();
            serializer.setOutput(writer);

            //入力から出力へコピー
            int e=xpp.getEventType();
            while (e != XmlPullParser.END_DOCUMENT) {
            	isWriteTXT=false;
            	if(e == XmlPullParser.START_DOCUMENT) {
                    serializer.startDocument("UTF-8", true);
            	} else if(e == XmlPullParser.END_DOCUMENT) {
                    serializer.endDocument();
            	} else if(e == XmlPullParser.START_TAG) {
                    serializer.startTag("", xpp.getName());
            		if(depth<nowPos.length){
	            		nowPos[depth]=xpp.getName();	//タグがあれば現在地に追加
	            		if(isRightPos(position,nowPos)){	//所望のポイントに入ったか
	            			serializer.startTag("", tagName);
                    		serializer.text(data);
	            			serializer.endTag("", tagName);
                    		isWriteTXT=true;
            			}
        			}
            		//深さを一つ下げる
            		depth++;
            	} else if(e == XmlPullParser.END_TAG) {
            		if(depth<nowPos.length){
	            		nowPos[depth]="";	            	//タグから出たのでそのデータを消去
            		}
            		//深さを一つ上げる
            		depth--;
                    serializer.endTag("", xpp.getName());
            	} else if(e == XmlPullParser.TEXT) {
            		serializer.text(xpp.getText());
            	}
        		e=xpp.next();
            }

            //終了処理
            writer.append(swriter.toString());
            writer.close();
            reader.close();
   		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		renameFile(sdcard+"/copy.xml", fileName);
    }

    //データの保存
    //存在しないタグがある場合は引数の位置にしたがってタグを追加
    public void appendData(String destination,String data){
    	//destination	タグの位置
    	//data	保存するデータ
		try {
			String position[]=destination.split("/");
			//タグの存在確認(なければ追加)
			for(int i=1;i<position.length;i++){
				String[] distance;
				distance=new String[i+1];
				for(int j=0;j<distance.length;j++){
					distance[j]=position[j];
				}
				if(!searchTag(distance)){
					Log.e("tag","見つからないので追加します");
					insertTag(distance);
				}
			}

	        String[] nowPos=new String[position.length];	//現在の位置を取得
			int depth=0;
			boolean isWriteTXT;

			//入力データ
	        final XmlPullParser xpp = Xml.newPullParser();
	        BufferedReader reader =new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
	        xpp.setInput(reader);

			//出力データ
	        //コピー先はcopy.xml
			BufferedWriter writer =	new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sdcard+"/copy.xml"),"UTF-8"));
	        XmlSerializer serializer = Xml.newSerializer();
	        StringWriter swriter = new StringWriter();
            serializer.setOutput(writer);

            //入力から出力へコピー
            int e=xpp.getEventType();
            while (e != XmlPullParser.END_DOCUMENT) {
            	isWriteTXT=false;
            	if(e == XmlPullParser.START_DOCUMENT) {
                    serializer.startDocument("UTF-8", true);
            	} else if(e == XmlPullParser.END_DOCUMENT) {
                    serializer.endDocument();
            	} else if(e == XmlPullParser.START_TAG) {
                    serializer.startTag("", xpp.getName());
            		if(depth<nowPos.length){
	            		nowPos[depth]=xpp.getName();	//タグがあれば現在地に追加
	            		if(isRightPos(position,nowPos)){	//所望のポイントに入ったか
	            			//タグ内にデータを投げる時
                    		serializer.text(data);
                    		isWriteTXT=true;
            			}
        			}
            		//深さを一つ下げる
            		depth++;
            	} else if(e == XmlPullParser.END_TAG) {
            		if(depth<nowPos.length){
	            		nowPos[depth]="";	            	//タグから出たのでそのデータを消去
            		}
            		//深さを一つ上げる
            		depth--;
                    serializer.endTag("", xpp.getName());
            	} else if(e == XmlPullParser.TEXT) {
            		serializer.text(xpp.getText());
            	}
        		e=xpp.next();
            }

            //終了処理
            writer.append(swriter.toString());
            writer.close();
            reader.close();
   		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		renameFile(sdcard+"/copy.xml", fileName);
    }


    //初回起動時など，ファイルが存在しないことを防ぐためにあらかじめファイルを作成する
    public void makeFile(){
		BufferedWriter writer;
		try {
			//初期設定
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName),"UTF-8"));
	        XmlSerializer serializer = Xml.newSerializer();
	        StringWriter swriter = new StringWriter();
	        serializer.setOutput(writer);

	        //XMLのファイルとして最低限の内容を保存
            serializer.startDocument("UTF-8", true);//XMLの設定
            serializer.startTag("", "root");//ルートタグの作成(第一階層はこれのみとする)
            serializer.endTag("", "root");
            serializer.endDocument();

            writer.append(swriter.toString());
            writer.close();

		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

    }

    //データの消去
    public void deleteData(String destination, String data){
		try {
			String position[]=destination.split("/");
			String[] nowPos=new String[position.length];	//現在の位置を取得
			int depth=0;
			boolean isDeleteArea=false;
			String tagName="";

			//入力データ
	        final XmlPullParser xpp = Xml.newPullParser();
	        BufferedReader reader =new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
	        xpp.setInput(reader);

			//出力データ
	        //コピー先はcopy.xml
			BufferedWriter writer =	new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sdcard+"/copy.xml"),"UTF-8"));
	        XmlSerializer serializer = Xml.newSerializer();
	        StringWriter swriter = new StringWriter();
            serializer.setOutput(writer);

            //入力から出力へコピー
            int e=xpp.getEventType();
            while (e != XmlPullParser.END_DOCUMENT) {
            	if(isDeleteArea){
            		if(e==XmlPullParser.TEXT){
            			String nowData=xpp.getText();
            			if(!nowData.equals(data)){
            				serializer.startTag("", tagName);
            				isDeleteArea=false;
            			}
            		}
            	}
            	if(e == XmlPullParser.START_DOCUMENT) {
                    serializer.startDocument("UTF-8", true);
            	} else if(e == XmlPullParser.END_DOCUMENT) {
                    serializer.endDocument();
            	} else if(e == XmlPullParser.START_TAG) {
            		if(depth<nowPos.length){
	            		nowPos[depth]=xpp.getName();	//タグがあれば現在地に追加
	            		//深さを一つ下げる
	            		depth++;
	            		if(isRightPos(position,nowPos)){	//所望のポイントに入ったか
	            			tagName=xpp.getName();
            				isDeleteArea=true;
            			}else{
        					serializer.startTag("", xpp.getName());
            			}
        			}else{
        				if(!isDeleteArea){
        					serializer.startTag("", xpp.getName());
        				}
        				depth++;
        			}
            	} else if(e == XmlPullParser.END_TAG) {
    				if(isDeleteArea){
	            		if(isRightPos(position,nowPos)){	//所望のポイントに入ったか
	            			isDeleteArea=false;
	            		}
     				}else{
                        serializer.endTag("", xpp.getName());
    				}
            		if(depth<nowPos.length){
	            		nowPos[depth]="";	            	//タグから出たのでそのデータを消去
            		}
            		//深さを一つ上げる
            		depth--;
            	} else if(e == XmlPullParser.TEXT) {
    				if(!isDeleteArea){
    					serializer.text(xpp.getText());
    				}
    			}
        		e=xpp.next();
            }

            //終了処理
            writer.append(swriter.toString());
            writer.close();
            reader.close();
   		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		renameFile(sdcard+"/copy.xml", fileName);
    }

    //タグの消去
    public void deleteTag(String destination){
		try {
			String position[]=destination.split("/");
			String[] nowPos=new String[position.length];	//現在の位置を取得
			int depth=0;
			boolean isDeleteArea=false;

			//入力データ
	        final XmlPullParser xpp = Xml.newPullParser();
	        BufferedReader reader =new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
	        xpp.setInput(reader);

			//出力データ
	        //コピー先はcopy.xml
			BufferedWriter writer =	new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sdcard+"/copy.xml"),"UTF-8"));
	        XmlSerializer serializer = Xml.newSerializer();
	        StringWriter swriter = new StringWriter();
            serializer.setOutput(writer);

            //入力から出力へコピー
            int e=xpp.getEventType();
            while (e != XmlPullParser.END_DOCUMENT) {
            	if(e == XmlPullParser.START_DOCUMENT) {
                    serializer.startDocument("UTF-8", true);
                    Log.e("debug","StartDoc");
            	} else if(e == XmlPullParser.END_DOCUMENT) {
                    serializer.endDocument();
                    Log.e("debug","EndDoc");
            	} else if(e == XmlPullParser.START_TAG) {
                    Log.e("debug","StartTag");
            		if(depth<nowPos.length){
	            		nowPos[depth]=xpp.getName();	//タグがあれば現在地に追加
	            		//深さを一つ下げる
	            		depth++;
	            		if(isRightPos(position,nowPos)){	//所望のポイントに入ったか
	            			isDeleteArea=true;
            			}else{
            				if(!isDeleteArea){
            					serializer.startTag("", xpp.getName());
            				}
            			}
        			}else{
        				if(!isDeleteArea){
        					serializer.startTag("", xpp.getName());
        				}
        				depth++;
        			}
            	} else if(e == XmlPullParser.END_TAG) {
                    Log.e("debug","EndTag");
            		//深さを一つ上げる
    				if(isDeleteArea){
	            		if(isRightPos(position,nowPos)){	//所望のポイントに入ったか
	            			isDeleteArea=false;
	            		}
     				}else{
                        serializer.endTag("", xpp.getName());
    				}
            		depth--;
            		if(depth<nowPos.length){
	            		nowPos[depth]="";	            	//タグから出たのでそのデータを消去
            		}
            	} else if(e == XmlPullParser.TEXT) {
                    Log.e("debug","Text");
    				if(!isDeleteArea){
    					serializer.text(xpp.getText());
    				}
    			}
        		e=xpp.next();
            }

            //終了処理
            writer.append(swriter.toString());
            writer.close();
            reader.close();
   		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		renameFile(sdcard+"/copy.xml", fileName);
    }

    //データの挿入
    public void insertData(String destination,String data){
		try {
			String output=data;
			if(output.equals("")){
				output="----";
			}
			String position[]=destination.split("/");
			//タグの存在確認(なければ追加)
			for(int i=1;i<position.length;i++){
				String[] distance;
				distance=new String[i+1];
				for(int j=0;j<distance.length;j++){
					distance[j]=position[j];
				}
				if(!searchTag(distance)){
					Log.e("tag","見つからないので追加します");
					insertTag(distance);
				}
			}

	        String[] nowPos=new String[position.length];	//現在の位置を取得
			int depth=0;
			boolean isWriteTXT;

			//入力データ
	        final XmlPullParser xpp = Xml.newPullParser();
	        BufferedReader reader =new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
	        xpp.setInput(reader);

			//出力データ
	        //コピー先はcopy.xml
			BufferedWriter writer =	new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sdcard+"/copy.xml"),"UTF-8"));
	        XmlSerializer serializer = Xml.newSerializer();
	        StringWriter swriter = new StringWriter();
            serializer.setOutput(writer);

            //入力から出力へコピー
            int e=xpp.getEventType();
            while (e != XmlPullParser.END_DOCUMENT) {
            	isWriteTXT=false;
            	if(e == XmlPullParser.START_DOCUMENT) {
                    serializer.startDocument("UTF-8", true);
            	} else if(e == XmlPullParser.END_DOCUMENT) {
                    serializer.endDocument();
            	} else if(e == XmlPullParser.START_TAG) {
                    serializer.startTag("", xpp.getName());
            		if(depth<nowPos.length){
	            		nowPos[depth]=xpp.getName();	//タグがあれば現在地に追加
	            		if(isRightPos(position,nowPos)){	//所望のポイントに入ったか
	            			//タグ内にデータを投げる時
                    		serializer.text(output);
                    		isWriteTXT=true;
            			}
        			}
            		//深さを一つ下げる
            		depth++;
            	} else if(e == XmlPullParser.END_TAG) {
            		if(depth<nowPos.length){
	            		nowPos[depth]="";	            	//タグから出たのでそのデータを消去
            		}
            		//深さを一つ上げる
            		depth--;
                    serializer.endTag("", xpp.getName());
            	} else if(e == XmlPullParser.TEXT) {
            		serializer.text(xpp.getText());
            	}
        		e=xpp.next();
            	if(isWriteTXT&&e==XmlPullParser.TEXT){
            		e=xpp.next();
            	}
            }

            //終了処理
            writer.append(swriter.toString());
            writer.close();
            reader.close();
   		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		renameFile(sdcard+"/copy.xml", fileName);
    }

    //タグの挿入
    public void insertTag(String[] position){
		try {
	        String[] nowPos=new String[position.length-1];	//現在の位置を取得
	        String[] parent=new String[position.length-1];	//現在の位置を取得
	        String tagName =position[position.length-1];
			for(int i=0;i<parent.length;i++){
				parent[i]=position[i];
			}
			int depth=0;

			//入力データ
	        final XmlPullParser xpp = Xml.newPullParser();
	        BufferedReader reader =new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
	        xpp.setInput(reader);

			//出力データ
	        //コピー先はcopy.xml
			BufferedWriter writer =	new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sdcard+"/copy.xml"),"UTF-8"));
	        XmlSerializer serializer = Xml.newSerializer();
	        StringWriter swriter = new StringWriter();
            serializer.setOutput(writer);

            //入力から出力へコピー
            int e=xpp.getEventType();
            while (e != XmlPullParser.END_DOCUMENT) {
            	if(e == XmlPullParser.START_DOCUMENT) {
                    serializer.startDocument("UTF-8", true);
            	} else if(e == XmlPullParser.END_DOCUMENT) {
                    serializer.endDocument();
            	} else if(e == XmlPullParser.START_TAG) {
                    serializer.startTag("", xpp.getName());
            		if(depth<nowPos.length){
	            		nowPos[depth]=xpp.getName();	//タグがあれば現在地に追加
	            		if(isRightPos(parent,nowPos)){	//所望のポイントに入ったか
	            			//タグ内にデータを投げる時
                            serializer.startTag("", tagName);
                            serializer.endTag("", tagName);
            			}
        			}
            		//深さを一つ下げる
            		depth++;
            	} else if(e == XmlPullParser.END_TAG) {
            		if(depth<nowPos.length){
	            		nowPos[depth]="";	            	//タグから出たのでそのデータを消去
            		}
            		//深さを一つ上げる
            		depth--;
                    serializer.endTag("", xpp.getName());
            	} else if(e == XmlPullParser.TEXT) {
            		serializer.text(xpp.getText());
            	}
        		e=xpp.next();
            }

            //終了処理
            writer.append(swriter.toString());
            writer.close();
            reader.close();
   		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		renameFile(sdcard+"/copy.xml", fileName);
    }

    //タグの挿入
    public void insertTag(String destination){
		try {
			String position[]=destination.split("/");
	        String[] nowPos=new String[position.length-1];	//現在の位置を取得
	        String[] parent=new String[position.length-1];	//現在の位置を取得
	        String tagName =position[position.length-1];
			for(int i=0;i<parent.length;i++){
				parent[i]=position[i];
			}
			int depth=0;

			//入力データ
	        final XmlPullParser xpp = Xml.newPullParser();
	        BufferedReader reader =new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
	        xpp.setInput(reader);

			//出力データ
	        //コピー先はcopy.xml
			BufferedWriter writer =	new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sdcard+"/copy.xml"),"UTF-8"));
	        XmlSerializer serializer = Xml.newSerializer();
	        StringWriter swriter = new StringWriter();
            serializer.setOutput(writer);

            //入力から出力へコピー
            int e=xpp.getEventType();
            while (e != XmlPullParser.END_DOCUMENT) {
            	if(e == XmlPullParser.START_DOCUMENT) {
                    serializer.startDocument("UTF-8", true);
            	} else if(e == XmlPullParser.END_DOCUMENT) {
                    serializer.endDocument();
            	} else if(e == XmlPullParser.START_TAG) {
                    serializer.startTag("", xpp.getName());
            		if(depth<nowPos.length){
	            		nowPos[depth]=xpp.getName();	//タグがあれば現在地に追加
	            		if(isRightPos(parent,nowPos)){	//所望のポイントに入ったか
	            			//タグ内にデータを投げる時
                            serializer.startTag("", tagName);
                            serializer.endTag("", tagName);
            			}
        			}
            		//深さを一つ下げる
            		depth++;
            	} else if(e == XmlPullParser.END_TAG) {
            		if(depth<nowPos.length){
	            		nowPos[depth]="";	            	//タグから出たのでそのデータを消去
            		}
            		//深さを一つ上げる
            		depth--;
                    serializer.endTag("", xpp.getName());
            	} else if(e == XmlPullParser.TEXT) {
            		serializer.text(xpp.getText());
            	}
        		e=xpp.next();
            }

            //終了処理
            writer.append(swriter.toString());
            writer.close();
            reader.close();
   		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		renameFile(sdcard+"/copy.xml", fileName);
    }

    //タグの存在判定
    public boolean searchTag(String destination){
		//入力データ
		String position[]=destination.split("/");
        final XmlPullParser xpp = Xml.newPullParser();
        BufferedReader reader;

        String[] nowPos=new String[position.length];	//現在の位置を取得
		int depth=0;

		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
	        xpp.setInput(reader);

            int e=xpp.getEventType();
            while (e != XmlPullParser.END_DOCUMENT) {
            	if(e == XmlPullParser.START_TAG) {
            		if(depth<nowPos.length){
	            		nowPos[depth]=xpp.getName();	//タグがあれば現在地に追加
	            		if(isRightPos(position,nowPos)){	//所望のポイントに入ったか
	            			return true;
	            		}
            		}
            		//深さを一つ下げる
            		depth++;
            	} else if(e == XmlPullParser.END_TAG) {
            		if(depth<nowPos.length){
	            		//タグから出たのでそのデータを消去
	            		nowPos[depth]="";
            		}
            		//深さを一つ上げる
            		depth--;
            	}
            	e = xpp.next();
            }

	        reader.close();
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
    	return false;
    }

    //タグの存在判定
    public boolean searchTag(String[] position){
		//入力データ
        final XmlPullParser xpp = Xml.newPullParser();
        BufferedReader reader;

        String[] nowPos=new String[position.length];	//現在の位置を取得
		int depth=0;

		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
	        xpp.setInput(reader);

            int e=xpp.getEventType();
            while (e != XmlPullParser.END_DOCUMENT) {
            	if(e == XmlPullParser.START_TAG) {
            		if(depth<nowPos.length){
	            		nowPos[depth]=xpp.getName();	//タグがあれば現在地に追加
	            		if(isRightPos(position,nowPos)){	//所望のポイントに入ったか
	            			return true;
	            		}
            		}
            		//深さを一つ下げる
            		depth++;
            	} else if(e == XmlPullParser.END_TAG) {
            		if(depth<nowPos.length){
	            		//タグから出たのでそのデータを消去
	            		nowPos[depth]="";
            		}
            		//深さを一つ上げる
            		depth--;
            	}
            	e = xpp.next();
            }

	        reader.close();
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
    	return false;
    }

    public void getData(String result[],String destination){
		String position[]=destination.split("/");
		String name=fileName;
		//入力データ
        final XmlPullParser xpp = Xml.newPullParser();
        BufferedReader reader;

        String[] nowPos=new String[position.length];	//現在の位置を取得
		int depth=0;
		int counter=0;

		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(name)));
	        xpp.setInput(reader);

            int e=xpp.getEventType();
            while (e != XmlPullParser.END_DOCUMENT) {
            	if(e == XmlPullParser.START_TAG) {
            		if(depth<nowPos.length){
	            		nowPos[depth]=xpp.getName();	//タグがあれば現在地に追加
	            		if(isRightPos(position,nowPos)){	//所望のポイントに入ったか
            				e=xpp.next();
            				if(e == XmlPullParser.TEXT){
    	            			counter++;
            				}
	            		}
            		}
            		//深さを一つ下げる
            		depth++;
            	} else if(e == XmlPullParser.END_TAG) {
            		if(depth<nowPos.length){
	            		//タグから出たのでそのデータを消去
	            		nowPos[depth]="";
            		}
            		//深さを一つ上げる
            		depth--;
            	}
            	e = xpp.next();
            }
	        reader.close();

	        result=new String[counter];
	        counter=0;
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(name)));
	        xpp.setInput(reader);
            e=xpp.getEventType();
            while (e != XmlPullParser.END_DOCUMENT) {
            	if(e == XmlPullParser.START_TAG) {
            		if(depth<nowPos.length){
	            		nowPos[depth]=xpp.getName();	//タグがあれば現在地に追加
	            		if(isRightPos(position,nowPos)){	//所望のポイントに入ったか
            				e=xpp.next();
            				if(e == XmlPullParser.TEXT){
            					result[counter]=xpp.getText();
    	            			counter++;
            				}
	            		}
            		}
            		//深さを一つ下げる
            		depth++;
            	} else if(e == XmlPullParser.END_TAG) {
            		if(depth<nowPos.length){
	            		//タグから出たのでそのデータを消去
	            		nowPos[depth]="";
            		}
            		//深さを一つ上げる
            		depth--;
            	}
            	e = xpp.next();
            }
	        reader.close();

		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
    }

    //データの取得
    public String getData(String destination){
		String position[]=destination.split("/");
		String name=fileName;
		//入力データ
        final XmlPullParser xpp = Xml.newPullParser();
        BufferedReader reader;

        String[] nowPos=new String[position.length];	//現在の位置を取得
		String retString="----";			//返り値
		int depth=0;

		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(name)));
	        xpp.setInput(reader);

            int e=xpp.getEventType();
            while (e != XmlPullParser.END_DOCUMENT) {
            	if(e == XmlPullParser.START_TAG) {
            		if(depth<nowPos.length){
	            		nowPos[depth]=xpp.getName();	//タグがあれば現在地に追加
	            		if(isRightPos(position,nowPos)){	//所望のポイントに入ったか
            				e=xpp.next();
            				if(e == XmlPullParser.TEXT){
    	            			return xpp.getText();
            				}
            				else{
            					return "----";
            				}
	            		}
            		}
            		//深さを一つ下げる
            		depth++;
            	} else if(e == XmlPullParser.END_TAG) {
            		if(depth<nowPos.length){
	            		//タグから出たのでそのデータを消去
	            		nowPos[depth]="";
            		}
            		//深さを一つ上げる
            		depth--;
            	}
            	e = xpp.next();
            }

	        reader.close();
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
    	return retString;
    }

    //データの取得
    public String[] getTableData(String destination){
		String position[]=destination.split("/");
		String name=fileName;
		ArrayList<String> outputBox=new ArrayList<String>();
		String[] output;
		//入力データ
        final XmlPullParser xpp = Xml.newPullParser();
        BufferedReader reader;

        String[] nowPos=new String[position.length];	//現在の位置を取得
		String retString="----";			//返り値
		int depth=0;

		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(name)));
	        xpp.setInput(reader);

            int e=xpp.getEventType();
            while (e != XmlPullParser.END_DOCUMENT) {
            	if(e == XmlPullParser.START_TAG) {
            		if(depth<nowPos.length){
	            		nowPos[depth]=xpp.getName();	//タグがあれば現在地に追加
	            		if(isRightPos(position,nowPos)){	//所望のポイントに入ったか
            				e=xpp.next();
            				if(e == XmlPullParser.TEXT){
            					outputBox.add(xpp.getText());
            				}
            				else{
            					outputBox.add("----");
            				}
	            		}
            		}
            		//深さを一つ下げる
            		depth++;
            	} else if(e == XmlPullParser.END_TAG) {
            		if(depth<nowPos.length){
	            		//タグから出たのでそのデータを消去
	            		nowPos[depth]="";
            		}
            		//深さを一つ上げる
            		depth--;
            	}
            	e = xpp.next();
            }

	        reader.close();
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		//ArrayListからString配列に変換
		output=new String[outputBox.size()];
		for(int i=0;i<output.length;i++){
			output[i]=outputBox.get(i);
		}
		
    	return output;
    }

    //所望の位置に来ているかどうかを確認
    public boolean isRightPos(String[] position, String[] nowPos) {
		for(int i=0;i<nowPos.length;i++){
			if(!position[i].equals(nowPos[i])){
				return false;
			}
		}
		return true;
	}

    //ファイルの名前変更(上書きするときに使う)
	public void renameFile(String name,String afterName){
        java.io.File delFile =new File(afterName);
        delFile.delete();
        File beforeFile =new File(name);
        if(!beforeFile.exists()){
        	return;
        }
        File afterFile =new File(afterName);
        beforeFile.renameTo(afterFile);
    }


    public LinearLayout.LayoutParams createParam(int w, int h){
        return new LinearLayout.LayoutParams(w, h);
    }
}
