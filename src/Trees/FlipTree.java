package Trees;

import java.util.Stack;
//file:///Users/ali/Documents/workspace/KickStart/Notes/Tree/flip.png
public class FlipTree
{
	public void ft(Node n)
	{
		Node t = flipTree(n);
		print(t);
	}
	private Node flipTree(Node node)
	{
		if(node==null)
			return null;
		Node l=flipTree(node.left);
		Node r=flipTree(node.right);
		node.left = r;
		node.right = l;
		return node;
    }
	private void print(Node node)
	{
		if(node==null)
			return;
		Node current=node;
		Stack<Node> stack = new Stack<Node>();
		while(!stack.isEmpty() || current!=null)
		{
			if(current!=null)
			{
				stack.push(current);
				current=current.left;
			}
			else
			{
				Node n = stack.pop();
				System.out.println(n.val);
				current=n.right;
			}
		}
	}
	

}
