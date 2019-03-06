package DP;

// file:///Users/ali/Documents/workspace/KickStart/Notes/DP/mm.png
public class MaxSubMatrix
{
	public void msm()
	{
	/*	int mtx[][]={{0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0}, 
		           	 {0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
		           	 {1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
		           	 {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
		           	 {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
		           	 {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
		           	 {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1},
		           	 {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1},
		           	 {1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1},
		           	 {1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1},
		           	 {1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1},
		           	 {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1}};  
		 */
		 int mtx[][] = {{0, 1, 1, 1, 1, 1},
                	    {1, 1, 1, 1, 1, 1},
                	    {1, 1, 1, 1, 1, 1},
                	    {1, 0, 1, 1, 1, 1},
                	    {1, 0, 1, 1, 1, 1},
                	    {1, 0, 1, 1, 1, 1}};
		            	
	        
		maxSubmatrix(mtx);
	}
	//https://www.youtube.com/watch?v=aYnEO53H4lw
	// Time Complexity: O(m*n)
	// Auxiliary Space: O(m*n)
	private int maxSubmatrix(int[][] mtx)
	{
		 int  max_i=0;
		 int max_j=0;
		int max=Integer.MIN_VALUE;
		int[][]table= new int [mtx.length][mtx[0].length];
		for(int i=0;i<mtx.length;i++)
			table[0][i]=mtx[0][i];
		for(int j=0;j<mtx.length;j++)
			table[j][0]=mtx[j][0];
		
		for(int i=1;i<mtx.length;i++)
		{
			for(int j=1;j<mtx.length;j++)
			{
				if(mtx[i][j]!=0)
				{
					table[i][j]=Math.min(table[i-1][j-1], Math.min(table[i][j-1], table[i-1][j])) + 1;
					if(table[i][j]>max)
					{
						max=table[i][j];
						 max_i = i; 
		                 max_j = j;
					}
				}
				else
					table[i][j]=0;
			}
		}
       
		for(int i=max_i;i>(max_i-max);i--)
		{
			for(int j=max_j;j>(max_j-max);j--)
				System.out.print(mtx[i][j]);
			System.out.println();
		}
		
		return max;
	}
	
	private int maxSubmatrix2(int[][] mtx) 
	{
		int r=0;
		int c=0;
		int finalR=-1;
		int finalC=-1;
		int max=Integer.MIN_VALUE;
		for(r=0;r<mtx.length-1;r++)
		{
			for(c=0;c<mtx[0].length-1;c++)
			{
				if(mtx[r][c]==1 && mtx[r][c+1]==1 && mtx[r+1][c]==1)
				{	
					int rtemp=r+1;
					int ctemp=c+1;
					int count=1;
					while(mtx[r][ctemp]==1 && mtx[rtemp][c]==1)
					{
						if(isSquare(mtx,r,ctemp,rtemp,c)){
							count++;
						}else break;
						if(rtemp==mtx.length-1 || ctemp==mtx.length-1)
							break;
						ctemp++;rtemp++;
					}
					if(count>max){
						finalR=r;
						finalC=c;
						max=count;
					}
				}
			}
		}
		if(finalC !=-1 && finalR!=-1){
			for(int i=finalR;i<finalR+max;i++){
				for(int j=finalC;j<finalC+max;j++){
					System.out.print(mtx[i][j]+" ");
				}System.out.println();
			}
		}
		return max;
    }
	private boolean isSquare(int[][] mtx,int r1,int c1, int r2,int c2)
	{
		boolean res=true;
		//check down by incrementing row and keep col fixed
		while(r1<=r2){
			if(mtx[r1][c1]!=1){
				res=false;
				break;
			}
			r1++;
		}
		//now go left to right and keep row fixed
		while((c2+1)<c1){
			if(mtx[r2][c2+1]!=1){
				res=false;
				break;
			}
			c2++;
		}
		return res;
	}
}
/*
	int M[][] = {{0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0}, 
                	 {0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                	 {1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                	 {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                	 {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                	 {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                	 {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1},
                	 {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1},
                	 {1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1},
                	 {1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1},
                	 {1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1},
                	 {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1}};
                	 
                	 
                	 int M[][] = {{0, 1, 1, 0, 1, 1}, 
                	 {0, 1, 1, 1, 1, 1},
                	 {1, 1, 1, 1, 1, 1},
                	 {1, 1, 1, 0, 1, 1},
                	 {1, 0, 1, 1, 1, 1},
                	 {1, 0, 1, 1, 1, 1}};
*/
