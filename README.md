## Language Features

- [Switch Expressions](src/main/java/de/revdev/demo/feature/switchexp/README.md)
- [Sealed Classes](src/main/java/de/revdev/demo/feature/sealed/README.md)
- [Records](src/main/java/de/revdev/demo/feature/records/README.md)
- [Pattern Matching `instanceof`](src/main/java/de/revdev/demo/feature/patmatchinstof/README.md)
- [Text Block](src/main/java/de/revdev/demo/feature/textblocks/README.md)

## Api-Änderungen

### String-Klasse

In der **`String`-Klasse** gab es von **JDK 12** bis **JDK 17** einige nützliche Änderungen und Erweiterungen. 
Hier sind die wichtigsten Neuerungen:

[Beispiele](src/main/java/de/revdev/demo/api/StringKlasse.java)

### 1. **`indent(int n)`** (seit JDK 12)
- Fügt jeder Zeile des Strings eine Einrückung hinzu oder entfernt sie, wenn der Wert negativ ist.
- **Beispiel:**
  ```java
  String indented = "Hello\nWorld".indent(4);
  // Ergebnis:
  //     Hello
  //     World
  ```

### 2. **`transform(Function<String, R> f)`** (seit JDK 12)
- Wendet eine gegebene Funktion auf den String an und gibt das Ergebnis zurück.
- **Beispiel:**
  ```java
  Integer length = "Hello".transform(String::length);  // Ergebnis: 5
  ```

### 3. **`formatted(Object... args)`** (seit JDK 15)
- Ersetzt Platzhalter im String mit den angegebenen Argumenten, ähnlich wie `String.format()`, 
  jedoch als Instanzmethode.
- **Beispiel:**
  ```java
  String result = "Name: %s, Age: %d".formatted("Alice", 30);
  // Ergebnis: "Name: Alice, Age: 30"
  ```

### Collections-Framework

Seit **JDK 12** wurden einige neue Funktionen und Verbesserungen im Zusammenhang mit **Collections** eingeführt, 
ie die Arbeit mit Collections erleichtern und effizienter machen. 
Hier sind die wichtigsten Änderungen:

#### 1. **`Collectors`-Verbesserungen (seit JDK 12)**
- Neue Methoden wurden in der `Collectors`-Klasse hinzugefügt:
  - **`teeing(Collector<T,A,R1>, Collector<T,A,R2>, BiFunction<R1,R2,R>)`**: Erlaubt das Sammeln eines Streams 
    in zwei verschiedene Sammlungen und kombiniert deren Ergebnisse.
  - **Beispiel:**
    ```java
    var result = Stream.of(1, 2, 3, 4, 5)
        .collect(Collectors.teeing(
            Collectors.summingInt(i -> i),    // Summe aller Zahlen
            Collectors.counting(),            // Anzahl der Elemente
            (sum, count) -> sum / count       // Durchschnitt
        ));
    ```

#### 2. **Neue Methoden für `Map`-Interfaces (seit JDK 12)**
- **`Map.ofEntries(Map.Entry<K,V>... entries)`**: Ermöglicht das einfache Erstellen 
  von unveränderlichen Maps mit mehreren Einträgen.
- **`Map.entry(K key, V value)`**: Eine Methode, um Map-Einträge zu erstellen.
- **Beispiel:**
  ```java
  Map<String, Integer> map = Map.ofEntries(
      Map.entry("a", 1),
      Map.entry("b", 2)
  );
  ```

#### 3. **Neuer `nullPolicy` in `TreeMap` (seit JDK 16)**
- Ab JDK 16 kann bei der Erstellung einer `TreeMap` angegeben werden, wie `null`-Werte behandelt werden sollen.
- **Beispiel:**
  ```java
  TreeMap<String, Integer> treeMap = new TreeMap<>(Comparator.nullsFirst(String::compareTo));
  ```

### Sonstige Api's

Zwischen **JDK 12 und JDK 17** gab es in verschiedenen APIs viele bemerkenswerte Änderungen und Erweiterungen. 
Hier sind einige wichtige Neuerungen in verschiedenen Bereichen:

### 1. **`java.nio.file` (Dateien und Pfade)**

