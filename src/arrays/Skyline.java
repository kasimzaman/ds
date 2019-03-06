package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
//

/**
    You are given a set of rectangles in no particular order. They have varying widths and
	heights, but their bottom edges are collinear, so that they look like buildings on a
	skyline. For each rectangle, you’re given the x position of the left edge, the x position of
	the right edge, and the height. Your task is to draw an outline around the set of
	rectangles so that you can see what the skyline would look like when silhouetted at
	night.
	Each building is represented by triplet (left, ht, right)
	‘left': is x coordinated of left side (or wall).
	‘right': is x coordinate of right side
	‘ht': is height of building.
	 * Notes
	 * file:///Users/ali/Documents/workspace/KickStart/Notes/Arrays/Skyline
	 * Youtube : https://www.youtube.com/watch?v=GSBLe8cKu0s
	 * Youtube : https://www.youtube.com/watch?time_continue=649&v=Cv0ft2dFz80
	 * 
	 * The TreeMap class implements the Map interface by using a tree. A TreeMap provides an efficient means of storing key/value
	 * pairs in sorted order, and allows rapid retrieval.
	   You should note that, unlike a hash map, a tree map guarantees that its elements will be sorted in an ascending key order.
	 * 
	 * Get,Put,Remove,Contains => log(N)
	 * 
	 * 
	 * 
 */
public class Skyline
{
	public void skyline()
	{
		Scanner scan = new Scanner(System.in);
		int[][] arr= {{1,11,5},{2,6,7},{3,13,9},{12,7,16},{14,3,25},{19,18,22},{23,13,29},{24,4,28}};
		//int[][] arr= {{1,10,4},{2,5,5},{3,15,6}};
		//List<int[]> res = buildskyline(0,arr.length-1,arr);
		//List<int[]> res =getSkyline(0,arr.length-1,arr);
		List<int[]> res=getSkyLine(arr);
		for (int i = 0;  i < res.size(); i++)
        {
            System.out.println(res.get(i)[0]+","+res.get(i)[1]);
        }
	}
	
	/*********************************************Implementation Using QUEUE*******************************************************/
	List<int[]> getSkyLine(int[][]buildings)
	{
		List<int[]> result = new ArrayList<>();
		
		//Make building point list. This will by n*2 as for every point we store start and end.
		BuildingPoint[] bps = new BuildingPoint[buildings.length*2];
		int index=0;
		for(int[]building : buildings)
		{
			bps[index] = new BuildingPoint();
			bps[index].x = building[0];
			bps[index].height = building[1];
			bps[index].isStart = true;
			
			bps[index+1] = new BuildingPoint();
			bps[index+1].x = building[2];
			bps[index+1].height = building[1];
			bps[index+1].isStart = false;
			index += 2;
		}
		Arrays.sort(bps);
		
		TreeMap<Integer, Integer> q = new TreeMap<>();
		int maxSoFar=0;
		q.put(0, 1);
		
		for(BuildingPoint bp : bps)
		{
			if(bp.isStart)
			{
				if(q.get(bp.height)!=null){
					int val = q.get(bp.height);
					q.put(bp.height, val+1);
				}
				else
					q.put(bp.height, 1);
			}
			else
			{
				if(q.containsKey(bp.height)){
					int val = q.get(bp.height);
					if(val == 1)
						q.remove(bp.height);
					else
						q.put(bp.height, val-1);
				}
				else
					q.remove(bp.height);
				
			}
			//Now check if it changes the max. if it did then change the max and add to result.
			int currentHeight = q.lastKey();//It is used to return the last (highest) key currently in this sorted map.
			if(maxSoFar != currentHeight){
				result.add(new int[]{bp.x,currentHeight});
				maxSoFar = currentHeight;
			}
		}
		
		return result;
	}
	
