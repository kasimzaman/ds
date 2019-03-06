package Trees;

import java.util.Stack;
//file:///Users/ali/Documents/workspace/KickStart/Notes/Tree/pot.png
public class PostOrder
{
	public void po(Node n)
	{
		//poR(n);
		//System.out.println();
		poI(n);
	}
	/*
	 * Use 2 stacks.
	 * Push root in stack1.
	 * Pop node from stack1 and push it in stack2. If node has children push them in stack1(left then right)
	 * we do left first then right in stack 1 because when we will pop children, we will pop right first then left(reverse) and
	 * in stack 2, right will be pushed before left child. So in printing from stack2, left will be printed before right as in postorder.	
	 * repeat.
	 * 
	 */
	private void poI(Node root)
	{
		if(root==null)
			return;
		Stack<Node> stack1 = new Stack<Node>();
		Stack<Node> stack2 = new Stack<Node>();
		stack1.push(root);
		
		while(!stack1.isEmpty())
		{
			Node n = stack1.pop();
			stack2.push(n);
			if(n.left!=null)
				stack1.push(n.left);
			if(n.right!=null)
				stack1.push(n.right);
		}
		while(!stack2.isEmpty())
		{
			System.out.println(stack2.pop().val);
		}
	}
	private void poR(Node root)
	{
		if(root==null)
			return;
		poR(root.left);
		poR(root.right);
		System.out.println(root.val);
	}

}
