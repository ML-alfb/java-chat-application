package chatserver;
 import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class ChatServer extends Thread {
	int num=0; 
	@Override
	public void run() {
		try {
			ServerSocket ss=new ServerSocket(1234);
			System.out.println("server on");
			while(true) {
				Socket s=ss.accept();
				num++;
				System.out.println(num +" Client Connected to the chat server");
				new ClientHandler(s).start();
			}	
		} catch (IOException e) {	
		}
	}
  public static void main(String[] args) {
	  new ChatServer().start();  
  }
}


