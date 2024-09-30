package de.revdev.demo.feature.records;

public class CompactConstructor {


    public record Person(String name, int age) {
        public Person {
            if (age < 0) {
                throw new IllegalArgumentException("Age cannot be negative");
            }
        }
    }
}
