package de.revdev.demo.feature;

public class StringKlasseDemo {


    public static void main(String[] args) {
        stringIndent();
        stringTransform();
        stringFormatted();
    }

    public static void stringIndent() {
        String indent1 = "Hallo\nWelt".indent(4);
        System.out.println(indent1);

        String indent2 = """
                Hallo
                Welt""".indent(4);
        System.out.println(indent2);
    }

    public static void stringTransform() {
        Integer length = "Hello".transform(String::length);
        System.out.println(length);
    }

    public static void stringFormatted() {
        String welt = "Welt";
        final String hwAlt = String.format("Hallo %s", welt);
        System.out.println(hwAlt);
        final String hwNeu = "Hallo %s".formatted(welt);
        System.out.println(hwNeu);
    }
}
