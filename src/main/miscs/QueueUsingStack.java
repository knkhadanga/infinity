package main.miscs;
import java.util.Arrays;
import java.util.Stack;
/**
 * Implementing queue using two stacks
 */
public class QueueUsingStack {
    Stack<String> stack1;
    Stack<String> stack2;

    QueueUsingStack (){
        stack1 = new Stack();
        stack2 = new Stack();
    }

    void enqueue (String data) {
        //while enquing, always enque in the fist stack
        stack1.push(data);
    }

    String dequeue (){
        /* always dequeue from stack 2
           1. check if the stack2 is empty, If stack2 is empty,
              then pop all elements from stack1 and push it to stack 2
              and then pop from stack2 and return.
           2. if stack2 is not empty, then pop it from stack2 and return
         */
        if (stack2.isEmpty()){
            //pop all the data from stack1 and push it to stack2
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }

        return stack2.pop();
    }

    boolean isEmpty (){
        if (stack1.isEmpty() && stack2.isEmpty()){
            return  true;
        }

        return false;
    }

    String peek (){
        if (stack1.isEmpty() && stack2.isEmpty()){
            return null;
        }

        if (!stack2.isEmpty()){
            return stack2.peek();
        } else {
            //pop all the data from stack1 and push it to stack2
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }

            return stack2.peek();
        }
    }

    public static void main (String[] args){
        QueueUsingStack queue = new QueueUsingStack();
        String[] input = {"one", "two", "three", "four", "five"};

        Arrays.stream(input).forEach(queue::enqueue);

        System.out.println(queue.dequeue() + " ");
        queue.enqueue("six");
        System.out.println(queue.dequeue() + " ");
        System.out.println("------");
        while(!queue.isEmpty()){
            System.out.print(queue.dequeue() + " ");
        }

    }
}
