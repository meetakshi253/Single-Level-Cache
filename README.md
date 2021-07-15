
# Single-level Cache Implementation

A Java program to simulate single-level cache implementation via Direct, Associative and N-way set associative mapping.


## About

- The source code is an attempt at implementing and simulating a single level cache memory (without the Main Memory) and its working in a computer system. 
- The machine is 32-bit i.e. the size of the instruction is 32-bits. Size of a word has been assumed to be 1 byte.
- Three different types of cache mapping are implemented- Direct, Associative and N-way set associative.
- The Least Recently Used or LRU cache replacement policy is employed to free up space in the cache in case it is full (Fully Associative Mapping) or in case a cache set representing a particular index is full (N-set Associative Mapping).

  
## Technology Used

- Java


  
## Run Locally

Ensure that Java Development Toolkit is installed on your system. 

Clone the project

```bash
  git clone https://github.com/meetakshi253/Single-Level-Cache-Implementation.git
```

Go to the project directory

```bash
  cd Single-Level-Cache-Implementation
```

Compile

```bash
  javac ​MeetakshiSetiya_2019253_FinalAssignment.java
```

Run

```bash
  java MeetakshiSetiya_2019253_FinalAssignment
```

  
## Input/Output format

The program asks for the following inputs:

- Cache size and block size
- Choice of cache mapping method (Direct, Fully Associative or N-set Associative) and the requisite arguments required, if any, for a particular mapping method (N, in case of N-set Associative mapping). 
- Choice of operation to be performed on the cache (read data from an address, write data to an address or display the cache contents).
- Requisite arguments pertaining to the chosen cache operation

> The program follows the same mapping method for the throughout the program execution. 
> Execution continues till an exception/error is reported or when the user explicitly inputs the instruction to quit execution.
## Documentation

For more extensive documentation, visit 
[Documentation](./Documentation.pdf)

  
