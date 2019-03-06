package strings;

public class LongestCmnPrefix
{
	public void lcp()
	{
		String[]words= {"geeksforgeeks", "geeks", "geek", "geekzer"};
		System.out.println(lcp2(words));
	}
	//time complexity is O(NM log M)
	//N = Number of strings
	//M = Length of the largest string string

	//To store the longest prefix string we are allocating space which is O(N) where, N = length of the largest string among all the strings
	private String lcp(String[]words)
	{
		int len = shortest(words);
		int low=0;
		int high=len-1;
		String prefix="";
		while(low<=high)
		{
			int mid = low+(high-low)/2;
			if(containsWords(words,words[0],low,mid))
			{
				prefix+=words[0].substring(low, mid);
				low=mid+1;
			}
			else
				high=mid-1;
		}
		return prefix;
	}
	private boolean containsWords(String[]words,String str, int start,int end)
	{
		for(int i=0;i<words.length;i++)
		{
			for(int j=start;i<=end;j++)
			{
				if(words[i].charAt(j)!=str.charAt(j))
					return false;
			}
		}
		return true;
	}
	private int shortest(String[]words)
	{	
		int min=words[0].length();
		for(int i=0;i<words.length;i++)
		{
			if(min<words[i].length())
				min=words[i].length();
		}
		return min;
	}
	
	//////////////////////////////////////////////////////////////////////
	private String lcp2(String[]words)
	{
		String prefix="";
		int minIndex=Integer.MAX_VALUE;
		int min=Integer.MAX_VALUE;
		for(int i=0;i<words.length;i++)
		{
			if(words[i].length()<min){
				minIndex=i;
				min=words[i].length();
			}
		}
		int low=0;
		int high=words[minIndex].length()-1;
		while(low<=high)
		{
			int mid = low+(high-low)/2;
			boolean matched = hasChars(words,low,mid);
			if(matched)
			{
				prefix+=words[0].substring(low, mid+1);
				low=mid+1;
			}
			else
				high=mid-1;
		}
		return prefix;
	}
	private boolean hasChars(String[]words,int low,int high)
	{
		for(int i=0;i<words.length-1;i++)
		{
			String a = words[i];
			String b = words[i+1];
			int start=low;
			int end=high;
			while(start<=end)
			{
				if(a.charAt(start) != b.charAt(start))
					return false;
				start++;
			}
		}
		return true;
	}
	

}
