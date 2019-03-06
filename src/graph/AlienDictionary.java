package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;


/*
 	Remember in a AM, the length of the grid or number of rows is the number of Vertices.
  	Topological Sort
  	Iterate over all edges.
  	Get children of each edge, till there are no children, then add that vertex to stack.
  	So children get pushed to the stack first. The top of the stack are only those items which have no parent..
  	
  	 a b r
  	 a c v
  	 Since the first 2 char of the words are same. We can be sure now that b comes before c. But we don't not for sure if r comes before v.
  	 q c
  	 a v  Here we are sure q comes before a , but not sure about c ,v.
  	 For the first set of characters we can safely assume always that q is before a. But for second set of character we need more info like
  	 q c r 
  	 a c v   now we know for sure that r is before v
  	 
  	
 */

public class AlienDictionary 
{
	Map<Character,List<Character>>graph=new HashMap<>();
	Stack<Character>vertices=new Stack<>();
	int length;
	public void ts()
	{
		String[]words = {"baa", "abcd", "abca", "cab", "cad"};
		//b a
		//d a
		//a c		
		// d  b  a c
		//String[]words = {"caa", "aaa", "aab"};
		findOrder(words);
		
	}
	private String findOrder(String[] words)
	{
		length = countChars(words);
		createGraph(words);
		topologicalSort();
		return printOrder();
    }
	private String printOrder()
	{
		String res="";
		while(!vertices.isEmpty())
		{
			char c=  vertices.pop();
			res+=Character.toString(c);
		}
		return res;
	}
	private void topologicalSort()
	{
		Map<Character,Boolean>visited=new HashMap<>();
		Iterator itr = graph.entrySet().iterator();
		while(itr.hasNext())
		{
			Map.Entry pair = (Map.Entry)itr.next();
			char c = (char)pair.getKey();
			if(visited.containsKey(c))
				continue;
			sort(c,visited);
		}
		
	}
	private void sort(char c, Map<Character,Boolean>visited)
	{
		visited.put(c, true);
		List<Character> children =graph.get(c);
		if(children!=null)
		for(Character ch : children){
			if(visited.containsKey(ch))
				continue;
			sort(ch,visited);
		}
		//no more children, now add to stack
		vertices.add(c);
	}
	/*	b a a      a b c d     a b c a    c a b
	 	a b c d    a b c a     c a b      c a d
	 */
	private void createGraph(String[]words)
	{	
		for(int i=0;i<words.length-1;i++)
		{
			char prevA= ' ';	
			char prevB= ' ';
			String w1 = words[i];
			String w2 = words[i+1];
			for(int j=0;j<Math.min(w1.length(),w2.length());j++)
			{
				char a=w1.charAt(j);
				char b=w2.charAt(j);
				
				if(a != b && prevA==prevB)
				{
					if(!graph.containsKey(a))
					{
						List<Character>list=new ArrayList<>();
						list.add(b);
						graph.put(a, list);
					}
				}
				prevA=a;
				prevB=b;
			}
		}
	}
	private int countChars(String[] words)
	{
		Set<Character>chars=new HashSet<>();
		for(int i=0;i<words.length;i++)
		{
			for(int j=0;j<words[i].length();j++)
			{
				char c = words[i].charAt(j);
				chars.add(c);
			}
		}
		return chars.size();
	}
	
	/***********************************************************************************/
	public String getCharOrder(String[] words)
	{
		
		HashMap<Character, Integer> charToId = new HashMap<Character, Integer>(); 
		HashMap<Integer, Character> idToChar = new HashMap<Integer, Character>(); 
		assignIds(words, charToId, idToChar); 

		int numNodes = idToChar.keySet().size(); 
		int[][] graph = new int[numNodes][numNodes]; 
		constructGraph(words, charToId, graph);
		return getCharOrder(graph, idToChar); 
	}
	
	
	public static void assignIds(String[] words, HashMap<Character, Integer> charToId, 
			HashMap<Integer, Character> idToChar){
		
		int id = 0; 
		for(String word: words)
			for(int i=0;  i<word.length(); i++){
				char c = word.charAt(i); 
				if(!charToId.keySet().contains(c)){
					charToId.put(c, id); 
					idToChar.put(id, c); 
					id++; 
				}
			}
	}
	
	public static void constructGraph(String[] words, 
			HashMap<Character, Integer> charToId, int[][] graph){
		
		int numWords = words.length; 
		for(int i=0; i<numWords; i++)
			for(int j=i+1; j<numWords; j++)				
				addEdge(words[i],words[j], charToId, graph); 
	}
	
	protected static void addEdge(String first, String second, 
			HashMap<Character, Integer> charToId, int[][] graph){
	
		int len = Math.min(first.length(), second.length()); 
		for(int i=0; i<len; i++)
		{
			if(first.charAt(i)!=second.charAt(i)){
				int headId = charToId.get(first.charAt(i)); 
				int tailId = charToId.get(second.charAt(i)); 
				graph[headId][tailId] = 1; 
				break; 
			}
		}
	}
	
	
	protected static String getCharOrder(int[][] graph, HashMap<Integer, Character> idToChar){
		
		Stack<Integer> vertexes = new Stack<Integer>();
		topologicalSort(graph, vertexes); 
		
		StringBuilder builder = new StringBuilder();
		while(!vertexes.isEmpty()){
			builder.append( idToChar.get(vertexes.pop())); 
		}
		return builder.toString(); 
	}

	
	protected static void topologicalSort(int[][] graph, Stack<Integer> vertexes){
		
		int numVertexes = graph.length ; 
		boolean[] visited = new boolean[numVertexes];  
		for(int i=0;  i<numVertexes;  i++)
			visited[i] = false; 
		
		for(int v=0; v<numVertexes; v++)
			if(!visited[v])
				topologicalSort(graph, v, visited, vertexes); 
	}
	
	
	protected static void topologicalSort(int[][] graph, int currVertex, boolean[] visited, 
			Stack<Integer> vertexes){
		
		visited[currVertex] = true ; 
		for(int v=0;  v<graph.length; v++){
			if(graph[currVertex][v]!=0 && !visited[v])
				topologicalSort(graph, v, visited, vertexes); 
		}
		vertexes.push(currVertex); 
	}

}
