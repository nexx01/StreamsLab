package com.amigoscode.examples;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectExample {

    @Test
    void collect() {
        List<Integer> integers = List.of(1, 2, 3);
        ArrayList<Integer> even = integers.stream()
                .filter(f -> f % 2 == 0)
                .collect(ArrayList::new, (list, b) -> list.add(b), (a, b) -> a.addAll(b));

        System.out.println(even);
        assertEquals(1, even.size());
    }

    @Test
    void collect2() {
        List<Goods> goods = List.of(new Goods("Apple", 50),
                new Goods("Orange", 70),
                new Goods("Pear", 65),
                new Goods("Cherry", 75));

        ArrayList<Object> collect = goods.stream()
                .filter(a -> a.getPrice() > 50)
                .collect(ArrayList::new, (a, b) -> a.add(b.getName()), (l1, l2) -> l1.addAll(l2));

        System.out.println(collect);

        assertEquals(3, collect.size());
    }

    @Test
    void collectToMap() {
        List<Integer> integers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Map<String, List<Integer>> result = integers.stream()
                .collect(() -> createMap(), (map, el) -> addToMap(map, el), (map1, map2) -> mergeMap(map1, map2));

        System.out.println(result);
        assertEquals(2,result.size());
    }


    public static Map<String, List<Integer>> createMap() {
        HashMap<String,List<Integer>> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("even", new ArrayList<>());
        objectObjectHashMap.put("odd", new ArrayList<>());
        return objectObjectHashMap;
    }

    public static void addToMap(Map<String, List<Integer>> map, Integer element) {
        if (element % 2 == 0) {
            map.get("even").add(element);
        } else {
            map.get("odd").add(element);
        }
    }

    public static void mergeMap(Map<String, List<Integer>> map1, Map<String, List<Integer>> map2) {
        map2.forEach((k, v) -> map1.get(k).addAll(v));
    }
}

class Goods {
    private String name;
    private int price;

    public Goods(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
