package arrays;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class Test2 {
	
	public static void check_if_rotated()
	{
		int[] s= {4,10};
		//System.out.println(detect_primes(s));
    }
	
	private static int checkIfRotated(String s)
	{
		int n=0;
		while(n<=s.length())
		{
			char temp = s.charAt(s.length()-1);
			String str=Character.toString(temp);
	
			for(int i=0;i<s.length()-1;i++)
			{
				str+=s.charAt(i);
				
			}
			s=str;
			if(isPalindrome(s))
				return 1;
			n++;
		}
		return 0;
		
	}
	
	private static boolean isPalindrome(String s)
	{
		String reverse = new StringBuffer(s).reverse().toString(); 
		if(reverse.compareTo(s)==0)
			return true;
		else
		return false;
	}
	
	public static void testlhm()
	{
		LinkedHashMap<Integer, Integer>map = new LinkedHashMap<>();
		map.put(1, 100);
		map.put(2, 200);
		map.put(3, 300);
		map.put(4, 400);
		
		for(Entry<Integer,Integer> entry : map.entrySet())
		{
			System.out.println(entry.getValue());
		}
	}
	
	

}