	/***********************************************Implementation Using Merge Sort*******************************************************/
	private List<int[]> buildskyline(int low, int high, int[][] list)
	{
		List<int[]> result = new ArrayList<>();
		if(low > high)
			return result;
		else if(high == low)
		{
			int x = list[low][0];
			int h = list[low][1];
			int r = list[low][2];
			int[] element1 = {x,h};
			int[] element2 = {r,0};
			result.add(element1);
			result.add(element2);
			return result;
		}
		else
		{
			int mid = low + (high - low)/2;
			List<int[]> lowerList = buildskyline(low, mid, list);
			List<int[]> higherList = buildskyline(mid+1, high, list);
			return mergedList(lowerList,higherList);
		}
	}
	
	private List<int[]> mergedList(List<int[]> lowerList,List<int[]> higherList)
	{
		List<int[]> merged= new ArrayList();
		int h1=0;
		int h2=0;
		
		while(true)
		{
			if(lowerList.isEmpty() || higherList.isEmpty())
				break;
			int[] rect1 = lowerList.get(0);
			int[] rect2 = higherList.get(0);
			int[] mergedRect = new int[2];
			if(rect1[0] < rect2[0])
			{
				mergedRect[0]=rect1[0];
				mergedRect[1]=rect1[1];
				if(rect1[1] < h2)
					mergedRect[1]=h2;
				h1 = rect1[1];
				lowerList.remove(0);
				
			}
			else if(rect1[0] > rect2[0])
			{
				mergedRect[0] = rect2[0];
				mergedRect[1] = rect2[1];
				if(rect2[1] < h1)
					mergedRect[1] = h1;
				h2 = rect2[1];
				higherList.remove(0);
			}
			else
			{
				mergedRect[0]=rect1[0];
				mergedRect[1]=Math.max(rect1[1], rect2[1]);
				h1=rect1[1];
				h2=rect2[2];
				lowerList.remove(0);
				higherList.remove(0);
			}
			merged.add(mergedRect);
		}
		if(lowerList.isEmpty())
		{
			while(!higherList.isEmpty())
			{
				merged.add(higherList.remove(0));
			}
		}
		else
		{
			while(!lowerList.isEmpty())
				merged.add(lowerList.remove(0));
		}
		//remove duplicates
		;
		int index=0;
		int current=0;
       
        while (current < merged.size())
        {
            boolean dupeFound = true;
            int i = current + 1;
            while ((i < merged.size()) &&  dupeFound)
            {
                if (merged.get(current)[1] == merged.get(i)[1])
                {
                    dupeFound = true;
                    merged.remove(i);
                }
                else
                {
                    dupeFound = false;
                }
            }
            current += 1;
        }
		
		return merged;
	}

	
    private int greater(int a, int b)
    {
        if (a > b) return a;
        return b;
    }
     
