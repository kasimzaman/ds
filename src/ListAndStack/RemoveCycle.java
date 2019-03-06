package ListAndStack;

public class RemoveCycle
{
	private void rc()
	{
		
	}
	private void detectCycle(LinkedListNode head)
	{
		LinkedListNode slow=head;
		LinkedListNode fast=head;
		while(slow!=null && fast!=null && fast.next!=null)
		{
			slow=slow.next;
			fast=fast.next.next;
			if(slow==fast)
			{
				remove(slow,head);
			}
		}
	}
	private void remove(LinkedListNode slow, LinkedListNode head)
	{
		LinkedListNode p1 = slow;
		LinkedListNode p2 = slow;
		int n=0;
		while(p1.next!=p2)
		{
			n++;	
			p1=p1.next;
		}
		p1=head;
		p2=head;
		while(n!=0)
		{
			p2=p2.next;
			n--;
		}
		LinkedListNode last=null;
		while(p1!=p2)
		{
			p1=p1.next;
			last=p2;
			p2=p2.next;
		}
		last.next=null;
	}
}
