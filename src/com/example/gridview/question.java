package com.example.gridview;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ImageView;
public class question extends BaseActivity{
    @Override
	protected void onCreate(Bundle savedInstanceState) {
	 super.onCreate(savedInstanceState);
	 setContentView(R.layout.layout_question);
	  //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	  AlertDialog.Builder dialog = new AlertDialog.Builder(question.this);//弹出对话框，判断是否需要开启音乐
      dialog.setTitle("注意");
      dialog.setMessage("是否播放音乐");
      dialog.setCancelable(false);
      dialog.setPositiveButton("否",new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
			  Intent intent = new Intent(question.this,MainActivity.class);
		      intent.putExtra("extra_data","0");//向MainActivity传递值
		      startActivity(intent);
			 }
		});
      dialog.setNegativeButton("是",new DialogInterface.OnClickListener(){
    	   @Override
			public void onClick(DialogInterface arg0, int arg1) {
			  Intent intent = new Intent(question.this,MainActivity.class);
		      intent.putExtra("extra_data","1");//向MainActivity传递值
		      startActivity(intent);
			 }
      });
     dialog.show();
 }
    @Override
    protected void onDestroy(){
    	super.onDestroy();
    	finish();
    }
}