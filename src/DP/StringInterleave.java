package DP;

// file:///Users/ali/Documents/workspace/KickStart/Notes/DP/sil.png
//time complexity of recursive solution is O(2^(n+m)).  where n , m are length of word1 and word2 
public class StringInterleave
{
	public void sil()
	{
		String a="XXZXXXY";
		String b="XXY";
		String c="XXZ";
		int[][]cache=new int[a.length()+1][b.length()+1];
		for(int i=0;i<a.length();i++)
			for(int j=0;j<b.length();j++)
				cache[i][j]=-1;
		System.out.println(leaveR(a,b,c,0,0,0,cache));
		System.out.println("calls:"+calls);
		calls=0;
		System.out.println(((sil(a,b,c)==true)?"true":"false"));
		System.out.println("calls:"+calls);
		
	}
	static int calls=0;
	private boolean sil(String a, String b, String c)
	{
		if(a.length()<(b.length()+c.length()))
			return false;
		if(b.length()==0 && a.equals(c))
			return true;
		if(c.length()==0 && a.equals(b))
			return true;
		
		int[][][]cache=new int[a.length()+1][b.length()+1][c.length()+1];
		for(int i=0;i<a.length();i++)
			for(int j=0;j<b.length();j++)
				for(int k=0;k<c.length();k++)
					cache[i][j][k]=-1;
		
		return isCommonSubSeq(a,b,c,0,0,0,cache);
	}
	//a:XXZXXY
	//b:XXY
	//c:XXZ
	
	private boolean isCommonSubSeq(String a, String b,String c, int m, int n, int o,int[][][]cache)
	{	calls++;
		
		if(n==b.length() || o==c.length())
			return true;
		if (cache[m][n][o]==1)
			return true;
		if (cache[m][n][o]==2)
			return false;
            

		if(n<b.length() && a.charAt(m)==b.charAt(n) && isCommonSubSeq(a, b, c, m+1, n+1,o,cache))
		{	
			cache[m][n][o]=1;
			return true;
		}
		if(o<c.length() && a.charAt(m)==c.charAt(o) && isCommonSubSeq(a, b, c, m+1, n,o+1,cache) )
		{
			cache[m][n][o]=1;
			return true;
		}
		cache[m][n][o]=2;
		 
		return false;
	}
	//a=aadbbcbcac  b=aadb  c=bcbcac
	//a=aabacac  b=aacac  c=ab
	/***************************************************/
	//Not works if b,c have common characters."XXZXXXY" "XXY" "XXZ";
	private boolean leave(String a, String b,String c)
	{
		if(a.length()!=(b.length()+c.length()))
			return false;
		for(int i=0;i<a.length();i++)
		{
			if(b.length()>0 && a.charAt(i)==b.charAt(0))
				b=b.substring(1);
			else if(c.length()>0 && a.charAt(i)==c.charAt(0))
				c=c.substring(1);
		}
		if(b.length()!=0 || c.length()!=0)
			return false;
		return true;
	}
	
	private boolean leave2(String a, String b,String c)
	{
		int b1=0;
		int c1=0;
		if(a.length()<(b.length()+c.length()))
			return false;
		for(int i=0;i<a.length();i++)
		{
			if(b1<b.length() && a.charAt(i)==b.charAt(b1))
				b1++;
			else if(c1<c.length() && a.charAt(i)==c.charAt(c1))
				c1++;
		}
		if(b.length()!=b1 || c.length()!=c1)
			return false;
		return true;
	}
	//"XXXXXXXXXXXXXXXXXXXASASSGSGSGSGGY" "XXXXXXXXXXXXXX" "XXXXXXXXXXXXX";
	//Space complexity : O(m+n). The size of stack for recursive calls can go upto m+n.
	//Time Time complexity without DP : O(2^(m+n)) m is the length of s1 and n is the length of s2
	//With memo m*n
	private boolean leaveR(String a, String b,String c,int an, int bn, int cn,int cache[][])
	{calls++;
		if(an>=a.length() || bn>=b.length() || cn>=c.length())
			return true;
		if(cache[bn][cn]>=0)
			return cache[bn][cn]==0 ? false:true;
		if(a.charAt(an)==b.charAt(bn) && leaveR(a, b, c, an+1, bn+1, cn,cache)){
			cache[an][bn]=1;
			return true;
		}
		else if(a.charAt(an)==c.charAt(cn) && leaveR(a, b, c, an+1, bn, cn+1,cache)){
			cache[an][cn]=1;
			return true;
		}
		else{
			cache[bn][cn]=0;
			return false;
		}
	}
	
	
	 
}
