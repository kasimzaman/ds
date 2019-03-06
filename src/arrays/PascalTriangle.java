package arrays;


public class PascalTriangle
{
	/*	Pascal’s triangle is a triangular array of the binomial coefficients. Write a function that takes an
		integer value n as input and prints first n lines of the Pascal’s triangle. Following are the first 6
		rows of Pascal’s Triangle.
	  	O(N^2)
	 	If we take a closer at the triangle, we observe that every entry is sum of the two values above it.
	 	So we can create a 2D array that stores previously generated values. To generate a value in a line,
	  	we can use the previously stored values from array. 
	  	
	  	Solution: Need 2D ARRAY
	  	1st and last element equals 1
	  	Middle element is: Sum of the above line and same column and one column less.
	 */
	public void printPT()
	{
		printPT2(7);
	}
	private void printPT(int n)
	{
		int[][]list = new int[n][n];
		for(int line=0;line<n;line++)
		{
			for(int i=0;i<=line;i++)
			{
				if(i==0 || i==line)
					list[line][i]=1;
				else
				{
					list[line][i]=list[line-1][i]+list[line-1][i-1];
				}
			}
			for(int i=0;i<list.length;i++)
			{	
				System.out.print(" ");
				if(list[line][i]>0){
					System.out.print(list[line][i]);
				}
			}
			System.out.println();
		}		
	}
	
	/***********************************************REVISION*******************************************************/
	private void printPT2(int n)
	{
		int space=n;
		int[][]arr=new int[n][n];
		for(int line=0;line<n;line++)
		{
			for(int i=0;i<=line;i++)
			{
				if(i==0 || i==line)
					arr[line][i]=1;
				else
					arr[line][i]=arr[line-1][i-1]+arr[line-1][i];
			}
			for(int j=0;j<space;j++)
				System.out.print(" ");
			space--;
			for(int i=0;i<=line;i++)
			{
				System.out.print(arr[line][i]+" ");
			}
			System.out.println();
		}
	}
}








