package main.patterns;

public class AbstractFactory {
    public static void main(String[] args) {
        FactoryProducer test = new FactoryProducer();
        Factory factory = test.getFactory("regular");
        Shape shape = factory.getShape("circle");
        shape.printShape();

        factory = test.getFactory("rounded");
        shape = factory.getShape("circle");
        shape.printShape();
    }

}

class FactoryProducer {
    Factory getFactory(String type) {
        if ("regular".equalsIgnoreCase(type)) {
            return new RegularShapeFactory();
        } else {
            return new RoundedShapeFactory();
        }
    }
}

abstract class Factory {
    abstract Shape getShape(String shape);
}

class RegularShapeFactory extends Factory {

    @Override
    Shape getShape(String shape) {
        if ("circle".equalsIgnoreCase(shape)) {
            return new RegularCircle();
        }

        if ("rectangle".equalsIgnoreCase(shape)) {
            return new RegularRectangle();
        }

        return null;
    }
}

class RoundedShapeFactory extends Factory {

    @Override
    Shape getShape(String shape) {
        if ("circle".equalsIgnoreCase(shape)) {
            return new RoundedCircle();
        }

        if ("rectangle".equalsIgnoreCase(shape)) {
            return new RoundedRectangle();
        }

        return null;
    }
}

class RegularCircle implements Shape {
    public void printShape() {
        System.out.println("I am a regular circle");
    }
}

class RegularRectangle implements Shape {
    public void printShape() {
        System.out.println("I am a regular Rectangle");
    }
}

class RoundedCircle implements Shape {
    public void printShape() {
        System.out.println("I am a rounded circle");
    }
}

class RoundedRectangle implements Shape {
    public void printShape() {
        System.out.println("I am a rounded Rectangle");
    }
}









