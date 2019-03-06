package recursion;

/* Constructed binary tree is 
    https://www.geeksforgeeks.org/diameter-of-a-binary-tree/
  
																1
															  /   \
															2      3
														     \		
														      5		 
															 / \      
														   21   51     
														  /       \
														 22        52
														/           \
													   23            53
													  /               \
													 24                54

Time Complexity: O(n)
*/



public class DiameterTree
{
	public void dt()
	{
		Node root = createTree();
		//diameterTree2(root);
		System.out.println(diameterTree(root).diameter);
	}
	class DM
	{
		int diameter=0; 
		int longestLeg=0;
	}
	
	private DM diameterTree(Node root)
	{	int maxDiameter=0;
		DM dm = new DM();
		DM left=null;
		DM right=null;
		if(root==null)
			return new DM();
		
		left = diameterTree(root.left);
	
		right =diameterTree(root.right); 
	
		dm.longestLeg=Math.max(left.longestLeg, right.longestLeg);
		int diameterRoot = left.longestLeg + right.longestLeg + 1;  //when adding both legs we have to add 1 for the root
		
		//So the maximum diameter at any state is the maximum of the 3.
		//diameter of the left sub tree.
		//diameter of the right sub tree.
		//Diameter of the current root
		maxDiameter=Math.max(right.diameter, left.diameter);
		maxDiameter=Math.max(maxDiameter, diameterRoot);
		
		//I pass on the max diameter to parent. Also I pass the longest arm to parent
		dm.diameter=maxDiameter;
		dm.longestLeg=dm.longestLeg+1;
		return dm;
	}
	
	
	
	private Node createTree()
	{
		Node root= new Node(1);
		root.left= new Node(2);
		root.right= new Node(3);
		
//        root.left.right = new Node(5);
//        root.left.right.left = new Node(21);
//        root.left.right.left.left = new Node(22);
//        root.left.right.left.left.left = new Node(23);
//        root.left.right.left.left.left.left = new Node(24);
//        root.left.right.right = new Node(51);
//        root.left.right.right.right = new Node(52);
//        root.left.right.right.right.right = new Node(53);
//        root.left.right.right.right.right.right = new Node(54);
        return root;
	}
	public class Node
	{
	    int val;
	    Node left;
	    Node right;
	    public Node(int value) 
	    {
	        this.val = value;
	    }
	}
	
}
