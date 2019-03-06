package graph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Revision
{
	 Graph graph = new Graph(6);
	class Graph
	{
		 int V;
		 LinkedList<Integer>[]adj;
		 Graph(int v)
		 {
			 this.V=v;
			 adj=new LinkedList[V];
			 for(int i=0;i<v;i++)
			 { 
				 adj[i]=new LinkedList(); 
			 }
		 }
		 public void addEdge(int v, int e)
		 {
			 adj[v].add(e);
		 }
	}
	public void revision()
	{	
		graph.addEdge(5, 2);
		graph.addEdge(5, 0);
		graph.addEdge(4, 0);
		graph.addEdge(4, 1);
		graph.addEdge(2, 3);
		graph.addEdge(3, 1);
		ts();
	}
	/******************************************************************************/
	//DFS
	private void dfs(Graph graph)
	{
		boolean[]visited=new boolean[graph.adj.length];
		dfs(5,visited);
		
	}
	private void dfs(int v, boolean[]visited)
	{
		visited[v]=true;
		System.out.print(v+" ");
		Iterator<Integer> i = graph.adj[v].listIterator();
		while(i.hasNext())
		{
			int n = i.next();
			if(!visited[n])
				dfs(n,visited);
		}
	}
	/******************************************************************************/
	//TOPOLOGICAL SORT
	//Time Complexity: The above algorithm is simply DFS with an extra stack. So time complexity is same as DFS which is O(V+E).
	private void ts()
	{
		boolean[]visited=new boolean[graph.adj.length];
		Stack<Integer>stack=new Stack<>();
		for(int i=0;i<graph.V;i++)
		{	
			if(!visited[i])
				ts2(i,stack,visited);
		}
		while(!stack.isEmpty())
			System.out.print(stack.pop()+" ");
	}
	private void ts2(int v,Stack<Integer>stack, boolean[]visited)
	{
		visited[v]=true;
		LinkedList<Integer> children = graph.adj[v];
		for(int i : children)
		{
			if(!visited[i])
			{
				ts2(i,stack,visited);
			}
		}
		stack.push(v);
	}
	
	/******************************************************************************/
	
	
	
	/******************************************************************************/
	
	
	
	/******************************************************************************/
	
	
	
	/******************************************************************************/
	
	
	
	/******************************************************************************/
	
	
	
	/******************************************************************************/
	
	
	
	
}
