package DP;

/*
 * If array sum is even then we can find the subset which equals the half of the total.
 * If its odd then no result
 * 
 */
//file:///Users/ali/Documents/workspace/KickStart/Notes/DP/BalancedPartition/balancedpartition.png
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class BalancedPartition
{
	static int calls=0;
	static int count=0;
	public void bp()
	{
		ArrayList<Integer> sf=new ArrayList<>();
		ArrayList<Integer> indexs=new ArrayList<>();
		int[] arr={4,1,-5,6,-11,3,2,1,3,4,-9,8,-6,4,3,4,3,4,3,44,67,90,-1};
		
		int total=0;
		for(int e:arr){
			total+=e;
			
		}
		Map<String, Boolean>memo= new HashMap<>();
		if(partition(arr,0,total/2,memo))
			System.out.println("true");
		else
			System.out.println("false");
		System.out.println(calls);
		calls=0;
		BPResult  res=balancedPartition(arr,0,sf,indexs,total/2);
		int[] result = new int[arr.length];
		int index=0;
		for(Integer e:res.list){
			result[index++]=e;
			System.out.print(e+",");
		}
		System.out.print(" and ");
		for(int i=0;i<arr.length;i++){
			if(!res.indexs.contains(i)){
				result[index++]=arr[i];
				System.out.print(arr[i]+",");
			}
		}
		System.out.println("calls"+calls);
		
	}
	
	private BPResult balancedPartition(int[] arr,int start,ArrayList<Integer> sf,ArrayList<Integer> indexs,int total)
	{
		BPResult res = new BPResult(null,null);
		if(start>=arr.length)
		{count++;
			int sum=0;
			for(Integer e:sf){
				sum+=e;
			}
			if((sum)==total)
				return new BPResult(sf,indexs);
		}
		else
		{
			Object obj = arr[start];
			sf.add(arr[start]);
			Object obj1 = start;
			indexs.add(start);
			 res=balancedPartition(arr,start+1,sf,indexs,total);
			if(res.list==null){
				sf.remove(obj);
				indexs.remove(obj1);
				res=balancedPartition(arr,start+1,sf,indexs,total);
			}
		}
		return res;
    }
	//cache if a solution exists for a given sum and index
	//https://www.youtube.com/watch?v=PKLLFEN3HpU
	//Time Complexity: O(2^n)
	//Memo O(N*SUM)
	private boolean partition(int[]arr,int index,int sum,Map<String, Boolean>memo)
	{calls++;
		if(sum == 0)
			return true;
		if(index >= arr.length)
			return false;
		String key = Integer.toString(sum)+Integer.toString(index);
		if(memo.containsKey(key))
			return memo.get(key);
		boolean r1 = partition(arr, index+1, sum-arr[index],memo);
		boolean r2 = partition(arr, index+1, sum,memo);
		memo.put(key, r1 || r2);
		return memo.containsKey(key);
	}
}
class BPResult
{
	ArrayList<Integer> list = new ArrayList<Integer>();
	ArrayList<Integer> indexs = new ArrayList<Integer>();
	
	public BPResult(ArrayList<Integer> list, ArrayList<Integer> ind)
	{
		this.list = list;
		this.indexs=ind;
	}
	
}
