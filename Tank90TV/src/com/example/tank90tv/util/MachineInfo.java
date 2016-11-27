package com.example.tank90tv.util;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class MachineInfo { 

	public static String getWifiIpAddress(Context context){
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		if(!wifiManager.isWifiEnabled()){
			wifiManager.setWifiEnabled(true);
		}
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		int ipAddress = wifiInfo.getIpAddress();
		return intToIP(ipAddress);
	}
	
	private static String intToIP(int ip){
		return (ip&0xFF)+"."+
				((ip>>8)&0xFF)+"."+
				((ip>>16)&0xFF)+"."+
				((ip>>24)&0xFF);
	}
}
