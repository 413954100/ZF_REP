package com.example.tank90tv;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

public class MyApplication extends Application {

	private static Context context;
	
	@Override
	public void onCreate(){
		context = getApplicationContext();
	}
	
	public static Context getContext(){
		return context;
	}
	public static int[]getScreenSize(){
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		int[]displaySize = {dm.widthPixels,dm.heightPixels}; 
		return displaySize;
	}
}
