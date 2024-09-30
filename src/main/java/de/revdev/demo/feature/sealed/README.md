Hier ist eine Markdown-formatierte Erklärung zu **Sealed Classes** in Java:

---

# Sealed Classes in Java

**Sealed Classes** wurden in **JDK 17** eingeführt, um die Vererbungshierarchie besser zu kontrollieren. 
Sie ermöglichen es, eine Klasse oder ein Interface so zu definieren, 
dass nur eine festgelegte Menge von Klassen oder Interfaces diese erweitern bzw. implementieren dürfen.

## Ziel von Sealed Classes

Sealed Classes erlauben es Entwicklern:

- **Vererbung einzuschränken**: Nur explizit angegebene Klassen dürfen von der sealed-Klasse erben.
- **Klarheit**: Sie verbessern die Kontrolle über die Vererbungshierarchie und sorgen für eine klare Struktur.
- **Sicherheitsmechanismen**: Durch Einschränkungen der Vererbung verhindern sie unerwünschte oder unsichere
  Erweiterungen.

## Syntax

Sealed Classes werden mit dem Schlüsselwort `sealed` definiert, 
und die erlaubten Unterklassen werden über die `permits`-Klausel angegeben.

### Beispiel:

```java
public sealed interface Shape
        permits Circle, Rectangle, FlexibleShape {
}

public final class Circle implements Shape {
    // Implementierung
}

public sealed class Rectangle implements Shape
        permits Square {
    // Implementierung
}

public final class Square extends Rectangle {
    // Implementierung
}

public abstract non-sealed class FlexibleShape implements Shape {
    // Implementierung
}

public class Triangle extends FlexibleShape {
    // Implementierung
}
```

In diesem Beispiel dürfen nur `Circle`, `Rectangle` und `FlexibleShape` das Interface `Shape` implementieren.
Keine anderen Klassen sind erlaubt.
`Square` darf von `Rectangle` erben.
`FlexibleShape` hat keine Einschränkungen, welche Klassen von ihr erben können.

## Wichtige Regeln

### 1. Drei Schlüsselwörter

- **`sealed`**: Beschränkt die Vererbung auf eine definierte Menge von Unterklassen.
- **`non-sealed`**: Öffnet eine Unterklasse für weitere Vererbung.
- **`final`**: Verhindert, dass eine Unterklasse weitervererbt wird.

### 2. `permits`-Klausel

- Die `permits`-Klausel listet alle erlaubten Unterklassen auf. Diese müssen entweder `final`, `sealed`, oder
  `non-sealed` sein.

### 3. Deklarationspflicht

- Jede erlaubte Unterklasse muss entweder als `final`, `sealed` oder `non-sealed` deklariert werden.

## Beispiele für Vererbungsarten

### 1. **`final` Unterklassen**

```java
public final class Circle implements Shape {
// Diese Klasse kann nicht weiter erweitert werden.
}
```

### 2. **`sealed` Unterklassen**

```java
public sealed class Rectangle implements Shape
    permits Square {
// Diese Klasse kann beliebig erweitert werden.
}
```

### 3. **`non-sealed` Unterklassen**

```java
public abstract non-sealed class FlexibleShape implements Shape {
// Diese Klasse kann beliebig erweitert werden.
}
```

## Anwendungsfälle

- **Disjunkte Hierarchien**: In Fällen, in denen nur eine festgelegte Menge von Klassen möglich sein soll, 
  wie bei geometrischen Formen:
```java
public abstract sealed class Shape permits Circle, FlexibleShape, Rectangle { }
```

- **Verbesserte Kontrolle**: Sealed Classes sind nützlich, wenn du mehr Kontrolle über die Vererbung und Architektur in
  einem System benötigst.

## Sealed Classes und Pattern Matching (Erst mit Jdk 21)

In Verbindung mit Pattern Matching können Sealed Classes verwendet werden, 
um eine vollständige Überprüfung aller möglichen Unterklassen sicherzustellen:

```java
public static void printShape(Shape shape) {
    switch (shape) {
        case Circle c -> System.out.println("Circle with radius " + c.radius());
        case Rectangle r -> System.out.println("Rectangle with width " + r.width() + " and height " + r.height());
        case FlexibleShape s -> System.out.println("FlexibleShape with side " + s.side());
        default -> throw new IllegalStateException("Unexpected value: " + shape);
    }
}
```

## Vorteile von Sealed Classes

- **Sicherheit**: Begrenzung der Vererbungshierarchie sorgt für sicherere und wartbare Codebasen.
- **Lesbarkeit**: Klarheit bei der Definition erlaubter Unterklassen.
- **Pattern Matching**: Erleichtert die Verwendung von Pattern Matching durch vollständige Abdeckung aller möglichen
  Unterklassen.

---

Sealed Classes helfen dabei, eine striktere und kontrolliertere Vererbungshierarchie in Java zu etablieren und bieten
bessere Möglichkeiten für die Strukturierung komplexer Systeme.