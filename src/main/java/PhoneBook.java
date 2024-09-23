import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PhoneBook {

    public static Map<String, String> book = new TreeMap<>();

    public int add(String name, String phone) {
        if (!book.containsKey(name)) {
            book.put(name, phone);
        }
        return book.size();
    }

    public String findByNumber(String phone) {
        return book.entrySet()
                .stream()
                .filter(v -> v.getValue().equals(phone))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    public String findByName(String name) {
        return book.get(name);
    }

    public List<String> printAllNames() {
        return new ArrayList<>(book.keySet());
    }
}
