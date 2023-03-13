package cs2720;

import cs2720.Sort;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Integer;

/**
 * Class to run the sorting methods in
 * Sort.java.
 */
public class SortDriver2 {

    /**
     * Main method. Takes in args (a file name)
     * and generates an array of ints to be sorted.
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        //all in a try/catch block for File errors
        try {
            System.out.print("How many elements in the array? ");
            int arrSize = Integer.parseInt(sc.nextLine());
            int numbers[] = new int[arrSize];
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = i;
            } //for
            for (int i = 0; i < numbers.length; i++) {
                int rand = (int)(Math.random() * numbers.length);
                int temp = numbers[i];
                numbers[i] = numbers[rand];
                numbers[rand] = temp;
            } //for

            Sort sort = new Sort(numbers);
            //sort.printList();

            //prints out sort types available to the user
            System.out.print("selection-sort (s)  ");
            System.out.print("merge-sort (m)  ");
            System.out.print("heap-sort (h)  ");
            System.out.print("quick-sort-fp (q)  ");
            System.out.print("quick-sort-rp (r)  ");
            System.out.println();
            System.out.print("Enter the algorithm: ");

            String sortType = sc.nextLine();

            switch (sortType) {

            case "s": //good on comparisons + sort
                sort.selectionSort();
                sort.printList();
                System.out.println("#Selection-sort comparisons: " + sort.comparisons);
                break;

            case "m": //good on comparisons + sort
                sort.mergeSort(0, numbers.length - 1);
                sort.printList();
                System.out.println("#Merge-sort comparisons: " + sort.comparisons);
                break;

            case "h": //good on comparisons + sort
                sort.heapSort();
                sort.printList();
                System.out.println("#Heap-sort comparisons: " + sort.comparisons);
                break;

            case "q": //good on sort
                sort.quickSort(0, numbers.length - 1);
                sort.printList();
                System.out.println("#Quick-sort-fp comparisons: " + sort.comparisons);
                break;

            case "r": //good on sort
                sort.RAND = true;
                sort.quickSort(0, numbers.length - 1);
                sort.printList();
                System.out.println("#Quick-sort-rp comparisons: " + sort.comparisons);
                break;

            default:
                return;
            } //switch


        } catch (IndexOutOfBoundsException e) {
            //in case the user didn't include a file
            System.err.println("Error: no file inputted.");
        } //try
    } //main

} //SortDriver2
