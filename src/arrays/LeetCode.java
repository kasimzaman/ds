package arrays;

import java.awt.geom.Ellipse2D;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.plaf.FontUIResource;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.UUID;

import prob.datetime;

public class LeetCode {

	public static void main(String[] args)
	{	
		 int[] nums=  {1,2,3,4,5,6};
		sortNums(nums);
		for(int i : nums)
			System.out.print(i+" ");
//		int[] scores={1,3,5,6,8};
//		int[] lowerLimits={2};
//		int[] upperLimits={6};
//		jobOffers(scores,lowerLimits,upperLimits);
//		int[][] edges={ {0, 1},
//						{1, 2},
//						{2, 3},
//						{3, 4}};
//		int n=5;
//		countComponents(n, edges);
//		int[][] matrix={{1, 2, 3, 4},
//						{5, 6, 7, 8},
//						{9, 10,11,12},
//						{13,14,15,16}};
//		findDiagonalOrder(matrix);
//		int max = 10;
//		int min = 0;
//		Random rand = new Random();
//		for(int i=0;i<20;i++)
//		{
//			int randomInt = rand.nextInt(max+1) + min;
//			System.out.print(randomInt+" ");
//		}
		
	}
	//1 2 3 4 5 6   
	public static void sortNums(int[]nums)
	{
		int temp = 0;
		for(int i = 0; i < nums.length-1; i++)
		{
			for(int j = i+1; j < nums.length; j++)
			{
				if(nums[i] < nums[j])
				{
					temp = nums[i];
					nums[i] = nums[j];
					nums[j] = temp;
				}
					
			}
			
		}
	}
	
	static int[] jobOffers(int[] scores, int[] lowerLimits, int[] upperLimits) 
	{
		List<Integer>list = new ArrayList<>();
		
		for(int i=0;i<lowerLimits.length;i++)
		{
			int a = lowerLimits[i];
			int b = upperLimits[i];
			
			int score=-1;
			int count=0;
			for(int j=0;j<scores.length;j++)
			{
				score = scores[j];
				
				if(score>=a && score<=b)
				{
					count++;
				}	
			}
			list.add(count);
		}
		int[]result = new int[list.size()];
		for(int i=0;i<list.size();i++)
			result[i]=list.get(i);
		return result;
    }
	
	
	/*
	 	Given a non-empty integer array, find the minimum number of moves required to make all array elements equal,
	 	where a move is incrementing a selected element by 1 or decrementing a selected element by 1.
	 	You may assume the array's length is at most 10,000.
	 	Input:
		[1,2,3]
		
		Output:
		2
		
		Explanation:
		Only two moves are needed (remember each move increments or decrements one element):
		
		[1,2,3]  =>  [2,2,3]  =>  [2,2,2]
		
		SolutionA : Sort array. Get the middle element. Calculate the differences from the middle element to left side then right side.
		nlogn
		
		Solution B: No need to sort to get the middle element. Use quick sort partitioning to find the middle element.
		Remember after each partition the pivot comes to its correct index in the array.
		Here we are interested in the middle index of the array. So after every partition keep checking if the pivot index is ==
		array.lenght/2. If yes then get the element at this pivot , its your middle element.
		O(N)
		
	 */
	//NLOGN
	public static int minMoves(int[] nums)
	{
		Arrays.sort(nums);
		int mid = 0 + (nums.length - 0)/2;
		int a =  nums[mid];
		int diff=0;
		for(int i=0;i<mid;i++)
		{
			diff+=a - nums[i];
		}
		for(int i=mid+1;i<nums.length;i++)
		{
			diff+=nums[i]-a;
		}
		return diff;
    }
	
