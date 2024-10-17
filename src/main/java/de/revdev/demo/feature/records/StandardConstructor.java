package de.revdev.demo.feature.records;

public class StandardConstructor {

    public record Person(String name, int age) {
        public Person(String name, int age) {
            if (age < 0) {
                throw new IllegalArgumentException("Age cannot be negative");
            }
            this.name = name.trim();  // Entfernt Leerzeichen vor der Zuweisung
            this.age = age;
        }
    }

    public static void main(String[] args) {
        try {
            Person person1 = new Person("Hans Müller", -10);
            System.out.println(person1);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        Person person2 = new Person(" Hans Müller ", 18);
        System.out.println(person2);
    }

}
