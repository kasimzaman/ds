package DP;

import java.util.Arrays;

/*
 * file:///Users/ali/Documents/workspace/KickStart/Notes/DP/wr.png
 * O(n^2)
 * The extra spaces includes spaces put at the end of every line.  
 The problem is to minimize the following total cost.
 Cost of a line = (Number of extra spaces in the line)^3
 Total Cost = Sum of costs for all lines

 For example, consider the following string and line width M = 15
 "Geeks for Geeks presents word wrap problem" 
     
 Following is the optimized arrangement of words in 3 lines
 Geeks for Geeks
 presents word
 wrap problem 

The total extra spaces in line 1, line 2 and line 3 are 0, 2 and 3 respectively. 
So optimal value of total cost is 0 + 2*2*2 + 3*3*3 = 0+8+27=35
 */
	
public class WordWrap
{
	static int call=0;
	public void ww()
	{
		int maxWidth=16;
		int[] l={5,3,5,8,4,4,7};
		String[] strArr={"This", "is", "an", "example", "of", "text", "justification."};
		//String[] strArr={"a","b","c"};
		int[]cache=new int[l.length];
		Arrays.fill(cache, -1);
		int[]parent=new int[strArr.length];
		System.out.println(fullJustify(strArr,maxWidth,0,cache));
		System.out.println(call);
		
	}
	/*	We are working from the end backwards. Like from right end and back to the beginning.
	 *   j is always bigger than i.
	 *  SubProblems:Suffixes.put each word in the line then recurse on the remaining subproblems. We have total n subproblems.
	 * 				DP[i] will be solution to the above suffix.
		Guess:      Where to start the 2nd line. Total choices here are n-i or O(N)
		Recurrence :DP[i] : Do a for loop from i+1 to n. Line starts at i, so I can't start there.
		            j will be the word where the next line starts.
		            so word i is the 1st word of the 1st line.
		            so word j is the 1st word of the 2nd line.
		            cost is the badness of i j.
	   Time/subproblem:        each subproblem does constant work. And there are n choices. so O(N)
	   Topological  order: i = n, n-1,..... 0
	   Total time:  Number of subproblems X running time per sub problem. => n * n => O(N^2)
	*/
	private int fullJustify(String[] words,int maxWidth,int index,int[]cache)
	{call++;
		int min=Integer.MAX_VALUE;
		int remainder=maxWidth;
		if(index>=words.length) //base case. No more words left.
			return 0;
		if(cache[index]!=-1)
			return cache[index];
		
		for(int i=index;i<words.length;i++)
		{	int cost=0;
			if(remainder>=words[i].length())
			{	
				if(remainder<maxWidth)//if remainder != maxWidth, it means a word has already been added and i can add a space.
					remainder=(remainder-(words[i].length()+1));
				else	
					remainder=(remainder-words[i].length());
					
				int rc = remainder*remainder*remainder;
				cost=rc +  fullJustify(words,maxWidth,i+1,cache);
				if(min>cost){
					min=cost;
				}
			}
		}
		
		cache[index]=min;
		return min;
	}
	
	/*********************************************************/
	private int fullJustify(String[] words,int maxWidth,int index)
	{
		if(index>=words.length)
			return 0;
		int min=Integer.MAX_VALUE;
		
		int remainder=maxWidth;
		
		for(int i=index;i<words.length;i++)
		{
			int cost=0;
			String word = words[i];
			if(remainder>=word.length())
			{
				if(remainder<maxWidth)
					remainder=(remainder-(word.length()+1));
				else
					remainder=(remainder-word.length());
				int rc = remainder*remainder*remainder;
				cost=rc +  fullJustify(words,maxWidth,i+1);
				if(min>cost)
					min=cost;
			}
		}
		return min;
	}
}







