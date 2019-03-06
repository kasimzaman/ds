package Trees;

import java.util.ArrayList;
import java.util.List;

/*
 	A binary tree and a number k are given. Print every path in the tree with sum of the nodes in the path as k.
    A path can start from any node and end at any node, i.e. they need not be root node and leaf node; and negative
    numbers can also be there in the tree.
 */
public class PrintKSumPathinBT 
{
	public void pp()
	{
		Node n1=new Node(1);
		Node n2=new Node(2);
		Node n3=new Node(3);
		Node n4=new Node(3);
		Node n5=new Node(1);
		Node n6=new Node(10);
		Node n7=new Node(2);
		Node n8=new Node(2);
		
		n1.left=n2;
		n1.right=n3;
		n2.left=n4;
		n2.right=n5;
		n4.left=n8;
		n3.left=n6;
		n3.right=n7;
		List<Integer>list=new ArrayList<>();
		printAllNodesWithSum(n1,list,5);
	}
	
	//O(N) Do a pre-order. Pre order will give you all the roots
	public void printAllNodesWithSum(Node node, List<Integer> list, int k)
	{
		if(node==null)
			return;
		list.add(node.val);
		printAllNodesWithSum(node.left,list,k);
		printAllNodesWithSum(node.right,list,k);
		
		int f=0;
		for(int i=list.size()-1; i>=0;i--)
		{
			f+=list.get(i);
			if(f==k)
			{
				for(int j=i;j<=list.size()-1;j++)
					System.out.print(list.get(j)+" ");
				System.out.println();
			}
		}
		list.remove(list.size()-1);
	}

}
