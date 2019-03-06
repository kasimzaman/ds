package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//O(V+E)  Same as DFS
public class DetetctCycle
{
	public void dc()
	{
		Map<Integer,List<Integer>> graph = new HashMap<>();
		List<Integer> c= new ArrayList<>();
		c.add(1);
		c.add(2);
		graph.put(0, c);
		c= new ArrayList<>();
		c.add(2);
		graph.put(1, c);
		
		c= new ArrayList<>();
		c.add(0);
		c.add(3);
		graph.put(2, c);
		
		c= new ArrayList<>();
		c.add(3);
		graph.put(3, c);
		boolean[]visited = new boolean[graph.size()];
		boolean[]recStack = new boolean[graph.size()];
		
		if(hasCycle(graph,visited,0,recStack))
			System.out.println("true");
		else
			System.out.println("false");
		
	}
	//Keep storing the current vertices in the call stack. And if you see the same vertex in the same call stack , its an edge.
	//Don't forget to remove the vertex when the call stack rewinds. 
	//We recursively check for cycles for each tree. or sub graph.
	//Also mark the vertex visited, as we don't want to explore the sub trees we have already visited.
	private boolean hasCycle(Map<Integer,List<Integer>> graph,boolean[]visited,int vertex,boolean[]recStack)
	{
		if(visited[vertex])
			return false;	
		visited[vertex]=true;
		recStack[vertex]=true;
		List<Integer> children = graph.get(vertex);
		for(int i =0;i<children.size();i++)
		{
			int child = children.get(i);
			if(recStack[child]==true)
				return true;
			if(!visited[child] && hasCycle(graph,visited,child,recStack))
				return true;
		}
		recStack[vertex]=false;
		return false;
	}
	

}
