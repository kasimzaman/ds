package recursion;



public class DoublePower
{
	public void dp()
	{
		generatePrime(10);
	}
	//Complexity = O(log(ipower))
	private float pow(float dblbase, int ipower)
	{
		float result=0;
		if(ipower==0)
			return 1;
		if(ipower==1)
			return dblbase;
		result=pow(dblbase,ipower/2);
		
		if(ipower%2==0)
		{
			result=result*result;
		}
		else
		{
			result=result*result*dblbase;
		}
		
		return result;
    }
	
	/*********************************************/
	//negative
	float power(float base, int exp) 
	{
		if(exp==0)
			return 1;
		if(base==1)
			return base;
		
		float res = power(base,exp/2);
		if(exp%2==0)
			return res*res;
		else
		{
			if(exp>0)
				return res*res*base;
			else
				return (res*res)/base;
		}
	}
	
	//Time Complexity: O(Log x)
	public void floorSqrt(int x)
    {
        if (x == 0 || x == 1){
        	 System.out.println(x);
        	 return;
        }
        // Do Binary Search for floor(sqrt(x))
        int start = 1, end = x, ans=0;
        while (start <= end)
        {
            int mid = (start + end) / 2;
 
            // If x is a perfect square
            if (mid*mid == x){
            	 System.out.println(mid);
            	 return;
            }
            // Since we need floor, we update answer when mid*mid is
            // smaller than x, and move closer to sqrt(x)
            if (mid*mid < x)
            {
                start = mid + 1;
                ans = mid;
            }
            else
                end = mid - 1;
        }
        System.out.println(ans);
    }
	
	
	
	public static void factors() 
	{
		int num = 100;
		for(long i = 1; i <= Math.sqrt(num); i++)  //we go till sqroot. The remaining factors are corresponding factors taken care by num/i
		{
	        if(num % i == 0)
	        {
	            System.out.println(i); //like i=10 and 100/10 
	           if(i != num/i)
	            {
	                System.out.println(num/i);
	            }
	        }
	    }
	}
	
    /*************************************DETECT PRIME***********************************************/
	
	//generate primes
	public void generatePrime(int n)
	{
		for(int i=1;i<=n;i++)
		{
			if(isPrime(i))
				System.out.print(i+" ");
		}
	}
	
	//check for even first
	//then run till sqroot and skip the evens and start from 3
	public static boolean isPrime(int n) 
	{
        if((n > 2 && n % 2 == 0) || n == 1)
        {
            return false;
        }
        for (int i = 3; i <= (int)Math.sqrt(n); i += 2)
        {

            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    
    /****************************************GCD**************************************************/
    // log(a+b)
	int gcd(int a, int b) 
    {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
	
	//LCM(a,b)=> a*b/gcd(a,b)
/*
	public void reverse()
	{
		Node current = head;
		Node prev = null;
		Node next=null;
		while(current!=null)
		{
			next = current.next;
			current.next = prev;
			prev = current;
			current=next;
		}
		head=prev;
	}
	
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
	*/
}
