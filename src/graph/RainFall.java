package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class RainFall
{
	int N=5;
	public void rf()
	{   
		int[][]map = {{1,0,2,5,8},
					  {2,3,4,7,9},
					  {3,5,7,8,9},
					  {1,2,5,4,3},
					  {3,3,5,2,1}};
		int[]parent=new int[N*N];
		Arrays.fill(parent, -1);
		boolean[][]visited=new boolean[N][N];
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
				if(!visited[i][j])
				findBasins(map,parent,i,j,visited);
		Map<Integer,Integer>basin = new HashMap<>();
		for(int i=0;i<parent.length;i++)
		{	
			int root = find(parent,i);
			if(basin.containsKey(root))
			{
				basin.put(root, basin.get(root)+1);
			}
			else
				basin.put(root,1);
		}
		Iterator itr = basin.entrySet().iterator();
		List<Integer>result=new ArrayList<>();
		while(itr.hasNext())
		{
			Map.Entry<Integer, Integer> me = (Map.Entry<Integer, Integer>)itr.next();
			result.add(me.getValue());
		}
		for(int i=0;i<result.size();i++)
			System.out.print(result.get(i)+" ");
	}
	
	int R[]={-1,1,0,0};
	int C[]={0,0,-1,1};
	private void findBasins(int[][]map,int[]parent,int row,int col,boolean[][]visited)
	{
		visited[row][col]=true;
		createSink(map,row,col,parent);
	}
	
	private void createSink(int[][]map,int row,int col,int[]parent)
	{
		int current = map[row][col];
		int min=current;
		int curIndex= row * N + col;
		int minIndex=-1;
		for(int i=0;i<4;i++)
		{
			int r = row + R[i];
			int c = col + C[i];
			if(isValid(r,c))
			{
				if(map[r][c]<min)
				{
					min=map[r][c];
					minIndex=r*N+c;
				}
			}
		}
		if(min!=current)//found smaller neighbor
			parent[curIndex]=minIndex;
	}
	
	private int find(int[]parent,int i)
	{	
		if(parent[i]==-1)
			return i;
		return find(parent,parent[i]);
	}
	private boolean isValid(int r,int c)
	{
		if((r>=0 && r <N) && (c>=0 && c<N))
			return true;
		else
			return false;
	}
}

