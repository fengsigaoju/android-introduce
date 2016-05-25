package com.example.gridview;
import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
public class FirstActivity extends BaseActivity{
	  private SensorManager sensorManager;
	  private ImageView imageview;
	  private TextView textview;
	    int []name={R.drawable.childhood,R.drawable.primary_school,R.drawable.high_school};
	    String ch[]={"我的孩提时代","我的小学时代","我的高中时代"};
	    int s;
	    int i=0;
	    String t;
	   @Override
	 protected void onCreate(Bundle savedInstanceState){
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.first_layout);
		 Date date=new Date();//分别得到当前的时分秒
		 textview=(TextView)findViewById(R.id.view1);
		 imageview=(ImageView)findViewById(R.id.picture1);
		 String second = String.format("%tS",date);
		 s= Integer.parseInt(second);
		 Log.i("syt",second+"");
	 	 Intent intent = getIntent();
	     t=intent.getStringExtra("extra_data");
	     Toast.makeText(FirstActivity.this,"摇一摇可翻页",Toast.LENGTH_SHORT).show();
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
					Date date=new Date();
				    String secondr = String.format("%tS",date);
				 	int ss= Integer.parseInt(secondr);
			        float xValue=Math.abs(event.values[0]);
					float yValue=Math.abs(event.values[1]);
					float zValue=Math.abs(event.values[2]);
					Log.i("syttttt",secondr+"");
					if (ss<s)
					ss=ss+60;
					if ((xValue>15||yValue>15||zValue>15)&&(ss-s>=2)){
					s=ss;
					i++;
					if(i<=2)
					{
						textview.setText(ch[i]);
                        imageview.setImageBitmap(decodeSampledBitmapFromResource(getResources(),name[i], 100, 100));
                        Log.i("ysm",i+"");
                     }
					if (i>2)
					{
						Intent intent=new Intent(FirstActivity.this,MainActivity.class);
						intent.putExtra("extra_data",t);   
						startActivity(intent);	
					}
					//Intent intent=new Intent(FirstActivity.this,first_second.class);
					//intent.putExtra("extra_data",t);   
					//startActivity(intent);	
					
					
					
				}
				}
	@Override
	public void onAccuracyChanged(Sensor sensor,int accuracy){
	}  
	};
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
