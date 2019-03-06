package strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class cw1
{
/*
	find the smallest substring in string S that contain all the characters in in the set c
	S: "abcdba"   c:{b,d}. Remember that sets have no order. So it will be sub-string "db"
	Brute Force: Generate all substring of S, and for each sub-string, check it it contains all char from the set C.
	If it does , i will record the minimum length. Maintain the minimum. 
	Generate SubStr=> I have a start index and end index. And for every start index, I will try out all the end indexes from that point on.

	Once you have generated all the substrings, how will you check if the char from set C are in those substrings.
	So this is a sub problem of the above problem we are trying to solve.
	given a substring find weather the char from set C exist in this substring.
	Can you do this in liners time without using extra memory?
	NO you can't !!
	
	So how do you check a String against a set then?
	use hashmap.
	Algorithm: go over the string character by character and keep checking if that character exists in the set. If it does then put that character in HashMap and count=1.
	If you encounter it again count++. In the end check if the size of the hashMap==set of set. if yes true else false.
	(Still uses additional memory: Not good)Another way is you make a copy you the original set. Now start going over character of the string and start removing character from the new set as you see them there.
	If at the end the new set is empty. You have found the solution.
	Or better use a set instead of a hashmap. And keep adding characters to it. In the end size should be same if true.
	
	for  st: 0--> n-1
	{
   	   for  end: st--> n-1
   	   { 
   		 new HashMap={}//new hashmap for every substring
   		 for i: st-->end //doing for every substring
   		    if[i] in C //Check if exist in HM
   		    if(size.Map==size.C)
   		 		//check against current min length so far
   		 		if(min > end-st+1)   => end-start+1 is the current substring's length
   		 		  min = end-st+1
   		 		  minStart=currentStart
   		 		  minEnd=currentEnd
  
  Complexity O(N^3)
  
  
  
 Techniques to Bring every N^3 Solution to N^2 IF the problem is about generating all sub-arrays or substrings
  
    	Declare the HM above. 
    	
    	for  st: 0--> n-1
		{
			 new HashMap={
   	   		for  end: st--> n-1
   	   		{
   	   			if(end in C){
   	   			  if(!map.contains(end))
   	   			      map.put(end)
   	   			  else
   	   			      map.put(end)+1
   	   			  
   	   			  if(size.Map==size.C)
   		 		//check against current min length so far
   		 		if(min > end-st+1)   => end-start+1 is the current substring's length
   		 		  min = end-st+1
   		 		  minStart=currentStart
   		 		  minEnd=currentEnd
   	   			}	  
   	   		}	
   	   }
   	   We have reduced to n^2. 
   	   In the first solution we were generating the substrings which was n^2, then for every substring in the HM we were checking against the set, so n^3.
   	   Now while generating the substrings, we keep storing the string in the HM.
   	   Because in every iteration we are adding 1 character, so why not add that to the HM . Instead of waiting for whole string to form and then going over
   	   that string in another loop and checking against C.
   	   So no need to re process the whole string once we have processed it already once.
   	   
   	   More optimization.
   	   Why always start at 0th index . Why not check if that character even exists in the set or not.
   	   Also trim all unnecessary character from left and right
   	   ex: S=[a,b,c,d,a]  C={b,d}
   	   trim left/right you are left with [b,c,d] But there is a problem if
   	   S=[a,b,c,d,b,a]  C={b,d}
   	   Drop a => [b,c,d,b]  now which b should I care about ?
   	   Its the one to the right (shortest). But how do I know that without looking at the rest of the string?
   	   How do I know which one is closer to d. You can't !!
   	   But at least now you have a shorter substring after trimming.
   	   But I now don't know in there which once is necessary and which is not. And this problem arises because I am starting from both ends.
   	   
   	   So start from left and make sure there are no unnecessary characters to the right.
   	   So get rid of all unnecessary character from the left.
   	   
   	   S=[a,b,c,d,b,a]  C={b,d}
   	   Take 2 pointers: left,right pointing to 0th(a)
   	   
   	   1:Expand Right until we control the set. (control means:current string contains all the characters from the set=>MAP.size==set.size)
   	   
   	   		start: At a not controlling .Go to b
   	   		At b  add to map since in set Map{b:1} Not controlling still so move ahead
   	   		At c not controlling . Move on
   	   		At d  add to map map{b:1, d:1}  size.map==size.set. So controlling the set between Current Left and current Right.
   	   2:Now trim from left until we no longer control the set. or until this condition (map.size < set.size) becomes true. 
   	   		Drop a, move to b. Still controlling.
   	   		Drop b, move to c. Map.remove(b) so (map.size < set.size) becomes true. so not controlling now.
   	   		So the current left and current right ends up no controlling. But its was controlling one before. or when left was left-1. we were controlling.
   	   3:Record minlength = r-(l-1)+1=>r-l+2=>3
   	   
   	   4:Repeat until right>left
   	   
   	   next time when up move R ahead to b.add b to map. Your are still controlling. 
   	   then trim from left. you trim c. but still controlling.
   	   then move left and trim d. not controlling now 
   	   minlength= r-l+2=2. New min length is 2
   	   
   	   
    	
 */
	public void findMinSubStringContainingSet()
	{
		String S="abcdba";
		Set<Character> set = new HashSet<>();
		set.add('b');
		set.add('d');
		System.out.println(findMinSubString(S,set));
	}
	
	//S=[a,b,c,d,b,a]  C={b,d}
	private int findMinSubString(String s, Set<Character> set)
	{
		int minLen=Integer.MAX_VALUE;
		int left=0;
		int right=0;
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
					minLen=Math.min(minLen, min);
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
				else
					left++;
			}
		}
		return minLen;
	}
	
	
	
}
