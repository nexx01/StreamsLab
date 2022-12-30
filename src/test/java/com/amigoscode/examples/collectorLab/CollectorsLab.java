package com.amigoscode.examples.collectorLab;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectorsLab {

    @Test
    void test() {
        List<Integer> integers = List.of(0, -2, 3, 4, -4, 7, 5, 0);
        Predicate<Integer> predicate = a -> a > 0;

        NumberClassificator numberClassificator = new NumberClassificator(predicate);

        List<Integer> result = integers.stream().collect(numberClassificator);

        System.out.println(result);

        assertEquals(4, result.size());
    }

    @Test
    void test2() {
        String text = "Hello world";

        GroupByEqualsCustomCollector<Object> customCollector = new GroupByEqualsCustomCollector<>();

        Map<Object, Integer> result =
                Arrays.stream(text.split("")).collect(customCollector);

        System.out.println(result);


    }
}
