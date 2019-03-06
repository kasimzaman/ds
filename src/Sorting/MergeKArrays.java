package Sorting;

import java.util.PriorityQueue;
import java.util.Queue;

/*
 *  Time Complexity : O(nk Logk).
 *  we have N*K elements. And Priority queue uses Min Heap, which requires Logk time to heapify.
 * 
 */
public class MergeKArrays
{
	boolean isAsc = false;
	public void mk()
	{
		int[][] iarray={{1,3,5,7},
						{2,4,6,8},
						{0,9,10,11}};
		int[] res = mergearrays(iarray,3,4);
		for(int n : res)
			System.out.print(n+" ");
	}
	
	private int[] mergearrays(int[][] iarray,int K, int N) 
	{
		
		int[]res=new int[N*K];
		if(iarray!=null && iarray.length>0)
			if(iarray[0][0]<iarray[0][N-1])
				isAsc=true;
		Queue<Node> que = new PriorityQueue<>();
		
		for(int i=0;i<K;i++)
		{		
			que.add(new Node(i,0,iarray[i][0]));
		}
		int index=0;
		while(!que.isEmpty())
		{
			Node elem = que.poll();
			res[index++]=elem.val;
			int r = elem.row;
			int c = elem.col+1;
			if(r>=0 && r<K && c>=0 && c<N)
				que.add(new Node(r,c,iarray[r][c]));
		}
		return res;
    }
	class Node implements Comparable<Node>
	{
		int row;
		int col;
		int val;
		Node(int row,int col,int val)
		{
			this.row=row;
			this.col=col;
			this.val=val;
		}
		@Override
		public int compareTo(Node n)
		{
			if(isAsc)
				return this.val - n.val;
			else
				return n.val - this.val;
		}
	}

}
