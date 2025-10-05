import java.util.Scanner;

class Fraction {
    private int numerator;
    private int denominator;

    public void init(int n, int d) {
        if (d == 0) d = 1;
        numerator = n;
        denominator = d;
    }

    public void read() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("числитель: ");
            try {
                numerator = Integer.parseInt(sc.nextLine().trim());
                break;
            } catch (Exception ex) {
                System.out.println("введи целое!!!!");
            }
        }
        while (true) {
            System.out.print("знаменатель (не 0): ");
            try {
                int d = Integer.parseInt(sc.nextLine().trim());
                if (d == 0) { System.out.println("знаменатель не может быть 0!!!!!"); continue; }
                denominator = d;
                break;
            } catch (Exception ex) {
                System.out.println("Нужно целое число!!!!!");
            }
        }
    }

    public void display() {
        System.out.println(numerator + "/" + denominator);
    }

    public int getDenominator() { return denominator; }
    public void setDenominator(int d) { if (d != 0) denominator = d; }

    public double fractionalPart() {
        int an = Math.abs(numerator);
        int ad = Math.abs(denominator);
        int whole = an / ad;
        int fracNum = an - whole * ad;
        return (double) fracNum / ad;
    }

    public static Fraction add(Fraction a, Fraction b) {
        Fraction r = new Fraction();
        r.numerator = a.numerator * b.denominator + b.numerator * a.denominator;
        r.denominator = a.denominator * b.denominator;
        if (r.denominator == 0) r.denominator = 1;
        return r;
    }

    public Fraction() { numerator = 0; denominator = 1; }

    public static void main(String[] args) {
        System.out.println("демонстрация: ");

        Fraction a = new Fraction();
        a.init(9, 4);
        System.out.print("a = "); a.display();
        System.out.println("остаток a = " + a.fractionalPart());

        System.out.println("\nвведите дробь b:");
        Fraction b = new Fraction();
        b.read();
        System.out.print("b = "); b.display();
        System.out.println("остаток b = " + b.fractionalPart());

        Fraction c = Fraction.add(a, b);
        System.out.print("\na + b = "); c.display();

        System.out.println("знаменатель c = " + c.getDenominator());
        c.setDenominator(10);
        System.out.print("c после setDenominator = "); c.display();
    }
}