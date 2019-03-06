package graph;

/*
 	Detect cycle in Un-Directed graph.
 	Only keeping visited is not enough. You need a parent pointer as well. Because when iterating over the adjacent nodes you might visit the 
 	already visited node from which you just came. Now you have already marked that parent as visited , but this is not a cycle.
 	So in order to have a cycle the visited has to be true and the node should not be a parent. So pass the parent in every call.
 */
public class CycleUDG 
{
	public void dc()
	{
		boolean vivited[] = new boolean[5];
		Vertice v = new Vertice(0);
		Vertice v1 = new Vertice(1);
		v1.children.add(v);
		Vertice v2 = new Vertice(2);
		v.children.add(v2);
		v2.children.add(v);
		Vertice v3 = new Vertice(3);
		v.children.add(v3);
		Vertice v4 = new Vertice(4);
		v3.children.add(v4);
		isCyclicUtil(vivited,v,-1);
	}
	
	private boolean isCyclicUtil(boolean visited[], Vertice v,int parent)
	{
		visited[v.data]=true;
		for(Vertice child : v.children )
		{
			if(!visited[child.data])
			{
				if(isCyclicUtil(visited,child,v.data))
					return true;
			}
			else if(child.data != parent)
				return true;
		}
		return false;
	}
}


