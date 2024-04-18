package com.example.tpo1.task1;

import org.example.task1.Sin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SinTest {

    private static final double DELTA = 1e-4;

    @ParameterizedTest(name = "sin({0})")
    @DisplayName("Check PI dots")
    @ValueSource(doubles = {-2 * Math.PI, -3 * Math.PI / 2, -Math.PI, -Math.PI / 2, 0, Math.PI / 2, Math.PI, 3 * Math.PI / 2, 2 * Math.PI})
    void checkPiDots(double param) {
        assertAll(
                () -> assertEquals(Math.sin(param), Sin.power_series(param, 100), DELTA)
        );
    }

    @ParameterizedTest(name = "sin({0}) = {1}")
    @DisplayName("Check between dots [-1; +1]")
    @CsvFileSource(resources = "/task1/table_values.csv", numLinesToSkip = 1, delimiter = ';')
    void checkBetweenDotsMinusPiAndPi(double x, double y) {
        assertAll(
                () -> assertEquals(y, Sin.power_series(x, 100), DELTA)
        );
    }

    @ParameterizedTest(name = "sin({0}) = {1}")
    @DisplayName("Check positive/negative")
    @ValueSource(doubles = {Math.PI / 2, Math.PI, 3 * Math.PI / 2, 2 * Math.PI})
    void checkPositiveNegative(double x) {
        Double positive = Sin.power_series(x, 100);
        Double negative = Sin.power_series(-x, 100);
        assertEquals(-negative, positive, DELTA);
    }
}