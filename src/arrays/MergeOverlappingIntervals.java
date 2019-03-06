package arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
    Input: {{1,3}, {2,4}, {5,7}, {6,8} }. The intervals {1,3} and {2,4} overlap with
	each other, so they should be merged and become {1, 4}. Similarly {5, 7} and {6, 8} should
	be merged and become {5, 8}.
	Write a function which produces the set of merged intervals for the given set of intervals.
	Solution: http://www.geeksforgeeks.org/merging-intervals/
    file:///Users/ali/Documents/workspace/KickStart/Notes/Arrays/MergeIntervals
 
 */

public class MergeOverlappingIntervals
{
	public void mergeIntervals()
	{
		int[][] a={{1,3},{2,6},{4,9},{10,13},{11,15},{16,20},{20,25}};
		
		List<Interval> intervals = new ArrayList<>();
		for(int i=0; i<a.length;i++)
		{
			intervals.add(new Interval(a[i][0],a[i][1]));
		}
		sortArray(intervals);
		List<Interval> list = mergeInterval(intervals);
		for(int i=0; i<list.size();i++)
		{
			System.out.println(list.get(i).start+" "+list.get(i).end);
		}
		
	}
	private List<Interval> mergeInterval(List<Interval> a)
	{
		List<Interval> intervals = new ArrayList<Interval>();
		Interval prev = a.get(0);
		
		for(int i=1;i<a.size();i++)
		{
			Interval current = a.get(i);
			if(current.start > prev.end){
				intervals.add(prev);
				prev = current;
			}
			else
			{
				Interval merged = new Interval(prev.start, Math.max(prev.end, current.end));
				prev = merged;
			}
		}
		intervals.add(prev);
		return intervals;
	}
	private List<Interval> sortArray(List<Interval> a)
	{
		Collections.sort(a, new Comparator<Interval>() {

			@Override
			public int compare(Interval o1, Interval o2) {
				if(o1.start != o2.start)
					return o1.start - o2.start;
				else
					return 0;
			}
		});
		return a;
	}

}

class Interval
{
	int start;
	int end;
	public Interval(int a, int b)
	{
		this.start = a;
		this.end= b;
	}
}
