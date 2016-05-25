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
	  AlertDialog.Builder dialog = new AlertDialog.Builder(question.this);//�����Ի����ж��Ƿ���Ҫ��������
      dialog.setTitle("ע��");
      dialog.setMessage("�Ƿ񲥷�����");
      dialog.setCancelable(false);
      dialog.setPositiveButton("��",new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
			  Intent intent = new Intent(question.this,MainActivity.class);
		      intent.putExtra("extra_data","0");//��MainActivity����ֵ
		      startActivity(intent);
			 }
		});
      dialog.setNegativeButton("��",new DialogInterface.OnClickListener(){
    	   @Override
			public void onClick(DialogInterface arg0, int arg1) {
			  Intent intent = new Intent(question.this,MainActivity.class);
		      intent.putExtra("extra_data","1");//��MainActivity����ֵ
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