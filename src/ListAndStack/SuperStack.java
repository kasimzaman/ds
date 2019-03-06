package ListAndStack;

/*
 file:///Users/ali/Documents/workspace/KickStart/Notes/ListAndStack/superstack1.png
 file:///Users/ali/Documents/workspace/KickStart/Notes/ListAndStack/superstack2.png
 file:///Users/ali/Documents/workspace/KickStart/Notes/ListAndStack/superstack3.png
 file:///Users/ali/Documents/workspace/KickStart/Notes/ListAndStack/superstack4.png
 * */

//https://www.geeksforgeeks.org/stack-data-structure-introduction-program/
public class SuperStack 
{
	int[] stack;
	int top=-1;
	int bottom=-1;
	public void superStack(String[] operations) 
	{	
		stack = new int[operations.length];
		for(int index=0;index<operations.length;index++)
		{
			String[] opr = operations[index].split(" ");
			
			if(opr[0].compareTo("push")==0)
				push(Integer.parseInt(opr[1]));
			else if(opr[0].compareTo("pop")==0){
				if(top>-1)
					pop();
			}
			else if(opr[0].compareTo("inc")==0)
				inc(Integer.parseInt(opr[1]),Integer.parseInt(opr[2]));
			
			System.out.println((top==-1)?"EMPTY":stack[top]);
		}
    }
	
	private void push(int element)
	{	
		if((bottom ==-1) && (top==-1))
		{
			bottom=top=0;
			stack[top]=element;
		}
		else{
			top++;
			stack[top]=element;
		}
		
		
	}
	private int pop()
	{
		int element=-1;
		if(top!=-1){
			element=stack[top];
			top--;
			if(top<bottom)
				top=bottom=-1;
		}
		return element;
	}
	private void inc(int b, int k)
	{
		if(bottom!=-1)
			return;
		for(int i=0;i<b;i++)
		{
			if(i<=top)
				stack[i]+=k;
		}
		
	}
}
