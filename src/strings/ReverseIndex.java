package strings;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
 
//hash table will take space equal to number of words.
//Trie will use about 50% less space, as most words have common prefixes.
public class ReverseIndex
{
	Map<String,ArrayList<Integer>> map = new HashMap<>();
	TrieNode root;
	public ReverseIndex()
	{
		root = new TrieNode();
		readFile();
	}
	public void searchWord()
	{
		String str="platform";
		List<Integer>results=getIndex(str);
		if(results!=null && results.size()>0)
		{
			System.out.println("Search results using Hash Table");
			for(int i:results)
				System.out.println(i);
		}
		results.clear();
		results=searchTrie(str);
		if(results!=null && results.size()>0)
		{
			System.out.println("Search results using Trie");
			for(int i:results)
				System.out.println(i);
		}
	}
	public ArrayList<Integer> getIndex(String key)
	{
		if(map.containsKey(key))
			return map.get(key);
		else
			return null;
	}
	class TrieNode
	{
		boolean isWordEnd;
		TrieNode[] children= new TrieNode[26];
		ArrayList<Integer>indexes = new ArrayList<Integer>();
	}
	private void insert(String word, int position)
	{
		TrieNode current=root;
		for(int i=0;i<word.length();i++)
		{
			int index=word.charAt(i)-'a';
			if(current.children[index]==null)
			{
				current.children[index]=new TrieNode();
				current=current.children[index];
			}
			else
				current=current.children[index];
		}
		current.isWordEnd=true;
		current.indexes.add(position);
		
	}
	private List<Integer> searchTrie(String word)
	{
		TrieNode current=root;
		for(int i=0;i<word.length();i++)
		{
			int index = word.charAt(i)-'a';
			if(current.children[index]==null)
				return null;
			current=current.children[index];
		}
		if(current.isWordEnd)
			return current.indexes;
		else
			return null;
	}
	public void readFile()
	{
		try
		{
			File file = new File("/Users/ali/Documents/workspace/KickStart/Notes/Strings/Resume");
			Scanner scanner = new Scanner(file);
			String line;
			int counter=0;
			while(scanner.hasNextLine())
			{
				line = scanner.nextLine();
				String words[] = line.toLowerCase().replaceAll("[^a-z0-9]", " ").replaceAll("\\p{Punct}|\\d","").split(" ");
				int i;
				for(i=0;i<words.length;i++)
				{
					String word = words[i];
					insert(word,counter+i);
					if(map.containsKey(word))
					{
						ArrayList<Integer> list= map.get(word);
						list.add(counter+i);
						map.put(word,list);
					}
					else
					{
						ArrayList<Integer> n = new ArrayList<Integer>();
						n.add(counter+i);
						map.put(word,n);
					}
				}
				counter+=i;
			}
			scanner.close();
		}
		catch(Exception x)
		{
			System.out.println(x.getMessage());
		}
	}
}

