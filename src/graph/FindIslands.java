package graph;

public class FindIslands
{
	//Time complexity: O(ROW x COL)
	public void fi()
	{
		int mat[][] = { {1, 1, 0, 0, 0},
                		{0, 1, 0, 0, 1},
                		{1, 0, 0, 1, 1},
                		{0, 0, 0, 0, 0},
                		{1, 0, 1, 0, 1}};
		int count=0;
	    boolean visited[][]=new boolean[mat.length][mat.length];
		for(int row=0;row<mat.length;row++)
		{
			for(int col=0;col<mat.length;col++)
			{
				if(mat[row][col]==1 && !visited[row][col]){
					countIL(mat,row,col,visited);
					count++;
				}
			}
		}
		System.out.println(count);
	}
	int[]R={-1, -1, -1,  0, 0,  1, 1, 1};
	int[]C={-1,  0,  1, -1, 1, -1, 0, 1};
	public void countIL(int[][]mat,int row,int col,boolean visited[][])
	{
		if(!safe(mat,row,col,visited))
			return;
		visited[row][col]=true;
		for(int i=0;i<8;i++)
		{
			int r = row + R[i];
			int c = col + C[i];
			if(safe(mat,r,c,visited))
			{
				countIL(mat, r, c, visited);
			}
		}
	}
	public boolean safe(int[][]mat,int r,int c,boolean visited[][])
	{
		if(r>=0 && r<mat.length && c>=0 && c<mat.length && mat[r][c]==1 && !visited[r][c])
			return true;
		else
			return false;
	}
}
