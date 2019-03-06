package DP;

import java.util.HashMap;

/*
 * Rules of calculating cost of multiplication
 * 
 * A=[10,30]  B=[30,5]  C=[5,60]
 * 
 * AB => 10*COMMON(30)*5  and result matrix is first and the last[10,5]
 * 
 * AC => 30*5*60  => [30,60]
 * 
 * 
 * (AB)C => AB + C
 * A(BC) => A + BC	
 */

public class MartixMultiply {
static int calls=0;
	public void mm()
	{
		//int[] mtxSizes={84,35,34,60,22,60,56,47,47,28,5,45,34,59,72,37,55,99,18,54,57,97,73,94,62,83,60,29,77,60,62,74,97,68,36,88,99,19};
		int[] mtxSizes={77,72,100,82,8,15,5,66,28};
		//int[] mtxSizes={1,2,3,4};
		int[][]cache = new int[mtxSizes.length][mtxSizes.length];
		System.out.println(minCost(mtxSizes,1,mtxSizes.length-1,cache));
//		HashMap<String,ResultMatrix> map = new HashMap<>();
//		ResultMatrix r= minCost(mtxSizes,0,mtxSizes.length-1,map);
//		System.out.println("mincost: "+r.cost);
		System.out.println(calls);
		
	}
	
	private ResultMatrix minCost(int[] mtxSizes,int start,int len,HashMap<String,ResultMatrix>cache)
	{	calls++;
		int min=Integer.MAX_VALUE;
		ResultMatrix res=new ResultMatrix();
		if(start+1>=mtxSizes.length)
			return null;
		String key = Integer.toString(start)+"#"+Integer.toString(len);
		
		if(cache.containsKey(key))
			return cache.get(key);
		if(start==len)
		{	
			res.row=mtxSizes[start];res.col=mtxSizes[start+1];res.cost=0;
			return res;
		}
		//base case is 2 matrices
		if(start+1==len){
			int cost=0;
			if(len==mtxSizes.length-1){
				cost =0;
				res.row=mtxSizes[start];res.col=mtxSizes[len];res.cost=cost;
			}
			else{
				cost = mtxSizes[start]*mtxSizes[start+1]*mtxSizes[start+2];
				res.row=mtxSizes[start];res.col=mtxSizes[len+1];res.cost=cost;
			}
			return res;
		}
		
		for(int i=start;i<len;i+=1)
		{
			ResultMatrix r1 = minCost(mtxSizes,start,i,cache);
			ResultMatrix r2 = minCost(mtxSizes,i+1,len,cache);
			if(r1!=null && r2!=null){
				int cost = r1.row*r1.col*r2.col;
				res.cost=cost+r1.cost+r2.cost;
				res.row=r1.row;
				res.col=r2.col;
				if(min>res.cost)
					min=res.cost;
				res.cost=min;
			}
		}
		cache.put(Integer.toString(start)+"#"+Integer.toString(len), res);
		return res;
	}
	
	/*****************************************************************/
	//Time complexity of the naive recursive approach is exponential.
	//with caching Time Complexity: O(n^3)
	//ABCD = (A)(BCD), (AB)(CD) and (ABC)(D)
	private int minCost(int[]arr,int i,int j,int[][]cache)
	{
		if(i==j)
			return 0;
		int cost = Integer.MAX_VALUE;
		for(int k=i;k<j;k++)
		{
			int c = minCost(arr, i, k,cache) + minCost(arr, k+1, j,cache) + arr[i-1]*arr[k]*arr[j];
			if(c<cost)
				cost=c;
		}
		return cost;
	}
	
}
class ResultMatrix
{
	int row=0;
	int col=0;
	int cost=0;
}
