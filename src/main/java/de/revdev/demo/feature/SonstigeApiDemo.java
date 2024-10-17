package de.revdev.demo.feature;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SonstigeApiDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        filesMismatch();
        runtimeVersion();
        httpClient();
        streamToList();
        regexToPredicate();
        dayPeriodRules();
    }

    public static void filesMismatch() throws IOException {
        final Path path1 = Paths.get("src/main/resources/match/file.txt");
        final Path path2 = Paths.get("src/main/resources/mismatch/file.txt");
        final Path path3 = Paths.get("src/main/resources/match/file.txt");
        System.out.println(Files.mismatch(path1, path2));
        System.out.println(Files.mismatch(path1, path3));
    }

    public static void runtimeVersion() {
        System.out.println(Runtime.version());
    }

    public static void httpClient() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                                         .uri(URI.create("http://10.233.42.127:7778/RMSParse4j/v2/info"))
                                         .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    public static void streamToList() {
        List<String> listAlt = Stream.of("a", "b", "c").collect(Collectors.toList());
        System.out.println(listAlt);
        List<String> listNeu = Stream.of("a", "b", "c").toList();
        System.out.println(listNeu);
    }

    public static void regexToPredicate() {
        Predicate<String> predicate = Pattern.compile("[a-z]+").asMatchPredicate();
        System.out.println(predicate.test("abc"));
        final List<String> list = Stream.of("abc", "ABC", "def").filter(predicate).toList();
        System.out.println(list);
    }

    public static void dayPeriodRules() {
        System.out.printf("Pattern 'B' (time now): %s\n", DateTimeFormatter.ofPattern("B").format(LocalTime.now()));
        System.out.printf("Pattern 'B' (time now): %s\n", DateTimeFormatter.ofPattern("B").format(LocalTime.now().plusHours(12)));
    }

}
