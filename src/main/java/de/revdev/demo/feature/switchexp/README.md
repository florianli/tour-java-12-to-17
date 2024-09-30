**Switch Expressions** sind eine Erweiterung des klassischen `switch`-Statements in Java. Sie wurden als Vorschau in *
*JDK 12** eingeführt und in **JDK 14** stabilisiert. Sie ermöglichen es, `switch` nicht nur als Kontrollflussanweisung,
sondern auch als Ausdruck zu verwenden, der einen Wert zurückgibt. Dies verbessert die Lesbarkeit und reduziert den
Boilerplate-Code.

## Eigenschaften von Switch Expressions

1. **Rückgabe eines Wertes**:
    - Mit Switch Expressions kann `switch` jetzt einen Wert zurückgeben, was vorher nicht möglich war.
    - Dies wird durch das neue Schlüsselwort `yield` erreicht.

2. **Neue Syntax**:
    - Eine neue, kompakte Syntax mit `->` (Pfeil-Syntax) ersetzt die alte `case`- und `break`-Struktur.
    - Man kann aber weiterhin die klassische Syntax verwenden, falls gewünscht.

3. **Fehlervermeidung**:
    - Mit der neuen Syntax entfällt das Risiko von **"fall-through"**-Fehlern (wenn man vergisst, `break` zu setzen).

4. **Arbeiten mit `enum` und `sealed` Klassen**:
    - `switch` kann jetzt besser mit `enum`-Werten und `sealed` Klassen kombiniert werden, was das Pattern Matching
      erleichtert.

## Syntax

### Klassische `switch`-Syntax:

Die klassische `switch`-Syntax besteht aus den `case`-Blöcken, die mit `break` enden müssen, um unerwünschte
Fall-throughs zu verhindern.

```java
public String dayName(int day) {
    String dayName;
    switch (day) {
        case 1:
            dayName = "Monday";
            break;
        case 2:
            dayName = "Tuesday";
            break;
        case 3:
            dayName = "Wednesday";
            break;
        default:
            dayName = "Unknown";
            break;
    }
    return dayName;
}
```

### Neue Switch-Expression Syntax:

In der neuen Syntax verwenden wir `->`, um eine Anweisung oder einen Ausdruck an einen Fall zu binden. Es gibt keine
Notwendigkeit für `break`.

```java
int day = 2;
String dayName = switch (day) {
    case 1 -> "Monday";
    case 2 -> "Tuesday";
    case 3 -> "Wednesday";
    default -> "Unknown";
};
```

### Verwendung von `yield`:

Das Schlüsselwort `yield` wird verwendet, um innerhalb von komplexeren Fällen einen Wert zurückzugeben.

```java
int day = 2;
String dayName = switch (day) {
    case 1 -> "Monday";
    case 2 -> {
        System.out.println("Processing day 2");
        yield "Tuesday";
    }
    case 3 -> "Wednesday";
    default -> "Unknown";
};
```

In diesem Beispiel wird der Fall `2` verwendet, um zusätzlichen Code auszuführen (`System.out.println()`) und dann mit
`yield` den Wert `"Tuesday"` zurückzugeben.

## Vorteile der neuen Switch Expressions

1. **Verbesserte Lesbarkeit**: Der Code wird kompakter und leichter zu lesen, da die Notwendigkeit für `break` entfällt.
2. **Fehlervermeidung**: Die Gefahr von **"fall-through"**-Fehlern wird vermieden, da jeder `case`-Block seinen eigenen
   Ausdruck besitzt.
3. **Rückgabewert**: Switch Expressions ermöglichen es, dass `switch`-Blöcke direkt einen Wert zurückgeben, was den Code
   eleganter macht.
4. **Flexibilität mit `yield`**: Komplexe Fälle können mit `yield` behandelt werden, wenn mehr als nur eine einfache
   Zuordnung in einem Fall erforderlich ist.

## Beispiel mit `enum`:

Switch Expressions sind besonders nützlich beim Arbeiten mit `enum`-Werten:

```java
enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

Day day = Day.TUESDAY;

String typeOfDay = switch (day) {
    case SATURDAY, SUNDAY -> "Weekend";
    case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Weekday";
};
```

Hier ordnen wir sowohl `SATURDAY` als auch `SUNDAY` dem Wert `"Weekend"` zu, was den Code sehr kompakt und verständlich
macht.

### Bedeutung von Exhaustiveness:

- Wenn eine Switch Expression nicht alle möglichen Fälle abdeckt, führt das zu einem **Compilerfehler**.
  Dies ist besonders wichtig, um Laufzeitfehler zu vermeiden, wenn ein nicht abgedeckter Wert auftritt.
