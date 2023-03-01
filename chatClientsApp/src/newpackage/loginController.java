
package newpackage;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
public class loginController {
    private String UserID;
    @FXML
    private TextField UserName;
    @FXML
     void closeWn(MouseEvent event) {
       System.exit(0);
    }
    @FXML
     void logedIn(MouseEvent event) {
          UserID= (String)UserName.getText();
           try {	     
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainApp.fxml"));
			 Parent root=loader.load();
                       MainController m=loader.getController();
                         m.setUserID(UserID);
                         m.x();
                        Scene scene = new Scene(root);
                      Stage stage1=(Stage)((Node)event.getSource()).getScene().getWindow();
			stage1.setScene(scene);
			stage1.show();
				} catch (IOException e) {
				} 
    }
    @FXML
    void logedInKey(KeyEvent event) {
      if (event.getCode()== KeyCode.ENTER){
            UserID= (String)UserName.getText();
           try {		     
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainApp.fxml"));
			Parent root=loader.load();
                        MainController m=loader.getController(); 
                       m.setUserID(UserID);
                      m.x();
                        Scene scene = new Scene(root);
                      Stage stage1=(Stage)((Node)event.getSource()).getScene().getWindow();
			stage1.setScene(scene);
			stage1.show();
				} catch (IOException e) {
				} 
      }
 
    }

}
