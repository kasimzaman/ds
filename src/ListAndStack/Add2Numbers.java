package ListAndStack;

/*
 * 
 * with 2 list L1 and l2, You can do the following
 * while(L1!=null || L2!=null)
 * {
 * 
 * 	if(L1!=null)
 *     L1=L1.next;
 *  if(L2!=null)
 *     L2s=L2.next;
 * }
 * this will continue the other pointer to keep going if the other ends.
 * This is better than doing a while loop for both after this loop to get the remaining values.
 * 
 * 
 * When adding nodes to list at the end by using prev pointer. do this
 * if(head==null)
 *     head=node
 *  else
 *     prev.next=node
 *  
 *  prev=node;
 *  
 *  You dont have to repeat prev=node in both if/else conditions.
 *  Also note that if head==null , you just do head=node and not prev=head.
 *  file:///Users/ali/Documents/workspace/KickStart/Notes/ListAndStack/add2numbers.png
 * */



public class Add2Numbers
{
	ZipList z = new ZipList();
	ZipList z2 = new ZipList();
	public void add2Num()
	{	
		
		z.insert(9);
		z.insert(9);
		z.insert(9);
		z2.insert(0);
		
		addNumbers(z.head,z2.head);
	}
	//  82=>2->8    23=>3->2   => 5->3
	private  LinkedListNode addNumbers(LinkedListNode l1, LinkedListNode l2) 
	{
		LinkedListNode result=null;
		int carry=0;
		LinkedListNode itr1 = l1;
		LinkedListNode itr2 = l2;
		LinkedListNode prev=null;
		while(itr1!=null || itr2!=null)
		{
			int sum = (itr1!=null?itr1.val:0) + (itr2!=null?itr2.val:0) + carry;
			carry = 0;
			LinkedListNode node = new LinkedListNode();
			
			carry = sum<=9?0:1;
			sum=sum%10;
			
			node.val=sum;
			if(result==null)
				result=node;
			else
				prev.next=node;
			prev=node;
			
			if(itr1!=null)
				itr1=itr1.next;
			if(itr2!=null)
				itr2=itr2.next;
		}
		if(carry>0){
			LinkedListNode node = new LinkedListNode();
			node.val = carry;
			prev.next = node;
		}
		
		return result;
    }
}
