package strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Revision
{
	/*
	 *  Ukkonen’s Suffix Tree Construction takes O(N) time and space to build suffix tree for a string of length N
	 *  and after that finding deepest node will take O(N).
	 *  
	 * 	T for search/insert the key in O(key_length) time. However the penalty is on Trie storage requirements.
	 * 	Space O(key_length * total_words)
	 * 	Suffix trie: Space worst O(M^2) but using offsets it can be O(M)
	 * 	Suffix tree can be used for
	 * 	1: Substring Check
	 * 	------------------
	 * 	Given a text string and a pattern string, check if pattern exists in text or not..
	 * 	This can also be done using : (KMP, Rabin-Karp, Naive Algorithm, Finite Automata).
	 * 	Ukkonen’s Suffix Tree Construction takes O(N) time and space to build suffix tree
	 *  for a string of length N and after that, traversal for substring check takes O(M) for a pattern of length M.
	 * 	
	 * 	1:Traverse the tree from root to leaf against the characters in pattern.
	 * 	2:If we do not fall off the tree while traversal, then pattern exists in text as a substring.
	 * 
	 * 	2: Check if string S is suffix
	 * 	Same as above but end should be $ sign.	
	 * 
	 * 	3:Count the number of times a string S occurs as substring.
	 * 	Pattern Search
	 * 	--------------
	 * 	Given a text string and a pattern string, find all occurrences of the pattern in string?
	 * 	This can also be done using : (KMP, Rabin-Karp, Naive Algorithm, Finite Automata).
	 * 
	 * 	Suffix tree number of nodes can be reduced by adding labels.
	 * 	
	 * 	4: Most repeating substring
	 * 	Its the number of $ signs in the subtree from that prefix onwards. The node that has the max number of dollars in its sub-tree.
	 * 	DFS: Hey all my children, how many dollars you each have in your subtrees.
	 * 	5: Longest repeating substring: for uncompressed trie: https://www.youtube.com/watch?v=hLsrPsFHPcQ. Same for suffix tree
	 * 	First, repeating substring is any node(prefix) in the subtree with more than 1 dollar in its subtree. and out of them you want the longest.
	 * 	And that one is the farthest from the root. Use recursion. Root asks , the child for the deepest node that has more than one dollar. And also the 
	 * 	number of dollars you have in your subtree. So ask each child 2 questions. 1: who is the deepest node that has more than 1 dollar. 2: and how many
	 * 	dollars do you have. Why do I ask 2? Because if the child could not find any deepest node. Then may be I am the deepest node.
	 * 
	 * 	For uncompressed suffix trie the worst case space is O(M^2)
	 * 	In compressed suffix trie we have O(M) nodes. But each node is holding the label as well. So the total space of the suffix trie is still m^2 .
	 *  To fix this use offset and length instead of string. then its O(M) space
	 *  Compress trie
	 *  CAT, CATTLE, BAT ,BATTLE
	 *  			O
	 *  		  /	  	\
	 *  		CAT   	BAT
	 *  	   /  \  	/   \
	 *       $   TLE$  $   TLE$
	 *       
	 *      class Node
	 *      {
	 *      	HashMap<Character,Node>children; //char just stores the char and not the full string.
	 *      	String s; //stores the whole label.
	 *      }				
	 *  
	 */
	class Trie
	{
		class TrieNode
		{
			TrieNode[]children=new TrieNode[26];
			boolean isWordEnd;
			TrieNode()
			{
				isWordEnd=false;
				for(int i=0;i<26;i++)
					children[i]=null;
			}
		}
		TrieNode root=null;
		Trie()
		{
			root=new TrieNode();
		}
		public void insert(char[] key)
		{
			TrieNode current=root;
			int len = key.length;
			for(int i=0;i<len;i++)
			{
				int index = key[i]-'a';
				if(current.children[index]==null)
					current.children[index]=new TrieNode();
				current=current.children[index];
			}
			current.isWordEnd=true;
		}
		public boolean search(char[] key)
		{
			TrieNode current=root;
			for(int i=0;i<key.length;i++)
			{
				int index = key[i]-'a';
				if(current.children[index]==null)
					return false;
				current=current.children[index];
			}
			if(current.isWordEnd)
				return true;
			else
				return false;
		}
		public List<String> autoComplete(char[]prefix)
		{
			List<Character>list=new ArrayList<>();
			TrieNode current=root;
			for(int i=0;i<prefix.length;i++)
			{
				int index = prefix[i]-'a';
				if(current.children[index]==null)
					return null;
				list.add((char)(index+'a'));
				current=current.children[index];
			}
			
			recurse(current,list);
			return null;
		}
		public void recurse(TrieNode node,List<Character>list)
		{
			if(node.isWordEnd)
			{
				for(char s:list)
					System.out.print(s);
				System.out.println();
			}
			if(!hasChildren(node))
				return;
			
			for(int i=0;i<26;i++)
			{
				if(node.children[i]!=null)
				{
					char c = (char)(i+'a');
					list.add(c);
					recurse(node.children[i],list);
				}
			}
		}
		public boolean hasChildren(TrieNode node)
		{
			for(int i=0;i<26;i++){
				if(node.children[i]!=null)
					return true;
			}
			return false;
		}
		
		
	}
	/************************************************************************/
	//Trie with Hashmap
	class Node
	{
		Map<Character,Node>children=new HashMap<>();
		boolean isWordEnd=false;
	}
	class Tri
	{
		Node root=new Node();
		
		public void insert(char[]key)
		{
			Node current=root;
			for(int i=0;i<key.length;i++)
			{
				char c = key[i];
				if(!current.children.containsKey(c))
					current.children.put(c, new Node());
				current = current.children.get(c);
			}
			current.isWordEnd=true;
		}
		public boolean search(char[]key)
		{
			if(root==null)
				return false;
			Node current=root;
			for(int i=0;i<key.length;i++)
			{
				char c = key[i];
				if(!current.children.containsKey(c))
					return false;
				current=current.children.get(c);
			}
			if(current.isWordEnd)
				return true;
			else
				return false;
		}
		public void autoComplete(char[]prefix)
		{
			List<Character>list=new ArrayList<>();
			if(root==null)
				return;
			Node current=root;
			for(int i=0;i<prefix.length;i++)
			{
				char c = prefix[i];
				if(!current.children.containsKey(c))
					return;
				list.add(c);
				current=current.children.get(c);
			}
			recurse(current,list);
		}
		public void recurse(Node node,List<Character>path)
		{
			if(node.isWordEnd)
			{
				for(char s:path)
					System.out.print(s);
				System.out.println();
			}
			if(node.children.size()==0)
				return;
			for(Entry<Character,Node> entry : node.children.entrySet())
			{
				Node child = entry.getValue();
				path.add(entry.getKey());
				recurse(child, path);
			}
		}
		// .izza
		public void dotMatch(char[]key)
		{
			List<Character> list=new ArrayList<>();
			Node current=root;
			for(int i=0;i<key.length;i++)
			{
				char c = key[i];
				
				if(c=='.')
				{
					for(Entry<Character,Node> entry : current.children.entrySet())
					{
						Node node = entry.getValue();
						char k = entry.getKey();
						list.add(k);
						getStr(node,key,i+1,list);
						for(char ch : list)
							System.out.print(ch);
						System.out.println();
						list.clear();
					}
				}
				else
				{
					if(!current.children.containsKey(c))
						return;
					list.add(c);
					current=current.children.get(c);
				}
			}
		}
		public void getStr(Node child, char[]suffix,int start,List<Character> list)
		{
			Node current=child;
			for(int i=start;i<suffix.length;i++)
			{
				char c = suffix[i];
				if(!current.children.containsKey(c))
				{
					list.clear();
					return;
				}
				list.add(c);
				current=current.children.get(c);
			}
			if(current.isWordEnd)
				return;
			else
			{
				list.clear();
				return;
			}	
		}
		
		//deepest node with more than 1 child.
		public void longestRSS()
		{
			Node current=root;
			System.out.println(deepestNode(current).s);
			
		}
		class RES
		{
			int depth=0;
			int leafCount=0;
			String s;
		}
		public RES deepestNode(Node node)
		{
			RES res = new RES();
			int max=Integer.MIN_VALUE;
			for(Entry<Character,Node>entry: node.children.entrySet())
			{
				int nLeafs=0;
				Node child = entry.getValue();
				res.s=Character.toString(entry.getKey());
				RES r = deepestNode(child);
				res.depth=r.depth+1;
				res.leafCount=r.leafCount;
				res.s+=r.s;
				if(child.isWordEnd)
					nLeafs = 1+countLeafUnderMe(child);
				else
					nLeafs = countLeafUnderMe(child);
				if(max<res.depth && nLeafs>=2)
				{
					max=res.depth;
				}
			}
			return res;
		}
		public void isSubstring(char[]key)
		{
			Node current=root;
			for(int i=0;i<key.length;i++)
			{
				char c = key[i];
				if(!current.children.containsKey(c)){
					System.out.println("false");
					return;
				}
				current=current.children.get(c);
			}
			System.out.println("true");
			
		}
		public void isSuffix(char[]key)
		{
			Node current=root;
			for(int i=0;i<key.length;i++)
			{
				char c = key[i];
				if(!current.children.containsKey(c)){
					System.out.println("false");
					return;
				}
				current=current.children.get(c);
			}
			if(current.isWordEnd)
				System.out.println("true");
			else
				System.out.println("false");
		}
		//start searching from root till all key words till n. Then return the number of leaf nodes below that point.
		//do a DFS down from n.
		public void countStrOccourances(char[]key)
		{
			Node current=root;
			for(int i=0;i<key.length;i++)
			{
				char c = key[i];
				if(!current.children.containsKey(c)){
					System.out.println("false");
					return;
				}
				current=current.children.get(c);
			}
			int n=0;
			if(current.isWordEnd)
				n=1+countLeafUnderMe(current);
			System.out.println(n);
			//
		}
		public int countLeafUnderMe(Node node)
		{	
			int count=0;
			for(Entry<Character,Node> entry : node.children.entrySet())
			{
				Node n = entry.getValue();
				if(n.isWordEnd)
					count=1+countLeafUnderMe(n);
				else
					count=countLeafUnderMe(n);
			}
			return count;
		}
	}
	/************************************************************************/
	public void trieTester()
	{
		Tri trie=new Tri();
		String str = "abaaba";
	//	for(int i=0;i<str.length();i++)
		//	trie.insert(str.substring(i).toCharArray());
		
		//trie.longestRSS();
		trie.insert("pizza".toCharArray());
		
		//trie.insert("pvzza".toCharArray());
		trie.insert("pizz".toCharArray());
		
		trie.insert("pizzahut".toCharArray());
		trie.search("pizz".toCharArray());
		trie.dotMatch("p.zza".toCharArray());
	}
	
}
