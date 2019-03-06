package DP;



public class DPTest 
{
	//- - - - -
	
	static int call=0;
	public void test()
	{
		int len=10;
		int[]cache=new int[len+1];
		for(int i=0;i<cache.length;i++)
		{
		for(int j=0;j<cache.length;j++)
			cache[i]=Integer.MAX_VALUE;
		
		}
		System.out.println(maxProdutRecursion(len,cache));
		
		System.out.println(call);
	}
	//Time Complexity: O(2^n).
	//dp  n^2
	public int maxProdutRecursion(int n,int[]cache)
	{	call++;
		if(cache[n]!=Integer.MAX_VALUE)
			return cache[n];
		if (n == 0 || n == 1)
			return 0;
		int max = 0;
		for (int i = 1; i < n; i++)
		{
			max = Math.max(max,  Math.max(i * (n - i), i * maxProdutRecursion(n - i,cache)));
		}
		cache[n]=max;
		return max;
	}
	
	
	
	private int numberOfPaths(int[][] a,int r, int c,int[][]cache) 
	{	
		if(r>=a.length || c>=a[0].length)
			return 0;
		if(r==a.length-1 && c==a[0].length-1)
			return 1;
		if(cache[r][c]!=Integer.MAX_VALUE)
			return cache[r][c];
		int p1=0;
		int p2=0;
		if((c+1)<=a[0].length-1 && a[r][c+1]!=0)
			p1+=numberOfPaths(a,r,c+1,cache);
		if((r+1)<=a.length-1 && a[r+1][c]!=0)
			p2+=numberOfPaths(a,r+1,c,cache);
		cache[r][c]=p1+p2;
		return p1+p2;
	}


}
