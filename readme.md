
# RushHour Solver

This repository is to showcase the rushhour solver program.

## Description

The main purpose of this program is to solve a 6 by 6 rushhour game board and have the main vehicle reach the exit destination. 

**The implementation process goes according to the following:**

The program class structure consists of four classes: the Solver class, the RushHourGraph class, the Vertex class, and the Board class. The Solver class includes a key method called solveFromFile that takes a string input path and a string output path. This method is used for the initial reading of the Rush Hour board from the given file and for creating the RushHourGraph object with the input of a Board object (the original board). This RushHourGraph object is then used to call the startingSolver method with the input of the original board object, which returns a Stack object containing vertices of the board. This stack indicates the path to solving the board, with each vertex in the stack representing the required car move data (car name, moved slot, direction) to successfully solve the board. Furthermore, the stack is used to generate the solution files based on all the correct movement data, such as EL3, which indicates car E, left direction, and moved 3 slots left.

The RushHourGraph class implements the core of the project using a basic Breadth First Search (BFS) algorithm, which can potentially find the shortest path to solve each Rush Hour board test case. The Vertex and Board classes are used to store each board’s data so that the target board can locate the path to the original board by setting the parent vertex. Since the BFS algorithm does not require heuristics, they were not considered in this project.

The BFS algorithm implementation approach is rather simple. The original board vertex is first added to the queue, and while the queue is not empty, we remove the first/current vertex and evaluate if it has already been visited or explored to prevent creating duplicate vertices further down the program. If the vertex has not been visited, it is added to a hash set of explored boards (boards that are not the target board). Using the current vertex’s board, we get all its neighboring boards and assign them to a hash set. A loop is then used to iterate through all neighboring boards (child boards of the current vertex board). If one of the boards has not been visited (or does not exist in the explored hash set), a new vertex is created based on the unexplored board, the vertex parent is set to the current vertex, and the vertex is added to the queue created earlier. This process is repeated until the target board is found.

**Example testcase:**

<img src="https://github.com/thomaslui003/rushHourSolver/blob/main/testcase.png" width="264" height="328">

**Solution to the board above:**

PD1
CL1
OU1
XL1
EU1
QR2
BD4
AR1
XR1
DR1
PU3
QL3
GU3
QR3
PD3
DL1
AL1
IL1
XL1
BU4
QL2
HL2
OD3
ED2
CR1
GU1
XR4

**Eg.** PD1 of the solution above means vehicle P move downward one step

By following all of the steps will lead to a solved board.

### Executing program

The application can be compiled and run by executing the following at the src file level:
```
javac rushhourtest/TestRushHour.java
java rushhourtest/TestRushHour
```
If there are errors locating the testcase files, change the directories of the input testcase and output resulting files in the TestRushHour java file.

The execution will create all step by step solution to all input board in a text file format.




