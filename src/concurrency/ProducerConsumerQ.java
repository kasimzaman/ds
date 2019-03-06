package concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ProducerConsumerQ
{
	final int MAXSIZE=100;
	Object[]data=new Object[MAXSIZE];
	int getIndex,putIndex,count=0;
	Lock lock = new ReentrantLock();
	Condition notEmpty = lock.newCondition();
	Condition notFull = lock.newCondition();
	
	public void Main(String args[])
	{
		ProducerConsumerQ pcq= new ProducerConsumerQ();
		Producer producer = new Producer(pcq);
		Consumer consumer = new Consumer(pcq);
		new Thread(producer).start();
		new Thread(consumer).start();
		
	}
	
	public Object consume()
	{
		Object o=null;
		lock.lock();
		try
		{
			while(count==0)
				notEmpty.await();
			o=data[getIndex];
			count--;
			getIndex=getIndex+1 % MAXSIZE;
			notFull.signal();
		}
		catch(Exception e){
		}finally {
			lock.unlock();
		}
		return o;
	}
	public void produce(Object o)
	{
		lock.lock();
		try
		{
			while(count==MAXSIZE)
				notFull.await();
			data[putIndex]=o;
			count++;
			putIndex=putIndex+1 % MAXSIZE;
			notEmpty.signal();
		}catch(Exception e){
		}finally{
			lock.unlock();
		}
	}
}

class Producer implements Runnable
{
	ProducerConsumerQ queue;
	Producer(ProducerConsumerQ q)
	{
		queue=q;
	}
	@Override
	public void run()
	{
		String msg="testing";
		for(int i=0;i<10;i++)
		{
			try{
				Thread.sleep(1);
				queue.produce(msg+i);
			}catch(Exception e){}
		}
	}
	public void exit()
	{
		queue.produce("exit");
	}
}

class Consumer implements Runnable
{
	ProducerConsumerQ queue;
	Consumer(ProducerConsumerQ q)
	{
		queue=q;
	}
	@Override
	public void run()
	{
		try{
			String msg="";
			while((msg = queue.consume().toString())!="exit"){
				Thread.sleep(1);
				System.out.println(msg);
			}
		}catch(Exception e){
		}
	}
}


