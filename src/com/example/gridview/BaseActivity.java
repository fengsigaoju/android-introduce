package com.example.gridview;
import android.app.Activity;
import android.os.Bundle;
public class BaseActivity extends Activity{//作为所有活动的父类,相当于增加了活动管理的activity（为什么谷歌自己不完善...）
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		ActivityCollector.addActivity(this);
	}
	@Override
	protected void onDestroy(){
		super.onDestroy();
		ActivityCollector.removeActivity(this);
	}

}
