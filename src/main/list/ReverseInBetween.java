package main.list;

/*
    Reverse a linked list from position m to n.
    Do it in one-pass.
    Note: 1 ≤ m ≤ n ≤ length of list.

    Input: 1->2->3->4->5->NULL, m = 2, n = 4
    Output: 1->4->3->2->5->NULL

    https://www.youtube.com/watch?v=GSJuwQzKSnI

    https://leetcode.com/problems/reverse-linked-list-ii/

 */
public class ReverseInBetween {

    public static void main(String[] args) {
        
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || (m == n)) {
            return head;
        }

        ListNode previous = null;
        ListNode current_node = head;

        //iterate to find the start node from where the reverse has to be done
        while (m > 1) {
            previous = current_node;
            current_node = current_node.next;
            m--;
            n--; //decrement n as well
        }

        ListNode connection_node = previous; //(m-1)th node

        //this is mth node which will be tail after reverse
        ListNode new_tail = current_node;

        //now reverse from m to n
        ListNode temp = null;
        while (n > 0) {
            ListNode next_node = current_node.next;
            current_node.next = temp;
            temp = current_node;
            current_node = next_node;
            n--;
        }

        ListNode new_head = temp; //this is the new head of reversed sub list
        if (connection_node != null) {
            connection_node.next = new_head;
        } else {
            head = new_head;
        }

        new_tail.next = current_node;
        return head;
    }
}
