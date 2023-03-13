package cs2720;

/**
 * This class holds four different sorting
 * algorithms (selection, merge, heap, and
 * quick sort). Quick sort is able to do
 * either random or first item pivot.
 *
 * Uses code not written by me.
 * Credit to other methods is in both the
 * Readme.txt and in the method comments.
 */
public class Sort {

    int values[];
    public int comparisons; //count total comparisons
    public boolean RAND; //for quicksort

    /**
     * Construct a sort object, taking
     * in an int array to be sorted.
     * @param: int values array of data to
     * be sorted.
     */
    public Sort(int values[]) {
        this.values = values;
        comparisons = 0;
        RAND = false;
    } //Sort

    /**
     * Private helper method that Swaps
     * two items in the values array.
     * Serves as a useful tool for
     * sorting algorithms.
     * @param: int one index of first item
     * @param: int two index of second item
     */
    private void swap(int one, int two) {
        int temp = values[one];
        values[one] = values[two];
        values[two] = temp;
    } //swap

    /**
     * Title: selectionSort
     * Author: Sachin Meena
     * meena@uga.edu
     * This method was taken from the
     * lecture slides presented in class.
     * CH10_Sorting
     */
    public void selectionSort() {
        int endIndex = values.length - 1;
        for (int current = 0; current < endIndex; current++) {
            swap(current, minIndex(current, endIndex));
        } //for
    } //selectionSort

    /**
     * Not my code.
     *
     * Title: minIndex
     * Author: Sachin Meena
     * meena@uga.edu
     * This method was taken from the
     * lecture slides presented in class.
     * CH10_Sorting
     */
    public int minIndex(int start, int end) {
        int indexOfMin = start;
        for (int index = start + 1; index <= end; index++) {
            if (values[index] < values[indexOfMin]) {
                indexOfMin = index;
            } //if
            comparisons++;
        } //for
        return indexOfMin;
    } //minIndex

    /**
     * Not my code.
     *
     * Title: merge
     * Author: Rajat Mishra
     * This code was taken from:
     * https://www.geeksforgeeks.org/merge-sort/
     */
    void merge(int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int L[] = new int[n1];
        int R[] = new int[n2];
        for (int i = 0; i < n1; ++i) {
            L[i] = values[l + i];
        } //for
        for (int j = 0; j < n2; ++j) {
            R[j] = values[m + 1 + j];
        } //for
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            comparisons++;
            if (L[i] <= R[j]) {
                values[k] = L[i];
                i++;
            } else {
                values[k] = R[j];
                j++;
            } //if
            k++;
        } //while
        while (i < n1) {
            values[k] = L[i];
            i++;
            k++;
        } //while
        while (j < n2) {
            values[k] = R[j];
            j++;
            k++;
        } //while
    } //merge

    /**
     * Not my code.
     *
     * Title: mergeSort
     * Author: Rajat Mishra
     * This code was taken from:
     * https://www.geeksforgeeks.org/merge-sort/
     */
    public void mergeSort(int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(l, m);
            mergeSort(m + 1, r);
            merge(l, m, r);
        } //if
    } //mergeSort

    //heap sort

    /**
     * Not my code.
     *
     * Title: heapSort
     * Author: Unknown
     * This code was taken from:
     * https://www.geeksforgeeks.org/java-program-for-heap-sort/
     */
    public void heapSort() {
        int n = values.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(n, i);
        } //for
        for (int i=n-1; i>=0; i--) {
            swap(0, i);
            heapify(i, 0);
        } //for
    } //heapSort

    /**
     * Not my code.
     *
     * Title: heapify
     * Author: Unknown
     * This code was taken from:
     * https://www.geeksforgeeks.org/java-program-for-heap-sort/
     */
    void heapify(int n, int i) {
        int largest = i;
        int l = 2*i + 1;
        int r = 2*i + 2;
        comparisons++;
        if (l < n && values[l] > values[largest]) {
            largest = l;
        } //if
        comparisons++;
        if (r < n && values[r] > values[largest]) {
            largest = r;
        } //if
        // If largest is not root
        if (largest != i) {
            swap(i, largest);
            heapify(n, largest);
        } //if
    } //heapify

    /**
     * Not my code.
     *
     * Title: quickSort
     * Author: Ayush Choudhary
     * This code was taken from:
     * https://www.geeksforgeeks.org/quick-sort/
     */
    public void quickSort(int first, int last) {
        if (first < last) {
            int splitPoint = partition(first, last);
            quickSort(first, splitPoint - 1);
            quickSort(splitPoint + 1, last);
        } //if
    } //quickSort

    /**
     * Not my code.
     *
     * Title: partition
     * Author: Ayush Choudhary
     * This code was taken from:
     * https://www.geeksforgeeks.org/quick-sort/
     */
    public int partition(int first, int last) {
        int pivot;
        if (RAND) {
            int rand = first + (int)(Math.random() * (last - first));
            swap(rand, last);
            pivot = values[last];
        } else {
            pivot = values[first];
            swap(last, first);
        } //if
        int i = (first - 1);
        for (int j = first; j<= last - 1; j++) {
            comparisons++;
            if (values[j] < pivot) {
                i++;
                swap(i, j);
            } //if
        } //for
        swap(i + 1, last);
        return (i + 1);
    } //split

    /**
     * A simple method to print the items of
     * the list in the order they appear.
     */
    public void printList() {
        for(int i = 0; i < values.length; i++) {
            System.out.print(values[i] + " ");
        } //for
        System.out.println();
    } //printList

} //Sort
