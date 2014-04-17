package Search;

import java.io.*;

/*
 * example:
 * Input adjacency matrix:
5 1 
 0.0  1.0  3.0 -1.0 -1.0 
 1.0  0.0  3.0  6.0 -1.0 
 3.0  3.0  0.0  4.0  2.0 
-1.0  6.0  4.0  0.0  5.0 
-1.0 -1.0  2.0  5.0  0.0 
 * Output MST:
 0.0  1.0  3.0 -1.0 -1.0 
 1.0  0.0 -1.0 -1.0 -1.0 
 3.0 -1.0  0.0  4.0  2.0 
-1.0 -1.0  4.0  0.0 -1.0 
-1.0 -1.0  2.0 -1.0  0.0 
 */
public class Prim_AM {
	private static int numVertices;
	private static float[][] adjMatrix;
	private static float[][] primMST;
	private static boolean[] SpannedVertices;
	private static float[] Distance;
	private static int[] Predecessor;

	public Prim_AM(){}
	
	public Prim_AM(int v, float[][] adjM)
	{
		numVertices=v;
		adjMatrix=adjM;
	}

	public float[][] searchMST()
	{
		initArray();
		/*
		 * Randomly select a vertex to start sear, here we pick 0.
		 */
		Distance[0] = 0;
		int numVerticesAdded = 0; 
		Predecessor[0] = 0;
		int newVertex = 0;
		while(numVerticesAdded < numVertices)
		{
			float minWeight = -1;
			for(int i = 0; i < numVertices; i++ )
			{
				/*
				 * Search for vertex with lowest priority that is not in MST;
				 * That is search for least value in Distance[i] for which i is not spanned.
				 */
				if(SpannedVertices[i] == true || Distance[i] == -1)
				{
					continue;
				}


				if(minWeight == -1)
				{ 
					minWeight = Distance[i];
					newVertex = i;
					continue;
				}

				if(Distance[i] < minWeight)
				{
					minWeight = Distance[i];
					newVertex = i;
				}

			}

//			System.out.println(" Next vertex: " + (newVertex+1) + ", minWeight: "+ minWeight + ", Distance[i]: "+ Distance[newVertex]);

//			for(int i=0; i< numVertices; i++)
//			{
//				System.out.print(", V"+ (i+1) + " D: " + Distance[i]);
//			}
//			System.out.println();

			/*
			 * Change the Vertex to be spanned
			 */
			SpannedVertices[newVertex] = true; 
			numVerticesAdded++;
			
			/*
			 * Update the Distance array
			 */
			for(int i=0; i<numVertices; i++)
			{
				if( SpannedVertices[i] == true || adjMatrix[newVertex][i] == -1 )
				{
					continue;
				}
				
				/*
				 * If this distance is smaller, then update Distance
				 */
				if ( Distance[i]==-1 || Distance[i]>adjMatrix[newVertex][i] )
				{
					Distance[i] = adjMatrix[newVertex][i];
					Predecessor[i] = newVertex;
				}
				
			}
		}
		
//		for(int i=0; i< numVertices; i++)
//		{
//			System.out.println("Vertex: "+ (i+1) + ", Parent: " + (Predecessor[i]+1));
//		}
		arrayToMST();
		
		return primMST;
	}
	
	public static void arrayToMST() 
	{
		/*
		 * Get the adjacency matrix expression of MST from predecessor array. 
		 */
		for(int i = 0; i < numVertices; i++)
		{
			System.arraycopy( adjMatrix[i], 0, primMST[i], 0, numVertices );
			for(int j = 0; j < numVertices; j++)
			{
				if( i == j || Predecessor[i] == j || Predecessor[j] == i ){
					continue;
					}
				primMST[i][j] = -1; 
			}
		}
	}
	
	public static void initArray()
	{
		/*
		 * Initialize SpannedVertices[], Distance[], primMST[][] and predecessor[]. 
		 */
		SpannedVertices = new boolean[numVertices];
		Distance = new float[numVertices];
		primMST = new float[numVertices][numVertices];
		Predecessor = new int[numVertices];

		for(int i = 0; i < numVertices; i++)
		{
			SpannedVertices[i] = false;
			Distance[i] = -1; 
			Predecessor[i] = -1;
		}
	}
	
	public void printMatrix(float[][] matrix){
		/*
		 * Print matrix of the graph
		 */
		for (int i = 0; i < numVertices; i++) {
		    for (int j = 0; j < numVertices; j++) {
		        if(matrix[i][j]==-1){System.out.print(0.0 + " ");}
		        else{System.out.print(matrix[i][j] + " ");}
//		        System.out.print(matrix[i][j] + " ");
		    }
		    System.out.print("\n");
		}
	}
	public float weight(){
		float weight=0;
		for (int i = 0; i < numVertices; i++) {
		    for (int j = i+1; j < numVertices; j++) {
		        if(primMST[i][j]>0){
		        	weight +=primMST[i][j];
		        	}
		        }
		    }
		return weight;
	}
	
	
	public void readMatrix(String filepath) throws IOException {
		/*
		 *  Read matrix from file
		 */
		BufferedReader in = new BufferedReader(new FileReader(filepath));
		try{
			String lineString;
			String[] valueStrings;
			
			int x = 0;
			while((lineString = in.readLine()) != null )
			{
				
				valueStrings = lineString.split("\\s+");
				
				if(valueStrings.length == 2 && valueStrings[0] != null){
					
					/*
					 * Read the first line to get the vertice number
					 */
					
					numVertices = Integer.parseInt(valueStrings[0]);
					
					/*
					 * Initiate adjacency matrix here
					 */
					adjMatrix = new float[numVertices][numVertices];
					
//					System.out.println("The total vertices number is: " + numVertices);
					
					continue;
				}
//				System.out.println(valueStrings.length);
				for(int y = 0; y<valueStrings.length; y = y+1)
				{
//					System.out.println(valueStrings[y]);
					if(valueStrings[y] == null)
					{	continue;	}
					adjMatrix[x][y] = Float.parseFloat(valueStrings[y]);
					
					if((x>=numVertices) || (y>= numVertices)){
						System.out.println("Input data error, please check!");
					}
				}
				x = x + 1;
			}
			
		}catch(Exception e){
			System.out.println("Error in readMatrix:" + e.getMessage());
        } finally {
            if (in!=null) in.close();
        }
		
	}

	public static void main(String[] args)throws Exception{
		/**
		 * @param filepath
		 * @throws IOException
		 */
		try {
			
			/*
			 * Handle the arguments
			 */
//			String filepath = args[0];
			String filepath = "/Users/charles/Documents/eclipse/MinSpanTree/src/input.txt";
//			System.out.println(filepath);
			
			/*
			 * Read graph info from file
			 */
			Prim_AM prim_AM= new Prim_AM();
			prim_AM.readMatrix(filepath);
			
			/*
			 * Search for minimum spanning tree
			 */
			prim_AM.searchMST();
			
			System.out.println("The adjacency matrix of the graph is:");
			prim_AM.printMatrix(adjMatrix);
			
			/*
			 *  Print out the adjacency matrix of Minimum Spanning Tree
			 */
			System.out.println("The final MST matrix is:");
			prim_AM.printMatrix(primMST);
			System.out.println(prim_AM.weight());
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error!"+e.getMessage());
			
		}
		
	}
}

