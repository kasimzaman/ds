package Sorting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/*
 	After the 1st partition get done the pivot is placed in it right index in the array.
 	Every element on the left is smaller and on the right is larger.
 	Here we are trying to find the Kth nearest neighbors. So we compare the K with pivot index every time.
 	If they are the same we terminate the QS recursion. Because at this point pivot is K and every thing to its
 	left will be smaller, hence closer to out point. All the distances will be <= to the pivot.
  
    Heap based solution: create max heap.  :  O(NLogK) K is size of heap
    PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(10, new Comparator<Integer>()
    {
    	@Override
    	public int compare(Integer a, Integer b)
    	{
        	return b - a; 
    	}
	});
	
	OR
	
	PriorityQueue<Integer> maxHeap = new PriorityQueue<>(10, Collections.reverseOrder());
	
	OR
	
	PriorityQueue<Integer> pq = new PriorityQueue<>(10,(x, y) -> y - x);
	 
 */
public class NearestNeighbor
{
	//Quick sort based solution
	//As answer take first K points. This is too NlogN complexity but it is possible optimize to approximate O(N).
	//If skip sorting of unnecessary sub arrays. When you split array by 2 sub arrays you should take only array where Kth index located
	public void nn()
	{	
		List<Point>points = new ArrayList<>();
		points.add(new Point(5,5));
		points.add(new Point(4,4));
		points.add(new Point(3,3));
		points.add(new Point(2,2));
		points.add(new Point(1,1));
		points.add(new Point(6,6));
		points.add(new Point(7,7));
		
		Point pt = new Point(0,0);
		
		List<Point> res = nearestNeighbor(points,pt,2);
	}
	private List<Point> nearestNeighbor(List<Point>points,Point p, int K)
	{
		Distance[]distances = new Distance[points.size()];
		for(int i=0;i<distances.length;i++)
		{
			distances[i]= new Distance(distance(p, points.get(i)),i);
		}
		QS(distances,0,distances.length-1,K);
		List<Point> res=new ArrayList<>();
		for(int i=0;i<K;i++)
		{
			res.add(points.get(distances[i].index));
		}
		return res;
	}
	private void QS(Distance[]list,int low,int high,int K)
	{
		if(low<high)
		{
			int p = partition(list,low,high);
			if(p==K)
				return;
			else if(K>p)
				QS(list,p+1,high,K);
			else
				QS(list,low,p-1,K);
		}
	}
	private int partition(Distance[]A,int low,int high)
	{
		int right=high;
		if(low < high)
		{	
			int p=low;
			
			int left=low+1;
			while(left<=right)
			{
				while(A[left].compareTo(A[p])<0)
					left++;
				while(A[right].compareTo(A[p])>0)
					right--;
				if(left<=right)
				{
					swap(A,left,right);
					left++;
					right--;
				}
			}
			swap(A,p,right);
		}
		return right;
	}
	private void swap(Distance[]A, int a, int b)
	{
		Distance temp = A[a];
		A[a]=A[b];
		A[b]=temp;
	}
	private int distance(Point p1, Point p2)
	{
		int n=((p1.x - p2.x)*(p1.x - p2.x)) + ((p1.y - p2.y)*(p1.x - p2.y));
		return (int)Math.sqrt(n);
	}
	class Distance implements Comparable<Distance>
	{
		int d;
		int index;
		Distance(int d,int i)
		{
			this.d=d;
			this.index=i;
		}
		@Override
		public int compareTo(Distance o)
		{
			return this.d - o.d;
		}
	}
	class Point implements Comparable<Point>
	{
		int x;
		int y;
		Point(int x,int y)
		{
			this.x=x;
			this.y=y;
		}
		@Override
		public int compareTo(Point p)
		{
			return 1;
		}
	}
	
	/*****************************************************/
	/*private List<Point> nearestKPoint_1(List<Point> list, final Point center, int k) {
	    List<Point> ans = new ArrayList<>();
	    PriorityQueue<Point> maxHeap = new PriorityQueue<>(k + 1, new Comparator<Point>() {
	        @Override
	        public int compare(Point o1, Point o2) {
	            return distance(center, o2) - distance(center, o1);
	        }
	    });
	    for (Point p : list) {
	        maxHeap.offer(p);
	        if (maxHeap.size() > k) {
	            maxHeap.poll();
	        }
	    }
	    Iterator<Point> i = maxHeap.iterator();
	    while (i.hasNext()) {
	        ans.add(i.next());
	    }
	    return ans;
	}

	*/

	
}
