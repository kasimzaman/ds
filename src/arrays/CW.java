package arrays;

public class CW
{
	//Modify Binary search to find...
	//Using binary search find the first occurrence of an element if it is repeating.
	
	//Using binary search find the last occurrence of an element if it is repeating.
	
	//Find the largest element smaller than the given element; {40,90,100,120} k= 81  result =>40
	
	//the smallest element, larger than the given element in an array. {40,90,100,120} k= 81  result =>90

	//check for boundary like smallest number greater than or equal to  {40,90,100,120} k= 90  result =>90
	
	
	//find a number in a array that repeats more than 50% of the time.
	//note: regardless of where it starts , this number will cross the middle.
	
	//now what if the element is repeating 25% of times in the sorted array. what could be various starting positions?
	//just doing a linear search will take O(n) here by starting from 0th element. just check for repeating numbers by comparing i with i+1.
	//Note no matter where you start, you set will touch on of the following points: 1/4, 1/2, 3/4 (1st qtr, mid and last qtr). One of these three values will be the answer
	//so we have to check which one out of these three points is the answer?
	//we can use binary search to search our element.  So is K is the number of check points the run time is  O(klogn)
	//so if we have a number repeating 10%. we have 9 check points. so k=9
	
	
	//how many sub array are present in a array of sine n ?
	//basically start and end define a subarray. so what are all possible values of start and end in an array?
	//start can go frm 0 to n-1
	// end can go from start to n-1
	//so  if st=0 how many ends can we have => n
	//when start=1   we can have n-1 ends
	// so start can go from  0...n-1
	//and ends can go from n....n(n+1)/2
	//so we can have approx O(n^2) sub arrays
	
	//find a sub array with maximum sum in an array?
	//Brute force: generate all sub arrays, sum it up and find which one is the max.
	//  MaxSum=0
	//  max_start = -1
	//  max_end = -1
	//  for st:0 -> n-1
	//	   for end: st -> n-1
	//		   sum=0
	//		   for i=st -> end
	//			   sum+=arr[i]
	//          if (sum > maxsum)
	//             max_start= start
	//			   max_end = end
	//	  O(n^3)	
	/*
	 *   arr= {1,2,3}
	 *    first process [1,2]  then process [1,2,3] but [1,2] have already been processed. Because the sub array you process next will process one new element every
	 *     time. so keep a sum of the 2nd loop.
	 *      for st:0 -> n-1
	 *         sum =0
	*      for end: st -> n-1
	*        sum+=arr[end]
	*      O(n^2)
	*      can we do better than this ?
	*      
	*      MSS = Maximum Sum Sub Array  has 3 possibilities 
	*      MSS entirely in the left half  => recursive call into the left half
	*      MSS entirely in the right half  => recursive call into the right half
	*      MSS crosses the middle  =>  do a linear scan.
	*      -10, 15, 3, -2, 18
	*      so to check the mid case: start from mid and go left and accumulate the sum then go right. 
	*      left  max = 15 right max = 16
	*      Max = Max((st,mid-1),(mid+1,end), g(mid))  => returns start and end of maximum sum
	*      O(nLogn)  => logn levels and at each level we have n amount of work calculating the mid part.
	*      space complexity is the depth of the tree = > logn
	*      
	*      
	*      
	*      
	*/
	
	
	
	//Using binary search find the first occurrence of an element if it is repeating.
	//Take away :  you have to check for the return == -1. And only go left of right if the result is -1 other wise return the result.
	//because otherwise you will keep going in a while loop. and in the inner most if condition just return the result true or false; this will be your terminating condition
	public void cwclient()
	{
		int[] a = {1,2,3,4,5,7,7,8,8};
		System.out.println(a[findRepeatingNumberFirstOccourence(a, 0, a.length-1)]);
	}
	private int findRepeatingNumberFirstOccourence(int[]a, int low, int high)
	{
		int result=-1;
		if(low == high)
			return -1;
		while(low < high)
		{
			int mid = low + (high - low)/2;
			result = checkMid(a,mid,low,high);
			if(result==-1)
			{
				result = findRepeatingNumberFirstOccourence(a,low,mid-1);
				if(result==-1)
				{
					result = findRepeatingNumberFirstOccourence(a,mid+1,high);
						return result;
				}
				else
					return result;
			}
			else
				return result;
		}
		return -1;
	}

	private int checkMid(int[]a,int mid,int low, int high)
	{
		int temp=mid;
		if(low == high)
			return -1;
		while(temp>low && a[temp]==a[temp-1])
			temp--;
		if(temp<mid)
			return temp;
		temp=mid;
		while(temp<high && a[temp]==a[temp+1])
			temp++;
		if(temp>mid)
			return mid;
		return -1;
	}
}
