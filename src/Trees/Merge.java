package Trees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
//file:///Users/ali/Documents/workspace/KickStart/Notes/Tree/merge.png
public class Merge
{

	public void merge(Node n1,Node n2)
	{
		mergetrees(n1,n2);
		int size= countNodes(n1);
		int size2=countNodes(n2);
		int[]arr=new int[size+size2];
		inorder(n1,arr,0);
		inorder(n2,arr,size);
		Node r = createBSTFromArray(arr,0,arr.length-1);
//		Node r = mergeTrees(n1,n2);
//		printTree(r);
		if(isBalanced(r))
			System.out.println("Tree is Balanced");
		else
			System.out.println("Tree is not Balanced");
			
	}
	//O(N)
	private void printTree(Node root)
	{
		if(root==null)
			return;
		Queue<Node> que= new LinkedList<Node>();
		que.add(root);
		
		while(!que.isEmpty())
		{
			int nodeCount=que.size();
			while(nodeCount>0)
			{
				Node n = que.poll();
				System.out.print(n.val+" ");
				if(n.left!=null)
					que.add(n.left);
				if(n.right!=null)
					que.add(n.right);
				nodeCount--;
			}
			System.out.println();
		}
	}
	
	private Node mergeTrees(Node n1, Node n2)
	{
		if(n1==null && n2==null)
			return n1;
		else if(n1==null)
			return n2;
		else if(n2==null){
			return n1;
		}
		int h1 = getTreeHeight(n1);
		int h2 = getTreeHeight(n2);
		Node small=null;
		Node large=null;
		if(h1<h2){
			small=n1;
			large=n2;
		}
		else if(h2<h1){
			small=n2;
			large=n1;
		}
		else{
			large=n2;
			small=n1;
		}
		
		Stack<Node> stack = new Stack<Node>();
		Node current=small;
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
				insertBST(large,n.val);
				current=n.right;
			}
		}
		
		return large;
    }
	private int getTreeHeight(Node node)
	{
		if(node==null)
			return 0;
		return 1+Math.max(getTreeHeight(node.left), getTreeHeight(node.right));
		
	}
	private Node insertBST(Node node, int k)
	{
		if(node==null)
			return new Node(k);
		if(k<node.val)
			node.left=insertBST(node.left, k);
		else
			node.right=insertBST(node.right, k);
		return node;
	}
	
	
	
	/*******************************Using Arrays********************************/
	//Do in-order traversal of both trees and store the result in a array.
	//then create a bst from the sorted array
	private void inorder(Node root,int[]arr,int index)
	{
		if(root==null)
			return;
		Stack<Node> stack = new Stack<Node>();
		Node  current = root;
		
		while(!stack.isEmpty() || current !=null)
		{
			if(current!=null){
				stack.push(current);
				current=current.left;
			}
			else
			{
				Node n = stack.pop();
				arr[index++]=n.val;
				current=n.right;
			}
		}
	}
	private int countNodes(Node root)
	{
		if(root==null)
			return 0;
		return 1+countNodes(root.left)+countNodes(root.right);
	}
	private Node createBSTFromArray(int[]arr, int start,int end)
	{
		if(start>end)
			return null;
		int mid = start+(end-start)/2;
		Node n = new Node(arr[mid]);
		n.left = createBSTFromArray(arr,start,mid-1);
		n.right = createBSTFromArray(arr,mid+1,end);
		return n;
	}

	
	
	
	/*
	 *             25
                 /    \
               15      35
              /  \    /  \
            10    20 30   40
           /  \			    \
          7    11            41
         /                     \
        6                       42
       /                 
      5
     
    
    start from root. Check if length or left and right satisfy the condition.If yes
    Move to root.left, then check again recursively. The right.
    Empty tree is balanced automatically.
    When calculating height, return 0 for a null node.
    COMPLEXITY=> O(N^2)
	 */
	private boolean isBalanced(Node root)
	{
		int r=0;
		int l=0;
		if(root==null)
			return true;
		l=maxLen(root.left);
		r=maxLen(root.right);
		
		if((Math.abs(l-r)<=1) && (isBalanced(root.left) && isBalanced(root.right)))
			return true;
		else
			return false;
	}
	private int maxLen(Node node)
	{
		if(node == null)
			return 0;
		return 1 + Math.max(maxLen(node.left),maxLen(node.right));
	}
	
	//isbalanced O(N)
	//https://www.geeksforgeeks.org/how-to-determine-if-a-binary-tree-is-balanced/
	
	class Height
	{
		boolean isBal=true;
		int height=0;
	}
	private void isbal(Node root)
	{
		if(isbalanced(root).isBal)
			System.out.println("balanced");
		else
			System.out.println("not balanced");
	}
	private Height isbalanced(Node root)
	{
		if(root==null)
			return new Height();
		Height current = new Height();
		Height l = isbalanced(root.left);
		Height r = isbalanced(root.right);
		
		current.height = l.height>r.height?l.height:r.height +1;
		if(Math.abs(l.height-r.height)>1)
			current.isBal=false;
		return current;
		
	}
	
	/*********************************************************************/
	class MB
	{
		Node head=null;
		Node prev=null;
	}
	private void mergetrees(Node t1, Node t2)
	{	
		Node h1 = spine(t1);
		Node h2= spine(t2);
		Node res=mergeLists(h1,h2);
		Node r=sortedListToBST(res);
		System.out.println();
		
	}
	private Node mergeLists(Node a,Node b)
	{
		Node prev=null;
		Node c1=a;
		Node c2=b;
		Node head=null;
		while(c1!=null && c2!=null)
		{
			if(c1.val>c2.val)
			{
				if(head==null){
					head=c2;
					prev=head;
				}
				else{
					prev.right=c2;
					prev=c2;
				}
				c2=c2.right;
			}
			else if(c1.val<c2.val)
			{
				if(head==null){
					head=c1;
					prev=head;
				}
				else{
					prev.right=c1;
					prev=c1;
				}
				c1=c1.right;
			}
			else
			{
				if(head==null){
					head=c1;
					prev=head;
				}
				else{
					prev.right=c1;
					prev=c1;
				}
				c1=c1.right;
				c2=c2.right;
			}
		}
		while(c1!=null)
		{
			prev.right=c1;
			prev=c1;
			c1=c1.right;
		}
		while(c2!=null)
		{
			prev.right=c2;
			prev=c2;
			c2=c2.right;
		}
		return head;
	}
	private Node spine(Node root)
	{
		Node prev=null;
		Node head=null;
		Stack<Node>stack=new Stack<>();
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
				Node node = stack.pop();
				if(head==null){
					head=node;
					prev=head;
				}
				else{
					prev.right=node;
					node.left=prev;
				}
				prev=node;
				current=node.right;
			}
		}
		return head;
	}
	Node sortedListToBST(Node head) 
    {
       Node curr=head;
       int n=0;
       while(curr!=null)
       {
			curr=curr.right;
			n++;
       }
       return linkedListToBSTUtil(head,0,n);
    }
	//O(N)  Construct from leaves to root
    private Node sortedListToBSTRecur(Node list,int start,int end) 
    {
    	if (start > end)
            return null;
    	int mid = start + (end - start) / 2;
        
        Node left = sortedListToBSTRecur(list,start,mid-1);
        Node parent = new Node(list.val);
        parent.left = left;
        list = list.right;
        parent.right = sortedListToBSTRecur(list,mid+1,end);
        return parent;
    }
    //nlogn  constructs the tree from root to leave
    private Node linkedListToBSTUtil(Node head,int start,int end)
    {
        if (start > end)
            return null;
         
        int mid = start+(end-start)/2;
        Node temp=head;
        
        int i = 0;
        while(i<mid && temp.right!=null)
        {
            temp = temp.right;
            i++;
        }
        Node root = new Node(temp.val);
        root.left = linkedListToBSTUtil(head, start, mid-1);
        root.right = linkedListToBSTUtil(head, mid+1, end);
        return root;
    }
   
	
}


