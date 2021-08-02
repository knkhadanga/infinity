package main.miscs;

/**
 * Implementing queue using two stacks
 */
public class QueueUsingStack {

    Stack stack1;
    Stack stack2;

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
                stack2.push(stack1.pop().strData);
            }
        }

        Node x = stack2.pop();

        if (x == null){
            return  null;
        }

        return x.strData;
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
            return stack2.peek().strData;
        } else {
            //pop all the data from stack1 and push it to stack2
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop().strData);
            }

            return stack2.peek().strData;
        }
    }

    public static void main (String[] args){
        QueueUsingStack queue = new QueueUsingStack();
        String[] input = {"one", "two", "three", "four", "five"};

        for (String x: input){
            queue.enqueue(x);
        }

        System.out.print(queue.dequeue() + " ");
        queue.enqueue("six");
        System.out.print(queue.dequeue() + " ");
        while(!queue.isEmpty()){
            System.out.print(queue.dequeue() + " ");
        }

    }
}
