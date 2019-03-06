package ListAndStack;

import java.util.Stack;

/**
 * to get size of list. in the get size method you don't have to declare a current pointer. ref is local copy
 * it wont change the caller value;
 * To count size do size++ first like
 * while
 * 	size++
 *  p=p.next;
 *  
 * To move K number of places do:
 *  while(k!=0)
 *   n p=p.next
 *    then k--; 
 * 
 * After moving the argument pointer node, return it.
 *
 */

public class ClassTest 
{
	//Find intersection of 2 lists
	LinkedList l1 = new LinkedList();
	LinkedList l2 = new LinkedList();
	
	/****************************************INTERSECTION OF 2 LISTS*******************************************/
	public void findIntersection()
	{
		l1.insert(1);
		l1.insert(2);
		l1.insert(3);
		l1.insert(4);
		 l1.insert(5);
		 LinkedListNode n =l1.insert(13);
		l1.insert(14);
		l2.insert(6);
		l2.insert(7);
		l2.insert(8);
		LinkedListNode n2=l2.insert(9);
		n2.next=n;
		findIntersection(l1.head,l2.head);
	}
	
	//  1->2->3->4->5
	//					13->14->null		 =>13		
	// 	   6->7->8->9
	
	private LinkedListNode findIntersection(LinkedListNode l1, LinkedListNode l2)
	{
		int lenA = getLength(l1);
		int lenB = getLength(l2);
		if(lenA > lenB)
			l1=advanceK(l1, lenA-lenB);
		else
			l2=advanceK(l2,lenB-lenA);
		
		while(l1!=l2) //if there is no intersection , this will terminate null==null and return null
		{
			l1=l1.next;
			l2=l2.next;
		}
		return l1;
	}
	private LinkedListNode advanceK(LinkedListNode l, int k)
	{
		while(k!=0)
		{
			l=l.next;
			k--;
		}
		return l;
	}
	private int getLength(LinkedListNode n)
	{
		int size=0;
		while(n!=null)
		{
			size++;
			n=n.next;
		}
		return size;
	}
	
	
	/****************************************FIND MEDIUM OF CIRCULAR SORTED LIST*******************************************/
	//Given node is any arbitrary node.
	//If length is odd , 1 2 3 4 5 => 3
	//If even            1 2 3 4 5 6  = >  (3+4)/2
	private LinkedListNode f=null;
	public void find_median()
	{
		LinkedListNode f = l1.insert(1);
		l1.insert(2);
		LinkedListNode arbit=l1.insert(3);
		l1.insert(4);
		l1.insert(5);
		l1.insert(6);
		LinkedListNode p =l1.insert(7);
		p.next=f;
		
		System.out.println(find_median(arbit));
	}
	private int find_median(LinkedListNode ptr)
	{
		int len = findLen(ptr);
		int mid = len/2;
		LinkedListNode  n = advanceK(f,mid);
		if(mid%2==0)
			return (n.val+n.next.val)/2;
		return n.val;
	}
	private int findLen(LinkedListNode p)
	{
		if(p==null)
			return 0;
		int len=1;
		LinkedListNode curr=p;
		
		while(curr.next!=p)
		{
			len++;
			if(curr.val<p.val)
				f=curr;
			curr=curr.next;
		}
		if(f==null)
			f=p;
		return len;
	}
	
	
	
											//	-------END--------	
	
}
