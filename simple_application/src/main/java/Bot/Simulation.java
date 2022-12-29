package Bot;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import model.Repository;
import org.json.JSONArray;
public class Simulation {
    private ArrayList<BotUser> bots;

    public ArrayList<BotUser> getBots() {
        return bots;
    }

    public void setBots(ArrayList<BotUser> bots) {
        this.bots = bots;
    }

    private Repository repo;

    private static final Random random = new Random();

    public Simulation(int nbBots, Repository repo) {
        bots = new ArrayList<>();
        for (int i = 0; i < nbBots; i++) {
            bots.add(new BotUser());
        }
        this.repo = repo;
    }

    public void run() {
        for (int j = 10; j < random.nextInt(20); j++) {
            for (BotUser bot : bots) {
                for (int i = 0; i < random.nextInt(2); i++) {
                    bot.doScenario(repo);
                }
            }
        }
    }

    public void saveBotUsers() {
        // Creating an ArrayList of String
        // Creating a JSONArray object
        JSONArray jsonArray = new JSONArray();
        // Looping through the list
        for (BotUser bot : bots) {
            // Adding the JSONObject to the JSONArray
            jsonArray.put(bot.tJsonObject());
        }
        try {
            FileWriter file = new FileWriter("users.json");
            file.write(jsonArray.toString());
            file.flush();
            file.close();
        } catch (Exception e) {
            System.out.println("Error in writing JSONObject to file");
            e.printStackTrace();
        }
    }

    public void saveProducts() {
        repo.saveProducts();
    }
}