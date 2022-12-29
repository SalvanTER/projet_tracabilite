package LogsAnalyzer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
public class LogParser {
    private String path;

    private BufferedReader br;

    private ArrayList<IProcessor> processors;

    public LogParser(String path) {
        processors = new ArrayList<>();
        this.path = path;
        try {
            FileReader fr = new FileReader(this.path);
            br = new BufferedReader(fr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addProcessor(IProcessor processor) {
        processors.add(processor);
    }

    public void run() {
        process();
        finish();
    }

    private void finish() {
        try {
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void process() {
        String line;
        JSONObject obj;
        try {
            while ((line = br.readLine()) != null) {
                obj = ((JSONObject) (new JSONObject(line).get("log")));
                for (IProcessor ap : processors) {
                    if (ap instanceof IProcessorLog) {
                        ((IProcessorLog) (ap)).process(new Log(obj));
                    } else if ((ap instanceof IProcessorLogSearch) && obj.has("product price")) {
                        ((IProcessorLogSearch) (ap)).process(new LogSearch(obj));
                    }
                }
            } 
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}