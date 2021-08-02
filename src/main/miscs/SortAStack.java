package main.miscs;

import java.util.Stack;

/**
 * Sort a stack. A stack can be sorted using another temp stack.
 * https://www.youtube.com/watch?v=vFOY8rd_Bcw
 */
public class SortAStack {

    //Elements in the stack to be sorted
    Stack<Integer> sourceStack = new Stack<>();

    //A temporary stack
    Stack<Integer> tempStack = new Stack<>();

    void sortTheStack() {
        //pop elements from source stack until it is empty.
        while (!sourceStack.isEmpty()) {

            //pop the item on top of the source stack
            int itemToBePushed = sourceStack.pop();

            //check if the tempStack is empty. If not, then compare value on top
            //of tempStack. Pop items from the tempStack until the tempStack have
            //item on top which is less then itemToBePushed.
            //Push the value popped from temp stack to the sourceStack
            while (!tempStack.isEmpty()) {
                int itemOnTop = tempStack.peek();

                if (itemOnTop <= itemToBePushed) { //item on top is less than itemToBePushed
                    break;
                } else {
                    //pop item from temp stack and store them in source stack
                    int poppedItem = tempStack.pop();
                    sourceStack.push(poppedItem);
                }
            }

            //finally push the item to temp stack
            //at this point the tempStack would either empty or
            //would have item on top which is less than itemToBePushed
            tempStack.push(itemToBePushed);
        }

        //change the reference
        sourceStack = tempStack;
    }

    void pushItemToStack(int x) {
        sourceStack.push(x);
    }

    void display() {
        while (!sourceStack.isEmpty()) {
            System.out.print(sourceStack.pop() + " ");
        }
    }

    void sortStackR(){
        sortStackRecursive(sourceStack);
    }

    void sortStackRecursive(Stack<Integer> stack){

        if(!stack.isEmpty()){
            int item = stack.pop();
            sortStackRecursive(stack);
            sortUtil(stack, item);
        }

    }

    void sortUtil (Stack<Integer> stack, int item){
        if (stack.isEmpty() || stack.peek() >= item){
            stack.push(item);
            return;
        }

        int temp = stack.pop();
        sortUtil(stack, item);

        stack.push(temp);
    }


    public static void main(String[] args) {
        int[] numbers = {29, 772, 1, 2, 7, 22, 33, 11, 5, 2};

        SortAStack test = new SortAStack();
        for (int x : numbers) {
            test.pushItemToStack(x);
        }

       // test.sortTheStack();

        test.sortStackR();
        test.display();
    }


}
