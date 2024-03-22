package org.example.task3;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

@Getter
@Setter
public class Person extends Human {

    private String mood;
    private String clothingColor;
    private String affiliation;

    public Person(String name, Integer hp, Double x, Double y, Double z, String mood, String clothingColor, String affiliation) {
        // Call the superclass (Human) constructor with required parameters
        super(name, hp, x, y, z);

        // Initialize Person-specific fields
        this.mood = mood;
        this.clothingColor = clothingColor;
        this.affiliation = affiliation;
    }
    public void fastXSteps() {
        x += 2;
    }

    public void simpleXSteps() {
        x += 1;
    }

    public void fastYSteps() {
        y += 2;
    }

    public void simpleYSteps() {
        y += 1;
    }

    @SneakyThrows
    public void pushHuman(Human human) {
        if (human.getHp() <= 0)
            throw new Exception("Нельзя бить лежачего!");
        human.setHp(human.getHp() - 50);
    }
}
