package LogsAnalyzer;
public class ActionProfile {
    String type;

    String id;

    Integer nbActions;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNbActions() {
        return nbActions;
    }

    public void setNbActions(Integer nbActions) {
        this.nbActions = nbActions;
    }

    public ActionProfile(String type, String id, Integer nbActions) {
        this.nbActions = nbActions;
        this.type = type;
        this.id = id;
    }
}