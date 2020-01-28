import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
/**
 * Alfred Langer
 * Student ID: 500813614
 * This is the CarDealershipSimulator
 */
public class CarDealershipSimulator 
{

	public static void main(String[] args) 
	{
		//Creating a scanner for our while loop called sLineInput
		Scanner sLineInput = new Scanner(System.in);
		
		//Creating a CarDealership Object called "AlfredDealership"
		CarDealership AlfredDealership = new CarDealership();
		
		//Creating an ArrayList<Car> called "CarList"
		ArrayList<Car> CarList = new ArrayList<Car>();
		
		//This line will call the function readDatTextFile which will add all the cars to CarList from the "cars.txt" file
		try 
		{
			readDatTextFile(CarList); //GOTTA WRTIE A TRY CATCH LOOP
		}
		
		//These catch statements are used for the exceptions that could be thrown by readDatTextFile
		
		catch (FileNotFoundException e) 
		{
			System.out.println("This program can't seem to find the file 'cars.txt'");
			e.printStackTrace();
		}
		
		catch(IllegalArgumentException e)
		{
			System.out.println("There seems to be an error in your 'cars.txt' file");
			e.printStackTrace();
		}
		
		
		//This is a variable that hold the last car bought by the user
		Car lastBoughtCar = null;
		
		//This flag prevents the ArrayList of AlfredDealership from  being added multiple times to CarList
		boolean AlfredDealershipAddFlag = false;
		
		//This while loop continues to run until one of the if cases returns false 
		while(true)
		{
			//This scanner looks at the line entered in by the user
			Scanner scanner2 = new Scanner(sLineInput.nextLine());
			
			//This makes sure the program won't crash if the enters a blank command 
			if (!scanner2.hasNext()) 
			{
				System.out.println("That is not a valid command!");
				continue;
			}
			
			//This looks at the first token in the first line entered in by the user
			String userInput = scanner2.next();
			
			if (userInput.equals("L")) //This will display all the cars and their information in the CarDealership Object
			{
				AlfredDealership.displayInventory(); 
			}
			else if (userInput.equals("Q")) //This will return false to the while loop closing it
			{
				return;
			}
			else if (userInput.equals("BUY")) //This will ask the user for which car they want to buy, display the car info, call the buyCar method, and store the car object in lastBoughtCar
			{
				if(!scanner2.hasNextInt())
				{
					System.out.println("Provide a VIN with your BUY method");
					continue;
				}
				else
				{
					try
					{
						String result = AlfredDealership.buyCar(scanner2.nextInt());
						System.out.println(result);
					}
					catch (InvalidVINException e)
					{
						System.out.println("No car with that VIN");
					}
					catch(arrayListZeroException e)
					{
						System.out.println("There are no cars to buy currently");
					}
				}
				
			 }
			else if (userInput.equals("RET")) //This will call the returnCar method, which puts lastBoughtCar back into the ArrayList of the CarDealership Object, and then set the lastBoughtCar variable to null
			{
				if(!scanner2.hasNextInt())
				{
					System.out.println("Provide a transactionID with your RET method");
					continue;
				}
				else
				{
					AlfredDealership.returnCar(scanner2.nextInt());
				}
			}
			
			else if (userInput.equals("ADD")) //This will add the all of the elements from the passed in ArrayList<Car> into the ArrayList<Car> of the CarDealership
			{
				if(AlfredDealershipAddFlag == false)
				{
					AlfredDealership.addCars(CarList);
					AlfredDealershipAddFlag = true;
				}
					
			}
			
			else if (userInput.equals("FEL")) //This is used to turn on the ElectricFilter
			{
				AlfredDealership.filterByElectric();
			}
			
			else if (userInput.equals("FAW")) //This is used to turn on the awdFilter
			{
				AlfredDealership.filterByAWD();;
			}
			
			else if (userInput.equals("FCL")) //This is used to turn off all the filters
			{
				AlfredDealership.filtersClear();
			}
			
			else if(userInput.equals("FPR")) //This is used to turn on the PriceFilter as well as set the price range
			{
				if (!scanner2.hasNextDouble()) 
				{
					System.out.println("You must provide two doubles to set your price range");
					continue;
				}
				
				double minRange = scanner2.nextDouble();
				
				if (!scanner2.hasNextDouble()) 
				{
					System.out.println("You must provide two doubles to set your price range");
					continue;
				}
				
				double maxRange = scanner2.nextDouble();
				
				if ((minRange > maxRange) || (minRange < 0)|| (maxRange < 0))
				{
					System.out.println("Your minRange and maxRange must be positive, and minRange has to be lower than or equal to your maxRange");
					continue;
				}
				AlfredDealership.filterByPrice(minRange, maxRange);
			}
			
			else if(userInput.equals("SPR")) //This calls the sortByPrice method 
			{
				AlfredDealership.sortByPrice();
			}
			
			else if(userInput.equals("SSR")) //This calls the sortBySafetyRating method
			{
				AlfredDealership.sortBySafetyRating();
			}
			
			else if(userInput.equals("SMR")) //This calls the sortByMaxRange method
			{
				AlfredDealership.sortByMaxRange();
			}
			else if (userInput.equals("SALES")) 
			{
				if (scanner2.hasNextInt()) //If there is another int token, then we will display all the sales of the month associated with the int token
				{
					// User input is "SALES 10"
					AlfredDealership.dealershipASystem.printTransactionsOfMonth(scanner2.nextInt());
				} 
				else if (scanner2.hasNext()) //If there is another String token, then we will look at the possible commands below
				{
					// User inputed "SALES " + some string
					if (scanner2.hasNext("TEAM")) //This will display all the names of the salesTeam
					{
						System.out.println(AlfredDealership.dealershipSalesTeam.displayAllNames());
					}
					else if (scanner2.hasNext("TOPSP"))//This will display the topSalesPerson and the number of cars they sold
					{
						LinkedList<String> sTeamList = AlfredDealership.dealershipSalesTeam.getSaleTeamList();
						System.out.println(AlfredDealership.dealershipASystem.topSalesPerson(sTeamList));
					}
					else if (scanner2.hasNext("STATS")) //This will display statistics about the transactions.
					{
						AlfredDealership.dealershipASystem.transactionStats();
					}
					else //This is just in case the next String Token doesn't match any of the commands above
					{
						System.out.println("That is not a valid command!");
					}

				} 
				else //If there is no token following, then we'll just print all the transactions recorded
				{
					// User inputted "SALES"
					AlfredDealership.dealershipASystem.printAllTransactions();
				}
			}
			
			
			
			else //This is used to catch any instance where the user types enters an invalid command.
			{
				System.out.println("That is not a valid command!");
			}
		}
		
		
		
	
	}
	
