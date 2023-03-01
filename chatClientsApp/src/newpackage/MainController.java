package newpackage;



import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class MainController {
    private Socket socket;
    private String theUserID;
     Client client;
     private final  int ClientNUM=(int) Math.round(Math.random()*6);
    private static final String[] colors={"(55, 113, 142)","(109, 114, 195)","(21, 96, 100)","(74, 88, 89)","(195, 60, 84)","(107, 77, 87)","(107, 77, 87)"} ;
    @FXML
    private Label hada;
      @FXML
    private TextField msgtosend;
    public void setUserID(String x) {
         hada.setText(x);
     this.theUserID=x;  
    }
    @FXML
    private Button sendBtn;
    @FXML
    private ScrollPane ScollPage;
    @FXML
    private VBox vbox;
    @FXML
    void sendMsg(ActionEvent event) {
    if(!msgtosend.getText().isEmpty())
        client.sendMessageToTheHandler(msgtosend.getText(),vbox);
       msgtosend.setText("");
    }
    public void x(){
            try {
            socket = new Socket("localhost",1234);
             client = new Client(socket,theUserID,ClientNUM);
                System.err.println(ClientNUM);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
       client.sendUserNameToTheHandler();
       vbox.heightProperty().addListener(new ChangeListener<Number>(){
                @Override
                public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                     ScollPage.setVvalue((double) t1);
                }
       });
       client.ListenToMessageFromTheHandler(vbox);
    }
   public static void addLabel(String msg,VBox vbox,Boolean isMe,int ClientNUMc){
       HBox hBox=new HBox();
       hBox.setPadding(new Insets(5,5,5,10));
      Text text=new Text(msg);
      text.setFill(Color.WHITE);
      TextFlow textFlow=new TextFlow(text);
      textFlow.setPadding(new Insets(5,10,5,10));
      if (!isMe)
      {hBox.setAlignment(Pos.CENTER_LEFT);
      textFlow.setStyle(" -fx-background-color:rgb"+colors[ClientNUMc]+"; -fx-background-radius:0 5px 5px 5px;");  
      }
      else{
          hBox.setAlignment(Pos.CENTER_RIGHT); 
          textFlow.setStyle("-fx-background-color:rgb(72, 172, 240); -fx-background-radius:5px 5px 0 5px;");  
      }
      hBox.getChildren().add(textFlow);
      Platform.runLater(new Runnable(){
           @Override
           public void run() {
              vbox.getChildren().add(hBox);
           }
      }
      );
      
      
   }
    
    
    
}
