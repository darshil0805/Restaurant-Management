# Restaurant-Management

This is a JAVA project made by Sarthak Arora (2020A7PS0060P) and Darshil Jariwala(2020A7PS0985P) under course OOP, BITS Pilani 2021


Project 16: Restaurant Management (Smart kitchen) 
Created By: P HARSHITH BHARGAV


Description:

The restaurant can be open, close or full and depending on the state the orders will be taken
Then there will be employee/staff class, recipe class, dishes, pricing, expenses and revenue.
The students have to implement these classes along with the multithreading i.e different states the restaurant and customers can be in.
It should provide functionality to choose tables for 2 or 4 or 6.


Class Design:

Here is the list of the basic classes, interfaces and methods one should implement 
Order -  will contain the details of the item ordered, order number, price etc
Revenue - should calculated the revenue based on number of orders
Menu - This class shall contain the items that can be ordered and it’s description pricelist can be inherited
Pricelist - prices of all the items available. May include tax separately
ResturantState(open or closed or full) - This class shall be implementing the multithreading so as will take orders only in open state
CookState(taken, preparing, served) - If the restaurant is open then only orders can be taken. Then the order will pass through the following states and finally the order will be served.


                                                     

Sample input and outputs:

There can be two menus, one for customers and one for administration.

Inputs: 

For customers, the order can be taken if the restaurant is in open state. 
if it is closed or full it should deny order or display the same.
Then the menu could be shown and items that are ordered will go into taken state. And the item should finally be served.
For the administration menu  the option to add more items to the menu or change price could be there. 

Output:

    

The bill for order could be displayed.
There can be an option in the administration menu to show revenue.
When the order is finally served then that could be displayed.
