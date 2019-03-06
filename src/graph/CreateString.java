package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


/*
 	Time Complexity: O(n²m) where n is the number of entries originally in the dictionary
 	and m is the size of the string
  
  	Algorithm: Create a graph. Every dictionary word is a node and there is an edge to another word only if both these words differ by 1.
  	After creating the graph, simply do a bfs till you find the end words.
  	
  	If the dictionary is too large then creation time can be reduced by using a different technique. But even this algorithm assumes that there
  	are limited/26 alphabets. So this is what i will do. Iterate over the alphabets and keep replacing the characters one by one in the
  	dictionary words.
  	And keep looking if that new words exists in the dictionary, if it does then create a edge.
  
  	O(N*26*K) N=words, 26=alphabet, k length of each word
		char[] arr = a.toCharArray();
        for(int i=0; i<arr.length; i++)
        {
            for(char c='a'; c<='z'; c++)
            {  
                if(arr[i]!=c)
                {
                    arr[i]=c;
                }
                String newWord = new String(arr);
            }
        }
 */
public class CreateString 
{
	public void cs()
	{
		String a="bat";
		String b="had";
		String[] words={"cat", "bat", "hat", "bad", "had"};
		
		createString(words,a,b);
	}
	
	//Time Complexity: O(n²m)
	private void createString(String[]words,String a, String b)
	{
		Map<String,String> graph = createGraphEx(words);
		Queue<String>path=dfs(graph,a,b);
		while(!path.isEmpty())
		{
			System.out.print(""+path.poll()+"");
			if(!path.isEmpty())
				System.out.print("->");
		}
	}
	private Queue<String> dfs(Map<String,String> graph,String start, String end)
	{
		Queue<String>q=new LinkedList<>();
		Queue<String>path=new LinkedList<>();
		path.add(start);
		q.add(start);
		
		while(!q.isEmpty())
		{
			String e = q.poll();
			String element = graph.get(e);
			path.add(element);
			if(element.equals(end)){
				return path;
			}
			q.add(element);
		}
		return path;
	}
	private Map<String,String> createGraph(String[]words)
	{
		Map<String,String>graph=new HashMap<>();
		for(int i=0;i<words.length-1;i++)
		{
			for(int j=i+1;j<words.length;j++)
			{
				if(differByOne(words[i],words[j]))
					graph.put(words[i], words[j]);
			}
		}
		return graph;
	}
	private boolean differByOne(String s1, String s2)
	{
		int[]map=new int[26];
		for(int i=0;i<s1.length();i++){
			int n=s1.charAt(i)-'a';
			map[n]++;
		}
		for(int i=0;i<s2.length();i++){
			int n=s2.charAt(i)-'a';
			map[n]--;
		}
		int count=0;
		for(int n : map)
			if(n>0)
				count++;
		if(count==1)
			return true;
		else
			return false;
	}
	
	/**********************************************************************/
	//O(N*26*K) N=words, 26=alphabet, k length of each word
	private void createStringEx(String[]words,String a, String b)
	{
		
	}
	private Map<String,String> createGraphEx(String[]words)
	{
		Set<String>dictionary=new HashSet<>();
		for(String s:words)
			dictionary.add(s);
		Map<String,String>graph=new HashMap<>();
		for(int i=0;i<words.length;i++)
		{
			String w=words[i];
			
			char[]arr=w.toCharArray();
			for(int j=0;j<arr.length;j++)
			{
				for(char c='a';c<='z';c++)
				{
					if(arr[j]!=c)
					{
						arr[j]=c;
						String tem= new String(arr);
						if(dictionary.contains(tem))
							graph.put(w, tem);
						arr=w.toCharArray();
					}
				}
			}
		}
		return graph;
	}

}
