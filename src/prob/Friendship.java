package prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Friendship
{

    private final Map<String, HashSet<String>> friendship;
    private final CreateFriends createFriends;
    // constructor
    public Friendship()
    {
        this.createFriends = new CreateFriends();
        friendship = new HashMap<String, HashSet<String>>();
    }
    public void test()
    {
    	String[] str={"abc","def","ghi"};
    	LinkedList<String>list=new LinkedList<>(Arrays.asList(str));
    	Object x =new Object();
    	System.identityHashCode(x);
    }

    public void makeFriend(String name1, String name2) 
    {
        CheckIfNameAreNotEmptyOrNull(name1, name2);
        AddFriends(name1, name2);
        AddFriends(name2, name1);
    }

    public void unmakeFriend(String name1, String name2) 
    {
        CheckIfNameAreNotEmptyOrNull(name1, name2);
        removeFriend(name1, name2);
        removeFriend(name2, name1);
    }

    public List<String> getDirectFriends(String name) 
    {
        CheckIfNameIsEmptyOrNull(name);
        if (friendship.containsKey(name))
        {
            ArrayList<String> friends = new ArrayList<String>(friendship.get(name));
            //Sorting logic below
            return friends;// sortFriendsList(friends);
        }
        System.out.println(name + " Does not have any direct Friends!");
        return null;
    }

    public ArrayList<String> ReadFriendsFromFile(String filename)
    {
        return createFriends.ReadFriendsTextFile(filename);
    }

    public List<String> getIndirectFriends(String name)
    {
        CheckIfNameIsEmptyOrNull(name);
        HashSet<String> indirectFriends = new HashSet<String>(getDirectFriends(name));
        HashSet<String> individualDirectFriends;

        if (!indirectFriends.isEmpty()) 
        {
            HashSet<String> loopInDirectFriends = (HashSet<String>) indirectFriends.clone();

            for (String friend : loopInDirectFriends) 
            {
                individualDirectFriends = new HashSet<String>(getDirectFriends(friend));
                indirectFriends.addAll(individualDirectFriends);
            }
            //remove the name itself from the list of indirect friends
            indirectFriends.remove(name);
        }
        return new ArrayList<String>(indirectFriends);
    }

    private void removeFriend(String name1, String name2)
    {
        CheckIfNameAreNotEmptyOrNull(name1, name2);

        if (friendship.containsKey(name1)) 
        {
            HashSet<String> friendRemovedHashList = new HashSet<String>(friendship.get(name1));
            friendRemovedHashList.remove(name2);
            friendship.put(name1, friendRemovedHashList);
        } 
        else
        {
            System.out.println(name1 + " Is not a friend of " + name2);
        }
    }

    private void CheckIfNameIsEmptyOrNull(String name)
    {
        if (name.isEmpty() || name == null)
        {
            throw new IllegalArgumentException("Name cannot be Null or Empty");
        }
    }

    private void CheckIfNameAreNotEmptyOrNull(String name1, String name2)
    {
        if (name1 == null || name1.isEmpty() || name2 == null || name2.isEmpty()) 
        {
            throw new IllegalArgumentException("Names Cannot Be Null");
        }
    }

    private void AddFriends(String name1, String name2)
    {
        HashSet<String> set = new HashSet<String>();
        if (!friendship.containsKey(name1))
        {
            set.add(name2);
            friendship.put(name1, set);
        }
        else
        {
            set = friendship.get(name1);
            set.add(name2);
            friendship.put(name1, set);
        }
    }
    

//    private ArrayList<String> sortFriendsList(ArrayList<String> friends) 
//    {
//        Collections.sort(friends, new Comparator<String>() {
//            @Override
//            public int compare(String s1, String s2) {
//                return s1.compareToIgnoreCase(s2);
//            }
//        });
//        return friends;
//    }
}




class CreateFriends 
{
    public ArrayList<String> ReadFriendsTextFile(String filename) 
    {
    	 ArrayList<String> names = new ArrayList<String>();
    	// Location of file to read
        File file = new File(filename);
 
        try
        {
            Scanner scanner = new Scanner(file);
 
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                names= SplitLineIntoNames(line,names);
                System.out.println(line);
            }
            scanner.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    	
        return names;
    	
//       
//        FileReader file = null;
//        BufferedReader bReader = null;
//        String line;
//        String allText = "";
//        try
//        {
//            file = new FileReader(filename);
//            bReader = new BufferedReader(file);
//        }
//        catch (FileNotFoundException ex)
//        {
//            ex.printStackTrace();
//        }
//        try
//        {
//            int i =0;
//            while((line = bReader.readLine()) != null)
//            {
//                names= SplitLineIntoNames(line,names);
//            }
//        }
//        catch (IOException e) 
//        {
//            e.printStackTrace();
//        }
//        finally
//        {
//            if(file != null)
//            {
//                try {
//                    file.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        //System.out.println(allText);
       
    }

    private ArrayList<String> SplitLineIntoNames(String line, ArrayList<String> names)
    {
        String [] name = line.split(";");
        if(name.length <3 && !name[0].isEmpty() && !name[1].isEmpty())
        {
            names.add(name[0]);
            names.add(name[1]);
        }
        return names;
    }
}