- **`Files.mismatch(Path, Path)`** (seit JDK 12):
  - Diese Methode vergleicht den Inhalt zweier Dateien und gibt die Position des ersten Unterschieds zurück, 
    oder `-1`, wenn die Dateien identisch sind.
  - **Beispiel:**
    ```java
    long mismatch = Files.mismatch(path1, path2);
    ```

### 2. **`java.lang`**

- **`Object.finalize()` (Deprecated seit JDK 16)**:
  - Die Methode `finalize()` wurde als veraltet markiert, 
    da sie ineffizient ist und durch alternative Mechanismen wie `try-with-resources` 
    und explizites Ressourcenmanagement ersetzt wurde.

- **`Runtime.Version` Verbesserungen (JDK 12)**:
  - Neue Methoden wie `Runtime.Version::feature` für das einfache Abrufen der Hauptversion von Java.

### 3. **`java.net` (Netzwerk)**

- **`HttpClient` API (seit JDK 11, aber nennenswerte Verbesserungen in JDK 12-17)**:
  - Verbesserungen in der `HttpClient`-API, die nun stabil und produktionsbereit ist. 
    Sie bietet eine einfache Möglichkeit, asynchrone HTTP-Anfragen zu versenden.
  - **Beispiel für GET-Anfrage:**
    ```java
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://example.com"))
        .build();
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    System.out.println(response.body());
    ```

### 4. **`java.util`**

- **`Pattern.asMatchPredicate()` (seit JDK 12)**:
  - Erzeugt ein `Predicate<String>`, das überprüft, ob der String das Muster vollständig erfüllt.
  - **Beispiel:**
    ```java
    Predicate<String> predicate = Pattern.compile("[a-z]+").asMatchPredicate();
    boolean matches = predicate.test("abc");  // true
    ```

- **`Comparator` Verbesserungen (seit JDK 12)**:
  - `Comparator` enthält nun eine Methode **`thenComparingInt(ToIntFunction<? super T>)`**, 
    die das Vergleichsverhalten verbessert, indem es einen zusätzlichen Vergleich auf Basis von Ganzzahlen ermöglicht.

### 5. **`java.time` (Datum und Zeit)**

- **`InstantSource` Interface (seit JDK 17)**:
  - Ermöglicht das Abrufen eines aktuellen Zeitpunkts (`Instant`) 
    und kann für Tests oder zeitbasierte Operationen verwendet werden.
  - **Beispiel:**
    ```java
    InstantSource source = InstantSource.system();
    Instant now = source.instant();
    ```

- **`DayPeriodRules` (seit JDK 17)**:
  - Ein neues Utility, das für die Handhabung von Tagesperioden (wie "Morgen", "Nachmittag" usw.) verwendet wird.

### 6. **`java.util.stream` (Streams)**

- **`Stream.toList()` (seit JDK 16)**:
  - Diese Methode sammelt die Elemente eines Streams in eine unveränderliche Liste. 
    Sie ist eine kompaktere Alternative zu `Collectors.toList()`.
  - **Beispiel:**
    ```java
    List<String> list = Stream.of("a", "b", "c").toList();
    ```

### 7. **`java.nio.charset`**

- **StandardCharsets.UTF_16 (seit JDK 17)**:
  - Es wurde eine Konstante für **UTF_16** in der Klasse `StandardCharsets` eingeführt,
    was die Arbeit mit diesem Charset vereinfacht.
  - **Beispiel:**
    ```java
    String text = "Hello, World!";
    byte[] bytes = text.getBytes(StandardCharsets.UTF_16);
    ```

### 8. **`java.lang.invoke` (Method Handles und dynamische Aufrufe)**

- **`VarHandle`-Erweiterungen (seit JDK 14)**:
  - Verbesserungen in der `VarHandle` API, die nun **`compareAndExchange`**,
    **`getAndSet`** und weitere atomare Operationen auf Arrays und Objekten bietet.

- **`MethodHandles::privateLookupIn` (seit JDK 12)**:
  - Diese Methode erlaubt es, auf private Methoden und Felder über `MethodHandles.Lookup` zuzugreifen,
    wenn eine Erlaubnis besteht.
  - **Beispiel:**
    ```java
    MethodHandles.Lookup lookup = MethodHandles.privateLookupIn(SomeClass.class, MethodHandles.lookup());
    ```