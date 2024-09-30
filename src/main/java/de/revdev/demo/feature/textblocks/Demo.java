package de.revdev.demo.feature.textblocks;

public class Demo {

    String test = "Das ist kein Textblock.\n" +
            "Hat aber zwei Zeilen.";
    String test1 = """
            Das ist ein Textblock.
            Er hat zwei Zeilen.""";
    String test2 = """
            Das ist ein Textblock.
            Er hat zwei Zeilen und eine Leerzeile am Ende.
            """;
    String test3 = """
            Das ist ein Textblock.
            Er hat zwei Zeilen und keine Leerzeile am Ende. \
            """;
    String test4 = """
            Das ist ein Textblock. \
            Er hat zwei Zeilen wird aber keine Zeilenumbrüche. \
            """;


    public static void main(String[] args) {
        Demo demo = new Demo();

        System.out.println(demo.test);
        System.out.println("----------");
        System.out.println(demo.test1);
        System.out.println("----------");
        System.out.println(demo.test2);
        System.out.println("----------");
        System.out.println(demo.test3);
        System.out.println("----------");
        System.out.println(demo.test4);
        System.out.println("----------");
    }
}
