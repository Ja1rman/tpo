package com.example.tpo1.task2;

import org.example.task2.ShellSort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SellSortTest {
    @Test
    @DisplayName("Check empty")
    void checkEmpty() {
        int[] list = new int[]{};
        assertArrayEquals(list, ShellSort.sort(list));
    }

    @Test
    @DisplayName("Check null")
    void checkNull() {
        assertThrows(NullPointerException.class, () -> ShellSort.sort(null));
    }

    @Test
    @DisplayName("Check single elements")
    void checkSingle() {
        int[] list = new int[]{1};
        assertArrayEquals(list, ShellSort.sort(list));
        list = new int[]{0};
        assertArrayEquals(list, ShellSort.sort(list));
    }

    @ParameterizedTest()
    @DisplayName("Check sorted lists")
    @CsvFileSource(resources = "/task2/sorted_array_table.csv")
    void checkSorted(String arrayAsString) {
        int[] a = Arrays.stream(arrayAsString.split(";"))
                .mapToInt(Integer::parseInt)
                .toArray();
        assertAll(
                () -> assertArrayEquals(a, ShellSort.sort(a))
        );
    }

    @ParameterizedTest()
    @DisplayName("Check negative lists")
    @CsvFileSource(resources = "/task2/negative_array_table.csv")
    void checkNegative(String arrayAsString) {
        int[] a = Arrays.stream(arrayAsString.split(";"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] sorted = Arrays.stream(a).sorted().toArray();
        assertAll(
                () -> assertArrayEquals(sorted, ShellSort.sort(a))
        );
    }

    @ParameterizedTest()
    @DisplayName("Check positive lists")
    @CsvFileSource(resources = "/task2/positive_array_table.csv")
    void checkPositive(String arrayAsString) {
        int[] a = Arrays.stream(arrayAsString.split(";"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] sorted = Arrays.stream(a).sorted().toArray();
        assertAll(
                () -> assertArrayEquals(sorted, ShellSort.sort(a))
        );
    }

    @ParameterizedTest()
    @DisplayName("Check random lists")
    @CsvFileSource(resources = "/task2/random_array_table.csv")
    void checkRandom(String arrayAsString) {
        int[] a = Arrays.stream(arrayAsString.split(";"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] sorted = Arrays.stream(a).sorted().toArray();
        assertAll(
                () -> assertArrayEquals(sorted, ShellSort.sort(a))
        );
    }
}
