package DP;
/* file:///Users/ali/Documents/workspace/KickStart/Notes/DP/LCSS/lcss.png
 * Bad imple would be to compute subsets of both sets and then compare. 
	complexity  O(2^n) 2 recursive call
	with caching its m*n
 * 
 * 	take away: when calculating a total or adding all sequences of something,then do this
 *  declare a temp variable. Temp is equal to first value plus the recursive call.=> and temp=val+foo();
 *  When the recursion will finish you will get some value in the temp. Now you can check for min/max
 *  then for max or min compare temp with max and update max or min 
 * 
 *   keep adding not concatenating the matched its in a soFar string the make a recursive all like
 *   soFar= str.char(i) + foo(n+1,n+1,cache)
 */

//Time Complexity of the above implementation is O(mn) with caching
//O(2^N) without caching
public class LongestCmSubSeq
{
	public void lcs()
	{
		String 	strX="GXTXAYB";
		String strY ="AGGTAB";
		
		String[][]cache = new String[strX.length()][strY.length()];
		for(int i=0;i<strX.length();i++)
			for(int j=0;j<strY.length();j++)
				cache[i][j]="";
		DP(strX,strY);
		System.out.println(lcss2(strX,strY,0,0,cache));
		//System.out.println(lcss(strX,strY,0,0,cache));
		System.out.println("calls "+calls);
	}
	static int calls=0;
	
	private String lcss(String X, String Y, int m, int n,String[][]cache)
	{
		String soFar="";
		String maxSeq="";
		if(m==X.length() || n==Y.length())
			return "";
		if(cache[m][n]!="")
			return cache[m][n];
		if(X.charAt(m)== Y.charAt(n)){
			soFar=Y.charAt(n) + lcss(X,Y,m+1,n+1,cache);
			if(soFar.length()>maxSeq.length())
				maxSeq=soFar;
		}
		else
		{
			String s = lcss(X,Y,m+1,n,cache);
			if(maxSeq.length()<s.length())
				maxSeq=s;
			s = lcss(X,Y,m,n+1,cache);
			if(maxSeq.length()<s.length())
				maxSeq=s;
		}
		cache[m][n]=maxSeq;
		return maxSeq;
	}
	
	/************************************************************/
	private String lcss2(String A, String B, int a, int b,String[][]cache)
	{
		calls++;
		String str="";
		String max="";
		if(a>=A.length() || b>=B.length())
			return "";
		if(cache[a][b]!="")
			return cache[a][b];
		if(A.charAt(a)==B.charAt(b)){
			str= A.charAt(a) + lcss2(A,B,a+1,b+1,cache);
			if(str.length()>max.length())
				max=str;
		}
		else
		{
			String s1 = lcss2(A,B,a+1,b,cache);
			if(s1.length()>max.length())
				max=s1;
			String s2 = lcss2(A,B,a,b+1,cache);
			if(s2.length()>max.length())
				max=s2;
		}
		cache[a][b]=max;
		return max;
	}
	
	//dp
	private int DP(String s1, String s2)
	{
		int max=0;
		int[][]table=new int[s1.length()+1][s2.length()+1];
		for(int i=0;i<=s1.length();i++)
		{
			for(int j=0;j<=s2.length();j++)
			{	
				if(i==0 || j==0)
					table[i][j]=0;
				else if(s1.charAt(i-1)==s2.charAt(j-1))
					table[i][j]=table[i-1][j-1] +1;
				else
					table[i][j]=Math.max(table[i][j-1], table[i-1][j]);
				if(max<table[i][j])
					max=table[i][j];
			}
		}
		return max;
	}//to print https://www.geeksforgeeks.org/printing-longest-common-subsequence/
	/*
	 	int[]temp = new int[max];//to store result
	 	while(i>0 && j>0)
	 	{
	 		if(s1(i)==s2(j)){
	 		   temp[index]=s1(i)
	 		   index--;
	 		   i--;
	 		   j--;
	 		}
	 		else
	 		{
	 			find larger of 2 neighbors and go there
	 			if(table[i-1][j]>table[i][j-1])
	 			    i--;
	 			else
	 			    j--;
	 		}
	 	}
	 */
	public int lcsDynamic(char str1[],char str2[]){
	    
        int temp[][] = new int[str1.length + 1][str2.length + 1];
        int max = 0;
        for(int i=1; i < temp.length; i++){
            for(int j=1; j < temp[i].length; j++){
                if(str1[i-1] == str2[j-1]) {
                    temp[i][j] = temp[i - 1][j - 1] + 1;
                }
                else
                {
                    temp[i][j] = Math.max(temp[i][j-1],temp[i-1][j]);
                }
                if(temp[i][j] > max){
                    max = temp[i][j];
                }
            }
        }
        return max;
    
}
	
	public int lcs(char str1[],char str2[],int len1, int len2)
	{
        
        if(len1 == str1.length || len2 == str2.length){
            return 0;
        }
        if(str1[len1] == str2[len2]){
            return 1 + lcs(str1,str2,len1+1,len2+1);
        }
        else{
            return Math.max(lcs(str1,str2,len1+1,len2),lcs(str1,str2,len1,len2+1));
        }
}
	
	
	 
}
