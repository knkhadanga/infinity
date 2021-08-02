package main.miscs;

import java.util.Arrays;

public class MaxHeapNew {

    int HEAP_SIZE = 16;
    int[] array;
    int lastIndex = 1;

    MaxHeapNew() {
        array = new int[HEAP_SIZE];
    }

    void insert(int data) {
        if (lastIndex == 1) {
            array[lastIndex] = data;
            lastIndex++;
            return;
        }

        //Double the size if it is exceeding the current size
        if (lastIndex == HEAP_SIZE) {
            HEAP_SIZE += 16;
            Arrays.copyOf(array, HEAP_SIZE);
        }

        array[lastIndex] = data;

        percolateData();

        lastIndex++;
    }

    void percolateData() {
        int childIndex = lastIndex;
        int parentIndex = Math.floorDiv(childIndex, 2);

        while (true) {
            if ((array[parentIndex] > array[childIndex]) || (childIndex == 1)) {
                break;
            }

            int temp = array[parentIndex];
            array[parentIndex] = array[childIndex];
            array[childIndex] = temp;

            childIndex = parentIndex;
            parentIndex = Math.floorDiv(childIndex, 2);
        }
    }

    int delete() {
        if (lastIndex == 1) {
            int temp = array[lastIndex];
            array[lastIndex] = 0;
            return temp;
        }

        int returnData = array[1];
        int dataToBeReplaced = array[lastIndex - 1];
        array[1] = dataToBeReplaced;


        percolateDown();
        lastIndex--;
        return returnData;
    }

    void percolateDown() {
        int parent = 1;

        while (true) {
            int leftChildIndex = parent * 2;
            int rightChildIndex = parent * 2 + 1;

            if ((leftChildIndex < lastIndex) && (rightChildIndex < lastIndex)) {
                if (array[leftChildIndex] > array[rightChildIndex]) {
                    //swap with the left
                    if (array[leftChildIndex] > array[parent]) {
                        int temp = array[parent];
                        array[parent] = array[leftChildIndex];
                        array[leftChildIndex] = temp;
                        parent = leftChildIndex;
                    } else {
                        break;
                    }
                } else {
                    //swap with right child
                    if (array[rightChildIndex] > array[parent]) {
                        int temp = array[parent];
                        array[parent] = array[rightChildIndex];
                        array[rightChildIndex] = temp;
                        parent = rightChildIndex;
                    } else {
                        break;
                    }
                }
            } else if (leftChildIndex < lastIndex) { // it has only left child
                //swap with the left
                if (array[leftChildIndex] > array[parent]) {
                    int temp = array[parent];
                    array[parent] = array[leftChildIndex];
                    array[leftChildIndex] = temp;
                    parent = leftChildIndex;
                } else {
                    break;
                }
            } else if (rightChildIndex < lastIndex) { //it has only right child
                //swap with right child
                if (array[rightChildIndex] > array[parent]) {
                    int temp = array[parent];
                    array[parent] = array[rightChildIndex];
                    array[rightChildIndex] = temp;
                    parent = rightChildIndex;
                } else {
                    break;
                }
            } else {
                break;
            }
        }

    }

    void print() {
        for (int x = 1; x < lastIndex; x++) {
            System.out.print(array[x] + " ");
        }
    }

    public static void main(String[] args) {
        MaxHeapNew test = new MaxHeapNew();
        int[] data = {10, 30, 20, 40, 50, 60};
        for (int x : data) {
            test.insert(x);
        }

        test.print();
        System.out.println("\nDeleted - " + test.delete());
        test.print();
        System.out.println("\nDeleted - " + test.delete());
        test.print();
        System.out.println("\nDeleted - " + test.delete());
        test.print();
    }
}
