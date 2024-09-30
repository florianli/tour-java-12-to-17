### **Records in Java**

**Records** wurden in **Java 14** als Vorschau eingeführt und sind seit **Java 16** fest in der Sprache integriert.
Sie bieten eine kompakte Möglichkeit, **unveränderliche Datenklassen** (Immutable Data Classes) zu erstellen, 
die automatisch einige grundlegende Methoden wie `equals()`, `hashCode()`, `toString()` 
und **Getter** für die Felder generieren.

### Ziel von Records:

- **Weniger Boilerplate-Code**: Records reduzieren den Code, den man normalerweise für Getter-Methoden, 
  `equals()`, `hashCode()` und `toString()` schreiben müsste.
- **Unveränderlichkeit**: Alle Felder in einem Record sind standardmäßig `final` und werden über den Konstruktor
  initialisiert, was die Objekte unveränderlich macht.
- **Klare Datenrepräsentation**: Records sind ideal, um einfache, unveränderliche Datencontainer zu erstellen, 
  die keine komplexe Logik beinhalten.

---

## **Record-Deklaration**

Ein Record wird mit der `record`-Syntax erstellt. Hier ein Beispiel für ein einfaches Record:

```java
public record Person(String name, int age) {
}
```

Dieses Record enthält zwei Felder `name` und `age`. Der Compiler generiert automatisch:

- **Konstruktor**: Ein Konstruktor, der alle Felder initialisiert.
- **Getter-Methoden**: Getter für `name` und `age` (die Methoden heißen einfach `name()` und `age()`).
- **`equals()` und `hashCode()`**: Diese Methoden werden basierend auf den Feldern generiert.
- **`toString()`**: Gibt eine String-Darstellung des Records in der Form `Person[name=..., age=...]` zurück.

---

## **Konstruktoren in Records**

Records unterstützen verschiedene Arten von Konstruktoren, 
je nach den Anforderungen an Validierung oder Initialisierung.

### 1. **Automatisch generierter Konstruktor**

Wenn du keinen Konstruktor definierst, erstellt der Compiler automatisch einen,
der alle Felder des Records initialisiert:

```java
public record Person(String name, int age) {
}
```

- Dieser erzeugt den Konstruktor:
  ```java
  public Person(String name, int age) {
      this.name = name;
      this.age = age;
  }
  ```

### 2. **Kompakter Konstruktor (Compact Constructor)**

Ein kompakter Konstruktor ermöglicht dir, zusätzliche Logik oder Validierung einzufügen, 
ohne explizit die Feldzuweisungen vornehmen zu müssen. 
Der Compiler übernimmt diese Aufgabe automatisch.

#### Beispiel:

```java
public record Person(String name, int age) {
    public Person {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
    }
}
```

- In diesem Fall wird die Validierung durchgeführt, 
  und der Compiler übernimmt die Zuweisung der Felder `name` und `age`.

### 3. **Standardmäßiger Konstruktor mit Logik**

Wenn du eine vollständige Kontrolle über die Initialisierung benötigst, 
kannst du einen **vollständigen Konstruktor** schreiben. 
In diesem Fall musst du die Felder explizit initialisieren.

#### Beispiel:

```java
public record Person(String name, int age) {
    public Person(String name, int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        this.name = name.trim();  // Entfernt Leerzeichen vor der Zuweisung
        this.age = age;
    }
}
```

- In diesem Beispiel wird der Name getrimmt, bevor er dem Feld zugewiesen wird. 
  Du hast vollständige Kontrolle über die Initialisierung.

### 4. **Konstruktor mit Standardwerten**

Du kannst auch einen **Konstruktor mit Standardwerten** definieren. 
Dieser ermöglicht es dir, Werte für bestimmte Felder standardmäßig zu setzen, 
wenn sie nicht explizit übergeben werden.

#### Beispiel:

```java
public record Person(String name, int age) {
    public Person(String name) {
        this(name, 0);  // Alter wird standardmäßig auf 0 gesetzt
    }
}
```

- Hier kannst du eine `Person` nur mit dem Namen erstellen, und das Alter wird automatisch auf `0` gesetzt, 
  wenn kein Wert angegeben wird.

### 5. **Alternative Konstruktoren**

Zusätzliche Konstruktoren können ebenfalls definiert werden, um spezifische Initialisierungsszenarien abzudecken.

#### Beispiel:

```java
public record Rectangle(double width, double height) {
    public Rectangle(double side) {
        this(side, side);  // Erstellt ein Quadrat, wenn nur eine Seitenlänge angegeben wird
    }
}
```

- Hier wird ein alternativer Konstruktor erstellt, der ein Quadrat erstellt, indem er die Breite und Höhe gleich setzt.

---

## **Einschränkungen von Records**

1. **Keine Vererbung**: Ein Record kann nicht von einer anderen Klasse erben, da es `final` ist. 
   Es kann jedoch **Interfaces implementieren**.
2. **Unveränderliche Felder**: Alle Felder eines Records sind `final` und können nach der Initialisierung nicht mehr
   geändert werden.
3. **Konstruktoren müssen alle Felder initialisieren**: Alle Felder eines Records müssen direkt im Konstruktor
   initialisiert werden.

---

## **Wann sollte man Records verwenden?**

- **Datenklassen**: Wenn du eine Klasse benötigst, die hauptsächlich Daten speichert und keine komplexe Logik enthält.
- **Unveränderlichkeit**: Wenn deine Daten nach der Erstellung unveränderlich sein sollen,
  wie bei DTOs (Data Transfer Objects).
- **Reduktion von Boilerplate-Code**: Wenn du den Aufwand für das Schreiben von Getter-Methoden, 
  `equals()`, `hashCode()` und `toString()` minimieren möchtest.

---

## **Zusammenfassung**

**Records** bieten eine elegante Möglichkeit, unveränderliche Datenklassen in Java zu erstellen. 
Sie reduzieren Boilerplate-Code und verbessern die Lesbarkeit. 
Durch die verschiedenen Konstruktoroptionen – vom **automatisch generierten Konstruktor** 
über den **kompakten Konstruktor** bis hin zu **alternativen Konstruktoren** – 
kannst du Records flexibel einsetzen und dennoch eine saubere und minimalistische Struktur beibehalten.