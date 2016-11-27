package com.example.tank90tv.view;

 
 

import com.example.tank90tv.pojo.Tank;

import android.graphics.Canvas; 
import android.view.SurfaceHolder;

public class DrawThread extends Thread {

	public boolean drawFlag = true;
	private SurfaceHolder surfaceHodler;
	private TankView tankView;
	
	public DrawThread(SurfaceHolder surfaceHodler,TankView tankView){
		this.surfaceHodler = surfaceHodler;
		this.tankView = tankView;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Canvas canvas = null;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(drawFlag){
			try{
				canvas = surfaceHodler.lockCanvas(); 
				synchronized(surfaceHodler){
					tankView.drawTank(canvas);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(canvas!=null){
					surfaceHodler.unlockCanvasAndPost(canvas);
				}
			}
			try {
				Thread.sleep(Tank.SLEEP_TIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
