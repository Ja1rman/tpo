package org.example.trigs;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class Sec {
    private final Cos cosInstance;

    public Sec(Cos cos) {
        this.cosInstance = cos;
    }

    public Sec() {
        this.cosInstance = new Cos();
    }

    public double calculate(double x, double eps) {
        double cos = cosInstance.calculate(x, eps);
        if (Double.isNaN(cos) || cos == 0.0) {
            return Double.NaN;
        }
        double sec = 1 / cos;
        if (sec > -1 && sec < 1) {
            return Double.NaN;
        }
        return sec;
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
