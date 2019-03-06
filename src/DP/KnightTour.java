package DP;
/*	
 *			C	C	C
 * 		R	1	2	3
 * 		R	4	5	6
 * 		R	7	8	9
 * 		R		0
 * 
 * 		KT: exponential algorithm of size O(k^n). n=number of squares. K is the number of moves Knight can make
 * 			
 * 		O(8^n) branching factor is 8 unlike a binary tree which is 2
 * 
 */
//file:///Users/ali/Documents/workspace/KickStart/Notes/DP/KnightsTour/kt.png

public class KnightTour 
{
	static int calls=0;
	static int[][] pad = new int[6][6];
	public void tour()
	{
		int val=1;
		for(int i=0;i<6;i++){
			for(int j=0;j<6;j++){
				pad[i][j]=val++;
			}
		}
		//pad[6][1]=0;
		//pad[6][0]=Integer.MIN_VALUE;
		//pad[6][2]=Integer.MIN_VALUE;
		int phonenumberlength=10;
		int startDigit=1;
		long[][]cache = new long[10*phonenumberlength][phonenumberlength+1];
		for(int i=0;i<cache.length;i++){
			for(int j=0;j<cache[0].length;j++)
				cache[i][j]=Integer.MIN_VALUE;
		}
		System.out.println(numPhoneNumbers(startDigit,phonenumberlength,cache));
		System.out.println("calls"+calls);
	}
	
	int[]R={1,2,1,2,-1,-2,-1,-2};
	int[]C={2,1,-2,-1,2,1,-2,-1};
	
	private long numPhoneNumbers(int startdigit, int phonenumberlength,long[][]cache) 
	{calls++;
		long l=0;
		if(phonenumberlength<1)
			return 0;
		else if(phonenumberlength==1)
			return 1;
		if(cache[startdigit][phonenumberlength]!=Integer.MIN_VALUE)
			return cache[startdigit][phonenumberlength];
		for(int i=0;i<8;i++)
		{
			int r = R[i];
			int c = C[i];
			
			int next = moveTo(startdigit,r,c);
			if(next!=Integer.MIN_VALUE)
			{	
				l+=numPhoneNumbers(next,phonenumberlength-1,cache);
			}
		}
		cache[startdigit][phonenumberlength]=l;
		return l;
    }
	private int[] findPosition(int startdigit)
	{
		if(startdigit==0)
			return new int[]{3,1};
		for(int i=0;i<6;i++){
			for(int j=0;j<6;j++){
				if(pad[i][j]==startdigit)
					return new int[]{i,j}; 
			}
		}
		return null;
	}
	private int moveTo(int currentDigit, int r, int c)
	{
		int result=Integer.MIN_VALUE;
		int[] start= findPosition(currentDigit);
		int newR = start[0]+r;
		int newC = start[1]+c;
		if((newR>=0 && newR<pad.length) && (newC >=0 && newC<pad[0].length))
		{
			result =pad[newR][newC];
		}
		return result;
	}
	
	/*********************************************************************/
	//solveKTUtil(0, 0, 1, sol, xMove, yMove)
	static int N=8;
	static boolean solveKTUtil(int x, int y, int movei, int sol[][], int xMove[],int yMove[])
	{
		int k, next_x, next_y;
		if (movei == N * N)
		return true;		
		
		for (k = 0; k < 8; k++)
		{
			next_x = x + xMove[k];
			next_y = y + yMove[k];
			if (isSafe(next_x, next_y, sol))
			{
				sol[next_x][next_y] = movei;
				if (solveKTUtil(next_x, next_y, movei + 1,sol, xMove, yMove))
					return true;
				else
					sol[next_x][next_y] = -1;// backtracking
			}
		}
		
		return false;
	}
	
	static boolean isSafe(int x, int y, int sol[][])
	{
        return (x >= 0 && x < N && y >= 0 && y < N && sol[x][y] == -1);
    }
	
}
