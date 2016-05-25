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
public class first_second extends BaseActivity {
	  private SensorManager sensorManager;
	    int s;
	    String t;
	   @Override
	 protected void onCreate(Bundle savedInstanceState){
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.first_second_layout);
		 Date date=new Date();//�ֱ�õ���ǰ��ʱ����
	     String second = String.format("%tS",date);
	 	 s= Integer.parseInt(second);
	 	 Intent intent = getIntent();
	     t=intent.getStringExtra("extra_data");
	 	 Toast.makeText(first_second.this, "�ҵ�Сѧ", Toast.LENGTH_SHORT).show();
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
				Date date=new Date();//�ֱ�õ���ǰ��ʱ����
			    String second = String.format("%tS",date);
			    int ss= Integer.parseInt(second);
				float xValue=Math.abs(event.values[0]);
				float yValue=Math.abs(event.values[1]);
				float zValue=Math.abs(event.values[2]);
				if (xValue>15||yValue>15||zValue>15){
					    s=ss;
				        Intent intent=new Intent(first_second.this,first_third.class);
				        intent.putExtra("extra_data",t);//����һ�����Ƿ������ִ�����
				        startActivity(intent);
				      }
				
	}   

			@Override
			public void onAccuracyChanged(Sensor sensor,int accuracy){
			}  
			};
	}
