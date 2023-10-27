import java.util.*;

public class StudentRegistry {
    private Map<String, Student> map = new HashMap<>();
    void register(String key, Student student) { }
    Student get(String key) {
        return map.get(key);
    }
}
