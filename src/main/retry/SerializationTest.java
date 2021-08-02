package main.retry;

import java.io.*;

public class SerializationTest {
    String path = "/Users/kkhadang/Desktop/ser.test";
    Employee employee = new Employee("Hello", 32, "Belmont");
    Employee testObject;

    public static void main(String[] args) throws Exception {
        SerializationTest test = new SerializationTest();
        test.serialize();
        test.deSerealize();
    }

    void serialize() throws Exception {
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(employee);
        oos.close();
        fos.close();
    }

    void deSerealize() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);

        testObject = (Employee) ois.readObject();
        System.out.println("Data from the object - ");
        System.out.print(testObject.name + " " + testObject.age + " " + testObject.city + " "
                + testObject.transientOne + " " + testObject.transientTwo);
    }

}

class Employee implements Serializable {
    String name;
    int age;
    String city;
    transient String transientOne = "got transient one ...";
    transient String transientTwo = "got transient two ...";

    public Employee(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    /**
     * Implement the following method to write the transient items of the employee
     * object while serielizing the employee object. The write object would store
     * the transient variable while seriealizing the object which otherwise ignored.
     *
     * @param os
     * @throws IOException
     */
    private void writeObject(ObjectOutputStream os) throws IOException {
        os.defaultWriteObject();
        os.writeUTF(this.transientOne);
        os.writeUTF(this.transientTwo);
    }

    /**
     * Implement the following method to read the transient items of the employee
     * object. The method would restore the specially stored transient variable while
     * deserializing the object.
     */
    private void readObject(ObjectInputStream oi) throws IOException, ClassNotFoundException {
        oi.defaultReadObject();

        //read in the order they are written in the writeObject method
        this.transientOne = oi.readUTF();
        this.transientTwo = oi.readUTF();
    }
}

