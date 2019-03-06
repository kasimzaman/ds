package arrays;

import java.util.HashSet;
import java.util.Set;

public class Partition 
{
	static final int ALPHABET_SIZE = 26;
	public void subSeqPartition(char[] list)
	{
		int[]freqMap=new int[ALPHABET_SIZE]; 
		//Build the character frequency map
		for(int i = 0;i < list.length; i++){
			int index = list[i]-'a';
			freqMap[index]++;
		}
		
		Set<Character> set = new HashSet<Character>();
		int pCount=0;
		for(int i = 0;i < list.length; i++){
			pCount++;
			char c = list[i];
			set.add(c);
			int index = c-'a';
			freqMap[index]--;
			
			if(freqMap[index] == 0){
				set.remove(c);
				if(set.size() == 0){
					System.out.println("Partition :" + pCount );
					pCount=0;
				}
			}
		}
	}
}
