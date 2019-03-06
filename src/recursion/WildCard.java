package recursion;

public class WildCard
{
	public void wc()
	{
		String s = "?";
		wildCard(s,0,"");
	}
	
	private void wildCard(String input,int index,String sofar)
	{
		if(index>=input.length()){
			System.out.println(sofar);
			return;
		}
		char c = input.charAt(index);
		if(c=='?')
		{	
			wildCard(input,index+1,sofar+'0');
			wildCard(input,index+1,sofar+'1');
		}
		else
			wildCard(input,index+1,sofar+c);
	}
	/******************************************************/
	
}
