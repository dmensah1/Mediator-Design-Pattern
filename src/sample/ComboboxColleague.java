package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;


public class ComboboxColleague extends Colleague{

    private List<String[]> list = new ArrayList<String[]>();

    public ComboboxColleague(Mediator mediator, String ListName, boolean status){
        readList(ListName);
        this.status = status;
    }

    public ComboboxColleague(Mediator mediator, boolean status){
        this.status = status;
    }

    @Override
    public List<String> getList() {
        List<String> returnList = new ArrayList<String>();
        for(int i = 0; i < list.size(); i++){
            returnList.add(list.get(i)[0]);
        }

        return returnList;
    }

    @Override
    public List<String> getList(String country) {
        List<String> returnList = new ArrayList<String>();
        for(int i = 0; i < list.size(); i++){
            if(list.get(i)[0].equals(country)){
                returnList.add(list.get(i)[1]);
            }
        }

        return returnList;
    }

    @Override
    public List<String> getList(String country, String state) {
        List<String> returnList = new ArrayList<String>();
        for(int i = 0; i < list.size(); i++){
            String[] item = list.get(i);
            if(item[0].equals(country) && item[1].equals(state)){
                returnList.add(list.get(i)[2]);
            }
        }

        return returnList;
    }

    private void readList(String path) {
        try{
            File file = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(file));
            System.out.println("List File Opened");
            String line;
            while ((line = br.readLine()) != null) {
                String[] item = line.split(",");
                list.add(item);
            }
        }
        catch(Exception e){
            System.out.println("Exception thrown in ComboboxColleague:readList()");
        }
    }

    @Override
    public List<String> getList(List<Employee> employees) {
        List<String> ids = new ArrayList<String>();
        for(int i = 0; i < employees.size(); i++){
            ids.add(employees.get(i).id);
        }
        return ids;
    }
}
