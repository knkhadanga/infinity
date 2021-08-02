package main.miscs;

class QuickSort {

    public static void main(String[] args) {
        int[] input = {8, 4, 7, 3, 9, 10, 7, 5};
        QuickSort sort = new QuickSort();
        sort.quickSort(input, 0, input.length - 1);
        sort.display(input);
    }

    void display(int[] array) {
        for (int x : array) {
            System.out.print(x + " ");
        }

        System.out.println("");
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (arr == null || arr.length == 0)
            return;

        if (low >= high)
            return;

        // pick the pivot (middle element)
        int middle = low + (high - low) / 2;
        int pivot = arr[middle];

        // make left < pivot and right > pivot
        int i = low, j = high;
        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }

            while (arr[j] > pivot) {
                j--;
            }

            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }

        // recursively sort two sub parts
        if (low < j) //check if left
            quickSort(arr, low, j);

        if (high > i)
            quickSort(arr, i, high);
    }

}
