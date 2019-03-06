package ListAndStack;

public class Revision
{

	/*
	 	
	 	1:Remove duplicates from an unsorted linked list
	 		1:Merge sort then delete o(nlogn)
	 		2:iterate and put in hashmap. O(N) O(N)
	 	
		2:check if a singly linked list is palindrome
			1)Iterate list and push to stack
			2)Iterate again and keep comparing with stack  O(N) O(N)
			OR O(N)
			1) Get the middle of the linked list.
			2) Reverse the second half of the linked list.
			3) Check if the first half and second half are identical.
			4) Construct the original linked list by reversing the second half again and attaching it back to the first half
			
		3:Delete last occurrence of an item from linked list
			1)traverse the linked list from beginning to end. keep track of last occurrence key.
			2)After traversing the complete list, delete last occurrence 
		
		4:Rotate a Linked List
			10->20->30->40->50->60    rotate k=4
			1)change next of kth node to NULL (40->NULL) (store ref to k+1, this will become head)
			2)Next of last to head.  60->10
			3)Change head to k+1 node (so keep ref to k+1)
			
		5:Write a function to delete a Linked List
			1)  head = null; garbage collection will take care of the rest.	
	 	
	 	
	 */
	//Time Complexity: O(n)
	boolean isPalindromeUtil(LinkedListNode right,LinkedListNode head) 
    {  
        if (right == null)
            return true;
 
        boolean isp = isPalindromeUtil(right.next,head);
        if (isp == false)
            return false;
        
        boolean isp1 = (right.val == head.val);
       
        head = head.next;
 
        return isp1;
    }
	
	/*****************************************FLATEN LINK LIST (RIGHT/DOWN POINTERS)*****************************************/
	//We use merge() to merge lists one by one. We recursively merge() the current list with already flattened list.
	public LinkedListNode flatten(LinkedListNode root)
    {
        if (root == null || root.next == null)
            return root;
        root.next = flatten(root.next);
        root = merge(root, root.next);
        return root;
    }
	LinkedListNode merge(LinkedListNode a, LinkedListNode b)
    {
        if (a == null)
        	return b;
        if (b == null)
        	return a;
        LinkedListNode result;
 
        if (a.val < b.val)
        {
            result = a;
           // result.down =  merge(a.down, b);
        }
        else
        {
            result = b;
           // result.down = merge(a, b.down);
        }
        return result;
    }
	
}
