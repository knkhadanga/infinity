package main.miscs;

public class Stack {

    public static void main(String[] args) {

        Stack stack = new Stack();
        /*
        String expression = "a+b*(c^d-e)^(f+g*h)-i";
        System.out.println("Input - " + expression);

        System.out.println("Post fix expression - " + stack.infixToPostfix(expression));

        String perfixExpression = stack.infixToPrefix("a+b*(c^d-e)^(f+g*h)-i");
        System.out.println("PrefixExpression - " + perfixExpression);

        String infix = stack.prefixToInfixConversion(perfixExpression);
        System.out.println("Infix expression - " + infix);
        perfixExpression = stack.infixToPrefix(infix);
        System.out.println("PrefixExpression - " + perfixExpression);
        stack.currentMaximumInStack();
        */
        java.util.Stack<Integer> tempStack = new java.util.Stack<>();
        tempStack.push(12); tempStack.push(13);tempStack.push(1);tempStack.push(10);tempStack.push(18);
        //stack.sortStack(tempStack);
        stack.sortStackRecursive(tempStack);
        while (!tempStack.isEmpty()){
            System.out.print(tempStack.pop() + " ");
        }
    }


    Node head;

    void push(char data) {
        if (head == null) {
            head = new Node();
            head.charData = data;
            return;
        }

        Node temp = new Node();
        temp.charData = data;
        temp.next = head;
        head = temp;
    }

    void push(String data) {
        if (head == null) {
            head = new Node();
            head.strData = data;
            return;
        }

        Node temp = new Node();
        temp.strData = data;
        temp.next = head;
        head = temp;
    }

    Node pop() {
        if (head == null) {
            return null;
        }

        Node temp = head;
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

    Node peek() {
        Node temp = head;

        return temp;
    }


    boolean balancingSymbol(String input) {
        if (input == null || input.isEmpty() || input.length() == 0) {
            return false;
        }

        Stack stack = new Stack();
        char[] array = input.toCharArray();
        for (char c : array) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
                continue;
            }

            if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) {
                    return false;
                }

