package arrays;

public class ArrayProduct
{
	/**
	 * Problem: Given an array of integers, modify each position to contain product of all values in all other locations.
	 * Input = {1,2,3,4}
	 * Output= {24,12,8,6};
	 * 
	 * 1st Multiply from left
	 * Then Multiply from right
	 * Update cell with the multiplication of left*right
	 * 
	 * 			1	2	3	4
	 * In the new array the 1st element should contain all the product of all the elements from  left and right
	 * 1  x  24
	 * Similarly 2nd 
	 * 1 X 12
	 * 2 X 4
	 * 2 X 3
	 * 
	 * After first loop and copying multiplying from left we get
	 * 
	 * 		1	1	2	6
	 * 
	 * After first loop and copying multiplying from right we get
	 *  	24	12	8	6
	 * 
	 * Algo : O(n)
	 * Notes
	 * file:///Users/ali/Documents/workspace/KickStart/Notes/Arrays/ArrayProduct
	 * Youtube : https://www.youtube.com/watch?v=vB-81TB6GUc
	 */
	public void findProduct()
	{
		int[]a= {1,2,3,4};
		int []r =arrayProduct(a);
		for(int i=0;i<r.length;i++)
			System.out.println(r[i]);		
	}
	
	private int[] arrayProduct(int[]a)
	{	
		int[]r = new int[a.length];
		r[0]=1;
		int x=1;
		for(int i=1;i<a.length;i++)
		{
			r[i]= a[i-1]*r[i-1];
		}
		for(int i=a.length-1;i>0;i--)
		{
			x = x* a[i];
			r[i-1]= x*r[i-1];
		}
	    return r;
	}
	
}
