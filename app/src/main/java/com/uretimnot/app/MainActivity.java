package com.uretimnot.app;
import android.app.*;
import android.os.*;
import android.Manifest;
import android.content.*;
import android.content.pm.PackageManager;
import android.webkit.*;
import android.view.*;

public class MainActivity extends Activity {
 WebView web;
 @Override public void onCreate(Bundle state){super.onCreate(state);
  web=new WebView(this);setContentView(web);
  web.getSettings().setJavaScriptEnabled(true);
  web.getSettings().setDomStorageEnabled(true);
  web.addJavascriptInterface(new Bridge(),"AndroidBridge");
  web.setWebViewClient(new WebViewClient());
  web.loadUrl("file:///android_asset/index.html");
  ReminderReceiver.schedule(this);
  if(Build.VERSION.SDK_INT>=33 && checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)!=PackageManager.PERMISSION_GRANTED){requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS},100);}
 }
 class Bridge { @JavascriptInterface public void saveTasks(String json){getSharedPreferences("uretimnot",MODE_PRIVATE).edit().putString("tasks",json).apply();} }
 @Override public void onBackPressed(){if(web.canGoBack())web.goBack();else super.onBackPressed();}
}
