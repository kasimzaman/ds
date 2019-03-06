package ListAndStack;

public class SwapKthNodeR
{
	public void swapNodes()
	{
		ZipList z = new ZipList();
		z.insert(0);
		z.insert(7);
		z.insert(4);
		z.insert(3);
		z.insert(2);
		z.insert(1);
		z.printList(z.head);
		
		LinkedListNode n=swapNodes(z.head, 1);
		z.printList(n);
	}
	
	//1->2->3->4->7->0   k=2
	/*
	 	start i=1, then Move till i=k. Current will be 2
	 	then keep moving till i==(size-k+1), Current will be 7
	 	assign 1.next to 7
	 	assign 4.next to 2
	 	store 2.next in temp
	 	assign 2.next = 7.next
	 	7.next=temp.
	 	if k=1; then start's prev is null. so head=0
	 */
	private LinkedListNode swapNodes(LinkedListNode head, int k)
	{
		LinkedListNode left=head;
		LinkedListNode pLeft=null;
		LinkedListNode right=head;
		LinkedListNode pRight=null;
		int i=1;
		while(i!=k)
		{
			pLeft=left;
			left=left.next;
			i++;
			
			pRight=right;
			right=right.next;
		}
		int size=getSize(head);
		while(i!=(size-k+1))
		{
			pRight=right;
			right=right.next;
			i++;
		}
		if(pLeft!=null)
			pLeft.next=right;
		if(pRight!=null)
			pRight.next=left;
		if(pLeft==null)
			head=right;
		//store 2's next
		LinkedListNode temp=left.next;
		left.next=right.next;
		right.next=temp;
		return head;
	}
	private int getSize(LinkedListNode head)
	{
		int n=0;
		LinkedListNode itr=head;
		while(itr!=null)
		{
			n++;
			itr=itr.next;
		}
		return n;
	}
}
