package main.miscs;

import java.util.Arrays;
import static java.lang.System.out;

public class MaxHeap {

    private int HEAP_SIZE = 16;
    int[] array;
    int indexOfLastElement;

    MaxHeap() {
        array = new int[HEAP_SIZE];
        indexOfLastElement = 1;
    }

    void insert(int data) {
        //double the size once the array reaches the max size
        if (indexOfLastElement == HEAP_SIZE) {
            HEAP_SIZE *= 2;
            array = Arrays.copyOf(array, HEAP_SIZE);
        }

        array[indexOfLastElement] = data;

        rebuildHeap();

        indexOfLastElement++;
    }

    void rebuildHeap() {

        /*
        if (indexOfLastElement == 1) {
            return;
        }

        int currentElement = indexOfLastElement;
        int parent = Math.floorDiv(indexOfLastElement, 2);

        while (array[currentElement] > array[parent]) {
            int temp = array[currentElement];
            array[currentElement] = array[parent];
            array[parent] = temp;

            if (parent == 1) {
                break;
            } else {
                currentElement = parent;
                parent = Math.floorDiv(currentElement, 2);
            }
        }
         */

        int child = indexOfLastElement;
        int parent = child / 2;
        while (parent != 0){
            if (array[parent] > array[child]){
                return;
            }

            int temp = array[parent];
            array[parent] = array[child];
            array[child] = temp;

            child = parent;
            parent = parent/2;
        }
    }

    int delete() {
        if (indexOfLastElement == 1) {
            return -1;
        }

        int lastElement = array[indexOfLastElement - 1];
        int itemToBeReturned = array[1];

        //swap with last element
        array[1] = lastElement;
        indexOfLastElement--;

        int parentIndex = 1;
        while (true) {
            int leftChildIndex = 2 * parentIndex;
            int rightChildIndex = 2 * parentIndex + 1;

            int element = array[parentIndex];

            if ((leftChildIndex < indexOfLastElement - 1) && (rightChildIndex < indexOfLastElement - 1)) {


                if ((array[leftChildIndex] > array[rightChildIndex]) && (array[leftChildIndex] > array[parentIndex])){ //swap with leftChild
                    int temp = array[parentIndex];
                    array[parentIndex] = array[leftChildIndex];
                    array[leftChildIndex] = temp;
                    parentIndex = leftChildIndex;
                    continue;
                } else if ((array[leftChildIndex] < array[rightChildIndex]) && (array[rightChildIndex] > array[parentIndex])) { //swap with right child
                    int temp = array[parentIndex];
                    array[parentIndex] = array[rightChildIndex];
                    array[rightChildIndex] = temp;
                    parentIndex = rightChildIndex;
                    continue;
                } else {
                    break;
                }
            } else if ((leftChildIndex < indexOfLastElement - 1) && (rightChildIndex > indexOfLastElement - 1)){
                    if (array[leftChildIndex] > array[parentIndex]){
                        int temp = array[parentIndex];
                        array[parentIndex] = array[leftChildIndex];
                        array[leftChildIndex] = temp;
                        parentIndex = leftChildIndex;
                        continue;
                    }else{
                        break;
                    }
            } else if ((rightChildIndex < indexOfLastElement - 1) && (leftChildIndex > indexOfLastElement - 1)){
                if (array[rightChildIndex] > array[parentIndex]){
                    int temp = array[parentIndex];
                    array[parentIndex] = array[rightChildIndex];
                    array[rightChildIndex] = temp;
                    parentIndex = rightChildIndex;
                    continue;
                }else {
                    break;
                }
            } else {
                break;
            }

        }

        return itemToBeReturned;
    }

    void print (){
        for (int i = 1; i < indexOfLastElement; i++){
            out.print(" "+array[i]);
        }
    }

    public static void main(String[] args){
        MaxHeap heap = new MaxHeap();
        for (int i = 10; i < 80; i+=10){
            heap.insert(i);
        }

        heap.print();
    }
}
