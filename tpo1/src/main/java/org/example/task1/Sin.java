package org.example.task1;

public class Sin {
    public static double power_series(double x, int n) {
        double PI2 = Math.PI * 2;

        if (x >= 0) {
            while (x > PI2) {
                x -= PI2;
            }
        } else if (x < 0) {
            while (x < PI2) {
                x += PI2;
            }
        }

        double result = 0;
        double num = x;
        double den = 1;
        int sign = 1;

        for (int i = 1; i < n; i += 2) {
            den *= i;
            result += sign * num / den;
            sign *= -1;
            den *= (i + 1);
            num *= x * x;
        }

        return result;
    }
}
