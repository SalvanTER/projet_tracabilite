package Bot;
import java.util.ArrayList;
import java.util.Random;
public class UserFactory {
    private static final double LAMBDA = 0.5;

    private static final int MIN_AGE = 18;

    private static final int MAX_AGE = 90;

    private static final String[] FIRST_NAMES = new String[]{ "Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Harry", "Ivy", "Julia" };

    private static final String[] LAST_NAMES = new String[]{ "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor" };

    private static final char[] CHARACTERS = new char[]{ 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '!', '@', '#', '$', '%', '^', '&', '*' };

    private static int currentName = 0;

    private static final ArrayList<String> NAMES = new ArrayList<>();

    private static boolean generated = false;

    public static void generateNames(int count) {
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int firstNameIndex = random.nextInt(UserFactory.FIRST_NAMES.length);
            int lastNameIndex = random.nextInt(UserFactory.LAST_NAMES.length);
            NAMES.add((FIRST_NAMES[firstNameIndex] + " ") + LAST_NAMES[lastNameIndex]);
        }
    }

    public static String generateRandomName() {
        if (!generated) {
            generateNames(100);
            generated = true;
        }
        Random random = new Random();
        currentName = random.nextInt(NAMES.size());
        return NAMES.get(currentName);
    }

    public static String getMail() {
        return NAMES.get(currentName).replace(" ", ".") + "@mail.com";
    }

    public static int generateRandomAge() {
        Random random = new Random();
        double age = (-Math.log(1 - random.nextDouble())) / LAMBDA;
        age = Math.max(age, MIN_AGE);
        age = Math.min(age, MAX_AGE);
        return ((int) (Math.round(age)));
    }

    public static String generateRandomPassword() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(UserFactory.CHARACTERS.length);
            sb.append(CHARACTERS[index]);
        }
        return sb.toString();
    }
}