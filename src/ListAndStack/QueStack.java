package ListAndStack;

import java.util.Stack;

public class QueStack
{
	Stack<Integer> stack1 ;
    Stack<Integer> stack2 ;
    
   
    public void enQueue(int x)
    {
        stack1.push(x);
    }
    
    public int deQueue()
    {
        int x=0;
        if(stack1.isEmpty() && stack2.isEmpty())
        {
            System.out.println("Q is empty");
            System.exit(0);
        }
        if(stack2.isEmpty())
        {
            while(!stack1.isEmpty())
            {
            	x =stack1.pop();
            	stack2.push(x);
            }
        }
        if(!stack2.isEmpty())
        	x = stack2.pop();
        return x;
    }
}
