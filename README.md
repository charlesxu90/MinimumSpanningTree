Thank you for reading our code.

Here in this code, we included 4 algorithms to inplememt minimum spanning tree.
They are as follows:
1. Prim's using adjacency matrix: Prim_AM.java;
2. Prim's using lazy Priority Queue: Prim_PQ_lazy.java;
3. Prim's using eager Priority Queue: Prim_PQ_eager.java;
4. Kruskal's using Priority Queue: Kruskal_PQ.java.

To check our codes are right, we used a sample graph to test it. The graph has 6 vertices and 10 edges.(As show in input.txt and inputE.txt). The testing results is shown in Test.txt.

In order to specify the runtime of the algorithms, we first generate random graphs with fixed number of nodes and edges(RandomGraph.java). (The selected number of nodes and edges to test our programs are shown in inputVE.txt.)

For each V/E pairs, we run it 5 and 10 times. The run time results are shown in 5runTime.txt and 10runTime.txt.(The Computer Configure are in cpu.txt and ram.txt files. We allocate Java Virtual Machine 10G memory, when running the program, the maximum virtual memory is 11.9G and physical memory is 9.8G.)

Finally, we use R and Excel to exemplify our results. R is used to create the sample graph. Excel is used to generate the performance curve of the algorithms.


%%%%%%%%%%%%%%
To run the codes, 
First compile the codes with:
javac MinSpanTree.java

Then run the code with
java MinSpanTree

To run the all the graph with selected V and E number, first comment out the line in file inputVE.txt. Then run with the code below:
java -Xmx10g MinSpanTree

You may need to change the route of the input file: inputVE.txt in the code
