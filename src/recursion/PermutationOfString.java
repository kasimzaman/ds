package recursion;

/*
 * Returns a string that is a substring of this string. The substring begins at the specified beginIndex
 * and extends to the character at index endIndex - 1. Thus the length of the substring is endIndex-beginIndex.

 	"hamburger".substring(4, 8) returns "urge"   u is at 4 and 8-1 is 7 and 7th is e
 	"smiles".substring(1, 5) returns "mile"
 */

//n!
public class PermutationOfString
{
	public void pp()
	{
		String s="coat";
		printPermutations(s,"");
	}
	private void printPermutations(String s,String sofar)
	{
		if(s.length()==0)
			System.out.println(sofar);
		else
		{
			for(int i=0;i<s.length();i++)
			{
				char str=s.charAt(i);
				String next = s.substring(0,i) +  s.substring(i+1, s.length());
				printPermutations(next,sofar+str);
			}
		}
	}
	

}
