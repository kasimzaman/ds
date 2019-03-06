package Trees;

import java.util.Stack;

public class TreeIterator
{
	Stack<Node>stack = new Stack<>();
	Node root=null;
	int next=0;
	public void ti(Node node)
	{
		this.root=node;
		inOrderPush(this.root,stack);
		while(hasNext())
			System.out.println(next().val);
	}
	
	private Node next()
	{
		if(!stack.isEmpty())
		{
			Node node=stack.pop();
			inOrderPush(node.right,stack);
			return node;
		}
		else
			return null;
	}
	private boolean hasNext()
	{
		if(!stack.isEmpty())
			return true;
		else
			return false;
	}
	private void inOrderPush(Node n,Stack<Node>stack)
	{
		if(n==null)
			return;
		Node current=n;
		while(current!=null)
		{
			if(current!=null)
			{
				stack.push(current);
				current=current.left;
			}
		}
	}

}
