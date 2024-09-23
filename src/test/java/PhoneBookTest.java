import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class PhoneBookTest {

    PhoneBook phoneBook = new PhoneBook();

    @BeforeEach
    public void prepare() {
        phoneBook.add("Ivan", "+79876543210");
    }

    @ParameterizedTest
    @MethodSource("sourceAdd")
    public void testAdd(String name, String phone, int expected) {
        int result = phoneBook.add(name, phone);

        Assertions.assertEquals(expected, result);
    }

    public static Stream<Arguments> sourceAdd() {
        return Stream.of(Arguments.of("Ivan", "+79876543210", 1),
                Arguments.of("Anna", "+71234567890", 2),
                Arguments.of("Ivan", "+79176543210", 2),
                Arguments.of("Alena", "+7987654321", 3)
        );
    }

    @ParameterizedTest
    @MethodSource("sourceFindByNumber")
    public void testFindByNumber(String phone, String expected) {
        String result = phoneBook.findByNumber(phone);

        Assertions.assertEquals(expected, result);
    }

    public static Stream<Arguments> sourceFindByNumber() {
        return Stream.of(Arguments.of("+79876543210", "Ivan"),
                Arguments.of("+79998765433", null)
        );
    }

    @Test
    public void testFindByName() {
        String expected = "+79876543210";

        String result = phoneBook.findByName("Ivan");

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void printAllNames() {

        phoneBook.add("Anna", "+71234567890");
        phoneBook.add("Alena", "+7987654321");

        List<String> expected = new ArrayList<>(Arrays.asList("Alena", "Anna", "Ivan"));

        List<String> result = phoneBook.printAllNames();

        Assertions.assertEquals(expected, result);
    }
}