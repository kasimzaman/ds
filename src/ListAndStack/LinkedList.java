package ListAndStack;

public class LinkedList
{
	LinkedListNode head = null;
	LinkedListNode tail = null;
	int size=0;
	public LinkedListNode insert(int data)
	{
		LinkedListNode node = new LinkedListNode();
		
		node.val=data;
		if(head == null)
			head=tail=node;
		else{
			tail.next = node;
			tail=node;
		}
		size++;
		return node;
	}
	public void printList(LinkedListNode head)
	{
		LinkedListNode curr = head;
		while(curr!=null)
		{
			System.out.println(curr.val);
			curr=curr.next;
		}
	}
	public int getListSize(LinkedListNode pList)
    {
        int size=0;
        LinkedListNode curr = pList;
        while(curr!=null)
        {
            size++;
            curr=curr.next;
        }
        return size;
    }   
	

}
