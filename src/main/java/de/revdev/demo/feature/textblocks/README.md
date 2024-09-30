### **Text Blocks** in Java

**Text Blocks** wurden in **Java 13** als Vorschau eingeführt und sind seit **Java 15** ein fester Bestandteil 
der Sprache. 
Sie ermöglichen es, mehrzeilige Strings einfach und übersichtlich darzustellen, 
ohne dass viele Escape-Zeichen notwendig sind. 
Text Blocks verbessern die Lesbarkeit von code-integrierten textbasierten Daten wie **JSON**, **SQL**, **HTML**, 
oder anderen mehrzeiligen Texten.

---

## Ziel von Text Blocks:

- **Mehrzeilige Strings**: Text Blocks ermöglichen das Schreiben von Strings über mehrere Zeilen hinweg, 
  ohne dass Zeichen wie `\n` oder `+` zur Verkettung benötigt werden.
- **Reduktion von Escape-Zeichen**: Escape-Sequenzen wie `\"` oder `\\` werden durch die Text Blocks 
  überflüssig oder drastisch reduziert.
- **Verbesserte Lesbarkeit**: Formatierungen und Einrückungen des Texts bleiben erhalten und 
  verbessern die Lesbarkeit und Wartbarkeit des Codes.
- **Zeilenenden anpassen**: Text Blocks bieten verschiedene Möglichkeiten, wie Zeilenenden behandelt werden, 
  um den String genau so zu formatieren, wie es der Anwendungsfall erfordert.

---

## Syntax von Text Blocks

Text Blocks werden durch **drei Anführungszeichen (`"""`)** eingeleitet und abgeschlossen. 
Der Text dazwischen kann sich über mehrere Zeilen erstrecken.

### Einfaches Beispiel:

```java
String json = """
    {
        "name": "John",
        "age": 30
    }
    """;
```

- Hier wird der Text so dargestellt, wie er im Quellcode steht, einschließlich der Zeilenumbrüche und der Einrückung.
- Im Gegensatz zu herkömmlichen Strings musst du keine Escape-Sequenzen 
  für Anführungszeichen oder Zeilenumbrüche verwenden.

---

## Eigenschaften von Text Blocks

### 1. **Mehrzeilige Strings ohne Escape-Zeichen**

Mit Text Blocks kannst du mehrzeilige Texte, die häufig in Formaten wie JSON, SQL oder HTML vorkommen, 
klar und strukturiert darstellen, ohne Zeichen wie `\n` oder `+` zu benötigen.

#### Vor Text Blocks:
```java
String query = "SELECT * FROM users\n" +
               "WHERE age > 30\n" +
               "ORDER BY name;";
```

#### Mit Text Blocks:
```java
String query = """
    SELECT * FROM users
    WHERE age > 30
    ORDER BY name;
    """;
```

In diesem Fall wird die SQL-Abfrage in ihrer vollständigen, mehrzeiligen Form ohne zusätzliche Zeichen dargestellt.

### 2. **Einrückungen und Zeilenumbrüche**

Text Blocks analysieren automatisch die Einrückung, die du im Code verwendest, 
und passen die Darstellung entsprechend an. 
Die Leerzeichen vor der ersten Zeile bestimmen dabei, wie der Text eingerückt wird.

#### Beispiel:
```java
String html = """
        <html>
            <body>
                <h1>Hello, World!</h1>
            </body>
        </html>
        """;
```

- Die Einrückungen werden wie im Quellcode dargestellt.
- Der Compiler entfernt die Leerzeichen, welche in allen Zeilen gleich auf der linken Seite sind, um die Einrückung sauber zu halten.

---

## **Unterschiedliche Zeilenenden**

Text Blocks bieten verschiedene Möglichkeiten, wie Zeilenenden und Leerzeichen am Ende des Text Blocks behandelt werden. 
Java bietet dabei eine Flexibilität, um den Text so darzustellen, wie es die jeweiligen Anforderungen verlangen.

