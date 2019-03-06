package Sorting;
/*
 * We define a temporary array of the size of the original array. This array is used for storing the result of each merge. And then copy that
 * sorted part back to the original array. While copying back we only copy from the current low to high.
 */
public class MergeSort 
{
	public void ms()
	{
		int[] intArr={3,4,5,1,2,0,4,1,34,90,7};
		int[]res=new int[intArr.length];
		int[] sorted = mergeSort(intArr,0,intArr.length-1,res);
		for(int i:sorted)
			System.out.print(i+" ");
	}
	
	private int[] mergeSort(int[] intArr,int low,int high,int[]res)
	{	
		if(low<high)
		{
			int mid = low+(high-low)/2;
			mergeSort(intArr,low,mid,res);
			mergeSort(intArr,mid+1,high,res);
			merge(intArr,low,high,mid,res);
			copy(intArr,res,low,high);     //IMPORTANT  IMPORTANT  IMPORTANT
		}
		return intArr;
    }
	void copy(int arr[],int res[],int low,int high)
	{
		for(int i=low;i<=high;i++)   //IMPORTANT  IMPORTANT  IMPORTANT
			arr[i]=res[i];
	}
	// {1,3,5}  {2,6,7}
	// low = 0, mid= 2, right=mid+1. Right should be next of mid.
	private void merge(int[]A,int low,int high,int mid,int[]res)
	{
		int index=low;
		int left=low;
		int right=mid+1;
		while(left<=mid && right <=high)  //IMPORTANT  IMPORTANT  IMPORTANT   
		{
			if(A[left]<A[right]){
				res[index]=A[left];
				index++;
				left++;
			}
			else if(A[left]>A[right]){
				res[index]=A[right];
				index++;
				right++;
			}
			else
			{
				res[index++]=A[left];//Handling duplicates
				res[index++]=A[right];
				left++;
				right++;
			}
		}
		while(left<=mid)
		{
			res[index++]=A[left];
			left++;
		}
		while(right<=high)
		{
			res[index++]=A[right];
			right++;
		}
	}
}
