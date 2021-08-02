package main.list;

/*
https://leetcode.com/problems/add-two-numbers/

You are given two non-empty linked lists representing two non-negative integers.
The digits are stored in reverse order and each of their nodes contain a single digit.
Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero,
except the number 0 itself.


 Example:
    Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
    Output: 7 -> 0 -> 8
    Explanation: 342 + 465 = 807.
*/
public class AddlingTwoLists {
    public static void main(String[] args) {
        ListNode l3 = new ListNode(); l3.val = 3;
        ListNode l2 = new ListNode(); l2.val = 2;
        ListNode l1 = new ListNode(); l1.val = 1;

        l3.next = l2;
        l2.next = l1;

        ListNode l4 = new ListNode(); l4.val = 9;
        ListNode l5 = new ListNode(); l5.val = 5;
        ListNode l6 = new ListNode(); l6.val = 9;
        l4.next = l5;
        l5.next = l6;

        ListNode result;
        AddlingTwoLists test = new AddlingTwoLists();
        result = test.addTwoNumbers(l3, l4);

        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }

        ListNode returnList = null;
        ListNode lastNode = null;
        int carry = 0;

        while (l1 != null || l2 != null) {
            int sum = ((l1 == null) ? 0 : l1.val) + ((l2 == null) ? 0 : l2.val) + carry;
            carry = (sum > 9) ? 1 : 0;
            sum = sum % 10;

            ListNode temp = new ListNode();
            temp.val = sum;

            if (returnList == null){
                returnList = temp;
                lastNode = temp;
            }else {
                lastNode.next = temp;
                lastNode = lastNode.next;
            }

            if (l1 != null) {
                l1 = l1.next;
            }

            if (l2 != null){
                l2 = l2.next;
            }
        }
        System.out.println("Carry - " + carry);
        if (carry != 0){
            lastNode.next = new ListNode();
            lastNode.next.val = carry;
        }

        return returnList;
    }
}