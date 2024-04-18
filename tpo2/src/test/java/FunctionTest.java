import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.example.functions.BaseFunc;
import org.example.logariphmics.Ln;
import org.example.logariphmics.Log;
import org.example.trigs.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class FunctionTest {
    static double functionEps = 0.001;
    double eps = 0.1;

    static Csc cscMock;
    static Tan tanMock;
    static Sec secMock;
    static Cos cosMock;
    static Sin sinMock;
    static Ln lnMock;
    static Log logMock;

    static Reader cscIn;
    static Reader tanIn;
    static Reader secIn;
    static Reader cosIn;
    static Reader sinIn;
    static Reader lnIn;
    static Reader log2In;
    static Reader log3In;
    static Reader log5In;
    static Reader log10In;


    @BeforeAll
    static void init() {
        cscMock = Mockito.mock(Csc.class);
        tanMock = Mockito.mock(Tan.class);
        secMock = Mockito.mock(Sec.class);
        cosMock = Mockito.mock(Cos.class);
        sinMock = Mockito.mock(Sin.class);
        lnMock = Mockito.mock(Ln.class);
        logMock = Mockito.mock(Log.class);
        try {
            cscIn = new FileReader("src/test/resources/csvIn/CscIn.csv");
            tanIn = new FileReader("src/test/resources/csvIn/TanIn.csv");
            secIn = new FileReader("src/test/resources/csvIn/SecIn.csv");
            cosIn = new FileReader("src/test/resources/csvIn/CosIn.csv");
            sinIn = new FileReader("src/test/resources/csvIn/SinIn.csv");
            lnIn = new FileReader("src/test/resources/csvIn/LnIn.csv");
            log2In = new FileReader("src/test/resources/csvIn/Log2In.csv");
            log3In = new FileReader("src/test/resources/csvIn/Log3In.csv");
            log5In = new FileReader("src/test/resources/csvIn/Log5In.csv");
            log10In = new FileReader("src/test/resources/csvIn/Log10In.csv");

            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(cscIn);
            for (CSVRecord record : records) {
                Mockito.when(cscMock.calculate(Double.parseDouble(record.get(0)), functionEps)).thenReturn(Double.parseDouble(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(tanIn);
            for (CSVRecord record : records) {
                Mockito.when(tanMock.calculate(Double.parseDouble(record.get(0)), functionEps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(secIn);
            for (CSVRecord record : records) {
                Mockito.when(secMock.calculate(Double.parseDouble(record.get(0)), functionEps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(cosIn);
            for (CSVRecord record : records) {
                Mockito.when(cosMock.calculate(Double.parseDouble(record.get(0)), functionEps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(sinIn);
            for (CSVRecord record : records) {
                Mockito.when(sinMock.calculate(Double.parseDouble(record.get(0)), functionEps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(lnIn);
            for (CSVRecord record : records) {
                Mockito.when(lnMock.calculate(Double.parseDouble(record.get(0)), functionEps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(log2In);
            for (CSVRecord record : records) {
                Mockito.when(logMock.calculate(Double.parseDouble(record.get(0)), 2, functionEps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(log3In);
            for (CSVRecord record : records) {
                Mockito.when(logMock.calculate(Double.parseDouble(record.get(0)), 3, functionEps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(log5In);
            for (CSVRecord record : records) {
                Mockito.when(logMock.calculate(Double.parseDouble(record.get(0)), 5, functionEps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(log10In);
            for (CSVRecord record : records) {
                Mockito.when(logMock.calculate(Double.parseDouble(record.get(0)), 10, functionEps)).thenReturn(Double.valueOf(record.get(1)));
            }
        } catch (IOException ex) {
            System.err.println("Патовая");
        }

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csvIn/FunctionIn.csv")
    void testWithMocks(double value, double expected) {
        BaseFunc function = new BaseFunc(secMock, cosMock, sinMock, tanMock, cscMock, lnMock, logMock);
        System.out.println(secMock.calculate(Double.POSITIVE_INFINITY, functionEps));
        //Assertions.assertEquals(expected, function.calculate(value, functionEps), eps);
        try {
            Assertions.assertEquals(expected, function.saveResultToCSV(value, functionEps,
                    new FileWriter("/home/jairman/IdeaProjects/tpo/tpo2/src/test/resources/results/FunctionOut.csv", true)), eps);
        } catch (IOException e) {
            System.out.println("Ошибка сохранения результатов");
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csvIn/FunctionIn.csv")
    void testWithSec(double value, double expected) {
        BaseFunc function = new BaseFunc(new Sec(cosMock), cosMock, sinMock, tanMock, cscMock, lnMock, logMock);
        Assertions.assertEquals(expected, function.calculate(value, functionEps), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csvIn/FunctionIn.csv")
    void testWithSecCos(double value, double expected) {
        BaseFunc function = new BaseFunc(new Sec(new Cos(sinMock)), cosMock, sinMock, tanMock, cscMock, lnMock, logMock);
        Assertions.assertEquals(expected, function.calculate(value, functionEps), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csvIn/FunctionIn.csv")
    void testWithSecSin(double value, double expected) {
        BaseFunc function = new BaseFunc(new Sec(new Cos(new Sin())), cosMock, sinMock, tanMock, cscMock, lnMock, logMock);
        Assertions.assertEquals(expected, function.calculate(value, functionEps), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csvIn/FunctionIn.csv")
    void testWithCsc(double value, double expected) {
        BaseFunc function = new BaseFunc(secMock, cosMock, sinMock, tanMock, new Csc(sinMock), lnMock, logMock);
        Assertions.assertEquals(expected, function.calculate(value, functionEps), eps*10);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csvIn/FunctionIn.csv")
    void testWithCscSin(double value, double expected) {
        BaseFunc function = new BaseFunc(secMock, cosMock, sinMock, tanMock, new Csc(new Sin()), lnMock, logMock);
        Assertions.assertEquals(expected, function.calculate(value, functionEps), eps*10);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csvIn/FunctionIn.csv")
    void testWithTan(double value, double expected) {
        BaseFunc function = new BaseFunc(secMock, cosMock, sinMock, new Tan(sinMock, cosMock), cscMock, lnMock, logMock);
        Assertions.assertEquals(expected, function.calculate(value, functionEps), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csvIn/FunctionIn.csv")
    void testWithLog(double value, double expected) {
        BaseFunc function = new BaseFunc(secMock, cosMock, sinMock, tanMock, cscMock, lnMock, new Log(lnMock));
        Assertions.assertEquals(expected, function.calculate(value, functionEps), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csvIn/FunctionIn.csv")
    void testWithLn(double value, double expected) {
        BaseFunc function = new BaseFunc(secMock, cosMock, sinMock, tanMock, cscMock, new Ln(), new Log());
        Assertions.assertEquals(expected, function.calculate(value, functionEps), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csvIn/FunctionIn.csv")
    void testEmpty(double value, double expected) {
        BaseFunc function = new BaseFunc();
        Assertions.assertEquals(expected, function.calculate(value, functionEps), eps * 10);
    }
}
