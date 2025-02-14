# Project #: Project 1 (Deterministic Finite Automata)

* Author: Reggie Wade, Vlad Maliutin
* Class: CS361
* Semester: Spring 2025

## Overview

This program simulates a Deterministic Finite Automaton.  The program is built
to manage the 5-tuple, as well as swapping transition labels.

## Reflection

Based on our pretty good understanding of object oriented programming, the things
that went the best were related to that.  Checking states, adding stats, adding 
transitions, and adding the alphabet was pretty straightforward.  The two hardest
tasks when building this project was the swap function and the accept function.
The swap function was difficult because we had to find a way to loop through each
state and check all of the transition labels.  Once we had that done it was easy to
implement the swap functionality itself.  The accept function wasn't extrememly hard,
but it took a second for us to figure out a good way to loop through every character
of the string.

Another thing that was somewhat annoying to implement was the toString method just
because it had to replicate the exact format in order to compare to the tests
successfully.  We used strong object oriented design to make our code easy to modify
and ran some of the tests as we got certain things working to verify our implementation.
We don't really know what we would have changed about the design process because it
didn't take too long and was pretty straightforward.


## Compiling and Using

Directions to run tests (onyx)
```sh
javac -cp .:/usr/share/java/junit.jar ./test/dfa/DFATest.java
java -cp .:/usr/share/java/junit.jar:/usr/share/java/hamcrest/core.jar
org.junit.runner.JUnitCore test.dfa.DFATest
```