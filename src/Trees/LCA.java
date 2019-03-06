package Trees;

import java.util.ArrayList;
import java.util.List;
//file:///Users/ali/Documents/workspace/KickStart/Notes/Tree/lca.png
public class LCA 
{
	public void  lca(Node root)
	{
		findLCABST(root,10,40);
	}
	//O(N) for binary tree.
	private Node findLCA(Node root, int n1, int n2)
	{	
		if(root==null)
			return null;
		if(root.val==n1 || root.val==n2)
			return root;
		Node l=findLCA(root.left, n1, n2);
		Node r=findLCA(root.right, n1, n2);
		// If both of the above calls return Non-NULL, then one key
		// is present in once subtree and other is present in other,
		// So this node is the LCA
		if(l!=null && r!=null)
			return root;
		if(l!=null)
			return l;
		else
			return r;
    }
	/********************************************/
	private int findLCAPaths(Node root, int n1, int n2)
	{
		if(root==null)
			return -1;
		List<Integer>path1=new ArrayList<>();
		List<Integer>path2=new ArrayList<>();
		if(path(root,n1,path1) && path(root,n2,path2))
		{
			int i=0;
			for(i=0;i<path1.size();i++)
			{
				if(path1.get(i)!=path2.get(i))
					break;
			}
			return path2.get(i-1);
		}
		return -1;
	}
	private boolean path(Node root, int n, List<Integer>path)
	{
		if(root==null)
			return false;
		else if(root.val==n)
			return true;
		path.add(root.val);
		if(path(root.left,n,path))
			return true;
		if(path(root.right,n,path))
			return true;
		path.remove(path.size()-1);
		return false;
	}
	
	//O(logn) or O(h) since its a bst
	private void findLCABST(Node root, int n1, int n2)
	{
		if(root==null)
			return;
		if(n1<root.val && n2<root.val)
			findLCABST(root.left, n1, n2);
		else if(n1>root.val && n2>root.val)
			findLCABST(root.right, n1, n2);
		else
			System.out.println(root.val);
		
	}
	
	
	
}
