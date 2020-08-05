package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController implements Initializable{

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Close(ActionEvent event) { System.exit(0); }

    @FXML
    private void OpenAddForm(ActionEvent event) {
        OpenForm("AddForm.fxml");
    }

    @FXML
    private void OpenModifyForm(ActionEvent event) {
        OpenForm("ModifyForm.fxml");
    }

    private void OpenForm(String form){
        try {
            System.out.println("Opening: " + form);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(form));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
