package Trees;
//file:///Users/ali/Documents/workspace/KickStart/Notes/Tree/lbst.png
//http://www.ideserve.co.in/learn/size-of-largest-bst-in-binary-tree
//https://www.youtube.com/watch?v=4fiDs7CCxkc
       /* Let us construct the following Tree
						   	   50
							 /    \
						   10      60
						  /  \    /   \
					     5   20  5    70
					    /   /  \
					   45  65  80
*/
//Right sub tree 60-5-70 is a BST but as a whole its not, as every node on the right of 50 should be greater the 50.
//So we do bottom up approach. Do a post order traversal. 
//At every root , check the structure that both left/right children have returned.
//If both have returned true or false.
//Only true is not enough. Why ?
//because we need to maintain the property of BST that the minimum on the right sub tree should be greater than root
// maximum on the left subtree should be less than root.
//The return structure has min set to max_int,  and max set to int_min.
// so 5 should be greater than min(int_max)max of left subtree)
//and 5 should be less than max(min of right (int_min))
//also you need to pass on the min and max to the parent. you do that by setting min and max of 5 in struc before returning it.
//if 5 has no children then set min and max as 5.
//and when you return from 60. min is set to 5 and max is set to 70.
//At 50, when you check for condition if 50 is > than min of right tree (5), its is false.
public class LargestBST
{
	static int count=0;
	public void lbst(Node n2)
	{
		Node n=createBT();
		System.out.println(findLBST(n).count);
	}
	//O(N)
	//postorder traversal of tree. First visit left and right then
	//use information of left and right to calculate largest BST.
	private Result findLBST(Node node)
	{	
		if(node==null)
			return new Result();
		
		Result left = findLBST(node.left);
		Result right = findLBST(node.right);
		Result result = new Result();
		if((left.isBst && right.isBst) && (right.min > node.val && left.max < node.val))
		{
			result.isBst=true;
			result.count = left.count + right.count +1;
			result.min = node.left==null?node.val:left.min;
			result.max = node.right==null?node.val:right.max;
		}
		else
		{
			int max=Math.max(left.count, right.count);
			result.isBst=false;
			result.count=max;
		}
		return result;
	}
	class Result
	{
		int min = Integer.MAX_VALUE;  // For minimum value in right subtree
	    int max = Integer.MIN_VALUE;  // For maximum value in left subtree
	    boolean isBst=true;
	    int count=0;
	}
		
	//N^2. Check every node if it is a BST sub tree. If yes then count its nodes.
	//If not then check left sub tree then right sub tree for same 
	private int findLargestBST(Node n)
	{
		if(isBST(n, Integer.MIN_VALUE, Integer.MAX_VALUE))
			return countNodes(n);
		else
			return Math.max(findLargestBST(n.left),findLargestBST(n.right));
    }
	
	private boolean isBST(Node node, int min, int max)
	{
		if(node==null)
			return true;
		if(node.val<min||node.val>max)
			return false;
		return isBST(node.left, min, node.val-1) && isBST(node.right, node.val+1, max);
	}
	private int countNodes(Node n)
	{
		if (n==null)
			return 0;
		return 1+countNodes(n.left) + countNodes(n.right);
	}
	
	private Node createBT()
	{
		Node n1= new Node(9);
		Node n2= new Node(11);
		Node n3= new Node(12);
		n1.left=n2;
		n1.right=n3;
		
		//7 4 9 14
	//	Node n4= new Node(7);
//		Node n5= new Node(4);
	//	n2.left=n4;
//		n2.right=n5;
		
		Node n6= new Node(10);
		Node n7= new Node(14);
		n3.left=n6;
		n3.right=n7;
		
		//13  16
//		Node n8= new Node(13);
//		Node n9= new Node(16);
//		n7.left=n8;
//		n7.right=n9;
//		
//		//17
//		Node n10= new Node(17);
//		n9.right=n10;
//		
		
		return n1;
	}
	
	
	/***********************************************/
	class BS
	{
		int min=Integer.MAX_VALUE;
		int max=Integer.MAX_VALUE;
		boolean isBst=true;
		int count=0;
	}
	private BS largest(Node node)
	{
		BS bs = new BS();
		if(node==null)
			return bs;
		BS left = largest(node.left);
		BS right = largest(node.right);
		if((left.isBst && right.isBst) && left.max<node.val && right.min > node.val)
		{
			bs.count = left.count + right.count + 1;
			if(left==null)
				bs.min=node.val;
			else
				bs.min=left.min;
			if(right==null)
				bs.max=node.val;
			else
				bs.max= right.max;
			bs.isBst=true;
		}
		else
		{
			bs.count= Math.max(left.count, right.count);
			bs.isBst=false;
		}
		return bs;
	}
}
