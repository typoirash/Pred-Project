package koschei.models;

import org.springframework.stereotype.Component;

@Component
public class Rabbit4 {
    private Duck5 duck;

    public Rabbit4(Duck5 duck) {
        this.duck = duck;
    }

    @Override
    public String toString() {
        return ", в зайце утка " + duck.toString();
    }
}
