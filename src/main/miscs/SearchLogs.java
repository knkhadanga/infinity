package main.miscs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a log file as an input where the log has information about the
 * different function name, time it was invoked and status of it (entry or exit),
 * parse the log file and print the function name and it's execution time in an
 * ascending order. Here is the format of log file:
 *
 * <p>
 * function1,1,entry
 * function1,4,exit
 * function2,1,entry
 * function2,2,entry
 * function2,13,exit
 * function2,10,exit
 * function1,18,entry
 * function1,25,exit
 * <p>
 * Output: [ function1 | 3 ] [ function1 | 7 ] [ function2 | 9 ] [ function2 | 11 ]
 * <p>
 * Note - Assume the logs are written by a single threaded application and the exit status of a function
 * corresponds to the latest entry of the function.
 */
public class SearchLogs {

    public static void main(String[] args) {
        SearchLogs test = new SearchLogs();
        test.parseData();
    }


    void parseData() {
        String[] data = readFile();
        Stack<Function> stack = new Stack<>();
        Stack<Function> tempStack = new Stack<>();
        List<Function> list = new ArrayList<>();

        for (String line : data) {
            String[] values = line.split(",");
            String functionName = values[0];
            int time = Integer.valueOf(values[1]);
            String status = values[2];

            //whenever entry status is found, then create a function object and push
            if (status.equalsIgnoreCase("entry")) {
                Function f = new Function();
                f.name = functionName;
                f.startTime = time;
                stack.push(f);
                continue;
            }

            //whenever exit status is found, then search for the function object with name
            //which is not completed and update it
            if (status.equalsIgnoreCase("exit")) {
                while (!stack.isEmpty()) {
                    Function temp = stack.pop();
                    if (temp.name.equalsIgnoreCase(functionName)
                            && !(temp.executionCompleted)) {
                        temp.setExecutionTime(time);
                        stack.push(temp);
                        while (!tempStack.isEmpty()) {
                            stack.push(tempStack.pop());
                        }
                        break;
                    } else {
                        tempStack.push(temp);
                    }
                }
            }
        }

        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }

        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }

        printList(list);
    }

    void printList(List<Function> list) {
        list.sort((o1, o2) -> (o1.executionTime - o2.executionTime));

        for (Function f : list) {
            System.out.println("[ " + f.name + " | " + f.executionTime + " ]");
        }
    }

    /**
     * This method is returning the logs in an array
     *
     * @return
     */
    String[] readFile() {

        String[] logs = {
                "function1,1,entry",
                "function2,1,entry",
                "function1,4,exit",
                "function2,2,entry",
                "function2,13,exit",
                "function2,10,exit",
                "function1,18,entry",
                "function1,25,exit"
        };

        return logs;
    }

    class Function {
        String name;
        int startTime;
        int endTime;
        int executionTime;
        boolean executionCompleted;

        public void setExecutionTime(int endTime) {
            this.endTime = endTime;
            executionTime = endTime - startTime;
            executionCompleted = true;
        }
    }
}
