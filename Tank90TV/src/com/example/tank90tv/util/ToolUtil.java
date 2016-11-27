package com.example.tank90tv.util;

import java.io.DataOutputStream;

 

import android.app.Activity;
import android.provider.Settings.Secure;
import android.util.Log;

public class ToolUtil {
	private static final String tag = "ToolUtil";
	private static int batteryLever = 0;
	
	public static boolean openAdbd(String pkgCodePath){
		Process process = null;
		DataOutputStream os = null;
		try {
			Log.d(tag, "openAdbd...");
			String cmd = "chmod -R 0777 " + pkgCodePath;
			process = Runtime.getRuntime().exec("su"); // 切换到root帐号
			os = new DataOutputStream(process.getOutputStream());
			os.writeBytes(cmd + "\n");
			os.writeBytes("stop adbd\n");
			os.writeBytes("setprop service.adb.tcp.port 5555\n");
			os.writeBytes("start adbd\n");
			os.writeBytes("exit\n");
			os.flush();
			process.waitFor();
		} catch (Exception e) {
			return false;
		} finally {
			try {
				if (os != null) {
					os.close();
				}
				process.destroy();
			} catch (Exception e) {
			}
		}
		return true;
	}
	
	public static boolean upgradedirPermission(String pkgCodePath) {
		Process process = null;
		DataOutputStream os = null;
		try {
			Log.d(tag, "upgradedirPermission = " + pkgCodePath);
			String cmd = "chmod -R 0777 " + pkgCodePath;
			process = Runtime.getRuntime().exec("su"); // 切换到root帐号
			os = new DataOutputStream(process.getOutputStream());
			os.writeBytes(cmd + "\n");
			os.writeBytes("exit\n");
			os.flush();
			process.waitFor();
		} catch (Exception e) {
			return false;
		} finally {
			try {
				if (os != null) {
					os.close();
				}
				process.destroy();
			} catch (Exception e) {
			}
		}
		return true;
	}

	public static boolean changePermission(String permission, String path) {
		Process process = null;
		DataOutputStream os = null;
		try {
			Log.d(tag, "changePermission permission: " + permission 
					+ "   path: " + path);
			String cmd = "chmod " + permission + " " + path;
			process = Runtime.getRuntime().exec("su"); // 切换到root帐号
			os = new DataOutputStream(process.getOutputStream());
			os.writeBytes(cmd + "\n");
			os.writeBytes("exit\n");
			os.flush();
			process.waitFor();
		} catch (Exception e) {
			return false;
		} finally {
			try {
				if (os != null) {
					os.close();
				}
				process.destroy();
			} catch (Exception e) {
			}
		}
		return true;
	}

	public static String getAndroidID(Activity activity) {
		String androidID;
		androidID = Secure.getString(activity.getContentResolver(), Secure.ANDROID_ID);
		Log.i(tag, "Android id is " + androidID);
		return androidID;
	}
	
	public static void setBatteryLevel(int level)  { //百分比，最大100
		Log.i(tag, "Set battery level " + level);
		batteryLever = level;
	}
	
	public static int getBatteryLevel() {
		return batteryLever;
	}
}
