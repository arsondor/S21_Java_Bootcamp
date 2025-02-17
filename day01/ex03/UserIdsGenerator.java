public class UserIdsGenerator {
    private int lastGeneratedId = 0;
    private static UserIdsGenerator instance;

    private UserIdsGenerator() {
    }

    public static UserIdsGenerator getInstance() {
        if (instance == null) {
            instance = new UserIdsGenerator();
        }
        return instance;
    }

    public Integer generateId() {
        lastGeneratedId++;
        return lastGeneratedId;
    }
}