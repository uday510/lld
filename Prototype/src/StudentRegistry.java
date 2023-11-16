import java.util.*;

public class StudentRegistry {
    private final Map<String, Student> map = new HashMap<>();
    public void register(String key, Student student) {
        map.put(key, student);
    }
    public Student get(String key) {
        return map.get(key);
    }
}
