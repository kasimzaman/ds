package prob;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestFriendship
{
	public static void main(String[] args) throws Exception
	{
		
		TestFriendship tf = new TestFriendship();
		tf.addFriendsFromTextFile();
	}

	@Test
	public void testGetDirectFriends() 
	{
		Friendship friendship = new Friendship();
		friendship.makeFriend("Aaron", "Bella");
		friendship.makeFriend("Bella", "Cindy");
		friendship.makeFriend("Bella", "David");
		friendship.makeFriend("David", "Elizabeth");
		friendship.makeFriend("Cindy", "Frank");
		
		List<String> directFriends = friendship.getDirectFriends("David");
		
		ArrayList<String> expectedFriends = new ArrayList<String>();
		expectedFriends.add("Bella");
		expectedFriends.add("Elizabeth");
		
		assertEquals(expectedFriends, directFriends);
	}
	
	@Test
	public void getIndirectFriends() 
	{
		Friendship friendship = new Friendship();
		friendship.makeFriend("Aaron", "Bella");
		friendship.makeFriend("Bella", "Cindy");
		friendship.makeFriend("Bella", "David");
		friendship.makeFriend("David", "Elizabeth");
		friendship.makeFriend("Cindy", "Frank");
		
		List<String> directFriends = friendship.getIndirectFriends("David");
		
		ArrayList<String> expectedFriends = new ArrayList<String>();
		expectedFriends.add("Aaron");
		expectedFriends.add("Elizabeth");
		expectedFriends.add("Cindy");
		expectedFriends.add("Bella");
		
		assertEquals(expectedFriends, directFriends);
	}
	
	@Test
	public void removeFriends() 
	{
		Friendship friendship = new Friendship();
		friendship.makeFriend("Aaron", "Bella");
		friendship.makeFriend("Aaron", "Elizabeth");
		friendship.makeFriend("Bella", "Cindy");
		friendship.makeFriend("Bella", "Aaron");
		
		//remove friends here
		friendship.unmakeFriend("Aaron", "Bella");
		
		ArrayList<String> expectedAaronDirectFriends = new ArrayList<String>();
		expectedAaronDirectFriends.add("Elizabeth");
		assertEquals(expectedAaronDirectFriends, friendship.getDirectFriends("Aaron"));
		
		ArrayList<String> expectedBellaDirectFriends = new ArrayList<String>();
		expectedBellaDirectFriends.add("Cindy");
		assertEquals(expectedBellaDirectFriends, friendship.getDirectFriends("Bella"));
	}
	
	@Test
	public void addFriendsFromTextFile() 
	{
		Friendship friendship = new Friendship();
		ArrayList<String> names = new ArrayList<String>();
		
		if ((names = friendship.ReadFriendsFromFile("//Users//ali//Desktop//Friends.txt")) == null)
		try
		{
			throw new Exception("Friends file is empty!");
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		for (int i = 0; i < names.size(); i += 2) 
		{
		    friendship.makeFriend(names.get(i), names.get(i + 1));
		}
		
		List<String> directFriends = friendship.getDirectFriends("David");
		
		ArrayList<String> expectedFriends = new ArrayList<String>();
		expectedFriends.add("Bella");
		expectedFriends.add("Elizabeth");
		
		assertEquals(expectedFriends, directFriends);
	}
	
	
	

}