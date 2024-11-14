# Rex Koh - Project Portfolio Page

## Project: CliRental

CliRental is a perfect desktop app dedicate to managing a car rental business involves handling large volumes
of data, making traditional pen-and-paper methods inefficient for managers. This app is designed specifically for car
rental managers, offering a Command Line Interface (CLI) that enables quick data entry and efficient tracking of
customers, cars, and transactions. It is written in Java and has about 4k Loc.

## Summary of Contributions

### <u>Features added</u>

`Feature 1` : Add the ability to add customers details into the database.

* `What it does` : Allows user to store customer's name , age and contact number to our database.
* `Justification`: Customer details like their names are needed to link car rental transactions to the specific customer.
* `Highlights`: This command allows user to add customer which is the building blocks for our application. Without the 
ability to add customers , the app would not achieve its intended use.

`Feature 2` : Add the ability to list all cars in the database.

* `What it does` : Allows user to get a list of all the cars in the database , giving the user a 
good overview of the cars.
* `Justification` : User cannot know what car is available to add a transaction if the user does not have a way to view 
all cars. The user is also not able know the current status of the car , whether it is rented or not as well.

`Feature 3` : Save car, customer and transaction data into different text file.

* `What it does` : The application automatically stores car, customer and transaction data into different text files and 
automatically loads upon start of program. The predetermined text files are customerData.txt, carData.txt,
transactionData.txt. They will be loaded into a directory called data. If the data files are not created on the local
computer, the files and folder will be automatically created by the program upon running.
* `Justification`: data should be able to be stored on the local computer and read by the program. If the user 
accidentally exits the program without any file saving , it would result in the user having to type every command again.
This is very troublesome. Furthermore, the automatic updating and saving of the file is convenient to the user as the 
user does not have to do anything extra. 
* `Highlights`: The feature requires constant maintenance when new parameters are added to the Customer, Car and 
Transaction class. This maintenance requires good care to allow data loaded to be accurate as if the user had type it 
via commands. The files are also seperated into multiple different files and classes for easy maintenance and lesser 
coupling. Multiple Junit tests for each individual file class are implemented to ensure the correctness of data which 
is a priority.

### <u>Code Contribution</u>

Click [here](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=rexkoh425&breakdown=true)
to view my code contributions for this project!!

### <u>Project management</u>

Managed releases v1.0 - v2.1 (3 releases) on GitHub.<br>

### <u>Enhancements to existing features:</u>

1) Improved Junit test for CarFile , CustomerFile and TransactionFile. <br>
2) Achieve at least 80% coverage for Class, Method , Line and Branch for each class mentioned above.<br>
3) Improved error handling of loading data from file to prevent malicious data from crashing the program.<br>
4) Data loaded from file follows constraints as if the user had added the command through the command line.<br>

### <u>Documentation:</u>
#### User Guide:
Added documentation for the features e.g. add-user and list-cars.<br>
Added documentation to inform user of file loading.<br>
Added user stories for add-user , list-cars<br>

#### Developer Guide:
1) Added implementation details of the adding customer details e.g. AddCustomerSequence.png.<br>
2) Added implementation details of listing cars e.g. ListCarsSequence.png.<br>
3) Added implementation details of file saving e.g. CarFileLoader.png & FileHandlerClassDiagram.png.<br>
4) Updated NFR.<br>
5) Added overall architecture diagram.<br>
6) Include manual testing instructions.<br>

### Community:
PRs reviewed (with non-trivial review comments): #68, #95, #110.<br>
Reported bugs and suggestions for other teams in the class in peer DG review.<br>