	public static int getMedian(int[]nums,int low,int high,int medianIndex)
	{
		if(low>high)
			return -1;
		int pivot = partition(nums,low,high);
		if(pivot == medianIndex)
			return pivot;
		if(pivot > medianIndex)
			return getMedian(nums, low, pivot-1, medianIndex);
		else
			return getMedian(nums, pivot+1, high, medianIndex);
	}
	public static int partition(int[]nums,int low,int right)
	{
		int pivot= nums[low];
		int left=low+1;
		while(left<right)
		{
			while(nums[left]<pivot)
				left++;
			while(nums[right]>pivot)
				right--;
			if(left<right)
			{
				swap(nums,left,right);
				left++;
				right--;
			}
		}
		swap(nums,low,right);
		return right;
	}
	public static void swap(int[]nums,int left,int right)
	{
		int temp = nums[left];
		nums[left]=nums[right];
		nums[right]=temp;
	}
	public static int minMoves3(int[] nums,int mid)
	{	
		int a =  nums[mid];
		int diff=0;
		for(int i=0;i<mid;i++)
		{
			diff+=a - nums[i];
		}
		for(int i=mid+1;i<nums.length;i++)
		{
			diff+=nums[i]-a;
		}
		return diff;
    }
	
	/*********************************************************************************************/
	/*
		Input: grid = 
		[[1, 1, 1],
		 [1, 1, 1],
		 [1, 1, 1]]
		Output: 9
		Explanation: There are four 2x2 rectangles, four 2x3 and 3x2 rectangles, and one 3x3 rectangle.
	 	Number Of Corner Rectangle
	 	Input: grid = 
		[[1, 0, 0, 1, 0],
		 [0, 0, 1, 0, 1],
		 [0, 0, 0, 1, 0],
		 [1, 0, 1, 0, 1]]
		Output: 1
		Explanation: There is only one corner rectangle, with corners grid[1][2], grid[1][4], grid[3][2], grid[3][4].
		
	 */
	public static int countCornerRectangles2(int[][] grid)
    {   
        int ans = 0;
        for( int colm = 0; colm<grid[0].length-1;colm++)
        {
	        for(int row = 0 ;row<grid.length-1;row++)
	        {
	        	if(grid[row][colm]!=1)
	        		continue;
	        	for(int rowx = row+1 ;rowx<grid.length;rowx++)
	        	{	
	        		if(grid[rowx][colm]!=1)
	        			continue;
	        		
	        		for( int col = colm+1; col<grid[0].length;col++)
	        		{
	        			if(grid[row][col]==1 && grid[rowx][col]==1)
	            			ans++;
	        		}
	        	}
	        }
        }
        return ans;
    }
	
	/*********************************************************************************************/
	public static void frequencySort(String s)
	{
		String ans="";
		int[]fmap= new int[256];
		for(int i=0;i<s.length();i++)
		{
			fmap[s.charAt(i)]++;
		}
		int chars=0;
		for(int i=0;i<fmap.length;i++)
		{
			if(fmap[i]==0)
				continue;
			chars++;
			int count= fmap[i];
			chars=Math.max(chars, count);
		}
		String[]buckets=new String[chars+1];
		for(int i=0;i<fmap.length;i++)
		{
			if(fmap[i]==0)
				continue;
			int n = fmap[i];
			if(n>0){
				for(int j=0;j<n;j++)
				{
					if(buckets[n]==null)
						buckets[n]= Character.toString((char)i);
					else
						buckets[n]= buckets[n]+Character.toString((char)i);
				}
			}
		}
		
		for(int i=chars;i>=0;i--){
			if(buckets[i]!=null)
				ans+=(buckets[i]);
		}
    }
	
	
	/*********************************************************************************************/
	
	public static int shortestWordDistance(String[] words, String word1, String word2)
	{
		int a=-1;
		boolean done=false;
        for(int i=0;i<words.length;i++)
        {
        	if(a!=-1)
        		a++;
        	if(words[i]==word1 && !done){
        		a++;
        		done=true;
        	}
        	else if(words[i]==word2)
        		return a-1;
        }
        return -1;
    }
	/*********************************************************************************************/
	//delete leet
	public static int minimumDeleteSum(String s1, String s2)
	{
		int min=Integer.MAX_VALUE;
		if(s1.length()==0)
			return ascii(s2);
		if(s2.length()==0)
			return ascii(s1);
		
		if(s1.charAt(0)==s2.charAt(0))
			return minimumDeleteSum(s1.substring(1), s2.substring(1));
		else
		{
			min = Math.min(minimumDeleteSum(s1, s2.substring(1)) +ascii(s2.substring(0,1)), minimumDeleteSum(s1.substring(1), s2)+ascii(s1.substring(0,1)));
		}
		return min;
    }
	private static int ascii(String s)
	{
        int res = 0;
        for(int i=0; i<s.length(); i++)
        {
            res += (int)s.charAt(i);
        }
        return res;
	}
	
