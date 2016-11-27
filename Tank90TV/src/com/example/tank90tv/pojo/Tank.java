package com.example.tank90tv.pojo;

 

import com.example.tank90tv.DirecMoni;
import com.example.tank90tv.moudle.TankEngine;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Tank implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8035678486942214407L;

	public static final int SLEEP_TIME = 20;
	public static float SPEED = 0.25f; //速度
	public float startX = 0;//起始坐标
	public float startY = 0;//起始坐标
	
	public float x = 0;//当前坐标
	public float y = 0;//当前坐标
	
	public  Bitmap bitmap = null;
	public  double runTime;			//运动时间 
	public TankEngine tankEngine;
	public int[]displaySize;
	public DirecMoni direcMoni;
	
	public Tank(float x,float y,Bitmap bitmap,DirecMoni direcMoni,int[]displaySize){
		this.startX = x;
		this.startY = y;
		this.x = x;
		this.y = y;
		this.bitmap = bitmap; 
		this.displaySize = displaySize;
		this.direcMoni = direcMoni;
		tankEngine = new TankEngine(this, this.direcMoni, this.displaySize);
		tankEngine.start();
	}
	
	public void drawSelf(Canvas canvas){
//		System.out.println(canvas+"x:"+x+"||||||y:"+y);
		if(null!=canvas){
			canvas.drawBitmap(this.bitmap, this.x, this.y, null);
		}
	}
	
	
	 
	
	
	
}
