package sample;

import java.util.List;

public abstract class Mediator {
    protected List<Colleague> colleagues;

    public abstract void Register(Colleague colleague);
}
