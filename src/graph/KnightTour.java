package graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
//file:///Users/ali/Documents/workspace/KickStart/Notes/Graphs/KnightTour/Screen%20Shot%202017-12-01%20at%2012.17.34%20PM.png

/*
 	DO BFS
 	USE NODES
 	HAVE DISTANCE IN NODE
 
 */
public class KnightTour
{
	//In worst case, below code visits all cells of board, making worst-case time complexity as O(N^2)
	public void kt()
	{
		int rows=8;
		int cols=8;
		int startx=0;
		int starty=0;
		int endx=7;
		int endy=7;
		
		
		System.out.println(minMoves(rows, cols, startx, starty, endx, endy));
		System.out.println(calls);
	}
	static int calls=0;
	int R[]={2,-2, 2,-2, 1,-1, 1,-1};
	int C[]={1, 1,-1,-1, 2, 2,-2,-2};
	
	class GNode
	{
		int x;
		int y;
		int dist=-1;
		public GNode(int x,int y,int d)
		{
			this.x=x;
			this.y=y;
			this.dist=d;
		}
	}
	private int minMoves(int rows, int cols, int startx, int starty, int endx, int endy)
	{
		if(!isValidCord(startx, starty, rows, cols) || !isValidCord(endx, endy, rows, cols))
			return -1;
		Map<GNode,Boolean>visited=new HashMap<>();
		Queue<GNode> que = new LinkedList<>();
		GNode start=new GNode(startx,starty,0);
		que.add(start);
		
		while(!que.isEmpty())
		{
			GNode node = que.poll();
			int x=node.x;
			int y=node.y;
			int dist=node.dist;
			if(x==endx && y==endy)
				return dist;
			if(!visited.containsKey(node))
			{
				visited.put(start, true);
				for(int i=0;i<8;i++)
				{
					int newR = x + R[i];
					int newC = y + C[i];
					if(isValidCord(newR,newC,rows,cols))
					{	
						GNode n = new GNode(newR, newC, dist+1);
						que.add(n);
					}
				}
			}
		}
		return -1;
	}
	
	
	//This is doing DFS. TRYING out all the possible paths.
	//Expensive and incorrect approach
	private int minimumMoves(int rows, int cols, int startx, int starty, int endx, int endy,boolean[][]visited,int sum,int[][]cache) 
	{
		if(!isValidCord(startx, starty, rows, cols) || !isValidCord(endx, endy, rows, cols))
			return Integer.MAX_VALUE;
		if(cache[startx][starty]!=-1)
			return cache[startx][starty];
		if(startx==endx && starty==endy)
			return sum;
		visited[startx][starty]=true;
		int shortestPath=Integer.MAX_VALUE;
		
		for(int i=0;i<8;i++)
		{
			int newR = startx + R[i];
			int newC = starty + C[i];
			if(isValidCord(newR,newC,rows,cols) && visited[newR][newC]!=true)
			{
				int len = minimumMoves(rows,cols,newR,newC,endx,endy,visited,sum+1,cache);
					
				if(len !=Integer.MAX_VALUE){
					if(shortestPath>len)
						shortestPath=len;
				}
			}
		}
		visited[startx][starty]=false;
		cache[startx][starty]=shortestPath;
		return shortestPath;
    }
	private boolean isValidCord(int r,int c,int rows,int cols)
	{
		if(r>=0 && r<rows && c>=0 && c<cols)
			return true;
		else
			return false;
	}

}
