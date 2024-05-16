package org.example.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class CsvWriter {
    private static final String resPath = "/home/jairman/IdeaProjects/tpo/tpo2/src/test/resources/results/";

    public static void write(String methodName, Object[] args, Object result) {
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(new FileWriter(resPath + methodName + ".csv", true))) {
            printer.printRecord(args);
            printer.printRecord(result);
        } catch (IOException e) {
            System.out.println("Не удалось записать файл");
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
