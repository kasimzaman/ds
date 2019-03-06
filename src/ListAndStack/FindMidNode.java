package ListAndStack;

/*
 * Take away : using slow and fast pointer. When the fast pointer reaches the end the slow one is at the middle
 * In while loop always check fast.next!=null && fast.next.next!-null  because if fast.next is null it will throw exception if you don't have the first check
 * file:///Users/ali/Documents/workspace/KickStart/Notes/ListAndStack/findMidElement.png
 */


public class FindMidNode
{
	public void findMiddleNode() {
		
		
		LinkedList list = new LinkedList();
        for (int i=6; i>0; --i)
        {
            list.insert(i);
        }
       // printMiddle(list.head);
        findMiddleNode(list.head);
    }
	private int findMiddleNode(LinkedListNode pList)
	{
		LinkedListNode slow = pList;
		LinkedListNode fast = pList;
		int size=1;
		while(fast.next!=null && fast.next.next!=null)
		{
			size++;
			slow=slow.next;
			fast= fast.next.next;
		}
		if(fast.next!=null)
			size++;
		
		return size;
	}
	////6 5 4 3 2 1
	void printMiddle(LinkedListNode head)
    {
		LinkedListNode slow_ptr = head;
		LinkedListNode fast_ptr = head;
        if (head != null)
        {
            while (fast_ptr != null && fast_ptr.next != null)
            {
                fast_ptr = fast_ptr.next.next;
                slow_ptr = slow_ptr.next;
            }
            System.out.println("The middle element is " +slow_ptr.val);
        }
    }
	
	/**********************************************REVISION**************************************************/
	
}


