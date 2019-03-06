package Sorting;

public class Rank
{
	public void fr()
	{
		//2 3 4 6 7 8 9 10
		int[]list = {7,6,2,8,3,10,9,4};
		kthsmallest(list,0,list.length-1,3);
	}
	public void kthsmallest(int[]arr,int low,int high,int k)
	{
		if(low>high)
			return;
		int pivot = partition(arr,low,high);
		if(pivot+1==k)
			System.out.println(arr[pivot]);
		else if(pivot+1>k)
			kthsmallest(arr, low, pivot-1, k);
		else
			kthsmallest(arr, pivot+1,high, k);
	}
	public int partition(int[]arr,int low,int high)
	{
		int pivot = arr[low];
		int left=low+1;
		int right=high;
		while(left<=right)
		{
			while(arr[left]<pivot)
				left++;
			while(arr[right]>pivot)
				right--;
			if(left<=right)
			{
				swap(arr,left,right);
				left++;
				right--;
			}
		}
		swap(arr,low,right);
		return right;
	}
	public void swap(int[]arr,int a,int b)
	{
		int temp=arr[a];
		arr[a]=arr[b];
		arr[b]=temp;
	}
}
