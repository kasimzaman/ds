package prob;

public class hk1 
{

	public static void main(String[] args) 
	{
		int[] inputs={5,1,2,4,2,2};
		float allowedIncrease=1.5f;
		if(alert(inputs,3,2.0F))
			System.out.println("true");
		else
			System.out.println("false");
	}
	
	static String longestEvenWord(String sentence)
	{
		String word="";
		int maxLen=Integer.MIN_VALUE;
		 
		
		
		String[] words = sentence.split(" ");
		for(int i=0;i<words.length;i++)
		{
			String a = words[i];
			if(a.length() % 2 == 0)
			{
				if(maxLen < a.length())
				{
					maxLen=a.length();
					word=a;
				}
			}
		}
		if(word.length()==0)
			return "00";
		else
			return word;
    }
	
	
	
	//{1,2,100,2,2};
	
	static boolean alert(int[] inputs, int windowSize, float allowedIncrease)
	{
		int sum=0;
		int maxVal=Integer.MIN_VALUE;
		float prevAvg=0;
		int size=windowSize;
		int index=0;
		for(int i=0;i<inputs.length-windowSize;i++)
		{
			index=i;
			while(size!=0 && index<inputs.length)
			{
				if(maxVal < inputs[index])
					maxVal=inputs[index];
				sum+=inputs[index];
				size--;
				index++;
			}
			
			float currentAvg = (float)sum/windowSize;
			sum=0;
			if(prevAvg!=0)
			{
				prevAvg = prevAvg * allowedIncrease;
			
				if(currentAvg > prevAvg)
					return true;
			}
			prevAvg = currentAvg;
			
			float diff = Math.abs(maxVal - currentAvg);
			
			currentAvg = currentAvg * allowedIncrease;
			if(diff > currentAvg)
				return true;
			size=windowSize;
				
		}
		return false;
    }
	
	
	
	
	
	
	
	
	
	
	

}
