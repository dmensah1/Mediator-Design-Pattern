package sample;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModifyFormController implements Initializable {
    @FXML
    private TextField nameText;
    @FXML
    private ComboBox<String> countryBox;
    @FXML
    private TextField postalText;
    @FXML
    private TextField streetText;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;
    @FXML
    private ComboBox<String> provinceBox;
    @FXML
    private ComboBox<String> cityBox;
    @FXML
    private Button deleteButton;
    @FXML
    private ComboBox<String> idBox;

    private List<Employee> employees;
    private Form mediatorForm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        employees = Main.getEmployees();
        updateEmployeeList();

        mediatorForm = new Form();
        mediatorForm.Register(new ComboboxColleague(mediatorForm, true));
        mediatorForm.Register(new TextboxColleague());
        mediatorForm.Register(new ComboboxColleague(mediatorForm, "src/sample/Countries.txt", false));
        mediatorForm.Register(new ComboboxColleague(mediatorForm, "src/sample/Provinces_States.txt", false));
        mediatorForm.Register(new ComboboxColleague(mediatorForm, "src/sample/Cities.txt", false));
        mediatorForm.Register(new TextboxColleague());
        mediatorForm.Register(new TextboxColleague());
        mediatorForm.Register(new ButtonColleague());
        mediatorForm.Register(new ButtonColleague());

        update();
    }

    @FXML
    private void CancelButtonClicked(ActionEvent event) {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void SaveButtonClicked(ActionEvent event) {
        String id = idBox.getValue();
        String name = nameText.getText();
        String country = countryBox.getValue();
        String province = provinceBox.getValue();
        String city = cityBox.getValue();
        String postal = postalText.getText();
        String street = streetText.getText();

        if(name.equals("") || country.equals("") || province.equals("") || city.equals("")){
            return;
        }

        for(int i = 0; i < employees.size(); i++){
            if(employees.get(i).id.equals(id)){
                Employee e = employees.remove(i);
                e.name = name;
                e.country = country;
                e.province = province;
                e.city = city;
                e.postal = postal;
                e.street = street;

                Main.save(e);
                break;
            }
        }

        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void CountrySelected(ActionEvent event) {
        String country = countryBox.getValue();
        provinceBox.getItems().clear();
        List<String> list = mediatorForm.colleagues.get(3).getList(country);
        provinceBox.getItems().addAll(list);
        update();
    }

    @FXML
    private void ProvinceSelected(ActionEvent event) {
        String country = countryBox.getValue();
        String province = provinceBox.getValue();
        cityBox.getItems().clear();
        List<String> list = mediatorForm.colleagues.get(4).getList(country, province);
        cityBox.getItems().addAll(list);
        update();
    }

    @FXML
    private void DeleteButtonClicked(ActionEvent event) {
        for(int i = 0; i < employees.size(); i++){
            if(employees.get(i).id.equals(idBox.getValue())){
                employees.remove(i);
                Main.setEmployees(employees);
                break;
            }
        }
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    private void update(){
        nameText.setDisable(!mediatorForm.colleagues.get(1).getStatus());
        countryBox.setDisable(!mediatorForm.colleagues.get(2).getStatus());
        provinceBox.setDisable(!mediatorForm.colleagues.get(3).getStatus());
        cityBox.setDisable(!mediatorForm.colleagues.get(4).getStatus());
        postalText.setDisable(!mediatorForm.colleagues.get(5).getStatus());
        streetText.setDisable(!mediatorForm.colleagues.get(6).getStatus());
        saveButton.setDisable(!mediatorForm.colleagues.get(7).getStatus());
        deleteButton.setDisable(!mediatorForm.colleagues.get(8).getStatus());
    }

    @FXML
    private void EmployeeSelected(ActionEvent event) {
        countryBox.getItems().clear();
        List<String> list = mediatorForm.colleagues.get(2).getList();
        countryBox.getItems().addAll(list);

        for(int i = 0; i < mediatorForm.colleagues.size(); i++){
            mediatorForm.colleagues.get(i).setStatus(true);
        }
        update();

        Employee e = null;
        for(int i = 0; i < employees.size(); i++){
            if(employees.get(i).id.equals(idBox.getValue())){
                e = employees.get(i);
                break;
            }
        }

        nameText.setText(e.name);
        countryBox.setValue(e.country);
        provinceBox.setValue(e.province);
        cityBox.setValue(e.city);
        postalText.setText(e.postal);
        streetText.setText(e.street);

    }

    private void updateEmployeeList(){
        List<String> list = new ArrayList<String>();
        for(int i = 0; i < employees.size(); i++){
            list.add(employees.get(i).id);
        }
        idBox.getItems().addAll(list);
    }

}
