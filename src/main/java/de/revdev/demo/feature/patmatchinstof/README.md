### Pattern Matching für `instanceof` in Java

**Pattern Matching für `instanceof`** ist ein Feature, das in **Java 14** als Vorschau eingeführt wurde und seit **Java 16** stabil ist. Es erleichtert das Arbeiten mit dem **`instanceof`**-Operator, indem es automatisch die Typprüfung und die Typumwandlung (Casting) kombiniert. Dieses Feature reduziert Boilerplate-Code und macht den Code lesbarer und sicherer.

## Ziel von Pattern Matching für `instanceof`:
- **Kompakterer Code**: Es reduziert den Boilerplate-Code, der normalerweise für die Typprüfung und das anschließende Casting erforderlich ist.
- **Sicherere Typprüfung**: Es eliminiert die Gefahr von Fehlern durch manuelles Casting.
- **Lesbarkeit**: Der Code wird lesbarer, da unnötige Casts vermieden werden.

---

## Vorher: Klassisches `instanceof` und Casting

Vor der Einführung von Pattern Matching für `instanceof` musste man nach einer Typprüfung (`instanceof`) explizit den Typ casten, um auf die Methoden oder Felder des geprüften Typs zugreifen zu können. Dies führte oft zu unnötig ausführlichem Code.

### Beispiel (klassische Methode):

```java
Object obj = "Hello, World!";

if (obj instanceof String) {
    String s = (String) obj;  // Manuelles Casten erforderlich
    System.out.println(s.toUpperCase());
}
```

- Zuerst prüfst du, ob `obj` eine Instanz von `String` ist.
- Danach musst du das Objekt explizit in `String` umwandeln (casten), um auf die Methoden des `String`-Typs zuzugreifen.

---

## Neu: Pattern Matching für `instanceof`

Mit Pattern Matching für `instanceof` wird die Typprüfung und das Casting in einem Schritt erledigt. Du gibst nach dem `instanceof`-Operator den Variablennamen an, und der Compiler übernimmt das Casting automatisch, falls der Typ stimmt.

### Beispiel (Pattern Matching für `instanceof`):

```java
Object obj = "Hello, World!";

if (obj instanceof String s) {
    System.out.println(s.toUpperCase());  // Kein manuelles Casten nötig
}
```

- In diesem Fall prüfst du, ob `obj` eine Instanz von `String` ist, und wenn ja, wird `obj` automatisch als `String` gecastet und der Variablen `s` zugewiesen.
- Jetzt kannst du direkt auf `s` zugreifen, ohne explizit casten zu müssen.

---

## Vorteile von Pattern Matching für `instanceof`:

1. **Kompakterer Code**:
    - Du musst nicht mehr `instanceof` und danach manuell casten. Der Cast wird direkt in den Ausdruck integriert.

2. **Sichere Zuweisung**:
    - Das Pattern Matching garantiert, dass die Variable nur dann verwendet wird, wenn die Typprüfung erfolgreich ist. Es gibt keine Möglichkeit, versehentlich mit einem falschen Typ zu arbeiten.

3. **Verwendung in logischen Ausdrücken**:
    - Du kannst Pattern Matching auch in Kombination mit anderen logischen Operatoren verwenden.

      **Beispiel:**
      ```java
      Object obj = "Hello, World!";
    
      if (obj instanceof String s && s.length() > 5) {
        System.out.println(s.toUpperCase());
      }
      ```
    - In diesem Beispiel wird die Variable `s` nur dann verfügbar, 
      wenn sowohl `obj` eine Instanz von `String` ist **und** die Bedingung `s.length() > 5` erfüllt ist.

4. **Verschachtelung von Bedingungen**:
    - Du kannst Pattern Matching auch in verschachtelten Bedingungen verwenden, 
      was den Code noch kompakter und verständlicher macht.

      **Beispiel:**

      ```java
      Object obj = 123;
    
      if (obj instanceof Integer i) {
        if (i > 100) {
            System.out.println(i + " is greater than 100");
        }
      }
      ```

5. **Negativprüfung**:
    - Es kann auch eine Negativprüfung in der Bedingung durchführt werden.
      Die Variable ist dann nur außerhalb des Blocks sichtbar.

      **Beispiel:**

      ```java
      Object obj = 123;
    
      if (!(obj instanceof Integer i)) {
          throw new IllegalArgumentException();
      }
      System.out.println("Argument i = " + i);
      ```

---

## Einschränkungen:

- **Pattern Matching für `instanceof`** funktioniert nur bei der Typprüfung und nicht bei anderen Operationen.
- **Scope der Variablen**: Die im `instanceof`-Pattern deklarierte Variable (`s` im Beispiel) ist nur innerhalb des entsprechenden Blocks sichtbar. Sie kann nicht außerhalb des `if`-Blocks verwendet werden.

### Beispiel (Fehlerhaft):

```java
Object obj = "Hello, World!";

if (obj instanceof String s) {
    System.out.println(s.toUpperCase());
}
// Die Variable 's' ist außerhalb des Blocks nicht sichtbar
System.out.println(s);  // Compiler-Fehler: 's' cannot be resolved
```

---

## Beispiel: Pattern Matching mit Switch Expressions

Das Pattern Matching wird in zukünftigen Java-Versionen mit anderen Features wie **Switch Expressions** kombiniert, um den Code noch präziser zu machen. Hier ist ein Beispiel mit `switch` (dieses Feature ist in zukünftigen Versionen verfügbar oder in der Vorschau):

```java
static void printObject(Object obj) {
    switch (obj) {
        case String s -> System.out.println("String: " + s.toUpperCase());
        case Integer i -> System.out.println("Integer: " + (i * 2));
        default -> System.out.println("Unknown type");
    }
}
```

- Hier wird `switch` mit Pattern Matching kombiniert, um automatisch die Typprüfung durchzuführen und den Typ in der passenden Variable zu speichern.

---

## Zusammenfassung:

- **Pattern Matching für `instanceof`** ist eine Erweiterung des `instanceof`-Operators, die die Typprüfung und das Casten vereinfacht.
- Es eliminiert den Bedarf für manuelle Typumwandlungen nach einer erfolgreichen Typprüfung und macht den Code lesbarer und sicherer.
- Das Feature verbessert die Lesbarkeit und Wartbarkeit von Code, indem es redundanten Code entfernt, der für Typprüfungen und Typumwandlungen benötigt wird.

Dieses Feature ist besonders nützlich, wenn du häufig mit polymorphen Typen arbeitest, bei denen der tatsächliche Typ eines Objekts geprüft und basierend auf dem Ergebnis Aktionen ausgeführt werden müssen.