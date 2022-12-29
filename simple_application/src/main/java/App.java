import Bot.BotUser;
import Bot.Simulation;
import LogsAnalyzer.ActionProfile;
import LogsAnalyzer.LogParser;
import LogsAnalyzer.SearchProfile;
import LogsAnalyzer.Visitors.LogSearchVisitor;
import LogsAnalyzer.Visitors.LogVisitor;
import java.util.ArrayList;
import java.util.Scanner;
import model.Repository;
import model.User;
public class App {
    private static Simulation simulation;

    private static LogParser logParser;

    private static Repository repo = new Repository();

    private static ArrayList<User> users = new ArrayList<>();

    private static Scanner scanner = new Scanner(System.in);

    private static User currentUser;

    private static final LogVisitor logVisitor = new LogVisitor();

    private static final LogSearchVisitor logSearchVisitor = new LogSearchVisitor();

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Display the menu
            System.out.println("Choose an option:");
            System.out.println("1. Simulation");
            System.out.println("2. Application");
            // Read the user's selection
            int selection = scanner.nextInt();
            // Process the user's selection
            switch (selection) {
                case 1 :
                    System.out.println("Running simulation...");
                    simulation();
                    System.out.println("Ending simulation...");
                    System.exit(0);
                case 2 :
                    // Application option selected
                    System.out.println("Running application...");
                    application();
                    System.out.println("Ending application...");
                    System.out.println(0);
                    break;
                default :
                    // Invalid selection
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
        } 
    }

    private static void printUsersMostlyAction(String action) throws Exception {
        System.out.println(("Top 10% users mostly " + action) + "s in repository:");
        for (ActionProfile p : logVisitor.usersMostlyPerformedAction(action)) {
            if (simulation != null) {
                User user = getBotUserFromId(p.getId(), simulation.getBots());
                System.out.println(((((("User " + user.getName()) + " performed ") + p.getNbActions()) + " ") + action) + "s");
            }
        }
    }

    private static User getBotUserFromId(String id, ArrayList<BotUser> bots) throws Exception {
        for (User user : bots) {
            if (user.getId().equals(id))
                return user;

        }
        throw new Exception("User not found");
    }

    private static void printUsersMostlySearchExpensiveProducts() throws Exception {
        System.out.println("Top 10% users mostly search expensive products in repository:");
        for (SearchProfile p : logSearchVisitor.usersMostlySearchExpensiveProducts()) {
            if (simulation != null) {
                User user = getBotUserFromId(p.getId(), simulation.getBots());
                System.out.println((("User " + user.getName()) + " search products with average price : ") + p.averagePriceProducts());
            }
        }
    }

    private static void simulation() throws Exception {
        simulation = new Simulation(10, repo);
        for (int i = 0; i < 10; i++) {
            simulation.run();
        }
        simulation.saveBotUsers();
        simulation.saveProducts();
        runParser();
    }

    private static void runParser() throws Exception {
        logParser = new LogParser("./trace.json");
        logParser.addProcessor(logVisitor);
        logParser.addProcessor(logSearchVisitor);
        logParser.run();
        printUsersMostlyAction("read");
        printUsersMostlyAction("write");
        printUsersMostlySearchExpensiveProducts();
        logSearchVisitor.searchProfilesToJson();
    }

    private static void application() throws Exception {
        while (true) {
            // Display the menu
            System.out.println("Choose an option:");
            System.out.println("1. Login");
            System.out.println("2. Sign in");
            System.out.println("3. Exit");
            // Read the user's selection
            int selection = scanner.nextInt();
            // Process the user's selection
            switch (selection) {
                case 1 :
                    System.out.println("Logging in...");
                    login();
                    repo.runMenuRepo(currentUser);
                    break;
                case 2 :
                    // Sign in option selected
                    System.out.println("Signing in...");
                    signin();
                    break;
                case 3 :
                    repo.saveProducts();
                    runParser();
                    System.exit(0);
                default :
                    // Invalid selection
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
        } 
    }

    private static void login() {
        while (true) {
            // Prompt the user for their name
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            // Validate the name
            if (name.length() < 3) {
                System.out.println("Name must be at least 3 characters long. Please try again.");
                continue;
            }
            // Prompt the user for their password
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();
            // Validate the password
            if (password.length() < 8) {
                System.out.println("Password must be at least 8 characters long. Please try again.");
                continue;
            }
            // Check if an User object with the same name and password exists in the ArrayList
            boolean found = false;
            for (User user : users) {
                if (user.getName().equals(name) && user.getPassword().equals(password)) {
                    found = true;
                    currentUser = user;
                    break;
                }
            }
            if (found) {
                System.out.println(("Welcome, " + name) + "!");
                break;
            } else {
                System.out.println("Invalid name or password. Please try again.");
            }
            break;
        } 
    }

    private static void signin() {
        while (true) {
            // Prompt the user for their name
            System.out.print("Enter your name(at least 3 characters long): ");
            String name = scanner.nextLine();
            // Validate the name
            if (name.length() < 3) {
                System.out.println("Name must be at least 3 characters long. Please try again.");
                continue;
            }
            // Prompt the user for their age
            System.out.print("Enter your age(Age must be a positive number): ");
            int age = scanner.nextInt();
            scanner.nextLine();// consume the newline character

            // Validate the age
            if (age < 0) {
                System.out.println("Age must be a positive number. Please try again.");
                continue;
            }
            // Prompt the user for their email
            System.out.print("Enter your email(must contains @): ");
            String email = scanner.nextLine();
            // Validate the email (you can add additional validation rules here)
            if (!email.contains("@")) {
                System.out.println("Email must be a valid email address. Please try again.");
                continue;
            }
            // Prompt the user for their password
            System.out.print("Enter your password(must be at least 8 characters long): ");
            String password = scanner.nextLine();
            // Validate the password
            if (password.length() < 8) {
                System.out.println("Password must be at least 8 characters long. Please try again.");
                continue;
            }
            // Create a new User object with the entered name, age, email, and password
            users.add(new User(name, age, email, password));
            break;
        } 
    }

    public User getUserFromId(String id, ArrayList<User> users) throws Exception {
        for (User user : users) {
            if (user.getId().equals(id))
                return user;

        }
        throw new Exception("User not found");
    }
}