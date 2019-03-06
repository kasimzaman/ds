package Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import Trees.Merge.Height;

public class PrintLevelOrder
{
	public void plo(Node root)
	{
		Node r = createbt();
		isbal(r);
	}
	public Node createbt()
	{
		Node root = new Node(1);
       // root.left = new Node(18);
		
		root.left = new Node(2);
       root.right = new Node(3);
       root.left.left = new Node(4);
       root.left.right = new Node(5);
    	root.left.right.left = new Node(50);
    	root.left.right.left.left = new Node(51);
    	root.left.right.left.left.right = new Node(51);
       root.right.right = new Node(6);
        root.left.left.left = new Node(7);
        return root;
        /*
         	   10
            /     \
          2        3
        /   \    /    \
       7     8  1     15
                      
                    
                    
         19+20+25+42=106
         */
	}
	public Node createbt2()
	{
		Node root = new Node(2);
        root.right = new Node(3);
        root.right.right = new Node(-3);
        root.right.right.right = new Node(3);
        return root;
        /*
         	   2
                 \
                  3
            		\
         			-3
                      \
                       3
                    
         */
	}
	
	
	//Time: O(N) Space=> Max number of nodes at the given depth. This can be n/2.
	private void printLevelOrder(Node root)
	{
		if(root==null)
			return;
		Queue<Node> que = new LinkedList<>();
		que.add(root);
		
		while(!que.isEmpty())
		{
			Node n = que.poll();
			System.out.print(n.val+" ");
			if(n.left!=null)
				que.add(n.left);
			if(n.right!=null)
				que.add(n.right);
		}
		System.out.println(" ");
	}
	
