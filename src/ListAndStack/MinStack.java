package ListAndStack;

import java.util.Stack;

/*
 * You can use a extra int  variable in the stack node itself. And keep adding the min val if it is less that the one already added to stack.
 * To handle duplicates make sure to add to minStack even if it already present by doing if(item<=minstack.peek) instead if if(item<minstack.peek)
 * 	this is because the original stack will have duplicates in it and when it pops one of those like (2,3,2) the min will still be 2.
 * But if you have only one of these variables in minStack and after that is poped , you will have the wrong min remaining.	
 * file:///Users/ali/Documents/workspace/KickStart/Notes/ListAndStack/minstack.png
 */
public class MinStack
{
	Stack<Integer> stack = new Stack<Integer>();
	Stack<Integer> aux = new Stack<Integer>();
	
	public void push(int item)
	{
		if(aux.isEmpty())
			aux.push(item);
		else
		{
			if(aux.peek()>=item)
				aux.push(item);
		}
		stack.push(item);
	}
	
	public int pop()
	{
		int item = stack.pop();
		if(!aux.isEmpty())
		{
			if(aux.peek()==item)
				aux.pop();
		}
		return item;
	}
	
	public int getMin()
	{
		return aux.peek();
	}

}
