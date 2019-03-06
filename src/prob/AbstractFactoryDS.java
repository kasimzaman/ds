package prob;



/*
 	Abstract Factory patterns work around a super-factory which creates other factories. This factory is also called as factory of factories.
 */

public class AbstractFactoryDS
{
	public static void main(String[] args)
	{
//		AbstractFactory shapeFactory = FactoryProducer.getFactory("SHAPE");
//	    Shape shape1 = shapeFactory.getShape("CIRCLE");
//	    shape1.draw();
//	     
//	    AbstractFactory colorFactory = FactoryProducer.getFactory("COLOR");
//	    Color color1 = colorFactory.getColor("RED");
//	    color1.fill();
	}
	
	
	class FactoryProducer
	{
		public AbstractFactory getFactory(String choice)
		{
			if(choice.equalsIgnoreCase("SHAPE"))
		         return new ShapeFactory();
		         
			else if(choice.equalsIgnoreCase("COLOR"))
		         return new ColorFactory();
		      return null;
		}
	}
	
	
	
	//abstract factory
	abstract class AbstractFactory
	{
		abstract Color getColor(String color);
		abstract Shape getShape(String shape);
	}
	
	class ShapeFactory extends AbstractFactory
	{
		@Override
		public Shape getShape(String shapeType)
		{
			if(shapeType == null)
		         return null;
		     if(shapeType.equalsIgnoreCase("RECTANGLE"))
		         return new Rectangle();
		         
		      else if(shapeType.equalsIgnoreCase("SQUARE"))
		         return new Square();
		      
		      return null;
		}
		@Override
		Color getColor(String color)
		{	
			return null;
		}
	}
	
	class ColorFactory extends AbstractFactory
	{
		@Override
		public Shape getShape(String shapeType)
		{
			return null;
		}
		@Override
		Color getColor(String color)
		{
			if(color == null)
		         return null;
			if(color.equalsIgnoreCase("RED"))
		         return new Red();
		         
			else if(color.equalsIgnoreCase("BLUE"))
		         return new Blue();
		      return null;
		}
	}
	
	//shape
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
	
	//color
	interface Color
	{
		void fill();
	}
	class Red implements Color
	{
		@Override
		public void fill()
		{
			System.out.println("Inside red::fill() method.");
		}
	}
	class Blue implements Color
	{
		@Override
		public void fill()
		{
			System.out.println("Inside blue:fill method.");
		}
	}
}


