import java.util.*;
/**
 * Alfred Langer
 * Student ID: 500813614
 * This is the CarDealership class
 */
public class CarDealership 
{
	//This is a reference to an ArrayList that will hold multiple Car objects
	ArrayList<Car> cars;
	
	//This is a reference to a SalesTeam object
	SalesTeam dealershipSalesTeam;
	
	//This is a reference to a AccountingSystem object
	AccountingSystem dealershipASystem;
	
	//These variables set the price range for our FilterByPrice method
	private double minimumPrice = 0.0;
	private double maximumPrice = 0.0;
	
	//These variables act as flags for our filter methods
	private boolean electricFilter = false;
	private boolean awdFilter = false;
	private boolean priceFilter = false;
	
	/**
	 * This class implements the Comparator interface and it is used for the method sortBySafetyRating
	 */
	private class SafetyComparator implements Comparator<Car>
	{
		public int compare(Car arg0, Car arg1) 
		{
			if (arg0.getsafetyRating() > arg1.getsafetyRating())
			{
				return -1;
			}
			else if(arg0.getsafetyRating() < arg1.getsafetyRating())
			{
				return 1;
			}
			else
			{	
				return 0;
			}
		}	
	}
	
	/**
	 * This class implements the Comparator interface and it is used for the method sortBySafetyRating
	 */
	private class RangeComparator implements Comparator<Car>
	{
		public int compare(Car arg0, Car arg1) 
		{
			if (arg0.getmaxRange() > arg1.getmaxRange())
			{
				return -1;
			}
			else if(arg0.getmaxRange() < arg1.getmaxRange())
			{
				return 1;
			}
			else
			{	
				return 0;
			}
		}	
	}
	
	/**
	 * This is our primary constructor of the class
	 * It assigns our reference variable to an empty Car ArrayList
	 */
	public CarDealership()
	{
		cars = new ArrayList<Car>();
		dealershipASystem = new AccountingSystem();
		dealershipSalesTeam = new SalesTeam();
		dealershipSalesTeam.addMember("Tolaz");
		dealershipSalesTeam.addMember("Ryan");
		dealershipSalesTeam.addMember("Gabriel");
		dealershipSalesTeam.addMember("Adande");
		dealershipSalesTeam.addMember("Phil");
		dealershipSalesTeam.addMember("Channing");
	}
	
	/**
	 * This method takes all the elements of a "Car" ArrayList and adds them "cars" which is the ArrayList variable of the current CarDealership object
	 * @param newCars This is the ArrayList you are passing in
	 */
	public void addCars(ArrayList<Car> newCars)
	{
		for(int i = 0; i < newCars.size(); i++)
		{
			this.cars.add(newCars.get(i));
		}
	}
	
	/**
	 * This method removes a car element from the array list of the current "CarDealership" object and then returns that object to the user
	 * @param index This is how the user selects which car to buy
	 * @return the Car object that was removed from the ArrayList
	 * @return null if the user's selection was invalid 
	 */
	public String buyCar(int iVIN) throws arrayListZeroException,InvalidVINException
	{
		//Throws an exception if cars is empty
		if(cars.size() == 0)
		{
			throw new arrayListZeroException();
		}	
		
		//This goes through all the elements of cars and tries to find a Car object that has a matching VIN that was passed in
		//If so, the element is removed from cars and a Transaction object is created and added to the AccountingSystem
		//If not, then an exception is thrown
		for(int i = 0; i < cars.size(); i++)
		{
			Car possibleCar = cars.get(i);
			if (possibleCar.getVIN() == iVIN)
			{
				//This part of the code stores you car in boughtCar and removes it from the ArrayList<Cars> of AlfredsDealership
				Car boughtCar = possibleCar;
				this.cars.remove(i);
				System.out.println("You just bought a car");
				
				//This part updates the AccountSystem
				String salesPerson = dealershipSalesTeam.chooseRandomSalesPerson();
				int month = (int)(Math.random() * 12);
				int day = randomDay(month);
				Calendar purchaseDate = new GregorianCalendar(2019,month,day);
				String receipt = dealershipASystem.add(purchaseDate, boughtCar, salesPerson, Transaction.BUY, boughtCar.getprice());
				return (receipt); 
			}
		}
		throw new InvalidVINException();
	}
	
