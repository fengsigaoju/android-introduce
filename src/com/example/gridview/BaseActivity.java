package com.example.gridview;
import android.app.Activity;
import android.os.Bundle;
public class BaseActivity extends Activity{//��Ϊ���л�ĸ���,�൱�������˻�����activity��Ϊʲô�ȸ��Լ�������...��
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
