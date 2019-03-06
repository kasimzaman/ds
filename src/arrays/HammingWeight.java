package arrays;


/**
 *  We're given a large array of 4-byte integers.We need to write a method to find out how many
	total bits are turned on (i.e. 1s are set) inside such an array. [Such a digital sum of binary
	representation of a number, is also called its Hamming Weight].
	e.g.
	1. if input array has two numbers: 31 and 51, the answer is 9, because 31 has 5 bits turned on
	(out of 32) and 51 has 4.
	2. if the input is 2147483647 and 3, the answer is 31 + 2 = 33
	We're looking for a fast solution, even if it uses extra memory. While it is possible to optimize
	solutions based on the machine architecture, we're not looking for intense bit-hackery.
	Assume input in base-10. No floating points.
	Hint: Think hash tables.
	
	Solution: See the top solution here: http://stackoverflow.com/questions/8871204/count-
	number-of-1s-in-binary-representation
	 * file:///Users/ali/Documents/workspace/KickStart/Notes/Arrays/HammingWeight
	 * Youtube : 
	 * 
	 * Extras: 		http://javabypatel.blogspot.in/2017/01/count-set-bits-in-number-java.html
	 * Bit hacks	http://www.catonmat.net/blog/low-level-bit-hacks-you-absolutely-must-know/ 
	 * 
	 int is 32 bits. So worst case if all 1's are set for a given integer is 32 which is constant
 */

public class HammingWeight
{
	public void hammingWeight()
	{	
		
		System.out.println(countSetBit(000));
		int[] n={31,51};
		
		System.out.println(hammingWeight(n));
	}
	private int hammingWeight(int[] arr)
	{	
		int count=0;
		for(int i=0;i<arr.length;i++)
		{
			int n = arr[i];
			while(n>0)
			{
				if(n%2==1)
					count++;
				n = n/2;
			}
		}
		return count;
	}
	
	public int countSetBits(long number)
	{
        int count = 0;
        while(number>0){
            ++count;
            number &= number-1;
        }
        return count;
	}
	
	private void shiftright()
	{
		int count=0;
		int a = 60; //0011 1100
		
		for(int i=0;i<32;i++)
		{	
			count+=a&1;
			a=a>>>1;
		}
	}
	//The complexity of the program would be: number of 1's in n (which is constantly < 32).
	public static int countSetBit(long n)
	{
		int count=0;
		while(n!=0)
		{
			count++;
			n= n&(n-1);
		}
		return count;
	}
	
	/*********************************************REVISION***********************************************/
	
}