	//Time O(N^2) Space  none
	private void levelOrder(Node root)
	{
		if(root==null)
			return;
		int level=1;
		while(printlevel(root,level)){
			level++;
			System.out.println(" ");
		}
	}
	private boolean printlevel(Node node, int level)
	{	
		if(node==null)
			return false;
		if(level==1)
		{
			System.out.print(node.val+" ");
			return true;
		}
		
		boolean left = printlevel(node.left,level-1);
		boolean right =	printlevel(node.right,level-1);
		return left || right;
		
	}
	class Res
	{
		boolean res;
		Node node;
		Res(Node n){
			this.node=n;
		}
	}
	//T=O(N) S=O(N). Space can be maximum number of leaf nodes.
	private void pl(Node node)
	{	
		int nodesInNextLevel=0;
		int nodesInCurrentLevel=0;
		Queue<Node>que = new LinkedList<>();
		que.add(node);
		nodesInNextLevel=0;
		nodesInCurrentLevel=1;
		while(!que.isEmpty())
		{
			if(nodesInCurrentLevel==0){
				System.out.println();
				nodesInCurrentLevel=nodesInNextLevel;
				nodesInNextLevel=0;
			}
			Node n = que.poll();
			System.out.print(n.val+" ");
			nodesInCurrentLevel--;
			if(n.left!=null){
				que.add(n.left);
				nodesInNextLevel++;
			}
			if(n.right!=null){
				que.add(n.right);
				nodesInNextLevel++;
			}
		}
	}
	
	
	/********************************************/
	/* T=O(N) S=O(N)
	   Spiral basic idea. Use 2 stacks. in stack 1 push right then left. So that left is at top and printed before right node. 
	   in stack 2 do the opposite. Push in left node first then right so that right is at top and get printed first.
	 */
	private void spiral(Node root)
	{
		Stack<Node>s1=new Stack<>();
		Stack<Node>s2=new Stack<>();
		s1.push(root);
		while(!s1.isEmpty() || !s2.isEmpty())
		{
			while(!s1.isEmpty())
			{
				Node n = s1.pop();
				System.out.print(n.val+" ");
				if(n.right!=null)
					s2.push(n.right);
				
				if(n.left!=null)
					s2.push(n.left);
			}
			System.out.println();
			while(!s2.isEmpty())
			{
				Node n = s2.pop();
				System.out.print(n.val+" ");
				if(n.left!=null)
					s1.push(n.left);
				
				if(n.right!=null)
					s1.push(n.right);
			}
			System.out.println();
		}
	}
	/*************************************************************/
	//Time Complexity: O(n) 
   private void boundary(Node root)
   {
	   if(root==null)
		   return;
	   printLeftSide(root);
	   printLeafs(root);
	   printRightSide(root);
   }
   private void printLeftSide(Node root)
   {
	   if(root.left!=null){
		   System.out.print(root.val+" ");
		   printLeftSide(root.left);
	   }
   }
   private void printRightSide(Node root)
   {
	   if(root==null)
		   return;
	   if(root.right!=null){
		   printRightSide(root.right);
		   System.out.print(root.val+" ");
	   }
   }
   private void printLeafs(Node root)
   {
	   if(root==null)
		   return;
	   if(root.left==null && root.right==null)
		   System.out.print(root.val+" ");
	   printLeafs(root.left);
	   printLeafs(root.right);
	  
   }
   /*************************************************************/
   //Time Complexity : O(n)
   private void leftRightCorners(Node root)
   {
	   Queue<Node>que=new LinkedList<>();
	   que.add(root);
	   int nodesInCurrentLevel=1;
	   int nodesInNextLevel=0;
	   boolean isFirst=true;
	   while(!que.isEmpty())
	   {
		   if(nodesInCurrentLevel==0)
		   {
			   System.out.println();
			   isFirst=true;
			   nodesInCurrentLevel=nodesInNextLevel;
			   nodesInNextLevel=0;
		   }
		   Node node = que.poll();
		   nodesInCurrentLevel--;
		   if(isFirst)
			   System.out.print(node.val+" ");
		   else if(nodesInCurrentLevel==0)
			  System.out.print(node.val+" ");
		   if(node.left!=null)
		   {
			   que.add(node.left);
			   nodesInNextLevel++;
		   }
		   if(node.right!=null)
		   {
			   que.add(node.right);
			   nodesInNextLevel++;
		   }
		   isFirst=false;
	   }
   }
   private void leftRightCorners2(Node root)
   {
	   Queue<Node>que=new LinkedList<>();
	   que.add(root);
	   while(!que.isEmpty())
	   {
		   int n = que.size();
		   int a=n;
		   while(n!=0)
		   {
			   Node node = que.remove();
			   if(n==a)
				   System.out.print(node.val+" ");
			   
			   if(node.left!=null)
				   que.add(node.left);
			   if(node.right!=null)
				   que.add(node.right);
			   n--;
			   if(n==0 && a!=1)
				   System.out.print(node.val+" ");
		   }
		   System.out.println();
	   }
   }
   
   
   /*************************************************************/
   //T=O(N), S=O(N)
   //To save space convert the tree into DLL. S()
   private void sumExistBST(Node root, int sum)
   {
	   List<Integer>list=new ArrayList<>();
	   doInorder(root,list);
	   if(findPair(list,sum))
		   System.out.println("true");
	   else
		   System.out.println("false");
   }
   private void doInorder(Node root,List<Integer>list)
   {
	   if(root==null)
		   return;
	   doInorder(root.left,list);
	   list.add(root.val);
	   doInorder(root.right,list);
   }
   private boolean findPair(List<Integer>list,int sum)
   {
	   int start=0;
	   int end=list.size()-1;
	   while(start<=end)
	   {
		   int n = list.get(start)+list.get(end);
		   if(n==sum)
			   return true;
		   else if(n>sum)
		   	  	end--;
		   else
			   start++;
	   }
	   return false;
   }
   //best solution: Do an in-order as well as in-order in reverse. and keep comparing
   //We traverse BST in Normal Inorder and Reverse Inorder simultaneously. 
   //O(n) time, O(Logn) space
	private void findSum(Node root, int sum)
	{
		if(root==null)
			return;
		Stack<Node>stack1=new Stack<>();
		Stack<Node>stack2=new Stack<>();
		Node curr1=root;
		Node curr2=root.right;
		int done=1;
		int val1=0;
		int val2=0;
		while(true)
		{
			while((done==1) && (!stack1.isEmpty() || curr1!=null))
			{
				if(curr1!=null)
				{
					stack1.push(curr1);
					curr1=curr1.left;
				}
				else
				{
					Node node = stack1.pop();
					val1=node.val;
					stack1.push(node.right);
					done=2;
				}
			}
			while((done==2) && (!stack2.isEmpty() || curr2!=null))
			{
				if(curr2!=null)
				{
					stack2.push(curr2);
					curr2=curr2.right;
				}
				else
				{
					Node node = stack2.pop();
					val2=node.val;
					if(node.left!=null)
						stack2.push(node.left);
					done=0;
				}
			}
			int total = val1+val2;
			if(total==sum)
				System.out.println(val1+" "+val2);
			else if(total < sum)
				done=1;
			else if(total > sum)
				done=2;
			if(val1 >=val2)// no pair
				return;
		}
	}
	/*************************************************************/
	//Search key in BST
	private boolean searchKeyItr(Node root,int key)
	{
		Node curr=root;
		while(curr!=null)
		{
			if(key>curr.val)
				curr=curr.right;
			else if(key<curr.val)
				curr=curr.left;
			else
				return true;
		}
		return false;
	}
	
