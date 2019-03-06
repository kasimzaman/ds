package Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum
{
	public void ts()
	{
		int[] intAr={1, 4, 45, 6, 10, 8};
		int sum = 22;
		Arrays.sort(intAr);
		tsum(intAr,sum);
	}
	
	private String[] printTriplets(int[] A)
	{	
		List<Integer>list = new ArrayList<>();
		for(int i=0;i<A.length-2;i++)
		{
			twoSum(A, A[i],i+1,list);
		}
		String[]res=new String[list.size()/3];
		int index=0;
		String s="";
		int j=0;
		for(j=0;j<list.size();j++)
		{
			if(j>0 && j%3==0){
				res[index++]=s;
				s="";
			}
			s+= Integer.toString(list.get(j))+" ";
		}
		res[index++]=s;
		return res;
    }
	
	private void twoSum(int[]A,int a,int index,List<Integer>list)
	{
		int left=index;
		int right=A.length-1;
		while(left<right)
		{
			int n = A[right] + A[left];
			if(a + n == 0){
				list.add(a);
				list.add(A[left]);
				list.add(A[right]);
				left++;
				right--;
			}
			else if(a+n > 0)
				right--;
			else
				left++;
		}
	}
	//[1, 4, 6, 8, 10, 45]
	/*********************************************/
	private void tsum(int[]arr,int sum)
	{
		Arrays.sort(arr);
		
		for(int i=0;i<arr.length-2;i++)
		{
			int a = arr[i];
			int left=i+1;
			int right=arr.length-1;
			
			while(left<right)
			{
				if(a + arr[left] + arr[right]>sum)
					right--;
				else if(a + arr[left] + arr[right]<sum)
					left++;
				else if(a + arr[left] + arr[right]==sum){
					System.out.println(a+" "+arr[left]+" "+arr[right]);
					left++;
					right--;
				}
			}
		}
		
	}

}
