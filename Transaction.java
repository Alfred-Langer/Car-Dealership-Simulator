import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 * Alfred Langer
 * Student ID: 500813614
 * This is the Transaction Class
 */
public class Transaction 
{
	//These are all the instance variables of Transaction Class
	private Calendar transactionDate;
	private int transactionID;
	private Car lastboughtCar;
	private String salesPersonName;
	private String transactionType;
	private double salePrice;
	
	//These are two constants used for the transactionType
	public static final String BUY = "BUY";
	public static final String RETURN = "RETURN";
	
	
	/**
	 * This is our primary Transaction Constructor
	 * @param itransactionDate
	 * @param itransactionID
	 * @param ilastboughtCar
	 * @param isalesPersonName
	 * @param itransactionType
	 * @param isalePrice
	 */
	public Transaction(GregorianCalendar itransactionDate, int itransactionID, Car ilastboughtCar, String isalesPersonName, String itransactionType, double isalePrice)
	{
		transactionDate = itransactionDate;
		transactionID = itransactionID;
		lastboughtCar = ilastboughtCar;
		salesPersonName = isalesPersonName;
		transactionType = itransactionType;
		salePrice = isalePrice;
	}
	
	/**
	 * This method is used to display the information of the current Transaction Object
	 * @return a string that contains information about the transaction
	 */
	public String display()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM dd, yyyy");
		String date = sdf.format(transactionDate.getTime());
		return("ID: " + transactionID + " " + date + " " +  transactionType + " SalesPerson: " + salesPersonName + " Car: " + lastboughtCar.display());
	}
	
	//The following methods are all get Methods for the instance variables of class "Transaction"
	
	public int getTransactionID()
	{
		return transactionID;
	}
	
	public Calendar getTransactionDate()
	{
		return transactionDate;
	}
	
	public Car getLastBoughtCar()
	{
		return lastboughtCar;
	}
	
	public String getSalesPersonsName()
	{
		return salesPersonName;
	}
	
	public String getTransactionType()
	{
		return transactionType;
	}
	
	public double getSalePrice()
	{
		return salePrice;
	}
}
