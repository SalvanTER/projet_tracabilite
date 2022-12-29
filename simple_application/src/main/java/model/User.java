package model;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
public class User {
    private String id;

    private String name;

    private int age;

    private String email;

    private String password;

    public User(String name, int age, String email, String password) {
        this.id = generateUniqueID();
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    private String generateUniqueID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public JSONObject tJsonObject() {
        // create a sample object
        Map<String, Object> sampleObject = new HashMap<String, Object>();
        sampleObject.put("name", this.getName());
        sampleObject.put("age", this.getAge());
        sampleObject.put("email", this.getEmail());
        sampleObject.put("id", this.getId());
        sampleObject.put("password", this.getPassword());
        // convert object to JSON
        JSONObject jsonObject = new JSONObject(sampleObject);
        // print the converted object
        return jsonObject;
    }
}