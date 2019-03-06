package prob;

import java.util.ArrayList;
import java.util.Scanner;

public class FriendsTest 
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        
        System.out.println("1.Add a friend");
        System.out.println("2.Remove a friend"); 
        System.out.println("3.Display all friends");
        System.out.println("4.Exit Program");
        
        int selection = input.nextInt();
        
        FriendsList bornList = new FriendsList();
        
        int count = 0;
        
        while (selection != 4)
        {
            int number = 0;
          
            if (selection == 1)
            {
                System.out.printf("Whats the first Name: ");
                String first = input.next();
                System.out.println("Whats there Last Name: ");
                String last = input.next();
                System.out.println("Whats there Age");
                String age = input.next(); 
                bornList.setList(first, last, age);  //sets the values for friend
                bornList.addFriend(); // adds the friend to the array list
                count++;
            }
            
            if (selection == 2)
            {
                System.out.printf("Whats the first Name: ");
                String first = input.next();
                System.out.println("Whats there Last Name: ");
                String last = input.next();
                System.out.println("Whats there Age");
                String age = input.next(); 
        
                bornList.setList(first, last, age);  // sets up the friend to remove
                bornList.removeFriend(); // removes the friend from the array list
            }// end of if
            
            if (selection == 3)
            {
              
                 //System.out.println(bornList.toString());

                for(int i= 0; i < 1;i++)
                {
                System.out.println(bornList.list);
                }
            }
          
                System.out.println("1.Add a friend (First Name, Last Name, Age)");
                System.out.println("2.Remove a friend"); 
                System.out.println("3.Display all friends");
                System.out.println("4.Exit Program");
                selection = input.nextInt(); 
            } // end of else statement
        } // end of while loop     
} 

class FriendsList
{
    // initialize 
    public String firstName;
    public String lastName;
    public String age;
    public ArrayList<String> list = new ArrayList<String>();
    
    
    //parameterized constructor
    public void setList(String first, String last, String age)
    {
        setFirstName(first);
        setLastName(last);
        setAge(age);
    }//end constructor for friend
    
/****************Starting of get methods************************/
    public String getFirstName()
    {
     return firstName;
    }//end getFirstName()

    public String getLastName()
    {
     return lastName;
    }//end getlastName()

    public String getAge()
    {
      return age;
    }//end of getAge()
    /****************End of get methods************************/




    /****************Starting of set methods************************/

    public void setFirstName( String FirstName )
    {
     firstName = FirstName;
    }//end setFirstName() 

    public void setLastName( String LastName )
    {
     lastName = LastName;
    }//end setLastName()

    public void setAge(String Age)
    {
      age = Age;
    }// end of setAge()

    /****************End of set methods************************/
    
    /****************Add to Array Method************************/
    public void addFriend()
    {   
        list.add(firstName);
        list.add(lastName);
        list.add(age);
    }
    
        /****************Remove a friend method************************/
    public void removeFriend()
    {
        list.remove(firstName);
        list.remove(lastName);
        list.remove(age);
    }
    
    //utility methods

 @Override
 public String toString()
 {
   return String.format( "Name: %s\nStudent Last Name: %s\nStudent Age: %s\nStudent", firstName, lastName, age);
 }//end toString()
    
}// end of main Class
