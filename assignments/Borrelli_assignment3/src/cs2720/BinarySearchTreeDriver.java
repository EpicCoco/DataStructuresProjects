package cs2720;

import cs2720.BinarySearchTree;
import cs2720.NodeType;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.IndexOutOfBoundsException;
import java.lang.Integer;
import java.lang.NumberFormatException;

/**
 * Driver class for using the @BinarySearchTree
 * Contains the main method, uses command line arguments
 * and File input. Suppresses unchecked warnings since
 * the data types being used are known and program
 * still works as intended.
 */
@SuppressWarnings("unchecked")
public class BinarySearchTreeDriver {

    /**
     * Main method. Takes in file from user input, uses
     * it to create a BinarySearchTree, and then runs
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
            BinarySearchTree list;
            //switch statement for the data type of the list
            switch (listType) {
            case "d":
                list = new BinarySearchTree<Double>();
                while (readFile.hasNextDouble()) {
                    list.insert(readFile.nextDouble());
                } //while
                break;
            case "s":
                list = new BinarySearchTree<String>();
                while (readFile.hasNext()) {
                    list.insert(readFile.next());
                } //while
                break;
            default:
                list = new BinarySearchTree<Integer>();
                while (readFile.hasNextInt()) {
                    list.insert(readFile.nextInt());;
                } //while
                break;
            } //switch
            //Prints out all of the commands available to the user
            System.out.println("Commands:");
            System.out.println("(i) - Insert Item");
            System.out.println("(d) - Delete Item");
            System.out.println("(p) - Print Tree");
            System.out.println("(r) - Retrieve item");
            System.out.println("(l) - Count Leaf Nodes");
            System.out.println("(s) - Find Single Parents");
            System.out.println("(c) - Find Cousins");
            System.out.println("(q) - Quit program");
            System.out.print("Enter a command: ");
            String line = sc.nextLine();
            //main program loop - runs until user quits
            while (!line.equals("q")) { //quit command
                switch (line) {

                case "i": //insert
                    System.out.print("In-order: ");
                    list.inOrder();
                    if (listType.equals("s")) {
                        System.out.print("Enter a string to insert: ");
                        list.insert(sc.nextLine());
                    } else if (listType.equals("d")) {
                        list.insert(commandDoubleInput(" to insert", sc));
                    } else {
                        list.insert(commandIntInput(" to insert", sc));
                    } //if
                    System.out.print("In-order: ");
                    list.inOrder();
                    break;

                case "d": //delete
                    System.out.print("In-order: ");
                    list.inOrder();
                    if (listType.equals("s")) {
                        System.out.print("Enter a string to delete: ");
                        if (list.delete(sc.nextLine()) == 0) {
                            System.out.println("Item is not present in the tree");
                        } else {
                            System.out.print("In-order: ");
                            list.inOrder();
                        } //if
                    } else if (listType.equals("d")) {
                        if (list.delete(commandDoubleInput(" to delete", sc)) == 0) {
                            System.out.println("The number is not present in the tree");
                        } else {
                            System.out.print("In-order: ");
                            list.inOrder();
                        } //if
                    } else {
                        if (list.delete(commandIntInput(" to delete", sc)) == 0) {
                            System.out.println("The number is not present in the tree");
                        } else {
                            System.out.print("In-order: ");
                            list.inOrder();
                        } //if
                    } //if
                    break;

                case "p": //print
                    System.out.print("In-order: ");
                    list.inOrder();
                    break;

                case "r": //retrieve
                    list.inOrder();
                    boolean retrieve = false;
                    if (listType.equals("s")) {
                        System.out.print("Enter a string to retrieve: ");
                        retrieve = list.retrieve(sc.nextLine());
                    } else if (listType.equals("d")) {
                        retrieve = list.retrieve(commandDoubleInput(" to retrieve", sc));
                    } else {
                        retrieve = list.retrieve(commandIntInput(" to retrieve", sc));
                    } //if
                    if (retrieve) {
                        System.out.println("Item is present in the tree");
                    } else {
                        System.out.println("Item is not present in the tree");
                    } //if
                    break;

                case "l": //num leaf nodes
                    System.out.println("The number of leaf nodes are " + list.getNumLeafNodes());
                    break;

                case "s": //single parents
                    System.out.print("Single Parents: ");
                    list.getSingleParent();
                    break;

                case "c": //cousins
                    System.out.print("In-order: ");
                    list.inOrder();
                    if (listType.equals("s")) {
                        System.out.print("Enter a string: ");
                        list.getCousins(sc.nextLine());
                    } else if (listType.equals("d")) {
                        list.getCousins(commandDoubleInput("", sc));
                    } else {
                        list.getCousins(commandIntInput("", sc));
                    } //if
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
        System.out.print("Enter a number" + operation + ": ");
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
        System.out.print("Enter a number" + operation + ": ");
        try {
            return Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid input");
            return commandIntInput(operation, sc);
        } //try
    } //commandIntInput

} //LinkedListDriver
