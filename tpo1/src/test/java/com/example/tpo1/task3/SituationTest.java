package com.example.tpo1.task3;


import org.example.task3.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SituationTest {
    @Nested
    class RoomTest {
        private List<Person> persons;
        private Room room;

        @BeforeEach
        void init() {
            Door door = Door.builder()
                    .isOpen(false) // or .isOpen(Boolean.TRUE)
                    .build();
            Atmosphere atmosphere = Atmosphere.builder()
                    .noise(false)
                    .scream(false)
                    .build();
            Human footman = Human.builder()
                    .name("Лакей 1")
                    .hp(100)
                    .x(2.)
                    .y(2.)
                    .z(10.)
                    .build();
            Human footman2 = Human.builder()
                    .name("Лакей 2")
                    .hp(100)
                    .x(2.)
                    .y(2.)
                    .z(10.)
                    .build();
            List<Human> footmans = new ArrayList<>() {{add(footman); add(footman2);}};
            room = Room.builder()
                    .door(door)
                    .x(2.)
                    .y(2.)
                    .z(10.)
                    .atmosphere(atmosphere)
                    .humans(footmans)
                    .build();
            Person person1 = new Person("Человек 1",
                    100,
                    0.,
                    0.,
                    0.,
                    "Сердитый",
                    "выцветший синий",
                    "Круксванский университет");
            Person person2 = new Person("Человек 2",
                    100,
                    0.,
                    0.,
                    0.,
                    "Сердитый",
                    "выцветший синий",
                    "Круксванский университет");
            persons = new ArrayList<>() {{add(person1); add(person2);}};
        }

        @Test
        @DisplayName("Проверка добавления нездорового человека")
        void add0HpTest() {
            persons.getFirst().setHp(0);
            Throwable ex = assertThrows(Exception.class,
                    () -> room.addNewHuman(persons.getFirst()));
            assertEquals("Нельзя добавить в битву неподвижных!", ex.getMessage());
        }

        @Test
        @DisplayName("Проверка добавления человека не в комнате")
        void addFalsePositionTest() {
            persons.getFirst().setX(room.getX()-1);
            persons.getFirst().setY(room.getY());
            persons.getFirst().setZ(room.getZ());
            Throwable ex = assertThrows(Exception.class,
                    () -> room.addNewHuman(persons.getFirst()));
            assertEquals("Человек не в комнате!", ex.getMessage());
            persons.getFirst().setX(room.getX());
            persons.getFirst().setY(room.getY()-1);
            ex = assertThrows(Exception.class,
                    () -> room.addNewHuman(persons.getFirst()));
            assertEquals("Человек не в комнате!", ex.getMessage());
            persons.getFirst().setZ(room.getZ()-1);
            persons.getFirst().setY(room.getY());
            ex = assertThrows(Exception.class,
                    () -> room.addNewHuman(persons.getFirst()));
            assertEquals("Человек не в комнате!", ex.getMessage());
        }

        @Test
        @DisplayName("Проверка добавления корректного человека")
        void addCorrectHumanTest() {
            persons.getFirst().setX(room.getX());
            persons.getFirst().setY(room.getY());
            persons.getFirst().setZ(room.getZ());
            assertDoesNotThrow(() -> room.addNewHuman(persons.getFirst()));
        }

        @Test
        @DisplayName("Проверка выкидывания отсутствующего человека")
        void KickInCorrectTest() {
            Throwable ex = assertThrows(Exception.class,
                    () -> room.kickHumanFromRoom(persons.getFirst()));
            assertEquals("Такого человека нет в комнате!", ex.getMessage());
        }

        @Test
        @DisplayName("Проверка выкидывания человека")
        void KickCorrectTest() {
            assertDoesNotThrow(() -> room.kickHumanFromRoom(room.getHumans().getFirst()));
        }
    }

    @Nested
    class HumanTest {
        private List<Person> persons;
        private List<Human> humans;

        @BeforeEach
        void init() {
            Human footman = Human.builder()
                    .name("Лакей 1")
                    .hp(100)
                    .x(2.)
                    .y(2.)
                    .z(10.)
                    .build();
            Human footman2 = Human.builder()
                    .name("Лакей 2")
                    .hp(100)
                    .x(2.)
                    .y(2.)
                    .z(10.)
                    .build();
            humans = new ArrayList<>() {{add(footman); add(footman2);}};

            Person person1 = new Person("Человек 1",
                    100,
                    0.,
                    0.,
                    0.,
                    "Сердитый",
                    "выцветший синий",
                    "Круксванский университет");
            Person person2 = new Person("Человек 2",
                    100,
                    0.,
                    0.,
                    0.,
                    "Сердитый",
                    "выцветший синий",
                    "Круксванский университет");
            persons = new ArrayList<>() {{add(person1); add(person2);}};
        }

        @Test
        @DisplayName("Проверка ошибки ударов")
        void pushFalseTest() {
            humans.getFirst().setHp(0);
            Throwable ex = assertThrows(Exception.class,
                    () -> humans.getLast().pushHuman(humans.getFirst()));
            assertEquals("Нельзя бить лежачего!", ex.getMessage());
            ex = assertThrows(Exception.class,
                    () -> persons.getLast().pushHuman(humans.getFirst()));
            assertEquals("Нельзя бить лежачего!", ex.getMessage());
        }

        @Test
        @DisplayName("Проверка корректных ударов")
        void pushTrueTest() {
            assertDoesNotThrow(() -> humans.getLast().pushHuman(humans.getFirst()));
            assertDoesNotThrow(() -> persons.getLast().pushHuman(humans.getFirst()));
        }
    }

}
