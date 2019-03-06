package Trees;

import java.util.ArrayList;
//file:///Users/ali/Documents/workspace/KickStart/Notes/Tree/paths.png
public class PrintAllPaths
{
	public void pap(Node root)
	{
		Node tree = new Node(1);
        tree.left = new Node(2);
        tree.right = new Node(3);
        tree.left.left = new Node(4);
        tree.left.right = new Node(5);
        
		ArrayList<Integer> paths = new ArrayList<Integer>();
	//	printPaths(tree,"");
		printAllPaths(tree,paths);
	}
	private void printAllPaths(Node root,ArrayList<Integer> paths)
	{	
		if(root == null)
			return;
		paths.add(root.val);
		if(root.left==null && root.right==null)
			printArray(paths);
		printAllPaths(root.left, paths);
		printAllPaths(root.right, paths);
		paths.remove(paths.size()-1);
					
    }
	private int sumAllPahts(Node root,int sum)
	{	
		if(root==null)
			return 0;
		sum=sum+root.val;
		if(root.left==null && root.right==null)
			return sum;
		return sumAllPahts(root.left,sum) + sumAllPahts(root.right,sum)	;	//no need to remove
	}
	private void printArray(ArrayList<Integer> paths)
	{
		for(int i : paths)
			System.out.print(i+" ");
		System.out.println("");
	}
	
	/***********************************/
	private void printPaths(Node root,String paths)
	{
		if(root==null){
			System.out.println(paths);
			return;
		}
		printPaths(root.left,paths+" "+root.val);
		if(root.right!=null)
			printPaths(root.right,paths+" "+root.val);
	}
	private void printPathSum(Node root,ArrayList<Integer>list)
	{
		if(root==null)
			return;
		list.add(root.val);
		if(root.left==null && root.right==null)
		{
			int sum=0;
			for(int i:list)
				sum+=i;
			System.out.println(sum);
		}
		printPathSum(root.left, list);
		printPathSum(root.right, list);
		list.remove(list.size()-1);
	}

}
