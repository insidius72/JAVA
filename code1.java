import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Remove {
    public static void main(String[] args) {
        Scanner a = new Scanner(System.in);
        int b = 0;
        while (true) {
            System.out.print("size: ");
            String c = a.nextLine().trim();
            try { b = Integer.parseInt(c); if (b > 0) break; }
            catch (Exception ignored) {}
            System.out.println("retry");
        }
        System.out.println("Введите " + b + " целых чисел:");
        List<Integer> d = new ArrayList<>();
        List<Integer> e = new ArrayList<>();
        int f = 0;
        while (f < b) {
            String g = a.next();
            try {
                int h = Integer.parseInt(g);
                d.add(h);
                if (!e.contains(h)) e.add(h);
                f++;
            } catch (NumberFormatException ex) {
                System.out.println("Переделай братан \"" + g + "\". Введи ещё раз элемент " + (f + 1) + ":");
            }
        }
        System.out.println("new size: " + e.size());
        for (int i = 0; i < e.size(); i++) System.out.println(e.get(i));
        a.close();
    }
}

