import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamPractice {
    /**
     * 返回年龄最大的对象
     */
    public static Person getOldestPerson(List<Person> people) {
        return people.stream().max((o1, o2) -> o1.age() - o2.age()).orElse(null);
    }

    /**
     * 将集合中的所有元素相加，尝试使用 reduce 运算符
     */
    public static int calculate(List<Integer> numbers) {
        return numbers.stream().reduce(0, (a, b) -> a + b);
    }

    /**
     * 获取所有年龄 < 18的儿童的姓名集合
     */
    public static Set<String> getKidNames(List<Person> people) {
        return people.stream()
                .filter(p -> p.age() < 18)
                .map(p -> p.name())
                .collect(Collectors.toSet());
    }

    /**
     * 分成两部分，成年人和儿童
     */
    public static Map<Boolean, List<Person>> partitionAdults(List<Person> people) {
        return people.stream().collect(Collectors.partitioningBy(p -> p.age() >= 18));
    }

    /**
     * 根据姓名分组
     */
    public static Map<String, List<Person>> partitionByName(List<Person> people) {
        return people.stream().collect(Collectors.groupingBy(p -> p.name()));
    }

    /**
     * 返回用 , 连接所有姓名的字符串
     */
    public static String namesToString(List<Person> people) {
        return people.stream().map(Person::name).collect(Collectors.joining(", ", "Names: ", "."));
    }

    /**
     * 用 , 连接所有整数，并且奇数前缀o，偶数前缀e
     */
    public static String getStringShouldOutputEvenUnevenString(List<Integer> list) {
        return list
                .stream()
                .map(i -> {
                    if (i % 2 == 0) {
                        return "e" + i;
                    } else {
                        return "o" + i;
                    }
                })
                .collect(Collectors.joining(","));
    }
}
