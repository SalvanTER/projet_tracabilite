package model;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
public class Repository {
    private List<Product> products;

    private User user = null;

    public Repository() {
        products = new ArrayList<>();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> displayProducts() {
        LOGGER.trace("\"action\" : \"read\", \"user\":\""+ user.getId() + "\"");
        return products;
    }

    public Product fetchProductById(int id) throws Exception {
        for (Product product : products) {
            if (product.getId() == id) {
                LOGGER.trace("\"action\" : \"read\", \"user\":\""+ user.getId() + "\""+ ", \"product price\" : \"" + product.getPrice() + "\"");
                return product;
            }
        }
        throw new Exception("Product not found");
    }

    public void addProduct(Product product) throws Exception {
        for (Product prod : products) {
            if (prod.getId() == product.getId()) {
                throw new Exception("Product already exists");
            }
        }
        products.add(product);
        LOGGER.trace("\"action\" : \"write\", \"user\":\""+ user.getId() + "\"");
        return;
    }

    public void deleteProduct(int id) throws Exception {
        for (Product product : products) {
            if (product.getId() == id) {
                products.remove(product);
                LOGGER.trace("\"action\" : \"write\", \"user\":\""+ user.getId() + "\"");
                return;
            }
        }
        throw new Exception("Product not found");
    }

    public void updateProduct(int id, String name, double price, Date dateexp) throws Exception {
        for (Product prod : products) {
            if (prod.getId() == id) {
                prod.setName(name);
                prod.setPrice(price);
                prod.setExpirationDate(dateexp);
                LOGGER.trace("\"action\" : \"write\", \"user\":\""+ user.getId() + "\"");
                return;
            }
        }
        throw new Exception("Product not found");
    }

    public void runMenuRepo(User user) {
        Scanner scanner = new Scanner(System.in);
        this.user = user;
        boolean exit = false;
        while (!exit) {
            System.out.println("************************\n1. Display Products");
            System.out.println("2. Fetch a Product by ID");
            System.out.println("3. Add a Product");
            System.out.println("4. Delete a Product by ID");
            System.out.println("5. Update a Product's Info");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 :
                    for (Product product : displayProducts()) {
                        System.out.println((((((("Product ID: " + product.getId()) + "\nName: ") + product.getName()) + "\nPrice: ") + product.getPrice()) + "\nExpiration Date: ") + product.getExpirationDate());
                    }
                    break;
                case 2 :
                    System.out.println("Enter the ID of the product to fetch: ");
                    int id = scanner.nextInt();
                    Product p;
                    try {
                        p = this.fetchProductById(id);
                        if (p != null) {
                            System.out.println((((((("ID: " + p.getId()) + " Name: ") + p.getName()) + " Price: ") + p.getPrice()) + " Expiration Date: ") + p.getExpirationDate());
                        } else {
                            System.out.println("No product found with the given ID");
                        }
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    break;
                case 3 :
                    System.out.println("Enter the name of the new product: ");
                    scanner.nextLine();
                    String newName = scanner.nextLine();
                    System.out.println("Enter the price of the new product: ");
                    double newPrice = scanner.nextDouble();
                    System.out.println("Enter the expiration date of the new product (dd/mm/yyyy): ");
                    Date newExpirationDate = readDate();
                    Product newProduct = new Product(newName, newPrice, newExpirationDate);
                    try {
                        this.addProduct(newProduct);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case 4 :
                    System.out.println("Enter the ID of the product to delete: ");
                    int deleteId = scanner.nextInt();
                    try {
                        this.deleteProduct(deleteId);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case 5 :
                    System.out.print("Enter the ID of the product to update: ");
                    int updateId = scanner.nextInt();
                    System.out.print("Enter the new name of the product: ");
                    scanner.nextLine();
                    String updateName = scanner.nextLine();
                    System.out.print("Enter the new price of the product: ");
                    double updatePrice = scanner.nextDouble();
                    System.out.print("Enter the new expiration date of the product (dd/mm/yyyy): ");
                    Date updateExpirationDate = readDate();
                    try {
                        this.updateProduct(updateId, updateName, updatePrice, updateExpirationDate);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 6 :
                    System.out.println("Successful logout!");
                    exit = true;
                    break;
            }
        } 
    }

    private static Date readDate() {
        Scanner scanner = new Scanner(System.in);
        String dateString = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }
        return date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void saveProducts() {
        // Creating an ArrayList of String
        // Creating a JSONArray object
        JSONArray jsonArray = new JSONArray();
        // Looping through the list
        for (Product bot : products) {
            // Adding the JSONObject to the JSONArray
            jsonArray.put(bot.tJsonObject());
        }
        try {
            FileWriter file = new FileWriter("products.json");
            file.write(jsonArray.toString());
            file.flush();
            file.close();
        } catch (Exception e) {
            System.out.println("Error in writing JSONObject to file");
            e.printStackTrace();
        }
    }

    public static final Logger LOGGER = LogManager.getLogger(Repository.class);
}