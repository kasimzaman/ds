package arrays;

/*
    Given an array containing both +ve and -ve integers, return an array of alternating positive
	integers and negative integers such that each set of integers are in the same order as in the
	input array (stable).
	e.g.
	input {2, 3, -4, -9, -1, -7, 1, -5, -6}
	output {2, -4, 3, -9, 1, -1, -7, -5, -6}.
	
	Can you implement it without using any additional space?
 */

public class Alternating
{
	public void alternating()
	{
		int[]arr={1,2,-3,-4,5,-6,-7,-8,-9,10,11,-12,13,14,15,16,-17};
		alternating2(arr);
		System.out.println();
		alternating(arr);
	}
	private void alternating(int[]arr)
	{
		boolean alter=true;
		int a=0;
		int b=0;
		if(arr[0]>0)
			alter=false;
		while(arr[a]<0)
			a++;
			
		while(arr[b]>0)
			b++;
			
		int i=0;
		while(a<arr.length && b<arr.length)
		{
			if(arr[i]>0 && alter==false){
				alter=true;
				System.out.print(arr[a]+",");
				a++;
				while(a<arr.length && arr[a]<0)
					a++;
				i=b;	
			}
			else if(arr[i]<0 && alter==true)
			{
				alter=false;
				System.out.print(arr[b]+",");
				b++;
				while( b<arr.length && arr[b]>0)
					b++;
				i=a;	
			}
		}
		while(a<arr.length)
		{
			if(arr[a]>0)
				System.out.print(arr[a]+",");
			a++;
		}
		while(b<arr.length)
		{
			if(arr[b]<0)
				System.out.print(arr[b]+",");
			b++;
		}
	}
	
	/******************************************************REVISION*************************************************************/
	//{1,2,-3,-4,5,-6,-7,-8,-9,10,11,-12,13,14,15,16,-17};
	private void alternating2(int[]arr)
	{
		int p=0;
		int n=0;
		boolean positive=false;
		if(arr[0]>=0)
			positive=true;
		while(arr[p]<=0)
			p++;
		while(arr[n]>=0)
			n++;
		
		while(p<arr.length || n<arr.length)
		{
			if(positive)
			{
				System.out.print(arr[p++]+",");
				while(p<arr.length && arr[p]<=0)
					p++;
			}	
			else
			{
				System.out.print(arr[n++]+",");
				while(n<arr.length && arr[n]>=0)
					n++;
			}
			positive=!positive;
		}
	}
	
	
	
	
	
	
	
	
	
}