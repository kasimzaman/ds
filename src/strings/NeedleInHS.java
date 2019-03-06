package strings;

/*
 	PROBLEM: Given a text txt[0..n-1] and a pattern pat[0..m-1], write a function search(char pat[], char txt[])
 	that prints all occurrences of pat[] in txt[]. You may assume that n > m.
 	
 	Input:  txt[] = "THIS IS A TEST TEXT"
        	pat[] = "TEST"
	Output: Pattern found at index 10
	
	Input:  txt[] =  "AABAACAADAABAABA"
	        pat[] =  "AABA"
	Output: Pattern found at index 0
	        Pattern found at index 9
	        Pattern found at index 12
	        
	The best case O(n) occurs when the first character of the pattern is not present in text at all.

	txt[]  = "AABCCAADDEE"
	pat[] = "FAA"    
	
	Worst case: O(m*(n-m+1)). 
	txt[] = "AAAAAAAAAAAAAAAAAA"
	pat[] = "AAAAA"
	And
	txt[] = "AAAAAAAAAAAAAAAAAB"
	pat[] = "AAAAB"
	        
	        
	   
 */
//Naive approach
// O(n * m)
public class NeedleInHS 
{
	public void nhs()
	{
		char txt[] = new String("THIS IS A TEST TEXT").toCharArray();
		char pat[] = new String("TEST").toCharArray();
		   search(txt, pat);
	}
	private void search(char[]txt,char[]pat)
	{
		int M=txt.length;
		int N=pat.length;
		for(int i=0;i<=M-N;i++)
		{
			int j=0;
			for(j=0;j<N;j++)
			{
				if(txt[i+j]!=pat[j])
					break;
			}
			if(N==j)
				System.out.println("Found at starting position:"+i);
		}
	}
}
