package Sorting;

public class SortCharacters
{
	public void sc()
	{
		String inString="This is easy";
		System.out.println(sortCharacters(inString));
	}
	
	private String sortCharacters(String inString)
	{
		String s="";
		int[]map=new int[256];
		char[]str=inString.toCharArray();
		for(int i=0;i<str.length;i++)
		{
			int c = str[i];
			map[c]++;
		}
		for(int i=0;i<256;i++)
		{
			if(map[i]==0)
				continue;
			char c = (char)i;   //IMPORTANT   IMPORTANT    IMPORTANT  not map[i]
			for(int j=0;j<map[i];j++)
				s+=c;		
		}
		return s;
    }

}
