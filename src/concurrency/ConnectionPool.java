package concurrency;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool 
{
	int MAXSIZE=100;
	Queue<Object>freeObjs;
	int max=MAXSIZE;
	int allocated=0;
	
	Lock lock=new ReentrantLock();
	Condition objectsAvailable = lock.newCondition();
	//Allocated does not mean allocated to users, rather it means that maxsize objects have been constructed, so no more will be constructed anymore
	public Object getFromPool()
	{
		Object o=null;
		lock.lock();
		try{
			if(freeObjs.size()==0 && allocated==MAXSIZE)//All objects are taken, so there is nothing in the list.
				objectsAvailable.await();
			if(freeObjs.size()>0)
				o = freeObjs.poll();
			else
			{
				allocated++; //free size is 0 and nothing allocated
				if(o==null)
				o = new Object();
			}
		}catch(Exception e){
		}finally{
			lock.unlock();
		}
		return o;
	}
	public void returnToPool(Object o)
	{
		lock.lock();
		try{
			freeObjs.add(o);
			objectsAvailable.signal();
			
		}catch(Exception e){
		}finally {
			lock.unlock();
		}
	}
}
