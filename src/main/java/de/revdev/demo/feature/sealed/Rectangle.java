package de.revdev.demo.feature.sealed;

public sealed class Rectangle implements Shape
        permits Square {

    private final int x;
    private final int y;

    public Rectangle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String surface() {
        return "Fläche von %d".formatted((x * y));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
