package Trees;

import java.io.IOError;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class TreeTest
{
	List<Integer> list = new ArrayList<>();
	public void build(Node n)
	{
//		TreeNode n1= new TreeNode(2);
//		TreeNode n2= new TreeNode(1);
//		TreeNode n3= new TreeNode(3);
//		n1.left_ptr=n2;
//		n1.right_ptr=n3;
//		kth_smallest_element(n1,3);
	//	int[]a={1,2,3};
	//	build_balanced_bst(a);
		
	
	}
	private void kth_smallest_element(Node root, int k)
	{
		kth=k;
		inorder(root);
		System.out.println(number);
    }
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
	
	private TreeNode build_balanced_bst(int[] a) 
    {
        return buildFromArray(a,0,a.length-1);
    }
	private TreeNode buildFromArray(int[]a,int low,int high)
	{
		if(low>high)
			return null;
		int mid = low+(high-low)/2;
		TreeNode node = new TreeNode(a[mid]);
		node.left_ptr = buildFromArray(a, low, mid-1);
		node.right_ptr = buildFromArray(a, mid+1, high);
		return node;
	}
	
	//O(n), You are visiting every node to find the max height
	private int find_height(int k, TreeNode1 root)
    {
		int max=Integer.MIN_VALUE;
		if(k==0)
			return 0;	
		for(int i=0;i<k;i++)
		{
			TreeNode1 node = root.children.elementAt(i);
			int s = node.children.size();
			int count =1+ find_height(node.children.size(),node);
			if(max<count)
				max=count;
		}
        return max;
    }



}
class TreeNode1
{
	//int val;														// To find height of tree, value stored in nodes does not matter. So in input also we are not given this field. 
	Vector<TreeNode1> children = new Vector<TreeNode1>(0);
	TreeNode1()
	{
		children.clear();
	}
}
class TreeNode
{
	int val;
	TreeNode left_ptr;
	TreeNode right_ptr;
	
	TreeNode(int _val)
	{
		val = _val;
		left_ptr = null;
		right_ptr = null;
	}
}