    // given two skylines, merge them
    private List<int[]> mergeSkylines(List<int[]> skylineListLower, List<int[]> skylineListHigher)
    {
        int h1 = 0, h2 = 0;
        int newIndex = 0;
        List<int[]> skylineMerged = new ArrayList<int[]>();;
     
        while (true)
        {
            if (skylineListLower.isEmpty() || skylineListHigher.isEmpty())
            {
                break;
            }
             
            // first key points from both the skylines
            int [] stripe1 = skylineListLower.get(0);
            int [] stripe2 = skylineListHigher.get(0);
             
            // 0: 'x' co-ordinate, 1: height
            int [] mergedStripe = new int[2];
             
            // comparing 'x' co-ordinates of current key points of skyline-1 and skyline-2 
            if (stripe1[0] < stripe2[0]) // key point from skyline-1 is chosen 
            {
                mergedStripe[0] = stripe1[0];
                mergedStripe[1] = stripe1[1];
             
                // if 'y' co-ordinate for key point from skyline-1 is less than last seen height of skyline-2
                // then we need to update merged key point's 'y' co-ordinate to last seen height of skyline-2
                if (stripe1[1] < h2)
                {
                    mergedStripe[1] = h2;
                }
                 
                // update the last seen height for skyline-1
                // note that last seen height for skyline-2 is not updated 
                h1 = stripe1[1];
                 
                // move to next key point for this skyline
                skylineListLower.remove(0);
            }
            else if (stripe2[0] < stripe1[0])
            {
                mergedStripe[0] = stripe2[0];
                mergedStripe[1] = stripe2[1];
                 
                if (stripe2[1] < h1)
                {
                    mergedStripe[1] = h1;
                }
                 
                // update the last seen height of skyline-2
                // note that last seen height of skyline-
                h2 = stripe2[1];
                 
                skylineListHigher.remove(0);
            }
             
            // if 'x' co-ordinates of key points for both skylines are same
            // then choose the key point with greater 'y' value
            // advance key points for both and hence update last seen height for both
            else
            {
                mergedStripe[0] = stripe2[0];
                mergedStripe[1] = greater(stripe1[1], stripe2[1]);
                 
                h1 = stripe1[1];
                h2 = stripe2[1];
                 
                skylineListLower.remove(0);
                skylineListHigher.remove(0);
            }
             
            skylineMerged.add(mergedStripe);
        }
         
        if (skylineListLower.isEmpty())
        {
            while (!skylineListHigher.isEmpty())
            {
                skylineMerged.add(skylineListHigher.remove(0));
            }
        }
        else
        {
            while (!skylineListLower.isEmpty())
            {
                skylineMerged.add(skylineListLower.remove(0));
            }
        }
         
        // remove redundant key points from merged skyline 
        int current = 0;
        while (current < skylineMerged.size())
        {
            boolean dupeFound = true;
            int i = current + 1;
            while ((i < skylineMerged.size()) &&  dupeFound)
            {
                if (skylineMerged.get(current)[1] == skylineMerged.get(i)[1])
                {
                    dupeFound = true;
                    skylineMerged.remove(i);
                }
                else
                {
                    dupeFound = false;
                }
            }
            current += 1;
        }
         
        return skylineMerged;
    }
 
    private List<int[]> getSkyline(int low, int high, int[][]buildings)
    {
        List<int[]> skyLineList = new ArrayList<int[]>();
 
        // invalid case
        if (low > high)
        {
            return skyLineList;
        }
        // base case: if there is only one building, only two key points define the skyline for it
        else if (low == high)
        {
            int x1 = buildings[low][0];
            int x2 = buildings[low][1];
            int h  = buildings[low][2];
             
            int[] element1 = {x1, h}; // first key point
            int[] element2 = {x2, 0}; // second key point
             
            skyLineList.add(element1);
            skyLineList.add(element2);
             
            return skyLineList;
        }
        // general case
        else
        {
            int mid = (low + high) / 2;
             
            // get the skyline for lower half
            List<int[]> skylineListLower = getSkyline(low, mid, buildings);
          
            // get the skyline for upper half
            List<int[]> skylineListHigher = getSkyline(mid+1, high, buildings); 
             
            // merge skylines for these two halves
            return mergeSkylines(skylineListLower, skylineListHigher);  
        }
    }
    
}

class BuildingPoint implements Comparable<BuildingPoint>
{
	int x;
	int height;
	boolean isStart;
	
	@Override
	public int compareTo(BuildingPoint o)
	{
		if(this.x != o.x)
			return this.x - o.x;
		else
			return (this.isStart ? -this.height : this.height) - (o.isStart ? -o.height : o.height);
	}
}
     
     
//    public static void main(String[] args)
//    {
//        int[][] buildings = { {2,9,10}, {3,6,15}, {5,12,12}, {13,16,10}, {13,16,10}, {15,17,5}};
//         
//        SkylineSolution slnForSkyline = new SkylineSolution();
//         
//        List<int[]> skyLine = slnForSkyline.getSkyline(buildings);
//         
//        System.out.println("Skyline for given buildings would look like: ");
//        for (int i = 0;  i < skyLine.size(); i++)
//        {
//            System.out.println(skyLine.get(i)[0]+","+skyLine.get(i)[1]);
//        }
//    }
