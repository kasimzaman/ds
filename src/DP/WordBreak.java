package DP;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
//file:///Users/ali/Documents/workspace/KickStart/Notes/DP/WordBreak/wb.png
public class WordBreak 
{	static int calls=0;
	static Set<String> dictionary = new HashSet<String>();
	static String strWords;
	public void wordBreak()
	{
		String strWord="iamsuperman";
		String[] strDict={"i","am","iam","super","man","superman"};
		//String[] strDict={"in","ki","kist","inki","st"};
		//in  ki  st  ,  in kist  ,  inki  st
		
		ArrayList<String> res = new ArrayList<>();
		for(String e : strDict)
			dictionary.add(e);
		
		Map<Integer,ArrayList<String>>memo=new HashMap<>();
		//wordb(strWord,"",0);
		Map<String,Boolean>memo2 = new HashMap<>();
		wordb2(strWord,memo2);
		System.out.println("calls:"+calls);
//		strWords = strWord;
//		int[][]cache = new int[strWord.length()][strWord.length()];
//		for(int i=0;i<strWord.length();i++)
//		{
//			for(int j=0;j<strWord.length();j++)
//				cache[i][j]=Integer.MIN_VALUE;
//		}
//		ArrayList<String> finalList = new ArrayList<>();
//		wordBreak(res,0,finalList,cache);
//		for(String s : finalList)
//			System.out.println(s);
//		
		
	}
	private String[] wordBreak(List<String> res,int beginIndex,ArrayList<String> finalList,int[][]cache )
	{	
		for(int i=beginIndex;i<strWords.length();i++)
		{
			String word = strWords.substring(beginIndex, i+1);
			
			if(dictionary.contains(word))
			{	
				res.add(word);
				wordBreak(res,i+1,finalList,cache);
				copyResults(res,word,finalList);
			}
		}
		return finalList.toArray(new String[finalList.size()]);
	}
	private ArrayList<String> copyResults(List<String> soFar,String word,ArrayList<String> finalList)
	{
		int len = getLength(soFar);
		if(len<strWords.length())
			soFar.remove(word);
		if(len==strWords.length()){
			finalList.add(copyList(soFar));
			soFar.remove(word);
		}
		return finalList;
	}
	private String copyList(List<String> result)
	{	
		String temp="";
		for(String s : result)
			temp+=s+" ";
		return temp;
	}
	private int getLength(List<String> res)
	{
		int len=0;
		for(String s : res)
		{
			len+=s.length();
		}
		return len;
	}
	/**************************************************************************/
	////String strWord="i am super man";
	//String[] strDict={"inter","interview","kick","kicker","kickstart","star","start","view"};
	
	//The Dynamic Programming solution only finds whether it is possible to break a word or not.
	//Here we need to print all possible word breaks. 
	private void wordb(String str, String sofar,int start)
	{	
		String[]list ;
		
		
		if(start == str.length())
			System.out.println(sofar);
		for(int i=start;i<str.length();i++)
		{
			String s=str.substring(start, i+1);
			if(dictionary.contains(s))
			{
				wordb(str, sofar+s+" ", i+1);
			}
		}
	}
	
	private boolean wordb2(String str,Map<String,Boolean>memo)
	{calls++;
		if(str.length()==0)
			return true;
		if(memo.containsKey(str))
			return memo.get(str);
		for(int i=1;i<=str.length();i++)
		{
			if(dictionary.contains(str.substring(0, i)) && wordb2(str.substring(i),memo)){
				memo.put(str, true);
				return true;
			}
		}
		memo.put(str, false);
		return false;
	}
	
}
