package ListAndStack;

 // file:///Users/ali/Documents/workspace/KickStart/Notes/ListAndStack/swapKnodes.png

/*
 * SWAP
 * 1->2->3->4->7->0, k=2
 * 
 * prevX=>1  currX=>2         prevY=>4  currY=>7
 * 
 * Wrong way
 * prevX.next = prevY.next     so far OK!     
 * prevY.next = prevX.next     so far OK!   
 * Node tempY = currY=>7;		OK
 * Node tempX = currX=>2;		OK
 * 
 * prevY.next = currX.next   now 1->7 and you are assigning 7's next to where currX(2) is pointing to 3 . OK 7's next now points to 3
 * now you want to point 2 to where 7 was pointing, i,e to 0. 
 * But we had just changed currY(7) to point to 3. But you have a tempY stored. 
 * Now what is tempY's next pointing to . You may think its still pointing to 0 , but no , since tempY is a ref variable pointing to
 * currY.And when you changed currY to point to 3, tempY now also points to 3. So you dont have any reference variable that could point to 0.
 * 
 * Correct way
 * prevX.next = prevY.next     so far OK!
 * prevY.next = prevX.next     so far OK!
 * Store the reference to node 3 in a temporary variable.
 * Now point 2 to next of currY. 2.next=currY.next;
 * Now 7 can point to 3. 8.next = temp.
 * 
 * Swapping  1st and last   1->2->3->4->5
 * It pretty much the same as above.Meaning you swap the next of both previous pointers. Store current next in temp,
 * Then assign the next of current.
 * The only difference is that is either currX or currY is at position 1, it does not have a previous pointer.
 * So you will simply not do prevX.next = prevY  and prevY.next = prevX.next.  Skip these 2 steps.
 * Now if prevX is null then currY will become the head so do => head = currY
 * Now if prevY is null then currX will become the head so do => head = currX
 * 
 * if(prevX==null)
		pList=currY
 * LinkedListNode temp = currX.next;
        currX.next = currY.next;
        currY.next = temp
 */

public class SwapKthNode 
{
	public void swapNodes()
	{	
		ZipList z = new ZipList();
		
		z.insert(0);
		z.insert(2);
		z.insert(1);
		z.insert(3);
		z.printList(z.head);
		
		LinkedListNode n=swapNodes(z.head, 1);
		z.printList(n);
	}
	//3->1->2->0 k=1
	static LinkedListNode swapNodes(LinkedListNode pList, int iK)
	{	
		int size = getSize(pList);
		if((iK>size) ||(iK==1 && size==1))
			return pList;
		int i = iK-1;
		LinkedListNode currX = pList;
		LinkedListNode prevX = null;
		LinkedListNode currY = pList;
		LinkedListNode prevY = null;
		while(currX!=null && i!=0)
		{
			i--;
			prevX = currX;
			currX = currX.next;
		}
		i=size-iK;
		while(currY!=null && i!=0)
		{
			i--;
			prevY = currY;
			currY = currY.next;
		}
		if(prevX==null)
			pList=currY;
		if(prevY==null)
			pList=currX;
		
		if(prevX!=null)
			prevX.next = currY;
		if(prevY!=null)
			prevY.next = currX;
		
		LinkedListNode temp = currX.next;
        currX.next = currY.next;
        currY.next = temp;
		
		return pList;
    }
	static int getSize(LinkedListNode pList)
	{
		int n=0;
		LinkedListNode curr = pList;
		while(curr!=null)
		{
			n++;
			curr=curr.next;
		}
		return n;
	}
	

}

