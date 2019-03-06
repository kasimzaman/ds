package recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PalindromicDecomposition
{
	public void pd()
	{
		String strInput="abcac";
		pd2(strInput.toCharArray(),0);
	//	palindromicDecomposition(strInput);
		printAllPalindromes("abcac");
	}
	private String[] palindromicDecomposition(String strInput)
	{
		ArrayList<String> set = new ArrayList<>();
		List<ArrayList<String>> result = new ArrayList<>();
		palindromicDecompose(strInput.toCharArray(),0,strInput.length()-1,set,result);
		String[] r = new String[result.size()];
		
		for(int i=0;i<result.size();i++)
		{
			ArrayList<String> a=result.get(i);
			String e="";
			for(int j=0;j<a.size();j++)
			{
				e+=a.get(j);
			}
			r[i]=e;
		}
		return r;
    }
	//code to get all substrings
	//abcde 
	//i=0, j starts from i and goes till <str.len
	//i=0    a,ab,abc,abcd,abcde
	//i=1;   b,bc,bcd,bcde
	//i=2    c,cd,cde
	//i=3    d,de
	//i=4    e
	
	//O(N^2)
	private void printAllPalindromes(String s)
	{
		for(int i=0;i<s.length()-1;i++)
		{
			for(int j=i;j<s.length();j++)
				if(isPD(s,i,j))
				{
					for(int k=i;k<=j;k++)
						System.out.print(s.charAt(k));
					System.out.println();
				}
		}
		
	}
	private boolean isPD(String s,int low,int high)
	{
		while(low<high)
		{
			if(s.charAt(low)==s.charAt(high)){
				low++;high--;
			}
			else
				return false;
		}
		return true;
	}
	
	//O(2^n) and work of checking at each stage for palindrome O(n)
	private void palindromicDecompose(char[]arr,int start,int end,ArrayList<String> set,List<ArrayList<String>>result)
	{
		if(start>end){
			result.add(new ArrayList<String>(set));
			return;
		}
		for(int i=start;i<=end;i++)
		{
			if(isPalandrome(arr,start,i))
			{
				String str= "";
				for(int k=start;k<=i;k++)
					str+=arr[k];
				str+="|";
				set.add(str);
				palindromicDecompose(arr,i+1,end,set,result);
				set.remove(set.size()-1);
			}
		}
    }
	
	private boolean isPalandrome(char[]arr,int start,int end)
	{
		if(start>=end)
			return true;
		
		for(int i=start;i<end;i++)
		{
			if(arr[start]==arr[end])
				return isPalandrome(arr,start+1,end-1);
			else
				return false;
		}
		return false;
	}
	
	private boolean isPalindrome(String str) {
		int left = 0;
		int right = str.length() - 1;
	 
		while (left < right) {
			if (str.charAt(left) != str.charAt(right)) {
				return false;
			}
	 
			left++;
			right--;
		}
		String sst="aab";
		sst.toLowerCase();
		sst.toUpperCase();
		
		
	 
		return true;
	}
	
	/************************************************REVISION*************************************************/
	// "abcac";
	private String[] pd2(char[] arr,int start)
	{	
		for(int i=start;i<arr.length;i++)
		{
			if(isPalandrome(arr, start, i)){
				for(int k=start;k<=i;k++)
					System.out.print(arr[k]);
				System.out.println();
			}
			pd2(arr,start+1);
		}
		
		return null;
	}
	
	private boolean ispd(char[]arr,int left,int right)
	{	
		while(left<=right)
		{
			if(arr[left++]!=arr[right--])
				return false;
		}
		return true;
	}
}
