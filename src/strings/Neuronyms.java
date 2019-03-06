package strings;

public class Neuronyms
{
	public void neu()
	{
		generateNeu("abcdefg");
	}
	
	//Algorithm: Loop the string
	//Increment from left side, and keep right fixed. and keep calculating the distance in between.
	//Keep left pointer fixed and decrement right by i;
	
	private void generateNeu(String str)
	{
		if(str.length()<5)
			return;
		int n=str.length();
		int d = n-2;//current length -2
		System.out.println(str.substring(0, 1) + Integer.toString(d)  + str.substring(n-1) );
		for(int i=1;i<n-3;i++)
		{
			d=n-i;//get current length
			d = d-2;
			System.out.println(str.substring(0, i+1) + Integer.toString(d)  + str.substring(n-1) );
			
			System.out.println(str.substring(0, i) + Integer.toString(d)  + str.substring(n-1-i) );
			
		}

	}

}
