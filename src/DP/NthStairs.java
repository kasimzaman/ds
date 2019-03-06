package DP;
/**
 * file:///Users/ali/Documents/workspace/KickStart/Notes/DP/NthStair/stairs.png
 * Complexity is exponential without DP
 * With DP its O(mn)=> because it will at least calculate once for all mn combinations
 * So first tries all 1's till numsteps=0. return1 if it does
 * The stack unwinds, and tries 2 and checks if numsteps become 0 or not if yes return 1.
 * Stack keeps unwinding till it reaches the 1st call where the for loop started.
 * Now i becomes 2 => subtract 2 from stepssum and calls itself. Now the loop again starts from 0th. So now
 * all the steps are going to be checked against the stepsum.
 * 
 * 1so start: loop
 * 2take 1st value
 * 3subtract it
 * 4call itself with remaining balance and go back to 1.
 * If it was just 2 steps then its (n-1)+(n-2)
 * But here we have m steps,. So we run the loop m times in every call.
 *   
 *   so take 1st item and try the m items
 *   take the 2nd item and try the m items
 *   take the 3rd item and try the m items
 *                  .
 *   take the nth item and try the m items
 */

public class NthStairs 
{
	static int calls=0;
	public void nthstair()
	{
		int[] numSteps={1,2};
		int numStairs=15;
		System.out.println(allways(numSteps,numStairs));
		int way=0;
		int p = (int)Math.pow(numStairs, numSteps.length);
		int[]cache = new int[p];
		for(int i=0;i<cache.length;i++)
			cache[i]=Integer.MIN_VALUE;
		way+=countways(numStairs,numSteps,cache);
		
		System.out.println(way);
		System.out.println(calls);
	}	
	
	private int countways(int numStairs,int[] numSteps,int[]cache)
	{calls++;
		int w=0;
		if(numStairs==0)
			return 1;
		if(numStairs<0)
			return 0;
		if(cache[numStairs]!=Integer.MIN_VALUE)
			return cache[numStairs];
		for(int i=0;i<numSteps.length;i++)
		{
			w+= countways(numStairs-numSteps[i],numSteps,cache);
		}
		cache[numStairs]=w;
		return w;
	}
	
	/***************************************************/
	private int allways(int[]s,int n)
	{
		int total=0;
		for(int i=0;i<s.length;i++)
			total+=ways1(n,s[i]);
		return total;
	}
	private int ways1(int n,int s)
	{
		int total=0;
		if(n<0)
			return 0;
		if(n==0)
			return 1;
		
		total +=ways(n-s);
		return total;
	}
	//2^n
	//DP: steps * ways
	private int ways(int n)
	{
		int total=0;
		if(n<0)
			return 0;
		if(n==0)
			return 1;
		
		total +=ways(n-1);
		total +=ways(n-2);
		return total;
	}
}
