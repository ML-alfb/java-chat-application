package chatserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

import java.util.List;

public class ClientHandler extends Thread {
	public static List<ClientHandler> clientHandlers=new ArrayList<>();
	private Socket socket;
	private BufferedReader buffredReader;
	private BufferedWriter buffredWriter;
	private String  userID;
        public ClientHandler(Socket socket) {
		try {
			this.socket = socket;
			this.buffredReader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.buffredWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.userID=buffredReader.readLine();
                        System.out.println(userID + " is connected ");
			clientHandlers.add(this);
		}
		 catch (IOException e) {	
		}}
	@Override
	public void run() {
                while(socket.isConnected()) {
			try {
				String msg=buffredReader.readLine();
				BrodcastMsg(msg);
			} catch (IOException e) {
			}
		}
	}
	private void BrodcastMsg(String msgFromToClient)  {
		for (ClientHandler clientHandler : clientHandlers) {
			if(!clientHandler.userID.equals(this.userID)) {
				try {
					clientHandler.buffredWriter.write((userID+": "+msgFromToClient));
					clientHandler.buffredWriter.newLine();
					clientHandler.buffredWriter.flush();
				} catch (IOException e) {
				}
			}
		}	
	}
}
