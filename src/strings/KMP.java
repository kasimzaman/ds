package strings;
/*
 *  file:///Users/ali/Documents/workspace/KickStart/Notes/Strings/kpm.png
	Time Complexity O(N)
	**************************************Naive solution: O(nm)********************************************
	Problem:Given a string S, Find a substring M if exist. Or find a patter M if it exist and return the index.
	S  =   A B C B C G L    length=n
	P  =   B C G L			length=m
	Naive solution: O(nm)
	i=0,j=0
	Check if i==j
	If match, then j++ check i+j==j
	If j==length of pattern. return true;
	Else, i++, and repeat
	*******************************************************************************************************
	
	
	****************************************KMP Search O(m+n)**********************************************
i   0 1 2 3 4 5 6         
m	a b c d a b x a b c d
	j
n	a b c d a b c y 
	Pattern matches till just before i=6 and j=6.
	The matched string before 6 is : a b c d a b
	Now in naive solution(mn), we would start again from j=0. But notice that in n  prefix abc == suffix abc.
	And since we have already searched the suffix abc. It means that we don't need to start from j=0. Instead start from just after the prefix.At j=3 d.
	So KMP works like this. As we keep on comparing like in needleHayStack problem but when there is a mismatch we check for the longest common prefix/suffix
	and then skip that many characters.
	
	Next we compare C and X. Since they are not same. So we check is there a suffix/prefix match before C.
	Its not. So we now match X with A.
	
	Building LCPS  Time O(N), Space O(N)
	        j i
			0 1 2 3 4 5 6 7-->index
			A B C D A B C A
Array[]	= { 0 0				}	if (i!=j) put 0 , increment i;
		    j   i
			0 1 2 3 4 5 6 7-->index
			A B C D A B C A
		  { 0 0	0			}	if (i!=j) put 0 , increment i;
		
			j     i
			0 1 2 3 4 5 6 7-->index
			A B C D A B C A
		  { 0 0	0 0			}	if (i!=j) put 0 , increment i;
		  
		  	j       i
			0 1 2 3 4 5 6 7-->index
			A B C D A B C A
		  { 0 0	0 0	1		}	if (i==j) put value of j+1 , increment both i and j;
		  
		      j       i
			0 1 2 3 4 5 6 7-->index
			A B C D A B C A
		  { 0 0	0 0	1 2		}	if (i==j) put value of j(index)+1 , increment both i and j;Here value of 1 means that we length of longest common suffix and prefix==1
		  
		        j       i
			0 1 2 3 4 5 6 7-->index
			A B C D A B C A
		  { 0 0	0 0	1 2 3		}	if (i==j) put value of j(index)+1 , increment both i and j;Here value of 2 means that we length of longest common suffix and prefix==2
		        
		          j       i
		    0 1 2 3 4 5 6 7-->index
			A B C D A B C A
		  { 0 0	0 0	1 2 3		}	Here i!=j and j!=0. Then j looks at the value of its previous character.Its 0. So j becomes 0.
		  
		    j			  i
		    0 1 2 3 4 5 6 7-->index
			A B C D A B C A
		  { 0 0	0 0	1 2 3 1		}	Here i==j =>j+1
		  
*/
//Time Complexity : O(M + N)
public class KMP 
{
	public void searchUsingKPM()
	{
		String txt = "ABABDABACDABABCABAB";
        String pat = "AB";
        KMPSearch(pat.toCharArray(),txt.toCharArray());
	}
	void KMPSearch(char[] pattern, char[] txt)
    {
        int N = txt.length;
        int M = pattern.length;
        int j = 0; 
        
        int res = 0; //if checking for frequency of substring
        int next_i = 0; //if checking for frequency of substring  
 
        int lps[] = computeLPSArray(pattern);
 
        int i = 0;  
        while (i < N)
        {
            if (pattern[j] == txt[i])
            {
                j++;
                i++;
            }
            if (j == M)
            {
                System.out.println("Found pattern " + "at index " + (i-j));
                // Since j == M, print pattern found and reset 
                j = lps[j-1];
                //lps[j-1] (in above step) gave us index of next character to match.
                
                //if checking for more than 1 occurrence of substring then, 
                res++; //count of occurrences
                if (lps[j]!=0)
                    i = ++next_i;
                j = 0;
            }
 
            // mismatch after j matches
            else if (i < N && pattern[j] != txt[i])
            {
                if (j != 0)
                    j = lps[j-1];
                else
                    i = i+1;
            }
        }
    }
	
	private int[] computeLPSArray(char[] pattern)
    {
		int[] lps = new int[pattern.length];
		int j=0;
		lps[0]=0;
		for(int i=1;i<pattern.length;)
		{
			if(pattern[j]==pattern[i])
			{
				//put value of j+1
				lps[i]=j+1;
				i++;
				j++;
			}
			else
			{
				if(j!=0)//j get the value of previous character in array
				{
					j=lps[j-1];
				}
				else
				{
					lps[i]=0;
					i++;
				}
			}
		}
		return lps;
    }
	
	
}
