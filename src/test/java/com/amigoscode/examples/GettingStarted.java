package com.amigoscode.examples;

import com.amigoscode.beans.Person;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class GettingStarted {

    @Test
    public void imperativeApproach() throws IOException {
        // 1. Find people aged less or equal 18
        // 2. Then change implementation to find first 10 people
        List<Person> people = MockData.getPeople();

        ArrayList<Person> persons = new ArrayList<>();
        int limit = 10;
        int counter=0;

        for (Person person : people) {
            if (person.getAge() >= 18) {
                persons.add(person);
                counter++;
            }
            if (counter == limit) {
                break;
            }
        }

        persons.forEach(System.out::println);

    }

    @Test
    public void declarativeApproachUsingStreams() throws Exception {
        List<Person> people = MockData.getPeople();

        List<Person> persons = people.stream()
                .filter(p -> p.getAge() >= 18)
                .limit(10).toList( );

persons.forEach(System.out::println);
    }
}
