package org.example.task2;

import java.util.Collections;
import java.util.List;

public class ShellSort {
    public static int[] sort(int[] array) {
        for (int s = array.length / 2; s > 0; s /= 2)
            for (int i = s; i < array.length; i++)
                for (int j = i - s; j >= 0 && array[j] > array[j + s]; j -= s) {
                    int temp = array[j];
                    array[j] = array[j + s];
                    array[j + s] = temp;
                }
        return array;
    }
}
