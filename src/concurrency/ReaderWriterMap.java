package concurrency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
  I am using readerwriter lock here because we have supposed that in this case there are a lot more readers than writers. And readers should not be
  blocked if all they are doing is reading and not modifying the data. readerwriter are slower/expensive than normal lock/mutex . So you need a larger 
  number of readers than writers to out weight the cost of using readerwriter lock. So only worth it if you have sufficiently large number of concurrent
  readers.
 */
public class ReaderWriterMap 
{
	
	class Bucket
	{
		ReadWriteLock wrl= new ReentrantReadWriteLock();
		Map<Integer,Object>data=new HashMap<>();
	}
	int MAXSIZE=10;
	int count=0;
	List<Bucket> array = new ArrayList<>();
	ReadWriteLock lock= new ReentrantReadWriteLock();
	
	public Object get(Integer key)
	{
		Object o=null;
		int hash = key.hashCode();
		lock.readLock().lock();
		int bucketId = hash % array.size();
		
		Bucket bucket = array.get(bucketId);
		bucket.wrl.readLock().lock();
		
		if(bucket.data.containsKey(key)){
			o=bucket.data.get(key);
			count--;
		}
		bucket.wrl.readLock().unlock();
		lock.readLock().unlock();
		return o;
	}
	public void set(Integer key, Object value)
	{
		int hash = key.hashCode();
		lock.readLock().lock();
		int bucketId = hash % array.size();
		Bucket bucket = array.get(bucketId);
		bucket.wrl.writeLock().lock();
		bucket.data.put(key,value);
		count++;
		bucket.wrl.writeLock().unlock();
		if(count==MAXSIZE)
			reHash();
		lock.readLock().unlock();
	}
	public void erase(Integer key)
	{
		int hash = key.hashCode();
		lock.readLock().lock();
		int bucketId = hash % array.size();
		Bucket bucket = array.get(bucketId);
		bucket.wrl.writeLock().lock();
		bucket.data.remove(key);
		bucket.wrl.writeLock().unlock();
		lock.readLock().unlock();
	}
	//I need to have a global write lock when rehashing
	private void reHash()
	{
		lock.writeLock().lock();
		
		lock.writeLock().unlock();
	}
}
