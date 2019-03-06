package suffixArray;

import java.util.Arrays;
import java.util.Comparator;
// 37346.12   
//searches 1st match found
public class SearchPatternInText
{
	/*same as longest repeated ss. But here once you have the sorted array. Just do a binary search for the pattern and return.
	If required to return the original index of the pattern then use a structure 
	class Suffix
	{
		String sufix;
		int index;
	}
	And during the process of building the suffix array , keep storing the indexes in this structure.
	
	*/
	
	public static void main(String arg[])
	{
		String[] str={"ali", "baa", "cqwa", "dedaa","eawed","fff"};
		String key = "eawed";
		System.out.println(searchString(str,key));
	}
		 
	public static int searchString(String[] names, String key) 
	{
	    int low = 0;
	    int high  = names.length;
	 
	    while (low < high)
	    {
	        int mid = (low + high) / 2;
	        if (key.compareTo(names[mid]) < 0) {
	            high = mid;
	        } else if (key.compareTo(names[mid]) > 0) {
	            low = mid + 1;
	        } else {
	            return mid;
	        }
	    }
	    return -(low + 1);
	}
	 
	
}
