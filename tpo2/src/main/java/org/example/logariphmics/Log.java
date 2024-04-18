package org.example.logariphmics;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.example.trigs.Cos;
import org.example.trigs.Sin;

import java.io.IOException;
import java.io.Writer;

public class Log {
    private final Ln lnInstance;

    public Log(Ln ln) {
        this.lnInstance = ln;
    }

    public Log() {
        this.lnInstance = new Ln();
    }

    public double calculate(double x, double a, double eps) {
        double lnx = lnInstance.calculate(x, eps);
        double lna = lnInstance.calculate(a, eps);
        if (Double.isNaN(lnx) || Double.isNaN(lna) || lna == 0.0) {
            return Double.NaN;
        }
        return lnx / lna;
    }

    public double saveResultToCSV(double x, double a, double eps, Writer out) {
        double res = calculate(x, a, eps);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {
            printer.printRecord(x, res);
        } catch (IOException e) {
            System.out.println("Не удалось записать файл");
            System.out.println("Ошибка: " + e.getMessage());
        }
        return res;
    }
}
