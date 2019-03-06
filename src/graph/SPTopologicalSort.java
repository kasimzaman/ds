package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
/*
 * The correctness follows from the fact that by the time we reach v, we've already processed all vertices that have an edge to v.
 * Time Complexity: Time complexity of topological sorting is O(V+E). After finding topological order, the algorithm process
 * all vertices and for every vertex, it runs a loop for all adjacent vertices. Total adjacent vertices in a graph is O(E).
 * So the inner loop runs O(V+E) times. Therefore, overall time complexity of this algorithm is O(V+E).
 * 
 * You need to implement comparable , for the que to compare the nodes.
 */
public class SPTopologicalSort
{
	int N=6;
	public void sp()
	{
		Graph g = new Graph(N);
		g.addEdge(0, 1, 5);
	    g.addEdge(0, 2, 3);
	    g.addEdge(1, 3, 6);
	    g.addEdge(1, 2, 2);
	    g.addEdge(2, 4, 4);
	    g.addEdge(2, 5, 2);
	    g.addEdge(2, 3, 7);
	    g.addEdge(3, 5, 1);
	    g.addEdge(3, 4, -1);
	    g.addEdge(4, 5, -2);
        boolean[]visited=new boolean[N];
        Stack<Node>stack=new Stack<>();
        if(!hasCyclesDG(g,new Node(0,0),visited,stack))
        {
        	//shortestPath(0,g);
        	longestPath(1,g);
        }
	}
	
	private boolean hasCyclesDG(Graph graph,Node node,boolean[]visited,Stack<Node>stack)
	{
		if(visited[node.id])
			return false;
		visited[node.id]=true;
		stack.push(node);
		List<Node>children = graph.adj[node.id];
		for(int i=0;i<children.size();i++)
		{
			Node n = children.get(i);
			if(stack.contains(n))
				return true;
			if(hasCyclesDG(graph, n, visited, stack))
				return true;
		}
		stack.pop();
		return false;
	}
	private void shortestPath(int s,Graph graph)
	{
		Stack<Integer>stack=new Stack<>();
		boolean[]visited=new boolean[N];
		for(int i=0;i<N;i++)
		{
			if(visited[i]==true)
				continue;
			topologicalSort(graph,i,visited,stack);
		}
		int[]dist=new int[N];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0]=0;
		// Process vertices in topological order
		while(!stack.isEmpty())
		{
			// Get the next vertex from topological order
			int n = stack.pop();
			
			// Update distances of all adjacent vertices
			LinkedList<Node> list = graph.adj[n];
			for(int i=0;i<list.size();i++)
			{
				Node node = list.get(i);
				int sum = dist[n] + node.weight;
				if(dist[node.id] > sum)
					dist[node.id] = sum;
			}
		}
	}
	
	private void longestPath(int s,Graph graph)
	{
		Stack<Integer>stack=new Stack<>();
		boolean[]visited=new boolean[N];
		for(int i=0;i<N;i++)
		{
			if(visited[i]==true)
				continue;
			topologicalSort(graph,i,visited,stack);
		}
		int[]dist=new int[N];
		Arrays.fill(dist, Integer.MIN_VALUE);
		dist[0]=s;
		// Process vertices in topological order
		while(!stack.isEmpty())
		{
			// Get the next vertex from topological order
			int n = stack.pop();
			
			// Update distances of all adjacent vertices
			LinkedList<Node> list = graph.adj[n];
			
			for(int i=0;i<list.size();i++)
			{
				Node node = list.get(i);
				int sum = dist[n] + node.weight;
				if(dist[node.id] < sum)
					dist[node.id] = sum;
			}
		}
		
	}
	
	
	private void topologicalSort(Graph graph,int id, boolean[]visited,Stack<Integer>stack)
	{
		visited[id]=true;
		LinkedList<Node>children = graph.adj[id];
		for(int i=0;i<children.size();i++)
		{
			Node n = children.get(i);
			if(visited[n.id])
				continue;
			topologicalSort(graph,n.id,visited,stack);
		}
		stack.push(id);
	}
	
}

