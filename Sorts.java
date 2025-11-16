import java.util.Random;

public class Main {

    // выбором
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int t = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = t;
        }
    }

    // слиянием
    public static void mergeSort(int[] arr) {
        if (arr.length <= 1) return;
        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];
        System.arraycopy(arr, 0, left, 0, mid);
        System.arraycopy(arr, mid, right, 0, arr.length - mid);
        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }

    public static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) arr[k++] = left[i++];
            else arr[k++] = right[j++];
        }
        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }

    // пирамида
    public static void heapSort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) heapify(arr, n, i);
        for (int i = n - 1; i >= 0; i--) {
            int t = arr[0];
            arr[0] = arr[i];
            arr[i] = t;
            heapify(arr, i, 0);
        }
    }

    public static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < n && arr[l] > arr[largest]) largest = l;
        if (r < n && arr[r] > arr[largest]) largest = r;
        if (largest != i) {
            int t = arr[i];
            arr[i] = arr[largest];
            arr[largest] = t;
            heapify(arr, n, largest);
        }
    }
    // генерация тестового массива по типу
    public static int[] generate(int size, int type) {
        int[] arr = new int[size];
        Random rnd = new Random(123 + size + type);
        if (type == 0) {
            for (int i = 0; i < size; i++) arr[i] = rnd.nextInt(size * 10 + 1);
        } else if (type == 1) {
            for (int i = 0; i < size; i++) arr[i] = i;
            int keep = (int) (size * 0.75);
            for (int i = keep; i < size; i++) arr[i] = rnd.nextInt(size * 10 + 1);
            for (int i = keep; i < size; i++) {
                int j = keep + rnd.nextInt(size - keep);
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        } else if (type == 2) {
            for (int i = 0; i < size; i++) arr[i] = size - i - 1;
        } else if (type == 3) {
            int unique = Math.max(1, size / 10);
            int[] vals = new int[unique];
            for (int i = 0; i < unique; i++) vals[i] = rnd.nextInt(size * 5 + 1);
            for (int i = 0; i < size; i++) arr[i] = vals[rnd.nextInt(unique)];
        } else {
            for (int i = 0; i < size; i++) arr[i] = i;
            int swaps = Math.max(1, size / 10);
            for (int s = 0; s < swaps; s++) {
                int i = rnd.nextInt(size);
                int j = rnd.nextInt(size);
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }
        return arr;
    }

    public static int[] copyArray(int[] src) {
        int[] dst = new int[src.length];
        System.arraycopy(src, 0, dst, 0, src.length);
        return dst;
    }

    public static void main(String[] args) {
        int a = 100;
        int b = 1000;
        int c = 10000;
        int d = 5;        // число запусков
        int e = 5;        // число типов массивов

        int[] sizes = new int[]{a, b, c};
        String[] sizeNames = new String[]{"МАЛЫЙ", "СРЕДНИЙ", "БОЛЬШОЙ"};
        String[] typeNames = new String[]{"случайный", "частично отсортированный 75%", "обратно отсортированный", "множество дубликатов 10%", "почти отсортированный 90%"};

        for (int si = 0; si < sizes.length; si++) {
            int size = sizes[si];
            System.out.println("--------------------------------------------------------------");
            System.out.println(sizeNames[si] + " " + size + " " + "символов");
            for (int type = 0; type < e; type++) {
                System.out.println("---" + typeNames[type] + "---");
                int[] orig = generate(size, type);

                long totalSel = 0;
                for (int run = 0; run < d; run++) {
                    int[] arr = copyArray(orig);
                    long t1 = System.nanoTime();
                    selectionSort(arr);
                    long t2 = System.nanoTime();
                    totalSel += (t2 - t1);
                }
                System.out.println("Сортировка выбором | ср.нс. = " + (totalSel / d));

                long totalMerge = 0;
                for (int run = 0; run < d; run++) {
                    int[] arr = copyArray(orig);
                    long t1 = System.nanoTime();
                    mergeSort(arr);
                    long t2 = System.nanoTime();
                    totalMerge += (t2 - t1);
                }
                System.out.println("Сортировка слиянием | ср.нс. = " + (totalMerge / d));

                long totalHeap = 0;
                for (int run = 0; run < d; run++) {
                    int[] arr = copyArray(orig);
                    long t1 = System.nanoTime();
                    heapSort(arr);
                    long t2 = System.nanoTime();
                    totalHeap += (t2 - t1);
                }
                System.out.println("Сортировка кучей | ср.нс. = " + (totalHeap / d));
            }
        }
    }
}
