package main.list;

import main.miscs.Node;

public class SingleLinkedList {

    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList();

        list.addToList(1);
        list.addToList(2);
        list.addToList(1);
        list.addToList(1);
        list.addToList(11);
        list.addToList(22);
        list.addToList(1);
        list.displayList();
        list.removeElement();
        list.displayList();

        SingleLinkedList list1 = new SingleLinkedList();

        list1.addToList(10);
        list1.addToList(12);
        list1.addToList(23);
        list1.addToList(45);

        SingleLinkedList test = new SingleLinkedList();
        //test.mergeList(list1, list);

/*
        list.displayList();

        list.reverseList();

        list.displayList();

        list.deleteFromList(5);

        list.displayList();

        list.swapInPairs();

        list.displayList();

 */

        //  list.displayList();
        //  list.removeDuplicateFromSortedList();
        //  list.displayList();

    }

    Node head;
    Node lastNodePointer;

    void addToList(int input) {

        if (head == null) {
            head = new Node();
            head.data = input;
            lastNodePointer = head;

            return;
        }

        Node temp = new Node();
        temp.data = input;
        lastNodePointer.next = temp;
        lastNodePointer = temp;
    }

    Node deleteFromList(int input) {

        if (head == null) {
            return null;
        }

        if (head.data == input) {
            Node temp = head;
            head = head.next;

            return temp;
        }

        Node temp = head;
        Node nextNode = head.next;
        Node returnNode = null;

        while (nextNode != null) {
            if (nextNode.data == input) {
                returnNode = nextNode;
                temp.next = nextNode.next;
                break;
            } else {
                temp = nextNode;
                nextNode = nextNode.next;
            }
        }

        return nextNode;
    }

    void reverseList() {
        Node temp = head;
        Node x = head;
        Node y = x.next;

        Node z = null;
        while (y != null) {
            z = y.next;
            y.next = x;
            x = y;
            y = z;
        }

        head = x;
        temp.next = null;
    }

    void displayList() {
        System.out.println("");
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
    }

    void swapInPairs() {
        swapInPairs(head);
    }

    void swapInPairs(Node head) {

        if (head == null) {
            return;
        }

        Node tempHead = head.next;
        Node secondNode = head.next;
        Node current = head;

        if (secondNode != null) {
            head.next = secondNode.next;
            secondNode.next = head;
        }

        Node prev = current;
        current = current.next;

        while (current != null) {
            Node next = current.next;
            if (next == null) {
                break;
            }

            current.next = next.next;
            next.next = current;
            prev.next = next;
            prev = current;
            current = current.next;
        }

        this.head = tempHead;
    }

    void removeDuplicateFromSortedList() {
        Node currentNode = head;

        while (currentNode.next != null) {

            if (currentNode.data == currentNode.next.data) {
                Node temp = currentNode.next;

                currentNode.next = currentNode.next.next;
                temp.next = null;
            } else {
                currentNode = currentNode.next;
            }
        }
    }

    void mergeList(SingleLinkedList list1, SingleLinkedList list2) {
        Node head = mergeSortedList(list1.head, list2.head);
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

    /**
     * Method to merge two sorted list
     *
     * @param head1
     * @param head2
     * @return
     */
    Node mergeSortedList(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return (head1 == null) ? head1 : head2;
        }

        if (head1 == null && head2 == null) {
            return null;
        }

        Node head = null;
        Node current = null;
        int i = 0;
        while (head1 != null && head2 != null) {
            int data = (head1.data > head2.data) ? head2.data : head1.data;
            System.out.println("data = " + data);
            if (head == null) {
                head = new Node();
                head.data = data;
                current = head;
            } else {
                current.next = new Node();
                current = current.next;
                current.data = data;
            }

            if (head1.data > head2.data) {
                head2 = head2.next;
            } else {
                head1 = head1.next;
            }
        }

        if (head1 != null) {
            current.next = head1;
        }

        if (head2 != null) {
            current.next = head2;
        }

        return head;
    }

    void detectLoop() {
        Node slow;
        Node fast;

        slow = fast = head;
        boolean loopDetected = false;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                System.out.println("Loop detected at = " + slow.data);
                loopDetected = true;
                break;
            }
        }

        //find length of loop
        if (loopDetected) {
            int length = 0;
            while (slow.next != fast) {
                slow = slow.next;
                length++;
            }

            length++;
            System.out.println("Loop length = " + length);
        } else {
            System.out.println("No loop exist in the list.");
        }
    }

    void removeElement(){
        head = removeElement(head, 22);
    }
    /**
     * Remove all the occurance of element k from the list and returns the new head
     * @param head
     * @param k
     * @return
     */
    Node removeElement(Node head, int k){
        if (head == null) return null;

        Node temp = head;
        while (temp != null) {
            if (temp.data == k){
                temp = temp.next;
            } else {
                break;
            }
        }

        head = temp;
        Node previous = null;
        while (temp != null){
            if (temp.data != k){
                previous = temp;
                temp = temp.next;
            }else{
                while (temp!= null && temp.data == k){
                    temp = temp.next;
                }

                previous.next = temp;
            }
        }

        return head;
    }
}
