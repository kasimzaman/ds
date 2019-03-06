package suffixArray;

import java.util.Arrays;

public class LongestRepeatedSS
{
	//[abdefab, bdefab, defab, efab, fab, ab, b] unsorted will yield no result.
	//[ab, abdefab, b, bdefab, defab, efab, fab] sorted
	
	//O(n^2logn)
	//O(nlogn) for sorting and O(N) for comparing N suffixes during sorting so => O(N^2LOGN)
	public String lrs(String s)
	{
		String lrs="";
		int N=s.length();
		String[]suffixes = new String[N];
		//Make suffixes
		for(int i=0;i<N;i++)
		{
			suffixes[i]=s.substring(i, N);
		}
		Arrays.sort(suffixes);
		
		// find longest repeated substring by comparing adjacent sorted suffixes
       
        for (int i = 0; i < N - 1; i++)
        {
            String x = lcp(suffixes[i], suffixes[i+1]);
            if (x.length() > lrs.length())
                lrs = x;
        }
		
		return lrs;
	}
	 // return the longest common prefix of s and t
    private String lcp(String a, String b)
    {
    	int n=Math.min(a.length(), b.length());
    	for(int i=0;i<n;i++)
    	{
    		if(a.charAt(i)!=b.charAt(i))
    			return a.substring(0, i);
    	}
    	return a.substring(0,n);
    }
    
    /************************************************************/
   

}
