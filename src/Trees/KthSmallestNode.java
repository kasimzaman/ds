package Trees;

import java.util.Stack;

public class KthSmallestNode
{
	Node root=null;
	public void kse(Node root)
	{
		
	}
	//O(logN)
	
	//Using static variables to store the k and result
	static int kth=0;
	static int number=0;
	private void inorder(Node root)
	{
		if(root==null)
			return;
		inorder(root.left);
		kth--;
		if(kth==0)
		{
			number=root.val;
			return;
		}
		inorder(root.right);
	}
	
	//Using iterative. Do in-order traversal.
	private void inOrderT(Node n,int k)
	{
		int counter=k;
		if(n==null)
			return;
		Node current=n;
		Stack<Node> stack = new Stack<>();
		while(!stack.isEmpty() || current!=null)
		{
			if(current!=null)
			{
				stack.push(current);
				current=current.left;
			}
			else
			{
				Node e = stack.pop();
				counter--;
				if(counter==0)
					System.out.println(e.val);
				current=e.right;
			}
		}
	}
	
}
