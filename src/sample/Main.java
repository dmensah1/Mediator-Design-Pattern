package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private static List<Employee> employees;


    @Override
    public void start(Stage primaryStage) throws Exception{
        employees = new ArrayList<Employee>();

        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        primaryStage.setTitle("Mediator Design Pattern");
        primaryStage.setScene(new Scene(root, 900, 750));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void save(Employee employee){
        employees.add(employee);
        System.out.println("Saved");
    }

    public static List<Employee> getEmployees(){
        return employees;
    }

    public static void setEmployees(List<Employee> newEmployees){
        employees = newEmployees;
    }

}
