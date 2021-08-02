package main.miscs;

public class DoubleLinkedList<T> {
    NodeG<T> head;
    NodeG<T> lastNode;

    void addToList(T input) {
        if (head == null) {
            head = new NodeG();
            head.data = input;
            head.next = null;
            head.previous = null;

            lastNode = head;
            return;
        }
        NodeG temp = new NodeG();
        temp.data = input;
        temp.next = null;
        lastNode.next = temp;
        temp.previous = lastNode;

        lastNode = temp;
    }

    NodeG deleteFromList(T input) {

        NodeG<T> returnNode = null;

        if (head == null) {
            return null;
        }

        if (head.data == input) {
            returnNode = head;
            NodeG x = head.next;

            if (x != null) {
                x.previous = null;
                head = x;
            } else {
                head = null;
                lastNode = null;
            }

            return returnNode;
        }

        NodeG<T> temp = head;
        while (temp != null) {
            if (temp.data == input) {
                returnNode = temp;
                NodeG p = temp.previous;
                NodeG n = temp.next;


                p.next = n;
                if (n != null) {
                    n.previous = p;
                } else {
                    lastNode = p;
                }

                break;
            }

            temp = temp.next;
        }

        returnNode.next = null;
        returnNode.previous = null;
        return returnNode;
    }

    void displayList() {
        NodeG<T> temp = head;
        System.out.println("");
        System.out.println("Displaying forward ...");
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }

        System.out.println("\nDisplaying backward ...");
        temp = lastNode;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.previous;
        }
    }

    public static void main(String[] args) {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();

        for (int x = 0; x < 5; x++) {
            list.addToList(x);
        }

        list.displayList();

      //  System.out.println ("\n\nDeleting - 9");
      //  Node xt = list.deleteFromList(9);
      //  System.out.println("Deleted - " + xt.data);

      //  list.displayList();

        System.out.println("\nReversing list ---- ");
        list.reverseList();
        list.displayList();
    }



    void reverseList (){
        NodeG<T> current = head;
        NodeG<T> tempLast = head;
        NodeG<T> temp = null;

        while (current != null){
            temp = current.previous;
            current.previous = current.next;
            current.next = temp;
            current = current.previous;
        }

        head = temp.previous;
        lastNode = tempLast;
    }

    void reverseListNew(){
        NodeG<T> temp = null;
        NodeG<T> current_node = head;
        while(current_node != null){
            NodeG<T> next = current_node.next;
            current_node.next = temp;
            current_node.previous = next;
            temp = current_node;
            current_node = next;
        }

        head = temp;
    }
}
