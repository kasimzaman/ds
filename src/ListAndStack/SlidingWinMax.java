package ListAndStack;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

//file:///Users/ali/Documents/workspace/KickStart/Notes/ListAndStack/slidingWindow.png
public class SlidingWinMax 
{
	public void slidingWindow()
	{	
		int w=3;
		int[] a={1,3,-1,-3,5,3,6,7};
		
		slidingWindow2(a,w);
	}
	private int[] slidingWindow(int[]arr,int w)
	{
		if(arr.length==0 || w==0)
            return arr;
		int[] B = new int[arr.length-w+1];
		int maxVal=Integer.MIN_VALUE;
		Queue<Integer> que = new PriorityQueue<>(w,Collections.reverseOrder());
		int j=0;
		int index=0;
		
		for(j=0;j<w;j++)
			que.add(arr[j]);
		
		maxVal = que.peek();
		B[index++]=maxVal;
		int low=0;
		for(int i=w;i<arr.length;i++)
		{
			que.remove(arr[low++]);
			que.add(arr[i]);
			maxVal = que.peek();
			B[index++]=maxVal;
		}
		return B;
	}
	
	/**********************************************REVISION**************************************************/
	//{1,3,-1,-3,5,3,6,7};
	//O(nLogw).
	private void slidingWindow2(int[]arr,int w)
	{
		Queue<Integer>que=new PriorityQueue<>(w,Collections.reverseOrder());//max queue
		int index=0;
		for(int i=0;i<arr.length;i++)
		{
			que.add(arr[i]);
			if(que.size()==w){
				System.out.println(que.peek());
				que.remove(arr[index++]);
			}
		}
	}

}
