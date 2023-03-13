#!/bin/bash -e
#cat text.txt
#echo ---------- Compiling... ----------
javac -d bin src/cs2720/Sort.java
javac -d bin -cp bin src/cs2720/SortDriver.java
#javac -d bin -cp bin src/cs2720/SortDriver2.java
#echo -------- Done Compiling. ---------
#echo ------- Running Program... -------
java -cp bin cs2720.SortDriver random.txt
#java -cp bin cs2720.SortDriver2
#echo --------- Done Running. ----------