                char x = stack.pop().charData;
                switch (c) {
                    case ')':
                        if (x != '(') {
                            return false;
                        }
                        break;
                    case '}':
                        if (x != '{') {
                            return false;
                        }
                        break;
                    case ']':
                        if (x != '[') {
                            return false;
                        }
                        break;
                }
            }
        }

        if (!stack.isEmpty()){
            return false;
        }
        return true;
    }

    int getPriority(char c) {

        int priority = -1;
        switch (c) {
            case '+':
                priority = 1;
                break;
            case '-':
                priority = 1;
                break;
            case '*':
                priority = 2;
                break;
            case '/':
                priority = 2;
                break;
            case '^':
                priority = 3;
                break;
        }

        return priority;
    }

    /**
     * Method to convert infix statement to prefix statement
     *
     * @param input
     * @return
     */
    String infixToPrefix(String input) {
        if (input == null || input.isEmpty() || input.length() == 0) {
            return "";
        }

        //step 1 - Reverse the input string
        StringBuilder stb = new StringBuilder(input);
        stb = stb.reverse();

        //step 2 - Replace "(" with ")" and ")" with "(" - Swap the brackets
        char[] array = stb.toString().toCharArray();
        int i = 0;
        for (char x : array) {
            if (x == '(') {
                array[i] = ')';

            } else if (x == ')') {
                array[i] = '(';

            } else if (x == '{') {
                array[i] = '}';

            } else if (x == '}') {
                array[i] = '{';

            } else if (x == '[') {
                array[i] = ']';

            } else if (x == ']') {
                array[i] = '[';

            }

            i++;
        }

        input = new String(array);

        //step 3 - Get the post fix expression of the above string
        String output = infixToPostfix(input);

        stb.setLength(0);

        //step 4 - reverse the post fix string
        stb.append(output);
        String x = stb.reverse().toString();
        return x;
    }


    /**
     * Method to convert infix statement to postfix
     *
     * @param input
     * @return
     */
    String infixToPostfix(String input) {
        if (input == null || input.isEmpty() || input.length() == 0) {
            return "";
        }

        if (!balancingSymbol(input)) {
            return "Unbalanced input string - " + input;
        }

        StringBuilder output = new StringBuilder();
        Stack stack = new Stack();

        char[] array = input.toCharArray();

        for (char ch : array) {

            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
                continue;
            }

            if (ch == '^' || ch == '*' || ch == '/' || ch == '+' || ch == '-' ||
                    ch == ')' || ch == '}' || ch == ']') {

                updateStack(stack, ch, output);
            } else {
                output.append(ch);
            }
        }

        while (!stack.isEmpty()) {
            output.append(stack.pop().charData);
        }

        return output.toString();
    }

    void updateStack(Stack stack, char ch, StringBuilder output) {

        int p1 = getPriority(ch);
        if (ch == '^' || ch == '/' || ch == '*' || ch == '+' || ch == '-') {
            while (true) {

                if (stack.isEmpty()) { // push if stack is empty
                    stack.push(ch);
                    return;
                }

                Node temp = stack.peek();
                int p2 = getPriority(temp.charData);

                if (p1 > p2) { //if input char has more priority than the top element in head, then push it
                    stack.push(ch);
                    return;
                } else if ((p1 == p2) || (p1 < p2)) {
                    //if input char has equal priority as the top element in head, then pop until either
                    //stack is empty or stack has less priority operator on head
                    Node x = stack.pop();
                    output.append(x.charData);
                    continue;
                }
            }
        }

        //pop until the opening bracket is found and append the popped characters
        if (ch == ')' || ch == '}' || ch == ']') {

            while (true) {
                if (ch == ')') {
                    Node temp = stack.pop();
                    if (temp.charData == '(') {
                        break;
                    }

                    output.append(temp.charData);
                    continue;
                } else if (ch == '}') {
                    Node temp = stack.pop();
                    if (temp.charData == '{') {
                        break;
                    }

                    output.append(temp.charData);
                    continue;
                } else if (ch == ']') {
                    Node temp = stack.pop();
                    if (temp.charData == '[') {
                        break;
                    }

                    output.append(temp.charData);
                    continue;
                }

            }

        }

    }

    String prefixToInfixConversion(String input) {
        if (input == null || input.isEmpty() || input.length() == 0) {
            return "";
        }

        int length = input.length();
        Stack stack = new Stack();

        //parse right to left of the expression
        for (int i = length - 1; i >= 0; i--) {
            char ch = input.charAt(i);
            if (ch == '^' || ch == '/' || ch == '*' || ch == '+' || ch == '-') {
                Node one = stack.pop();
                Node two = stack.pop();

                String temp = "(" + one.strData + ch + two.strData + ")";
                stack.push(temp);

            } else {
                stack.push(Character.toString(ch));
            }
        }

        String output = stack.pop().strData;
        return output;
    }

    void currentMaximumInStack() {
        java.util.Stack<Integer> stack1 = new java.util.Stack<>();
        java.util.Stack<Integer> stack2 = new java.util.Stack<>();

        //insert data to the stack1 and keep current maximum in stack2
        int[] data = {2, 33, 11, 22, 44, 21, 55};
        for (int x : data) {
            stack1.push(x);
            if (stack2.isEmpty()) {
                stack2.push(x);
                continue;
            } else {
                int currentMax = stack2.peek();
                if (currentMax > x) {
                    stack2.push(currentMax);
                } else {
                    stack2.push(x);
                }
            }
        }

        while (!stack1.isEmpty()){
            System.out.print(stack1.pop() + " ");
        }
        System.out.println("");

        while (!stack2.isEmpty()){
            System.out.print(stack2.pop() + " ");
        }
        System.out.println("");
    }

    /**
     * Non recursive way of sorting stack.
     * Using temp stack.
     *
     * @param inputStack
     */
    void sortStack(java.util.Stack<Integer> inputStack){
        if (inputStack.isEmpty()){
            System.out.println("Stack is empty.");
            return;
        }

        java.util.Stack<Integer> tempStack = new java.util.Stack<>();

        while (!inputStack.isEmpty()){
            int data = inputStack.pop();
            if (tempStack.isEmpty()){
                tempStack.push(data);
                continue;
            }

            int currentTop = tempStack.peek();
            if (currentTop < data){
                tempStack.push(data);
                continue;
            }else {
                while (!tempStack.isEmpty()){
                    if (tempStack.peek() > data){
                        int temp = tempStack.pop();
                        inputStack.push(temp);
                    }else{
                        break;
                    }
                }

                tempStack.push(data);
            }
        }

        System.out.println("Sorted stack - ");
        while (!tempStack.isEmpty()){
            System.out.print(tempStack.pop() + " ");
        }
    }

    /**
     * recursive way of sorting stack
     * @param stack
     */
    void sortStackRecursive (java.util.Stack<Integer> stack){

        if (stack.size() > 0){
            //pop items recursively and store them in function stack
            int item = stack.pop();

            sortStackRecursive(stack);

            //sort them in util method
            sortUtil(stack, item);
        }

        //return stack;
    }

    void sortUtil(java.util.Stack<Integer> stack, int item){
        //if stack is empty or the top item in stack is less than current item
        //then push the item to stack and return
        if (stack.isEmpty() || stack.peek() > item){
            stack.push(item);
            return;
        }

        //pop top item from stack which is greater than item to be placed
        int temp = stack.pop();

        //and call the util method recursively until the item can be placed at the right place in stack
        sortUtil(stack, item);

        //push back the popped item to the stack
        stack.push(temp);
    }
}
