package Sorting;

/*
   file:///Users/ali/Documents/workspace/KickStart/Notes/Sorting/4Billion/p.png
 	Byte : 2^3
 	1KB  : 2^10    10^3  1 Thousand bytes
 	1MB  : 2^20    10^6  1 Million bytes
    1GB  : 2^30    10^9	 1 Billion bytes
 	4GB  : 2^32          4 Billion bytes
 	
 	
 	Array Size: 4 billion integers.
 	Size of integer is 32 bit, which is 2^5
 	4 billion x 2^5
 	2^2 x 2^30 x 2^5
	
	Input size : 2^37
	Memory : 1GB  2^30
	Difference : 2^7, so input is 128 times larger than your ram.
 	Break the input into 128 pieces.													 
 	Input is larger than memory. So we process the input array half at a time by splitting the array.
 	
 	But all this still requires a lot of file IO.
 	Better technique is as follows.
 	
 	Integer is 32 bytes or 2^5
 	We have 1GB of memory. 
 	How many integers can fit in 1GB memory
 	2^30/2^5 => 2^25 approximately 32 million integers
 	
 	
 	Int has 32 bits and hence can store 2^32 different values. Half of those are negative.
	2,147,483,647
	And the lowest is −2,147,483,648.
 	32-bit integers. There are 2^32 = 4*109 distinct integers.
 */
public class BillionSearch
{
	public void bs()
	{

	}
}
