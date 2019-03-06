package strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MinimumWindow
{
	public void mw()
	{
		String strText="ADOBECODEBANC";
		String strCharacters="BANC";
		Set<Character> set = new HashSet<>();
		for(int i=0;i<strCharacters.length();i++)
			set.add(strCharacters.charAt(i));
		System.out.println(findMinSubString(strText,set));
	}
	
	private String findMinSubString(String s, Set<Character> set)
	{
		int minLen=Integer.MAX_VALUE;
		int left=0;
		int right=0;
		int min_left=0;
		boolean controlling=false;
		Map<Character,Integer> map = new HashMap<>();
		
		while(left<=right && right<s.length())
		{
			if(map.size()==set.size())// check controlling.
			{
				char trim = s.charAt(left);
				if(map.containsKey(trim))
				{
					if(map.get(trim)==1)
						map.remove(trim);
					else
						map.put(trim, map.get(trim)-1);
				}
				if(map.size()!=set.size())
				{	
					int min=right-left+1;
					if(minLen>min)
					{
						minLen=min;
						min_left=left;
					}
					left++;
				}
				if(map.size()==set.size())
					left++;
				else
					right++;
			}
			else
			{
				char c = s.charAt(right);
				if(set.contains(c) && !controlling)
				{
					if(map.containsKey(c))
						map.put(c, map.get(c)+1);
					else
						map.put(c, 1);
				}
				if(map.size()!=set.size())
					right++;
			}
		}
		if(minLen==Integer.MAX_VALUE)
			return "";
		else
			return s.substring(min_left, min_left+minLen);
	}

}
