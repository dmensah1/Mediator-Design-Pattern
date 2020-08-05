package sample;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddFormController implements Initializable {

    @FXML
    private TextField idText;
    @FXML
    private TextField nameText;
    @FXML
    private ComboBox<String> countryBox;
    @FXML
    private TextField postalText;
    @FXML
    private TextField addressText;
    @FXML
    private Button saveButton;

    private Form mediatorForm;
    @FXML
    private ComboBox<String> provinceBox;
    @FXML
    private ComboBox<String> cityBox;

    @FXML
    private Button deleteButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        mediatorForm = new Form();
        mediatorForm.Register(new ComboboxColleague(mediatorForm, "src/sample/Countries.txt", true));
        mediatorForm.Register(new ComboboxColleague(mediatorForm, "src/sample/Provinces_States.txt", false));
        mediatorForm.Register(new ComboboxColleague(mediatorForm, "src/sample/Cities.txt", false));
        mediatorForm.Register(new TextboxColleague());
        mediatorForm.Register(new TextboxColleague());
        mediatorForm.Register(new ButtonColleague());

        List<String> list = mediatorForm.colleagues.get(0).getList();
        countryBox.getItems().addAll(list);

        update();
    }

    @FXML
    private void CountrySelected(ActionEvent event) {
        mediatorForm.colleagues.get(1).setStatus(true);
        mediatorForm.colleagues.get(2).setStatus(false);
        String country = countryBox.getValue();
        provinceBox.getItems().clear();
        List<String> list = mediatorForm.colleagues.get(1).getList(country);
        provinceBox.getItems().addAll(list);
        update();
    }

    @FXML
    private void ProvinceSelected(ActionEvent event) {
        mediatorForm.colleagues.get(2).setStatus(true);
        String country = countryBox.getValue();
        String province = provinceBox.getValue();
        cityBox.getItems().clear();
        List<String> list = mediatorForm.colleagues.get(2).getList(country, province);
        cityBox.getItems().addAll(list);
        update();
    }

    @FXML
    private void CitySelected(ActionEvent event) {
        mediatorForm.colleagues.get(3).setStatus(true);
        mediatorForm.colleagues.get(4).setStatus(true);
        mediatorForm.colleagues.get(5).setStatus(true);
        update();
    }

    private void update(){
        provinceBox.setDisable(!mediatorForm.colleagues.get(1).getStatus());
        cityBox.setDisable(!mediatorForm.colleagues.get(2).getStatus());
        postalText.setDisable(!mediatorForm.colleagues.get(3).getStatus());
        addressText.setDisable(!mediatorForm.colleagues.get(4).getStatus());
        saveButton.setDisable(!mediatorForm.colleagues.get(5).getStatus());
    }

    @FXML
    private void CancelButtonClicked(ActionEvent event) {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void SaveButtonClicked(ActionEvent event) {
        String id = idText.getText();
        String name = nameText.getText();
        String country = countryBox.getValue();
        String province = provinceBox.getValue();
        String city = cityBox.getValue();
        String postal = postalText.getText();
        String street = addressText.getText();

        if(!id.equals("") && !name.equals("")){
            Employee e = new Employee(id, name, country, province, city, postal, street);
            Main.save(e);
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
        }
    }

}
