package DP;

public class LongestIncreasingSS
{
	//https://www.youtube.com/watch?v=1RpMc3fv0y4&t=456s
	public void liss()
	{
		int[]a={3,1,5,2,6,4,9,10,7};
		lciss(a);
		//LIS(a);
	}
	//nlogn
	public void lciss(int[]a)
	{
		int[]subArr=new int[a.length+1];
		int[]parent=new int[a.length];
		int length=0;
		for(int i=0;i<a.length;i++)
		{
			int low=1; //start with 1 because 0th element has no parent and parent[i]=subArr[low-1]; will throw exception
			int high=length;
			while(low<=high)
			{
				int mid = low+(high-low)/2;
				int n = subArr[mid];
				if(a[n] < a[i])
					low=mid+1;
				else
					high=mid-1;
			}
			//low is the correct position to put the element in subArr
			subArr[low]=i;
			parent[i]=subArr[low-1];
			if(length<low)
				length=low;
		}
		int[]temp =new int[length];
		int k = subArr[length];
		for(int i=length-1;i>=0;i--)
		{
			temp[i]=a[k];
			k=parent[k];
		}
		for(int i=0;i<temp.length;i++)
			System.out.print(temp[i]+" ");
	}
	
	public static void LIS(int X[])
	{
		int parent[]= new int[X.length]; //Tracking the predecessors/parents of elements of each subsequence.
		int increasingSub[]= new int[X.length + 1]; //Tracking ends of each increasing subsequence.
		int length = 0; //Length of longest subsequence.
		
		for(int i=0; i<X.length; i++)
		{
			//Binary search
			int low = 1;
			int high = length;
			while(low <= high)
			{
				int mid = (int) Math.ceil((low + high)/2);
				
				if(X[increasingSub[mid]] < X[i])
					low = mid + 1;
				else
					high = mid - 1;
			}
			
			int pos = low;
			//update parent/previous element for LIS
			parent[i] = increasingSub[pos-1];
			//Replace or append
			increasingSub[pos] =  i;
			
			//Update the length of the longest subsequence.
			if(pos > length)
				length=pos;
		}
		
		//Generate LIS by traversing parent array
		int LIS[] = new int[length];
		int k 	= increasingSub[length];
		for(int j=length-1; j>=0; j--)
		{
			LIS[j] =  X[k];
			k = parent[k];
		}
		
		
		for(int i=0; i<length; i++)
		{
			System.out.println(LIS[i]);
		}
	
		
	}
	

}
