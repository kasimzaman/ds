package prob;

/*
  	Lazy initialization is possible.
	It is also thread safe.
	Performance reduced because of synchronized keyword is overcome
 */

public class Singleton
{
	private static Singleton instance;
	private Singleton() 
	{
	    // private constructor
	}
	public static Singleton getInstance()
	{
		if (instance == null) 
	    {
			//if 2 threads A,B get here
			
			synchronized (Singleton.class)//thread B waits. When A is done , you need instance==null check 
			{
				//thread A gets in
				if(instance==null)
				{
					// if instance is null, initialize
					instance = new Singleton();
				}
			}
	    }
	    return instance;
	}
	
}
