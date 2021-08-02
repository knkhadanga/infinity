package main.miscs;

public class Queue {

    public  static void main (String[] args){
        Queue queue = new Queue();
        for (int i =0; i < 10; i++){
            queue.enqueue(i);
        }

        System.out.println (queue.peek().data + " ");
        System.out.println (queue.peek().data + " ");

        while (!queue.isEmpty()){
            System.out.print (queue.dequeue().data + " ");
        }
    }

    Node head;
    Node tail;

    void enqueue(int data) {
        if (head == null) {
            head = new Node();
            head.data = data;
            tail = head;
            return;
        }

        Node temp = new Node();
        temp.data = data;
        tail.next = temp;
        tail = temp;
    }

    Node dequeue() {
        if (head == null) {
            return null;
        }

        Node temp = head;
        head = head.next;

        temp.next = null;
        return temp;
    }

    Node peek() {
        if (head == null) {
            return null;
        }

        Node temp = new Node();
        temp.data = head.data;

        return temp;
    }

    boolean isEmpty() {
        if (head == null) {
            return true;
        }

        return false;
    }
}


