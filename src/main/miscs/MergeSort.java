package main.miscs;

public class MergeSort {

    public static void main(String[] args){
        MergeSort test = new MergeSort();
        int[] input = {100,3,2,1,-22,8,6,71};

        test.sort(input, 0, input.length - 1);
        test.display(input);
    }

    void sort (int[] array, int left, int right){
        if (left < right){
            int mid = (left + right) / 2;
            sort(array, left, mid);
            sort (array, mid+1, right);
            merge (array, left, mid, right);
        }
    }

    void merge (int[] array, int left, int mid, int right){
        int leftSize = mid - left +1 ;
        int rightSize = right - mid;

        int[] arrayLeft = new int[leftSize];
        int[] arrayRight = new int[rightSize];

        for (int i = 0; i < leftSize; i++){
            arrayLeft[i] = array[left + i];
        }

        for (int i = 0; i < rightSize; i++){
            arrayRight[i] = array[mid + 1 + i];
        }

        int li = 0;
        int ri = 0;
        int ai = left;

        while (li < leftSize && ri < rightSize){
            if (arrayLeft[li] <= arrayRight[ri]){
                array[ai++] = arrayLeft[li++];
            }else {
                array[ai++] = arrayRight[ri++];
            }
        }


        while (li < leftSize){
            array[ai++] = arrayLeft[li++];
        }

        while (ri < rightSize){
            array[ai++] = arrayRight[ri++];
        }
    }

    void display(int[] array){
        for (int x: array){
            System.out.print(x + " ");
        }

        System.out.println("");
    }

}