	/*************************************************************/
	//Find largest number less than or equal to k in BST
	private int findLargest(Node root, int k)
	{
		if(root==null)
			return 0;
		Node curr=root;
		Node prev=null;
		while(curr!=null)
		{
			if(k>=curr.val)
			{
				prev=curr;
				curr=curr.right;
			}
			else if(k<curr.val)
			{	
				curr=curr.left;
			}
			else
				return prev.val;
		}
		return prev.val;
	}
	private int findLargest2(Node root, int N)
	{
		if (root.left == null && root.right == null && root.val > N)
	        return -1;
	 
	    if ((root.val <= N && root.right == null) || (root.val <= N && root.right.val > N))
	        return root.val;
	    if (root.val >= N)
	        return findLargest2(root.left, N);
	    else
	        return findLargest2(root.right, N);
	}
	/*************************************************************/
	//Do 2 BSTs contain same values.
	//inorder both and compare.
	
	/*************************************************************/
	//Find distance between 2 nodes of BT
	//T=O(N)
	private void findDist(Node root, int a, int b)
	{
		int d1 = findLevel(root,a,0);
		int d2 = findLevel(root,b,0);
		Node lc = findLCA(root,a,b);
		int d3 = findLevel(root,lc.val,0);
		int distance = (d1 + d2) -2*d3;
		System.out.println(distance);
		
	}
	private int findLevel(Node root, int n,int count)
	{	
		if(root==null)
			return -1;
		if(root.val==n)
			return count;
		int res = findLevel(root.left, n,count+1);
		if(res!=-1)
			return res;
		res = findLevel(root.right, n,count+1);
		if(res!=-1)
			return res;
		return -1;
	}
	private Node findLCA(Node root, int a, int b)
	{
		if(root==null)
			return null;
		if(root.val==a || root.val==b)
			return root;
		Node left = findLCA(root.left, a, b);
		Node right = findLCA(root.right, a, b);
		if(left !=null && right!=null)
			return root;
		if(left!=null)
			return left;
		else
			return right;
	}
	
	/*************************************************************/
	//Print nodes that are K distance from the root.
	private int printKDistant(Node root,int k)
	{
		if(root==null)
			return -1;
		if(k==0){
			System.out.println(root.val);
			return 1;
		}
		int res = printKDistant(root.left, k-1);
		res = printKDistant(root.right, k-1);
		return res;
	}
	private int findNode(Node root,Node target,  int k)
	{
		int res=-1;
		if(root==null)
			return -1;
		if(root==target)
		{
			printKDistant(target,k);
			return 0;
		}
		res=findNode(root.left,target,k);
		if(res==-1)
			res=findNode(root.right,target,k);
		
		return res;
	}
	
	/*************************************************************/
	//Are two trees mirrors?
	//Time Complexity : O(n)
	//or you can do inorder of a and inorder reverse of b and keep comparing nodes. and at the end both current pointer should be =null,
	//meaning both trees are done. 
	private boolean areMirrors(Node a, Node b)
	{
		if(a==null && b== null)
			return true;
		if(a !=null &&  b!=null && a.val == b.val)
			return areMirrors(a.left, b.right) && areMirrors(a.right, b.left);
		return false;
	}
	//Is a Tree	symetric
	private boolean isTreeSym(Node root)
	{	
		return areMirrors(root,root);
	}
	/*************************************************************/
	private int maxDepth(Node root)
	{
		if(root==null)
			return 0;
		int left = 1+maxDepth(root.left);
		int right = 1+maxDepth(root.right);
		return Math.max(left, right);
	}
	/*************************************************************/
	//Convert a normal BST to balanced BST
	//Do inorder o(n), then create a bst from sorted array o(n)
	/*************************************************************/
	//sum of all numbers from root to leaf paths.
	/*
		10
	 /     \
	2        3
  /   \    /   \
 7     8 12     15
	           /
	         14
         
19+20+25+42=106
*/
	private int sumAllPahts(Node root,int sum)
	{	
		if(root==null)
			return 0;
		sum=sum+root.val;
		if(root.left==null && root.right==null)
			return sum;
		return sumAllPahts(root.left,sum) + sumAllPahts(root.right,sum)	;	
	}
	
