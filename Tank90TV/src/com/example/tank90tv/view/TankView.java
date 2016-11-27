package com.example.tank90tv.view;

 
 

import com.example.tank90tv.R;
import com.example.tank90tv.map.ParseMap;
import com.example.tank90tv.pojo.Tank;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TankView extends SurfaceView implements SurfaceHolder.Callback{

	 
	private Bitmap tankBMP;
	private Tank tank;
	private DrawThread drawThread = null;
	private Bitmap backBMP;
	 

	public TankView(Context context, LinearLayout view,Tank tank,ParseMap pm) {
		super(context);
		getHolder().addCallback(this);
		// TODO Auto-generated constructor stub
		tankBMP = BitmapFactory.decodeResource(getResources(), R.drawable.tank_top_0);
		backBMP = pm.getBackgroundBMP();
		if(backBMP==null){
			this.backBMP = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
		}		
		
		this.tank = tank;
		this.tank.bitmap = this.tankBMP;
		if(this.drawThread==null)
			drawThread = new DrawThread(getHolder(),this);
		view.removeAllViews();
		view.addView(this);
	}
	
	@Override
	protected void onDraw(Canvas canvas){
		
	}

	
	public void drawTank(Canvas canvas){
//		canvas.drawBitmap(tankBMP, 0, 0, null);
		RectF rectF= new RectF(0,0,this.tank.displaySize[0],this.tank.displaySize[1]);
		canvas.drawBitmap(backBMP, null, rectF, null);
		 
		this.tank.drawSelf(canvas);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		if(this.drawThread!=null&&!this.drawThread.isAlive()){
			
			this.drawThread.start();
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		if(this.drawThread!=null){
			this.drawThread.drawFlag = false;
			this.drawThread = null;
		}
	}

}
