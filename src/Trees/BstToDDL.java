package Trees;

import java.util.Stack;
//file:///Users/ali/Documents/workspace/KickStart/Notes/Tree/dll.png
public class BstToDDL 
{
	Node head=null;
	public void bstddl(Node root)
	{
		
		Node prev=BSTtoDLL(root,null);
		if(prev!=null){
			prev.right=head;
			head.left=prev;
		}
		Node current=head;
		while(current.right!=head){
			System.out.print(current.val+" ");
			current=current.right;
		}
		System.out.print(current.val+" ");
	}
	private Node BSTtoLL(Node root)
	{
		Node head=null;
		if(root==null)
			return root;
		Node current=root;
		Stack<Node> stack = new Stack<Node>();
		Node prev=null;
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
				if(prev==null)
					head=n;
				else
				{
					prev.right=n;
					n.left=prev;
				}
				prev=n;
				current=n.right;
			}
		}
		if(prev!=null){
			prev.right=head;
			head.left=prev;
		}
		return head;
    }
	/* time complexity is O(n)
	   The idea is to do in-order traversal of the binary tree. While doing in-order traversal,
	   keep track of the previously visited node in a variable say prev. For every visited node,
	   make it next of prev and previous of this node as prev.
	 */
	private Node BSTtoDLL(Node root,Node prev)
	{	
		if(root==null)
			return prev;
		
		prev=BSTtoDLL(root.left,prev);
		if(prev==null)
			head=root;
		else
		{
			prev.right=root;
			root.left=prev;
		}
		prev=root;
		prev=BSTtoDLL(root.right,prev);
		return prev;
	}
	

}
