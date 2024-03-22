package org.example.task3;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
public class Room {
    private Door door;
    private Double x;
    private Double y;
    private Double z;
    private Atmosphere atmosphere;
    private List<Human> humans;

    @SneakyThrows
    public void kickHumanFromRoom(Human human) {
        this.humans.stream().filter(human::equals).findAny()
                .orElseThrow(() -> new Exception("Такого человека нет в комнате!"));
        humans.remove(human);
    }

    @SneakyThrows
    public void addNewHuman(Human human) {
        if (human.getHp() <= 0)
            throw new Exception("Нельзя добавить в битву неподвижных!");
        if (!Objects.equals(human.getX(), x) ||
            !Objects.equals(human.getY(), y) ||
            !Objects.equals(human.getZ(), z))
            throw new Exception("Человек не в комнате!");
        humans.add(human);
    }
}
