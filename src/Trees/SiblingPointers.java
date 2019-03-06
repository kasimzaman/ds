package Trees;

import java.util.LinkedList;
import java.util.Queue;

/*
 *             25
             /    \
           15 ---> 35
          /  \    /   \
        10--->20->30->40
       /  \			    
      7--> 11       filled or all nodes far left as possible. So in the end 11 points to 10.next.left. which is null 
      
      					Solution 1 : Time Complexity: O(n) Pre-order. Works for Complete BT only
    Solution 1 works for a complete binary tree. 
    A complete binary tree is a binary tree in which every level, except possibly the last,
    is completely filled, and all nodes are as far left as possible
    Algorithm:
    Do s PreOrder. Assign parent's next then children's
    If parent's left, then assign left's next to parent's right. Its a complete BT, So it left!null then it has a right as well.
    Otherwise when you do root.left.next=root.right, left.next will point to null. Last level.
    Assigning 20 to 30
    if (root.right!null) then if(root.next !null) then root.right.next=root.next.left or null
    This will only connect left children.
    Now recurse for both left and then right.
    

 * This is not a compete binary tree. In above, for 11's next we do 10.next.left:null.
 * That wont work here because 11 needs to point to 41.
 * 
 *             25
             /    \
           15 ---> 35
          /  \    /  \
        10--->20->30->40    
       /  \			    \
      7--> 11--------->  41 --------> while loop. keep checking if node.next!=null till it finds its children like 41.or terminates  and returns  null
   */
public class SiblingPointers
{
	SiblingTNode root=null;
	public void sp()
	{
		SiblingTNode node = createTree();
		//connectRecur2(node);
		itrSibling(node);
		printRightPointers(node);
	}
	//method 1. Works for only Complete Binary tree
	private void connectRecur(SiblingTNode p) 
    {
        if(p==null)
        	return;
        if(p.left!=null)
        	p.left.next=p.right;
        if(p.right!=null)
        	p.right.next = p.next!=null?p.next.left:null;
        connectRecur(p.left);
        connectRecur(p.right);
    }
	//METHOD 2 RECURSIVE
	//Method 2. I do a right sub tree traversal first. 
	//after this I get all next pointers of the right sub trees populated.
	//Then I do a left traversal and assign next pointers.
	// If I can find next through parent then I keep looping like parent=parent.next and try finding left/right children in each iteration.
	private void connectRecur2(SiblingTNode node)
	{
		if(node==null)
			return;
		
		if(node.left!=null && node.right!=null)
			node.left.next=node.right;
		else if(node.left!=null && node.right==null)
		{	
			node.left.next = getSibling(node);
		}
		if(node.right!=null)
		{	
			node.right.next = getSibling(node);
		}
		connectRecur2(node.right);
		connectRecur2(node.left);
	}
	private SiblingTNode getSibling(SiblingTNode parent)
	{
		SiblingTNode res=null;
		while(parent.next!=null)
		{
			if(parent.next.left!=null){
				res=parent.next.left;
				break;
			}
			else if(parent.next.right!=null){
				res= parent.next.right;
				break;
			}
			parent=parent.next;
		}
		return res;
	}
	//METHOD 2 ITERATIVE
	private void itrSibling(SiblingTNode node)
	{
		if(node==null)
			return;
		while(node!=null)
		{
			SiblingTNode itr = node;
			while(itr!=null)
			{
				if(itr.left!=null && itr.right!=null)
					itr.left.next=itr.right;
				else if(itr.left!=null && itr.right==null)
					itr.left.next = getSibling(itr);
				if(itr.right!=null)
					itr.right.next = getSibling(itr);
				itr=itr.next;
			}
			if(node.left!=null)
				node=node.left;
			else if(node.right!=null)
				node=node.right;
			else
				node=getSibling(node);
		}
		
	}
	
	private SiblingTNode populateSP(SiblingTNode node)
	{
		if(node == null)
			return node;
		return levelTraversal(node);
		
	}
	private void printRightPointers(SiblingTNode node)
	{
		if(node==null)
			return;
		int level=1;
		while(printLevelOrder(node,level))
		{
			level++;
			System.out.println("");
		}
		
	}
	
	private boolean printLevelOrder(SiblingTNode node, int level)
	{
		if(node==null)
			return false;
		if(level==1){
			//String s = node.right==null?"nil":node.right.val;
			System.out.print(node.val+"-->");
			
			return true;
		}
		boolean left = printLevelOrder(node.left,level-1);
		boolean right = printLevelOrder(node.right,level-1);
		return left||right;
	}
	//Time Complexity: O(n)
	//Space Complexity is the size of the max nodes in a level.
	private SiblingTNode levelTraversal(SiblingTNode node)
	{
		if(node == null)
			return node;
		Queue<SiblingTNode> que = new LinkedList<>();
		que.add(node);
		int count=0;
		SiblingTNode prev=null;
		while(true)
		{
			count=que.size();
			if(count==0)
				break;
			prev=null;
			while(count>0)
			{
				SiblingTNode n = que.remove();
				if(prev!=null)
					prev.next=n;
				n.next=null;
				prev=n;
				if(n.left!=null)
					que.add(n.left);
				if(n.right!=null)
					que.add(n.right);
				count--;
			}
		}
		return node;
	}
	private void printSiblingTree(SiblingTNode node)
	{
		if(node==null)
			return;
		Queue<SiblingTNode> que = new LinkedList<>();
		int count=0;
		que.add(node);
		while(true)
		{
			count=que.size();
			if(count==0)
				break;
			while(count>0)
			{
				SiblingTNode n = que.remove();
				System.out.print(n.val);
				if(n.next==null)
					System.out.print("->nil");
				else
					System.out.print("->");
				if(n.left!=null)
					que.add(n.left);
				if(n.right!=null)
					que.add(n.right);
				count--;
			}
			System.out.println("");
		}
		
	}
	private SiblingTNode createTree()
	{
		SiblingTNode n1 = new SiblingTNode(1);
		SiblingTNode n2 = new SiblingTNode(2);
		SiblingTNode n3 = new SiblingTNode(3);
		n1.left=n2;
		n1.right=n3;
		
		SiblingTNode n4 = new SiblingTNode(4);
		SiblingTNode n5 = new SiblingTNode(5);
		n2.left=n4;
		n2.right=n5;
		SiblingTNode n6 = new SiblingTNode(6);
		SiblingTNode n7 = new SiblingTNode(7);
		n3.left=n6;
		n3.right=n7;
		SiblingTNode n8 = new SiblingTNode(8);
		SiblingTNode n9 = new SiblingTNode(9);
		n4.left=n8;
		n4.right=n9;
		SiblingTNode n10 = new SiblingTNode(10);
		SiblingTNode n11 = new SiblingTNode(11);
		n7.left=n10;
		n7.right=n11;
		return n1;
		
	}
	private SiblingTNode create(int val,SiblingTNode node)
	{
		if(node==null)
			return new SiblingTNode(val);
		if(val<node.val)
			node.left = create(val,node.left);
		if(val>node.val)
			node.right = create(val,node.right);
		return node;
			
	}
	class SiblingTNode
	{
		int val=0;
		SiblingTNode left=null;
		SiblingTNode right=null;
		SiblingTNode next=null;
		public SiblingTNode(int val)
		{
			this.val=val;
		}
	}
	
	/****************************************************/
	
}
