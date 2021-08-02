package main.miscs;

import java.util.HashMap;
import java.util.Map;

/**
 * way to find first non repeating character.
 * Example - if the input string is “GeeksforGeeks”, then output should be ‘f’ and
 * if input string is “GeeksQuiz”, then output should be ‘G’.
 */
public class FindFirstNonRepeatingCharacter {
    public static void main(String[] args) {
        solutionOne("GeeksForGeeks");
        solutionOne("GeeksForQuiz");

        solutionTwo("GeeksForGeeks");
        solutionTwo("GeeksForQuiz");
    }

    static void solutionOne(String input) {
        if (input == null || input.length() == 0) {
            System.out.println("Invalid input");
            return;
        }

        int[] count = new int[256];

        for (char x : input.toCharArray()) {
            count[x]++;
        }

        for (char x : input.toCharArray()) {
            if (count[x] == 1) {
                System.out.println("First occurance of non repetitve character - " + x);
                break;
            }
        }
    }

    static void solutionTwo(String input) {
        if (input == null || input.length() == 0) {
            System.out.println("Invalid input");
            return;
        }

        Map<Character, Count> map = new HashMap<>();

        int index = 0;
        for (char ch : input.toCharArray()) {
            if (map.containsKey(ch)) {
                map.get(ch).changeIndexAndCount(index); //increase the count and index
            } else {
                Count object = new Count(ch, index);
                map.put(ch, object);
            }
            index++;
        }

        Count temp = null;
        for (Map.Entry<Character, Count> entry : map.entrySet()) {
            if (entry.getValue().count != 1) {
                continue;
            }

            if (temp == null) {
                temp = entry.getValue();
                continue;
            }

            if (temp.lastIndex > entry.getValue().lastIndex) {
                temp = entry.getValue();
            }
        }

        if (temp == null) {
            System.out.println("Not found");
        } else {
            System.out.println("First occurance of non repetitve character - " + temp.data);
        }
    }
}

class Count {
    char data;
    int count;
    int lastIndex = -1;

    Count(char c, int index) {
        data = c;
        count = 1;
        this.lastIndex = index;
    }

    void changeIndexAndCount(int index) {
        count++; //increase the count
        this.lastIndex = index;
    }
}
