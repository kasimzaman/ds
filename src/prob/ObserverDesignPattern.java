package prob;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class ObserverDesignPattern
{
	interface Subject
	{	
		public void register(Observer o);
		public void unregister(Observer o);
		public void notifyObserver();
	}

	class Polling implements Subject
	{	
		private ArrayList<Observer> observers;
		private double price;
		
		public Polling()
		{
			observers = new ArrayList<Observer>();
		}
		
		public void register(Observer newObserver) 
		{
			observers.add(newObserver);
		}

		public void unregister(Observer deleteObserver) 
		{
			int observerIndex = observers.indexOf(deleteObserver);
			observers.remove(observerIndex);
		}

		public void notifyObserver() 
		{
			for(Observer observer : observers)
			{	
				observer.update(price);
			}
		}
		
		public void setPrice(double newPrice)
		{	
			this.price = newPrice;
			notifyObserver();
		}
	}
		

	interface Observer
	{	
		public void update(double price);
	}
	class DocumentObserver implements Observer
	{	
		private double price;
		private Subject polling;
		
		public DocumentObserver(Subject polling)
		{
			this.polling = polling;
			polling.register(this);
		}
		
		public void update(double price)
		{	
			this.price = price;
		}
	}
	 
	 
	 public static void main(String[] args)
	 {
//		 Polling pollingGrabber = new Polling();
//			
//		 DocumentObserver observer1 = new DocumentObserver(pollingGrabber);
//		 pollingGrabber.setPrice(197.00);
//			
//		 DocumentObserver observer2 = new DocumentObserver(pollingGrabber);
//		 pollingGrabber.setPrice(677.60);
//			
//		new Thread(new GetTheDocument(pollingGrabber,677.00)).start();
//			
	}
	 
	 class GetTheDocument implements Runnable
	 {
		 private double price;
		 private Subject pollingGrabber;
		 public GetTheDocument(Subject pollingGrabber, double newPrice)
		 {
			 this.pollingGrabber = pollingGrabber;
			 price = newPrice;
		 }	
		 public void run()
		 {		
			 for(int i = 1; i <= 20; i++)
			 {	
				 try
				 {
					 Thread.sleep(2000);
				 }
				 catch(InterruptedException e){}
					
				 double randNum = (Math.random() * (.06)) - .03;
				 DecimalFormat df = new DecimalFormat("#.##");
				 price = Double.valueOf(df.format((price + randNum)));
				 	((Polling) pollingGrabber).setPrice(price);
			}
		}	
	 }
	 
}
