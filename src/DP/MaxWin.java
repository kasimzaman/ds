package DP;

/*
 * Maximum of the first player is the minimum of the second player.
 * file:///Users/ali/Documents/workspace/KickStart/Notes/DP/CoinPlay/coinplay.png
 * 
 * Time O(2^n)
 */
public class MaxWin
{
	static int calls=0;
	public void maxWin()
	{
		int[] coins={73,220,192,205,25,187,51,214,11,147,102,212,67,107,11,65,116,41,73,96,135,142,156,162};
		//int[] coins={149,154,63,242,12,72,65};
		//int[] coins={8,15,3,7};
		//int[] coins={5,3,7,10};
		//int[] coins={1,2};
		int[][]cache=new int[coins.length+1][coins.length+1];
		for(int i=0;i<cache.length;i++)
		{
			for(int j=0;j<cache.length;j++)
				cache[i][j]=Integer.MIN_VALUE;
		}
		int[][][]cache2=new int[coins.length+1][coins.length+1][3];
		System.out.println(cp(coins,0,coins.length-1,1,cache2));
		//System.out.println(findMax(coins,0,coins.length-1,cache));
		System.out.println("calls:"+calls);
	}
	
	
	
	private int findMax(int[] coins,int start,int end,int[][]cache)
	{calls++;
		int max=0;
		if(start<=end)
		{
			//if(cache[start][end]!=Integer.MIN_VALUE)
			//	return cache[start][end];
			int v =coins[start] + Math.min(findMax(coins, start+2, end, cache),findMax(coins, start+1, end-1, cache));
			int v2 = coins[end] + Math.min(findMax(coins, start+1, end-1, cache),findMax(coins, start, end-2, cache));
			max = Math.max(v, v2);
			cache[start][end]=max;
		}
		return max;
	}
	
	private int cp(int[] coins,int start,int end,int player,int[][][]cache)
	{	calls++;
		int result=0;
		if(start>end)
			return 0;
		if(cache[start][end][player]!=0)
			return cache[start][end][player];
		if(player==1)
		{
			result =   Math.max(coins[start] + cp(coins, start+1,end,2,cache), coins[end]+cp(coins,start,end-1,2,cache));
		}
		else
		{
			result = Math.min(cp(coins,start+1,end,1,cache), cp(coins,start,end-1,1,cache));
		}
		cache[start][end][player]=result;
		return result;
	}
	
}