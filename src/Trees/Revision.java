package Trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Revision
{

	public static void main(String[] args)
	{
		Tree tree=new Tree();
		int[]a={10,15,20,25,30,35,40};
		tree.root = tree.bstFmArr(a, 0, a.length-1);
		tree.printParent(tree.root,10);
		
	}
}

class Tree
{
	Node root=null;
	class Node
	{
		Node left;
		Node right;
		Node parent;
		int data;
		Node(int data)
		{
			this.data=data;
		}
	}
	public Node bstFmArr(int[]a,int low,int high)
	{	
		if(low>high)
			return null;
		int mid = low+(high-low)/2;
		Node n = new Node(a[mid]);
		n.left =bstFmArr(a,low,mid-1);
		n.right = bstFmArr(a, mid+1, high);
		return n;
	}
	public Node insert(Node root,int element)
	{
		if(root==null)
			root=new Node(element);
		else if(root.data > element){
			root.left = insert(root.left,element);
			root.left.parent=root;
		}
		else if(root.data < element){
			root.right = insert(root.right,element);
			root.right.parent=root;
		}
		return root;
	}
	
	public void inorderR(Node root)
	{
		if(root==null)
			return;
		inorderR(root.left);
		System.out.println(root.data);
		inorderR(root.right);
	}
	
	public void inorderItr(Node root)
	{
		if(root==null)
			return;
		Stack<Node>stack=new Stack();
		Node current = root;
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
				System.out.println(n.data);
				current=n.right;
			}
		}
	}
	//Time complexity: O(n) 
	public void findKthItr(Node root, int k)
	{
		Stack<Node>stack=new Stack();
		Node current=root;
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
				k--;
				if(k==0)
					System.out.println(n.data);
				current=n.right;
			}
		}
	}
	//Time complexity: O(n) 
	public int findkthelementinorder(Node node, int k)
	{	
		if(node == null)
            return k;
		k=findkthelementinorder(node.left, k);
		k--;
		if(k==0)
			System.out.println(node.data);
        k=findkthelementinorder(node.right, k);
        return k;
	}
	
	public void inorderDes(Node root)
	{
		if(root==null)
			return;
		inorderDes(root.right);
		System.out.println(root.data);
		inorderDes(root.left);
	}
	public int findKthLast(Node root, int k)
	{
		if(root==null)
			return k;
		
		k=findKthLast(root.right, k);
		k--;
		if(k==0)
			System.out.println(root.data);
		k=findKthLast(root.left, k);
		return k;
	}
	public void preorder(Node root)
	{
		if(root==null)
			return;
		System.out.println(root.data);
		preorder(root.left);
		preorder(root.right);
	}
	
	public void preItr(Node root)
	{
		if(root==null)
			return;
		Stack<Node>stack=new Stack();
		stack.push(root);
		while(!stack.isEmpty())
		{
			Node n = stack.pop();
			System.out.println(n.data);
			if(n.right!=null)
				stack.push(n.right);
			if(n.left!=null)
				stack.push(n.left);
		}
	}
	public void postO(Node root)
	{
		if(root==null)
			return;
		if(root.left!=null)
			postO(root.left);
		if(root.right!=null)
			postO(root.right);
		System.out.println(root.data);
	}
	
	public void postI(Node root)
	{
		if(root==null)
			return;
		List<Integer>list=new ArrayList<>();
		Stack<Node>stack=new Stack();
		stack.push(root);
		while(!stack.isEmpty())
		{
			Node n = stack.pop();
			list.add(0,n.data);
			if(n.left!=null)
				stack.push(n.left);
			if(n.right!=null)
				stack.push(n.right);
		}
		for(int i : list)
			System.out.println(i+" ");
	}
	public Node findMin(Node root)
	{	
		if(root.left!=null)
			root=findMin(root.left);
		return root;
	}
	public Node findMinI(Node root)
	{
		while(root.left!=null)
		{
			root=root.left;
		}
		return root;
	}
	
	
	public Node findMax(Node root)
	{
		if(root==null)
			return root;
		root=findMax(root.right);
		return root;
	}
	public Node findnode(Node root,int n)
	{
		if(root==null)
			return root;
		if(root.data==n)
			return root;
		if(root.data>n)
			return findnode(root.left, n);
		else
			return findnode(root.right, n);
	}
	//Time Complexity: O(h) where h is height of tree.
	public Node findSucc(Node root,Node node)
	{
		if(node.right!=null)
			return findMinI(root.right);
		Node succ=null;
		//if not on the right side, then it's the max parent. If the Node has a no parent pointer maintained.
		//the find the node and keep first and store maximum parent
		while(root!=null)
		{
			if(root.data>node.data)
			{ 
				succ=root;
				root=root.left;
			}
			else
				root=root.right;
		}
		return succ;
	}
	//Time Complexity: O(h) where h is height of tree.
	public Node findSuccWithParent(Node root,Node node)
	{
		if(node.right!=null)
			return findMinI(root.right);
		Node p=node.parent;
		while(p!=null && node.data>p.data)
		{	
				p=p.parent;
		}
		return p;
	}
	//max in left sub tree
	public Node findPred(Node node)
	{
		if(node==null)
			return node;
		if(node.left!=null)
		{
			Node curr = node.left;
			while(curr.right!=null)
			{
				curr=curr.right;
			}
			return curr;
		}
		return node;
	}
	
	public void printParent(Node root,int n)
	{
		if(root==null)
			return;
		if(root.data==n)
			return;
		if(root.data>n)
			printParent(root.left, n);
		else
			printParent(root.right, n);
		System.out.println(root.data);
	}
	
	
	public Node delete(Node root, int n)
	{
		if(root==null)
			return root;
		if(root.data<n)
			root.right= delete(root.right, n);
		else if(root.data>n)
			root.left= delete(root.left, n);
		else
		{
			if(root.left==null)
				return root.right;
			else if(root.right==null)
				return root.left;
			//get smallest in right sub-tree. In-order successor.
			Node curr = root.right;
			while(curr.left!=null)
			{
				curr=curr.left;
			}
			root.data = curr.data;
			root.right = delete(root.right, root.data);
		}
		return root;
	}
}
	


