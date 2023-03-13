#!/bin/bash -e
#cat text.txt
#echo ---------- Compiling... ----------
javac -d bin src/cs2720/ItemType.java
javac -d bin -cp bin src/cs2720/NodeType.java
javac -d bin -cp bin src/cs2720/SortedLinkedList.java
javac -d bin -cp bin src/cs2720/LinkedListDriver.java
#echo -------- Done Compiling. ---------
#echo ------- Running Program... -------
java -cp bin cs2720.LinkedListDriver input.txt
#echo --------- Done Running. ----------
