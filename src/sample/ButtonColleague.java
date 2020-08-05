package sample;

import java.util.List;

public class ButtonColleague extends Colleague {

    public ButtonColleague(){
        this.status = false;
    }

    @Override
    public List<String> getList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<String> getList(String country) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<String> getList(String country, String province) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<String> getList(List<Employee> employees) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
