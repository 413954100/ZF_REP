package com.example.tank90tv.socketServers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.Thread;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListen extends Thread {

	public static final int THREAD_SLEEP_TIME = 10;
	private ServerSocket serverSocket;
	private Socket socket;
	private DataInputStream dip = null;
	private DataOutputStream dop = null;
	private boolean listenFlag = true;
	public ServerListen(ServerSocket serverSocket){
		this.serverSocket = serverSocket;
	}
	@Override
	public void run(){
		while(listenFlag){
			try {
				this.socket = serverSocket.accept();
				this.dip = new DataInputStream(this.socket.getInputStream());
				this.dop = new DataOutputStream(this.socket.getOutputStream());
				String msg = this.dip.readUTF();
				System.out.println("----"+msg);
				this.dop.writeUTF("access success!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try{
					if(this.dip!=null){
						this.dip.close();
					}
					if(this.dop!=null){
						this.dop.close();
					}
					if(this.socket!=null){
						this.socket.close();
					}
				}catch (Exception e) {
					// TODO: handle exception
				}
			}
			try {
				sleep(THREAD_SLEEP_TIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
