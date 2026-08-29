import java.util.Random;

public class NumberGenerator {
    private static final Random random = new Random();

    public static int generateNumber(int min, int max) {
        return min + random.nextInt(max - min + 1);
    }
}
