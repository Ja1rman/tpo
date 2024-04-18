package org.example.trigs;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class Tan {
    private final Sin sinInstance;
    private final Cos cosInstance;

    public Tan(Sin sin, Cos cos) {
        this.sinInstance = sin;
        this.cosInstance = cos;
    }

    public Tan() {
        this.sinInstance = new Sin();
        this.cosInstance = new Cos();
    }
    public double calculate(double x, double eps) {
        double sin = sinInstance.calculate(x, eps);
        double cos = cosInstance.calculate(x, eps);
        if (Double.isNaN(cos) || cos == 0.0) {
            return Double.NaN;
        }
        return sin / cos;
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
