package recursion;

import java.util.ArrayList;
import java.util.List;

public class CountAllPathsGrid
{
	//Time Complexity. 2 branching. 2^height.
	//Height is n+m. so => 2^(n+m)
	public void capg()
	{
		int[][] grid={{1,2,3},
					  {4,8,0},
					  {7,8,9}};
		System.out.println(countGridPaths2(grid,0,0));
		//System.out.println(max(grid,0,0));
		int[][]cache=new int[grid.length][grid[0].length];
//		List<Integer>path=new ArrayList<>();
//		path.add(0);
//		//printPaths(grid,0,0,path);
		System.out.println(countGridPaths(grid,0,0,cache));
		System.out.println(bestPath(grid,0,0));
//		int[]a={3,4,72,2,41,11,21};
		
	}
	static int calls=0;
	
	private int countGridPaths(int[][]grid,int row,int col,int[][]cache)
	{calls++;
		if(row>=grid.length || col>=grid[0].length)
			return 0;
		if(cache[row][col]!=0)
			return cache[row][col];
		if(row==grid.length-1 && col==grid[0].length-1)
			return 1;
		int r=countGridPaths(grid, row+1, col,cache);
		int c=countGridPaths(grid, row, col+1,cache);
		int sum=r+c;
		cache[row][col]=sum;
		return sum;
	}
	private void printPaths(int[][]grid,int row,int col,List<Integer>path)
	{
		if(row>=grid.length || col>=grid[0].length)
			return;
		if(row==grid.length-1 && col==grid[0].length-1)
		{
			for(int e:path)
				System.out.print(e+" ");
			System.out.println();
			return;
		}
		if((row+1)<grid.length){
			path.add(grid[row+1][col]);
			printPaths(grid,row+1,col,path);
			path.remove(path.size()-1);
		}
		if((col+1)<grid[0].length){
			path.add(grid[row][col+1]);
			printPaths(grid,row,col+1,path);
			path.remove(path.size()-1);
		}
	}
	private int bestPath(int[][]grid,int row,int col)
	{
		if(row>=grid.length || col >=grid[0].length)
			return 0;
		if(row==grid.length-1 && col==grid[0].length-1)
			return grid[row][col];
		int res = grid[row][col] + Math.max(bestPath(grid,row+1,col),bestPath(grid,row,col+1));
		return res;
	}
	
	/***************************************************************/
	private int max(int[][]grid,int row,int col)
	{
		if(row>=grid.length || col>=grid.length)
			return 0;
		return grid[row][col] + Math.max(max(grid,row,col+1), max(grid,row+1,col));
	}
	private int countGridPaths2(int[][]grid,int row,int col)
	{
		if(row>=grid.length || col>=grid.length)
			return 0;
		if(row==grid.length-1 && col==grid.length-1)
			return 1;
		int l=countGridPaths2(grid,row+1,col);
		int r=countGridPaths2(grid,row,col+1);
		return l+r;
	}
	

}
