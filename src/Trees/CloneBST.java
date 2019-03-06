package Trees;

import java.util.LinkedList;
import java.util.Queue;

//file:///Users/ali/Documents/workspace/KickStart/Notes/Tree/clone.png
public class CloneBST 
{
	public void clone(Node root)
	{
		Node t=null;
		
		Node clone = clonetree(root,t);
		System.out.println("--------");
		print(clone);
	}
	private Node cloneTree(Node root)
	{
		Node clone=null;
		if(root==null)
			return root;
		Queue<Node> que = new LinkedList<Node>();
		Queue<Node> que2 = new LinkedList<Node>();
		que.add(root);
		clone = new Node(root.val);
		que2.add(clone);
		while(!que.isEmpty())
		{
			Node node = que.remove();
			Node node2 = que2.remove();
			
			System.out.println(node.val);
			if(node.left!=null){
				node2.left = new Node(node.left.val);
				que.add(node.left);
				que2.add(node2.left);
			}
			if(node.right!=null){
				node2.right = new Node(node.right.val);
				que.add(node.right);
				que2.add(node2.right);
			}
		}
		return clone;
    }
	private void print(Node node)
	{
		if(node==null)
			return;
		print(node.left);
		System.out.println(node.val);
		print(node.right);
	}
	
	private Node clonetree(Node node, Node clone)
	{
		if(node == null)
			return node;
		clone = new Node(node.val);
		clone.left = clonetree(node.left,clone);
		clone.right = clonetree(node.right,clone);
		return clone;
	}
	/**********************************/
	


}
