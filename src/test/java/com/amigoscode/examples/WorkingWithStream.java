package com.amigoscode.examples;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WorkingWithStream {

    @Test
    void streams() {
        List<String> names = List.of("Amigouscos", "Alex", "Zara");
        Stream<String> stream = names.stream();

        Stream<String> nameStream = Stream.of("Amigouscos", "Alex", "Zara");

        long count = stream
                .limit(2).map(null).sorted(null).dropWhile(null)
//                .collect(Collectors.toList());
                .count();

        String[] nameArray = {};
        Arrays.stream(nameArray);
    }

}
