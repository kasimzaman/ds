package Sorting;


import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * Build heap for first K elements : O(k)
 * The for the remaining n-k elements , compare the root of heap and extract min if required (OlogK) so => O(K + (N-K)LogK)
 */
public class TopK
{
	public void tk()
	{
		int[] iStream={1,32,4,3,66,700,98,10,3,2,4,-1,67,66,89,89,99,100,-1,3,4,5,6,7,-1,20,22};
		int K=3;
		topk(iStream,K);
	}
	private int[] topK(int[] iStream, int K) 
	{
		Queue<Integer>que = new PriorityQueue<>(K);
		for(int j=0;j<K;j++)
			que.add(iStream[j]);
			
		for(int i=K;i<iStream.length;i++)
		{
			if(iStream[i]==-1)
			{
				Iterator<Integer> itr = que.iterator();
				while(itr.hasNext())
					System.out.print(itr.next()+" ");
				System.out.println();
			}
			int n =que.peek();
			if(iStream[i] > n)
			{
				que.poll();
				que.add(iStream[i]);
			}
		}
		
		return null;
    }
	
	
	/**************************/
	
	private void topk(int[] arr, int K) 
	{
		Queue<Integer>pq=new PriorityQueue<>(K);
		
		for(int i=0;i<arr.length;i++)
		{	
			if(pq.size()<K)
				pq.offer(arr[i]);
			else if(pq.peek()<arr[i])
			{
				pq.remove();
				pq.offer(arr[i]);
			}
		}
		Iterator<Integer>itr=pq.iterator();
		while(itr.hasNext())
			System.out.println(itr.next().intValue());
	}
	

}
