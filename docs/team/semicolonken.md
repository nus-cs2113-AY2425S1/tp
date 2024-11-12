# Kenneth Tan - Project Portfolio Page

## Project: CliRental

CliRental is a straightforward, user-friendly application designed to help manage car rental operations efficiently. 
Built with Java, this app allows users to track rental transactions, manage customer information, 
and monitor car availability through a series of command-line commands. Key functionalities include 
adding and removing customers, logging rental transactions, checking car availability, 
and marking transactions as completed. Designed for quick access and ease of use, 
this app is ideal for small to mid-sized rental services looking to streamline operations without a complex interface.

## Summary of Contributions

### <u>Features added</u>

`Feature 1` : Add the ability to remove customers details from the database.

* What it does : Allows user to remove a customer from our database. 
* Justification: Customer details such as contact number can change, removal of customers allow the updated information 
to be added afterward. It is also important to allow removal when details have been entered wrongly.
* Highlights: This command allows user to remove customer which is the building blocks for our application. Without the
  ability to remove customers , the app would not achieve its intended use.

`Feature 2` : Add the ability to add a rental transaction into the database

* What it does : Allows user to add a rental transaction into the database using information from the car and the customer
* Justification: To allow management of rental transaction, it is essential to add a rental transaction into the database.
This allows the user to keep a record of all rental transactions that have occurred which can then be view later on. 
* Highlights: This command allows user to add a rental transaction which is the part of the main feature for our 
application, rental management Without the ability to add transaction, the app would not serve its purpose.
* Difficulty of task: It is tedious to make sure that the transactionID is only generated when a valid transaction
has been successfully added and the transactionID is generated incrementally but not randomly. There is a need to have a 
counter to increment the ID but the counter will not be stored when the program exit as the program only stores the 
transactionID. It is necessary to compare the transactionID from the transactionData store and
update the transactionCounter which was not stored to match the saved file. 


### <u>Enhancements to existing features:</u>
* Worked with rexkoh425 to save Transaction ID
* Added case-insensitive features for some commands, e.g. remove-user and add-tx
* Added junittest for for customer removal

### <u>Documentation:</u>
#### User Guide:
Added documentation for the features e.g. remove-user and list completed transactions.
### Adding a Transaction: `add-tx`

Adds a new rental transaction to the system.

To add transaction bearing either an existing license plate number and/or customer name, all previous transactions containing either both or one of the parameter must be either marked as completed or be removed from the transaction list.

**Format:** `add-tx /c [LICENSE_PLATE_NUMBER] /u [CUSTOMER_NAME] /d [DURATION] /s [START_DATE: dd-MM-yyyy]`

- `/c` identifier specifies the license plate number of the car the customer wants to rent.
- `LICENSE_PLATE_NUMBER` must match an existing car in the database. This is unique as the program will not allow 2 cars to have the same license plate number.


- `/u` identifier specifies the name of the customer.
- `CUSTOMER_NAME` must match an existing customer in the database. This is unique as the program will not allow 2 customers to have the same name.


- `/d` identifier specifies the duration of the rental in days.
- `DURATION` must be an integer between 1 and 365 (inclusive). This allows the rental companies to handle rental transactions from 1 day to 365 days (a year).


- `/s` identifier specifies the start date of the rental.
- `START_DATE` is in the format of [dd-MM-yyyy], accepting integers input only. It must be a valid date in the calendar.

**Example:**  
`add-tx /c SZZ1579D /u John /d 15 /s 11-05-2025`

**Sample Response:**
```
Transaction added: 
[ ] TX2 | SZZ1579D | John | 15 days
Start Date: 11-05-2025 | End Date: 26-05-2025
```
Added sample outputs to match the latest release

### <u>Code Contribution</u>

Code contributed: https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=SemiColonKen&breakdown=true
