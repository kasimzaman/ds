package graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*  file:///Users/ali/Documents/workspace/KickStart/Notes/Graphs/dijkstra.jpg

	O(N^2). Its quadratic because we can't get the adjacent vertices with N^2. But adjacency list provide easy access to adjacent vertices,
	If the input graph is represented using adjacency list, it can be reduced to O(E+V)logV with the help of priority queue
	logV for extract min.

 	Why use a priority queue in Dijkstra
 	The idea behind it is, for every vertex(node) you are currently working on, you already found the shortest path to.
 	If you aren't expanding the smallest vertex, you aren't guaranteed to be finding the shortest path, thus you would
 	have to test every single path, not just one. So instead of having to go through every edge in just one path, you go
 	through every edge in every path.
 	Priority Queue complexity is O(logN)
 */
public class Dijkstra 
{
	public void dj()
	{	
		int graph[][] = new int[][]{{0,5,7,0},
						            {0,0,0,9},
						            {0,0,0,1},
						            {0,0,0,0}
						           };
		minPath(graph);
	}
	
	private void minPath(int graph[][])
	{
		int n=graph.length;
		int[]distances = new int[n];
		Arrays.fill(distances, Integer.MAX_VALUE);
		boolean visited[]=new boolean[n];
		Arrays.fill(visited, false);
		distances[0]=0;
		
		for(int row=0;row<n-1;row++)
		{
			// Pick the minimum distance vertex from the set of vertices
            // not yet processed. min is always equal to src in first
            // iteration.
			int min=getMin(distances,visited);
			visited[min]=true;
			// Update dist value of the adjacent vertices of the
            // picked vertex.
			for(int i=0;i<n;i++)
			{
				if(visited[i] || graph[row][i]==0)
					continue;
				int sum = distances[min] + graph[row][i];
				if(sum < distances[i])
					distances[i]=sum;
			}
		}
		System.out.println(distances[n-1]);
	}
	private int getMin(int[]distance,boolean visited[])
	{
		int min=Integer.MAX_VALUE;
		int index=-1;
		for(int i=0;i<distance.length;i++)
		{
			if(visited[i]==false && distance[i]<min){
				min = distance[i];
				index=i;
			}
		}
		return index;
	}
    
}
