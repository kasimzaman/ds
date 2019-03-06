package Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class CW
{
	private Node root=null;
	
	public void cwmain()
	{
		
	}
	public Node bstfmarr(int[]a,int low,int high)
	{
		if(low>high)
			return null;
		int mid=low+(high-low)/2;
		Node root= new Node(a[mid]);
		root.left=bstfmarr(a,low,mid-1);
		root.right=bstfmarr(a,mid+1,high);
		return root;
	}
	public Node insert(int val)
	{
		root = insertBST(root,val);
		return root;
	}
    public void findkthelementinorder(Node node, int k)
    {
        if(node == null)
            return;
        
        findkthelementinorder(node.left, k);
        if(k==0)
            System.out.println(node.val);
        k--;
        findkthelementinorder(node.right, k);
    }	
    
    public Node insertBST(Node root, int val)
    {	
    	if(root == null)
    		root = new Node(val);
    	else
    	{
	    	if(val<root.val){
	    		root.left = insertBST(root.left,val);
	    	}
	    	else{
	    		root.right = insertBST(root.right,val);
	    	}
    	}
    	return root;
    }
    
    public void inorderR(Node root)
    {
    	if(root == null)
    		return;
    	inorderR(root.left);
    	System.out.println(root.val);
    	inorderR(root.right);
    }
    public void inorderI(Node root)
    {
    	if(root==null)
    		return;
    	Stack<Node> stack = new Stack<Node>();
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
    			System.out.println(n.val);
    			current=n.right;
    		}
    	}
    }
    public void preorderI(Node root)
    {
    	if(root==null)
    		return;
    	Stack<Node> stack = new Stack<Node>();
    	stack.push(root);
    	while(!stack.isEmpty())
    	{
    		Node n = stack.pop();
    		System.out.println(n.val);
    		if(n.right!=null)
    			stack.push(n.right);
    		if(n.left!=null)
    			stack.push(n.left);
    	}
    }
    public void postorderI(Node root)
    {
    	List<Integer> list = new ArrayList<Integer>();
    	if(root==null)
    		return;
    	Stack<Node> stack = new Stack<Node>();
    	stack.push(root);
    	while(!stack.isEmpty())
    	{
    		Node n = stack.pop();
    		list.add(0,n.val);
    		if(n.left!=null)
    			stack.push(n.left);
    		if(n.right!=null)
    			stack.push(n.right);
    	}
    	for(int i : list)
    		System.out.println(i+",");
    }
    public int sumNodes(Node root)
    {
    	if(root==null)
    		return 0;
    	int nl = sumNodes(root.left);
    	int nr = sumNodes(root.right);
    	return root.val+nl+nr;
    }
    public Node swapNodes(Node root)
    {
    	if(root==null)
    		return null;
    	Node l= swapNodes(root.left);
    	Node r= swapNodes(root.right);
    	root.left= r;
    	root.right=l;
    	return root;
    }
    public Node min(Node root)
    {
    	if(root.left==null)
    		return root;
    	return min(root.left);
    }
    public Node search(Node root,int k,Queue<Node> q,Node parent)
    {	
    	if(root==null || root.val ==k)
    		return root;
    	q.add(root);
    	parent=root;
    	if(k<root.val)
    		return search(root.left,k,q,parent);
    	else
    		return search(root.right,k,q,parent);

    }
    //find successor without using queue. Here we use a 3rd parent pointer in the node class
    //  Node inOrderSuccessor(Node root, Node n) { 
//        if (n.right != null) {
//            return minValue(n.right);
//        }
//        
//        Node p = n.parent;
//        while (p != null && n == p.right) {
//            n = p;
//            p = p.parent;
//        }
//        return p;
 //   }
    //using a queue
    public Node findSuccessor(Node root,int k)
    {
    	if(root==null)
    		return null;
    	Queue<Node> q = new LinkedList<Node>();
    	Node parent=root;
    	Node n = search(root,k,q,parent);
    	if(n.right!=null)
    		return min(n.right);
    	else
    	{	
    		return q.peek();
    	}
    }
    //find successor without using DS  no need to maintain parent pointer.
    public Node findSuccessor2(Node root, int k)// successor is the min on the right sub tree
    {
    	if(root==null)
    		return null;
    	if(root.right!=null)
    		return min(root.right);
    	Node succ=null;
    	while(root!=null)
    	{
    		if(k<root.val)
    		{
    			succ=root;
    			root=root.left;
    		}
    		else if(k>root.val)
    			root=root.right;
    		else
    			break;
    	}
    	return succ;
    }
    public Node search(Node root, int k)
    {
    	if(root==null || k==root.val)
    		return root;
    	else
    	{
    		if(k<root.val)
    			return search(root.left,k);
    		else
    			return search(root.right,k);
    				
    	}
    }
    public Node getmin(Node n)
    {	
    	while(n.left!=null)
    		n=n.left;
    	return n;
    }
    public Node getMax(Node n)
    {
    	while(n.right!=null)
    		n=n.right;
    	return n;
    }
    //find predecessor. its the max on the left sub tree. 
    public Node findPred(Node root, int k)
    {
    	Node n = search(root,k);
    	if(n.left!=null)
    		return getMax(n);//its the max on the left sub tree.
    	Node pred=null;
    	while(root!=null)//if no left sub tree, then on the right sub tree , its the 1st num smaller than itself.
    	{
    		if(k<root.val)
    		{
    			root=root.left;
    		}
    		else
    		{
    			pred=root;
    			root=root.right;
    		}
    	}
    	return pred;
    }
    
    
    public boolean printParents(Node root, int k)
    {
    	if(root==null)
    		return false;
    	if(root.val==k)
    		return true;
    	else
    	{
	    	if(k<root.val){
	    		if(printParents(root.left, k)){
	    			System.out.println(root.val);
	    			return true;
	    		}
	    	}
	    	else{
	    		if(printParents(root.right, k)){
	    			System.out.println(root.val);
	    			return true;
	    		}
	    	}
    	}
    	return false;
    }
    public boolean printParentQ(Node root, int k,Stack<Integer> q)
    {
    	if(root==null)
    		return false;
    	if(root.val==k)
    		return true;
    	else
    	{
    		q.add(root.val);
    		if(k<root.val){
    			printParentQ(root.left,k,q);
    			while(!q.isEmpty())
    				System.out.println(q.pop());
    			return true;
    		}
    		else
    		{
    			printParentQ(root.right,k,q);
    			while(!q.isEmpty())
    				System.out.println(q.pop());
    			return true;

    		}
    	}
    	
    }
    
    public Node deleteRec(Node root, int key)
    {
        /* Base Case: If the tree is empty */
        if (root == null)  return root;
 
        /* Otherwise, recur down the tree */
        if (key < root.val)
            root.left = deleteRec(root.left, key);
        else if (key > root.val)
            root.right = deleteRec(root.right, key);
 
        // if key is same as root's key, then This is the node
        // to be deleted
        else
        {
            // node with only one child or no child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
 
            // node with two children: Get the in-order successor (smallest
            // in the right subtree)
            int minValue = minValue(root.right); //here you get the val of the in-order successor and assign that val in node you want deleted.
            root.val = minValue;								//I am copying the value of successor in to-be delete node. (actual node will not be deleted)
 
            // Delete the inorder successor
            root.right = deleteRec(root.right, minValue); //here I return the next pointer of the successor. so that my predecessor can point to it,
            											//skipping the successor node.
        }
 
        return root;
    }
    
    int minValue(Node root)
    {
        int minv = root.val;
        while (root.left != null)
        {
            minv = root.left.val;
            root = root.left;
        }
        return minv;
    }
    
	

}

