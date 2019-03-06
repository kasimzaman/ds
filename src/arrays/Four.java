package arrays;

import java.util.Random;

public class Four 
{
	/*
	 	[1, 2, 1, 2, 0, 0, 0]
	 	[2, 1, 2, 1, 0, 0, 0]
	 	[1, 2, 1, 2, 0, 0, 0]
	 	[2, 1, 2, 1, 0, 0, 0]
	 	[1, 2, 1, 2, 1, 0, 0]
	 */
	public void four()
	{
		int[][]grid=new int[5][7];
		play(grid,1);
	}
	private int pickColumn(int n)
	{
		Random rand = new Random();
		return rand.nextInt(n);
	}
	
	private boolean isDraw(int[][]grid)
	{
		int width=grid[0].length;
		for(int i=0;i<width;i++)
			if(grid[0][i]==0)
				return false;
		return true;
	}
	private void play(int[][]grid, int player)
	{
		if(player==1)
		{
			int col = pickColumn(grid[0].length);
			
			if(isDraw(grid)){
				System.out.println("Its a Draw");
				return;
			}
			while(grid[0][col]!=0)
				col = pickColumn(grid[0].length);
			if(drop(grid,col,player)){
				System.out.println("Player #"+player+" wins");
				return;
			}
			else
				play(grid,2);
			
		}
		if(player==2)
		{
			int col = pickColumn(grid[0].length);
			
			if(isDraw(grid)){
				System.out.println("Its a Draw");
				return;
			}
			if(grid[0][col]!=0)
				col++;
			if(drop(grid,col,player)){
				System.out.println("Player #"+player+" wins");
				return;
			}
			else
				play(grid,1);
		}
		
	}
	private boolean drop(int[][]grid,int col,int player)
	{
		int row=0;
		if(grid[row][col]!=0)
			return false;
		while(row<grid.length && (grid[row][col]==0))
			row++;
		row--;
		grid[row][col]=player;
		
		return check(grid,player,row,col);
		
	}
	private boolean check(int[][]grid,int player,int row,int col)
	{
		int width=grid[0].length;
		int height = grid.length;
		int r=row;
		int c=col;
		int count=0;
		while(c<width && grid[r][c++]==player)
			count++;
		if(count==4)
			return true;
		count=0;
		
		r=row;
		c=col;
		while(c>0 && grid[r][c--]==player)
			count++;
		if(count==4)
			return true;
		r=row;
		c=col;
		count=0;
		while(r>0 && grid[r--][c]==player)
			count++;
		if(count==4)
			return true;
		r=row;
		c=col;
		count=0;
		while(r<height && grid[r++][c]==player)
			count++;
		if(count==4)
			return true;
		
		
		r=row;  
		c=col;
		count=0;
		while((r<height && c<width) && grid[r++][c++]==player)
			count++;
		if(count==4)
			return true;
		
		
		r=row;  
		c=col;
		count=0;
		while((r>0 && c>0) && grid[r--][c--]==player)
			count++;
		if(count==4)
			return true;
		
		
		r=row;  
		c=col;
		count=0;
		while((r>0 && c<width) && grid[r--][c++]==player)
			count++;
		if(count==4)
			return true;
		
		r=row;  
		c=col;
		count=0;
		while((r<height && c>0) && grid[r++][c--]==player)
			count++;
		if(count==4)
			return true;
		
		return false;
		
	}

}
