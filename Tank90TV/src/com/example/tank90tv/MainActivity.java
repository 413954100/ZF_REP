package com.example.tank90tv;

 
 
import com.example.tank90tv.map.ParseMap;
import com.example.tank90tv.pojo.Tank;
import com.example.tank90tv.socketServers.SocketServer;
import com.example.tank90tv.util.MachineInfo;
import com.example.tank90tv.util.ToolUtil;
import com.example.tank90tv.view.TankView;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.DisplayMetrics; 
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private LinearLayout gameView;
	 
	private int[]displaySize = new int[2];
	private DirecMoni direcMoni;
	private TextView orientationText = null;
	private Tank tank;
	private TankView tankView = null;
	private ParseMap pm = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SocketServer ss = new  SocketServer();
		Toast.makeText(this, MachineInfo.getWifiIpAddress(this), Toast.LENGTH_LONG).show();

		ToolUtil.openAdbd(this.getPackageCodePath());

		gameView = (LinearLayout) findViewById(R.id.tankView);
		orientationText = (TextView) findViewById(R.id.orientation);
		direcMoni = new DirecMoni(this,orientationText);
		direcMoni.initSensor();
		DisplayMetrics dm = getResources().getDisplayMetrics();
		float xdpi = dm.xdpi;
		float ydpi = dm.ydpi;
		 
		displaySize[0] = dm.widthPixels;
		displaySize[1] = dm.heightPixels; 
		tank = new Tank(700, 400, null, direcMoni, displaySize);
		 
		pm = new ParseMap(this);
		pm.initMap();
		Toast.makeText(this, "oncreate()--", Toast.LENGTH_SHORT).show(); 
	}
	
	@Override
	protected void onResume(){
		if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
		  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		}
		super.onResume();
		this.tankView = new TankView(this, gameView, tank,this.pm);
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
		this.tank.tankEngine.setTankFlag(false);
	}
	
}
