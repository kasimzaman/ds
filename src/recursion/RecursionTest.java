package recursion;

	
public class RecursionTest
{
	public void rt()
	{
		long[]arr={-6,3,8};
		int k=5;
		//System.out.println(check_if_sum_possible(arr,k,0));
		wellFormedBrackets(2);
	}

	//QUESTION: file:///Users/ali/Documents/workspace/KickStart/Notes/Recursion/Test/1.png
	
	//TAKEAWAY: keep subtracting from k. You don't need to store in sofar
	//When the 1st recursive/inclusive call returns TRUE, no need to go in the 2nd call. Just return.
	//Whenever you see summing/aggregation in a problem always think can it overflow. Here k can, so go with wider bit.
	//like a large negative number plus another large negative number -900000000 -
	private boolean check_if_sum_possible(long[] arr, long k,int index)
	{	
		if(k==0)
			return true;
		if(index>=arr.length)
			return false;
		if(check_if_sum_possible(arr,k-arr[index],index+1))
			return true;
		return check_if_sum_possible(arr,k,index+1);
    }
	
	//   file:///Users/ali/Documents/workspace/KickStart/Notes/Recursion/Test/2.png
	//TAKEAWAY: Think about it. We need to print all permutation of n.
	// if n is 1 ()
	//if n=2 => () (()) ()()
	//So use a for loop. And solve for every value of n.
	private void wellFormedBrackets(int n)
	{
		for(int i=1;i<=n;i++)
			welFormed(i,0,0,"");
    }
	private void welFormed(int n,int open, int close,String sofar)
	{
		if(n==close){
			System.out.println(sofar);
			return;
		}
		if(open<n)
			welFormed(n,open+1,close,sofar+"(");
		if(close<open)
			welFormed(n,open,close+1,sofar+")");
	}
	




}
