package sample;

import java.util.ArrayList;

public class Form extends Mediator {
    public Form() {
        colleagues = new ArrayList<Colleague>();
    }

    @Override
    public void Register(Colleague colleague) {
        colleagues.add(colleague);
    }
}
