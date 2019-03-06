package concurrency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWM
{
	class Bucket
	{
		Map<Integer, Object>data =  new HashMap<Integer, Object>();
		ReadWriteLock bucketLock = new ReentrantReadWriteLock();
	}
	int MAXSIZE=10;
	int count=0;
	List<Bucket> array = new ArrayList<Bucket>(MAXSIZE);
	ReadWriteLock lock = new ReentrantReadWriteLock();
	
	public Object get(Integer key)
	{
		Object o=null;
		int hash = key.hashCode();
		lock.readLock().lock();
		int bucketId = hash % array.size();
		array.get(bucketId).bucketLock.readLock().lock();
		if(array.get(bucketId).data.containsKey(key))
			o = array.get(bucketId).data.get(key);
		array.get(bucketId).bucketLock.readLock().unlock();
		lock.readLock().unlock();
		return o;
	}
	
	public void put(Integer key, Object value)
	{
		int hash = key.hashCode();
		lock.readLock().lock();
		int bucketId = hash % array.size();
		array.get(bucketId).bucketLock.writeLock().lock();
		array.get(bucketId).data.put(key, value);
		count++;
		array.get(bucketId).bucketLock.writeLock().unlock();
		if(count==MAXSIZE)
			reHash();
		lock.readLock().unlock();
	}
	private void reHash()
	{
		lock.writeLock().lock();
		lock.writeLock().unlock();
	}
	public void erase(Integer key)
	{
		int hash = key.hashCode();
		lock.readLock().lock();
		int bucketId = hash % array.size();
		array.get(bucketId).bucketLock.writeLock().lock();
		array.get(bucketId).data.remove(key);
		array.get(bucketId).bucketLock.writeLock().unlock();
		count--;
		lock.readLock().unlock();
	}
	
	public int sum(int n)
	{
		if(n<=0)
			return 0;
		return n + sum(n-1);
	}
}
