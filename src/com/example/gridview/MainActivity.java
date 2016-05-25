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
	private boolean MusicSwitch;//�ж������Ƿ���
	String s;
	int[] drawable = {R.drawable.messenger, R.drawable.address_book, 
    R.drawable.world, R.drawable.guanzhu,R.drawable.yinyue,R.drawable.exit};
	String[] iconName = { "�ɳ�����", "��Ȥ����", "�ҵĲ���", "һ����ע","����","�˳�"};
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
	    s=intent.getStringExtra("extra_data");//�����һ�ε��Ƿ������ֵ�ֵ  
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
    public void onItemClick(AdapterView<?>parent,View view,int position,long id){//������
    	switch(position)
    	{
    	case 0://�ɳ����̣�ҡһҡ��ҳ)
    		if (MusicSwitch)//Ϊ�˷��ػ���ʱ֪���Ƿ������ڲ���
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
    	case 1://��Ȥ����
    		Intent intent=new Intent(MainActivity.this,secondactivity.class);
    		if (MusicSwitch) {
    			MusicSwitch=false;
			    Intent intentSV=new Intent(MainActivity.this,Myservice.class);
				stopService(intentSV);
			}//����ת����ƵǰҪ�ص���������
		   startActivity(intent);
		break;
    	case 2://�ҵĲ���
    		Intent intent2=new Intent(MainActivity.this,thirdactivity.class);
    	    startActivity(intent2);	
    	break;
    	case 3://һ����ע
        break;
    	case 4://����
    		  if (MusicSwitch) {  
    			  MusicSwitch=false;
    			  Intent intentSV=new Intent(MainActivity.this,Myservice.class);  
                  stopService(intentSV);  
                  Toast.makeText(MainActivity.this, "�����ѹر�", Toast.LENGTH_SHORT).show();
              }  
              else    {  
                  MusicSwitch = true;  
                  try{  
                      Intent intentSV=new Intent(MainActivity.this,Myservice.class);  
                      startService(intentSV); 
                      Toast.makeText(MainActivity.this, "�����ѿ���", Toast.LENGTH_SHORT).show();
                  }catch(Exception e){  
                      e.printStackTrace();    
                  }  
                  }  
        break;
    	case 5://�˳�
    		 Intent intentSV=new Intent(MainActivity.this,Myservice.class);  
             stopService(intentSV); 
             ActivityCollector.finishAll();//�����Լ����Ļ������ȫ���˳�
             android.os.Process.killProcess(android.os.Process.myPid());
    		 System.exit(0);
    	break;
    	}
    }
}
