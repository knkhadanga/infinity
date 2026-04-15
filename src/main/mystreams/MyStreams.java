package main.mystreams;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MyStreams {
    public static void main(String[] args) {
        streamTest();
    }

    static void streamTest() {
        List<String> names = List.of("appl", "banana", "cherry", "orange");
        List<String> results = names.stream()
                .filter(name -> name.contains("a"))
                .collect(Collectors.toList());

        results.forEach(output -> System.out.println(output));
        results.forEach(System.out::println);

        results = names.stream()
                .map(name -> name + "_lastname")
                .collect(Collectors.toList());

        results.forEach(name -> System.out.println(name));
        System.out.println("----------------------");
        results.forEach(System.out::println);

        System.out.println("----------------------");
        results
                .stream()
                .map(name -> name.toUpperCase())
                .forEach(System.out::println);

        System.out.println("----------------------");
        names
                .stream()
                .skip(2)
                .forEach(System.out::println);

        System.out.println("\n Testing drop while");
        names
                .stream()
                .dropWhile(name -> name.length() < 5)
                .forEach(System.out::println);

        System.out.println("\n Print first 2 elements");
        names
                .stream()
                .limit(2)
                .forEach(System.out::println);

        System.out.println("\nBreak loop using take while");
        names
                .stream()
                .takeWhile(name -> name.length() < 5)
                .map(name -> name.toUpperCase())
                .forEach(System.out::println);

        System.out.println("\nPredicate");

        Predicate<String> startsWithA = name -> name.startsWith("a");
        names.stream().filter(startsWithA).forEach(System.out::println);

        System.out.println("\nPredicate function");
        names.stream().filter(nameStartWith("a")).forEach(System.out::println);

        final Function<String, Predicate<String>> testFunction = //this function takes a string and returns a predicate
                (String letter) -> {
                    Predicate<String> checkStartPredicate = name -> name.startsWith(letter);
                    return checkStartPredicate;
                };

        System.out.println("\nTest with function");
        //when apply is called, it returns the predicate, which is used with filter condition. The predicate is lazy initialized
        names.stream().filter(testFunction.apply("a")).forEach(System.out::println);

        System.out.println("\nUsing optional");
        final Function<String, Predicate<String>> findNameFunction =
                (String inputName) -> {
                    Predicate<String> checkName = name -> name.equals(inputName);
                    return checkName;
                };

        Optional<String> foundName = names.stream().filter(findNameFunction.apply("appl")).findFirst();
        foundName.ifPresent(name -> System.out.println("Found name - " + name));
        if (foundName.isPresent()) {
            System.out.println("Found name - " + foundName.get());
        } else {
            System.out.println("Name doesn't exist.");
        }

        System.out.println("\nLength of the names - ");
        names.stream().mapToInt(name -> name.length()).forEach(System.out::println);

        System.out.println("\nLongest name - ");
        Optional<String> longestName = names.stream().reduce((name1, name2) ->
                (name1.length() >= name2.length()) ? name1 : name2
        );
        longestName.ifPresent(name -> System.out.println("Longest name found - " + name));

        System.out.println("\nprint upper case");
        System.out.println(
                names.stream().map(name -> name.toUpperCase()).collect(Collectors.joining(","))
        );
    }

    public static Predicate<String> nameStartWith(final String letter) {
        return name -> name.startsWith(letter);
    }


}
