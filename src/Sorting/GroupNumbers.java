package Sorting;

public class GroupNumbers
{
	//Time Complexity: O(n)
	public void gn()
	{
		int[] intArr={2,3,4,5,6,7,4,9,8};
		groupNumbers(intArr);
		for(int i:intArr)
			System.out.print(i+" ");
	}
	private void groupNumbers(int[]intArr)
	{
		int left=0;
		int right=intArr.length-1;
		while(left<right)
		{
			while(intArr[left]%2==0 && left<right)
				left++;
			while(intArr[right]%2==1 && left < right)
				right--;
			if(left<right)
			{
				swap(intArr,left,right);
				left++;
				right--;
			}
		}
    }
	private void swap(int[]A,int a,int b)
	{
		int tmp = A[a];
		A[a]=A[b];
		A[b]=tmp;
	}
			

}
