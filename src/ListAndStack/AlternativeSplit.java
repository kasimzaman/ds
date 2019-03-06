package ListAndStack;

/*
 * Split a LL into 2 such that every other node goes into new list.
 * For odd number list, the first list should be longer
 * Example : // a b c d e f g  =>  a c e g  and  b d f
 * file:///Users/ali/Documents/workspace/KickStart/Notes/ListAndStack/AlternativeNode/AlternateNode.png
 * */
public class AlternativeSplit
{
	ZipList list = new ZipList();
	public void alternativeSplit()
	{
		list.insert(1);
		list.insert(2);
		list.insert(3);
		list.insert(4);
		list.insert(5);
		list.insert(6);
		list.insert(7);

		alternativeSplit(list.head);
		
	}
	// 7 6 5 4 3 2 1      a=>7 5 3 1    b=>6 4 2
	private static void alternativeSplit2(LinkedListNode pList)
	{
		LinkedListNode a=pList;
		LinkedListNode head2=a.next;
		LinkedListNode b=head2;
		
		while(a!=null && a.next!=null)
		{
			a.next=a.next.next;
			b.next=b.next.next;
			a=a.next;
			b=b.next;
		}
		b.next=null;
		
	}
	private static void alternativeSplit(LinkedListNode pList)
	{
		LinkedListNode a=null;
		LinkedListNode b=null;
		LinkedListNode ap=null;
		LinkedListNode bp=null;
		boolean go=true;
		while(pList!=null)
		{
			if(go)
			{
				if(a==null)
					a=pList;
				else
					ap.next=pList;
				ap=pList;
			}
			else
			{
				if(b==null)
					b=pList;
				else
					bp.next=pList;
				bp=pList;
			}
			pList=pList.next;
			go=!go;
		}
		bp.next=null;
		ap.next=null;
	}
	
}
