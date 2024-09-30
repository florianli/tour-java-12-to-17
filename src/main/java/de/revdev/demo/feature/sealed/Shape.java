package de.revdev.demo.feature.sealed;

public sealed interface Shape permits Circle, FlexibleShape, Rectangle {

    String surface();

}
