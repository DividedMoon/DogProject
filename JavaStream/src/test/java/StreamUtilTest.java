import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StreamUtilTest {
    @Test
    public void test1() {
        System.out.println("Testing if [aaron, frank, william, gilliam] returns [AARON, FRANK, WILLIAM, GILLIAM]");
        assertTrue(StreamUtil.mapToUppercase("aaron", "frank", "william", "gilliam")
                .containsAll(asList("AARON", "FRANK", "WILLIAM", "GILLIAM")));

        System.out.println("Testing if [cegeka] returns [CEGEKA]");
        assertTrue(StreamUtil.mapToUppercase("cegeka")
                .contains("CEGEKA"));
    }

    @Test
    public void test2() {
        System.out.println("Testing if [william, jones, aaron, seppe, frank, gilliam] returns 14");
        assertEquals(StreamUtil.getTotalNumberOfLettersOfNamesLongerThanFive("william", "jones", "aaron", "seppe",
                "frank", "gilliam"), 14);

        System.out.println("Testing if [aaron] returns 0");
        assertEquals(StreamUtil.getTotalNumberOfLettersOfNamesLongerThanFive("aaron"), 0);
    }

    @Test
    public void transformShouldFlattenCollection() {
        List<List<String>> collection = asList(asList("Viktor", "Farcic"), asList("John", "Doe", "Third"));
        List<String> expected = asList("Viktor", "Farcic", "John", "Doe", "Third");
        List<String> actual = StreamUtil.transform(collection);
        Collections.sort(expected);
        Collections.sort(actual);
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void getOldestPersonShouldReturnOldestPerson() {
        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        List<Person> collection = asList(sara, eva, viktor);
        assertEquals(eva, StreamPractice.getOldestPerson(collection));
    }

    @Test
    public void calculateShouldSumAllNumbers() {
        List<Integer> numbers = asList(1, 2, 3, 4, 5);
        assertEquals(1 + 2 + 3 + 4 + 5, StreamPractice.calculate(numbers));
    }

    @Test
    public void getKidNameShouldReturnNamesOfAllKidsUnder18() {
        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        Person anna = new Person("Anna", 5);
        List<Person> collection = asList(sara, eva, viktor, anna);
        Set<String> kidNames = StreamPractice.getKidNames(collection);
        assertEquals(2, kidNames.size());
        assertTrue(kidNames.containsAll(List.of("Sara", "Anna")));
    }

    @Test
    public void partitionAdultsShouldSeparateKidsFromAdults() {
        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        List<Person> collection = asList(sara, eva, viktor);
        Map<Boolean, List<Person>> result = StreamPractice.partitionAdults(collection);
        assertThat(result.get(true)).hasSameElementsAs(asList(viktor, eva));
    }

    @Test
    public void toStringShouldReturnPeopleNamesSeparatedByComma() {
        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        List<Person> collection = asList(sara, viktor, eva);
        assertThat(StreamPractice.namesToString(collection))
                .isEqualTo("Names: Sara, Viktor, Eva.");
    }

    @Test
    public void getStringShouldOutputEvenUnevenString() {
        assertThat(StreamPractice.getStringShouldOutputEvenUnevenString(asList(3, 44))).isEqualTo("o3,e44");
        assertThat(StreamPractice.getStringShouldOutputEvenUnevenString(List.of(3))).isEqualTo("o3");
    }
}
