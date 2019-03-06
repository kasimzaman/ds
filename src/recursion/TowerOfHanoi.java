package recursion;

public class TowerOfHanoi 
{
	public void toh()
	{
		int discs=3;
		towerOfHanoi(discs,'A','B','C');
	}
	/*
	 		|   	 |   	 |
	       --- 		--- 	---
	        A   	 B   	 C
	 	Move disk 1 from rod A to rod B
		Move disk 2 from rod A to rod C
		Move disk 1 from rod B to rod C
		Move disk 3 from rod A to rod B
		Move disk 1 from rod C to rod A
		Move disk 2 from rod C to rod B
        Move disk 1 from rod A to rod B

	 */
	void towerOfHanoi(int n, char from_rod, char to_rod, char aux_rod)
    {
        if (n == 1)
        {
            System.out.println("Move disk 1 from rod " +  from_rod + " to rod " + to_rod);
            return;
        }
        towerOfHanoi(n-1, from_rod, aux_rod, to_rod);
        System.out.println("Move disk " + n + " from rod " +  from_rod + " to rod " + to_rod);
        towerOfHanoi(n-1, aux_rod, to_rod, from_rod);
    }
	
	//from 		to 		aux
	//from 		aux 	to
	//aux 		to 		from
	
	
	
	
}
