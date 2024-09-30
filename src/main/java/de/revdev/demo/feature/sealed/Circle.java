package de.revdev.demo.feature.sealed;

public final class Circle implements Shape {

    private final int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public String surface() {
        return "Kreisfläche von %f".formatted((getRadius() * getRadius() * Math.PI));
    }

    public int getRadius() {
        return radius;
    }
}
