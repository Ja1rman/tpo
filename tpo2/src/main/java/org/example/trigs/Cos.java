package org.example.trigs;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class Cos {
    private final Sin sinInstance;

    public Cos(Sin sin) {
        this.sinInstance = sin;
    }

    public Cos() {
        this.sinInstance = new Sin();
    }

    public double calculate(double x, double eps) {
        double sin = sinInstance.calculate(x, eps);
        double result = Math.sqrt(1 - sin * sin);
        if (Double.isNaN(result) || Math.abs(result) > 1+eps) return Double.NaN;
        return result;
    }

    public double saveResultToCSV(double x, double eps, Writer out) {
        double res = calculate(x, eps);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {
            printer.printRecord(x, res);
        } catch (IOException e) {
            System.out.println("Не удалось записать файл");
            System.out.println("Ошибка: " + e.getMessage());
        }
        return res;
    }
}
