package strings;

public class PrintSinusoidally 
{
	public void ps()
	{
		int height=3;
		String str="Google Worked";
		print(str);
		
		//2  6  10
		//1  3  5  7 9  11
		//0  4   8  12
	}
	
	private void print(String s)
	{	
		System.out.printf("%4s", " ");
		for(int i=2;i<=10;i+=4){
			if(s.charAt(i)==' ')
				System.out.print("~");
			else
				System.out.print(s.charAt(i));
			System.out.printf("%4s", " ");
		}
		System.out.println();
		System.out.printf("%2s", " ");
		for(int i=1;i<=11;i+=2){
			System.out.print(s.charAt(i));
			System.out.printf("%2s", " ");
		}
		System.out.println();
		for(int i=0;i<=12;i+=4){
			System.out.print(s.charAt(i));
			System.out.printf("%5s", " ");
		}
	}
	private void space(int n)
	{
		
	}
   
}
