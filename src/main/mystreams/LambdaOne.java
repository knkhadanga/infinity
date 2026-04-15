package main.mystreams;

public class LambdaOne {
    void testMyInterface(MyInterface myInterface) {
        String firstName = "Krushna";
        String lastName = "Khadanga";

        System.out.println("Printing the name:");
        myInterface.printName(firstName, lastName);
        System.out.println("Done printing the name");
    }

    public static void main(String[] args) {
        LambdaOne one = new LambdaOne();
        one.testMyInterface(
                (firstName, lastName) -> {
                    String name = firstName + " " + lastName;
                    System.out.println("Name is - " + name);
                }
        );
    }
}

interface MyInterface {
    void printName(String firstName, String lastName);
}