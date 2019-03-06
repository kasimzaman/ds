package strings;

//Search/Insert is O(M)  M is the length of string

//Memory required is O(ALPHABET_SIZE * Key_length * N) N is number of keys
public class Trie
{
	TrieNode root;
	// Alphabet size (# of symbols)
    static final int ALPHABET_SIZE = 26;
    static class TrieNode
    {
    	boolean isWordEnd;
    	TrieNode[] children = new TrieNode[26];
    	public TrieNode()
    	{	
    	}
    }
	//O(key_length)
	public void insert(String key)
	{
		int length = key.length();
		TrieNode current=root;
		for(int i=0;i<length;i++)
		{
			int index = key.charAt(i)-'a';
			if(current.children[index]==null)
				current.children[index]=new TrieNode();
			current=current.children[index];
		}
		current.isWordEnd=true;
	}
	//O(key_length)
	public boolean search(String key)
	{
		int lenght = key.length();
		TrieNode current= root;
		for(int i=0;i<lenght;i++)
		{
			int index = key.charAt(i)-'a';
			if(current.children[index]==null)
				return false;
			else
				current=current.children[index];
		}
		if(current.isWordEnd)
			return true;
		else
			return false;
	}

}
