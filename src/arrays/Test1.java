package arrays;

import java.util.Arrays;
import java.util.BitSet;

public class Test1
{
	
	public void isPalendrome()
	{
		String str ="Dammit I'm mad.";
		System.out.println(isPalendrome(str.toLowerCase(),0,str.length()-1));
	}
	public void atoi()
	{
		String str = " -a889sd";
		System.out.println(atoi(str));
	}
	public void findMissingNumber()
	{
		int[] a = {1,2,3,4,6,7};
		System.out.println(findMissingNumberForLargeN(a,6));
	}
	private int atoi(String str)
	{
		boolean isNegative = false;
		int num=0;
		int n = 0;
		if(str.length() <= 0 )
			return -1;
		while(n < str.length())
		{
			if(str.charAt(n) != ' ')
			{	
				if(str.charAt(n) == '-')
					isNegative = true;
				else if(str.charAt(n) == '+')
					isNegative = false;
				
				while(9>=(str.charAt(n) - '0') &&(str.charAt(n) - '0') >=0)
				{	
					num = num * 10;
					num = num +str.charAt(n) - '0';
					n++;
				}
				if(num>0)
					break;	
			}
			n++;
		}
		return isNegative?-1 * num:num;
	}

	/*
	 * sum of 1.. n = n(n+1)/2
	 * sum of series 3,4,5,6 n(first + last)/2
	 * This solution has overflow problem, if n is large
	 * So the total sum can become large if the array is large
	 */
	private int findMissingNumber(int[]a,int n)
	{
		Arrays.sort(a);
		int total = (n+1) *(n+2)/2;
		for(int i=0;i<a.length;i++)
			total -=a[i];
		return total;
	}
	/*
	 * XOR means that A or B must be true, but not both,unlike OR
	 * A good way to think about it is to focus on the word "exclusive".
	 * There can be exclusively one true input for XOR to return true.
	 * he bitwise xor operation is commutative and associative: x ^ y ^ z is equal to z ^ x ^ y
	 * (1^2^3^5)^(1^2^3^4^5) will return 4, Since all other are same and cancel each other out.
	 * x^y^x => y
	 * 
	 * XOR will be largest as your largest number and not more. So if you can store that number in int then xor can also be stored. so no over flow because of xor	
	 */
	public int findMissingNumberForLargeN(int[]a ,int n)
	{
		int i;
		int total=-1;
		int x1=a[0];
		int x2=1;
		
		for(i=1;i<a.length;i++)
			x1 = x1^a[i];
		for(i=2;i<=n+1;i++)
			x2=x2^i;
		total = x1^x2;
		return total;
	}
	/*T=O(N),S=O(N)
	  Using BitMap is good and it works when you know the range
	  if count =1,000,000 and if we use Set to store numbers to check for duplicates then the size of the set will be
	  1M X 4bytes = 4,000,000 bytes => 4MB
	  But BitMap = 1,000,000 X 1bit = > 10^6 which is 2^20 bits which is 2^20/2^8 = 2^17 bytes which is 2^10 x 2^7 => 2^7 = 256 and 2^10 is 1KB. So 256KB
	*/
	public void printMissingNumber(int[] numbers, int count)
	{
        int missingCount = count - numbers.length;
        BitSet bitSet = new BitSet(count);
 
        for (int number : numbers)
        {
            bitSet.set(number - 1);
        }
 
        int lastMissingIndex = 0;

        for (int i = 0; i < missingCount; i++) 
        {
            lastMissingIndex = bitSet.nextClearBit(lastMissingIndex);
            System.out.println(++lastMissingIndex);
        }
    }

	/*
	 * ASCII value ranges
	 * ASCII values of  a to z = 97..122
	 * ASCII values of  A to Z = 65..90 (32 LESS)
	 * ASCII values of  0 to 9 = 48..57
	 */
	private boolean isPalendrome(String str, int start, int end)
	{
		if(str.length() <= 1 || (start >= end))
			return true;
		int c = str.charAt(start);
		start = checkAndMoveChar(c,start,end,true,str);
		int l = str.charAt(end);
		end = checkAndMoveChar(l,start,end,false,str);

		if(str.charAt(start)==str.charAt(end))
			return isPalendrome(str,start+1, end-1);

		return false;
	}
	private int checkAndMoveChar(int c, int start, int end,boolean checkStart,String str)
	{
		if(checkStart)
		{
			if(!isChar(c))
			{
				if(start < end-1)
					start++;
				for(int i=start;i<end;i++)
				{
					int c2 = str.charAt(start);
					if(!isChar(c2) && (start < end))
						start++;
					else
						break;
				}
			}
			return start;
		}
		else
		{	
			if(!isChar(c))
			{
				if(start < end-1)
					end--;
				for(int i=end;i>=start;i--)
				{
					int c2 = str.charAt(end);
					if(!isChar(c2) && (start < end))
						end--;
					else
						break;
				}
			}
			return end;
		}
	}
	private boolean isChar(int c)
	{
		if((c >=48 && c<=57) || (c>=65 && c<=90) || (c>=97 && c<=122))
			return true;
		else
			return false;
	}

}
