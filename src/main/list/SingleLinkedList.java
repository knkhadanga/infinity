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

    /**
     * This method returns the new list after deleting the node which has data
     *
     * @param input
     * @param head
     * @return
     */
    Node deleteFromList(int input, Node head) {
        // Check from zero'th element
        // If head has the data, return the next node as head.
        if (head != null && head.data == input) {
            return head.next;
        }

        // Now check from 1st element in the list until last element.
        Node current_node = head;
        while (current_node != null && current_node.next != null) {
            if (current_node.next.data == input) {
                current_node.next = current_node.next.next;
                break;
            }
            current_node = current_node.next;
        }

        return head;
    }

    Node reverseList() {
        Node prvious = null;
        Node current = head;
        while (current != null) {
            Node next = current.next;
            current.next = prvious;
            prvious = current;
            current = next;
        }

        return prvious;
    }

    void displayList() {
        System.out.println("");
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
    }

    Node swapInPairs() {
        if (head == null || head.next == null) {
            return head;
        }

        Node dummy_node = new Node(0); // create a dummy node, this will always point to head
        dummy_node.next = head;
        Node previous_node = dummy_node; // this node will act as previous pointer to any node pair that is being swapped

        while (previous_node.next != null && previous_node.next.next != null) {
            // swap nodes in pair now
            Node first_node = previous_node.next;
            Node second_node = previous_node.next.next;

            first_node.next = second_node.next;
            second_node.next = first_node;
            previous_node.next = second_node; // this will establish the links between pairs

            // after establishing the link, reset its position as previous to next pair to be swapped
            previous_node = first_node;
        }

        return dummy_node.next;
    }

    // https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/
    void removeDuplicateFromSortedList() {
        Node currentNode = head;

        while (currentNode != null) {
            if ((currentNode.next != null) && (currentNode.data == currentNode.next.data)) {
                Node temp = currentNode.next;
                currentNode.next = temp.next;
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

    void removeElement() {
        head = removeElement(head, 22);
    }

    /**
     * Given the head of a linked list and an integer val, remove all the nodes of the linked list
     * that has Node.val == val, and return the new head.
     * https://leetcode.com/problems/remove-linked-list-elements/description/
     *
     * @param head
     * @param k
     * @return
     */
    Node removeElement(Node head, int k) {
        while (head != null && head.data == k) {
            head = head.next; // this will address for use case 1-1-1-1-1-2-3-1-4-1-5 when k = 1
        }
        Node current = head;
        Node previous = null;
        while (current != null) {
            if (current.data != k) {
                previous = current;
                current = current.next;
            } else {
                previous.next = current.next;
                current = current.next;
            }
        }
        return head;
    }
}
