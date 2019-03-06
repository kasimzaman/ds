package recursion;

import java.util.ArrayList;
import java.util.List;

public class SubSets 
{
	public void ss()
	{
		ss("abc",0,"");
		//int[]a={1,2,3};
		//List<Integer> list = new ArrayList<>();
		//printAllSubSets(a,0,list);
	}
	
	private void printAllSubSets(int[]a,int start,List<Integer> list)
	{	
		if(start>=a.length)
		{
			System.out.print("{");
			for(int i:list){
				System.out.print(i);
			}
			System.out.print("}");
			return;
		}
		list.add(a[start]);
		printAllSubSets(a,start+1,list);
		list.remove(list.size()-1);
		printAllSubSets(a,start+1,list);
	}
	
	private void ss(String s,int start, String sofar)
	{
		if(start>s.length()-1)
			System.out.println(sofar);
		else
		{
			ss(s,start+1,sofar+s.charAt(start));
			ss(s,start+1,sofar);
		}
	}

}
