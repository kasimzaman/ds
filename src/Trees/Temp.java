package Trees;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import Trees.SiblingPointers.SiblingTNode;

public class Temp
{
	Stack<Node>stack=new Stack<>();
	private void build(Node root)
	{
		fillStack(root);
	}
	private int next()
	{
		Node node = stack.pop();
		if(node.right!=null)
			fillStack(node.right);
		return node.val;
	}
	private void fillStack(Node n)
	{
		Node curr=n;
		while(curr!=null)
		{
			stack.push(curr);
			curr=curr.left;
		}
	}
	
	
	
}
