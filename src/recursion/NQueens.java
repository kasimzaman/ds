package recursion;

/*
 	Backtracing is Recursion as well. But recursion is more general. 
 	Backtracking is recursion with pruning. 
 	So pruning is putting  a guard in that examins before going down that state or not.
 	
 	Placing 8 queen requires examining 8! times.
 	so its n!
 */

public class NQueens
{
	boolean go=true;
	public void nq(int n)
	{
		nq2(n);
		/*
		 	0 0 1 0
		 	1 0 0 0
		 	0 0 0 1
		 	0 1 0 0
		 	
		 	0 1 0 0
		 	0 0 0 1
		 	1 0 0 0
		 	0 0 1 0
		 
		 */
		char[][]grid = new char[n][n];
		for(int i=0;i<grid.length;i++)
		{
			if(nqueen(grid,0,i))
			{
				for(int row=0;row<grid.length;row++){
					System.out.print("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
					System.out.println();
					System.out.print(" | ");
					for(int col=0;col<grid.length;col++){
						System.out.print(grid[row][col]+" | ");
					}
					System.out.println();
				}
				System.out.print("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
			}
			System.out.println();
			System.out.println();
			cleanGrid(grid);
		}
	}
	//O(N!) for finding all solution.
	//Complexity: The level of recursion is equal to the grid levels.so =>N
	private boolean nqueen(char[][]grid,int col,int start)
	{	
		if(col>grid.length-1)
			return true;
		for(int r=start;r<grid.length;r++)
		{
			if(isValid(grid, r, col))
			{
				grid[r][col]='Q';
				if(nqueen(grid,col+1,0))
					return true;
				grid[r][col]=' ';//if false then backtrack
			}
		}
		return false;
	}
	private boolean isValid(char[][]grid,int r,int c)
	{
		int col=0;
		int row=r;
		//checking left to right
		while(col<grid.length)
		{
			if(r==row && c==col){
				col++;
				continue;
			}
			if(grid[row][col]=='Q')
				return false;
			col++;
		}
		//checking top down. no need.I am putting the Q in new col every time
//		col=c;
//		row=0;
//		while(row<grid.length)
//		{
//			if(r==row && c==col){
//				row++;
//				continue;
//			}
//			if(grid[row][col]=='Q')
//				return false;
//			row++;
//		}
		//checking diagonal
		col=c;
		row=r;
		//[r-1][c+1]
		while(col<grid.length && row>=0)
		{
			if(r==row && c==col){
				col++;row--;
				continue;
			}
			if(grid[row][col]=='Q')
				return false;
			col++;row--;
		}//[r++][c++]
		col=c;row=r;
		while(col>=0 && row<grid.length)
		{
			if(r==row && c==col){
				col--;row++;
				continue;
			}
			if(grid[row][col]=='Q')
				return false;
			col--;row++;
		}
		//[r--][c--]
		col=c;row=r;
		while(col>=0 && row>=0)
		{
			if(r==row && c==col){
				col--;row--;
				continue;
			}
			if(grid[row][col]=='Q')
				return false;
			col--;row--;
		}
		col=c;row=r;
		while(col<grid.length && row<grid.length)
		{
			if(r==row && c==col){
				col++;row++;
				continue;
			}
			if(grid[row][col]=='Q')
				return false;
			col++;row++;
		}
		return true;
	}
	private void cleanGrid(char[][]grid)
	{
		for(int i=0;i<grid.length;i++)
		{
			for(int j=0;j<grid.length;j++)
			grid[i][j]=' ';
		}
	}
	
	/*****************************************REVISION**********************************************/
	/*
	 	Also the backtracking algorithm time complexity is exponential. 
	 	The major advantage of the backtracking algorithm is the ability to find and count all the possible solutions
	 	rather than just one while offering decent speed. In fact this is the reason it is so widely used. Also one can
	 	easily produce a parallel version of the backtracking algorithm increasing speed several times just by starting
	 	multiple threads with different starting positions of the first queens
	 	https://sites.google.com/site/nqueensolver/home/algorithms
	 	
	 	N(N-1)(N-2)=N! for all solutions
	 	exponential for 1 solution.
	 	
	 */
	private void nq2(int n)
	{
		int[][]grid = new int[n][n];
		int solution=0;
		for(int row=0;row<n;row++)
		{	
			if(board(grid,row,0))
				System.out.println(++solution);
		}
	}
	
	private boolean board(int[][]grid,int row,int col)
	{
		if(col>grid.length-1)
			return true;
		for(int i=row;i<grid.length;i++)
		{
			if(safe(grid,i,col))
			{
				grid[i][col]=1;
				if(board(grid,0,col+1))
					return true;
				grid[i][col]=0;
			}
		}
		return false;
	}
	//ONLY check what's on my left side. Right side is all empty.
	private boolean safe(int[][]grid,int row,int col)
	{
		int c=col-1;
		while(c>=0)
		{
			if(grid[row][c]==1)
				return false;
			c--;
		}
		c=col-1;
		int r=row-1;
		while(c>=0 && r>=0)
		{
			if(grid[r][c]==1)
				return false;
			c--;r--;
		}
		
		c=col-1;
		r=row+1;
		while(r<grid.length && c>=0 && r>=0)
		{
			if(grid[r][c]==1)
				return false;
			c--;r++;
		}
		return true;
	}
	
}

