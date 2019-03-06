package Trees;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;


//file:///Users/ali/Documents/workspace/KickStart/Notes/Tree/rebuild.png

/*
 		_______7______
       /              \
    __10__          ___2
   /      \        /
   4       3      _8
            \    /
             1  11


	pre-order = {7,10,4,3,1,2,8,11}
	in-order = {4,10,3,1,7,11,8,2}
	
	Tree’s root always coincides with the first element in pre-order traversal.
	Find 7 in in-order set.
	Therefore, all elements left of 7 must be in the left subtree and all elements to the right must be in the right subtree.
	How to search the root value’s index in the in-order sequence?
	If its guaranteed to be a full binary tree, then do binary search. logN. Other wise use hash table.
 */
public class RebuildTree
{
	public void rbt()
	{
		int[] iInOrderArray={40,20,50,10,60,30,70};
		int[] iPreOrderArray={10,20,40,50,30,60,70};
		 constrctTree(iInOrderArray,iPreOrderArray);
	}
	//Time Complexity : O(n2) but if use Hash then O(N)
	//IF constructed binary tree is balanced then its O(NLOGN): N nodes and logn to search in in-order list instead of a linear scan every time.
	 private void constrctTree(int[] iInOrderArray,int[] iPreOrderArray)
	 {
		 if(iInOrderArray.length==0 || iPreOrderArray.length==0)
			 return;
		 Node n=constrctTree(iInOrderArray,iPreOrderArray,0,iInOrderArray.length-1);
		 printLevelOrder(n);
		 
	 }
	 static int iorderIndex=0;
	 private Node constrctTree(int[] iInOrderArray, int[] iPreOrderArray,int low, int high)
	 {
		 if(low>high)
			 return null;
 		 int element = iPreOrderArray[iorderIndex++];
		 Node n = new Node(element);
		 int index = findIndex(iInOrderArray,element,low,high);
		 n.left= constrctTree(iInOrderArray,iPreOrderArray,low,index-1);
		 n.right= constrctTree(iInOrderArray,iPreOrderArray,index+1,high);
		 return n;
	 }
	 private int findIndex(int[] iInOrderArray, int element,int low,int high)
	 {
		 int e=0;
		 for(e=low;e<high;e++)
		 {
			 if(element==iInOrderArray[e])
				 return e;
		 }	
		 return e;
	 }
	
	 private void printLevelOrder(Node n)
	 {
		 if(n==null)
			 return;
		 Queue<Node> que = new LinkedList<>();
		 que.add(n);
		 int count=0;
		 while(true)
		 {
			 count=que.size();
			 if(count==0)
				 break;
			 while(count>0)
			 {
				 Node node = que.remove();
				 System.out.print(node.val+" ");
				 if(node.left!=null)
					 que.add(node.left);
				 if(node.right!=null)
					 que.add(node.right);
				 count--;
			 }
			 System.out.println();
		 }
		 
	 }
	 //postorder and inorder
	 static int indx=0;
	 static Map<Integer,Integer> map = new HashMap<>();
	 private Node makeTree(int[]io,int[]po,int low,int high)
	 {
		
		 int r = po[po.length-1];
		 Node root = new Node(r);
		 int mid = map.get(r);
		 root.left=makeTree(io, po, low, mid-1);
		 root.right=makeTree(io, po, mid+1, high);
		 return root;
		 
	 }
	 private Map<Integer,Integer> store(int[]io)
	 { 
		 for(int i=0;i<io.length;i++)
		 {
			 map.put(io[i], i);
		 }
		 return map;
	 }

}
