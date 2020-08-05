package sample;

import java.util.List;

public abstract class Colleague {
    protected Mediator mediator;
    protected boolean status;

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return this.status;
    }

    public abstract List<String> getList();
    public abstract List<String> getList(String country);
    public abstract List<String> getList(String country, String province);
    public abstract List<String> getList(List<Employee> employee);

}
