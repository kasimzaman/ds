package arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
    Given a set of integers, find a contiguous subset whose sum is zero. There can be duplicate numbers in the input.
	Input: Integer array e.g. 5,1,2,-3,7,-4
	output: A subset that sums to zero.
	e.g. 1,2,-3 OR -3,7,-4
	* If there are no such subsets, then print nothing
	* If there are multiple such subsets, then print any one
	* If a matching subset is a subset of a larger matching subset, then print either one
	* If there is a number '0' in the array, then it counts as a valid answer subarray. 
	* What would be the complexity of the solution, if we were to print all subsets that sum to zero (instead of just one)?
	  Solution: http://www.geeksforgeeks.org/find-if-there-is-a-subarray-with-0-sum/ (This is a variation of the Maximum Subarray problem)
	  
	   O(n) 
	   
	   Keep storing the current sum in hasmap with indexes.
	   If the current sum has been seen before then there is a 0 sum array.
 */
public class SumZero
{
	private static ArrayList<String> results=new ArrayList<>();
	public void sumZero()
	{	
		int[]a = {5,5,5,-15,-3,7,-4};
		//badImpl(a);
		List<Integer>res =printZeroSumSubarray(a);
		for(int i : res)
			System.out.print(i+" ");
	}

	//keep adding prefixes, and see if any prefix sum repeats. If it does then get the stored index and your sub-array starts one index ahead of it this 
	//your current i;
    // a) Current element is 0
    // b) sum of elements from 0 to i is 0
    // c) sum is already present in hash map
	private static List<Integer> printZeroSumSubarray(int arr[])
    {
		int sum=0;
		int max=Integer.MIN_VALUE;
		Map<Integer,Integer>map = new HashMap<>();
        List<Integer>res=new ArrayList<>();
        for(int i=0;i<arr.length;i++)
        {
        	sum+=arr[i];
        	if(arr[i]==0)
        		res.add(arr[i]);
        	else if(sum==0)
        	{
        		for(int h=0;h<=i;h++)
        			res.add(arr[h]);
        	}
        	else if(map.containsKey(sum))
        	{
        		int n = map.get(sum);
        		int diff = i-(n+1);
        		if(max<diff)
        		{
        			res.clear();
        			for(int j=n+1;j<=i;j++)
        				res.add(arr[j]);
        		}
        	}
        	else
        		map.put(sum, i);
        }
        return res;
    } 
	//n^2
	private void badImpl(int arr[])
	{
		//generate all sub arrays
		for(int i=0;i<arr.length;i++)
		{
			System.out.print(arr[i]+" ");
			for(int j=i+1;j<arr.length;j++)
			{
				System.out.print(arr[j]+" ");
			}
			System.out.println();
		}
	}
	
	/***********************************************REVISION*******************************************************/
	
}
