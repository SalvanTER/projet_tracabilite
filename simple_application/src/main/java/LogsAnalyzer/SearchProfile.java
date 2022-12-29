package LogsAnalyzer;
import java.util.ArrayList;
public class SearchProfile {
    String id;

    ArrayList<Double> productsPrice;

    public SearchProfile(String id) {
        this.id = id;
        this.productsPrice = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Double> getProductsPrice() {
        return productsPrice;
    }

    public void addProductPrice(Double productPrice) {
        this.productsPrice.add(productPrice);
    }

    public double averagePriceProducts() {
        // average price products
        double sum = 0;
        for (int i = 0; i < productsPrice.size(); i++) {
            sum += productsPrice.get(i);
        }
        return sum / productsPrice.size();
    }
}