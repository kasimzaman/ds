package arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeOverlappingIntervalsR
{
	class Interval implements Comparable<Interval>
	{
		int x;
		int y;
		Interval(int x,int y)
		{
			this.x=x;
			this.y=y;
		}
		@Override
		public int compareTo(Interval o)
		{
			return Integer.compare(this.x, o.x);
		}
	}
	public void mergeIntervals()
	{
		int[][] a={{1,3},{2,6},{4,9},{10,13},{11,15},{16,20},{20,25}};
		List<Interval> intervals = new ArrayList<>();
		for(int i=0; i<a.length;i++)
		{
			intervals.add(new Interval(a[i][0],a[i][1]));
		}
		Collections.sort(intervals);
		merge(intervals);
	}
	private void merge(List<Interval> intervals)
	{
		List<Interval> res = new ArrayList<>();
		Interval v = intervals.get(0);
		for(int i=1;i<intervals.size();i++)
		{
			Interval v2 = intervals.get(i);
			if(v.y>v2.x)
			{
				v.y=Math.max(v.y, v2.y);
			}
			else
			{
				res.add(v);
				v=v2;
			}
		}
		res.add(v);
	}
	
}