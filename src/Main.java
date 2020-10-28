import java.util.Random;

public class Main {

    private static int capacity = 100000;

    private static long startTime;
    private static long stopTime;

    public static void main(String[] args) {
        Random random = new Random();
        Array<Integer> array = new Array<>(capacity);
        for (int i = 0; i < capacity; i++) {
            array.add(random.nextInt());
        }

        Array<Integer> array2 = new Array<>(array);
        Array<Integer> array3 = new Array<>(array);

        startTime();
        array.sortBubble();
        stopTime();
        System.out.println("Время пузырьковой сортировки: " + timeDifference());

        startTime();
        array2.sortSelect();
        stopTime();
        System.out.println("Время сортировки выбором: " + timeDifference());

        startTime();
        array3.sortInsert();
        stopTime();
        System.out.println("Время сортировки вставкой: " + timeDifference());
    }

    public static void startTime() {
        startTime = System.currentTimeMillis();
    }

    public static void stopTime() {
        stopTime = System.currentTimeMillis();
    }

    public static long timeDifference() {
        return stopTime - startTime;
    }

//    Время пузырьковой сортировки: 40750
//    Время сортировки выбором: 17054
//    Время сортировки вставкой: 11778
}
