package employee;

public class Administrator implements Employee {
    private static Administrator instance;

    private Administrator() {

    }

    public static Administrator getInstance() {
        if (instance == null) {
            instance = new Administrator();
        }
        return instance;
    }
}
