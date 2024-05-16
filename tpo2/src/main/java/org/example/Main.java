package org.example;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.example.functions.BaseFunc;
import org.example.trigs.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Main {
    public static void main(String[] args) {
        BaseFunc func = new BaseFunc();
        System.out.println(func.calculate(-1.4, 0.1));
        System.out.println(func.calculate(-1, 0.1));
        System.out.println(func.calculate(-1.2, 0.1));
        System.out.println(func.calculate(-0.8, 0.1));
    }
}