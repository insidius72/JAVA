import java.util.Scanner;

class Elem {
    public int w;
    public double p;

    public void init() {
        w = 0;
        p = 0.0;
    }

    public void read(Scanner sc) {
        System.out.print("вес: ");
        w = sc.nextInt();
        System.out.print("цена за грамм: ");
        p = sc.nextDouble();
        sc.nextLine();
    }

    public void display() {
        System.out.println("вес(г): " + w + " цена/г: " + p + " стоимость: " + cost());
    }

    public double cost() {
        return w * p;
    }
}

class Products {
    public Elem e1, e2, e3;
    public int q1, q2, q3;
    public double assemblyCost;

    public Products() {
        e1 = new Elem();
        e2 = new Elem();
        e3 = new Elem();
    }

    public void init() {
        e1.init();
        e2.init();
        e3.init();
        q1 = q2 = q3 = 0;
        assemblyCost = 0.0;
    }

    public void read(Scanner sc) {
        e1.read(sc);
        e2.read(sc);
        e3.read(sc);
        System.out.print("колво  1: ");
        q1 = sc.nextInt();
        System.out.print("колво  2: ");
        q2 = sc.nextInt();
        System.out.print("колво 3: ");
        q3 = sc.nextInt();
        System.out.print("прайс сборки: ");
        assemblyCost = sc.nextDouble();
        sc.nextLine();
    }

    public double totalCost() {
        return e1.cost() * q1 + e2.cost() * q2 + e3.cost() * q3 + assemblyCost;
    }

    public Elem maxCostElement() {
        Elem max = e1;
        if (e2.cost() > max.cost()) max = e2;
        if (e3.cost() > max.cost()) max = e3;
        return max;
    }

    public void display() {
        e1.display();
        System.out.println("кол-во 1: " + q1);
        e2.display();
        System.out.println("кол-во 2: " + q2);
        e3.display();
        System.out.println("кол-во 3: " + q3);
        System.out.println("стоимость сборки: " + assemblyCost);
        System.out.println("тотал: " + totalCost());
        System.out.println("max по цене: " + maxCostElement().cost());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Products p = new Products();
        p.init();

        p.read(sc);
        p.display();

        sc.close();
    }
}
