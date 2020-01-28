
/**
 * Alfred Langer
 * Student ID: 500813614
 * This is the Vehicle class
 */
public class Vehicle 
{
	
	//These are the instance variables of the class
	private String mfr;
	private String color;
	private int power;
	private int numWheels;
	private int VIN;
	
	//These are the permanent constants of the class
	public static final int ELECTRIC_MOTOR = 0;
	public static final int GAS_ENGINE = 1;
	
	
	/**
	 * This is our primary Vehicle constructor
	 * @param inp_mfr
	 * @param inp_color
	 * @param inp_power
	 * @param inp_numWheels
	 */
	public Vehicle(String inp_mfr,String inp_color,int inp_power, int inp_numWheels)
	{
		mfr = inp_mfr;
		color = inp_color;
		power = inp_power;
		numWheels = inp_numWheels;
		VIN = (int)(Math.random() * 400 + 100);
	}
	
	//The following methods are our get and set methods for each instance variable of the class
	public String getmfr()
	{
		return mfr;
	}
	
	public void setmfr(String inp_mfr)
	{
		mfr = inp_mfr;
	}
	
	public String getcolor()
	{
		return color;
	}
	
	public void setcolor(String inp_color)
	{
		color = inp_color;
	}
	
	public int getpower()
	{
		return power;
	}
	
	public void setpower(int inp_power)
	{
		power = inp_power;
	}
	
	public int getnumWheels()
	{
		return numWheels;
	}
	
	public void setnumWheels(int inp_numWheels)
	{
		numWheels = inp_numWheels;
	}
	
	public int getVIN()
	{
		return VIN;
	}
	
	
	/**
	 * This method checks whether or not, two "Vehicle" objects are equal by comparing their instance variables
	 * The variables it checks are: mfr, power, and numWheels
	 * @param other
	 * @return true if all three instance variables are the same
	 * @return false otherwise 
	 */
	public boolean equals (Object other)
	{
		Vehicle otherVehicle = (Vehicle) other;
		if (this.mfr == otherVehicle.mfr && this.power == otherVehicle.power && this.numWheels == otherVehicle.numWheels)
		{
			return true;
		}
		
		else
		{
			return false;
		}
		
	}
	
	/**
	 * This is a method that displays the information of the current "Vehicle" object
	 * @return VIN, mfr and color in a formatted string 
	 */
	public String display()
	{
		return("VIN: " + VIN + " " + mfr + " " + color + " ");
	}
}
