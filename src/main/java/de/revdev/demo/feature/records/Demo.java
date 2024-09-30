package de.revdev.demo.feature.records;

public class Demo {

    public record Person(String name, int age) {
    }


    public static void main(String[] args) {
        Person person1 = new Person("Hans Müller", 18);
        System.out.println(person1);
        System.out.println(person1.name());
        System.out.println(person1.age());
        Person person2 = new Person("Hans Müller", 18);
        System.out.println(person2.equals(person1));
        Person person3 = new Person("Hans Mustermann", 32);
        System.out.println(person3.equals(person1));
    }

}