	//This method reads the parameters of all the Car objects from the cars.txt file
	public static void readDatTextFile(ArrayList<Car> CarList) throws IllegalArgumentException,FileNotFoundException
	{
		File inputFile = new File("cars.txt");
			
			//This scanner looks at a single line in inputFile
			Scanner fileScanner = new Scanner(inputFile);
			while(fileScanner.hasNextLine())
			{
				//This takes the first two tokens of the line and puts them into variables
				String inp_mfr = fileScanner.next();
				String inp_color = fileScanner.next();
				
				//This method takes in the next token and then goes through the ModelConstant Enum in class Car and matches it with one of the elements
				//If it doesn't find a match, then it throws an IllegalArgumentException
				String modelType = fileScanner.next();
				Car.ModelConstant inp_model = null;
				for(Car.ModelConstant enumCycle: Car.ModelConstant.values())
				{
					if (enumCycle.toString().equals(modelType))
					{
						inp_model = enumCycle;
					}
				}
				if(inp_model == null)
					throw new IllegalArgumentException("Invalid text format (Incorrect modelType");
				
				//This method takes in the next token and then checks if it equals to GAS_ENGINE or ELECTRIC_MOTOR and assigns it to 0 or 1 respectively
				//If it doesn't find a match, then it throws an IllegalArgumentException
				int inp_power;
				String powerType = fileScanner.next();
				if (powerType.equals("GAS_ENGINE"))
					inp_power = 1;
				else if (powerType.equals("ELECTRIC_MOTOR"))
					inp_power = 0;
				else
					throw new IllegalArgumentException("Invalid text format (Incorrect PowerType)");
				
				//This takes in the next two tokens and puts them into variables
				double inp_safetyRating = fileScanner.nextDouble();
				int inp_maxRange = fileScanner.nextInt();
				
				//This block of codes checks to see if the next token is equal to AWD, if so then inp_AWD gets set to True. If not then inp_AWD is false
				boolean inp_AWD = false;
				String awdString = fileScanner.next();
				if (awdString.equals("AWD"))
					inp_AWD = true;
				
				//This variable gets set to 4 for all Car objects
				int inp_numWheels = 4;
				
				//This gets the next token and sets it to the price variable
				double inp_price = fileScanner.nextDouble();
				
				
				//This checks if there is another token left in the line
				if(fileScanner.hasNextInt())
				{
					//If so then it is an ElectricCar object and it gets the next token for rechargeTime and creates the object by using the variables collected
					int rechargeTime = fileScanner.nextInt();
					ElectricCar newCar = new ElectricCar(inp_mfr,inp_color,inp_power,inp_numWheels,inp_model,inp_maxRange, inp_safetyRating,inp_AWD, inp_price,rechargeTime);
					//This adds the object to the CarList
					CarList.add(newCar);
				}
				else
				{
					//If not then the Car object is created by using the variables collected
					Car newCar = new Car(inp_mfr,inp_color,inp_power,inp_numWheels,inp_model,inp_maxRange, inp_safetyRating,inp_AWD, inp_price);
					//This adds the object to the CarList
					CarList.add(newCar);
				}
			}
			
		} 
	
}
