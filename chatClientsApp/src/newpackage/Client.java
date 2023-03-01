
package newpackage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import javafx.scene.layout.VBox;

public class Client  {

        
	
        private int ClientNUM=0;
	private Socket socket;
	private BufferedReader buffredReader;
	private BufferedWriter buffredWriter;
	private String  userID="wamaaalna";
	public Client(Socket socket, String userID,int ClientNUM) {
		try {  
			this.socket = socket;
			this.buffredReader = new BufferedReader(new InputStreamReader (socket.getInputStream()));
			this.buffredWriter =new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.userID=userID;
                        this.ClientNUM=ClientNUM;
                        
		} catch (IOException e) {
                  

		}
	}

         
        public void sendUserNameToTheHandler() {
            try{
             buffredWriter.write(userID);
			buffredWriter.newLine();
			buffredWriter.flush();
                       
                        
            }catch (IOException e) {
                   

		}
        }
		public void sendMessageToTheHandler(String msg,VBox vbox) {
			
		try {
                         MainController.addLabel(msg, vbox,true,1);
				buffredWriter.write(msg);
				buffredWriter.newLine();
				buffredWriter.flush();	
				
			
		} catch (IOException e) {
                    // TODO Auto-generated catch block

		}
		
	   }
		
		public void ListenToMessageFromTheHandler(VBox vbox) {
			new Thread(new Runnable() {
                            @Override
                            public void run() {
                                 try {
                                String newMsg;
                                
                                while(socket.isConnected()) {
                                    newMsg=buffredReader.readLine();
                                  
                                    MainController.addLabel(newMsg, vbox,false,ClientNUM);
                                    
                                    
                                }
                            } catch ( IOException e) {
                                closeEverything( socket, buffredReader, buffredWriter);
                            }
                            }
                       
                           
                        }).start();
	   };
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
		public void closeEverything(Socket socket,BufferedReader bufferedReader,BufferedWriter bufferedWriter) {
		try {
			if(bufferedWriter!=null)
			bufferedWriter.close();
			if(bufferedReader!=null)
			bufferedReader.close();
			if(socket!=null)
                            socket.close();
                        
			
		} catch (IOException e) {
		}
		}


		
	
	
}