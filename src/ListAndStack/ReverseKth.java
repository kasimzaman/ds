package ListAndStack;
//file:///Users/ali/Documents/workspace/KickStart/Notes/ListAndStack/reverselinklist.png
public class ReverseKth
{
	public void reverseKth()
	{
		ZipList a = new ZipList();
		
		a.insert(4);
		a.insert(3);
		a.insert(2);
		a.insert(1);
		printList(a.head);
		LinkedListNode  h = reverseKth(a.head,2);
		printList(h);
	}
	// 1->2->3->4->5->6->7->null  => 3->2->1->6->5->4->7->null
	/*  For K=3
	 *   3->2->1 Kth node is 1
	 *   6->5->4 Kth node is 6
	 *   Its clear that every Kth node will become the start of each sub-list.
	 *   So the sub-lists will be connected through this node. 
	 *   Kth node is stored in the prev pointer.
	 *   When we finish reversing/recursion we return the prev pointer (which is the start node) to the calling function.
	 	 why do this :head.next = reverseKth(next, k)
	 	 Because in starting the head should point to the result of the first swap of k nodes like
	 	 head-> 3->2->1
	 	 
	 	 Complexity: O(n)
	 */
	private LinkedListNode reverseKth(LinkedListNode head,int k)
	{
		LinkedListNode current=head;
		LinkedListNode prev=null;
		LinkedListNode next=null;
		int count=0;
		while(current!=null && count!=k)
		{
			next = current.next;
			current.next=prev;
			prev=current;
			current=next;
			count++;
		}
		if(next!=null)
			head.next=reverseKth(next, k);
		return prev;
	}
	public void printList(LinkedListNode head)
	{
		LinkedListNode curr = head;
		while(curr!=null)
		{
			System.out.print(curr.val+",");
			curr=curr.next;
		}
		System.out.println();
	}
	
	/********************************************REVISION*******************************************/
	
	
	
}
