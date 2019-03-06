package recursion;

//  file:///Users/ali/Documents/workspace/KickStart/Notes/Recursion/CountTrees/catalan.png
//  https://www.youtube.com/watch?v=YDf982Lb84o
	
/*
   
    			2     With left side fixed, I can have  2
    		  /	  \									  /   \
     		 1 	   3                                 1     4
    		 		\                                     /
    		 		 4                                   3          
 
   2 and 1 are fixed. And on right side we have 2 combinations. So we multiply by 2.  so its a multiplication of all combinations of left and right
   You have different combination possible on both sides. So the total number of combinations will be the multiplication of all left combination and
   all right side combinations.
    
 */
public class CountTrees
{
	public void ct()
	{
		int iNodeCount=5;
		System.out.println(countTrees(iNodeCount));
	}
	//without memorization recursion is not a good solution.
	// because we end up calculating already calculated trees again
	// if I have total 4 nodes. And I start with 1 on the left then how many can I have on the root's right?
	// total -1.
	// so count(k-1)*count(nodes-k)
	//means as k increases, more nodes goes on left, and subsequently less nodes on right.
	//initially k=1; so 0 children on left and nodes-0=> all children on right.
	private int countTreesRec(int numKeys)   //without memo O(3^N)  with is 2^n
	{
        if (numKeys <=1)
        {
            return 1;
        }
        else
        {
            int sum = 0;
            int left, right, root;
            for (root=1; root<=numKeys; root++) 
            {
                left = countTreesRec(root - 1);
                right = countTreesRec(numKeys - root);
                sum += left*right;
            }
            return(sum);
        }
    }
	/* n=1 trees=1
	   n=2 trees=2
	   n=3 trees=5
	   n=4 trees=14
	   How can we use the above to calculate n=5? Let keys be 10,11,12,13,14
	   Now take 10 as root.
	    I am left with=>t[4]
	   if 11 == root => t[1]*t[3]  -->means i child on left and 3 children on right
	   if 12 == root => t[2]*t[2]
	   if 13 == root => t[3]*t[1]
	   if 14 == root => t[4]
	   So the total trees with 5 nodes = T[4] + T[1]*t[3] + T[2]*t[2] + T[3]*t[1] + T[4]
	                                   =   14 +    1*5    +    2*2    +    5*1    +  14
	   								   =   14+5+4+5+14
	   								   =   42
	   use nested for loops. outer can be i inner can be j.
	   notice how j is increasing from 1,2,3,4
	   And i will decrease from 4,3,2,1
	   for(i=2; i<=n;i++)
	   {
	   		for(j=0; j<i;j++)
	   		   T[i]+=T[j]*T[i-j-1];
	   }
	   
	   i=5,j=0 =>T[5]=T[0]*T[5-0-1]=>[4]
	   i=5,j=1 =>    =T[1]*T[5-1-1]=>[3]
	   i=5,j=2 =>    =T[2]*T[5-2-1]=>[2]
	   i=5,j=3 =>    =T[3]*T[5-3-1]=>[1]
	   i=5,j=4 =>    =T[4]*T[5-4-1]=>
	   
	   Now lets do it for n=2
	   Tree possible =>  10,12
	   10 root => left with 1 on right so
	   10=root => T[1]
	   12=root => T[1]
	   Total T[2]=>T[1]+T[1]
	                 1 +  1
	                 =2
*/
	//Complexity (n^2)
	public int countTrees(int n)
	{
        int T[] = new int[n+1];
        T[0] = 1;//base case
        T[1] = 1;//base case
        for(int i=2; i <= n; i++)
        {
            for(int j=0; j <i; j++)
            {
                T[i] += T[j]*T[i-j-1];
            }
        }
        return T[n];
	}
	
	/*******************************REVISION**************************************/
	
	private int count(int nodes)   
	{
		if(nodes==1)
			return 1;
		int res=0;
		for(int k=1;k<=nodes;k++)
		{
			res+= count(k-1)*count(nodes-k);
		}
		return res;
	}

}
