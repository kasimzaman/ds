package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class DetectCycleUnionFind
{
	public void uf()
	{
		int N=3;
		Graph g = new Graph(N);
		g.addEdge(0, 1, 1);
		g.addEdge(0, 2, 1);
		g.addEdge(1, 2, 1);
		int[]parent=new int[N];
		Arrays.fill(parent, -1);
		detectCycle(g,parent);
	}
	private int find(int[]parent,int i)
	{
		if(parent[i]==-1)
			return i;
		return find(parent,parent[i]);
	}
	private void union(int[]parent,int x,int y)
	{
		int a = find(parent,x);
		int b = find(parent,y);
		if(a!=b)
			parent[a]=b;
	}
	private boolean detectCycle(Graph graph,int[]parent)
	{
		Queue<Integer>que=new LinkedList<>();
		que.add(0);
		while(!que.isEmpty())
		{
			int node = que.poll();
			LinkedList<Node>vertices=graph.adj[node];
			for(int i=0;i<vertices.size();i++)
			{	
				int a = find(parent,node);
				int b = find(parent,vertices.get(i).id);
				if(a==b)
					return true;
				union(parent,a,b);
				que.add(b);
			}
		}
		return false;
	}
}