- Java-Compiler prüfen auf **Vollständigkeit** (exhaustiveness) bei Switch Expressions, um sicherzustellen,
  dass für jeden möglichen Wert ein passender Fall existiert.

### Anwendung bei `enum`-Werten:

Exhaustiveness ist besonders nützlich in Kombination mit **`enum`-Werten**,
da Java bei diesem Typ weiß, welche möglichen Werte es gibt.
Der Compiler kann prüfen, ob jeder `enum`-Wert im `switch`-Ausdruck behandelt wird.

### Beispiele:

#### 1. **Enum Exhaustiveness**:

Da bei einem `enum`-Typ alle möglichen Werte bekannt sind, muss die Switch Expression jeden `enum`-Wert abdecken.

```java
enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

Day day = Day.TUESDAY;

String typeOfDay = switch (day) {
    case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Weekday";
    case SATURDAY, SUNDAY -> "Weekend";
};
```

- Hier deckt die Switch Expression **alle möglichen Werte** von `Day` ab. Deshalb ist sie exhaustiv.
- Wenn einer der `enum`-Werte nicht behandelt worden wäre und es keinen `default`-Fall gäbe, hätte der Compiler einen
  Fehler geworfen.

Mit der Enumeration `Day` aus dem vorherigen Beispiel wird der Compiler einen Fehler ausgeben,
da in dem Switch `Day.FRIDAY` nicht behandelt wird.

```java
String typeOfDay = switch (day) {
    case MONDAY, TUESDAY, WEDNESDAY, THURSDAY -> "Weekday";
    case SATURDAY, SUNDAY -> "Weekend";
};
```

#### 2. **Fehlende Exhaustiveness und der `default`-Fall**:

Falls nicht alle möglichen Werte abgedeckt werden können, ist ein `default`-Fall erforderlich, um die Exhaustiveness
sicherzustellen.

```java
Day day = Day.TUESDAY;

String typeOfDay = switch (day) {
    case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Weekday";
    // Kein Fall für SATURDAY und SUNDAY
    default -> "Unknown day";
};
```

- Hier gibt es keinen Fall für `SATURDAY` und `SUNDAY`, aber der `default`-Fall stellt sicher, dass die
  Exhaustiveness-Anforderung erfüllt wird.

### Vorteile der Exhaustiveness-Prüfung:

- **Sicherheit**: Der Compiler verhindert, dass bestimmte Werte unbehandelt bleiben, was zu sichereren Programmen führt.
- **Verlässlichkeit**: Es wird sichergestellt, dass der Code alle möglichen Eingaben verarbeiten kann, ohne an einer
  nicht behandelten Eingabe zu scheitern.
- **Lesbarkeit und Wartbarkeit**: Entwickler können durch die Exhaustiveness-Prüfung sicherstellen, dass keine Fälle
  vergessen werden, insbesondere bei Änderungen oder Erweiterungen in `enum`-Typen oder `sealed`-Klassen.

## Zusammenfassung:

- **Switch Expressions** in Java bieten eine kompaktere und ausdrucksstärkere Möglichkeit, `switch` zu verwenden.
- Sie erlauben es, dass `switch`-Blöcke Werte zurückgeben, was den Code vereinfacht.
- Die neue Pfeil-Syntax (`->`) und das `yield`-Schlüsselwort helfen, unnötige Komplexität zu vermeiden und sicherere,
  lesbare Programme zu schreiben.
- **Exhaustiveness** bedeutet, dass eine **Switch Expression** alle möglichen Werte eines Typs behandeln muss.
- Für `enum`-Typen und `sealed`-Klassen prüft der Compiler automatisch, ob alle Fälle abgedeckt sind.
- Falls nicht alle Fälle abgedeckt werden, ist ein **`default`-Fall** erforderlich, um die Exhaustiveness
  sicherzustellen.

Dieses Feature ist ideal für Situationen,
in denen mehrere Fälle logisch zusammengehören oder ein Wert von einem `switch`-Block direkt verwendet werden soll.

Der Begriff **"Exhaustiveness"** (Vollständigkeit) im Zusammenhang mit **Switch Expressions** bezieht sich auf die
Tatsache, dass ein **`switch`-Ausdruck** alle möglichen Werte oder Fälle abdecken muss,
die er verarbeiten kann. Java verlangt,
dass eine **Switch Expression** "erschöpfend" (exhaustiv) ist, d. h., sie muss sicherstellen,
dass für jeden möglichen Eingabewert ein Fall (`case`) definiert ist oder zumindest ein `default`-Fall vorhanden ist.


