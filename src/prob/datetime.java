package prob;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class datetime {

	public static void main(String[] args) {
		
		dt3();

	}
	
	public static void dt1()
	{
		String input = "Jan 3 2003";
		try
		{
		    DateTimeFormatter formatter =	DateTimeFormatter.ofPattern("MMM d yyyy");
		    LocalDate date = LocalDate.parse(input, formatter);
		    System.out.printf("%s%n", date);
		}
		catch (DateTimeParseException exc) {
		    System.out.printf("%s is not parsable!%n", input);
		    throw exc;      
		}
	}
	
	public static void dt2()
	{
		String in = "19590709";
		LocalDate date = LocalDate.parse(in, DateTimeFormatter.BASIC_ISO_DATE);
	}
	
	public static void dt3()
	{
		String input = "Jul 20 2013  07:30 PM";

		try 
		{
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy  hh:mm a");
		    LocalDate date = LocalDate.parse(input, formatter);
		    System.out.printf("%s%n", date);
		}
		catch (DateTimeParseException exc) {
		    System.out.printf("%s is not parsable!%n", input);
		    throw exc;      
		}
	}

}
