package com.example.tank90tv;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

public class DirecMoni   {

	public static final int GO_STOP = 0;
	public static final int GO_LEFT = 1;
	public static final int GO_RIGHT = 2;
	public static final int GO_UP = 3;
	public static final int GO_DOWN = 4;
	
	private SensorManager sensorManager;
	private Context context;
	
	private int controlDirec = -1;
	private TextView orientationText;

	public DirecMoni(Context context,TextView orientationText){
		this.context = context;
		this.orientationText = orientationText;
	}

	
	public void initSensor(){
		sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		Sensor magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorManager.registerListener(sensorListener, magneticSensor, SensorManager.SENSOR_DELAY_GAME);
		sensorManager.registerListener(sensorListener, accelerometerSensor, SensorManager.SENSOR_DELAY_GAME);
	}
	
	public void resetSensor(){
		if(sensorManager!=null){
			sensorManager.unregisterListener(sensorListener);
		}
	}
	
	private SensorEventListener sensorListener = new SensorEventListener() {
		
		private float magneticValue[] = new float[3];
		private float accelerometerValue[] = new float[3];
		@Override
		public void onSensorChanged(SensorEvent event) {
			// TODO Auto-generated method stub
			if(event.sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD){
				magneticValue = event.values.clone();
			}else if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
				accelerometerValue = event.values.clone();
			}
			float []R = new float[9];
			float []values = new float[3];
			SensorManager.getRotationMatrix(R, null, accelerometerValue, magneticValue);
			SensorManager.getOrientation(R, values);
			float x = ((float) Math.round(values[1]*100))/100;
			float y = ((float) Math.round(values[2]*100))/100;
			int status = -1;
			float xWeight = 0;
			float yWeight = 0;
			if(x<-0.25f){
				xWeight = Math.abs(x + 0.25f);//-055
				status = GO_RIGHT;
			}else if(x>0.25f){
				xWeight = Math.abs(x - 0.25f);
				status = GO_LEFT;
			}else{
				status = GO_STOP;
			}
			if(y<-0.40f){//064
				yWeight = Math.abs(y + 0.4f);
				if(yWeight>xWeight)
				status = GO_DOWN;
			}else if(y>0.00f){
				yWeight = Math.abs(y - 0.0f);
				if(yWeight>xWeight)
				status = GO_UP;
			}
			String strr = "";
			switch(status){
				case 0:
					strr = "停";
					break;
				case GO_LEFT:
					strr = "左";		
					break;
				case GO_RIGHT:
					strr = "右";
					break;
				case GO_UP:
					strr = "上";
					break;
				case GO_DOWN:
					strr = "下";
					break;
			}
			orientationText.setText(strr+"=>X:"+x+"Y:"+y);
			controlDirec = status;
		}
		
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
			
		}
	};



	public int getControlDirec() {
		return controlDirec;
	}

	public void setControlDirec(int controlDirec) {
		this.controlDirec = controlDirec;
	}
	
	
}
