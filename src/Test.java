import java.util.ArrayList;
import java.util.List;

public class Test {

    /*
    Problem: Find the lowest cost of buying items.                                   
    Description:    There are two supermarkets A and B. There are 2N items we want to buy.
    P = [[10, 20], [20, 34], [56, 12], [25,19]]
     P[i] is the price of the i-th item at each market.                                                                                                                
    However, the shopping cart at each market can only have N items.                                                                                                  
    How do we buy all the 2N items from each market so that the total cost is lowest? 
     */

    //P = [[1,10],[2,20],[3,30],[4,40]]


    static int lowest_cost(int[][] cost){
        int result = 0;

        List<Integer> first_store_lowest_price = new ArrayList<>();
        List<Integer> second_store_lowest_price = new ArrayList<>();

        for (int[] item: cost){
            if (item[0] > item[1]){
                second_store_lowest_price.add(item[1]);
            }else   {
                first_store_lowest_price.add(item[0]);
            }
        }

       // int first_sum = A[i] + B[j]
        //int second_sum = A[j] + B[i]
        //int diff = (A[i] + B[j]) - (A[j] + B[i]) => (A[i] - B[i]) + (B[j] - A[j]) =>
        //(A[i] - B[i]) - (A[j] - B[j])

        //int diff =

        return result;
    }


    /*
    Rotate an array Input is an array and a number
    k. 1,2,3,4,5 
    K = 2 
    4,5,1,2,3 

     */

    public static void main(String[] args) {
        int [] array = {1,2,3,4,5};
        right_rotate(array, 2);
        for (int x : array) {
            System.out.print(x + " ");
        }
    }

    static int [] right_rotate(int[] array, int k){

        int length = array.length;
        if (k % length == 0) {
            return array;
        }

        if (k > length){
            k = k % length;
        }

        // 1 2 3 4 5 -> 4 5 1 2 3
        //
        // 5 4 3 2 1 -> 4 5 | 1 2 3

        //reverse the array first
        int i = 0;
        int j = length - 1;
        revese_array(array, i, j);

        //reverse from 0 to k-1
        i = 0;
        j = k - 1;
        revese_array(array, i, j);


        //reverse from k to last
        i = k;
        j = length - 1;
        revese_array(array, i, j);

        //(n/2) + (k/2) + (n-k)/2 = (n + n + k - k )/2
        return array;
    }

    static void revese_array(int[] array, int i, int j){
        while (i < j){
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }
    }
}
