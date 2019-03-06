package recursion;

import java.util.Stack;

public class PrintIncreasingSSeq
{
	//so when you are doing recursion and breaking it into sub-problems, these big recursive call trees, and if you can kill an entire branch of
	//the call tree, just because its invalid, is a way better optimization then putting that checking in the base case.
	public void piss()
	{
		Stack<Integer>sofar=new Stack<>();
		int[]a={1,2,3,4};
		printSeq(a,0,0,"");
		
	}
	//Print all increasing sub sequence in a array
	//3,4,72,41,11,21 => 3,4,41  and 3,4,72  and 3,4,11,21 etc
	//Why can't I do if(a[i-1] < a[i] or a[i]>a[i-1])
	//Because 3,4,41 is valid. But if I compare 41 and 72 then 41 wont make it to the 3,4,41 set, which is a valid set.
	private void printSeq(int[]a,int i,int last,String sofar)
	{
		if(i==a.length){
			System.out.println(sofar);
			return;
		}
		printSeq(a, i+1, a[i], sofar);            //don't take, and move forward
		
		if(a[i]>last)//putting the guard at the end. Can help us kill entire branches of the recursion that don't meet out criteria.
			printSeq(a,i+1,a[i],sofar+a[i]+" ");  // take, and move forward
	}
	
}
