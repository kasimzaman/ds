package concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Revision
{
	class PCQ
	{
		int size;
		Object[]data;
		int getIndex;
		int putIndex;
		int count;
		Lock lock = new ReentrantLock();
		Condition notFull = lock.newCondition();//Condition variable allows us to wait for a condition.
		Condition notEmpty = lock.newCondition();
		PCQ(int size)
		{
			this.size=size;
			data = new Object[size];
			getIndex=0;
			putIndex=0;
			count=0;
		}
		
		public Object consume()
		{
			Object obj=null;
			lock.lock();
			try
			{
				while(count==0)
					notEmpty.wait();
				obj = data[getIndex];
				count--;
				getIndex=getIndex+1 % size;
				notFull.signal();
			}
			catch(Exception ex){}
			finally
			{
				lock.unlock();
			}
			return obj;
		}
		public void produce(Object obj)
		{
			lock.lock();
			try
			{
				while(count==size)
					notFull.wait();
				data[putIndex]=obj;
				count++;
				putIndex=putIndex+1%size;
				notEmpty.signal();
			}catch(Exception ex){}
			finally
			{
				lock.unlock();
			}
		}
	}
	
	class Producer implements Runnable
	{
		PCQ pq;
		Producer(PCQ que)
		{
			this.pq=que;
		}
		@Override
		public void run()
		{
			for(int i=0;i<10;i++)
			{
				try{
					Thread.sleep(1);
					pq.produce(i);
				}catch(Exception e){}
			}
		}
		public void exit()
		{
			pq.produce("exit");
		}
	}
	
	class Consumer implements Runnable
	{
		PCQ cq;
		Consumer(PCQ q)
		{
			this.cq=q;
		}
		@Override
		public void run()
		{
			Object msg="";
			while((msg=cq.consume())!="exit")
			{
				try{
					Thread.sleep(1);
					System.out.println(msg);
				}catch(Exception x){}
			}
		}
	}
	
	/************************************************/
	class ConnectionPool
	{
		int size=100;
		Queue<Object>freeObjects=new LinkedList();
		int allocated=0;
		Lock lock = new ReentrantLock();
		Condition available = lock.newCondition();
		
		public Object getObject()
		{
			Object obj=null;
			lock.lock();
			try
			{
				if(allocated == size && freeObjects.size()==0)
					available.wait();
				if(freeObjects.size()> 0)
					obj = freeObjects.poll();
				else
				{
					obj=new Object();
					allocated++;
				}
			}catch(Exception e){}
			finally{
				lock.unlock();
			}
			return obj;
		}
		
		public void add(Object obj)
		{
			lock.lock();
			try
			{
				freeObjects.add(obj);
				available.signal();
			}catch(Exception e){}
			finally{lock.unlock();}
		}
	}

}
