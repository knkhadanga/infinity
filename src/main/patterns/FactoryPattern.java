package main.patterns;

/**
 * Factory pattern is one of the most used design patterns in Java.
 * This type of design pattern comes under creational pattern as
 * this pattern provides one of the best ways to create an object.
 *
 * In Factory pattern, we create object without exposing the creation logic
 * to the client and refer to newly created object using a common interface.
 */
public class FactoryPattern {
    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();
        factory.getShape("Circle").printShape();
    }
}

class Circle implements Shape {
    public void printShape() {
        System.out.println("I am a circle");
    }
}

class Rectangle implements Shape {
    public void printShape() {
        System.out.println("I am a Rectangle");
    }
}

class Square implements Shape {
    public void printShape() {
        System.out.println("I am a Square");
    }
}

class ShapeFactory {

    public Shape getShape(String shape) {
        if (shape.equalsIgnoreCase("circle")) {
            return new Circle();
        } else if (shape.equalsIgnoreCase("square")) {
            return new Square();
        } else if (shape.equalsIgnoreCase("Rectangle")) {
            return new Rectangle();
        }

        return null;
    }
}