package LogsAnalyzer.Visitors;
import LogsAnalyzer.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;
public class LogSearchVisitor implements IProcessorLogSearch {
    ArrayList<LogSearch> logs = new ArrayList<>();

    HashMap<String, SearchProfile> userSearchs;

    @Override
    public void process(LogSearch logSearch) {
        logs.add(logSearch);
    }

    public Set<SearchProfile> usersMostlySearchExpensiveProducts() {
        userSearchs = new HashMap<>();
        for (LogSearch log : logs) {
            String userId = log.getUserId();
            if (!userSearchs.containsKey(userId))
                userSearchs.put(userId, new SearchProfile(userId));

            userSearchs.get(userId).addProductPrice(log.getProductPrice());
        }
        return top10percentHighestsValues(sortHashMap(userSearchs));
    }

    private Set<SearchProfile> top10percentHighestsValues(List<Map.Entry<String, SearchProfile>> sortedEntries) {
        int numEntries = sortedEntries.size();
        int numTopEntries = ((int) (Math.ceil(numEntries * 0.1)));// Arrondi au nombre entier supérieur

        Set<SearchProfile> topKeys = new HashSet<>();
        for (int i = 0; i < numTopEntries; i++) {
            Map.Entry<String, SearchProfile> entry = sortedEntries.get(i);
            topKeys.add(entry.getValue());
        }
        return topKeys;
    }

    public void searchProfilesToJson() {
        // Create a JSONArray to store the SearchProfile objects
        JSONArray jsonArray = new JSONArray();
        // Loop through the ArrayList and add each SearchProfile object to the JSONArray
        for (SearchProfile profile : userSearchs.values()) {
            // Create a JSONObject for the current SearchProfile object
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", profile.getId());
            jsonObject.put("productsPrice", profile.getProductsPrice());
            // Add the JSONObject to the JSONArray
            jsonArray.put(jsonObject);
        }
        try {
            // Write the JSONArray to a file
            FileWriter writer = new FileWriter("search_profiles.json");
            writer.write(jsonArray.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Map.Entry<String, SearchProfile>> sortHashMap(HashMap<String, SearchProfile> map) {
        List<Map.Entry<String, SearchProfile>> sortedEntries = new ArrayList<>(map.entrySet());
        sortedEntries.sort((e1, e2) -> {
            try {
                return ((Double) (e2.getValue().averagePriceProducts())).compareTo(e1.getValue().averagePriceProducts());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        });
        return sortedEntries;
    }
}