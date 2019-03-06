package strings;

import java.util.ArrayList;
import java.util.List;

/*
 * file:///KickStart/Notes/ScalableSystems/ScalableSystem.mov
 * file:///KickStart/Notes/ScalableSystems/notes2     
 	1) Create an empty Trie.
	2) Do following for every word:-        
    	a) Insert reverse of current word.
    	b) Also store up to which index it is a palindrome. (deedba palindrome till index 3)
	3) Traverse list of words again and do following for every word.
    	a) If it is available in Trie then return true
    	b) If it is partially available
         	Check the remaining word is palindrome or not 
         	If yes then return true that means a pair forms a palindrome.
         Note: Position upto which the word is palindrome
               is stored because of these type of cases. like : {ab, deedba} => abdeedba
               
               
               			a
               			|
               			b
               			|
               			d Palindromd index=3
               			|
               			e
               			|
               			e
               			|
               			d

 */

public class MakePalindrome
{
	//{BAT,TAB,CAT} BAT AND TAB can be joined to for a palindrome
	//{ab,deedba}  can be joined.
	
	public void mp()
	{
		String[] words={"geek","keeg"};
		//System.out.println(palindromePairs(words));
		root = new TrieNode();
		checkPalindromePairs(words);
	}
	//Solution Using Trie:O(NK^2)
	private void checkPalindromePairs(String[] words)
	{
		// Construct trie
		for(String s : words)
			insert(s);
		// Search for different keys
		
	}
	/************************************************************************/
	static int ALPHABET_SIZE=26;
	static class TrieNode
	{
		boolean isWordEnd;
		TrieNode[] children = new TrieNode[ALPHABET_SIZE];
		 List<Integer> list= new ArrayList();
	}
	TrieNode root;
	private void insert(String key)
	{	
		TrieNode current = root;
		for(int i=key.length()-1;i>=0;i--)
		{
			int index = key.charAt(i)-'a';
			if(current.children[index]==null)
				current.children[index]=new TrieNode();
			
			if (isPalindrome(key, 0, i))
				root.list.add(index);
			
			current=current.children[index];
		}
		current.isWordEnd=true;
	}
	
	private boolean isPalindrome(String word, int i, int j)
	{
	    while (i < j) {
	    	if (word.charAt(i++) != word.charAt(j--)) return false;
	    }
	    	
	    return true;
	}
	/************************************************************************/
	
	
	
	//For each word, we simply go through the words array and check whether the concatenated string is a palindrome or not.
	//O(n^2*k)  n is the number of words. And K is the length that is checked for a palindrome
	public String palindromePairs(String[] words) 
	{
		String pairs="";
		if(words.length<2)
			return "";
		
		for(int i=0;i<words.length-1;i++)
		{
			for(int j=i+1;j<words.length;j++)
			{
				String w = words[i]+words[j];
				if(isPalindrome(w))
					pairs+=words[i]+words[j]+",";
			}
		}
	   return pairs;
	}
	private boolean isPalindrome(String s)
	{
		int left=0;
		int right=s.length()-1;
		while(left<=right)
		{
			if(s.charAt(left++)!=s.charAt(right--))
				return false;
		}
		return true;
	}
	

}
