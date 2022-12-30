package com.amigoscode.examples.collectorLab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Collector;

public class NumberClassificator implements Collector<Integer, List<Integer>, List<Integer>> {
    private Predicate<Integer> addOrNot;

    public NumberClassificator(Predicate<Integer> addOrNot) {
        this.addOrNot = addOrNot;
    }

    @Override
    public Supplier<List<Integer>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<Integer>, Integer> accumulator() {
        return (a, b) ->{
            if (addOrNot.test(b)) {
                a.add(b);
            }
        };
    }

    @Override
    public BinaryOperator<List<Integer>> combiner() {
        return (a,b)->{
            List<Integer> list = new ArrayList<>();
            list.addAll(a);
            list.addAll(b);
            return list;
        };
    }

    @Override
    public Function<List<Integer>, List<Integer>> finisher() {
        return a->{
            List<Integer> sortedList = new ArrayList<>();
            sortedList.addAll(a);
            Collections.sort(sortedList);
            return sortedList;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of();
    }

}
