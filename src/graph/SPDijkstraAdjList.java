package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
/*
 * // Algo:
		// 1. Take the unvisited node with minimum weight.
		// 2. Visit all its neighbors.
		// 3. Update the distances for all the neighbors (In the Priority Queue).
		// Repeat the process till all the connected nodes are visited.
 */
public class SPDijkstraAdjList
{
	int N=9;
	public void spd()
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
        
        shortestPath(g,0,4);
	}
	
	private void shortestPath(Graph graph,int start,int end)
	{	
		boolean[]visited=new boolean[N];
		int[]distance=new int[N];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[0]=0;
		Map<Integer,Integer>path = new HashMap<>();
		Queue<Node>que=new PriorityQueue<Node>();
		que.offer(new Node(start,0));
		path.put(start, Integer.MAX_VALUE);
		while(!que.isEmpty())
		{
			Node node = que.poll();
			visited[node.id]=true;
			LinkedList<Node> list = graph.adj[node.id];
			for(int i=0;i<list.size();i++)
			{
				Node next = list.get(i);
				if(visited[next.id])
					continue;
				int sum = distance[node.id] + next.weight;
				if(distance[next.id]>sum)
				{
					distance[next.id]=sum;
					path.put(next.id, node.id);
				}
				if(end==next.id)
				{
					List<Integer>pt = new ArrayList<>();
					pt.add(0, end);
					int parent=path.get(end);
					
					while(parent!=Integer.MAX_VALUE)
					{
						pt.add(0,parent);
						parent =path.get(parent);
					}
					for(int s:pt)
						System.out.print(s+" ");
				}
				que.offer(new Node(next.id,distance[next.id]));
			}
		}
	}
}

class Node implements Comparable<Node>
{
	int id;
	int weight;
	Node(int v,int w)
	{
		this.id=v;
		this.weight=w;
	}
	@Override
	public int compareTo(Node o) {
		return this.weight - o.weight;
	}
}
class Graph
{
	int V;
	LinkedList<Node>[]adj;
	public Graph(int v)
	{
		this.V=v;
		adj = new LinkedList[v];
		for(int i=0;i<v;i++)
			adj[i]=new LinkedList<Node>();
	}
	void addEdge(int u,int v,int w)
	{
		Node node=new Node(v,w);
		adj[u].add(node);
	}
}
