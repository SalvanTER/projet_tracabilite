package LogsAnalyzer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONObject;
public class Log {
    private Date date;

    private String threadName;

    private String level;

    private String logger_name;

    private String action;

    private String userId;

    public Log(Date date, String threadName, String level, String logger_name, String action, String userId) {
        this.date = date;
        this.threadName = threadName;
        this.level = level;
        this.logger_name = logger_name;
        this.action = action;
        this.userId = userId;
    }

    public Log(JSONObject object) {
        try {
            this.date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(((String) (object.get("date"))));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.action = ((String) (object.get("action")));
        this.level = ((String) (object.get("level")));
        this.logger_name = ((String) (object.get("logger_name")));
        this.userId = ((String) (object.get("user")));
        this.threadName = ((String) (object.get("thread name")));
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLogger_name() {
        return logger_name;
    }

    public void setLogger_name(String logger_name) {
        this.logger_name = logger_name;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}