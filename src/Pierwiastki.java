import java.util.InputMismatchException;
import java.util.Scanner;

//calculating roots of ->  (x^2 -1)sinh^3x = 0
public class Pierwiastki {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double x1 = 0, x2 = 0;
        System.out.println("Podaj przedziały liczbowe: ");
        try {
            x1 = scanner.nextDouble();
            x2 = scanner.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("Podano złe dane");
            System.exit(-1);
        }
        if (x2 < x1) {
            double tmp;
            tmp = x1;
            x1 = x2;
            x2 = tmp;
        }
        System.out.println(new Newton().rozwiaz(x1));
        System.out.println(new Bisekcja().rozwiaz(x1, x2));
        System.out.println(new Sieczne().rozwiaz(x1, x2));
    }
}
class Newton {
    protected String rozwiaz(double x) {
        System.out.println("Metoda Newtona");
        int licznik = 0;
        double E = 0.000000000001;
        double x1 = x;
        do {
            licznik++;
            x1 = x1 - f(x1) / dF(x1);
        } while (!(Math.abs(f(x1)) < E));
        return "Pierwiastek: " + String.valueOf(x1) + ", ilość iteracji: "
                + String.valueOf(licznik);
    }
    private static double f(double x) {
        return (x * x - 1) * Math.pow(Math.sinh(x), 3);
    }
    private static double dF(double x) {
        return Math.pow(Math.sinh(x), 2)
                * (3 * (x * x - 1) * Math.cosh(x) + 2 * x * Math.sinh(x));
    }
}
class Bisekcja {
    protected String rozwiaz(double start, double end) {
        System.out.println("Metoda bisekcji");
        int licznik = 0;
        double E = 0.000000000001;
        double x1 = start, x2 = end, x3;
        do {
            licznik++;
            if (f(x1) * f(x2) < 0) {
                x3 = (x1 + x2) / 2.;
                if (Math.abs(f(x3)) < E)
                    break;
                else if (f(x1) * f(x3) < 0)
                    x2 = x3;
                else
                    x1 = x3;
            } else if (f(x1) * f(x2) == 0) {
                if (f(x1) == 0)
                    return String.valueOf(x1);
                else
                    return String.valueOf(x2);
            } else {
                return "\tWartości funkcji na końcach przedziału mają taki sam znak. Zmień zakres";
            }
        } while (true);
        return "Pierwiastek: " + String.valueOf(x3) + ", ilość iteracji: "
                + String.valueOf(licznik);
    }
    private static double f(double x) {
        return (x * x - 1) * Math.pow(Math.sinh(x), 3);
    }
}
class Sieczne {
    protected String rozwiaz(double start, double end) {
        System.out.println("Metoda siecznych:");
        int licznik = 0;
        double E = 0.000000000001;
        double x1 = start, x2 = end, x3 = 0;
        if (f(x1) == f(x2)) {
            System.out.println("Złe przedziały");
            System.exit(-1);
        }
        do {
            licznik++;
            if (f(x1) == f(x2))
                break;
            x3 = x2 - (f(x2) * (x2 - x1)) / (f(x2) - f(x1));
            x1 = x2;
            x2 = x3;
        } while (!(Math.abs(f(x3)) < E));
        return "Pierwiastek: " + String.valueOf(x3) + ", ilość iteracji: "
                + String.valueOf(licznik);
    }

    private double f(double x) {
        return (x * x - 1) * Math.pow(Math.sinh(x), 3);
    }
}
