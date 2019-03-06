package strings;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class AdHoc
{
	public static void main(String args[])
	{
		System.out.println(reverseRecursively("12345"));
		
	}
	// Java program to count all duplicates from string using hashing
	//Time Complexity: O(n)
	//it is assumed that the characters are stored using 8 bit and there can be 256 possible characters.
	public void printDups(String str)
    {
		int count[] = new int[256];
		for (int i = 0; i < str.length();  i++){
			int n =  str.charAt(i);
			count[str.charAt(i)]++;
		}
     
		for (int i = 0; i < 256; i++)
		{
			if(count[i] > 1)
				System.out.print(count[i]);
		}
    }
	
	/********************************ARE ANAGRAM*************************************/
	public static boolean areAnagram(String s1, String s2)
	{
		int[]count=new int[26];
		for(int i=0;i<s1.length() && i<s2.length();i++)
		{
			int index = s1.charAt(i)-'a';
			count[index]++;
			index = s2.charAt(i)-'a';
			count[index]--;
			
		}
		for(int i=0;i<25;i++)
		{
			if(count[i]!=0)
				return false;
		}
		return true;
	}
	
	/********************************************************************************/
	//You can also use LinkedHashMap because they are sorted.
	public static char firstNonRepeatedCharacter(String word) 
	{
		Map<Character,Integer> counts = new LinkedHashMap();
        HashMap<Character,Integer> scoreboard = new HashMap<>();
    
        for (int i = 0; i < word.length(); i++)
        {
            char c = word.charAt(i);
            if (scoreboard.containsKey(c))
            	scoreboard.put(c, scoreboard.get(c) + 1);
            else
            	scoreboard.put(c, 1);
        }
        // since HashMap doesn't maintain order, going through string again. But its better to iterate the map instead.
        // for (Entry<Character,Integer> entry : counts.entrySet()) {
        for (int i = 0; i < word.length(); i++)
        {
            char c = word.charAt(i);
            if (scoreboard.get(c) == 1)
                return c;
        }
        throw new RuntimeException("Undefined behaviour");
    }
	
	/**********************************reverse str***************************************/
	public static String reverseRecursively(String str)
	{
        if (str.length() < 2)
            return str;
        return reverseRecursively(str.substring(1)) + str.charAt(0);
    }
	/**********************************STR CONTAINS ONLY DIGITS?***************************************/
	static boolean onlyDigits(String str)
	{
		for(int i=0;i<str.length();i++)
		{
			int n = str.charAt(i)-'0';
			if(n<0 || n>9)
				return false;
		}
		return true;
	}
	
	/**********************************ALL PERMUTATION OF STR***************************************/
	//Algorithm is nothing but keeping one character fix and then calculating permutations of others.
	//Time Complexity: O(n*n!) Note that there are n! permutations and it requires O(n) time to print a a permutation.
	private void printPermutations(String s,String sofar)
	{
		if(s.length()==0)
			System.out.println(sofar);
		else
		{
			for(int i=0;i<s.length();i++)
			{
				char str=s.charAt(i);
				String next = s.substring(0,i) +  s.substring(i+1, s.length());
				printPermutations(next,sofar+str);
			}
		}
	}
	// permutation.permute(str, 0, n-1);
	private void permute(String str, int l, int r)
    {
        if (l == r)
            System.out.println(str);
        else
        {
            for (int i = l; i <= r; i++)
            {
                str = swap(str,l,i);
                permute(str, l+1, r);
                str = swap(str,l,i);
            }
        }
    }
	public String swap(String a, int i, int j)
    {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i] ;
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }
	
	

}
