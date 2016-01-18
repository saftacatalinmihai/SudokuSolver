#!/bin/bash
javac -cp src/:/usr/share/java/junit4.jar test/PuzzleTest.java
java -cp test/:src/:/usr/share/java/junit4.jar org.junit.runner.JUnitCore PuzzleTest
