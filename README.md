# Single-Level-Cache
A Java program to simulate single-level cache implementation via Direct, Associative and N-way set associative mapping.

1- About the project
The source code is an attempt at implementing and simulating a single level cache memory (without the Main Memory) and its working in a computer system. The machine is 32-bit i.e. the size of the instruction is 32-bits. Size of a word has been assumed to be 1 byte.
There are three different types of cache mapping as implemented in the source file- Direct, Associative and N-way set associative.
The Least Recently Used or LRU cache replacement policy is employed to free up space in the cache in case it is full (in case of Fully Associative Mapping) or in case a cache set representing a particular index is full (in case of N-set Associative Mapping).

2- Running the code
The code is written in Java so it requires the Java Development Toolkit to be installed in the system on which it is going to be run.
Step 1- Open terminal/command prompt
Step 2- Go to the directory that contains the source code
Step 3- Type javac ​MeetakshiSetiya_2019253_FinalAssignment.java to compile the program. Step 4- Type java MeetakshiSetiya_2019253_FinalAssignment to run the program.
In case the file has been renamed, use the updated name instead of MeetakshiSetiya_2019253_FinalAssignment.

3- Input/Output format
The program asks for the following inputs-
1) Cache size and block size
2) Choice of cache mapping method (Direct, Fully Associative or N-set Associative) and
the requisite arguments required, if any for a particular mapping method (N, in case of N-set Associative mapping). It then follows the same mapping method for the entire program execution.
3) Choice of operation to be performed on the cache (read data from an address, write data to an address or display the cache contents)
4) Requisite arguments pertaining to the chosen cache operation
Execution continues till an exception/error is reported or when the user explicitly inputs the instruction to quit execution.

For a more extensive documentation and other information please refer the Documentation pdf file. 
