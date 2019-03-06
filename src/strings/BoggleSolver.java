package strings;

import java.util.ArrayList;
/*
   Algorithm: 	Trie has max 26 character at every level.
   				Pick 1st character from grid and check if it's in the trie's root. If not why bother going further.
   				If root has character in children. Recursively do same thing and pass in that particular child of the root which has that character.
 
 	   L=word length
 *     O(N^2 times 8^L)
 *     
 *     space : O(W * L)
 *     
 */
public class BoggleSolver
{
	TrieNode root=null;
	public void bs()
	{
		//To avoid duplicates. You can add results to set.
		String[] dictionary={"GEEKS","QUIZ","FOR","SEEK"};
		char[][] board= {{'G','I','Z'},
						 {'U','E','K'},
						 {'Q','S','E'}};
		String[] result =findWords(dictionary,board);
		for(String s:result)
			System.out.println(s);
	}
	
	private String[] findWords(String[] dictionaryList, char[][] board)
	{
		String[] words=null;
		createTrie(dictionaryList);
		ArrayList<ArrayList<String>> res=new ArrayList<>();
		boolean[][]visited=new boolean[board.length][board[0].length];//Initialized to FALSE by default
		
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board[0].length;j++)
			{
				int c = board[i][j]-'A';
				if(root.children[c]==null)
					continue;
				ArrayList<String> s=(dfs(root.children[c],board,i,j,Character.toString(board[i][j]),visited)); 
				if(s!=null && s.size()>0)
					res.add(s);
			}
		}
		int index=0;
		if(res.size()==0)
			return new String[]{"0"};
		words = new String[res.size()];
	
		for(int i=0;i<res.size();i++)
		{
			ArrayList<String> a = res.get(i);
			String str="";
			for(int j=0;j<a.size();j++)
			{
				str+=a.get(j);
			}
			words[index++]=str;
		}
		return words;
    }
	private int[] R={0,1,1,-1,-1,0,-1,1};
	private int[] C={1,0,1,0,1,-1,-1,-1};
	
	//private String res;
 	private ArrayList<String> dfs(TrieNode root, char[][] board,int row, int col, String sofar,boolean[][]visited)
	{
 		ArrayList<String> res = new ArrayList<>();
 		if(row>=board.length || col>=board[0].length || visited[row][col])
 			return res;
 		visited[row][col]=true;
 		for(int i=0;i<8;i++)
 		{	
 			int c =C[i]+col;
 			int r =R[i]+row;
 			if(c>=board.length|| c<0 ||r<0 || r>=board.length || visited[r][c])
 				continue;
 			
 			int ch = board[r][c]-'A';//Checking if this character is even present in the Trie's children. If not then no need to proceed
 			if(root.children[ch]==null)
				continue;
 			
 			if(search(sofar + board[r][c]))
 				res.add(sofar + board[r][c]);
 			ArrayList<String> list=dfs(root.children[ch],board,r,c,sofar + board[r][c],visited);
 			if(list!=null && list.size()>0)
 			{
 				 for(String s:list)
 					 res.add(s);
 			}
 		}
 		visited[row][col]=false;
		return res;
	}
	/***************************USING TRIE FOR DICTIONARY****************/
	private void createTrie(String[] dictionary)
	{
		root = new TrieNode();
		for(int i=0;i<dictionary.length;i++)
			insert(dictionary[i]);
	}
	private boolean search(String w)
	{
		TrieNode current=root;
		for(int i=0;i<w.length();i++)
		{
			int index=w.charAt(i)-'A';
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
	private void insert(String w)
	{
		TrieNode current=root;
		for(int i=0;i<w.length();i++)
		{
			int index = w.charAt(i)-'A';
			if(current.children[index]==null)
				current.children[index]=new TrieNode();
			current=current.children[index];
		}
		current.isWordEnd=true;
	}
	class TrieNode
	{
		boolean isWordEnd;
		TrieNode[] children = new TrieNode[26];
		TrieNode()//not needed
		{
			for (int i =0 ; i< 26 ; i++)
				children[i] = null;
		}
	}
	/******************************************************************/

}
