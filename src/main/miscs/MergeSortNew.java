package main.miscs;

public class MergeSortNew {

    void mergeSort (int[] array, int left, int right){
        if (left < right){
            int middle = (left + right) / 2;
            mergeSort(array, left, middle);
            mergeSort(array, middle+1, right);
            merge(array, left, middle, right);
        }
    }

    void merge(int[] array, int left, int middle, int right){
        int leftArrayLength = middle - left + 1;
        int rightArrayLength = (right - middle);

        int[] leftArray = new int[leftArrayLength];
        int[] rightArray = new int[rightArrayLength];

        for (int i = 0; i < leftArrayLength; i++){
            leftArray[i] = array[left + i];
        }

        for (int i = 0; i < rightArrayLength; i++){
            rightArray[i] = array[middle + i +1];
        }

        int li = 0;
        int ri = 0;
        int ai = left;

        while (li < leftArrayLength && ri < rightArrayLength){
            if (leftArray[li] <= rightArray[ri]){
                array[ai++] = leftArray[li++];
            }else {
                array[ai++] = rightArray[ri++];
            }
        }

        while (li < leftArrayLength){
            array[ai++] = leftArray[li++];
        }

        while (ri < rightArrayLength){
                array[ai++] = rightArray[ri++];
        }
    }
}
