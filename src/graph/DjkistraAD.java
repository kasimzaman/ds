package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class DjkistraAD
{
	int N=9;
	public void createGraph()
	{
		Graph g = new Graph(N);
		g.addEdge( 0, 1, 4);
		g.addEdge( 0, 7, 8);
		g.addEdge( 1, 2, 8);
		g.addEdge( 1, 7, 11);
		g.addEdge( 2, 3, 7);
		g.addEdge( 2, 8, 2);
		g.addEdge( 2, 5, 4);
		g.addEdge( 3, 4, 9);
		g.addEdge( 3, 5, 14);
		g.addEdge( 4, 5, 10);
		g.addEdge( 5, 6, 2);
		g.addEdge( 6, 7, 1);
		g.addEdge( 6, 8, 6);
		g.addEdge( 7, 8, 7);
		int[]distances=new int[N];
		Arrays.fill(distances, Integer.MAX_VALUE);
		distances[0]=0;
		boolean visited[]=new boolean[N];
		Vertice start = new Vertice(0, 0);
		Vertice end = new Vertice(4, 0);
		sp(g,start,end,distances,visited);
	}
	
	public void sp(Graph graph, Vertice start, Vertice end,int[]distances,boolean visited[])
	{
		Queue<Vertice>que = new PriorityQueue<>();
		que.add(start);
		while(!que.isEmpty())
		{
			Vertice v = que.remove();
			visited[v.id]=true;
			LinkedList<Vertice>children=graph.adj[v.id];
			for(Vertice child : children)
			{
				if(visited[child.id])
					continue;
				int sumPath = distances[v.id] + child.weight;
				if(sumPath < child.weight){
					distances[child.id]=sumPath;
					child.weight=sumPath;
				}
				que.offer(child);
			}
		}
	}
	
	
	class Graph
	{
		int V;
		LinkedList<Vertice>[]adj;
		Graph(int v)
		{
			this.V=v;
			adj=new LinkedList[v];
			for(int i=0;i<v;i++)
				adj[i]=new LinkedList();
		}
		public void addEdge(int u,int v ,int w)
		{
			Vertice vertice = new Vertice(v, w);
			adj[u].add(vertice);
		}
	}
	class Vertice implements Comparable<Vertice>
	{
		int id;
		int weight;
		Vertice(int id,int weight)
		{
			this.id=id;
			this.weight=weight;
		}
		@Override
		public int compareTo(Vertice v)
		{
			return this.weight - v.weight;
		}
	}
}
