package generator;

import java.io.*;  
import java.util.*;


public class RandomGraph {

	private int V;
	private int E;
	private static int MaxW=100;
	public float[][] adj;


	public RandomGraph(){ V=10;E=20;}

	public RandomGraph(int V, int E){
		this.V=V;
		this.E=E;
		InitiAdj(V);
	}

	public void InitiAdj(int V){
		this.adj=new float[V][V];
		int i,j;
		for(i=0;i<V;i++)
		{
			for(j=0;j<V;j++)
				if(i==j){this.adj[i][j]=0;}
				else{this.adj[i][j]=-1;}
		}
	}

	public int addEdge(int v, int w,int m,float W) {
		if (adj[v][w]==-1 && (v!=w)) 
		{
			m--;
			adj[v][w] = W;
			adj[w][v] = W;
		}
		return m;
	}

	public void Generate(){
		int m=this.E;
		
		ArrayList<Integer> InSet = new ArrayList<Integer>();
		ArrayList<Integer> OutSet = new ArrayList<Integer>();
		
		InSet.add(0);
		for(int i=0;i<V-1;i++)
			OutSet.add(i+1);
		// First generate a graph/tree which is connected
		while(OutSet.size()>0)
		{
			int indexin = (int) (InSet.size()*Math.random());
			int v=InSet.get(indexin);
			int indexout= (int) (OutSet.size()*Math.random());
			int w=OutSet.get(indexout);
			float W = (float) (MaxW * Math.random());
			m = addEdge(v, w, m, W);
			InSet.add(w);
			OutSet.remove(indexout);
		}
		// Then add edges to the graph arbitrarily
		while (m!=0) {
			int v = (int) (V * Math.random());
			int w = (int) (V* Math.random());
			float W = (float)(MaxW * Math.random());
			m = addEdge(v, w, m, W);
		}
	}

	public int V() { return V; }
	public int E() { return E; }
	public float[][] W() {return adj;}

	public static void main(String[] args) {  

		FileOutputStream out = null;       
		int i1=1;
		RandomGraph G=new RandomGraph(10000,49995000);
		G.Generate();
		int V=G.V();
		int E=G.E();
		float[][] adj= G.W();
		try {   

			out = new FileOutputStream(new File("/Users/charles/Documents/eclipse/MSTLargeGraph_Dense"+i1+".txt"));   
			out.write((V+"\r\n").getBytes());
			out.write((E+"\r\n").getBytes());

			for (int j = 0; j < V; j++) {
				for(int k=j+1;k<V;k++){
					if (adj[j][k]!=0f)
						out.write(( j +" "+ k +" "+ adj[j][k] +"\r\n").getBytes());   
				} 
			}

			out.close();
		}
		catch (Exception e) {   

			e.printStackTrace();   

		}

		finally {   

			try {  

				out.close();   

			} catch (Exception e) {   

				e.printStackTrace();   

			} 

		}   

	}
}




