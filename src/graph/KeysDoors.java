package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
//https://ideone.com/CWwNwE
//O(N*M)
public class KeysDoors
{
	/*
	 			'+'		'B'		'.'		'.'		'.'
	 			
	 			'#'		'#'		'#'		'#'		'.'
	 			
	 			'#'		'#'		'b'		'#'		'.'
	 			
	 			'a'		'.'		'.'		'.'		'A'
	 			
	 			'#'		'#'		'@'		'#'		'#'
	 			
	 			
	 			Start:'@'
	 			End: '+'
	 			Land: '.'
	 			Water: '#'
	 			Key: 'b'
	 			Door: 'B'
	 			Can travel in any 4 directions but not diagonal. And cannot step into water.
	 			Solution: '@' -> '.' -> 'b' -> '.'-> '.' -> 'B' -> '.'-> '.' +
	 			
	 			
	 			Note: adding 32 to capital case character like 'A', converts to lower case 'a'
	 			
	 */
	public void kd()
	{	
		String[] grid={"+B...","####.","##b#.","a...A","##@##"};
		char[][]matrix=new char[grid.length][grid[0].length()];
		int startR=-1;
		int startC=-1;
		for(int i=0;i<grid.length;i++)
		{
			char[]arr=grid[i].toCharArray();
			for(int j=0;j<arr.length;j++){
				if(arr[j]=='@'){
					startR=i;
					startC=j;
				}
				matrix[i][j]=arr[j];
			}
		}
		find_shortest_path(matrix,startR,startC);
	}
	int R[]={-1,1,0,0};
	int C[]={0,0,-1,1};
	private int[][] find_shortest_path(char[][]grid,int row,int col) 
	{
		Set<Character>keys=new HashSet<>();
		int minsize=Integer.MAX_VALUE;
		int[][]palth=null;
		char ch=grid[row][col];
		boolean[][]visited=new boolean[grid.length][grid[0].length];
		Node node = new Node();
		node.r=row;
		node.c=col;
		node.ch=ch;
		Queue<Node>q=new LinkedList<>();
		node.path.add(new Point(row,col));
		q.add(node);
		visited[row][col]=true;
		while(!q.isEmpty())
		{
			Node n = q.poll();
			int r=n.r;
			int c=n.c;
			
			if('+'==n.ch)
			{
				if(minsize>n.path.size())
				{
					minsize=n.path.size();
					palth=new int[n.path.size()][2];
					for(int i=0;i<n.path.size();i++)
					{
						Point p =n.path.get(i);
						palth[i][0]=p.x;
						palth[i][1]=p.y;
						System.out.println(p.x+","+p.y);
					}
				}
			}
			Node vertex=null;
			int nr=0;
			int nc=0;
			for(int i=0;i<4;i++)
			{

				nr=0;nc=0;
				nr =r + R[i];
				nc =c + C[i];
				if(isValid(grid,nr,nc) && visited[nr][nc]==false)
				{
					char chr = grid[nr][nc];
					
					vertex = new Node();
					vertex.r=nr;
					vertex.c=nc;
					vertex.ch=chr;
					vertex.distance=n.distance+1;
					
					visited[nr][nc]=true;
					
					if(isKey(chr))
					{
						keys.add(chr);
						
					}
					else if(isDoor(chr))
					{
						if(!hasKey(chr,keys))
							continue;
					}
					//copy parent's path then this new one
					for(Point pt:n.path)
					{
						vertex.path.add(pt);
					}
					vertex.path.add(new Point(nr,nc));
					q.add(vertex);
				}
			}
			//if(isValid(grid,nr,nc))
			//	visited[nr][nc]=false;
		}
		
		return palth;
    }
	private boolean hasKey(char c,Set<Character>keys)
	{
		char key= (char)(c +32); 
		if(keys.contains(key))
			return true;
		else
			return false;
	}
	private boolean isDoor(char c)
	{
		if(c-'A'>=0 && c-'A'<=25)//is a key
			return true;
		else
			return false;
	}
	private boolean isKey(char c)
	{
		if(c-'a'>=0 && c-'a'<=25)//is a key
			return true;
		else
			return false;
	}
	private boolean isValid(char[][]grid,int r,int c)
	{
		if((r >= 0 && r < grid.length) && (c >= 0 && c < grid[0].length) && grid[r][c]!='#')
			return true;
		else
			return false;
	}
	class Node
	{
		int r;
		int c;
		char ch;
		int distance=0;
		
		List<Point> path = new ArrayList<>();
	}
	class Point
	{
		int x;
		int y;
		public Point(int x,int y)
		{
			this.x=x;
			this.y=y;
		}
	}
		

}
