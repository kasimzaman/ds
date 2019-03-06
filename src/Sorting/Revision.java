package Sorting;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import Sorting.NearestNeighbor.Point;

public class Revision
{
	class Point
	{
		int x;
		int y;
		Point(int x,int y)
		{
			this.x=x;
			this.y=y;
		}
	}
	class Distance implements Comparable<Distance>
	{
		Point point;
		int distance;
		Distance(Point point, int dist)
		{
			this.point=point;
			this.distance=dist;
		}
		@Override
		public int compareTo(Distance o)
		{
			return this.distance - o.distance;
		}
	}
	
	private void nn()
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
	}
	private Point[] fnn(Point[] points, int K, Point p)
	{
		Point[]res =new Point[K];
		Distance[]distances =new Distance[K];
		for(int i=0;i<points.length;i++)
		{
			int d = getDistance(points[i], p);
			distances[i]=new Distance(points[i],d);
		}
		return res;
	}
	
	private void qs(Distance[]distances,int low,int high, int k)
	{
		if(low>high)
			return;
		int p = partition(distances,low,high);
		if(k==p)
			return;
		else if(k>p)
			qs(distances,p+1,high,k);
		else
			qs(distances,low,p-1,k);
		
	}
	private int partition(Distance[]distances,int low,int high)
	{
		int left = low+1;
		int right = high;
		Distance pivot = distances[low];
		while(left<right)
		{
			if(distances[left].compareTo(pivot)==-1)
				left++;
			if(distances[left].compareTo(pivot)>=1)
				right--;
			if(left<right)
			{
				swap(left,right,distances);
				left++;
				right--;
			}
		}
		swap(low,right,distances);
		return right;
	}
	private void swap(int left,int right,Distance[]dist)
	{
		Distance temp = dist[left];
		dist[left]=dist[right];
		dist[right]=temp;
	}
	
	
	
	//(x,y) (a,b) => sq-root of (x-a)^2 + (y-b)^2
	private int getDistance(Point a, Point b)
	{
		return (int)Math.sqrt((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y));
	}
	
	private void msort()
	{
		int[]arr={2,1,6,5,4,3,2};
		int[]res=new int[arr.length];
		ms(arr,0,arr.length-1,res);
	}
	private void ms(int[]arr,int low,int high,int[]res)
	{
		if(low>high)
			return;
		int mid = low+(high-low)/2;
		ms(arr,low,mid,res);
		ms(arr,mid+1,high,res);
		merge(arr,low,high,mid,res);
		copy(arr,res,low,high);
	}
	private void copy(int[]arr,int[]res,int st,int end)
	{
		for(int i=st;i<=end;i++)
		{
			arr[i]=res[i];
		}
	}
	private void merge(int[]arr,int low,int mid,int high,int[]res)
	{
		if(low>high)
			return;
		int index=low;
		int left=low;
		int right=mid+1;
		while(left<=mid && right<=high)
		{
			if(arr[left]<arr[right])
			{
				res[index++]=arr[left];
				left++;
			}
			if(arr[left]>arr[right])
			{
				res[index++]=arr[right];
				right++;
			}
			else
			{
				res[index++]=arr[right];
				right++;
				left++;
			}
		}
		while(arr[left]<=mid)
		{
			res[index++]=arr[left];
			left++;
		}
		while(arr[right]<=high)
		{
			res[index++]=arr[right];
			right++;
		}
	}
	
	public void sortChars(char[]str)
	{
		int[]fmap= new int[256];
		for(int i=0;i<str.length;i++)
		{
			int n = str[i];
			fmap[n]++;
		}
		
		for(int i=0;i<256;i++)
		{
			if(fmap[i]==0)
				continue;
			int n = fmap[i];
			char c = (char)i;
			for(int j=0;j<n;j++)
			{
				System.out.println(c);
			}
		}
	}
	
}
