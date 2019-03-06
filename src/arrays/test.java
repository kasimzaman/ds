package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class test
{
	public static void main(String args[])
	{
		CompressString ss = new CompressString();
		ss.compressStr();
		
	}
	static String[] braces(String[] values)
	{
		String[] res= new String[values.length];
		if(res.length==0){
			res[0]="YES";
			return res;
		}
		java.util.Stack<Character> stack = new java.util.Stack<Character>();
		boolean correct=true;
		for(int j=0;j<values.length;j++)
		{
			String expression = values[j];
			correct=true;
			for(int i=0;i<expression.length();i++)
			{
				
				char item = expression.charAt(i);
	            if(item == '{' || item == '[' || item == '(')   
	            	stack.push(item);
	            else if(stack.isEmpty())
            	{
            		res[j]="NO";
            		correct=false;
            		break;
            	}
	            else if(item == '}' )
	            {	
	            	char c = (char)stack.pop();
	            	if(c != '{'){
	            		res[j]="NO";
	            		correct=false;
	            		break;
	            	}
	            }
	            else if (item == ']'  && !stack.isEmpty())
	        	{
	            	char c = (char)stack.pop();
	            	if(c != '['){
	            		res[j]="NO";
	            		correct=false;
	            		break;
	            	}
	        	}
	            else if(item == ')'  && !stack.isEmpty())
	            {
	            	char c = (char)stack.pop();
	            	if(c != '('){
	            		res[j]="NO";
	            		correct=false;
	            		break;
	            	}
	            }
			}
			if(correct)
				res[j]="YES";
		}
		
		return res;
    }
	
	static class Pair
	{
		int l;
		int r;
		Pair(int l ,int r)
		{
			this.l=l;
			this.r=r;
		}
	}
	
	static int numberOfPairs(int[] a, long k)
	{
		int count=0;
		 int l, r;
		 int arr_size= a.length; 
	     List<Pair>pairs=new ArrayList<>();
	     Arrays.sort(a);
	     l = 0;
	     r = arr_size-1; 
        while (l < r)
        {
             if(a[l] + a[r] == k)
             {
            	 Pair p = new Pair(l, r);
            	 pairs.add(p);
             }
             else if(a[l] + a[r] < k)
                  l++;
             else 
                  r--;
        } 
        boolean found=false;
        for(int i=0;i<pairs.size()-1;i++)
        {
        	found=false;
        	Pair p = pairs.get(i);
        	for(int j=i+1;j<pairs.size();j++)
        	{
        		Pair p2 = pairs.get(j);
        		if(p.l!=p2.l || p.r!=p2.r)
        			count++;
        		
        	}
        }
        return count;
    }
	
	
	public static void findSumInUnSortedArray()
	{
		int arr[] = {1, 5, 7, 10, 5} ;
		HashMap<Integer,Integer> map = new HashMap<>();
		int k = 17;
		for(int i=0;i<arr.length;i++)
		{
			if(map.containsKey(arr[i]))
				System.out.println(arr[i]+" : "+map.get(arr[i]));
			else
				map.put(k-arr[i], arr[i]);
		}
		
	}
	
	
	
	public static void isBalanced(){
	     
		 String expression="{{";
		java.util.Stack<Character> stack = new java.util.Stack<Character>();
        for(int i=0;i<expression.length();i++)
        {
        	char item = expression.charAt(i);
            if(item == '{' || item == '[' || item == '(')   
            	stack.push(item);
            else if(stack.isEmpty())
            	System.out.println("NO");
            else if(item == '}' )
            {	
            	char c = (char)stack.pop();
            	if(c != '{')
            		System.out.println("NO");
            	
            }
            else if (item == ']'  && !stack.isEmpty())
        	{
            	char c = (char)stack.pop();
            	if(c != '[')
            		System.out.println("NO");
        	}
            else if(item == ')'  && !stack.isEmpty())
            {
            	char c = (char)stack.pop();
            	if(c != '(')
            		System.out.println("NO");
            }
        }
        if(!stack.isEmpty())
        	System.out.println("NO");
        else
        System.out.println("YES");
	}

}
