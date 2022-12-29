package model;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
public class Product {
    private static int i = 0;

    private int id;

    private String name;

    private double price;

    private Date expirationDate;

    public Product(String name, double price, Date expirationDate) {
        this.name = name;
        this.price = price;
        this.expirationDate = expirationDate;
        // generate id
        this.id = generateId();
    }

    private int generateId() {
        return i++;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public JSONObject tJsonObject() {
        // create a sample object
        Map<String, Object> sampleObject = new HashMap<String, Object>();
        sampleObject.put("name", this.getName());
        sampleObject.put("id", this.getId());
        sampleObject.put("price", this.getPrice());
        sampleObject.put("expirationDate", this.getExpirationDate());
        // convert object to JSON
        JSONObject jsonObject = new JSONObject(sampleObject);
        // print the converted object
        return jsonObject;
    }
}