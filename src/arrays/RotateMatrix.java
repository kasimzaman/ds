package arrays;

public class RotateMatrix
{
	public void rm()
	{
		int[][]mat={ {1,  2, 3, 4},
				     {5,  6, 7, 8},
				     {9, 10,11,12},
				     {13,14,15,16}};
		
		//anti clock wise: 1st changes
		//x,y => y,x changes
		//y,x => y, x changes
		int N=4;
//		for (int x = 0; x < N / 2; x++)
//        {
//            for (int y = x; y < N-x-1; y++)
//            {
//                int temp = mat[x][y];
//                mat[x][y] = mat[y][N-1-x];
//                mat[y][N-1-x] = mat[N-1-x][N-1-y];
//                mat[N-1-x][N-1-y] = mat[N-1-y][x];
//                mat[N-1-y][x] = temp;
//            }
//        }
		
		//clock wise: 2nd changes
		//x,y => y changes,x
		//y,x => x changes,y
	
		for(int x=0;x<N/2;x++)
		{
			for(int y=x;y<N-x-1;y++)
			{
				int temp = mat[x][y];
				mat[x][y]=mat[N-1-y][x];
				mat[N-1-y][x]=mat[N-1-x][N-1-y];
				mat[N-1-x][N-1-y]=mat[y][N-1-x];
				mat[y][N-1-x]=temp;
			}
		}
		//clock
		
		for(int x=0;x<N/2;x++)
		{
			for(int y=x;y<N-1-x;y++)
			{
				int temp = mat[x][y];
				mat[x][y]=mat[N-1-y][x];
				mat[N-1-y][x]=mat[N-1-x][N-1-y];
				mat[N-1-x][N-1-y]=mat[y][N-1-x];
				mat[y][N-1-x]=temp;
			}
		}
		
		for(int x=0;x<N;x++)
		{
			for(int y=0;y<N;y++)
			{
				System.out.print(mat[x][y]+" ");
			}
			System.out.println();
		}
	
	}
}
