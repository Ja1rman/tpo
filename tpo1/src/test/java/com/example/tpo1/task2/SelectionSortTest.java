package com.example.tpo1.task2;

import org.example.task2.SelectionSort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SelectionSortTest {
    @Test
    @DisplayName("Check empty")
    void checkEmpty() {
        int[] list = new int[]{};
        assertArrayEquals(list, SelectionSort.sort(list));
    }

    @Test
    @DisplayName("Check null")
    void checkNull() {
        assertThrows(NullPointerException.class, () -> SelectionSort.sort(null));
    }

    @Test
    @DisplayName("Check single elements")
    void checkSingle() {
        int[] list = new int[]{1};
        assertArrayEquals(list, SelectionSort.sort(list));
        list = new int[]{0};
        assertArrayEquals(list, SelectionSort.sort(list));
    }

    @ParameterizedTest()
    @DisplayName("Check sorted lists")
    @CsvFileSource(resources = "/task2/sorted_array_table.csv")
    void checkSorted(String arrayAsString) {
        int[] a = Arrays.stream(arrayAsString.split(";"))
                .mapToInt(Integer::parseInt)
                .toArray();
        assertAll(
                () -> assertArrayEquals(a, SelectionSort.sort(a))
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
                () -> assertArrayEquals(sorted, SelectionSort.sort(a))
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
                () -> assertArrayEquals(sorted, SelectionSort.sort(a))
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
                () -> assertArrayEquals(sorted, SelectionSort.sort(a))
        );
    }

    @Test
    @DisplayName("Check each sort step")
    void checkEachSteps() {
        int[] arr = {2, 4, 91, 0, 0};
        int[][] steps = {{0, 4, 91, 2, 0}, {0, 0, 91, 2, 4}, {0, 0, 2, 91, 4}, {0, 0, 2, 4, 91}};
        for (int i = 0; i < 4; i++) {
            int[] sorted = SelectionSort.sortOneStep(arr);
            assertArrayEquals(sorted, steps[i]);
        }

    }

}
