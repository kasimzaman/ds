package recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

   /*  file:///Users/ali/Documents/workspace/KickStart/Notes/Recursion/generateall.png
    *  file:///Users/ali/Documents/workspace/KickStart/Notes/Recursion/generateall2.png
    *  
    */
public class ExpressionEval 
{
	public void ee()
	{
		String strDigits="222";
		int K=24;
		expEvl(strDigits.toCharArray(),0,"",K);
		//expressionCreator(strDigits,K);
	}
	private String[] expressionCreator(String strDigits, int K)
	{
		char[]arr = strDigits.toCharArray();
		List<String> soFar = new ArrayList<>();
		List<String> result = new ArrayList<>();
		expressionEval(arr,K,0,soFar,result);
		String[] res= new String[result.size()];
		int index=0;
		for(String e:result)
			res[index++]=e;
		return res;
    }
	static int call=0;
	private void expressionEval(char[]arr, int K,int index,List<String> soFar,List<String> result)
	{call++;
	
		if(evaluate(soFar,K))
		{
			String str="";
			for(String e:soFar){
				if(e!=" ")
				str+=e;
			}
			result.add(str+"="+K);
		}
		if(index>=arr.length)
			return;
		int c = arr[index]-'0';
		String s=String.valueOf(c);
		if(soFar.size()==0){
			soFar.add(s);
			expressionEval(arr,K,index+1,soFar,result);
		}
		else
		{
			soFar.add("+");
			soFar.add(s);
			expressionEval(arr,K,index+1,soFar,result);
		}
		if(!soFar.isEmpty())
			soFar.remove(soFar.size()-1);
		if(!soFar.isEmpty())
			soFar.remove(soFar.size()-1);
		soFar.add("*");
		soFar.add(s);
		expressionEval(arr,K,index+1,soFar,result);
		if(!soFar.isEmpty())
			soFar.remove(soFar.size()-1);
		if(!soFar.isEmpty())
			soFar.remove(soFar.size()-1);
		
		soFar.add(" ");
		soFar.add(s);
		expressionEval(arr,K,index+1,soFar,result);
		if(!soFar.isEmpty())
			soFar.remove(soFar.size()-1);
		if(!soFar.isEmpty())
			soFar.remove(soFar.size()-1);
	}
	private List<String> join(List<String> list)
	{
		List<String> joined = new ArrayList<>();
		for(int i=0;i<list.size();i++)
		{
			if((i==0) && (list.get(i)==" " || list.get(i)=="+" || list.get(i)=="*"))
				break;
			if(list.get(i)!=" ")
				joined.add(list.get(i));
			else{
				String s=joined.remove(joined.size()-1);
				s+=list.get(i+1);
				joined.add(s);
				i++;
			}
		}
		return joined;
	}
	private boolean evaluate(List<String> list,int k)
	{
		int res=0;
		int old=Integer.MAX_VALUE;
		String operation="";
		List<String> joined = join(list);
		Stack<String> stack = new Stack<>();
		for(int i=0;i<joined.size();i++)
		{	
			if(joined.get(i)=="+")
			{
				stack.push("+");
			}
			else if(joined.get(i)=="*")
			{	
				String item=stack.pop();
				old =Integer.parseInt(item);
				i++;
				int c =Integer.parseInt(joined.get(i));
				res=old*c;
				stack.push(String.valueOf(res));
			}
			else
				stack.push(joined.get(i));
		}
		res=0;
		int p=0;
		while(!stack.isEmpty())
		{	
			int c= Integer.parseInt(stack.pop());
			if(operation=="+")
				res+=c;
			else if(operation=="-"){
				
			}
			else
				res=c;
			if(!stack.isEmpty())
				operation=stack.pop();
		}
		if(res==k)
		{
			for(String s :joined)
				System.out.print(s+" ");
			System.out.println();
			return true;
		}
		else
			return false;
	}
	
	private int getConcated(char[]arr,int c)
	{
		String str="";
		for(int i=c;i<arr.length;i++)
		{
			str+=arr[i];
		}
		return Integer.parseInt(str);
	}
	
	private int concat(int a, int b)
	{
		String s = String.valueOf(a)+String.valueOf(b);
		return Integer.parseInt(s);
	}
	private void expressionEval2(char[]arr, int K,int index,int soFar,String exp)
	{
		if(soFar==K)
		{
			//System.out.println(soFar);
			System.out.println(exp);
		}
		if(index>=arr.length)
			return;
		int c = arr[index]-'0';
		String prev=exp;
		if(soFar==0)
			exp=c+"+";
		else
			exp=soFar+"+"+c+"+";
		expressionEval2(arr,K,index+1,soFar+c,exp);
		exp=prev;
	//	expressionEval(arr,K,index+1,soFar*c,exp);
		expressionEval2(arr,K,index+1,concat(soFar,c),exp);
	}
	
	
	
	
	/********************************************REVISION*************************************************/
	// "","*","+"   "1234"  1*2*3*4
	//                       1*2*3+4
	//O(4^N)
	private void expEvl(char[] arr,int index,String exp, int k)
	{
		String s="";
		int a=0;
		if(index>=arr.length)
		{
			if(eval(exp)==k)
				System.out.println(exp);
			return;
		}
		
		// *
		if(index==0)
		{
			a =arr[index]-'0';
			s = Integer.toString(a);
			expEvl(arr,index+1,exp+s,k);
		}
		else
		{
			a =arr[index]-'0';
			s = Integer.toString(a);
			expEvl(arr,index+1,exp+'*'+a,k);
		}
		
		// +
		a =arr[index]-'0';
		s = Integer.toString(a);
		expEvl(arr,index+1,exp+'+'+a,k);
		
		// ""
		a =arr[index]-'0';
		if(index==0)
			System.out.println();
		expEvl(arr,index+1,exp+'"'+a,k);
		
		return;
	}
	//1*2*3"4
	private int eval(String exp)
	{ 
		int res=0;
		List<String> s=new ArrayList<>();
		String str="";
		for(int i=0;i<exp.length();i++)
		{	
			char c =exp.charAt(i);
			if((i==0) && (c=='"' || c=='+' || c=='*'))
				return 0;
			if(exp.charAt(i)=='"')
			{
				str+= s.remove(s.size()-1);
			}
			else
			{
				if(str.length()>0){
					s.add(str+Character.toString(c));
					str="";
				}
				else
					s.add(Character.toString(c));
			}
		}
		Stack<String>stack = new Stack<>();
		for(int i=0;i<s.size();i++)
		{	
			if(!s.get(i).equals("*"))
				stack.push(String.valueOf(s.get(i)));
			else
			{
				i++;
				String a = stack.pop();
				String b =String.valueOf(s.get(i));
				int c = Integer.parseInt(a) * Integer.parseInt(b); 
				String p = Integer.toString(c);
				stack.push(p);
			}
		}
		System.out.println();
		while(!stack.isEmpty())
		{
			String a = stack.pop();
			if(!a.equals("+"))
				res+=Integer.parseInt(a);
			else
			{
				if(!stack.isEmpty()){
					a = stack.pop();
					res+=Integer.parseInt(a);
				}
			}
		}
		
		return res;
	}

}
