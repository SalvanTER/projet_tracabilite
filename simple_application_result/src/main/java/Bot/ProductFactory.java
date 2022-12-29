package Bot;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import model.Product;
public class ProductFactory {
    private static final Random random = new Random();

    private static final String[] TYPE = new String[]{ "Apple", "Banana", "Orange", "Grapes", "Mango", "Peach", "Pineapple", "Strawberry", "Blueberry", "Raspberry" };

    private static final String[] COLOR = new String[]{ "Red", "Orange", "Yellow", "Green", "Blue", "Indigo", "Violet" };

    private static final String[] SIZE = new String[]{ "Big", "Small", "Medium" };

    public static Product productImpl() {
        return new Product(nameGenerator(), priceGenerator(), expirationDateGenerator());
    }

    public static Date expirationDateGenerator() {
        Calendar calendar = Calendar.getInstance();
        Random random = new Random();
        int numDays = random.nextInt(365);
        calendar.add(Calendar.DATE, numDays);
        return calendar.getTime();
    }

    public static double priceGenerator() {
        Random random = new Random();
        return ((((100 * random.nextDouble()) * random.nextDouble()) * random.nextDouble()) * 100) / random.nextInt(5, 10);
    }

    public static String nameGenerator() {
        return (((SIZE[random.nextInt(ProductFactory.SIZE.length)] + " ") + COLOR[random.nextInt(ProductFactory.COLOR.length)]) + " ") + TYPE[random.nextInt(ProductFactory.TYPE.length)];
    }
}