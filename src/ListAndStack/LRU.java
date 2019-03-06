package ListAndStack;

import java.util.HashMap;
import java.util.Map;
/*
 	1-You need a Doubly Linked List
 	2-Your list class should have the following members:insert,remove,moveToHead,evict.
 	3-A Map.Key is Integer, Value is List Node
 	4-LRU Class has Map and DList as members. LRU has members: insert,remove,getItem,evict,
 */
public class LRU
{
	Map<Integer,LRUNode> map = new HashMap<Integer,LRUNode>();
	LRUDList list = new LRUDList();
	
	public void insert(int key, int data)
	{
		LRUNode node = new LRUNode(data);
		map.put(key, node);
		list.insert(node);
	}
	
	public void remove(int key)
	{	
		if(map.containsKey(key))
		{
			LRUNode node = map.get(key);
			list.remove(node);
			map.remove(key);
		}
	}
	
	public int getItem(int key)
	{
		int result = -1;
		if(map.containsKey(key))
		{
			LRUNode node = map.get(key);
			list.moveToHead(node);
			result = node.val;
		}
		return result;
	}
	
	public void eveict(int items)
	{	
		int[] keys = list.evict(items);
		for(int i=0;i<keys.length;i++)
			map.remove(keys[i]);
	}
	
	public void printCache()
	{
		list.printList();
	}

}

class LRUDList
{
	LRUNode head=null;
	LRUNode prev=null;
	LRUNode tail=null;
	
	public void insert(LRUNode node)
	{
		if(head==null)
		{
			node.next = head;
			head = node;
			tail = node;
			
		}
		else
		{
			node.next = head;
			head.prev = node;
			head = node;
		}
	}
	public void remove(LRUNode node)
	{	
		node.prev.next = node.next;
		node.next.prev = node.prev;
	}
	public void moveToHead(LRUNode node)
	{
		node.prev.next = node.next;
		node.next.prev = node.prev;
		node.next = head;
		head = node;
	}
	public int[] evict(int count)
	{
		int[] keys = new int[count];
		for(int i = 0; i < count;i++)
		{
			tail=tail.prev;
			tail.next = null;
		}
		return keys;
	}
	public void printList()
	{
		LRUNode current = head;
		while(current!=null)
		{
			System.out.println(current.val);
			current = current.next;
		}
	}
	public void printListTail()
	{
		LRUNode current = tail;
		while(current!=head)
		{
			System.out.println(current.val);
			current = current.prev;
		}
	}
	//make previous pointer point to the next node and next pointer point to the previous node.	
	public void reverse(LRUNode root)
	{
		if(root==null || root.next==null)//just 0 or 1 node
			return;
		LRUNode current=root;
		LRUNode temp=null;
		while(current!=null)
		{
			temp = current.prev;
			current.prev= current.next;
			current.next=temp;
			//now prev is pointing to next
			current=current.prev;
		}
		// 2  4  5  7  at the end of while loop the temp is pointing to 5, but the new head is 7. so do temp=temp.prev 
		//so now temp points to 7
		head=temp.prev;
	}
}


class LRUNode
{
	int val;
	LRUNode next;
	LRUNode prev;
	public LRUNode(int value)
	{
		this.val = value;
	}
}