	/**
	 * This method adds a Car object into the ArrayList by passing in a transactionID
	 * It also creates a RETURN transaction object and adds it into the AccountingSystem
	 * @param transactionID
	 */
	public void returnCar(int transactionID) 
	{
		//This  line gets transaction associated with the Transaction ID passed in
		//If the transaction doesn't exist the method ends
		Transaction lastPurchase = dealershipASystem.getTransaction(transactionID);
		if (lastPurchase == null)
		{
			return;
		}
		//This line extracts the reference of the Car object from the last transaction.
		Car lastBoughtCar = lastPurchase.getLastBoughtCar();
		
		//These lines are used to check if the transaction that we are looking at it is a bought transaction and not a return transaction
		if(lastPurchase.getTransactionType() == "RETURN")
		{
			System.out.println("You can't return a return transaction silly :D");
			return;
		}
		//These lineS make sure that the transaction we are looking at, has not already been returned
		for (Transaction t: dealershipASystem.transactionList)
		{
			if(t.getLastBoughtCar() == lastBoughtCar && t.getTransactionType() == "RETURN")
			{
				System.out.println("The car you are trying to return, has already been returned :(");
				return;
			}
		}
		
		//These lines extract the date from the last transaction and then increments that date. 
		//possibleIncrement is used to advance the date. If the possibleIncrement would change to the next month then it doesn't increment the date
		Calendar lastPurchaseDate = lastPurchase.getTransactionDate();
		int day = lastPurchaseDate.get(Calendar.DAY_OF_MONTH);
		int possibleIncrement = 28 - day;
		if(possibleIncrement > 0)
			lastPurchaseDate.add(Calendar.DAY_OF_MONTH,possibleIncrement);
		
		//This line now adds a RETURN transaction to the AccountingSystem object of this CarDealership and prints the receipt of the BUY transaction
		dealershipASystem.add(lastPurchaseDate, lastBoughtCar, lastPurchase.getSalesPersonsName(), Transaction.RETURN, lastBoughtCar.getprice());
		System.out.println(lastPurchase.display());
		//This line adds the Car Object of the last BOUGHT transaction back to the cars ArrayList
		this.cars.add(lastBoughtCar);
	
		
	}
	
	//The next two methods are used to turn on the the flags electricFilter and awdFilter.
	public void filterByElectric()
	{
		electricFilter = true;
	}
	
	public void filterByAWD()
	{
		awdFilter = true;
	}
	
	/**
	 * This method turns on the awdFilter and it also sets the minimumPrice and maximumPrice variables
	 * @param minPrice
	 * @param maxPrice
	 */
	public void filterByPrice(double minPrice, double maxPrice)
	{
		priceFilter = true;
		minimumPrice = minPrice;
		maximumPrice = maxPrice;
	}
	
