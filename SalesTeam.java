import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Alfred Langer
 * Student ID: 500813614
 * This is the SalesTeam Class
 */
public class SalesTeam 
{
	//This is the only instance variable of this class. It is a LinkedList that stores the names of all the people on the team.
	private LinkedList<String> salesTeamList;
	
	//This is the general constructor for the SalesTeam object
	public SalesTeam ()
	{
		salesTeamList = new LinkedList<String>();
	}
	
	//This method adds a sales team member 
	public void addMember(String x)
	{
		salesTeamList.add(x);
	}
	//Returns the name of a random sales team member
	public String chooseRandomSalesPerson()
	{
		int index = (int)(Math.random() * salesTeamList.size()); 
		// Normally for a RNG you have to do (Math.random times (range + 1) + min) where range is (max - min) but salesTeamList.size equals (range + 1) and min = 0
		return (salesTeamList.get(index));
	}
	//Returns a string containing all the names of the sales team
	public String displayAllNames()
	{
		ListIterator<String> salesIterator = salesTeamList.listIterator();
		String displayString = "";
		while(salesIterator.hasNext())
		{
			displayString += (salesIterator.next()) + " ";
		}
		return ("Team: " + displayString);
	}
	
	//Returns the size of the sales team
	public int teamSize()
	{
		return(salesTeamList.size());
	}
	
	//Returns a specific sales team member based on the index passed in
	public String getSalesPerson(int index)
	{
		if(index >= 0 && index < salesTeamList.size())
			return(salesTeamList.get(index));
		else
			return("That is not a valid selection");
	}
	
	//Returns salesTeamList
	public LinkedList<String> getSaleTeamList()
	{
		return salesTeamList;
	}
}
