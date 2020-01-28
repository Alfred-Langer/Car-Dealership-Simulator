/**
 * Alfred Langer
 * Student ID: 500813614
 * This is the Car class. It extends the Vehicle class and implements the Comparable<Car> interface
 */
public class Car extends Vehicle implements Comparable<Car>
{
	// These are the instance variables of the class
	private ModelConstant model;
	private int maxRange;
	private double safetyRating;
	private boolean AWD;
	private double price;
	
	// This is an enum that contains the possible constants for the model of Car
	public static enum ModelConstant
	{
		SEDAN,
		SUV,
		SPORTS,
		MINIVAN
	}
	
	/**
	 * This is our primary Car constructor
	 * @param inp_mfr
	 * @param inp_color
	 * @param inp_power
	 * @param inp_numWheels
	 * @param inp_model
	 * @param inp_maxRange
	 * @param inp_safetyRating
	 * @param inp_AWD
	 * @param inp_price
	 */
	public Car(String inp_mfr,String inp_color,int inp_power, int inp_numWheels,ModelConstant inp_model,int inp_maxRange,double inp_safetyRating,boolean inp_AWD, double inp_price)
	{
		super(inp_mfr,inp_color,inp_power,inp_numWheels);
		model = inp_model;
		maxRange = inp_maxRange;
		safetyRating = inp_safetyRating;
		AWD = inp_AWD;
		price = inp_price;
	}
	
	//These are our get and set methods for our instance variables of class "Car"
	public ModelConstant getmodel()
	{
		return model;
	}
	
	public void setmodel(ModelConstant inp_model)
	{
		model = inp_model;
	}
	
	public int getmaxRange()
	{
		return maxRange;
	}
	
	public void setmaxRange(int inp_maxRange)
	{
		maxRange = inp_maxRange;
	}
	
	public double getsafetyRating()
	{
		return safetyRating;
	}
	
	public void setsafetyRating(double inp_safetyRating)
	{
		safetyRating = inp_safetyRating;
	}
	
	public boolean getAWD()
	{
		return AWD;
	}
	
	public void setAWD(boolean inp_AWD)
	{
		AWD = inp_AWD;
	}
	
	public double getprice()
	{
		return price;
	}
	
	public void setprice(double inp_price)
	{
		price = inp_price;
	}
	
	
	/**
	 * This is a method that displays the information of the current "Car" object
	 * It calls the display method from the super class and then concatenates the information of the new instance variables 
	 * @return the string from the display method of the super class as well as model, price, safetyRating, and maxRange in a formatted string
	 */
	public String display()
	{
		return(super.display() + model + " " + price + "$" + " SF: " + safetyRating + " AWD: " + AWD + " RNG: " + maxRange);
	}
	
	
	/**
	 * This method checks whether or not, two "Car" objects are equal by comparing their instance variables
	 * /It uses the equals method from the Vehicle class and then checks the AWD and model instance variables
	 * @param other (This would be the Car object you're comparing your current Car object to)
	 * @return true if the equals method of the super class returns true and the AWD and model variables are equal
	 * @return false otherwise
	 */
	public boolean equals (Object other)
	{
		Car otherCar = (Car) other;
		if (super.equals(otherCar) == true)
		{
			if (this.AWD == otherCar.AWD && this.model == otherCar.model) 
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * This method comes from the Comparable<Car> interface
	 * It compares the price variable of two Car objects
	 * @return 1 if the first Car has a higher price than the second Car
	 * @return -1 if the first Car has a lower price than the second Car
	 */
	public int compareTo(Car other)
	{
		if (this.price > other.price)
		{
			return 1;
		}
		
		else if (this.price < other.price)
		{
			return -1;
		}
		
		else
			return 0;
	}
	
}
