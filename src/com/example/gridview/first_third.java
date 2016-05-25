package com.example.gridview;

import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;
public class first_third extends BaseActivity {
	  private SensorManager sensorManager;
	    int s;
	    String t;
		@Override
	 protected void onCreate(Bundle savedInstanceState){
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.first_third_layout);
		 Date date=new Date();//分别得到当前的时分秒
	     String second = String.format("%tS",date);
	 	 s= Integer.parseInt(second);
	 	 Intent intent = getIntent();
	     t=intent.getStringExtra("extra_data");
	 	 Toast.makeText(first_third.this, "我的高中", Toast.LENGTH_SHORT).show();
		 sensorManager =(SensorManager) getSystemService(Context.SENSOR_SERVICE);
		 Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		 sensorManager.registerListener(listener, sensor,SensorManager.SENSOR_DELAY_NORMAL);
		}  
		@Override
	    protected void onDestroy(){
		super.onDestroy();
		if (sensorManager!=null){
			sensorManager.unregisterListener(listener);
		}
	}
		private SensorEventListener listener = new SensorEventListener(){
			@Override
			public void onSensorChanged(SensorEvent event){
				Date date=new Date();//分别得到当前的时分秒
			    String second = String.format("%tS",date);
			 	int  ss= Integer.parseInt(second);
				float xValue=Math.abs(event.values[0]);
				float yValue=Math.abs(event.values[1]);
				float zValue=Math.abs(event.values[2]);
				if ((xValue>15||yValue>15||zValue>15)&&(ss-s>=2)){
					s=ss;
					Intent intent=new Intent(first_third.this,MainActivity.class);
					intent.putExtra("extra_data",t);//把上一级的是否开启音乐传过来
					startActivity(intent);	
			}
			
	}
			@Override
			public void onAccuracyChanged(Sensor sensor,int accuracy){
			}  
			};
	}

