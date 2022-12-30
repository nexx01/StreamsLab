package com.amigoscode.examples.CustomSpliterator;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.Spliterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomSpliteratorTest {

    @Test
    void tryAdvanceTest() {
        Messages messages = prepareTestData();

        List<Object> actual = new CopyOnWriteArrayList<>();
        Consumer<MessageRecord> action = a -> {
            System.out.println(a);
            actual.add(a);
        };

        Spliterator<MessageRecord> spliterator = messages.getSpliterator();
        for (;spliterator.tryAdvance(action); ) {

        }
        assertEquals(9, actual.size());
    }

    @Test
    void estimateSize() {
        Messages messages = prepareTestData();

        List<Object> actual = new CopyOnWriteArrayList<>();
        Consumer<MessageRecord> action = a -> {
            System.out.println(a);
            actual.add(a);
        };

        Spliterator<MessageRecord> spliterator = messages.getSpliterator();
        int expected = 8;
        for (;spliterator.tryAdvance(action); ) {
            long estimateSize = spliterator.estimateSize();
            System.out.println(estimateSize);
            assertEquals(expected, estimateSize);
            expected--;
        }
    }

    @Test
    void name() {
        Messages messages = prepareTestData();

        List<Object> actual = new CopyOnWriteArrayList<>();
        Consumer<MessageRecord> action = a -> {
            System.out.println(a);
            actual.add(a);
        };

        Spliterator<MessageRecord> spliterator = messages.getSpliterator();

        Spliterator<MessageRecord> newSpliterator = spliterator.trySplit();
        for (;spliterator.tryAdvance(action); ) {
            System.out.println(spliterator.estimateSize());
        }
        System.out.println("----------------------------");

        for (;newSpliterator.tryAdvance(action); ) {
            System.out.println(newSpliterator.estimateSize());
        }
    }

    @Test
    void streamFromSpliterator() {

        Messages messages = prepareTestData();
        var expected = "hello2";
        List<MessageRecord> result = messages.stream()
                .filter(a -> a.getUserId() == 3)
                .collect(Collectors.toList());

        for (MessageRecord mr : result) {
            System.out.println(mr);
        }

        assertEquals(expected, result.get(0).getText());
    }

    Messages prepareTestData() {
        Date date = new Date();

        MessageRecord record1 = new MessageRecord(1, date, "hello");
        MessageRecord record2 = new MessageRecord(2, date, "hello1 sdfsd");
        MessageRecord record3 = new MessageRecord(3, date, "hello2");
        MessageRecord record4 = new MessageRecord(4, date, "hello3");
        MessageRecord record5 = new MessageRecord(5, date, "hello4");
        MessageRecord record6 = new MessageRecord(6, date, "hello5");
        MessageRecord record7 = new MessageRecord(7, date, "hello6");
        MessageRecord record8 = new MessageRecord(8, date, "hello7");
        MessageRecord record9 = new MessageRecord(9, date, "hello8");

        Messages messages = new Messages();
        messages.addMessageRecord(record1);
        messages.addMessageRecord(record2);
        messages.addMessageRecord(record3);
        messages.addMessageRecord(record4);
        messages.addMessageRecord(record5);
        messages.addMessageRecord(record6);
        messages.addMessageRecord(record7);
        messages.addMessageRecord(record8);
        messages.addMessageRecord(record9);
        return messages;
    }
}
