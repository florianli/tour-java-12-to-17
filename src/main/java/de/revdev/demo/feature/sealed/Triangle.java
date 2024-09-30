package de.revdev.demo.feature.sealed;

public class Triangle extends FlexibleShape {

    private final int a;
    private final int b;
    private final int c;

    public Triangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String surface() {
        return "Fläche ist %f".formatted((0.25 * Math.sqrt((a + b + c) * (-a + b + c) * (a - b + c) * (a + b - c))));
    }
}
