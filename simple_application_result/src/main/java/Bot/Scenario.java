package Bot;
import java.util.ArrayList;
import java.util.Random;
public class Scenario {
    private ArrayList<Step> steps;

    public Scenario() {
        steps = new ArrayList<>();
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void generate() {
        Random random = new Random();
        switch (random.nextInt(20)) {
            case 0 :
                for (int i = 0; i < random.nextInt(10); i++) {
                    steps.add(Step.ADD);
                }
                steps.add(Step.DISPLAY);
                break;
            case 1 :
                steps.add(Step.REMOVE);
                steps.add(Step.DISPLAY);
                break;
            case 2 :
                steps.add(Step.UPDATE);
                steps.add(Step.DISPLAY);
                break;
            case 3 :
                steps.add(Step.DISPLAY);
                steps.add(Step.REMOVE);
                break;
            case 4 :
                steps.add(Step.DISPLAY);
                steps.add(Step.UPDATE);
                break;
            case 5 :
                steps.add(Step.FETCH);
                steps.add(Step.UPDATE);
                break;
            case 6 :
                for (int i = 10; i < random.nextInt(50); i++) {
                    steps.add(Step.FETCH);
                }
                break;
            case 7 :
                for (int i = 0; i < random.nextInt(10); i++) {
                    steps.add(Step.UPDATE);
                }
                break;
            case 8 :
                for (int i = 0; i < random.nextInt(10); i++) {
                    steps.add(Step.ADD);
                    steps.add(Step.REMOVE);
                }
                break;
            case 9 :
                for (int i = 0; i < random.nextInt(10); i++) {
                    steps.add(Step.DISPLAY);
                }
                break;
            case 10 :
                for (int i = 0; i < random.nextInt(10); i++) {
                    steps.add(Step.REMOVE);
                }
                break;
            case 11 :
                steps.add(Step.REMOVE);
                steps.add(Step.ADD);
                break;
            case 12 :
                for (int i = 0; i < random.nextInt(10); i++) {
                    steps.add(Step.ADD);
                }
                break;
            case 13 :
                for (int i = 0; i < random.nextInt(10); i++) {
                    switch (random.nextInt(5)) {
                        case 0 :
                            steps.add(Step.ADD);
                            break;
                        case 1 :
                            steps.add(Step.REMOVE);
                            break;
                        case 2 :
                            steps.add(Step.FETCH);
                            break;
                        case 3 :
                            steps.add(Step.DISPLAY);
                            break;
                        case 4 :
                            steps.add(Step.UPDATE);
                            break;
                        default :
                            break;
                    }
                }
                break;
            default :
                break;
        }
    }
}