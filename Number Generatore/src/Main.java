import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        int arraySize = 100000;
        int[] numbers = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            numbers[i] = NumberGenerator.generateNumber(10, 9999);
        }

        AtomicInteger twoDigitCount = new AtomicInteger(0);
        AtomicInteger threeDigitCount = new AtomicInteger(0);
        AtomicInteger fourDigitCount = new AtomicInteger(0);


        Thread twoDigitThread = new Thread(new NumberChecker(numbers, twoDigitCount, num -> num >= 10 && num <= 99),
                "Двузначные");

        Thread threeDigitThread = new Thread(new NumberChecker(numbers, threeDigitCount, num -> num >= 100 && num <= 999),
                "Трёхзначные");

        Thread fourDigitThread = new Thread(new NumberChecker(numbers, fourDigitCount, num -> num >= 1000 && num <= 9999),
                "Четырёхзначные");


        twoDigitThread.start();
        threeDigitThread.start();
        fourDigitThread.start();


        try {
            twoDigitThread.join();
            threeDigitThread.join();
            fourDigitThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Двухзначных чисел " + twoDigitCount.get() + " шт.");
        System.out.println("Трёхзначных чисел " + threeDigitCount.get() + " шт.");
        System.out.println("Четырёхзначных чисел " + fourDigitCount.get() + " шт.");
    }
}
