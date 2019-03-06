package recursion;

public class DiameterTreeR
{
	class Node
	{
		Node left;
		Node right;
		int val;
		Node(int val)
		{
			this.val=val;
		}
	}
	class DM
	{
		int diameter=0;
		int armLenght=0;
	}
	
	public DM dmt(Node root)
	{
		DM dm=new DM();
		if(root==null)
			return dm;
		DM left = dmt(root.left);
		DM right = dmt(root.right);
		
		int rootDM = left.armLenght + right.armLenght +1;
		int maxDia = Math.max(rootDM, Math.max(left.diameter, right.diameter));
		dm.diameter=maxDia;
		dm.armLenght=Math.max(left.armLenght, right.armLenght)+1;
		return dm;
	}
}
