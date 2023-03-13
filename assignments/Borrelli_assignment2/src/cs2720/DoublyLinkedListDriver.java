package cs2720;

import cs2720.DoublyLinkedList;
import cs2720.NodeType;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.IndexOutOfBoundsException;
import java.lang.Integer;
import java.lang.NumberFormatException;

/**
 * Driver class for using the @DoublyLInkedList
 * Contains the main method, uses command line arguments
 * and File input. Suppresses unchecked warnings since
 * the data types being used are known and program
 * still works as intended.
 */
@SuppressWarnings("unchecked")
public class DoublyLinkedListDriver {

    /**
     * Main method. Takes in file from user input, uses
     * it to create a DoublyLinkedList, and then runs
     * the user input to modify the list.
     * @param: args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //all in a try/catch block for File errors
        try {
            String fileName = args[0];
            File file = new File(fileName);
            Scanner readFile = new Scanner(file);
            System.out.print("Enter list type (i - int, d - double, s - string): ");
            String listType = sc.nextLine();
            DoublyLinkedList list;
            //switch statement for the data type of the list
            switch (listType) {
            case "d":
                list = new DoublyLinkedList<Double>();
                while (readFile.hasNextDouble()) {
                    list.insertItem(readFile.nextDouble());
                } //while
                break;
            case "s":
                list = new DoublyLinkedList<String>();
                while (readFile.hasNext()) {
                    list.insertItem(readFile.next());
                } //while
                break;
            default:
                list = new DoublyLinkedList<Integer>();
                while (readFile.hasNextInt()) {
                    list.insertItem(readFile.nextInt());;
                } //while
                break;
            } //switch
            //Prints out all of the commands available to the user
            System.out.println("Commands:");
            System.out.println("(i) - Insert value");
            System.out.println("(d) - Delete value");
            System.out.println("(p) - Print list");
            System.out.println("(l) - Length");
            System.out.println("(t) - Print reverse");
            System.out.println("(r) - Reverse list");
            System.out.println("(b) - Delete Subsection");
            System.out.println("(s) - Swap Alternate");
            System.out.println("(q) - Quit program");
            System.out.print("Enter a command: ");
            String line = sc.nextLine();
            //main program loop - runs until user quits
            while (!line.equals("q")) { //quit command
                switch (line) {
                case "i": //insert
                    System.out.print("The list is : ");
                    list.print();
                    if (listType.equals("s")) {
                        System.out.print("Enter a string to insert: ");
                        list.insertItem(sc.nextLine());
                    } else if (listType.equals("d")) {
                        list.insertItem(commandDoubleInput("insert", sc));
                    } else {
                        list.insertItem(commandIntInput("insert", sc));
                    } //if
                    System.out.print("The list is : ");
                    list.print();
                    System.out.print("The reverse list: ");
                    list.printReverse();
                    break;
                case "d": //delete
                    System.out.print("The list is : ");
                    list.print();
                    if (listType.equals("s")) {
                        System.out.print("Enter a string to delete: ");
                        list.deleteItem(sc.nextLine());
                    } else if (listType.equals("d")) {
                        list.deleteItem(commandDoubleInput("delete", sc));
                    } else {
                        list.deleteItem(commandIntInput("delete", sc));
                    } //if
                    System.out.print("The list is : ");
                    list.print();
                    System.out.print("The reverse list: ");
                    list.printReverse();
                    break;
                case "p": //print
                    System.out.print("The list is : ");
                    list.print();
                    break;
                case "l": //length
                    System.out.println("The length of the list is " + list.getLength());
                    break;
                case "t": //printreverse
                    System.out.print("The reverse list: ");
                    list.printReverse();
                    break;
                case "r": //reverse
                    System.out.print("The original list: ");
                    list.print();
                    list.reverseList();
                    System.out.print("The reversed list: ");
                    list.print();
                    break;
                case "b": //deletesub
                    doDeleteSub(list, listType);
                    break;
                case "s": //swapalt
                    System.out.print("The original list: ");
                    list.print();
                    list.swapAlternate();
                    System.out.print("The modified list: ");
                    list.print();
                    System.out.print("The reversed list: ");
                    list.printReverse();
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
        } catch (FileNotFoundException e) {
            //in case the file is invalid
            System.err.println("Error: file not found.");
        } //try
        //final message, when exiting
        System.out.println("Exiting the program...");
    } //main

    /**
     * Prints the output for when doing commandInput on some
     * commands. Has recusion in case the user inputs an invalid
     * character (only allows integers).
     * @param: String operation (read out to the user)
     * @param: Scanner scanner for user input
     * @return: returns an integer for the user's input
     */
    private static int commandIntInput(String operation, Scanner sc) {
        System.out.print("Enter a number to " + operation + ": ");
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid input");
            return commandIntInput(operation, sc);
        } //try
    } //commandIntInput

    /**
     * Prints the output for when doing commandInput on some
     * commands. Has recusion in case the user inputs an invalid
     * character (only allows doubles).
     * @param: String operation (read out to the user)
     * @param: Scanner scanner for user input
     * @return: returns a double for the user's input
     */
    private static double commandDoubleInput(String operation, Scanner sc) {
        System.out.print("Enter a number to " + operation + ": ");
        try {
            return Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid input");
            return commandIntInput(operation, sc);
        } //try
    } //commandIntInput

    /**
     * Helper method for deleting a subsection of the DoublyLinkedList.
     * Is split up between data types.
     * @param: DoublyLinkedList list to modify
     * @param: String listType the type of list that is being used
     */
    private static void doDeleteSub(DoublyLinkedList list, String listType) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter lower bound: ");
        if (listType.equals("s")) {
            String ub = new String(sc.next());
            System.out.print("Enter upper bound: ");
            String lb = new String(sc.next());
            System.out.print("The original list: ");
            list.print();
            list.deleteSubsection(lb, ub);
            System.out.print("The modified list: ");
            list.print();
        } else if (listType.equals("d")) {
            double ub = sc.nextDouble();
            System.out.print("Enter upper bound: ");
            double lb = sc.nextDouble();
            System.out.print("The original list: ");
            list.print();
            list.deleteSubsection(lb, ub);
            System.out.print("The modified list: ");
            list.print();
        } else {
            int ub = sc.nextInt();
            System.out.print("Enter upper bound: ");
            int lb = sc.nextInt();
            System.out.print("The original list: ");
            list.print();
            list.deleteSubsection(lb, ub);
            System.out.print("The modified list: ");
            list.print();
        } //if
    } //doDeleteSub
} //LinkedListDriver
