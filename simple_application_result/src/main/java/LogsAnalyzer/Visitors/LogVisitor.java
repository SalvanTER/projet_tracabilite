package LogsAnalyzer.Visitors;
import LogsAnalyzer.ActionProfile;
import LogsAnalyzer.IProcessorLog;
import LogsAnalyzer.Log;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;
public class LogVisitor implements IProcessorLog {
    ArrayList<Log> logs = new ArrayList<>();

    HashMap<String, ActionProfile> userActionCounter;

    @Override
    public void process(Log log) {
        logs.add(log);
    }

    public Set<ActionProfile> usersMostlyPerformedAction(String action) {
        userActionCounter = new HashMap();
        for (Log log : logs) {
            if (log.getAction().equals(action)) {
                String userId = log.getUserId();
                if (userActionCounter.containsKey(userId))
                    userActionCounter.get(userId).setNbActions(userActionCounter.get(userId).getNbActions() + 1);
                else
                    userActionCounter.put(userId, new ActionProfile(action, userId, 1));

            }
        }
        actionProfilesToJson(action);
        return top10percentHighestsValues(sortHashMap(userActionCounter));
    }

    private List<Map.Entry<String, ActionProfile>> sortHashMap(HashMap<String, ActionProfile> map) {
        List<Map.Entry<String, ActionProfile>> sortedEntries = new ArrayList<>(map.entrySet());
        sortedEntries.sort((e1, e2) -> e2.getValue().getNbActions().compareTo(e1.getValue().getNbActions()));
        return sortedEntries;
    }

    public void actionProfilesToJson(String action) {
        // Create a JSONArray to store the SearchProfile objects
        JSONArray jsonArray = new JSONArray();
        // Loop through the ArrayList and add each SearchProfile object to the JSONArray
        for (ActionProfile profile : userActionCounter.values()) {
            // Create a JSONObject for the current SearchProfile object
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", profile.getId());
            jsonObject.put("nbActions", profile.getNbActions());
            jsonObject.put("action type", action);
            // Add the JSONObject to the JSONArray
            jsonArray.put(jsonObject);
        }
        try {
            // Write the JSONArray to a file
            FileWriter writer = new FileWriter(action + "_action_profiles.json");
            writer.write(jsonArray.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Set<ActionProfile> top10percentHighestsValues(List<Map.Entry<String, ActionProfile>> sortedEntries) {
        int numEntries = sortedEntries.size();
        int numTopEntries = ((int) (Math.ceil(numEntries * 0.1)));// Arrondi au nombre entier supérieur

        Set<ActionProfile> topKeys = new HashSet<>();
        for (int i = 0; i < numTopEntries; i++) {
            Map.Entry<String, ActionProfile> entry = sortedEntries.get(i);
            topKeys.add(entry.getValue());
        }
        return topKeys;
    }
}