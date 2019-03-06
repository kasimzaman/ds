package Sorting;
//https://www.geeksforgeeks.org/nuts-bolts-problem-lock-key-problem/
/*
 	HashMap based solution
 	Traverse the nuts array and create a hashmap
	Traverse the bolts array and search for it in hashmap.
	If it is found in the hashmap of nuts than this means bolts exist for that nut.
	
	As we apply partitioning on nuts and bolts both so the total time complexity will be Θ(2*nlogn) = Θ(nlogn) on average.
 */
public class NutsBolts
{
	public void nb()
	{	
		String[] nuts = {"N3","N2","N1","N4"};
		String[]bolts = {"B4","B2","B3","B1"};
		quickSort(nuts,bolts,0,nuts.length-1);
	}
	
	private void quickSort(String[]nuts,String[]bolts,int st,int end)
	{
		if(st>end)
			return;
		int pivot = partition(nuts,st,end,bolts[end],end);
		
		partition(bolts,st,end,nuts[pivot],pivot);
		
		quickSort(nuts,bolts,st,pivot-1);
		quickSort(nuts,bolts,pivot+1,end);
	}
	private int partition(String[]A,int st, int end,String pivot,int p)
	{	
		int left=st;
		int right=end;
		
		while(left<right)
		{
			while(A[left].compareTo(pivot)==-1)
				left++;
			while(A[right].compareTo(pivot)>=1)
				right--;
			if(left<right){
				swap(A,left,right);
				left++;
				right--;
			}
		}
		swap(A,p,right);
		return right;
	}
	private void swap(String[]A,int left,int right)
	{
		String temp = A[right];
		A[right]=A[left];
		A[left]=temp;
	}
	
}
