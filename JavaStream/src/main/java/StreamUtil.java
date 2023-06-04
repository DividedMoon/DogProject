import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamUtil {
    /**
     * 将给定的字符串列表中，所有的字符串变为大写
     */
    public static Collection<String> mapToUppercase(String... names) {
        return Stream.of(names).map(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        }).collect(Collectors.toList());
    }

    /**
     * 给出所有长度超过 5 的字符串的长度之和
     */
    public static int getTotalNumberOfLettersOfNamesLongerThanFive(String... names) {
        return Stream.of(names)
                .filter(s->s.length()>5)
                .mapToInt(s->s.length())
                .sum();
    }

    /**
     * 将二维的列表展平，变成一维
     */
    public static List<String> transform(List<List<String>> collection) {
        return collection.stream()
                .flatMap(strings->strings.stream())
                .collect(Collectors.toList());
    }

}
