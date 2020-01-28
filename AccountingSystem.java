import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

/**
 * Alfred Langer
 * Student ID: 500813614
 * This is the AccountingSystem class
 */
public class AccountingSystem 
{
	//This is the only instance variable of the class
	//It is an ArrayList that keeps houses Transaction Objects
	ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
	
	/**
	 * This method creates a transaction and adds it to the transactionList by using the information passed in and a Randomly generated ID
	 * @param date
	 * @param car
	 * @param salesPerson
	 * @param type
	 * @param salePrice
	 * @return The information about the transaction as a String
	 */
	public String add(Calendar date, Car car, String salesPerson, String type, double salePrice)
	{
		int transactionID = (int)(Math.random() * 99 + 1); //This creates a random integer from 1-99
		Transaction latestTransaction = new Transaction((GregorianCalendar) date,transactionID,car,salesPerson,type,salePrice); //Gregorian Calendar date vs Calendar date how should this be handled?
		transactionList.add(latestTransaction);
		return(latestTransaction.display());
	}
	
	
	
	public Transaction getTransaction(int id)
	{
		for (Transaction t : transactionList)
		{
			if (t.getTransactionID() == id)
				return t;
		}
		System.out.println("There is no transaction associated with that ID");
		return null;
	}
	
	//This method is used to find the SalesPerson who sold the most cars in the LinkedList passed in
	public String topSalesPerson(LinkedList<String> salesTeamList)
	{
		//This is to initialize the variables topNumberOfSales and topSalesPerson
		int topNumberOfSales = 0;
		String topSalesPerson = salesTeamList.get(0);
		
		//This for loop goes through all the names of the LinkedList passed in
		for(String name: salesTeamList)
		{
			
			int salesOfName = 0;//This variable keeps track of the total number of sales of the current SalesPerson were looking at
			
			//This for loop goes through all the transactions in this ArrayList of the AccountingSystem Object
			//If the transaction salesPerson matches the current SalesPerson were looking at and it is a BUY transaction salesOfName gets incremented
			for(Transaction t: transactionList)
			{
				if (t.getSalesPersonsName() == name && t.getTransactionType() == "BUY")
					salesOfName++;
			}
			
			//This will reassign the topNumberOfSales and topSalesPerson if the condition is met
			if (salesOfName > topNumberOfSales)
			{
				topNumberOfSales = salesOfName;
				topSalesPerson = name;
			}
			//If the salesOfName is equal to topNumberOfSales then we concatenate the current SalesPerson name instead of replacing it
			else if(salesOfName == topNumberOfSales)
			{
				topSalesPerson = topSalesPerson + " & " + name;
			}
		}
		String returnString = topSalesPerson + " # of sold cars: " + topNumberOfSales;
		return(returnString);
	}
	
	//This method is used to print the transaction info of all the transactions in the transactionList
	public void printAllTransactions()
	{
		for(Transaction t: transactionList)
		{
			System.out.println(t.display());
		}
	}
	
	//This method is used to print the transaction info of all the transaction of that passed in  month in transactionList 
	public void printTransactionsOfMonth(int iMonth)
	{
		if(iMonth < 0 || iMonth >= 12)
		{
			System.out.println("You must provide a month between 0 and 11. Jan is 0, Feb is 1...");
			return;
		}
		for(Transaction t: transactionList)
		{
			Calendar transactionDate = t.getTransactionDate();
			if(transactionDate.get(Calendar.MONTH) == iMonth)
				System.out.println(t.display());
		}
	}	
	
