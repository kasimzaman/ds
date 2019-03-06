package strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 	Find longest sub-string with at most 2 distinct characters
 	S="ecebaba"
 	=>"ece"
 	
 	Can't use subsets, because a ecb is also a subset(exclusion), but its not a substring. Have to be contiguous
 */
public class LongestSStr
{
	public void lss()
	{
		String strText="aabacbebebe";
		//System.out.println(longestSub(strText,3));
		System.out.println(findLongestSS(strText.toCharArray(),3));
	}
	private boolean isValid(int[]count, int k)
	{
		for(int i=0;i<count.length;i++)
		{
			if(count[i] !=0)
				k--;
		}
		if(k<0)
			return false;
		else
			return true;
	}
	private String findLongestSS(char[] str, int k)
	{
		String s="";
		int[]count = new int[26];
		int u=0;
		for(int i=0;i<str.length;i++)
		{
			if(count[str[i]-'a']==0)
				u++;
			count[str[i]-'a']++;
		}
		int start=0;
		int end=0;
		int maxLen=Integer.MIN_VALUE;
		if(u<k)
			return "";
		int left=0;
		Arrays.fill(count, 0);
		for(int j=0;j<str.length;j++)
		{
			count[str[j]-'a']++;
			while(!isValid(count, k))
			{
				count[str[left]-'a']--;
				left++;
			}
			if(maxLen<(j-left+1))
			{
				maxLen=(j-left+1);
				start=left;
				end=j;
			}
		}
		for(int i=start;i<=end;i++)
			s+=str[i];
		return s;
	}
	
	
	
	
	//O(n): Size of HashMap can be max==K which is max= 26 (alphabets)
	//Algorithm. Keep left pointer while iterating over the string.
	//Keep adding to map until map size > k. If it is then using left pointer keep removing elements from map till map size==k.
	//Also keep storing the left and i positions. Before storing check if the difference between left-i+1 is max.
	private String longestSubString(char[]str, int k)
	{	
		int maxLen=Integer.MIN_VALUE;
		int winStart=0;
		String s="";
		int winEnd=0;
		int[] count = new int[26];
		int u=0;
		
		for (int i=0; i<str.length; i++)
	    {	
	        if (count[str[i]-'a']==0)
	            u++;
	        count[str[i]-'a']++;
	    }
		if(u<k)
			return "";
		
		
		Map<Character,Integer> map=new HashMap<>();
		int left=0;
		for(int i=0;i<str.length;i++)
		{
			char c=str[i];
			if(map.size() < k)
			{
				if(map.containsKey(c))
					map.put(c, map.get(c)+1);
				else
					map.put(c, 1);
			}
			else if (map.size() == k)
			{
				if(map.containsKey(c))
					map.put(c, map.get(c)+1);
				else
				{	
					map.put(c, 1);
					while(map.size()>k)
					{
						char ch=str[i];
						if(map.containsKey(ch)){
							if(map.get(ch)>1)
								map.put(ch, map.get(ch)-1);
							else
								map.remove(ch);
						}
						left++;
					}
				}	
			}
			if(maxLen < (i-left+1))
			{
				maxLen = i-left+1;
				winStart=left;
				winEnd=i;
			}
		}
		for(int i=winStart;i<=winEnd;i++)
		{
			s+=str[i];	
		}
		return s;
		//return str.substring(winStart, winEnd+1);
	}
	
	
	
	
	
	//O(N^2) with additional memory n
	private String longestSub(String str) 
	{
		String maxStr="";
		for(int st=0;st<str.length();st++)
		{
			String s="";
			Map<Character,Integer> map=new HashMap<>();
			for(int end=st;end<str.length();end++)
			{
				char c=str.charAt(end);
				if(map.size()<2)
				{
					if(map.containsKey(c)){
						s+=c;
						map.put(c, map.get(c)+1);
					}
					else{
						s+=c;
						map.put(c, 1);
					}
				}
				else
				{
					if(map.containsKey(c)){
						s+=c;
						map.put(c, map.get(c)+1);
					}
				}
			}
			if(map.size()<3)
			{
				if(maxStr.length()<s.length())
					maxStr=s;
			}
		}
		return maxStr;
    }
	
	/******************************************/
	//cecadr
	public String longestSub(String str,int k) 
	{
		int max=0;
		int left=0;
		int right=0;
		String s="";
		Map<Character,Integer>map=new HashMap<>();
		while(left<=right && right<str.length())
		{
			
			char c = str.charAt(right++);
		
			if(map.containsKey(c))
				map.put(c,map.get(c)+1);
			else
				map.put(c, 1);
		
			while(map.size()>k)
			{
				c = str.charAt(left++);
				if(map.get(c)>1)
					map.put(c, map.get(c)-1);
				else
					map.remove(c);
			}
			int len = right-(left)+1;
			if(max<len)
			{
				max=len;
				s=str.substring(left, right);
			}
		}
		return s;
	}



}
