package strings;

import strings.Revision.Trie.TrieNode;

/*
 * Inserting all the words in the trie takes O(26*M*N)
 * T=O(M) 
 */
public class Longest_common_prefix
{
	static final int ALPHABET_SIZE = 26;
	static class TrieNode
    {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];
        boolean isWordEnd;
        public TrieNode() 
        {
        	isWordEnd = false;
            for (int i = 0; i < ALPHABET_SIZE; i++)
                children[i] = null;
        }
    };
    
    static TrieNode root;
    static int indexs;
    
    static void insert(String key)
    {
        int length = key.length();
        int index;
      
        TrieNode current = root;
      
        for (int level = 0; level < length; level++)
        {
            index = key.charAt(level) - 'a';
            if (current.children[index] == null)
                current.children[index] = new TrieNode();
      
            current = current.children[index];
        }
        current.isWordEnd = true;
    }
    
    static int countChildren(TrieNode node)
    {
        int count = 0;
        for (int i=0; i<ALPHABET_SIZE; i++)
        {
            if (node.children[i] != null)
            {
                count++;
                indexs = i;
            }
        }
        return (count);
    }
    
    static String walkTrie()
    {
        TrieNode current = root;
        indexs = 0;
        String prefix = "";
      
        while (countChildren(current) == 1 )
        {
        	current = current.children[indexs];
            prefix += (char)('a' + indexs);
        }
        return prefix;
    }
    
 // Function to count number of words
    public int wordCount(TrieNode root)
    {
        int result = 0;
        if (root.isWordEnd)
            result++;
          
        for (int i = 0; i < 26; i++)    
          if (root.children[i] != null )
             result += wordCount(root.children[i]);
         
        return result;   
    }
    public String walkTrie2()
    {
        TrieNode pCrawl = root;
        indexs = 0;
        String prefix = "";
      
        while (countChildren(pCrawl) == 1 &&
                pCrawl.isWordEnd == false)
        {
            pCrawl = pCrawl.children[indexs];
            prefix += (char)('a' + indexs);
        }
        return prefix;
    }
    
    static void constructTrie(String arr[], int n)
    {
        for (int i = 0; i < n; i++)
            insert (arr[i]);
        return;
    }
    
    static String commonPrefix(String arr[], int n)
    {
        root = new TrieNode();
        constructTrie(arr, n);
        return walkTrie();
    }
    
    public static void main(String args[])
    {
        String arr[] = {"foo", "bar", "bhaz"};
        int n = arr.length;
      
        String ans = commonPrefix(arr, n);
      
        if (ans.length() != 0)
            System.out.println("The longest common prefix is "+ans);
        else
            System.out.println("There is no common prefix");
    }
}
