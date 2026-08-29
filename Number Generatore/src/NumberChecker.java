import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

public class NumberChecker implements Runnable {
    private final int[] numbers;
    private final AtomicInteger counter;
    private final Predicate<Integer> condition;

    public NumberChecker(int[] numbers, AtomicInteger counter, Predicate<Integer> condition) {
        this.numbers = numbers;
        this.counter = counter;
        this.condition = condition;
    }

    @Override
    public void run() {
        for (int num : numbers) {
            if (condition.test(num)) {
                counter.incrementAndGet();
            }
        }
    }
}
