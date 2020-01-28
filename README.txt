Name: Alfred Langer

All of the java files should function the way they are supposed based on the Word Document
I also created two personal exceptions that are included in the zip file



CarDealership Simulator

This program is designed to simulate a car dealership. You are able to view the list of cars in the car dealership as well
as filter/sort the list based on your preferences.The simulator also allows you to purchase a car and return a car.

UPDATE:
There are a lot more cars now :D.

The simulator also keeps track of BUY and RETURN transactions now everytime you purchase or return a car.You can view these transactions
and certain stats about them such as the SalesPerson and the date that the transaction occured.

Note: Make sure to enter the command "ADD" when the program starts so the car dealership has cars to display.

List of Commands

a.	“L” : lists the cars currently in the dealership

b.	“Q” : quit out of the simulator

c.	“BUY (int)” : buys a car from the dealership based on the VIN/integer provided and creates a BUY transaction.
	(Note: The VIN/integer should be the VIN of the car you want to purchase)

d.	“RET (int)” : returns the car associated with the integer provided and creates a RETURN transaction.

e.	“ADD” : adds cars to the dealership so they can be displayed 
	(Note: You can only use the command once, it will have no effect after it's first use)

f.	“SPR” : This will sort the list of cars by price from lowest to highest

g.	"SSR" : This will sort the list of cars by safety rating from highest to lowest

h.	"SMR" : This will sort the list of cars by max range from highest to lowest
	
(Filters in this program stack, so you can call multiple filters if need be)

i.	"FEL" : This will filter out any cars that are not electrical

j.	"FAW" : This will filter out any cars that are not all wheel drive

k.	"FPR (double) (double): This filter requires you to pass in a range with the first double being the ______ and the second double being the ______. Any cars that are not in the given range will be filtered out.
	(Note: The first double must be lower than the second double.)

l.	"FCL" : This will remove any filters that have been previously activated.
 
m.	"SALES" : This will list all the transactions for the year

n.	"SALES TEAM" : This will list all the names of the members in the SalesTeam

o.	"SALES TOPSP" : This will display the name of the member in the SalesTeam who has sold the most cars.
	(Note: If there is a tie between two or members, both members will be shown)

p.	"SALES  (int)" : This will display all the transactions of the month passed in (Jan = 0, Feb = 1...)
	(Note: If int is 4, then all the transactions that occured in May will be displayed.

q.	"SALES STATS" : This will display certain statistics about all the transactions that occured in the year
	(Note: Stats include: totalSales, bestSalesMonth(in terms of # of cars), averageSalesPerMonth, total Cars bought, and total Cars returned 
