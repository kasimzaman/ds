package Trees;
//https://crazycoderzz.wordpress.com/count-the-number-of-unival-subtrees-in-a-binary-tree/
//file:///Users/ali/Documents/workspace/KickStart/Notes/Tree/unival.png
public class CountUnival
{
	public void countuv()
	{
		Node n = builsUnivalTree();
		
		Count count = new Count();
		call=0;
		//System.out.println(findSingleValueTrees(n));
		call=0;
		findSingleValueTreesX2(n,count);
		System.out.println(count.count);
		System.out.println("calls:"+call);
	}
	
	
	//A Simple Solution is to traverse the tree. For every traversed node, check if all values under this node
	//are same or not. If same, then increment count. Time complexity of this solution is O(n2). Since we are checking for 
	//every node whether the nodes under it are unival its N^2
	//This is a bottom up approach
	static int count=0;
	public int findSingleValueTrees(Node n)
	{	call++;
		if(n==null)
			return 0;
		if(isUnival(n,n.val))
			count++;
		findSingleValueTrees(n.left);
		findSingleValueTrees(n.right);
		return count;
	}
	private boolean isUnival(Node n,int value)
	{
		if(n==null)
			return true;
		if(n.val==value)
		{
			return isUnival(n.left,n.val) && isUnival(n.right,n.val);
		}
		return false;
	}
	
	//without using Static counter;
	//More effecient method. O(n)
	//Go all the way down , then check if tree is unival and use this result to find solution for the above tree during stack 
	//unwinding.
	static int call=0;
	public boolean findSingleValueTreesX(Node n,Count c)
	{	call++;
		if(n==null)
			return true;
		
		
		boolean l= findSingleValueTreesX(n.left,c);
		boolean r= findSingleValueTreesX(n.right,c);
		if(l==false || r==false)
			return false;
		if(isUnival(n,n.val)){
			c.count++;
			return true;
		}
		else
			return false;
	}
	private int isUnivalX(Node n,int value)
	{
		if(n==null)
			return 1;
		if(n.val==value)
		{
			if(isUnivalX(n.left,n.val)==1){
				if(isUnivalX(n.right,n.val)==1)
					return 1;
				else
					return 0;
			}
			else
				return 0;
		}
		return 0;
	}
	
	private Node builsUnivalTree()
	{
		Node n1 = new Node(5);
		Node n2 = new Node(5);
		Node n3 = new Node(5);
		
		n1.left=n2;
		n1.right=n3;
		
		Node n4 = new Node(5);
		Node n5 = new Node(5);
		
		n2.left=n4;
		n2.right=n5;
		
		Node n6 = new Node(5);
		n3.right=n6;
		return n1;
	}
	class Count 
	{
	    int count = 0;
	}
	
	/************************************/
	//Time complexity: O(n).
	//if any false ->false
	//if left val not equal -> false
	//if right val not equal -> false
	//else count ++ and return true;
	public boolean findSingleValueTreesX2(Node node,Count c)
	{	call++;
		if(node==null)
			return true;
		
		boolean left= findSingleValueTreesX2(node.left,c);
		boolean right= findSingleValueTreesX2(node.right,c);
		
		if(left == false || right == false)
			return false;
		if(node.left!=null && node.left.val!=node.val)
			return false;
		if(node.right!=null && node.right.val!=node.val)
			return false;
		c.count++;
		return true;
	}
	
}