	/*************************************************************/
	//maximum width of a binary tree
	static int maxwidth(Node root) 
    {
        if (root == null)
            return 0;
        int maxwidth = 0;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) 
        {
            int count = q.size();
            maxwidth = Math.max(maxwidth, count);
            while (count > 0) 
            {
            	count--;
                Node temp = q.remove();
                System.out.print(temp.val);
                if (temp.left != null) 
                {
                    q.add(temp.left);
                }
                if (temp.right != null) 
                {
                    q.add(temp.right);
                }
            }
            System.out.println();
        }
        return maxwidth;
    }
	/*************************************************************/
	//print right view 
	// runtime of O(h) and memory O(1)
	private void printRV2(Node root)
	{
		if (root == null)
            return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
         
        while (!queue.isEmpty()) 
        {   
            int n = queue.size();
            for (int i = 1; i <= n; i++) {
                Node temp = queue.poll();
                if (i == n)
                    System.out.print(temp.val + " ");
                if (temp.left != null)
                    queue.add(temp.left);
                if (temp.right != null)
                    queue.add(temp.right);
            }
        }
	}
	/*************************************************************/
	//get min cost of path
			/*
		    10
		 /     \
		2        3
	 /   \    /    \
	7     8  12     15
		           
		19+20+25+42=106
		*/
	private int getminCost(Node root)
	{
		if(root==null)
			return 0;
		int l = root.val + getminCost(root.left );
		int r = root.val + getminCost(root.right);
		return Math.min(l, r);
	}
	
	
	/*************************************************************/
	//are leafs balanced. Difference between number of leafs of sub trees should be less or equal to 1 and not more.
	private int leafsBalanced(Node root)
	{
		int left=0;
		int right=0;
		if(root==null)
			return 0;
		if(root.left==null && root.right==null)
			return 1;
		left += leafsBalanced(root.left);
		right += leafsBalanced(root.left);
		int diff = Math.abs(left)-Math.abs(right);
		if(diff<=1)
			return left+right;
		else
			return Integer.MIN_VALUE;
	}
	
	/*************************************************************/
	private int sumLeaf(Node root)
	{
		if(root==null)
			return 0;
		if(root.left==null && root.right==null)
			return root.val;
		return sumLeaf(root.left) + sumLeaf(root.right);
	}
	/*************************************************************/
	//T=O(2^k)
	//S=O(2^k)  DFS will be O(K) but time will be O(2^k)
	private void printAllNodesAtLevelK(Node root, int k)
	{
		if(root==null)
			return;
		Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int count=0;
        while(!queue.isEmpty())
        {
        	int size=queue.size();
        	for(int i=0;i<size;i++)
        	{
        		Node node = queue.poll();
        		if(count==k){
        			System.out.print(node.val+" ");
        			//return;
        		}
        		if(node.left!=null)
        			queue.add(node.left);
        		if(node.right!=null)
        			queue.add(node.right);
        	}
        	count++;
        }
	}
	/*************************************************************/
	//Better space complexity than BFS. Since only 1 path will be in the stack. 
	private void printAllNodesAtLevelDFSK(Node root, int k)
	{
		if(root==null)
			return;
		if(k==0){
			System.out.println(root.val);
			return;
		}
		printAllNodesAtLevelDFSK(root.left,k-1);
		printAllNodesAtLevelDFSK(root.right,k-1);
	}
	/***************************Print all paths from root, with a specified sum in BT*********************************/
	//https://www.geeksforgeeks.org/print-paths-root-specified-sum-binary-tree/
	private void printPathWithSum(Node root, int  k,String path, int sum)
	{
		if(root==null)
			return;
		sum+=root.val;
		path+=root.val+" ";
		if(k==sum)
		{
			System.out.println(path);
			return;
		}
		printPathWithSum(root.left,k,path,sum);
		printPathWithSum(root.right,k,path,sum);
		
	}
	
	/*************************************************************/
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
		if(l.isBal && r.isBal && Math.abs(l.height-r.height)<=1)
			current.isBal=true;
		else
			current.isBal=false;
		return current;
	}
	class Height
	{
		boolean isBal=true;
		int height=0;
	}
	//Nodes in binary tree + 2^k-1
	
}