	/**
	 * This method sets all the flags, electricFilter, awdFilter, and priceFilter, to false
	 */
	public void filtersClear()
	{
		priceFilter = false;
		awdFilter = false;
		electricFilter = false;
	}
	
	
	/**
	 * This method prints out the information of each Car object in the ArrayList cars (It does this by calling the displayMethod from class Car or ElectricCar)
	 * Depending on what flags are turned on, certain elements of cars will not be displayed
	 */
	public void displayInventory() 
	{
		if (priceFilter == false && awdFilter == false && electricFilter == false)
		{
			for(int i = 0; i < cars.size(); i++)
			{	
				Car currentCar = cars.get(i);
				System.out.println(currentCar.display());
			}
		}
		
		else if (priceFilter == false && awdFilter == false && electricFilter == true)
		
		{
			for(int i = 0; i < cars.size(); i++)
			{	
				Car currentCar = cars.get(i);
				if (currentCar.getpower() == 0)
				{
					System.out.println(currentCar.display());
				}
			}
		}
		
		else if (priceFilter == false && awdFilter == true && electricFilter == false)
		
		{
			for(int i = 0; i < cars.size(); i++)
			{	
				Car currentCar = cars.get(i);
				if (currentCar.getAWD() == true)
				{
					System.out.println(currentCar.display());
				}
			}
		}
		
		else if (priceFilter == true && awdFilter == false && electricFilter == false)
		{
			for(int i = 0; i < cars.size(); i++)
			{	
				Car currentCar = cars.get(i);
				if (currentCar.getprice() >= minimumPrice && currentCar.getprice() <= maximumPrice)
				{
					System.out.println(currentCar.display());
				}
			}
		}
		
		else if (priceFilter == false && awdFilter == true && electricFilter == true)
		{
			for(int i = 0; i < cars.size(); i++)
			{	
				Car currentCar = cars.get(i);
				if (currentCar.getpower()== 0 && currentCar.getAWD() == true)
				{
					System.out.println(currentCar.display());
				}
			}
		}
		
		else if (priceFilter == true && awdFilter == false && electricFilter == true)
		{
			for(int i = 0; i < cars.size(); i++)
			{	
				Car currentCar = cars.get(i);
				if (currentCar.getprice() >= minimumPrice && currentCar.getprice() <= maximumPrice && currentCar.getpower() == 0)
				{
					System.out.println(currentCar.display());
				}
			}
		}
		
		else if (priceFilter == true && awdFilter == true && electricFilter == false)
		{
			for(int i = 0; i < cars.size(); i++)
			{	
				Car currentCar = cars.get(i);
				if (currentCar.getprice() >= minimumPrice && currentCar.getprice() <= maximumPrice && currentCar.getAWD() == true)
				{
					System.out.println(currentCar.display());
				}
			}
		}
		
		else 
		{
			for(int i = 0; i < cars.size(); i++)
			{	
				Car currentCar = cars.get(i);
				if (currentCar.getprice() >= minimumPrice && currentCar.getprice() <= maximumPrice && currentCar.getpower() == 0 && currentCar.getAWD() == true)
				{
					System.out.println(currentCar.display());
				}
			}
		}
		
	}
	
	
	//This method will sort the ArrayList of Car objects by their price. (Lowest to Highest)
	public void sortByPrice()
	{
		 Collections.sort(cars);
	}
	
	//This method will sort the ArrayList of Car objects by their safetyRating.
	//By passing in an object of the SafetyComparator class, the sort method will use the compare method in the SafetyComparator class
	public void sortBySafetyRating()
	{
		Collections.sort(cars, new SafetyComparator());
	}
	
	//This method will sort the ArrayList of Car objects by their MaxRange.
	//By passing in an object of the RangeComparator class, the sort method will use the compare method in the RangeComparator class
	public void sortByMaxRange()
	{
		Collections.sort(cars, new RangeComparator());
	}
	
	
	//This method is used to pick a random day number based on the month passed in. For example if we passed in 2 as our month ,which would be February,
	//we wouldn't want to pick 30 as our day number because that February doesn't exist. Also I know February could have 29 days on a leap year but vatever, I'm not going to code that today.
	public int randomDay(int month)
	{
		if (month == 1)
		{
			int day = (int)(Math.random() * 28 + 1);
			return day;
		}
		else if (month == 0 || month == 2 || month == 4 || month == 6 || month == 7 || month == 9 || month == 11)
		{
			int day = (int)(Math.random() * 31 + 1);
			return day;
		}
		else
		{
			int day = (int)(Math.random() * 30 + 1);
			return day;
		}
	}
	
}
