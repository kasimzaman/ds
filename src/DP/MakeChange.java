package DP;

import java.util.ArrayList;


/*
 * When dealing with extremities of recursion sometimes you will come across UNDEFINED cases, Its undefined because the problem 
 * does not break down for that case at all. Like here when the coins become negative. So in that case we choose convenient default
 * value. Whatever helps us achieve the problem.
 * 
 * without DP its n^2
 * with DP its nxm
 *           
 * file:///Users/ali/Documents/workspace/KickStart/Notes/DP/MakingChange/cp.png
 */

public class MakeChange 
{
	static int calls=0;
	public void makeChange()
	{
		int C=10;
		int[] intDenominations={6,5,3,2};
		int[][] cache = new int[intDenominations.length+1][C+1];
		for(int i=0;i<cache.length;i++)
		{
			for(int j=0;j<C+1;j++)
				cache[i][j]=-1;
		}
		
		System.out.println(gmc(C,intDenominations,0,0,cache));
		System.out.println(calls);
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		for(ArrayList<?> a : result){
			for(int i=0;i<a.size();i++){
				System.out.print(a.get(i));
				if((i+1)<a.size())
					System.out.print(",");
			}
			System.out.println("");
		}
		
		int[][] cache2 =  new int[intDenominations.length+1][C+1];
		for(int i=0;i<cache2.length;i++)
		{
			for(int j=0;j<C+1;j++)
				cache2[i][j]=-1;
		}
		int[] mem= new int[C+1];
		for(int i=0;i<mem.length;i++)
				mem[i]=-1;
		
		System.out.println(getMinCoins(C,intDenominations,0,0,cache));
		System.out.println("Calls:"+calls);
	}
	
	private int gmc(int sum, int[] d,int start,int count,int[][] cache)
	{calls++;
		int min=Integer.MAX_VALUE;
		if(sum==0)
			return count;
		else if(sum<0)
			return Integer.MAX_VALUE;
		if(start>=d.length)
			return Integer.MAX_VALUE;
		if(cache[start][sum]!=-1)
			return cache[start][sum];
		min =  Math.min(gmc(sum-d[start],d,start,count+1,cache), gmc(sum,d,start+1,count,cache));
		cache[start][sum]=min;
		return min;
	}
	private int getMinCoins(int sum, int[] intDenominations,int start,int count,int[][] cache)
	{calls++;
		int minSoFar=0;
		int min=Integer.MAX_VALUE;
		if(start>=intDenominations.length || sum <0)
			return Integer.MAX_VALUE;
		if(sum==0)
			return count;
		if(cache[start][sum]!=-1)
			return cache[start][sum];
		minSoFar = getMinCoins(sum-intDenominations[start],intDenominations,start,count+1,cache);
		
		if(minSoFar<min)
			min=minSoFar;
			
		minSoFar = getMinCoins(sum,intDenominations,start+1,count,cache);
		if(minSoFar<min)
			min=minSoFar;
		
		cache[start][sum]=min;
		return min;
	}
	//Because the problem says that some sequence of coins will make the sum that is why I can keep on adding 1 with every call.
	//keep adding 1 per call till sum reaches 0;
	@SuppressWarnings("unused")
	private int getMinCoins2(int sum, int[] intDenominations,int[] cache)
	{calls++;	
		int min=0;
		if(sum<0)
			return 0;
		if(sum==0){
			System.out.println("0");
			return 0;
		}
		if(cache[sum]!=-1)
			return cache[sum];
		for(int i=0;i<intDenominations.length;i++)
			min=1+getMinCoins2(sum-intDenominations[i],intDenominations,cache);
		cache[sum]=min;
		return min;
	}
	
	/****************************************************************/
	// 1,2,3  sum=4  1+3    2+2  
	private int getMinCoins(int sum, int[] coins,int count)
	{
		int min=Integer.MAX_VALUE;
		if(sum==0)
			return count;
		if(sum<0)
			return 0;
		
		for(int i=0;i<coins.length;i++)
		{
			int res = getMinCoins(sum-coins[i],coins,count+1);
			if(res!=0)
				min = Math.min(min, res);
		}
		return min;
	}
	private int getMinCoins3(int sum, int[] coins,int count)
	{
		int min=Integer.MAX_VALUE;
		if(sum==0)
			return count;
		if(sum<0)
			return 0;
		for(int i=0;i<coins.length;i++)
		{
			int c = getMinCoins3(sum-coins[i], coins, count+1);
			if(c!=0){
				if(min>c)
					min=c;
			}	
		}
		return min;
	}
	
	
		
}
