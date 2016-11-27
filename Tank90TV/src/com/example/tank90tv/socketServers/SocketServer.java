package com.example.tank90tv.socketServers;


import java.io.IOException;
import java.net.ServerSocket;

public class SocketServer {

	private ServerSocket serverSocket = null;
	private ServerListen serverListen;
	
	public SocketServer(){
		startServer();
	}
	
	public void startServer(){
		try {
			if(this.serverSocket==null)
				this.serverSocket = new ServerSocket(8888);
			this.serverListen = new ServerListen(serverSocket);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
