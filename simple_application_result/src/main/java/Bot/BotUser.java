package Bot;
import java.util.List;
import java.util.Random;
import model.Product;
import model.Repository;
import model.User;
public class BotUser extends User {
    private static final Random random = new Random();

    private Scenario scenario;

    private int[] memory;

    private int maxMemorySize;

    public BotUser() {
        super(UserFactory.generateRandomName(), UserFactory.generateRandomAge(), UserFactory.getMail(), UserFactory.generateRandomPassword());
        scenario = new Scenario();
        maxMemorySize = 10 + random.nextInt(10);
        memory = new int[maxMemorySize];
        for (int i = 0; i < maxMemorySize; i++) {
            memory[i] = -1;
        }
    }

    public void doScenario(Repository repo) {
        scenario.generate();
        for (Step step : scenario.getSteps()) {
            doStep(repo, step);
        }
    }

    private void doStep(Repository repo, Step step) {
        repo.setUser(this);
        switch (step) {
            case ADD :
                Product product = ProductFactory.productImpl();
                try {
                    repo.addProduct(product);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case DISPLAY :
                List<Product> products = repo.displayProducts();
                if (products.size() > 0) {
                    for (int i = 0; i < maxMemorySize; i++) {
                        memory[i] = products.get(random.nextInt(products.size())).getId();
                    }
                }
                break;
            case REMOVE :
                try {
                    repo.deleteProduct(memory[random.nextInt(maxMemorySize)]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case FETCH :
                try {
                    memory[0] = repo.fetchProductById(memory[random.nextInt(maxMemorySize)]).getId();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case UPDATE :
                try {
                    repo.updateProduct(memory[0], ProductFactory.nameGenerator(), ProductFactory.priceGenerator(), ProductFactory.expirationDateGenerator());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}