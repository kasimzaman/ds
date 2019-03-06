package arrays;

public class Spiral2D {
	//O(mn). 
	public static void main(String[] args)
	{
		int R = 5;
        int C = 6;
        int a[][] = { {1,  2,  3,  4,  5,  6},
                      {7,  8,  9,  10, 11, 12},
                      {13, 14, 15, 16, 17, 18},
                      {19, 20, 21, 22, 23, 24},
                      {25, 25, 27, 28, 29, 30}
                    };
        
        spiral(R,C,a);
	}
	static void spiral(int r, int c, int a[][])
	{
		int rowStart=0;
		int rowEnd=r-1;
		int colStart=0;
		int colEnd=c-1;
		
		while(rowStart<=rowEnd && colStart<=colEnd)
		{
			int row=rowStart;
			int col=colStart;
			
			for(col=colStart;col<=colEnd;col++)
				System.out.print(a[row][col]+" ");
			col--;
			for(row=rowStart+1;row<=rowEnd;row++)
				System.out.print(a[row][col]+" ");
			row--;
			if(rowStart<rowEnd){ //to check if its not the same row. in case if there is only 1 row
				for(col=colEnd-1;col>=colStart;col--)
					System.out.print(a[row][col]+" ");
				col++;
			}
			if(colStart<colEnd){ //to check if its not the same col. in case if there is only 1 col
				for(row=rowEnd-1;row>rowStart;row--)
					System.out.print(a[row][col]+" ");
			}
			
			rowStart++;
			rowEnd--;
			colStart++;
			colEnd--;
		}
	}
	
	static void spiral2(int r, int c, int a[][])
	{
		int rowSt=0;
		int rowEnd=r-1;
		int colSt=0;
		int colEnd=c-1;
		
		while(rowSt<=rowEnd && colSt<colEnd)
		{
			int row=rowSt;
			int col=colSt;
			for(col=colSt;col<=colEnd;col++)
				System.out.print(a[row][col]+" ");
			col--;
			for(row=rowSt+1;row<rowEnd;row++)
				System.out.print(a[row][col]+" ");
			row--;
			if(rowSt<rowEnd){
			for(col=colEnd-1;col>=colSt;col--)
				System.out.print(a[row][col]+" ");
			col++;
			}
			if(colSt<colEnd){
			for(row=rowEnd-1;row>rowSt;row--)
				System.out.print(a[row][col]+" ");
			}
			rowSt++;
			rowEnd--;
			colSt++;
			colEnd--;
		}
		
		
	}
}
