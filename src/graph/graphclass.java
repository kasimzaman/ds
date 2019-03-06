package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class graphclass 
{
	private void dfs(Vertice v, Set<Vertice> visited )
	{	
	 	if(visited.contains(v))
	 		return;
	 	System.out.println(v.data);
	 	visited.add(v);
	 	for(Vertice vr: v.children)
	 		dfs(vr,visited);
	}
	
	private void bfs(Vertice v)
	{
		Set<Vertice> visited = new HashSet<>();
		Queue<Vertice> que = new LinkedList<>();
		que.add(v);
		while(!que.isEmpty())
		{
			Vertice vertice = que.poll();
			if(visited.contains(vertice))
				continue;
			visited.add(vertice);
			System.out.println(vertice.data);
			for(Vertice vr : vertice.children)
			{
				que.add(vr);
			}
		}
	}
	private void shortestPath(Vertice v, Vertice end)
	{
		Map<Vertice,Vertice> map = new HashMap<>();
		Queue<Vertice> que = new LinkedList<>();
		que.add(v);
		map.put(v, null);
		while(!que.isEmpty())
		{
			Vertice vertice = que.poll();
			if(v.data == end.data)
				break;
			for(Vertice vr : vertice.children)
			{
				if(map.containsKey(vr))
					continue;
				map.put(vr, v);
				que.add(vr);
			}
		}
		if(!map.containsKey(end))
			return;
		Vertice parent=map.get(end);
		ArrayList<Integer> result = new ArrayList<>();
		while(parent!=null)
		{
			result.add(parent.data);
			parent = map.get(parent);
		}	
	}
	
	private void clone(Vertice v)
	{
		Map<Vertice,Vertice>cloneMap = new HashMap<>();
		cloneHelper(v,cloneMap);
	}
	/*
	 * This will create all clone versions, only when it recurses back to Line 1.
	 * An easier but not great implementation would be to do without recursion and doing 2 passes. Has same time complexity though.
	 * First pass, just do dfs, and create all cloned nodes and store them in the map.
	 * Second pass do another traversal, but this time when you see an edge , create it between the cloned version.
	 */
	
	private Vertice cloneHelper(Vertice v,Map<Vertice,Vertice>cloneMap)
	{
		if(cloneMap.containsKey(v))
			return cloneMap.get(v);
		Vertice clone = new Vertice(v.data) ;
		cloneMap.put(v,clone );
		for(Vertice vx : v.children)
		{
			clone.children.add(cloneHelper(vx, cloneMap));//Line 1. Adding all those edges
		}
		return clone;
	}
	
}

class Vertice
{
	int data=0;
	ArrayList<Vertice> children = new ArrayList<>();
	public Vertice(int val)
	{
		this.data=val;
	}
}
