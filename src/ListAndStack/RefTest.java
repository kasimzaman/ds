package ListAndStack;

public class RefTest
{
	public void test1(){
		Ref1 a = new  Ref1();
		a.age=100;
//		Ref1 temp = a;
//		Ref1 b = temp;
//		b.age = 2;
//		System.out.println("a :"+a.age);
//		System.out.println("b :"+b.age);
		
		Ref1 aa=refchange(a);
		System.out.println("aa :"+aa.age);
	}
	
	public Ref1 refchange(Ref1 ref)
	{
		ref=new Ref1();
		ref.age=88;
		
		return ref;
	}
	
}
class Ref1
{
	public int age=0;
	public Ref1()
	{
		
	}
}
