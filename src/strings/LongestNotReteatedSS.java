package strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LongestNotReteatedSS {

	
	public static void main(String[] args)
	{
		int val = 10;
		int carry = val/10;
		int a = val%10;
		System.out.println();
		
		//reverseWords("ali asif".toCharArray());
		
	}
	////Given a string, find the length of the longest substring without repeating characters.
	
	public static void reverseWords(char[] s)
	{
	    int i=0;
	    for(int j=0; j<s.length; j++)
	    {
	        if(s[j]==' ')
	        {
	            reverse(s, i, j-1);        
	            i=j+1;
	        }
	    }
	    reverse(s, i, s.length-1);
	    reverse(s, 0, s.length-1);
	}
	 
	public static void reverse(char[] s, int i, int j){
	    while(i<j){
	        char temp = s[i];
	        s[i]=s[j];
	        s[j]=temp;
	        i++;
	        j--;
	    }
	}
	
	private static void pm(String a, String b,String sf)
	{
		
	}
	//Letter Combinations of a Phone Number
	public static List<String> letterCombinations(String digits) {
	    HashMap<Integer, String> map = new HashMap<Integer, String>();
	    map.put(2, "abc");
	    map.put(3, "def");
	    map.put(4, "ghi");
	    map.put(5, "jkl");
	    map.put(6, "mno");
	    map.put(7, "pqrs");
	    map.put(8, "tuv");
	    map.put(9, "wxyz");
	    map.put(0, "");
	 
	    ArrayList<String> result = new ArrayList<String>();
	 
	    if(digits == null || digits.length() == 0)
	        return result;
	    getString(digits,0, map,"");
	 
	    return result;
	}
	
	public static void getString(String digits,int start, HashMap<Integer, String> map,String sofar)
	{
		if(start==digits.length()){
			System.out.println(sofar);
			return;
		}
		int n = digits.charAt(start)-'0';
		String chrs = map.get(n);
		for(int i=0;i<chrs.length();i++)
		{
			char c = chrs.charAt(i);
			getString(digits,start+1,map,sofar+c);
		}
		
	}
	
	public static void generateParenthesis(int n,int open,int close,String sofar)
	{
		if(n==close){
			System.out.println(sofar);
			return;
		}
		if(open<n)
			generateParenthesis(n,open+1,close,sofar+"(");
		if(close<open)
			generateParenthesis(n,open,close+1,sofar+")");
		
    }
	//"eat", "tea", "tan", "ate", "nat", "bat"
	//T=> O(NKlogK) N strings. K is length of every string
	public static List<List<String>> groupAnagrams(String[] strs)
	{
        if (strs.length == 0)
        	return new ArrayList();
        Map<String, List> ans = new HashMap<String, List>();
        for (String s : strs)
        {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);
            if (!ans.containsKey(key))
            	ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }
	//String to Integer (atoi)
	public static int myAtoi(String str) 
	{
		int res=0;
		boolean neg=false;
		int temp=0;
		for(int i=0;i<str.length();i++)
		{
			if(str.charAt(i)=='-')
				neg=true;
			if(str.charAt(i)=='+')
				neg=false;
			
			while(i<str.length() && (str.charAt(i)-'0' < 0 || str.charAt(i)-'0' > 9))
				i++;
			if(i==str.length())
				break;
			
				temp=temp*10+str.charAt(i)-'0';
		}
		if(neg)
			return temp*-1;
		else
			return temp;
    }
	//Multiply Strings
	public static String multiply(String num1, String num2)
	{
		int carry=0;
		int t=0;
		int total=0;
		String old="";
		int index=0;
		for(int i=num2.length()-1;i>=0;i--)
		{ 
			old="";
			int x=num2.charAt(i)-'0';
			for(int j=num1.length()-1;j>=0;j--)
			{
				int s=num1.charAt(j)-'0';
				int p = x*s+carry;
				if(p>9){
					carry=p/10;
				}
				else
					carry=0;
				t=p%10;
				old+=t;
				
			}
			String n="";
			for(int y=old.length()-1;y>=0;y--)
				n+=old.charAt(y);
			int o=0;
			for(int k=0;k<n.length();k++)
			{
				o=o*10+n.charAt(k)-'0';
			}
			for(int f=0;f<index;f++)
			{
				o=o*10;
			}
			total+=o;
			index++;
		}
		return Integer.toString(total);
    }
	//Given a collection of distinct numbers, return all possible permutations.
	
	static void permute(int arr[],int start,int end)
	{
		if (start == end)
		{
			for (int i=0;i<=end;i++)
			      System.out.print(arr[i]+" ");
			   System.out.println();
		}
		for (int i=start;i<=end;i++) 
		{
			swap(arr,start,i);
			permute(arr,start+1,end);
			swap(arr,start,i);
		}
	}
	
	static void swap(int arr[],int idx1,int idx2) 
	{
		   int temp;
		   temp = arr[idx1];
		   arr[idx1] = arr[idx2];
		   arr[idx2] = temp;
		}

}