	//This method determines certain statistics about all the transactions in the ArrayList of the current AccountingSystem Object
	public void transactionStats()
	{
		//These variables are just being initialized here
		double totalSales = 0.0;
		int totalCarsSold = 0;
		int totalCarsReturned = 0;
		
		//This goes through all the transactions of transactionList.
		//If the transaction is a BUY, totalCarsSold is incremented and totalSales get increased by it's price
		//If the transaction is a RETURN, totalCarsReturned is incremented and totalSales gets decreased by it's price
		for (Transaction t : transactionList)
		{
			if (t.getTransactionType().equals("BUY"))
			{
				totalCarsSold++;
				totalSales += t.getSalePrice();
			}
			else if(t.getTransactionType().equals("RETURN"))
			{
				totalCarsReturned++;
				totalSales = totalSales - t.getSalePrice();
			}
		}
		
		//This variable is just the average of totalsSales over 12 months
		int averageSalesPerMonth = (int) (totalSales / 12);
		
		//This is just initializing the variables
		int highestSalesMonthInt = 0;
		String highestSalesMonth = "";
		int greatestNumberOfSales = 0;
		
		//This goes through all 12 months of the year
		for(int i = 0; i < 12;i++)
		{
			//This initializes this variable
			int currentSalesThisMonth = 0;
			
			//This goes through all the transactions in transactionList
			//If the transaction occurred in the same month as the month we're currently looking at, increment currentSalesThisMonth
			for(Transaction t : transactionList)
			{
				Calendar transactionDate = t.getTransactionDate();
				if(transactionDate.get(Calendar.MONTH) == i && t.getTransactionType().contentEquals("BUY"))
				{
					currentSalesThisMonth ++;
				}
			}
			
			//If the currentSalesThisMonth is greater than greatestNumberOfSales, than we reassign greatestNumberOfSales and highestSalesMonthInt
			if (currentSalesThisMonth > greatestNumberOfSales)
			{
				greatestNumberOfSales = currentSalesThisMonth;
				highestSalesMonthInt = i;
				switch(highestSalesMonthInt) 
				{
					case 0:
						highestSalesMonth = "Jan";
						break;
					case 1:
						highestSalesMonth = "Feb";
						break;
					case 2:
						highestSalesMonth = "Mar";
						break;
					case 3:
						highestSalesMonth = "Apr";
						break;
					case 4:
						highestSalesMonth = "May";
						break;
					case 5:
						highestSalesMonth = "Jun";
						break;
					case 6:
						highestSalesMonth = "Jul";
						break;
					case 7:
						highestSalesMonth = "Aug";
						break;
					case 8:
						highestSalesMonth = "Sep";
						break;
					case 9:
						highestSalesMonth = "Oct";
						break;
					case 10:
						highestSalesMonth = "Nov";
						break;
					case 11:
						highestSalesMonth = "Dec";
						break;
				}
			}
			else if(currentSalesThisMonth == greatestNumberOfSales)
			{
				if(greatestNumberOfSales == 0)
					highestSalesMonth = "No sales this year, so no best month";
				
				else
				{
					highestSalesMonthInt = i;
					switch(highestSalesMonthInt) 
					{
						case 0:
							highestSalesMonth += " Jan ";
							break;
						case 1:
							highestSalesMonth += " Feb ";
							break;
						case 2:
							highestSalesMonth += " Mar ";
							break;
						case 3:
							highestSalesMonth += " Apr ";
							break;
						case 4:
							highestSalesMonth += " May ";
							break;
						case 5:
							highestSalesMonth += " Jun ";
							break;
						case 6:
							highestSalesMonth += " Jul ";
							break;
						case 7:
							highestSalesMonth += " Aug ";
							break;
						case 8:
							highestSalesMonth += " Sep ";
							break;
						case 9:
							highestSalesMonth += " Oct ";
							break;
						case 10:
							highestSalesMonth += " Nov ";
							break;
						case 11:
							highestSalesMonth += " Dec ";
							break;
					}
				}
			}
		}
		
		
		//This just prints all the info in a formatted String
		System.out.println("Total Sales: " + totalSales + " Total Sold: " + totalCarsSold + " Avg Sales: " + averageSalesPerMonth + " Total Returned: " + totalCarsReturned + " Best Month(s): " + highestSalesMonth + " Cars sold - " + greatestNumberOfSales);
	}
}
