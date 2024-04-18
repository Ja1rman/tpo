package org.example.trigs;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class Csc {
    private final Sin sinInstance;

    public Csc(Sin sin) {
        this.sinInstance = sin;
    }

    public Csc() {
        this.sinInstance = new Sin();
    }

    public double calculate(double x, double eps) {
        double sin = sinInstance.calculate(x, eps);
        if (Double.isNaN(sin) || sin == 0.0) {
            return Double.NaN;
        }
        double csc = 1 / sin;
        if (csc > -1 && csc < 1) {
            return Double.NaN;
        }
        return csc;
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
