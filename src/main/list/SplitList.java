package main.list;

/*
Given a (singly) linked list with head node root, write a function to split the linked list
into k consecutive linked list "parts". The length of each part should be as equal as
possible: no two parts should have a size differing by more than 1.
This may lead to some parts being null. The parts should be in order of occurrence
in the input list, and parts occurring earlier should always have a size
greater than or equal parts occurring later. Return a List of ListNode's
representing the linked list parts that are formed.

Examples 1->2->3->4, k = 5 // 5 equal parts [ [1], [2], [3], [4], null ]

https://leetcode.com/problems/split-linked-list-in-parts/

 */
public class SplitList {

    ListNode[] solution(ListNode root, int k) {
        ListNode[] output = new ListNode[k];

        //get length of the list
        int length = 0;
        for (ListNode temp = root; temp != null; temp = temp.next) {
            length++;
        }

        int n = length / k; //total number of list with even
        int r = length % k;

        ListNode node = root;
        ListNode previous = null;

        for (int i = 0; i < k && node != null; i++, r--) {
            output[i] = node;
            for (int j = 0; j < n + (r > 0 ? 1 : 0); j++) {
                previous = node;
                node = node.next;
            }

            previous.next = null;
        }

        return output;
    }

}
