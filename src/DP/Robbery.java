package DP;
//file:///Users/ali/Documents/workspace/KickStart/Notes/DP/Robbery/rob.png
public class Robbery 
{
	static int calls=0;
	public void robbery()
	{
		int[]a={6, 7, 1, 3, 8, 2, 4};
		//int[]a={6,1,2,7};
		
		int[]cache = new int[a.length];
		for(int i=0;i<cache.length;i++)
			cache[i]=Integer.MIN_VALUE;
		System.out.println(maxStolenValue(a,0,cache));
		System.out.println("calls:"+calls);
	}
	
	private int maxStolenValue(int[] arrHouseValues,int start,int[]cache)
	{calls++;
		int max1=0;
		int max2=0;
		if(start>=arrHouseValues.length)
			return 0;
		if(cache[start]!=Integer.MIN_VALUE)
			return cache[start];
		
		 max1 = arrHouseValues[start] + maxStolenValue(arrHouseValues,start+2,cache);
		 if((start+1)<arrHouseValues.length)
			 max2 = arrHouseValues[start+1] + maxStolenValue(arrHouseValues,start+3,cache);
		 cache[start]=Math.max(max1, max2);
		return Math.max(max1, max2);
    }
	
	/********************************************/
	

}
