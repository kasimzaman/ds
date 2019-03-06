package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SnakesLadders
{	
	int[]moves=new int[36];
	
	public void sl()
	{
		Arrays.fill(moves, 0);
		moves[33]=11;
		moves[31]=29;
		moves[19]=5;
		moves[23]=15;
		moves[16]=3;
		moves[1]=14;
		moves[4]=6;
		moves[8]=26;
		moves[17]=28;
		moves[24]=34;
		findShortestPath();
	}
	//O(N)
	private void findShortestPath()
	{
		boolean visited[] = new boolean[36];
		Queue<Node>que = new LinkedList<>();
		que.add(new Node(0,0));
		visited[0]=true;
		while(!que.isEmpty())
		{
			Node node = que.poll();
			int pos=0;
			for(int i=1;i<=6;i++)
			{
				if(visited[i]==true)
					continue;
				pos=0;
				pos = node.pos + i;
				if(pos > 35)
					continue;
				if(pos == 35){
					System.out.println(node.distance);
					return;
				}
				if(moves[pos]!=-1)
					que.add(new Node(moves[pos],node.distance+1));
			}
		}
	}
	class Node
	{
		int pos;
		int distance=0;
		public Node(int p,int d)
		{
			this.pos=p;
			this.distance=d;
		}
	}
	
}
