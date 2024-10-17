package de.revdev.demo.feature;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionsFrameworkDemo {

    public static void main(String[] args) {
        teeingCollector();
        mapOfEntries();
    }

    public static void teeingCollector() {
        var result = Stream.of(1, 2, 3, 4, 5)
                           .collect(Collectors.teeing(
                                   Collectors.summingInt(i -> i),    // Summe aller Zahlen
                                   Collectors.counting(),            // Anzahl der Elemente
                                   (sum, count) -> sum / count       // Durchschnitt
                           ));
        System.out.println(result);
    }

    public static void mapOfEntries() {
        Map<String, Integer> map = Map.ofEntries(
                Map.entry("a", 1),
                Map.entry("b", 2)
        );
        System.out.println(map);
    }
}
