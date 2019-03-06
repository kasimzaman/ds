package recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//O(N^3)
public class ExpressionEvalR
{
	public void ee()
	{
		String strDigits="222";
		int K=24;
		expEvl(strDigits.toCharArray(),0,"",K);
		
	}
	private void expEvl(char[]arr,int index,String exp, int K)
	{
		if(index>=arr.length)
		{
			if(eval(exp)==K)
				System.out.println(exp);
			return;
		}
		char c = arr[index];
		if(index==0)
			expEvl(arr, index+1, exp+c, K);
		else
		{
			expEvl(arr, index+1, exp+"+"+c, K);
			expEvl(arr, index+1, exp+"*"+c, K);
			expEvl(arr, index+1, exp+"\""+c, K);
		}
	}
	//2+3"4
	private int eval(String exp)
	{
		List<String>list=new ArrayList<>();
		String prev="";
		for(int i=0;i<exp.length();i++)
		{
			char c = exp.charAt(i);
			if((i==0) && (c=='*' || c=='+' || c=='"'))
				return Integer.MIN_VALUE;
			if(c=='"')
				prev = list.remove(list.size()-1);
			else
			{
				list.add(prev+Character.toString(c));
				prev="";
			}
		}
		Stack<String>stack=new Stack();
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).equals("*"))
			{
				int a = Integer.parseInt(stack.pop());
				int b = Integer.parseInt(list.get(++i));
				stack.push(Integer.toString(a*b));
			}
			else
				stack.push(list.get(i));
		}
		int result=0;
		while(!stack.isEmpty())
		{
			String s= stack.pop();
			if(!s.equals("+"))
				result+=Integer.parseInt(s);
			else
				result+=Integer.parseInt(stack.pop());
		}
		return result;
	}
}
