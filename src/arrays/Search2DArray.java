package arrays;

/*
  	You're given a 2d array (N x M) where all the numbers (integers) in the array are in increasing
	order from left to right and top to bottom. You're also given a target number, to be searched
	inside the array. What is the best way to search and determine if a target number is in the array?

	Solution: take inspiration from http://www.geeksforgeeks.org/search-in-row-wise-and-column-wise-sorted-matrix/

	(Notice that this can be a very deceptive problem in a good way. Solution seems difficult, but it's actually quite simple.)
	
	O(m + n).
 */
public class Search2DArray
{
	public void search2d()
	{
		int[][] a = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
		System.out.println(search2D(a,11));
	}
	
	private boolean search2D(int[][]a, int k)
	{
		if(a.length >0)
		{
			int width = a[0].length-1;
			int height = a.length-1;
			for(int row = 0; row <= height; row++)
			{
				for(int col = width; col >= 0; col--)
				{
					if(a[row][col] == k)
						return true;
					else if(a[row][col] < k)
						break;
				}
			}
		}
		return false;
	}
	/***********************************************REVISION*******************************************************/
	
}
