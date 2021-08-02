package main.miscs;

import java.util.Stack;

/**
 * Generic implementations of Stack
 *
 * @param <T>
 */
public class StackG<T> {

    private NodeG<T> head;

    void push(T data) {
        if (head == null) {
            head = new NodeG<>();
            head.data = data;
            return;
        }

        NodeG<T> temp = new NodeG<>();
        temp.data = data;
        temp.next = head;
        head = temp;
    }

    public NodeG<T> pop() {
        if (head == null) {
            return null;
        }

        NodeG<T> temp = head;
        head = head.next;
        temp.next = null;

        return temp;
    }

    boolean isEmpty() {
        if (head == null) {
            return true;
        }

        return false;
    }

    public <T> NodeG peek() {
        if (head == null) {
            return null;
        }

        return head;
    }

    public static <T extends Number & Comparable<T>> T countSum(T one, T two) {
        if (one.compareTo(two) > 0) {
            return one;
        }

        return two;
    }

    /**
     * Method to check if the expression is balanced or not
     *
     * @param input
     * @return
     */
    boolean balancingSymobol(String input) {
        if (input == null || input.isEmpty()) {
            System.out.println("Invalid input");
            return false;
        }

        char[] array = input.toCharArray();
        Stack<Character> stack = new Stack<>();

        for (char ch : array) {
            if (ch == '(') {
                stack.push(ch);
                continue;
            }

            if (stack.isEmpty()) {
                return false;
            }

            if (ch == ')') {
                char out = stack.pop();
                if (out != '(') {
                    return false;
                }
            }
        }

        if (!stack.isEmpty()) {
            return false;
        }

        return true;
    }

    /**
     * Method to convert infix to postfix conversion
     *
     * @param input
     */
    void infixToPostfixConversion(String input) {
        if (input == null || input.isEmpty()) {
            System.out.println("Invalid input");
            return;
        }

        Stack<Character> stack = new Stack<>();
        char[] array = input.toCharArray();
        StringBuilder stb = new StringBuilder();

        for (char ch : array) {
            if (ch == '(' || ch == ')' || ch == '^' || ch == '*' || ch == '/' || ch == '+' || ch == '-') {
                pushAndUpdateStack(stack, stb, ch);
            } else {
                stb.append(ch);
            }
        }

        while (!stack.isEmpty()){
            stb.append(stack.pop());
        }

        System.out.println("Postfix - "+ stb.toString());
    }

    void pushAndUpdateStack(Stack<Character> stack, StringBuilder stb, char ch) {
        //if opening bracket is encountered, then push it to the stack
        if (ch == '(') {
            stack.push(ch);
            return;
        }

        //if closing bracket is encountered, then pop the stack until opening bracket is encountered
        if (ch == ')') {
            while (!stack.isEmpty()) {
                char temp = stack.pop();
                if (temp == '(') {
                    break;
                } else {
                    stb.append(temp);
                }
            }
            return;
        }

        int currentCharPriority = getPriority(ch);
        while (true){
            if (stack.isEmpty()){
                stack.push(ch);
                return;
            }

            int lastItemPriority = getPriority(stack.peek());
            if (currentCharPriority > lastItemPriority){
                stack.push(ch);
                break;
            }else if (currentCharPriority == lastItemPriority || currentCharPriority < lastItemPriority){
                char x = stack.pop();
                stb.append(x);
            }
        }

    }

    int getPriority(char ch) {

        int priority = -1;
        switch (ch) {
            case '^':
                priority = 3;
                break;
            case '/':
                priority = 2;
                break;
            case '*':
                priority = 2;
                break;
            case '+':
                priority = 1;
                break;
            case '-':
                priority = 1;
                break;
            default:
                priority = -1;
        }

        return priority;
    }


    public static void main(String[] args) {
        StackG<Integer> stack = new StackG<>();
        System.out.println("Is balanced = " + stack.balancingSymobol("(()()()"));
        stack.infixToPostfixConversion("a+b*(c^d-e)^(f+g*h)-i");
    }
}
