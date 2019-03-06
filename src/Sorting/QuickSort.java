package Sorting;

public class QuickSort
{
	public static void main(String[] args) 
	{
		QuickSort obj1 = new QuickSort();
		int[]list = {2,1,0,6,3,10,7};
		obj1.QS(list, 0, list.length-1);
		for(int n : list)
			System.out.print(n+" ");
	}
	private void QS(int[]list,int low,int high)
	{
		if(low<high){
			int p = partition(list,low,high);
			QS(list,low,p-1);
			QS(list,p+1,high);
		}
	}
	private int partition(int[]A,int low,int high)
	{
		int right=high;
		if(low < high)
		{	
			int p=low;
			int pivot = A[low];
			int left=low+1;
			while(left<=right)
			{
				while(A[left]<pivot)
					left++;
				while(A[right]>pivot)
					right--;
				if(left<=right)
				{
					swap(A,left,right);
					left++;
					right--;
				}
			}
			swap(A,p,right);
		}
		return right;
	}
	private void swap(int[]A, int a, int b)
	{
		int temp = A[a];
		A[a]=A[b];
		A[b]=temp;
	}
	
	/********************************************/
	private void qs2(int[]arr,int low,int high)
	{
		if(low>high)
			return;
		int p = par(arr,low,high);
		qs2(arr,low,p-1);
		qs2(arr,p+1,high);
	}
	
	private int par(int[]arr,int low,int high)
	{
		int pivot= arr[low];
		int left=low+1;
		int right=high;
		while(left<right)
		{
			while(arr[left]<pivot)
				left++;
			while(arr[right]>pivot)
				right--;
			if(left<=right){
				sw(arr,left,right);
				left++;
				right--;
			}
		}
		sw(arr,low,right);
		return right;
	}
	private void sw(int[]arr,int a, int b)
	{
		int temp = arr[a];
		arr[a]=arr[b];
		arr[b]=temp;
	}

}
