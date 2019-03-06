package arrays;

/**
 * 	Find the largest rectangular area possible in a given histogram where the largest rectangle can be
	made of a number of contiguous bars. For simplicity, assume that all bars have same width and the
	width is 1 unit.
  	Problem: Find largest rectangle in Histogram
  	file:///Users/ali/Documents/workspace/KickStart/Notes/Arrays/histogram/HistogramArea.png
  	Solution: http://www.geeksforgeeks.org/largest-rectangle-under-histogram/
  	O(NlogN)
 */
public class HistogramArea
{
	public void findMaxArea()
	{
		int[]a = {6,2,5,4,5,1,6};
		System.out.println(maxArea(a,0,a.length-1));
	}
	
	private int maxArea(int[]list,int start,int end)
	{
		int minPos=start;
		if(start>end)
			return 0;
		for(int i = start ; i<=end;i++)
		{
			if(list[i]<list[minPos])
				minPos = i;
		}
		return Math.max(list[minPos]*(end-start+1), Math.max(maxArea(list,start,minPos-1),maxArea(list,minPos+1,end)));
	}
	
	/*********************************************REVISION***********************************************/
	

}
