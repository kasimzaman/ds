package ListAndStack;

import java.util.Stack;
// file:///Users/ali/Documents/workspace/KickStart/Notes/ListAndStack/longestsubstring.png
public class MatchingParen
{
	public void matchingParen()
	{
		String s= "((()()(())";
		System.out.println(matchingParenMaxLength(s));
	}
	private int matchingParenMaxLength(String str)
	{
		Stack<Character> stack = new Stack<Character>();
		int maxLength = 0;
		int found=0;
		int length = str.length();
		int sum=0;
		for(int index = 0; index < length; index++)
		{
			char c = str.charAt(index);
			if(c=='(' ){
				if(found==1){
					found=-1;
					stack.removeAllElements();
					sum = maxLength;
					maxLength=0;
				}
				else if(maxLength>0)
				{
					int n = stack.size();
					if(n==0){
						sum+=maxLength;
						maxLength=0;
					}
				}
				stack.push(c);
			}
			else if(c==')'){
				if(!stack.isEmpty()){
					char b = stack.pop();
					if(b == '('){
						if(found==0)
							found++;
						maxLength+=2;
					}
				}
			}
		}
		int n = stack.size();
		if(n==0)
			sum+=maxLength;
		if(n>0 && found==1)
			sum+=maxLength;
		return sum;
	}
	/**********************************************REVISION***********************************************/
	//((()()((())
	
}
