package ListAndStack;

import java.util.Random;

/**
 * Problem: Implement SkipList
 * file:///Users/ali/Documents/workspace/KickStart/Notes/ListAndStack/SkipList/skiplist.png
 * Youtube : https://www.youtube.com/watch?v=Iait9BuZe78&list=PLTjcBkvRBqGFCPxmju5x3dpMJjUJ_CzVA&index=6
 * http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus/Map/skip-list-impl.html
 * 
 */

public class SkipList 
{	
	int MAX_LEVEL=5;
	class Node
	{
		Node[]Next;
		int value;
		Node(int v, int level)
		{
			value=v;
			Next=new Node[level];
		}
	}
	Node head = new Node(0,MAX_LEVEL);
	
	int levels=0;
	
	public void insert(int value)
	{	
		 int level = 0; 
		 while (Math.random() < .5 && level < MAX_LEVEL)
			 level++;
		 levels+=level;
		 Node newNode = new Node(value, level + 1);
		 Node cur = head;
		 for (int i = level - 1; i >= 0; i--)
		 {
			 while(cur.Next[i]!=null)
			 {
				 if(cur.Next[i].value > value)
					 break;
				 cur=cur.Next[i];
			 }
			 if(i<=level)
			 {
				 newNode.Next[i]=cur.Next[i];
				 cur.Next[i]=newNode;
			 }
		 }
	}
	public boolean Contains(int value)
	{
        Node cur = head;
        for (int i = levels - 1; i >= 0; i--)
        {
        	while(cur.Next[i]!=null)
            {
                if (cur.Next[i].value > value)
                	break;
                if (cur.Next[i].value == value)
                	return true;
                cur=cur.Next[i];
            }
        }
        return false;
	 }
	public boolean Remove(int value)
    {
        Node cur = head;
        boolean found = false;
        for (int i = levels - 1; i >= 0; i--)
        {
        	while(cur.Next[i]!=null)
            {
                if (cur.Next[i].value == value)
                {
                    found = true;
                    cur.Next[i] = cur.Next[i].Next[i];
                    break;
                }
                if (cur.Next[i].value > value)
                	break;
            }
        }
        return found;
    }

}