	/*********************************************************************************************/
	/*	process has a unique PID (process id) and its PPID (parent process id).
	 	pid =  [1, 3, 10, 5]
		ppid = [3, 0, 5,  3]
		kill = 5
		Output: [5,10]
	 */
	//Kill Process
	//O(N)  Space map of size n used O(N)
	public static List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) 
	{
		List<Integer> list =new ArrayList<>();
		list.add(kill);
		Map<Integer,ArrayList<Integer>>map=new HashMap<>();
		for(int i=0;i<ppid.size();i++)
		{
			int key = ppid.get(i);
			int value = pid.get(i);
			if(map.containsKey(key))
			{
				ArrayList<Integer>l =map.get(key);
				l.add(value);
				map.put(key, l);
			}
			else
			{
				ArrayList<Integer>l=new ArrayList<>();
				l.add(value);
				map.put(key, l);
			}
		}
		getChildren(list,map,kill);
		
		return list;
    }
	public static void getChildren(List<Integer> list,Map<Integer,ArrayList<Integer>>map,int kill)
	{		
		ArrayList<Integer> l = map.get(kill);
		if(l==null)
			return;
		for(int i=0;i<l.size();i++)
		{
			list.add(l.get(i));
			getChildren(list,map,l.get(i));
		}
	}
	/*********************************************************************************************/
	/* https://leetcode.com/problems/implement-magic-dictionary/description/
		Implement Magic Dictionary
		Hamming distance is the minimum number of substitution in 2 strings of same length
		like: zaman
			  zqman
		Minimum edit distance use insertion,deletion and substitution. And the string may be of different lengths. Of course if the are same length then why
		would we need insertion or editions.
		like : A  B C D E F   You can delete B to make them equal    OR
		       A    C D E F   You can insert B to make strings equal
	*/
	public static Map<Integer,ArrayList<String>>map = new HashMap<>();
	public static void MagicDictionary() {
		String[]dict={"MagicDictionary", "buildDict", "search", "search", "search", "search"};
		buildDict(dict);
		search("hell");
    }
	public static void buildDict(String[] dict)
	{
		for(int i=0;i<dict.length;i++)
		{
			int len = dict[i].length();
			if(map.containsKey(len))
			{
				ArrayList<String> list = map.get(len);
				list.add(dict[i]);
				map.put(len, list);
			}
			else
			{
				ArrayList<String>al = new ArrayList<>();
				al.add(dict[i]);
				map.put(len, al);
			}
			
		}
    }
	public static boolean search(String word)
	{
		int len = word.length();
		ArrayList<String> words = map.get(len);
		if(words!=null && words.size()>0)
		{
			for(String s : words)
			{
				if(hamming(s,word))
					return true;
			}
		}
		return false;
    }
	public static boolean hamming(String s1,String s2)
	{
		int diff=0;
		for(int i=0;i<s1.length();i++)
		{
			if(diff>1)
				return false;
			if(s1.charAt(i)!=s2.charAt(i))
				diff++;
		}
		return true;
    }
	
	/*********************************************************************************************/
	//Top K Frequent Element
	//nlogk
	//Given [1,1,1,2,2,3] and k = 2, return [1,2].

	public static List<Integer> topKFrequent(int[] nums, int k) 
	{
		int max=0;
		Queue<Nk>pq = new PriorityQueue<Nk>();
		List<Integer>list = new ArrayList<>();
		if(nums.length==1)
		{
			list.add(nums[0]);
			return list;
		}
		Map<Integer,Integer>map=new HashMap<>();
		for(int i=0;i<nums.length;i++)
		{
			int n = nums[i];
			if(map.containsKey(n))
			{
				int a = map.get(n);
				map.put(n, a+1);
				if(max<a+1)
					max=a+1;
			}
			else
			{
				if(max<1)
					max=1;
				map.put(n, 1);
			}
		}
		for(Entry<Integer, Integer>entry : map.entrySet())
		{
			int v =entry.getValue();
			int key =entry.getKey();
			Nk pair=new Nk(key,v);
			if(pq.size()<k)
			{
				pq.offer(pair);
			}
			else
			{
				Nk element = pq.peek();
				if(element.freq<v)
				{
					pq.poll();
					pq.offer(pair);
				}
			}
		}
		Iterator<Nk>itr = pq.iterator();
		while(itr.hasNext())
		{
			list.add(0,itr.next().value);
		}
		return list;
    }
	static class Nk implements Comparable<Nk>
	{
		int value;
		int freq;
		public Nk(int val,int fre)
		{
			this.value=val;
			this.freq=fre;
		}
		@Override
		public int compareTo(Nk o)
		{
			return this.freq-o.freq;
		}
	}
	
	/*********************************************************************************************/
    //Number of Connected Components in an Undirected Graph
	public static int countComponents(int n, int[][] edges)
	{
		if (n <= 1)
            return n;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        Set<Integer> visited = new HashSet<>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (visited.add(i)) {
                dfsVisit(i, map, visited);
                count++;
            }
        }
        return count;
    }
    
    private static void dfsVisit(int i, Map<Integer, List<Integer>> map, Set<Integer> visited) {
        for (int j : map.get(i)) {
            if (visited.add(j))
                dfsVisit(j, map, visited);
        }
    }

	
	/*********************************************************************************************/
	//Next Greater Element II
    //Given a circular array 
	//1,2,1  => 2,-1,2
	public static void nextGreaterElements(int[] nums) 
	{
		for(int i=0;i<nums.length;i++)
		{
			int j=i;
			while((j+1)%nums.length !=j)
			{
				int n = (j+1)%nums.length;
				if(n==i){
					System.out.print(-1);
					break;
				}
				if(nums[i]<nums[n])
				{
					System.out.print(nums[n]);
					break;
				}
				j++;
			}
		}
	}
	
	/*********************************************************************************************/
	//Maximum XOR of Two Numbers in an Array
	//3, 10, 5, 25, 2, 8   =>  The maximum result is 5 ^ 25 = 28
	//O(N*logN)  space O(1)
	public static int findMaximumXOR(int[] arr)
	{
		int n=arr.length;
		Arrays.sort(arr);
        
        int minXor = Integer.MAX_VALUE;
        int val = 0;
       
        for(int i=0;i<n-1;i++)
        {
            val = arr[i]^arr[i+1];
            minXor = Math.min(minXor, val);
        } 
        return minXor;
    }
	
	/*********************************************************************************************/
	// Total Hamming Distanc
	//4, 14, 2  => 6
	//Now your job is to find the total Hamming distance between all pairs of the given numbers
	//https://leetcode.com/problems/total-hamming-distance/solution/
	//xor 2 numbers gives you their difference. Which is the number of 1's in the result.
	//count the number of 1's for each pair and return the total.
	 public static void totalHammingDistance(int[] nums)
	 {
		 int a=0;
		 int count=0;
		 int total=0;
		 for(int i=0;i<nums.length-1;i++)
		 {
			 for(int j=i+1;j<nums.length;j++)
			 {
				 a= nums[i]^nums[j];
				 count=0;
				 while(a!=0)
				 {
					 a=a&(a-1);
					 count++;
				 }
				 total+=count;
			 }
		 }
		 System.out.println(total);
	 }
	 
	 /*********************************************************************************************/
	 //O(N^2)  O(N^2)
	 //A=[ 1, 2] B = [-2,-1] C = [-1, 2] D = [ 0, 2]  which combination=0
	 //-1,0,0,1    -1,1,2,4
	 public static int fourSumCount(int[] A, int[] B, int[] C, int[] D)
	 {
		 Map<Integer,Integer> sums = new HashMap<>();
		 int count = 0;
		 for(int i=0; i<A.length;i++) 
		 {
			 for(int j=0;j<B.length;j++)
			 {
				 int sum = A[i]+B[j];
				 if(sums.containsKey(sum)) 
				 {
					 sums.put(sum, sums.get(sum)+1);
				 }
				 else
				 {
					 sums.put(sum, 1);
				 }
			 }
		 }
		 for(int k=0; k<C.length;k++) 
		 {
			 for(int z=0;z<D.length;z++)
			 {
				 int sum = -(C[k]+D[z]);
				 if(sums.containsKey(sum)) 
				 {
					 count+=sums.get(sum);
				 }
			 }
		 }
		 return count;
	 }
	 public static void fourSum(int[] A, int[] B, int[] C, int[] D)
	 {
		 Map<Integer,ArrayList<Integer>> sums = new HashMap<>();
		 
		 for(int i=0; i<A.length;i++) 
		 {
			 for(int j=0;j<B.length;j++)
			 {
				 int sum = A[i]+B[j];
				 ArrayList<Integer> al= new ArrayList<>();
				 al.add(A[i]);
				 al.add(B[j]);
				 sums.put(sum, al);
			 }
		 }
		 for(int k=0; k<C.length;k++) 
		 {
			 for(int z=0;z<D.length;z++)
			 {
				 int sum = -(C[k]+D[z]);
				 if(sums.containsKey(sum)) 
				 {
					 System.out.print(sums.get(sum)+" "+C[k]+" "+D[z]);
				 }
			 }
		 }
	 }
	 
	 /*********************************************************************************************/
	 //4 sum
	 //2 loops for pairs n^2
	 //sorting nlogn
	 // last n
	 //O(n^2LOGN)
	 static class Four implements Comparable<Four>
	 {
		 int val;
		 int i;
		 int j;
		 Four(int val,int i,int j)
		 {
			 this.val=val;
			 this.i=i;
			 this.j=j;
		 }
		 @Override
		 public int compareTo(Four o)
		 {
			 return this.val-o.val;
		 }
	 }
	 //find sum of all pairs
	 //sort pairs
	 //find k in sorted pairs
	 public static void findFourElements(int[]arr,int x)
	 {
		 int n=arr.length;
		 //Four aux[]=new Four[n*(n-1)/2];
		 List<Four>aux=new ArrayList<>();
		 int index=0;
		 for(int i=0;i<arr.length-1;i++)
		 {
			 for(int j=i+1;j<arr.length;j++)
			 {
				 int v = arr[i]+arr[j];
				 Four f=new Four(v, i, j);
				 aux.add(f);
			 }
		 }
		 Collections.sort(aux);
		 
		 int left =0;
		 int right=aux.size()-1;
		 while(left<=right)
		 {
			 Four a = aux.get(left);
			 Four b = aux.get(right);
			 if(a.val+b.val == x)
			 {
				 System.out.print(arr[a.i]+" "+arr[a.j]+" "+arr[b.i]+" "+arr[b.j]);
				 break;
				 //right--;
				 //left++;
				 
			 }
			 else if(a.val+b.val > x)
				 right--;
			 else
				 left++;
		 }
	 }
	 
	 /*********************************************************************************************/
	 /*	Replace Word
	  	Input: dict = ["cat", "bat", "rat"]
		sentence = "the cattle was rattled by the battery"
		Output: "the cat was rat by the bat"
	  */
	 public static String replaceWords(List<String> dict, String sentence)
	 {
		 String ans="";
		 for(String s : dict)
		 {
			 insertTrie(s);
		 }
		 String[]split = sentence.split(" ");
		 for(int i=0;i<split.length;i++)
		 {
			 if(i>0)
				 ans+=" ";
			 String s = searchTrie(split[i]);
			 if(s.length()==0)
				 ans+=split[i];
			 else
				 ans+=s;
		 }
		 return ans.toString();
	 }
	 static TrieNode root=new TrieNode();
	 static class TrieNode
	 {
		 boolean isWordEnd=false;
		 TrieNode[]children=new TrieNode[26];
	 }
	 public static void insertTrie(String word)
	 {
		 TrieNode current=root;
		 for(int i=0;i<word.length();i++)
		 {
			 int index = word.charAt(i)-'a';
			 if(current.children[index]==null)
				 current.children[index]=new TrieNode();
			 current=current.children[index];
		 }
		 current.isWordEnd=true;
	 }
	 public static String searchTrie(String word)
	 {
		 TrieNode current=root;
		 for(int i=0;i<word.length();i++)
		 {
			 int index = word.charAt(i)-'a';
			 if(current.children[index]==null)
				 return "";
			 else
				 current=current.children[index];
			 if(current.isWordEnd)
				 return word.substring(0, i+1);
		 }
		 return "";
	 }
	 /*********************************************************************************************/
	 //Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.
	 static ListNode head;
	 static class ListNode {
        int data;
        ListNode next;
        ListNode(int d) {
            data = d;
            next = null;
        }
	 }
	 public static void Solution(ListNode head) 
	 { 
	     head = new ListNode(5);
	     head.next = new ListNode(20);
	     head.next.next = new ListNode(4);
	     head.next.next.next = new ListNode(3);
	     head.next.next.next.next = new ListNode(30);
	     getRandom();
	 }
	 public static int getRandom()
	 {
		 if (head == null)
	            return -1;
	        // Use a different seed value so that we don't get
	        // same result each time we run this program
		 Math.abs(UUID.randomUUID().getMostSignificantBits());
		 int result = head.data;
        
		 ListNode current = head;
		 int n;
		 for (n = 2; current != null; n++) 
		 {
            if (Math.random() % n == 0)
                result = current.data;
            current = current.next;
		 }
		 return result;
	 }
	 /*********************************************************************************************/
	 //Split BST
	 static public class TreeNode
	 {
	     int val;
	     TreeNode left;
	     TreeNode right;
	     TreeNode(int x) { val = x; }
	 }
	 public static TreeNode[] splitBST(TreeNode root, int V)
	 { 
		 if(root==null)
			 return new TreeNode[]{null, null};
		 TreeNode current = root;
		 TreeNode[]ans=new TreeNode[2];
		 TreeNode parent=null;
		 while(current!=null)
		 {
			 if(V == current.val){
				 TreeNode temp = current;
				 if(parent.val > current.val){
					 parent.left=current.right;
					 current.right=null;
				 }
				 if(parent.val < current.val){
					 parent.right=current.right;
					 current.right=null;
				 }
				 ans[0]=temp;
                 if(temp!=root)
				    ans[1]=root;
				 return ans;
			 }
			 else if(V < current.val)
			 {
				 parent=current;
				 current=current.left;
			 }
			 else
			 {
				 parent=current;
				 current=current.right;
			 }
		 }
		 ans[0]=null;
	        ans[1]=root;
	     return ans;
	 }
	 public static TreeNode[] splitBSTR(TreeNode root, int V)
	 {
		 TreeNode[] list= new TreeNode[2];
		 splitBSTR(root,V,list);
		 list[1]=root;
		 return list;
	 }
	 public static TreeNode splitBSTR(TreeNode root, int V,TreeNode[] list)
	 {
		 if(root==null)
			 return root;
		 if(V < root.val)
			 root.left =splitBSTR(root.left,V,list);
		 else if(V > root.val)
			 root.right = splitBSTR(root.left,V,list);
		 else
		 {
			 TreeNode temp = root;
			 temp.right=null;
			 list[0]=temp;
			 return root.right;
		 }
		return root;	
	 }
	 /*********************************************************************************************/
	 //shuffle array
	 public static void Solution()
	 {
		 int[] a = null;
		 
		 for(int i=0;i<10;i++)
		 {
			 a = shuffle(); 
			 for(int j=0;j<a.length;j++)
			 {
				 System.out.print(a[j]+" ");
			 }
			 System.out.println();
		 }	
	 }
	 static int[]nums= {1,2,3,4,5,6,7,8};
	 public static int[] shuffle()
	 {
		 int[]res=new int[nums.length];
		 int index=0;
		 int max = nums.length;
		 int min = 0;
		 Random rand = new Random();
		 Set<Integer>set = new HashSet<>();
		 while(set.size()!=nums.length)
		 {
			 int n = rand.nextInt(max) + min;
			 if(set.contains(n))
				 continue;
			 set.add(n);
			 res[index++]=nums[n];
		 }	 
		 return res;
	 }
	 
	 /*********************************************************************************************/
	 //Flip Game II
	 //https://leetcode.com/problems/flip-game-ii/description/
	 public static boolean canWin(char[] s)
	 {
		 for(int i=0;i<s.length -1;i++)
		 {
			 if(s[i] == '+' && s[i+1] == '+')
			 {
				 s[i]=s[i+1]='-';
				 boolean res = canWin(s);
				 s[i]=s[i+1]='+';
				 if(!res)
					 return true;
			 }
		 }
		 return false;
	 }
	 
	 public static boolean canWin2(char[] s,int player)
	 {
		 if(player==1)
		 {
			 for(int i=0;i<s.length-1;i++)
			 {
				 if(s[i]=='+' && s[i+1]=='+')
				 {
					 s[i]=s[i+1]='-';
					 boolean res = canWin2(s,2);
					 s[i]=s[i+1]='+';
					 if(!res)
						 return true;
				 }
			 }
			 return false;
		 }
		 else
		 {
			 for(int i=0;i<s.length-1;i++)
			 {
				 if(s[i]=='+' && s[i+1]=='+')
				 {
					 s[i]=s[i+1]='-';
					 boolean res =canWin2(s,1);
					 s[i]=s[i+1]='+';
					 if(!res)
						 return true;
				 }
			 }
			 return false;
		 }
	 }
	 /*********************************************************************************************/
	 //Permutations
	 //O(n!)
	 public static List<List<Integer>> permute(int[] nums) 
	 {
		 List<List<Integer>>list = new ArrayList<>();
		 permute(nums,list,0,nums.length-1);
		 return list;
	 }
	 public static void permute(int[] nums, List<List<Integer>>list,int l,int r)
	 {
		 if(l==r)
		 {
			 List<Integer>ls = new ArrayList<>();
			 for (int i = 0; i < nums.length; i++){
					ls.add(nums[i]);
				}
			 list.add(ls);
			 System.out.println();
			 return;
		 }
		 for(int i=l;i<=r;i++)
		 {
			swap(nums,l,i);
			permute(nums,list,l+1,r);
			swap(nums,l,i);
		 }
	 }
	 /*********************************************************************************************/
	 public static int integerBreak(int n,int breaks,List<Integer>list)
	 {
		int max=0;
		 if(n==0)
		 {
			 int res=1;
			 for(int i=0;i<list.size();i++)
				 res*=list.get(i);
			 return res;
		 }
		 if(n<breaks)
			 return 0;
		 list.add(breaks);
		 int a = integerBreak(n-breaks, 1,list);
		 list.remove(list.size()-1);
		 
	     int b =integerBreak(n, 2,list);
	     
	     return Math.max(a, b);
	 }
	 /*********************************************************************************************/
	 static class Brick
	 {
		 int i;
		 int j;
		 Brick(int i,int j)
		 {
			 this.i=i;
			 this.j=j;
		 }
	 }
	 //Brick Wall
	 public static int leastBricks(List<List<Integer>> wall)
	 {
		HashMap < Integer, Integer > map = new HashMap < > ();
		for (List < Integer > row: wall) {
		    int sum = 0;
		    for (int i = 0; i < row.size() - 1; i++)
		    {
		        sum += row.get(i);
		        if (map.containsKey(sum))
		            map.put(sum, map.get(sum) + 1);
		        else
		            map.put(sum, 1);
		    }
		}
		int res = wall.size();
		int max=0;
		for(Entry<Integer, Integer>entry: map.entrySet())
		{
			int a = entry.getValue();
			if(max<a)
				max=a;
		}
		    
		return res-max;
	 }
	 /*********************************************************************************************/
	 //Diagonal Traverse
	 /*
	  * 				{1, 2, 3, 4},
						{5, 6, 7, 8},
						{9, 10,11,12},
						{13,14,15,16}};
	  */
	 public static void findDiagonalOrder(int[][] matrix) 
	 {
		 int r = 0, c = 0, m = matrix.length, n = matrix[0].length, arr[] = new int[m * n];
	     for (int i = 0; i < arr.length; i++) 
	     {
	            System.out.print( matrix[r][c]+" ");
	            if ((r + c) % 2 == 0) // moving up
	            { 
	                if(c == n - 1)
	                {
	                	r++;
	                }
	                else if(r == 0)
	                {
	                	c++;
	                }
	                else
	                {
	                	r--; c++;
	                }
	            }
	            else // moving down
	            {                
	                if(r == m - 1)
	                {
	                	c++;
	                }
	                else if (c == 0)
	                {
	                	r++;
	                }
	                else
	                {
	                	r++; c--;
	                }
	            }   
	        }   
	 }
}
