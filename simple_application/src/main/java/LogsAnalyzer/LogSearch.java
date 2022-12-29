package LogsAnalyzer;
import java.util.Date;
import org.json.JSONObject;
public class LogSearch extends Log {
    private double productPrice;

    public LogSearch(Date date, String threadName, String level, String logger_name, String action, String userId, double productPrice) {
        super(date, threadName, level, logger_name, action, userId);
        this.productPrice = productPrice;
    }

    public LogSearch(JSONObject object) {
        super(object);
        productPrice = Double.parseDouble(((String) (object.get("product price"))));
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}