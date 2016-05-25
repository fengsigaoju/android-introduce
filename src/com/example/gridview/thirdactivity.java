package com.example.gridview;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
public class thirdactivity extends BaseActivity{
	private WebView webView;
	protected void onCreate(Bundle savedInstanceState){
		 super.onCreate(savedInstanceState);
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
		 setContentView(R.layout.third_layout);
		 webView = (WebView)findViewById(R.id.web_view);
	     webView.setWebViewClient(new WebViewClient(){
			 @Override
			 public boolean shouldOverrideUrlLoading(WebView view ,String url){
			     view.loadUrl(url);
				 return true;
			 }
		 } );
	     ConnectivityManager connectionManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		 NetworkInfo networkInfo=connectionManager.getActiveNetworkInfo();
		 if ((networkInfo!=null)&&networkInfo.isAvailable()){
			 Toast.makeText(thirdactivity.this,"网络可用  ",Toast.LENGTH_SHORT).show();
		 }
		 else
		 {
			 Toast.makeText(thirdactivity.this, "网络不可用", Toast.LENGTH_SHORT).show();
			 Intent wifiSettingsIntent = new Intent("android.settings.WIFI_SETTINGS");   
			 startActivity(wifiSettingsIntent);  
		 }
		webView.loadUrl("http://blog.csdn.net/fengsigaoju"); 
   }
  }
