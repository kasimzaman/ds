package graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

//Time Complexity: Time complexity of topological sorting is O(V+E)
//this is only possible if the graph is directed and Acyclic. We reduce complexity by doing topological sort first
//after we have topological ordering, the loop picks one vertice from stack at a time and calculate the distances of the adjacent edges.
//since total vertices are E so the inner loop runs O(E+V)
public class LongestPath 
{
	int N=0;
	public void lp()
	{
		int dag_nodes=4;
		N=dag_nodes;
		int[] dag_from={1,1,1,3};
		int[] dag_to={2,3,4,4};
		int[] dag_weight={2,2,4,3};
		int from_node=1;
		int to_node=4;
		find_longest_path(dag_nodes,dag_from,dag_to,dag_weight,from_node,to_node);
	}
	
	
	private void find_longest_path(int dag_nodes, int[] dag_from, int[] dag_to, int[] dag_weight, int from_node, int to_node)
	{
		Graph graph=new Graph(dag_nodes);
		for(int i=0;i<dag_nodes;i++)
		{
			graph.addEdge(dag_from[i],dag_to[i],dag_weight[i]);
		}
		longestPath(graph,from_node,to_node);
    }
	private void topologicalSort(Graph graph,int vertex,Stack<Integer>stack,Set<Integer>visited)
	{
		visited.add(vertex);
		LinkedList<Node> n =graph.adj[vertex];
		Iterator<Node> itr = n.iterator();
		
		while(itr.hasNext())
		{
			Node v = itr.next();
			if(visited.contains(v.id-1))
				continue;
			topologicalSort(graph,v.id-1,stack,visited);
		}
		stack.push(vertex);
	}
	
	private void longestPath(Graph graph,int from_node, int to_node)
	{
		Set<Integer>visited = new HashSet<>();
		Stack<Integer>stack=new Stack<>();
		for(int i=0;i<N;i++)
		{
			if(visited.contains(i))
				continue;
			topologicalSort(graph,i,stack,visited);
		}
		int[]dist=new int[N];
		Arrays.fill(dist, Integer.MIN_VALUE);
		dist[0]=from_node;
		while(!stack.isEmpty())
		{
			int vertex = stack.pop();
			
			LinkedList<Node> n =graph.adj[vertex];
			for(int i=0;i<n.size();i++)
			{
				Node next = n.get(i);
				
				int sum = dist[vertex-1] + next.weight;
				if(sum > dist[next.id-1])
					dist[next.id-1]=sum;
				if(to_node == next.id)
					System.out.println(dist[next.id-1]);
			}
		}
		
	}

}
