package Menu;
import java.util.ArrayList;
import java.util.Scanner;
import model.Repository;
import model.User;
public class Menu {
    public static ArrayList<User> listUsers = new ArrayList<>();

    public static Repository repo = new Repository();

    public static User currentUser;

    public static void run() {
        // Creating a list to store user objects
        // Creating a Scanner object to read input from user
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // printing menu for user
            System.out.println("Choose one of the following options:");
            System.out.println("1. Log in");
            System.out.println("2. Sign up");
            System.out.println("3. Exit");
            // reading input from user
            int choice = scanner.nextInt();
            // Switch case for user choice
            switch (choice) {
                case 1 :
                    // User wants to log in
                    System.out.println("Enter your email: ");
                    scanner.nextLine();
                    String mail = scanner.nextLine();
                    System.out.println("Enter your Password: ");
                    String password = scanner.next();
                    // Checking if user exist in the list with given id and password
                    boolean isUserExist = checkUser(mail, password);
                    if (isUserExist) {
                        System.out.println("Logged in successfully");
                        repo.runMenuRepo(currentUser);
                    } else {
                        System.out.println("User not found");
                    }
                    break;
                case 2 :
                    System.out.println("Enter your Name: ");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    System.out.println("Enter your Age: ");
                    int age = scanner.nextInt();
                    System.out.println("Enter your Email: ");
                    String email = scanner.next();
                    System.out.println("Enter your Password: ");
                    String newPassword = scanner.next();
                    // Creating a new user object
                    User newUser = new User(name, age, email, newPassword);
                    // Adding the new user to the list
                    listUsers.add(newUser);
                    System.out.println("Successfully signed up");
                    break;
                case 3 :
                    // User wants to exit
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default :
                    // Invalid choice
                    System.out.println("Invalid Choice");
                    break;
            }
        } 
    }

    private static boolean checkUser(String name, String pwd) {
        for (User user : listUsers) {
            System.out.println(user.getName());
            if (user.getEmail().equals(name) && user.getPassword().equals(pwd)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }
}