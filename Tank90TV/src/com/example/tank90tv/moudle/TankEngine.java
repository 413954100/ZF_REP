package com.example.tank90tv.moudle;

import com.example.tank90tv.DirecMoni;
import com.example.tank90tv.MyApplication;
import com.example.tank90tv.R;
import com.example.tank90tv.map.ParseMap;
import com.example.tank90tv.pojo.Tank;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

public class TankEngine extends Thread{

	private Tank tank;
	private boolean tankFlag = true; 
	private DirecMoni
	direcMoni = null;
	public  int screenWidth = 0;
	public  int screenHeight = 0; 
	private double runTime;
	private double currentTime;  
	private int bmWidth = 0;
	private int bmHeight = 0;
	
	public TankEngine(Tank tank,DirecMoni direcMoni,int[] displaySize){
		this.tank = tank; 
		this.direcMoni = direcMoni;
		this.screenWidth = displaySize[0]*5/6;
		this.screenHeight = displaySize[1]*5/6;
		this.bmWidth = displaySize[0]/ParseMap.MAP[0].length;
		this.bmHeight = displaySize[1]/ParseMap.MAP.length;
	}
 
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(tankFlag){ 
			currentTime = System.nanoTime();
			runTime = (currentTime - tank.runTime)/1000/1000;  
			int xCoord = (int)tank.x/this.bmWidth;
			int yCoord = (int)tank.y/this.bmHeight;
			tank.runTime = System.nanoTime();
			switch(this.direcMoni.getControlDirec()){
				case DirecMoni.GO_STOP:
					break;
				case DirecMoni.GO_LEFT:
					if(tank.x<=0){
						tank.x = 0;
					}else{
						xCoord = (int)(tank.x-this.bmWidth)/this.bmWidth;
						if(ParseMap.MAP[xCoord][yCoord]==0){
							tank.x = (float) (tank.startX - runTime*Tank.SPEED);
						}else{
							tank.x = (float) (xCoord + 1) * this.bmWidth +1;
						}
					}
					tank.startX = tank.x;
					this.tank.bitmap = BitmapFactory.decodeResource(
							MyApplication.getContext().getResources(), R.drawable.tank_left_0);
					break;
				case DirecMoni.GO_RIGHT:
					if(tank.x>=this.screenWidth)
						tank.x = this.screenWidth;
					else{ 
						xCoord = (int)tank.x/this.bmWidth;
						if(ParseMap.MAP[xCoord+1][yCoord]==0){
							tank.x = (float) (tank.startX + runTime*Tank.SPEED);
						}
//						else{
//							tank.x = (xCoord-1)*this.bmWidth;
//						}
					}
					tank.startX = tank.x;
					this.tank.bitmap = BitmapFactory.decodeResource(
							MyApplication.getContext().getResources(), R.drawable.tank_right_0);
					break;
				case DirecMoni.GO_UP:
					if(tank.y<=0)
						tank.y = 0;
					else{
						yCoord = (int)tank.y/this.bmHeight;
//						System.out.println(xCoord+"==="+yCoord);
						if(ParseMap.MAP[xCoord][yCoord]==0){
							tank.y = (float) (tank.startY - runTime*Tank.SPEED);
						}
//						else{
//							tank.y = (yCoord+1)*this.bmHeight;
//						}
					}
					tank.startY = tank.y;
					this.tank.bitmap = BitmapFactory.decodeResource(
							MyApplication.getContext().getResources(), R.drawable.tank_top_0);
					break;
				case DirecMoni.GO_DOWN:
					if(tank.y>=this.screenHeight)
						tank.y = this.screenHeight;
					else{
						yCoord = (int)tank.y/this.bmHeight;
						if(ParseMap.MAP[xCoord][yCoord+1]==0){
							tank.y = (float) (tank.startY + runTime*Tank.SPEED);
						}
//						else{
//							tank.y = (yCoord-1)*this.bmHeight;
//						}
					}
					tank.startY = tank.y;
					this.tank.bitmap = BitmapFactory.decodeResource(
							MyApplication.getContext().getResources(), R.drawable.tank_buttom_0);
					break;
				default:
					break;
			}
			try {
				sleep(Tank.SLEEP_TIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean isTankFlag() {
		return tankFlag;
	}

	public void setTankFlag(boolean tankFlag) {
		this.tankFlag = tankFlag;
	}

}
