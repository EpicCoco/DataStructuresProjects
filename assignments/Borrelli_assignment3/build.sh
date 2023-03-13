#!/bin/bash -e
#cat text.txt
#echo ---------- Compiling... ----------
javac -d bin src/cs2720/NodeType.java
javac -d bin -cp bin src/cs2720/BinarySearchTree.java
javac -d bin -cp bin src/cs2720/BinarySearchTreeDriver.java
#echo -------- Done Compiling. ---------
#echo ------- Running Program... -------
java -cp bin cs2720.BinarySearchTreeDriver int-input1.txt
#echo --------- Done Running. ----------
