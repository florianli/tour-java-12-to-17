package de.revdev.demo.feature.records;

public class AlternativeConstructor {

    public record Person(String name, int age) {
        public Person(String name) {
            this(name, 0);  // Alter wird standardmäßig auf 0 gesetzt
        }
    }

    public record Point(int x, int y) {
        public Point {
            if (x < 0 || y < 0) {
                throw new IllegalArgumentException("x and y must be positive");
            }
        }

        public Point(String x, String y) {
            this(Integer.parseInt(x), Integer.parseInt(y));
        }
    }

    public static void main(String[] args) {
        Person person1 = new Person("Hans Müller");
        System.out.println(person1);
        Person person2 = new Person("Hans Müller", 18);
        System.out.println(person2);
        System.out.println();
        Point point1 = new Point(2, 3);
        System.out.println(point1);
        Point point2 = new Point("4", "5");
        System.out.println(point2);
        try {
            Point point3 = new Point("4", "-6");
            System.out.println(point3);
        } catch (Exception e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }

}
