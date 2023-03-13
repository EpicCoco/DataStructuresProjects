package cs2720;

import cs2720.SortedLinkedList;
import cs2720.ItemType;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.IndexOutOfBoundsException;
import java.lang.Integer;
import java.lang.NumberFormatException;

/**
 * LinkedListDriver is the driver class for SortedLinkedList.
 */
public class LinkedListDriver {

    /**
     * Main method. Drives the program by accepting command
     * line arguments, taking in files, and handling most
     * program output.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SortedLinkedList list = new SortedLinkedList();
        try {
            String fileName = args[0];
            File file = new File(fileName);
            Scanner readFile = new Scanner(file);
            while (readFile.hasNextInt()) {
                int add = readFile.nextInt();
                list.insertItem(new ItemType(add));
            } //while
            //Prints out all of the commands available to the user
            System.out.println("Commands:");
            System.out.println("(i) - Insert value");
            System.out.println("(d) - Delete value");
            System.out.println("(s) - Search value");
            System.out.println("(n) - Print next iterator value");
            System.out.println("(r) - Reset iterator");
            System.out.println("(a) - Delete alternate nodes");
            System.out.println("(m) - Merge lists");
            System.out.println("(t) - Find intersection");
            System.out.println("(p) - Print list");
            System.out.println("(l) - Print length");
            System.out.println("(q) - Quit program");

            System.out.print("Enter a command: ");
            String line = sc.nextLine();
            while (!line.equals("q")) { //quit command
                int num;
                SortedLinkedList tempList;
                switch (line) {
                case "i": //insert
                    num = commandInput("insert", sc);
                    System.out.println("Original list : " + list.toString());
                    if (list.searchItem(new ItemType(num)) != -1) {
                        System.out.println("Sorry. You cannot insert the duplicate item");
                    } else {
                        list.insertItem(new ItemType(num));
                    } //if
                    System.out.println("New List : " + list.toString());
                    break;
                case "d": //delete
                    num = commandInput("delete", sc);
                    System.out.println("Original list : " + list.toString());
                    list.deleteItem(new ItemType(num));
                    System.out.println("New List : " + list.toString());
                    break;
                case "s": //search
                    int itemAt = list.searchItem(new ItemType(commandInput("search", sc)));
                    System.out.println("Original list : " + list.toString());
                    if (list.getLength() == 0) {
                        System.out.println("The list is empty");
                    } else if (itemAt == -1) {
                        System.out.println("Item is not present in the list");
                    } else {
                        System.out.println("The item is present at index " + (itemAt + 1));
                    } //if
                    break;
                case "r": //reset iterator
                    list.resetList();
                    System.out.println("Iterator is reset");
                    break;
                case "a": //delete alternate nodes
                    System.out.println("Original list : " + list.toString());
                    list.deleteAlternateNodes();
                    System.out.println("Modified List : " + list.toString());
                    break;
                case "m": //merge lists
                    tempList = newList(sc);
                    System.out.println("The list 1: " + list.toString());
                    System.out.println("The list 2: " + tempList.toString());
                    System.out.println("Merged list: " + list.mergeList(tempList).toString());
                    break;
                case "t": //find intersection
                    tempList = newList(sc);
                    System.out.println("The list 1: " + list.toString());
                    System.out.println("The list 2: " + tempList.toString());
                    System.out.println("Intersection of lists: " +
                    list.intersection(tempList).toString());
                    break;
                case "n": //print next iteration value
                    if (list.getLength() == 0) {
                        System.out.println("List is empty");
                    } else {
                        System.out.println(list.getNextItem().getValue());
                    } //if
                    break;
                case "p": //print list
                    System.out.println("The list is: " + list.toString());
                    break;
                case "l": //print list length
                    System.out.println("The length of the list is " + list.getLength());
                    break;
                default: //invalid command
                    System.out.println("Invalid command, try again!");
                } //switch
                System.out.print("Enter a command: ");
                line = sc.nextLine();
            } //while
        } catch (IndexOutOfBoundsException e) {
            //in case the user didn't include a file
            System.err.println("Error: no file inputted.");
            System.out.println("Exiting...");
        } catch (FileNotFoundException e) {
            //in case the file is invalid
            System.err.println("Error: file not found.");
            System.out.println("Exiting...");
        } //try
        //final message, when exiting
        System.out.println("Exiting the program...");
    } //main

    /**
     * Prints the output for when doing commandInput on some
     * commands. Has recusion in case the user inputs an invalud
     * character (only allows integers).
     * @param: String operation (read out to the user)
     * @param: Scanner scanner for user input
     * @return: returns an integer for the user's input
     */
    private static int commandInput(String operation, Scanner sc) {
        System.out.print("Enter a number to " + operation + ": ");
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid input");
            return commandInput(operation, sc);
        } //try
    } //commandInput

    /**
     * Helper metehod meant to make it easier for creating a new
     * list, to be used by the merge and intersection functions.
     * Takes in user input and creates a SortedLinkedList from it.
     * @param: Scanner for scanning user input
     * @return: SortedLinkedList the generated list from user input
     */
    private static SortedLinkedList newList(Scanner sc) {
        System.out.print("Enter the length of the new list: ");
        int length = Integer.parseInt(sc.nextLine());
        System.out.print("Enter the numbers: ");
        String numbers = sc.nextLine();
        SortedLinkedList toReturn = new SortedLinkedList();
        //uses a scanner on the string to get the integers easily
        Scanner stringScanner = new Scanner(numbers);
        for (int i = 0; i < length; i++) {
            toReturn.insertItem(new ItemType(stringScanner.nextInt()));
        } //for
        return toReturn;
    } //newList

} //LinkedListDriver
