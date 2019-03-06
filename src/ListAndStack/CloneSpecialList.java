package ListAndStack;
//file:///Users/ali/Documents/workspace/KickStart/Notes/ListAndStack/clone.png
/*
 	This is not O(n) space, because we are suppose to return a clone of original linked list so we are taking only
 	that many space and this is not a space to park some additional utilities but only to construct the Clone of LL.
 	Solution: algorithm goes like this.
 	Since I need to clone a entire list, then clone every node.
 	While after cloning each node keep it next to its original, because after cloning I want to transfer original nodes pointers to its clone.
 	Loop over the list again and copy the original nodes attrib pointer to cloned node. In other words
 	my child's attri pointer is same as my attrib pointer's next. (Next: because next will point to cloned node)
 */
public class CloneSpecialList
{
	SDList sdl = new SDList();
	//Build special list
	public SDList buildSpecialList()
	{
		
		sdl.add(5);sdl.add(4);sdl.add(3);sdl.add(2);sdl.add(1);
		//Assign arbt pointers based on node data
		sdl.setArbitPointer(sdl.head,1,3);
		sdl.setArbitPointer(sdl.head,2,1);
		sdl.setArbitPointer(sdl.head,3,5);
		sdl.setArbitPointer(sdl.head,4,3);
		sdl.setArbitPointer(sdl.head,5,2);
		SpecialNode s = cloneSpecialInN(sdl.head);
		return sdl;
	}
	
	public SpecialNode cloneSpecialInN(SpecialNode head)
	{	
		SpecialNode resultList = insertNodes(head);
		SpecialNode withPointers = copyPointers(resultList);
		return split(withPointers);
	}
	public SpecialNode split(SpecialNode head)
	{
		SpecialNode current = head;
		SpecialNode cloned= null;
		SpecialNode p=null;
		while(current!=null && current.next!=null)
		{
			SpecialNode temp=null;
			if(current.next.next!=null)
				temp = current.next.next;
			SpecialNode copy = current.next;
			if(cloned==null)
				cloned = copy;
			else
				p.next = copy;
			p=copy;
			current=temp;
		}
		return cloned;
	}
	public SpecialNode copyPointers(SpecialNode head)
	{
		SpecialNode current = head;
		while(current!=null && current.next!=null)
		{
			current.next.arbit = current.arbit.next;
			current=current.next.next;
		}
		return head;
	}
	public SpecialNode insertNodes(SpecialNode head)
	{	
		SpecialNode current = head;
		while(current!=null)
		{
			SpecialNode temp = current.next;
			SpecialNode copy = new SpecialNode(current.data);
			current.next = copy;
			copy.next = temp;
			current=current.next.next;
		}
		return head;
	}
	
	public SpecialNode cloneSpecial(SpecialNode head)
	{
		SpecialNode clone= null;
		
		SpecialNode current = head;
		SpecialNode prev=null;
		while(current!=null)
		{
			SpecialNode copy = new SpecialNode(current.data);
			if(clone==null)
				clone = copy;
			else
				prev.next = copy;
			prev = copy;
			current=current.next;
		}
		//Not extract arbit pointers from original list and make new ones in cloned list
		current=head;
		while(current!=null)
		{
			sdl.setArbitPointer(clone,current.data,current.arbit.data);
			current=current.next;
		}
		return clone;
	}
	public int extractArbitPointer(SpecialNode node)
	{
		return node.arbit.data;
	}
	
	
	public void printList(SpecialNode head)
	{
		sdl.printList(head);
	}
}

class SDList
{
	SpecialNode head=null;
	
	public void add(int data)
	{
		SpecialNode node = new SpecialNode(data);
		if(head == null)
		{
			head = node;
		}
		else
		{
			node.next = head;
			head=node;
		}
	}
	// a->b  not b->a
	public void setArbitPointer(SpecialNode head,int a, int b )
	{
		SpecialNode curr = head;
		//Find first node
		while(curr!=null && curr.next!=null)
		{
			if(a == curr.data)
				break;
			curr=curr.next;
		}
		//Find first node
		SpecialNode curr2 = head;
		while(curr2!=null && curr2.next!=null)
		{
			if(b == curr2.data)
				break;
			curr2=curr2.next;
		}
		curr.arbit = curr2;
	}
	public void printList(SpecialNode head)
	{
		SpecialNode curr = head;
		while(curr!=null)
		{
			System.out.println(curr.data);
			curr=curr.next;
		}
	}
}

class SpecialNode
{
	int data;
	SpecialNode next;
	SpecialNode arbit;
	public SpecialNode(int data)
	{
		this.data = data;
	}
}
