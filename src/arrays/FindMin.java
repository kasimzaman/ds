package arrays;

public class FindMin
{
	/**
	 *  We're given an array with sorted numbers. The array has been rotated an unknown number of
		times. We need to figure out the minimum number in such an array. What would be a fast
		method that uses only constant space?
		Solution: http://www.geeksforgeeks.org/find-minimum-element-in-a-sorted-and-rotated-array/
	 
	    file:///Users/ali/Documents/workspace/KickStart/Notes/Arrays/FindMinRotatedArray
	 */
	public void findMinimum()
	{
		int[]arr={3,4,5,6,7,8,9,1,2};
		System.out.println(findMinimun2(arr));
	}
	private int findMinimun(int[] arr)
	{
		int low = 0;
		int high = arr.length-1;
		
		if(arr[low]<arr[high])
			return arr[low];
		while(low <= high)
		{
			if(low==high)
				return arr[low];
			
			int mid = low+(high-low)/2;
			if(arr[mid]<arr[mid-1] && arr[mid]<arr[mid+1])
				return arr[mid];
			if(arr[mid]>arr[high])
				low = mid+1;
			else
				high= mid-1;
		}
		return -1;
	}
	
	/*********************************************REVISION***********************************************/
	//   1 2 3 4 5 6 7 8 9
	//   9 1 2 3 4 5 6 7 8  Right rotated once. If mid  < first, then min is on left side
	//   3 4 5 6 7 8 9 1 2  Left rotated once. If mid > last, then min is on right side
	private int findMinimun2(int[] arr)
	{
		if(arr[0]<arr[arr.length-1])
			return arr[0];
		
		int low=0;
		int high=arr.length-1;
		while(low<=high)
		{
			if(low==high)
				return arr[low];
			int mid = low + (high-low)/2;
			if(arr[mid]<arr[mid-1] && arr[mid]<arr[mid+1])
				return arr[mid];
			else if(arr[mid]>arr[high])
				low=mid+1;
			else
				high=mid-1;
		}
		return -1;
	}
}
