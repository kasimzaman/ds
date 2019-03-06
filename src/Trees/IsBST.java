package Trees;
//Time Complexity: O(n)
public class IsBST
{
	public boolean isbst(Node n)
	{
		return isBST(n,Integer.MIN_VALUE,Integer.MAX_VALUE);
	}
		
	private boolean isBST(Node node,int min, int max) 
	{
		if(node == null)
			return true;
		if(node.val < min || node.val > max)
			return false;
		
		return isBST(node.left,min,node.val-1) && isBST(node.right,node.val+1,max);
    }
}


