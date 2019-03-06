package ListAndStack;

/*
 * 
 * to get the mid of linked list
 * Get mid node.
 * mid.next is your right half
 * set mid.next to null
 * list is divided between start to null and mid.next  
 * 
 * To add nodes at the end. Keep a prev pointer. The is the reference to the last current pointer in the while loop.
 * next time set the new node as  prev.next = newnode
 * file:///Users/ali/Documents/workspace/KickStart/Notes/ListAndStack/mergesortlist.png
 * 
 * Quick sort requires a lot of random access and arrays are better at it.
 * */


public class MergeSortLL
{
	ZipList list = new ZipList();
	public void mergeSort()
	{	
		list.insert(6);
		list.insert(10);
		list.insert(1);
		list.insert(4);
		list.insert(7);
		list.insert(8);
		list.insert(9);
		list.insert(2);
		list.insert(3);

		LinkedListNode result = mergeSort(list.head);
		list.printList(result);
	}
	//3 2 9 8 7 4 1 10 6  mid => 7
	private LinkedListNode mergeSort(LinkedListNode pList)
	{	
		if(pList.next==null)
			return pList;
		LinkedListNode midNode = getMid(pList);
		LinkedListNode midNextNode=midNode.next;
		//Left Half
		midNode.next = null;
		LinkedListNode leftHalf = mergeSort(pList);
		//Right half
		LinkedListNode rightHalf = mergeSort(midNextNode);
		
		return mergeLists(leftHalf,rightHalf);
		
	}
	private LinkedListNode mergeLists(LinkedListNode a, LinkedListNode b) //  4 7    6  10
	{
		LinkedListNode merged=null;
		LinkedListNode prev=null;
		while(a!=null && b!=null)
		{
			if(a.val < b.val)
			{
				if(merged==null)
					merged=a;
				else
					prev.next=a;
				prev = a;
				a=a.next;
			}
			else if(a.val > b.val)
			{
				if(merged==null)
					merged=b;
				else
					prev.next=b;
				prev = b;
				b=b.next;
			}
			else
			{
				prev.next=b;
				prev = b;
				a=a.next;
				b=b.next;
			}
		}
		while(a!=null)
		{
			prev.next=a;
			prev = a;
			a=a.next;
		}
		while(b!=null)
		{
			prev.next=b;
			prev = b;
			b=b.next;
		}
		return merged;
	}
	private LinkedListNode getMid(LinkedListNode pList)
	{
		LinkedListNode slow=pList;
		LinkedListNode fast=pList;
		while(fast.next!=null && fast.next.next!=null)
		{
			fast=fast.next;
			slow=slow.next;
		}
		return slow;
	}
}
