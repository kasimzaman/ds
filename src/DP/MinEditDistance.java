package DP;

//file:///Users/ali/Documents/workspace/KickStart/Notes/DP/EditDistance/editdist.png
/**
 * Hamming Distance vs Edit Distance
 * Hamming Distance = > Distance between 2 string of equal length. And it just the minimum number of substitutions required to turn one into the other.
 * Easy. Line up the string and compare position by position and increment the counter when they dont match.
 *Edit distance is the minimum number of Substitutions or Insertions or deletions required to turn one string into another.
 *
 *	X: G  C  G  T  A  T  G  C  G  G  C  T  A  A  C  G  C  
 *  Y: G  C  T  A  T  G  C  G  G  C  T  A  T  A  C  G  C
 *           *  *  *  *  *  *     *  *  *  *   				==>Hamming Distance => 10
 *           
 *           EDIT Distance =>2
 *    
 *  The number of edits between string of different length is at least equal to the difference between there lengths.
 *  X:a b
 *  Y:a b c d     difference is length = 2. so at least 2 edits are required 
 *        
 *              intention to execution
 *          	  (intention)
 *  	Del			 Insert		Substitute
 *    ntention	   eintention    entention
 *  
 *  
 *  X:aaa	delete	x+1=> aa   delete  x+1=> a    delete=> x+1 
 *  Y:bbb                 bbb				 bbb              bbb  Now 3 deletes so far and finally 3 more so total cost 6
 *  
 *  Substitute cost aaa x+1 b aa  x+1 bb a  x+1 bbb   
 *  				bbb y+1 b bb  y+1 bb b  y+1 bbb    total cost 3
 *  
 *  Complexity : branching into 3 calls. so O(3^n)
 *  
 */


public class MinEditDistance
{
	static int calls=0;
	public void findMED()
	{
		String word1 = "GCGTATGCGGCTAACGC";
		String word2 = "GCTATGCGGCTATACGC";
		int[][]cache= new int [word1.length()][word2.length()];
		for(int i=0;i<word1.length();i++)
		{
			for(int j=0;j<word2.length();j++)
				cache[i][j]=Integer.MAX_VALUE;
		}
		System.out.println(recursive(word1,word2,0,0));
	//	System.out.println(med(word1,word2));
		//System.out.println(minEditDistance(word1,word2,cache,word1.length()-1,word2.length()-1));
		//System.out.println(minEditDistance2(word1,word2,cache,0,0));
		//System.out.println(levenshteinDistance(word1,word2));
		
		System.out.println(calls);
		calls=0;
	}
	
	//TOPDOWN
	@SuppressWarnings("unused")
	private int minEditDistance(String s1, String s2,int[][]cache,int m, int n)
	{
		calls++;
		int min=Integer.MAX_VALUE;
		if(m<0)
			return n;
		if(n==0)
			return m;
		
		if(cache[m][n]<Integer.MAX_VALUE)
			return cache[m][n];
			
		if(s1.charAt(m)==s2.charAt(n))
			return minEditDistance(s1,s2,cache,m-1,n-1);
		
		min=1+Math.min(Math.min(minEditDistance(s1,s2,cache,m-1,n),minEditDistance(s1,s2,cache,m,n-1)), minEditDistance(s1,s2,cache,m-1,n-1));
		cache[m][n]=min;
		return min;
	}
	//BOTTOMUP
	private int minEditDistance2(String s1, String s2,int[][]cache,int m, int n)
	{
		calls++;
		int min=Integer.MAX_VALUE;
		if(m==s1.length())
			return s2.length()-n;
		if(n==s2.length())
			return s1.length()-m;
		if(cache[m][n]<Integer.MAX_VALUE)
			return cache[m][n];
			
		if(s1.charAt(m)==s2.charAt(n))
			return minEditDistance2(s1,s2,cache,m+1,n+1);
		
		int m1=1+minEditDistance2(s1,s2,cache,m+1,n);//delete=> cape,ape delete c by moving m i ahead. Now both are same
		int m2=1+minEditDistance2(s1,s2,cache,m,n+1);// now adding will look like: add a to cape=> acape,ape. so 1st char are same so now move n. (cape,pe)
		int m3=1+minEditDistance2(s1,s2,cache,m+1,n+1);//replace=> ape,cape
		min = Math.min(Math.min(m1, m2),m3);
		
		cache[m][n]=min;
		return min;
	}
	
	 static int levenshteinDistance(String strWord1, String strWord2)
	 {
	        int value=0;
			if(strWord1.length()==0 && strWord2.length()==0)
				return 0;
			if(strWord1.length()==0)
				return strWord2.length();
			if(strWord2.length()==0)
				return strWord1.length();
			if(strWord1.charAt(0)!=strWord2.charAt(0))
				value =1+ levenshteinDistance(strWord1.substring(1),strWord2.substring(1));
			else
				value = levenshteinDistance(strWord1.substring(1),strWord2.substring(1));
			return value;
	 }
	 //Time Complexity: O(m x n)
	 //Auxiliary Space: O(m x n)
	 private int med(String s1, String s2)
	 {
		int[][]table = new int[s1.length()+1][s2.length()+1];
		for(int i=0;i<=s1.length();i++)
		{
			for(int j=0;j<=s2.length();j++)
			{
				if(i==0)
					table[i][j]=j; // Min. operations = j
				else if(j==0)
					table[i][j]=i; // Min. operations = i
				else if(s1.charAt(i-1)==s2.charAt(j-1))
					table[i][j]=table[i-1][j-1];//just copy the diagonally above value
				else
					table[i][j]=Math.min(table[i-1][j-1], Math.min(table[i][j-1], table[i-1][j])) + 1;
			}
		}
		return table[s1.length()][s2.length()];
	 }
	 /********************************************************************/
	 //https://www.youtube.com/watch?v=2veeHbRQUuw&t=135s  DP Solution
	 // O(3^(n+m))
	 private int recursive(String s1, String s2, int a, int b)
	 {
		 if(a==s1.length())
			 return s2.length()-b;
		 if(b==s2.length())
			 return s1.length()-a;
		 if(s1.charAt(a)==s2.charAt(b))
			 return recursive(s1, s2, a+1, b+1);
		 return 1+ Math.min(recursive(s1,s2,a+1,b), Math.min(recursive(s1,s2,a,b+1), recursive(s1,s2,a+1,b+1)));
	 }
	 
}
