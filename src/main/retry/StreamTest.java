package main.retry;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Programs to verify java 8 features
 */
public class StreamTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        list.add("test");

        //print all the string in list whose length is more than 4
        long count = list.stream().filter(str -> str.length() > 4).count();
        System.out.println("Number of string with length more than 4 - " + count);


        List<String> names = list.stream().filter(str -> str.length() > 4).collect(Collectors.toList());
        System.out.println("Names = " + names);

        //print names using for each
        names.stream().forEach(System.out::println);

        names.forEach(name -> System.out.println(name));


    }

}
