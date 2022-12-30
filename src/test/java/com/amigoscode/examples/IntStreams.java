package com.amigoscode.examples;


import com.amigoscode.beans.Person;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntStreams {

    @Test
    public void range() throws Exception {
        for (int i = 0; i <= 10; i++) {
            System.out.println(i);
        }

        System.out.println("wiith int stream exclusive");
        IntStream intStream = IntStream.range(0, 10);
        intStream.forEach(System.out::println);


        System.out.println("wiith int stream inclusive");
        IntStream intStreamCloser = IntStream.rangeClosed(0, 10);
        intStreamCloser.forEach(System.out::println);

    }

    // Loop through people using IntStream
    @Test
    public void rangeIteratingLists() throws Exception {
        List<Person> people = MockData.getPeople();
        IntStream.range(0, people.size())
                .forEach(index->{
                    System.out.println(people.get(index));
                });
    }

    @Test
    public void intStreamIterate()  {
        IntStream.iterate(0,value -> value+1)
                .limit(11)
                .forEach(System.out::println);
    }
}
