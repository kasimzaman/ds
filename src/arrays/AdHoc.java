package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class AdHoc
{
	public void adhoc()
	{
		int ar1[] = {1, 2,3,4,5};
        int ar2[] = {6,7,8,9,10} ;
        getMedian(ar1,ar2);
	}
	
	/***********************************DUPLICATE IN ARRAY************************************/
	//negate indexes
	//O(N) But array modified
	//But it will not work for [10000,1111]
	public void findDuplicate(int[]arr,int n)
	{	
		for(int i=0;i<n;i++)
		{
			int index = Math.abs(arr[i]);
			if(arr[index]>=0)
				arr[index]=arr[index]*-1;
			else
				System.out.println(arr[index]*-1);
		}
	}
	//Keep adding n. No element can be bigger than n
	//O(N) array modified. May cause overflows if n is large
	public void findDuplicate2(int[]a,int n)
	{
		for(int i=0;i<n;i++)
		{
			int index = a[i];
			if(index>n)
			{
				index=index-n;
			}
			if(a[index]>n)
				System.out.println(a[index]-n);
			else
				a[index]=a[index]+n;
		}
	}
	
	/***************************************MISSING NUMBER********************************/
	//Using BitSet: O(N), O(N)
	public void findMissingNumbert(int[]a,int n)
	{
		BitSet bitset = new BitSet(n);
		int missingCount = n-a.length;
		for(int i=0;i<a.length;i++)
		{
			bitset.set(a[i]-1);
		}
		for(int i=0;i<missingCount;i++)
		{
			int index = bitset.nextClearBit(i);
			System.out.println(index+1);
		}
	}
	//XOR: O(N) No over flow and space o(n)
	public void findMissingNumber2(int[]a,int n)
	{
		int X=1;
		for(int i=2;i<=n;i++)
		{
			X=X^i;
		}
		int Y=a[0];
		for(int i=1;i<a.length;i++)
		{
			Y=Y^a[i];
		}
		System.out.println(X^Y);
	}
	
	/*************************************IS PALINDROME**********************************/
	//Dammit I'm mad.
	public boolean isPalendrome(char[]str,int start,int end)
	{
		if(start>=end)
			return true;
		while(start<end && !validChar(str[start]))
		{
			start++;
		}
		while(end>start && !validChar(str[end]))
		{
			end--;
		}
		if(str[start]==str[end])
			return isPalendrome(str,start+1,end-1);
		else
			return false;
	}
	public boolean validChar(char c)
	{
		if(c>='a' && c<='z')
			return true;
		else
			return false;
	}
		
	/*************************************MAX SUM SUB ARRAY**********************************/
	//O(N)
	//-2,-3,4,-1,-2,1,5,-3
	public void maxSumSubArray(int[]a)
	{
		int currentMax=a[0];
		int maxSoFar=a[0];
		for(int i=1;i<a.length;i++)
		{
			currentMax = Math.max(a[i], currentMax+a[i]);
			maxSoFar = Math.max(maxSoFar, currentMax);
		}
		System.out.println(maxSoFar);
	}
	
	/*************************************MAX ELEMENT FROM EACH SUBARRAY**********************************/
	public void maxElementFromKSubArrays(int[]a,int k) //each sub array is of size K.
	{
		int currentmax=a[0];
		int maxSoFar=a[0];
		int size=k-1;
		for(int i=1;i<a.length;i++)
		{
			currentmax = Math.max(a[i], currentmax + a[i]);
			maxSoFar = Math.max(maxSoFar, currentmax);
			if(size==0){
				System.out.println(maxSoFar);
				size=k;
			}else
				size--;
		}
	}
	/*************************************LARGEST AND SMALLEST IN ARRAY**********************************/
	public void MaximumMinimumArray(int[]a)
	{
		int max=Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for(int i=0;i<a.length;i++)
		{
			if(a[i]>max)
				max=a[i];
			else if(a[i]<min)
				min=a[i];
		}
	}
	/*************************************2 SUM**********************************/
	public static void printPairsUsingTwoPointers(int[] numbers, int k)
	{
        if(numbers.length < 2)
        {
            return;
        }
        Arrays.sort(numbers);
        
        int left = 0; int right = numbers.length -1;
        while(left < right)
        {
            int sum = numbers[left] + numbers[right];
            if(sum == k)
            {
                System.out.printf("(%d, %d) %n", numbers[left], numbers[right]);
                left = left + 1;
                right = right -1;
                
            }else if(sum < k){
                left = left +1;
                
            }else if (sum > k) {
                right = right -1;
            }
        }
	}
	/*************************************INTERSECTION OF 2 SORTED ARRAYS**********************************/
       /*
        *Another approach that is useful when difference between sizes of two given arrays is significant.
		 The idea is to iterate through the shorter array and do a binary search for every element of short array in big array
 		 (note that arrays are sorted). Time complexity of this solution is O(min(mLogn, nLogm)). This solution works better than the above
  		 approach when ratio of larger length to smaller is more than logarithmic order. 
        *
        */
	//O(M+N)
	static void printIntersection(int arr1[], int arr2[], int m, int n)
    {
      int i = 0, j = 0;
      while (i < m && j < n)
      {
        if (arr1[i] < arr2[j])
          i++;
        else if (arr2[j] < arr1[i])
          j++;
        else
        {
          System.out.print(arr2[j++]+" ");
          j++;
          i++;
        }
      }
    }
	
	/*************************************EVERY ELEMENT REPEATED EXCEPT I**********************************/
	private void repeatedButOne(int[]a)
	{
		int x = a[0];
		for(int i=1;i<a.length;i++)
			x=x^a[i];
		System.out.println(x);
	}
	/*************************************K-TH SMALLEST ELEMENT **********************************/
	//The worst case time complexity of this method is O(n2), but it works in O(n) on average.
	public void kthSmallest(int[]a,int start, int end, int k)
	{
		if(start>end)
			return;
		int pivot = partition(a,start,end);
		if(k==pivot+1)
			System.out.println(a[pivot]);
		else if(k>pivot+1)
			kthSmallest(a, pivot+1,end, k);
		else
			kthSmallest(a, start, pivot-1, k);
			
	}
	//1, 2, 3, 9, 4
	public int partition(int[]a,int low,int high)
	{	
		int pivot = a[low];
		int left=low+1;
		int right=high;
		while(left<right)
		{
			while(a[left]<pivot)
				left++;
			while(a[right]>pivot)
				right--;
			if(left<right){
				swap(a,left,right);
				left++;
				right--;
			}
		}
		swap(a,low,right);
		return right;
	}
	public void swap(int[]a,int left,int right)
	{
		int temp = a[left];
		a[left]=a[right];
		a[right]=temp;
	}
	/*************************************K-TH LARGEST ELEMENT **********************************/
	public void kthLargest(int[]a,int start, int end, int k)
	{
		if(start>end)
			return;
		int pivot = partition2(a,start,end);
		if(k==pivot+1)
			System.out.println(a[a.length-k]);
		else if(k>pivot+1)
			kthSmallest(a, pivot+1,end, k);
		else
			kthSmallest(a, start, pivot-1, k);
	}
	public int partition2(int[]a,int low,int high)
	{	
		int middle=(low+high)/2;
		int pivot = a[middle];
		int left=low;
		int right=high;
		while(left<right)
		{
			while(left<a.length && a[left]>pivot)
				left++;
			while(right>=0 && a[right]<pivot)
				right--;
			if(left<right){
				swap(a,left,right);
				left++;
				right--;
			}
		}
		swap(a,middle,right);
		return right;
	}
	
	/*************************************INTERSECTION OF 3 ARRAYS **********************************/
	//O(n1 + n2 + n3)
	//{1, 5, 10, 20, 40, 80}
	//{6, 7, 20, 80, 100}
	//{3, 4, 15, 20, 30, 70, 80, 120}
	private void intersection3(int[]a,int[]b,int[]c)
	{
		int i=0;
		int j=0;
		int k=0;
		while(i<a.length && j<b.length && k<c.length)
		{
			if(a[i]==b[j] && b[j]==c[k]){
				System.out.println(c[k]);
				i++;j++;k++;
			}
			else if(a[i]<b[j])
				i++;
			else if(b[j]<c[k])
				j++;
			else
				k++;
		}
	}
	
	/*************************************FIND FIRST REPEATING ELEMENT **********************************/
	//{1,2,4,4,5,6,6};  6
	//first sort O(NLOGN)
	//Then do binary search logn
	//assuming its sorted
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
		int i=mid-1;
		if(low == high)
			return -1;
		while((i)>low && a[mid]==a[i])
			i--;
		if(i<mid-1)
			return i+1;
		
		if((mid+1)<a.length && a[mid]==a[mid+1])
			return mid;
		
		return -1;
	}
	
	/*************************************FIND TOP 2 NUMBERS **********************************/
	// 4 1 6 2
	private void findTop2(int[]a)
	{
		int max1=Integer.MIN_VALUE;
		int max2=Integer.MIN_VALUE;
		for(int i=0;i<a.length;i++)
		{
			if(max1<a[i])
			{
				max2=max1;
				max1=a[i];
			}
			else if(max2<a[i])
				max2=a[i];
		}
		System.out.println("top1 :"+max1);
		System.out.println("top2 :"+max2);
	}
	
	/*************************************SMALLEST INT AS SUM **********************************/
	// How to find the smallest positive integer value that cannot be represented as sum of any subset of a given array?
	//1, 1, 1, 1 => 5
	private void smallestNotSum(int[]a)
	{
		int res = 1; 
        for (int i = 0; i < a.length && a[i] <= res; i++)
            res = res + a[i];
        System.out.println(res);
	}
	
	/*************************************REMOVE ELEMENT INPLACE **********************************/
	//-5, -2, 5, 2, 4, 7, 1, 8, 0, -8  remove 2
	static int deleteElement(int arr[], int n, int x)
    {
        if (arr[n-1] == x)
            return (n-1);
        int prev = arr[n-1], i;
        for (i=n-2; i>=0 && arr[i]!=x; i--)
        {
            int curr = arr[i];
            arr[i] = prev;
            prev = curr;
        }
        if (i < 0)
            return 0;
        arr[i] = prev;
        return (n-1);
    }
	
	
	/*************************************MERGE 2 SORTED ARRAYS USING O(1) EXTRA SPACE **********************************/
	//O(m*n).
	//1, 5, 9, 10, 15, 20
	//2, 3, 8, 13
	//output : 1, 2, 3, 5, 8, 9
	//         10, 13, 15, 20
	//algo. if a is less than b then swap.
	// while putting the swapped value in b, check if its larger than its next. if it is then keep swapping till its not.
	private void merge2(int[]a,int[]b)
	{
		int i=0;
		int j=0;
		while(i<a.length && j<b.length)
		{
			if(a[i]<b[j])
				i++;
			else if(a[i]>b[j])
			{
				int temp = a[i];
				a[i]=b[j];
				b[j]=temp;
				i++;
				int d=j;
				while(d+1<b.length && b[d]>b[d+1])
				{
					temp = b[d];
					b[d]=b[d+1];
					b[d+1]=temp;
					d++;
				}
			}
			else
			{
				i++;
				j++;
			}
		}
	}
	/*************************************MAXIMUM PRODUCT SUBARAY **********************************/
	//O(N)
	//maximum product can also be obtained by minimum (negative) product ending with the previous element multiplied by this element//
	//need to maintain the minimum negative product
	private void maxProductSA(int[]A)
	{
		int maxherepre = A[0];
	    int minherepre = A[0];
	    int maxsofar = A[0];
	    int maxhere, minhere;
	    
	    for (int i = 1; i < A.length; i++) {
	        maxhere = Math.max(Math.max(maxherepre * A[i], minherepre * A[i]), A[i]);
	        minhere = Math.min(Math.min(maxherepre * A[i], minherepre * A[i]), A[i]);
	        maxsofar = Math.max(maxhere, maxsofar);
	        maxherepre = maxhere;
	        minherepre = minhere;
	    }
	    
		System.out.println("Max: "+maxsofar );
	}
	/*************************************FIND LONGEST CONSECUTIVE SEQUENCE**********************************/
	// [100, 4, 200, 1, 3, 2] => [1,2,3,4]
	//O(N) O(N)
	private void findLongestConseqSubseq(int[]a)
	{
		int len=0;
		int count=0;
		int[]res=new int[a.length];
		Set<Integer>set=new HashSet<>();
		for(int i=0;i<a.length;i++)
			set.add(a[i]);
		for(int i=0;i<a.length;i++)
		{
			int num = a[i];
			int j=num;
			while(set.contains(j)){
				j++;
			}
			if(len<count)
				len=count;
			count=0;
		}
	}
	
	//*******************************************Remove duplicates in array in-place***************************************************
	 
	public static void removeDuplicated()
	{
		int[] n ={1,2,2,2,3};
		
        int id = 1;
        for(int i = 1; i < n.length; ++i) 
            if(n[i] != n[i-1])
            	n[id++] = n[i];
        System.out.println(id);
	}
	
	//*******************************************MEDIAN OF 2 SORTED ARRAYS*****************************************
	//int ar1[] = {1, 12, 15, 26, 38};
   // int ar2[] = {2, 13, 17, 30, 45};
	//simple method: merge 2 arrays of size 2n. keep track of count while merging. when count is n for 2n elements, its the median
	// O(n)
	private void getMedian(int ar1[], int ar2[])
	{
		int i = 0;  
        int j = 0; 
        int count;
        int m1 = -1, m2 = -1;
        int n = ar1.length;
        for (count = 0; count <= n; count++)
        {
            if (i == n)
            {
                m1 = m2;
                m2 = ar2[0];
                break;
            }
            else if (j == n)
            {
                m1 = m2;
                m2 = ar1[0];
                break;
            }
            if (ar1[i] < ar2[j])
            {   
                m1 = m2;  
                m2 = ar1[i];
                i++;
            }
            else
            {
                m1 = m2;  
                m2 = ar2[j];
                j++;
            }
        }
       System.out.println((m1 + m2)/2);
		
	}
	/*Better O(LOGN)
	 	
	*/
	private int getMedian2(int ar1[], int ar2[],int s1,int e1,int s2,int e2,int n)
	{
		int m1 = median(ar1);
		int m2 = median(ar2);
		
		if(n<=0)
			return -1;
		if (n == 1)
	        return (ar1[0] + ar2[0])/2;
	    if (n == 2)
	        return (Math.max(ar1[0], ar2[0]) + Math.min(ar1[1], ar2[1])) / 2;
			
		
		if(m1 < m2)
		{
			return getMedian2(ar1, ar2,n/2,e1,s2,n/2,n/2);
		}
		else
			return getMedian2(ar1, ar2,s1,n/2,n/2,e2,n/2);
		
	}
	private int median(int[]a)
	{
		int len=a.length;
		if(len%2==0)
			return (a[len/2-1]+a[len/2])/2;
		else
			return a[len/2];
	}
	//*******************************************SORT COLORS*****************************************
	public static void sortColors(){
		int []nums = {1,2,1,0,2,1,0,1,0,1,2};
		int red=0,white=0,blue=0;
		for(int i=0;i!=nums.length;i++){//first, count their numbers
			if(nums[i]==0){
				red++;
			}else if(nums[i]==1){
				white++;
			}else{
				blue++;
			}
		}
		for(int i=0;i!=nums.length;i++){//second, rewrite the original array
			if(i<red){
				nums[i]=0;
			}else if(i<white+red){
				nums[i]=1;
			}else{
				nums[i]=2;
			}
		}
	}
	
	/****************************************SINGLE NUMBER 3*******************************************/
	/*
	 	Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice.
	 	Find the two elements that appear only once.
				For example:
		Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
	 */
	public void singleNumber(int[] nums)
	{
		Set<Integer>set=new HashSet<>();
		for(int i=0;i<nums.length;i++)
		{
			if(set.contains(nums[i]))
				set.remove(nums[i]);
			else
				set.add(nums[i]);
		}
		Iterator<Integer>itr = set.iterator();
		while(itr.hasNext())
			System.out.println(itr.next().intValue());
    }
	
	/**************************************MIN MOVES****************************************/
	//o(nlogn)
	public int minMoves2(int[] nums,int start,int moves)
	{
		Arrays.sort(nums);
        int sum = 0;
        for (int num : nums)
        {
            sum += Math.abs(nums[nums.length / 2] - num);
        }
        return sum;
    }
	
	/**************************************TOP K FREQUENT ELEMENTS****************************************/
	//Given [1,1,1,2,2,3] and k = 2, return [1,2]
	/*
	 	Put in map
	 	Get max count from map
	 	Create array of size max
	 	iterate map and get count of each entry. put array[count]=key
	 	iterate in reverse till k.
	 */
	public void topKFrequent(int[] nums, int k) 
	{
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	    for(int num: nums){
	        if(map.containsKey(num)){
	            map.put(num, map.get(num)+1);
	        }else{
	            map.put(num, 1);
	        }
	    }
	    int max=Integer.MIN_VALUE;
	    for(Entry<Integer,Integer>entry : map.entrySet())
	    {
	    	max = Math.max(max, entry.getValue());
	    }
	    ArrayList<Integer>[] list = new ArrayList[max+1];
	    for(int i=1; i<=max; i++){
	        list[i]=new ArrayList<Integer>();
	    }
	    for(Entry<Integer,Integer>entry : map.entrySet())
	    {
	    	int count = entry.getValue();
	        int number = entry.getKey();
	        list[count].add(number);
	    }
	    List<Integer> result = new ArrayList<Integer>();
	    
	    //add most frequent numbers to result
	    for(int j=max; j>=1; j--)
	    {
	        if(list[j].size()>0)
	        {
	            for(int a: list[j])
	            {
	                result.add(a);
	            }
	        }
	        if(result.size()==k)
	            break;
	    }
    }
	/**************************************ROTATE ARRAY D TIMES**********************************************/
	//Time complexity: O(n*d)
	void leftRotate(int arr[], int d, int n) 
    {
        int i;
        for (i = 0; i < d; i++)
            leftRotatebyOne(arr, n);
    }
    void leftRotatebyOne(int arr[], int n) 
    {
        int i, temp;
        temp = arr[0];
        for (i = 0; i < n - 1; i++)
            arr[i] = arr[i + 1];
        arr[i] = temp;
    }
    
    
   
	//The Best Solution is to do bitwise XOR of all the elements. XOR of all elements gives us odd occurring element.
}
