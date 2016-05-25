package com.example.gridview;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.VideoView;
public class secondactivity extends BaseActivity{
	private VideoView video;
	private Button play;
	private Button pause;
	private Button replay;
	protected void onCreate(Bundle savedInstanceState){
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.second_layout);
		 video=(VideoView)findViewById(R.id.video_view);
		 video.setVideoPath("android.resource://com.example.gridview/"+R.raw.movie);//这里中间（b1404030312）换成自己的包名 
		 play=(Button)findViewById(R.id.play);
		 play.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
				 if (!video.isPlaying())
					 video.start();
				}
			});
	     pause=(Button)findViewById(R.id.pause);
	     pause.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
				 if (video.isPlaying())
					 video.pause();
				}
			});
		 replay=(Button)findViewById(R.id.replay);
		 replay.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
				 if (video.isPlaying())
					 video.resume();
				}
			});
		
	}
	
	 @Override
    protected void onDestroy()
	{
		super.onDestroy();
	    if (video!=null)
	    	video.suspend();
	}
}
	
	