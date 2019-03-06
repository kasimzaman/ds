package arrays;

/**
 * (This question is quite rampant. Not sure why)
	Given a number, find the next smallest palindromic number, larger than this number.
	e.g
	Input: 23545
	
	http://www.geeksforgeeks.org/given-a-number-find-next-smallest-palindrome-larger-than-this-number/
    file:///Users/ali/Documents/workspace/KickStart/Notes/Arrays/NextPalindrome
  
 */
public class NextPalindrome
{
	public void nextPalindrome()
	{
		int n = 10;
		System.out.println(nextPalindrome2(n));
	}
	
	private int nextPalindrome(int n)
	{	
		while(!isPalindrome(++n));
		return n;
	}
	private boolean isPalindrome(int n)
	{
		int temp=n;
		int sum=0;
		while(temp>0)
		{
			int r = temp%10;
			sum=sum*10+r;
			temp=temp/10;
		}
		if(n==sum)
			return true;
		return false;
	}
	
	/***********************************************REVISION*******************************************************/
	private int nextPalindrome2(int n)
	{
		while(!isPalindrome(++n));
		return n;
	}
	private boolean isPalindrome2(int n)
	{
		int temp=n;
		int num=0;
		while(temp!=0)
		{
			num=num*10+ temp%10;
			temp=temp/10;
		}
		if(num==n)
			return true;
		else
			return false;
	}
	
}
