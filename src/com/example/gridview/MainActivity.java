package com.example.gridview;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.os.Build;
public class MainActivity extends BaseActivity implements OnItemClickListener{
    private GridView gridView;
    private SimpleAdapter adapter;
	private boolean MusicSwitch;//判断音乐是否开启
	String s;
	int[] drawable = {R.drawable.messenger, R.drawable.address_book, 
    R.drawable.world, R.drawable.guanzhu,R.drawable.yinyue,R.drawable.exit};
	String[] iconName = { "成长历程", "兴趣爱好", "我的博客", "一键关注","音乐","退出"};
	private List<Map<String, Object>> dataList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.main);
		gridView=(GridView) findViewById(R.id.gridView);
        dataList=new ArrayList<Map<String,Object>>();
        adapter=new SimpleAdapter(this, getData(), R.layout.item, new String[]{"pic","name"}, new int[]{R.id.pic,R.id.name});
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(MainActivity.this);
        Intent intent=getIntent();
	    s=intent.getStringExtra("extra_data");//获得上一次的是否开启音乐的值  
        if (s.equals("1")) MusicSwitch=true;  
        else MusicSwitch=false; 
        if (MusicSwitch) {  
            Intent intentSV=new Intent(MainActivity.this,Myservice.class);  
            startService(intentSV); 
           } 
        }
    private List<Map<String, Object>> getData() {
    	for (int i = 0; i < drawable.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pic", drawable[i]);
			map.put("name", iconName[i]);
            dataList.add(map);
		}
		Log.i("Main", "size="+dataList.size());
		return dataList;
	}
    public void onItemClick(AdapterView<?>parent,View view,int position,long id){//监听器
    	switch(position)
    	{
    	case 0://成长历程（摇一摇翻页)
    		if (MusicSwitch)//为了返回回来时知道是否音乐在播放
			{
			Intent intent=new Intent(MainActivity.this,FirstActivity.class);
			intent.putExtra("extra_data","1");
			startActivity(intent);		
			}
			else
			{
				Intent intent=new Intent(MainActivity.this,FirstActivity.class);
				intent.putExtra("extra_data","0");
				startActivity(intent);		
			}
		break;
    	case 1://兴趣爱好
    		Intent intent=new Intent(MainActivity.this,secondactivity.class);
    		if (MusicSwitch) {
    			MusicSwitch=false;
			    Intent intentSV=new Intent(MainActivity.this,Myservice.class);
				stopService(intentSV);
			}//在跳转到视频前要关掉背景音乐
		   startActivity(intent);
		break;
    	case 2://我的博客
    		Intent intent2=new Intent(MainActivity.this,thirdactivity.class);
    	    startActivity(intent2);	
    	break;
    	case 3://一键关注
        break;
    	case 4://音乐
    		  if (MusicSwitch) {  
    			  MusicSwitch=false;
    			  Intent intentSV=new Intent(MainActivity.this,Myservice.class);  
                  stopService(intentSV);  
                  Toast.makeText(MainActivity.this, "音乐已关闭", Toast.LENGTH_SHORT).show();
              }  
              else    {  
                  MusicSwitch = true;  
                  try{  
                      Intent intentSV=new Intent(MainActivity.this,Myservice.class);  
                      startService(intentSV); 
                      Toast.makeText(MainActivity.this, "音乐已开启", Toast.LENGTH_SHORT).show();
                  }catch(Exception e){  
                      e.printStackTrace();    
                  }  
                  }  
        break;
    	case 5://退出
    		 Intent intentSV=new Intent(MainActivity.this,Myservice.class);  
             stopService(intentSV); 
             ActivityCollector.finishAll();//运用自己建的活动管理器全部退出
             android.os.Process.killProcess(android.os.Process.myPid());
    		 System.exit(0);
    	break;
    	}
    }
}