### 1. **Automatische Zeilenenden**

Standardmäßig fügt Java am Ende jeder Zeile eines Text Blocks einen **Zeilenumbruch** hinzu. 
Dies bedeutet, dass die Zeilen im Text Block genauso formatiert werden wie im Quellcode.

#### Beispiel:
```java
String text = """
    This is line one.
    This is line two.
    """;
System.out.println(text);
```

- Ausgabe:
  ```
  This is line one.
  This is line two.
  ```

### 2. **Vermeiden von Zeilenumbrüchen am Ende**

Wenn du den Zeilenumbruch am Ende eines Text Blocks vermeiden möchtest, 
kannst du einen Backslash (`\`) am Ende der Zeile verwenden. 
Dies verbindet die Zeilen ohne Zeilenumbruch.

#### Beispiel:
```java
String text = """
    This is line one. \
    This is still line one.
    """;
System.out.println(text);
```

- Ausgabe:
  ```
  This is line one. This is still line one.
  ```

In diesem Beispiel wird der Backslash verwendet, 
um die zweite Zeile mit der ersten zu verbinden, 
ohne einen Zeilenumbruch einzufügen.

---

## Vorteile von Text Blocks:

1. **Lesbarkeit und Wartbarkeit**:
    - Text Blocks machen es einfacher, formatierten Text direkt in Java-Code zu schreiben und zu lesen,
      ohne den Code unübersichtlich zu machen.

2. **Weniger Fehleranfällig**:
    - Da weniger Escape-Zeichen verwendet werden, 
      sinkt die Wahrscheinlichkeit für Fehler beim Arbeiten mit komplizierten Texten wie HTML oder JSON.

3. **Einfache Formatierung**:
    - Text Blocks bewahren die Formatierung des Textes im Quellcode, 
      sodass du Texte in ihrer natürlichen Struktur darstellen kannst.

4. **Automatische Handhabung von Zeilenumbrüchen**:
    - Zeilenumbrüche und Leerzeichen werden automatisch entsprechend der Einrückung im Quellcode behandelt, 
      was die Formatierung von Ausgaben vereinfacht.

---

## Verwendung von Text Blocks in der Praxis

Text Blocks sind besonders nützlich, 
wenn man längere Textdaten wie **JSON**, **SQL**, **HTML** 
oder **konfigurationsbezogene Strings** direkt in Java-Code einbetten möchte.

### JSON-Beispiel:
```java
String json = """
    {
        "firstName": "John",
        "lastName": "Doe",
        "age": 25,
        "address": {
            "street": "123 Main St",
            "city": "Springfield"
        }
    }
    """;
```

### SQL-Beispiel:
```java
String sql = """
    SELECT id, name, age
    FROM users
    WHERE age > 18
    ORDER BY name;
    """;
```

### HTML-Beispiel:
```java
String html = """
    <html>
        <head>
            <title>Sample Page</title>
        </head>
        <body>
            <h1>Welcome to my website!</h1>
        </body>
    </html>
    """;
```

---

## Zusammenfassung:

- **Text Blocks** in Java bieten eine elegante und lesbare Möglichkeit, mit mehrzeiligen Strings umzugehen.
- Sie reduzieren die Notwendigkeit von Escape-Zeichen und machen den Code einfacher zu lesen und zu schreiben.
- Die Handhabung von **Zeilenumbrüchen**, **Einrückungen** und **Zeilenenden** ist flexibel, was die Darstellung von Texten in Java vereinfacht.
- Sie sind ideal für das Arbeiten mit formatierten Texten wie JSON, HTML, SQL oder anderen längeren Textblöcken, die im Quellcode erhalten bleiben sollen.

Text Blocks machen den Code kompakter, weniger fehleranfällig und leichter wartbar, insbesondere bei umfangreichen textbasierten Daten.