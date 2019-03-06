package prob;

public class Factory
{
	public static void main(String[] args)
	{
		Factory shapeFactory = new Factory();
	    Shape shape1 = shapeFactory.getShape("CIRCLE");
	    shape1.draw();
	    Shape shape2 = shapeFactory.getShape("RECTANGLE");
	    shape2.draw();
	}
	
	public Shape getShape(String shapeType)
	{
		if(shapeType == null)
		{
			return null;
		}		
	    if(shapeType.equalsIgnoreCase("RECTANGLE"))
	    {
	    	return new Rectangle();
	    }
	    else if(shapeType.equalsIgnoreCase("SQUARE"))
	    {
	         return new Square();
	    }
	    return null;
	}
	
	
	interface Shape
	{
		void draw();
	}
	class Rectangle implements Shape
	{
		@Override
		public void draw()
		{
			System.out.println("Inside Rectangle::draw() method.");
		}
	}
	class Square implements Shape
	{
		@Override
		public void draw()
		{
			System.out.println("Inside Square::draw() method.");
		}
	}
	
	
		
		
	
}
