package ListAndStack;

// input : 1->2->3->4->5->6
//output : 1->6->2->5->3->4
//split the list.You need list size for it.Then reverse the 2nd half then merge
// file:///Users/ali/Documents/workspace/KickStart/Notes/ListAndStack/zip.png


//reverse dll
//https://www.youtube.com/watch?v=uz6dimvl40Q


public class ZipList
{
	LinkedListNode head = null;
	
	int size=0;
	public void insert(int data)
	{
		LinkedListNode node = new LinkedListNode();
		node.val=data;
		if(head == null)
		{
			node.next = head;
			head=node;
		}
		else
		{
			node.next = head;
			head = node;
		}
		size++;
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
	static int getListSize(LinkedListNode pList)
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
	
	public void zipList()
	{
		LinkedListNode newList=null;
		LinkedListNode current = head;
		int n = size/2;
		for(int i=1;i<=n;i++)
		{
			if(i>=n)
			{
				LinkedListNode temp = current.next;
				current.next=null;
				newList = temp;
				
			}
			else
				current = current.next;
		}
		
		LinkedListNode prev=null;
		LinkedListNode curr = newList;
		while(curr!=null)
		{
			LinkedListNode temp = curr.next;
			curr.next = prev;
			prev = curr;
			curr= temp;
		}
		mergerLists(prev);
	}
	
	// 1  3  4  5         2  6  7  8
	private void mergerLists(LinkedListNode head2)
	{
		LinkedListNode left = head;
		for(int i=0;i<size/2;i++)
		{	
			LinkedListNode temp = left.next;
			LinkedListNode rTemp = head2;
			head2=head2.next;
			left.next=rTemp;
			rTemp.next = temp;
			if(left.next.next!=null)
				left=left.next.next;
			else
				left=left.next;
		}
		if(head2!=null){
			left.next=head2;
		}
	}
	//unzip list
	//1-2-3-4-5-6  => ziped => 1-6-2-5-3-4
	//split  alternative   1-2-3     4-5-6
	//merger 1-2-3-4-5-6
	public void unZip()
	{	
		LinkedListNode list2=null;
		LinkedListNode current = head;
		
		//spliting
		while(current.next!=null)
		{	
			LinkedListNode copy = current.next;
			LinkedListNode next = current.next.next;
			current.next = next;
			if(list2==null)
			{
				copy.next=null;
				list2=copy;
			}
			else
			{
				copy.next=list2;
				list2=copy;
			}
			if(current.next!=null)
				current=current.next;
		}
		//merging
		current = head;
		while(current.next!=null)
			current=current.next;
		current.next = list2;
	}
	
	
	
	/*********************************REVISION************************************/
}

