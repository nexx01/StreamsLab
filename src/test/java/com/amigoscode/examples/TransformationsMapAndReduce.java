package com.amigoscode.examples;

import com.amigoscode.beans.Car;
import com.amigoscode.beans.Person;
import com.amigoscode.beans.PersonDTO;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class TransformationsMapAndReduce {

    @Test
    void yourFirstTransformationWithMap() throws IOException {
        List<Person> people = MockData.getPeople();

        Function<Person, PersonDTO> personPersonDTOFunction = p -> new PersonDTO(p.getId(), p.getFirstName(), p.getAge());
        List<PersonDTO> dtos = people.stream()
                .map(personPersonDTOFunction)
                .toList();

        List<PersonDTO> dtos2 = people.stream()
                .map(PersonDTO::map)
                .toList();

        dtos.forEach(System.out::println);
        assertThat(people.size()).isEqualTo(dtos.size());
    }

    @Test
    void mapToDoubleAndFindAverageCarPrice() throws IOException {
        List<Car> cars = MockData.getCars();

        double avg = cars.stream()
                .mapToDouble(Car::getPrice)
                .average().orElse(0);
        System.out.println(avg);
    }

    @Test
    public void reduce() {
        int[] integers = {1, 2, 3, 4, 99, 100, 121, 1302, 199};
        int sum = Arrays.stream(integers)
                .reduce(0, (left, right) -> left + right);

        int sum2 = Arrays.stream(integers).reduce(0, Integer::sum);

        int sum3 = Arrays.stream(integers)
                .reduce(0, (left, right) -> left - right);
        System.out.println(sum);
        System.out.println(sum2);
        System.out.println(sum3);
    }
}

