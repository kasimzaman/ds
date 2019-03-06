package ListAndStack;

import java.util.Stack;
/*
 * To handel this case : }(1*2)+3*(5-6)
 * when encounter closing parenthesis, if the stack is empty then its false, as an opening paren should have been there.
 * file:///Users/ali/Documents/workspace/KickStart/Notes/ListAndStack/MatchingParan/validMatchingParan.png
 * */
public class ValidMatchingParen 
{
	public void validParen()
	{
		String str ="}(1*2)+3*(5-6)";
		System.out.println(hasMatchingParantheses(str));
	}
	public boolean hasMatchingParantheses(String strExpression) 
	{
		boolean bResult=true;
		Stack<Character> stack = new Stack<Character>();
		for(int index=0;index<strExpression.length();index++)
		{
			char c = strExpression.charAt(index);
			if(c=='(' || c=='[' || c=='{')
				stack.push(c);
			else if(c==')'){
				if(!stack.isEmpty()){
					if(stack.pop()!='(')
						return false;
				}else
					return false;
				
			}else if(c==']'){
				if(!stack.isEmpty()){
					if(stack.pop()!='[')
						return false;
				}else
					return false;
			}else if(c=='}'){
				if(!stack.isEmpty()){
					if(stack.pop()!='{')
						return false;
				}else
					return false;
			}
		}
		
		
		return bResult;
    }

}
