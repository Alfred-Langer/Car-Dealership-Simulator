/**
 * Alfred Langer
 * Student ID: 500813614
 * This is the ElectricCar class. It extends the Car class
 */
public class ElectricCar extends Car 
{
	//These are the instance variables of class "ElectricCar"
	private int rechargeTime;
	private String batteryType;
	
	/**
	 * This is our primary ElectricCar constructor
	 * @param inp_mfr
	 * @param inp_color
	 * @param inp_power
	 * @param inp_numWheels
	 * @param inp_model
	 * @param inp_maxRange
	 * @param inp_safetyRating
	 * @param inp_AWD
	 * @param inp_price
	 * @param inp_rechargeTime
	 * @param inp_batteryType
	 */
	public ElectricCar(String inp_mfr,String inp_color,int inp_power, int inp_numWheels,ModelConstant inp_model,int inp_maxRange,double inp_safetyRating,boolean inp_AWD, double inp_price, int inp_rechargeTime)
	{
		super(inp_mfr, inp_color,inp_power, inp_numWheels, inp_model, inp_maxRange, inp_safetyRating, inp_AWD, inp_price);
		rechargeTime = inp_rechargeTime;
		batteryType = "Lithium";
	}
	
	//These are our get and set methods for our instance variables of class "ElectricCar"
	public int getrechargeTime()
	{
		return rechargeTime;
	}
	
	public void setrechargeTime(int inp_rechargeTime)
	{
		rechargeTime = inp_rechargeTime;
	}
	
	public String getbatteryType()
	{
		return batteryType;
	}
	
	public void setbatteryType(String inp_batteryType)
	{
		batteryType = inp_batteryType;
	}
	
	
	/**
	 * This is a method that displays the information of the current "ElectricCar" object
	 * It calls the display method from the super class and then concatenates the information of the new instance variables 
	 * @return the string from the display method of the super class as well as the variables batteryType, and rechargeTime in a formatted string 
	 */
	public String display()
	{
		return(super.display() + " EL," + " BAT: " + batteryType + " RCH: " + rechargeTime);
	}
	
}

