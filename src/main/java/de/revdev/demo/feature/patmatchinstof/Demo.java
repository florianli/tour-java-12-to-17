package de.revdev.demo.feature.patmatchinstof;

import java.nio.file.Path;

public class Demo {

    public static void print(Object obj) {

        if (obj instanceof Integer zahl && zahl > 0) {
            System.out.println("Zahl größer 0: " + zahl);
            return;
        } else if (obj instanceof Integer zahl && zahl < 0) {
            System.out.println("Zahl kleiner 0: " + zahl);
            return;
        } else if (obj instanceof Integer zahl) {
            System.out.println("Zahl ist 0: " + zahl);
            return;
        }

        if (!(obj instanceof String text)) {
            throw new IllegalArgumentException("Parameter muss entweder Integer oder String sein." + obj.getClass().getName());
        }
        System.out.println(text.trim());
    }

    public static void main(String[] args) {
        print(20);
        print(-20);
        print(0);
        print("Text");
        try {
            print(Path.of(""));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
