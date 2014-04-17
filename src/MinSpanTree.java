
import java.io.*;
import Search.*;
import generator.*;

public class MinSpanTree {
	static float[][] adjMatrix;
	EdgeWeightedGraph ewg;
	static int v;
	int e;

	/**
	 * Print matrix of a graph, used for debugging
	 */
	public static void printMatrix(int V, float[][] matrix){

		for (int i = 0; i < V; i++) {
		    for (int j = 0; j < V; j++) {
		        if(matrix[i][j]==-1){System.out.print(0.0 + " ");}
		        else{System.out.print(matrix[i][j] + " ");}
		    }
		    StdOut.print("\n");
		}
	}

	/**
	 * Generate a random MST with certain Vertex and Edge number
	 */
	public void generateGraph(int V, int E){
		RandomGraph G = new RandomGraph(V,E);
		G.Generate();
		adjMatrix = G.W();
		v = G.V();
		//Get EdgeWeightedGraph (Adjacency list expression of graph),
		//which will be used in Priority Queue implementations
		ewg = new EdgeWeightedGraph(v,adjMatrix);
	}

	/**
	 * 1. Prim's using Adjacency Matrix
	 */
	public  void runPrim_AM(){
//		printMatrix(v, adjMatrix);
		Prim_AM primAdjMat = new Prim_AM(v,adjMatrix);
//		float[][] prim_AM_MST = primAdjMat.searchMST();
//		StdOut.println();
//		printMatrix(v, prim_AM_MST);
	}
	
	/**
	 * 2.1 Prim's using lazy Priority Queue
	 */
	public void runPrim_lazyPQ(){
        Prim_PQ_lazy Prim_PQ_lazy_MST = new Prim_PQ_lazy(ewg);
//        StdOut.printf("%.5f\n", Prim_PQ_lazy_MST.weight());
	}
	
	/**
	 * 2.2 Prim's using eager Priority Queue
	 */
	public void runPrim_eagerPQ(){
        Prim_PQ_eager prim_PQ_eager_MST = new Prim_PQ_eager(ewg);
//        StdOut.printf("%.5f\n", prim_PQ_eager_MST.weight());
	}
	
	/**
     * 3. Kruskal's using Priority Queue
     */
	public void runKruskal_PQ(){
        Kruskal_PQ kruskal_PQ_MST = new Kruskal_PQ(ewg);
//        StdOut.printf("%.5f\n", kruskal_PQ_MST.weight());
	}
	
	public static void main(String[] args) throws IOException {
		FileOutputStream out = new FileOutputStream("./src/runTime.txt");
		PrintStream outPrint = new PrintStream(out);
		MinSpanTree mst = new MinSpanTree();
		String filepath = "./inputVE.txt";
		BufferedReader in = new BufferedReader(new FileReader(filepath));
		try{
			String lineString;
			String[] valueStrings;
			
			int numVertex;int numEdges;
//			int numVertex=6;int numEdges=10;
			String[] edgeinfo; 
			long startTime; long endTime; 
			while((lineString = in.readLine()) != null )
			{
				
//				StdOut.println(lineString);
				valueStrings = lineString.split("\\s+");
				
				if(valueStrings.length == 2 && valueStrings[0] != null){
					//Jump commented line
					if(valueStrings[0].contains("#")){
						continue;
					}
					
					numVertex = Integer.parseInt(valueStrings[0]);
					System.out.println("Vertices: " + numVertex);
					edgeinfo = valueStrings[1].split(",");
					
					for(int i=0; i<edgeinfo.length; i++){
						numEdges = Integer.parseInt(edgeinfo[i]);
						if(numEdges>numVertex*(numVertex-1)/2){
							System.out.println("Edge number is bigger than v*(v-1)/2");
							continue;
						}
						System.out.println("\nVertices: " + numVertex + " Edges:" + numEdges);
						outPrint.print(numVertex + "-" + numEdges + ":\n");
						for(int times=1; times<=10; times++){
							mst.generateGraph(numVertex,numEdges);

							startTime = System.currentTimeMillis();
							mst.runPrim_AM();
							endTime = System.currentTimeMillis();
							outPrint.print((endTime-startTime)+"\t");
//							StdOut.println("Running Time :"+(endTime-startTime));

							startTime = System.currentTimeMillis();
							mst.runPrim_lazyPQ();
							endTime = System.currentTimeMillis();
							outPrint.print((endTime-startTime)+"\t");
//							System.out.println("Running Time :"+(endTime-startTime));

							startTime = System.currentTimeMillis();
							mst.runPrim_eagerPQ();
							endTime = System.currentTimeMillis();
							outPrint.print((endTime-startTime)+"\t");
//							System.out.println("Running Time :"+(endTime-startTime));

							startTime = System.currentTimeMillis();
							mst.runKruskal_PQ();
							endTime = System.currentTimeMillis();
							outPrint.print((endTime-startTime)+"\t");
//							System.out.println("Running Time :"+(endTime-startTime));

//							outPrint.println();
						}
					}
//					StdOut.println();
				}
				else{
					System.out.println("Error input data Format: \n"+"Example: 4 3,4,5");
					break;
				}
//				StdOut.println(valueStrings.length);
			}
			
		}catch(Exception e){
			System.out.println("Error in read:" + e.getMessage());
        } finally {
            if (in!=null) in.close();
            if(outPrint!=null) outPrint.close();
            if(out!=null) out.close();
        }
        
	}
	
}
