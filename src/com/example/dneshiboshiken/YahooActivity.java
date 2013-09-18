//ヤフー知恵袋機能

package com.example.dneshiboshiken;

import java.lang.reflect.Field;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.dneshiboshiken.R.id;

public class YahooActivity extends Activity implements OnClickListener {

	// 宣言
	private Button button_back;
	private Button button_forward;
	private Button button_home;

	private WebView webView;
	private Activity activity = this;

	// 一定時間について
	private Handler handler = new Handler();
	private final Runnable visi = new Runnable() {
		@Override
		public void run() {
			button_back.setVisibility(View.VISIBLE);
			button_forward.setVisibility(View.VISIBLE);
			button_home.setVisibility(View.VISIBLE);
		}
	};

	// ボタン非表示
	private final void invisi() {
		button_back.setVisibility(View.INVISIBLE);
		button_forward.setVisibility(View.INVISIBLE);
		button_home.setVisibility(View.INVISIBLE);
	}

	// タッチイベントについて
	// スクロールされていたらボタンを表示しない
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		invisi();
		handler.postDelayed(visi, 2000);
		return true;

	}

	// 履歴保存について
	private String[] url_history = new String[100];// 履歴のURLを保存する配列
	private int url_count = 0;// 今表示しているURLのurl_historyにおける要素番号
	private boolean check = true;// タップして移動したらtrue,戻る進むボタンで移動したらfalse
	private int max = 0;// url_historyの要素数

	private boolean first = true;// 画面回転後falseになる

	// ロード中に画面回転が起こった場合のため
	private boolean loading = false;
	private String load_url = "";

	// メイン
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// プログレスバー表示
		requestWindowFeature(Window.FEATURE_PROGRESS);

		setContentView(R.layout.yahoo_activity);

		button_back = (Button) findViewById(id.button_back);
		button_forward = (Button) findViewById(id.button_forward);
		button_home = (Button) findViewById(id.button_home);

		button_back.setOnClickListener(this);
		button_forward.setOnClickListener(this);
		button_home.setOnClickListener(this);

		webView = (WebView) findViewById(id.webView);

		// Flash
		// webView.getSettings().setPluginsEnabled( true );

		// ズーム,ピンチイン・ピンチアウトによるズーム操作は可能で、ズームのボタンは表示されない

		webView.getSettings().setBuiltInZoomControls(true);
		webView.getSettings().setSupportZoom(true);
		try {
			Field field = webView.getSettings().getClass()
					.getDeclaredField("mBuiltInZoomControls");
			field.setAccessible(true);
			field.set(webView.getSettings(), false);
		} catch (Exception e) {
			e.printStackTrace();
			webView.getSettings().setBuiltInZoomControls(false);
		}

		// キャッシュ消去
		// webView.clearCache();

		// JavaScript
		webView.getSettings().setJavaScriptEnabled(true);

		// UserAgent設定
		// webView.getSettings().setUserAgentString("文字列");

		webView.setWebViewClient(new WebViewClient() {
			// 読み込み開始時
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				loading = true;
				load_url = url;
				activity.setTitle("Loading...");
				activity.setProgressBarVisibility(true);
			}

			// 読み込み終了時
			@Override
			public void onPageFinished(WebView view, String url) {
				if (!(url_history[url_count].equals(url))) {
					if (check) {
						url_count++;
						url_history[url_count] = url;
						max = url_count;
						loading = false;
					}
				}
				check = true;
			}
		});

		// プログレスバーについて
		webView.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {
				activity.setProgress(progress * 100);
				if (progress == 100) {
					activity.setProgressBarVisibility(false);
					activity.setTitle(webView.getTitle());
				}
			}
		});

		if (first) {
			check = false;
			webView.loadUrl("http://m.chiebukuro.yahoo.co.jp/list/category.php?did=2078297879&depth=3");
			url_history[0] = "http://m.chiebukuro.yahoo.co.jp/list/category.php?did=2078297879&depth=3";
		}
	}

	// 戻る進むボタンが押された時
	public void onClick(View v) {
		if (v == button_back) {
			canGoBackForward(-1);
		} else if (v == button_forward) {
			canGoBackForward(1);
		} else if (v == button_home) {
			webView.loadUrl("http://m.chiebukuro.yahoo.co.jp/list/category.php?did=2078297879&depth=3");
		}
	}

	// 引数によって戻るか進む
	public void canGoBackForward(int dir) {
		int tmp = url_count + dir;
		if (tmp >= 0 && tmp <= max) {
			check = false;
			webView.loadUrl(url_history[tmp]);
			url_count = tmp;
		}
	}

	// 端末の戻るボタンで戻る
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (url_count == 0
					|| load_url
							.equals("http://m.chiebukuro.yahoo.co.jp/list/category.php?did=2078297879&depth=3")
					|| webView
							.getUrl()
							.equals("http://m.chiebukuro.yahoo.co.jp/list/category.php?did=2078297879&depth=3")) {
				//ダイアログ表示の処理
				/*
				 * AlertDialog.Builder alert = new AlertDialog.Builder(this);
				 * alert.setMessage("WebViewを終了しますか");
				 * alert.setCancelable(false); alert.setPositiveButton("はい", new
				 * DialogInterface.OnClickListener() { public void
				 * onClick(DialogInterface dialog, int id) {
				 * IndexActivity.this.finish(); } });
				 * alert.setNegativeButton("いいえ", new
				 * DialogInterface.OnClickListener() { public void
				 * onClick(DialogInterface dialog, int id) { dialog.cancel(); }
				 * }); alert.show();
				 */
				YahooActivity.this.finish();
			} else {
				canGoBackForward(-1);
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	// 画面回転時に保存
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean("LOAD", loading);
		outState.putString("LOAD_URL", load_url);
		outState.putStringArray("HISTORY", url_history);
		outState.putInt("COUNT", url_count);
		outState.putInt("MAX", max);
		outState.putBoolean("CHECK", check);
	}

	// 画面回転時に保存したデータを読み出す
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		max = savedInstanceState.getInt("MAX");
		url_count = savedInstanceState.getInt("COUNT");
		loading = savedInstanceState.getBoolean("LOAD");
		load_url = savedInstanceState.getString("LOAD_URL");
		check = savedInstanceState.getBoolean("CHECK");
		if (first)
			first = false;
		url_history = savedInstanceState.getStringArray("HISTORY");

		if (loading) {
			webView.loadUrl(load_url);
		} else {
			check = false;
			webView.loadUrl(url_history[url_count]);
		}
	}

}