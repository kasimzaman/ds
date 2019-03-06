package strings;

import java.util.Arrays;

public class HasUniqueChars
{
	//SOLUTION:1 Time Complexity: O(n log n)
	boolean uniqueCharacters(String str)
    {
        char [] chArray = str.toCharArray();
 
        // Using sorting
        Arrays.sort(chArray);
 
        for(int i=0; i<chArray.length-1; i++)
        {
            // if the adjacent elements are not
            // equal, move to next element
            if (chArray[i] != chArray[i+1])
                continue;
 
            // if at any time, 2 adjacent elements
            // become equal, return false
            else
                return false;
        }
        return true;
    }
	
	
	/*
	  Time Complexity: O(n) with Extra Data
	  Use of Extra Data Structure: This approach assumes ASCII char set(8 bits).
	  The idea is to maintain a boolean array for the characters. The 256 indices represent 256 characters. 
	  All the array elements are initially set to false. As we iterate over the string, set true at the index
	  equal to the int value of the character. If at any time, we encounter that the array value is already true,
	  it means the character with that int value is repeated
	 */
	
	final static int MAX_CHAR = 256; 
    boolean uniqueCharacters2(String str)
    {
        // If length is greater than 256,
        // some characters must have been repeated
        if (str.length() > MAX_CHAR)
            return false;
 
        boolean[] chars = new boolean[MAX_CHAR];
        Arrays.fill(chars, false);
 
        for (int i=0; i<str.length(); i++)
        {
            int index = (int) str.charAt(i);
 
            /* If the value is already true, string
               has duplicate characters, return false */
            if (chars[index] == true)
                return false;
 
            chars[index] = true;
        }
 
        /* No duplicates encountered, return true */
        return true;
    }
    
    
    /*
	   Time Complexity: O(n) no extra space
	   The approach is valid for strings having alphabet as a-z.
	   Instead of maintaining a boolean array, we maintain an integer value called checker(32 bits).
	   As we iterate over the string, we find the int value of the character with respect to ‘a’.
	   Then the bit at that int value is set to 1 with the statement 1<<bitAtIndex .
	   Now, if this bit is already set in the checker, the bit AND operation would make checker > 0.
	   Return false in this case.
	   Else Update checker to make the bit 1 at that index with the statement checker = checker | (1<<bitAtIndex);  
	*/
    
    boolean uniqueCharacters3(String str)
    {
        // Assuming string can have characters a-z
        
    	// This has 32 bits set to 0
        int checker = 0;
 
        for (int i=0; i<str.length(); i++)
        {
            int bitAtIndex = str.charAt(i)-'a';
 
            // if that bit is already set in checker,
            // return false
            if ((checker & (1<<bitAtIndex)) > 0)
                return false;
 
            // otherwise update and continue by
            // setting that bit in the checker
            checker = checker | (1<<bitAtIndex); //this set bit to 1 at position bitAtIndex
        }
 
        // no duplicates encountered, return true
        return true;
    }
	
	

}
