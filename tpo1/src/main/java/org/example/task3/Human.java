package org.example.task3;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

@Getter
@Setter
@Builder
public class Human {
    protected String name;
    protected Integer hp;
    protected Double x;
    protected Double y;
    protected Double z;

    @SneakyThrows
    public void pushHuman(Human human) {
        if (human.getHp() <= 0)
            throw new Exception("Нельзя бить лежачего!");
        human.setHp(human.getHp() - 1);
    }
}
