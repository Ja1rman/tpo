package org.example.functions;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.example.logariphmics.Ln;
import org.example.logariphmics.Log;
import org.example.trigs.*;

import java.io.IOException;
import java.io.Writer;

public class BaseFunc {
    private final Csc cscInstance;
    private final Tan tanInstance;
    private final Sec secInstance;
    private final Cos cosInstance;
    private final Sin sinInstance;
    private final Log logInstance;
    private final Ln lnInstance;

    public BaseFunc() {
        this.cosInstance = new Cos();
        this.sinInstance = new Sin();
        this.secInstance = new Sec();
        this.cscInstance = new Csc();
        this.tanInstance = new Tan();
        this.lnInstance = new Ln();
        this.logInstance = new Log();

    }

    public BaseFunc(Sec sec, Cos cos, Sin sin, Tan tan, Csc csc, Ln ln, Log log) {
        this.cosInstance = cos;
        this.sinInstance = sin;
        this.secInstance = sec;
        this.cscInstance = csc;
        this.tanInstance = tan;
        this.logInstance = log;
        this.lnInstance = ln;
    }

    private double less0(double x, double eps) {
        double cos = cosInstance.calculate(x, eps);
        double sin = sinInstance.calculate(x, eps);
        double sec = secInstance.calculate(x, eps);
        double csc = cscInstance.calculate(x, eps);
        double tan = tanInstance.calculate(x, eps);
        return Math.pow(((cos - tan + sec) * csc) - Math.pow(tan * sin, 2), 2);
    }

    private double greater0(double x, double eps) {
        double ln = lnInstance.calculate(x, eps);
        double log2 = logInstance.calculate(x, 2, eps);
        double log3 = logInstance.calculate(x, 3, eps);
        double log5 = logInstance.calculate(x, 5, eps);
        double log10 = logInstance.calculate(x, 10, eps);
        return Math.pow(Math.pow(log5 * log10, 3) + log2, 3) + ln * log3 * log3 * log10 * log10;
    }

    public double calculate(double x, double eps) {
        if (x <= 0) {
            return less0(x, eps);
        }
        return greater0(x, eps);
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
