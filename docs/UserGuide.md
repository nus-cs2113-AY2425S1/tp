# CliRental User Guide

## Introduction

Clirental is a CLI-based application that allows car rental companies to track their customers, cars, and rental transactions.

This application is useful to car rental companies that provide day-to-day rental service to their customers in helping them to manage their car fleet, customer information and rental transactions.

Summary of Contents:
- [Quick start](#quick-start)
- [File Saving](#file-saving)
- [Features](#features)
  - [Adding a user: `add-user`](#adding-a-user-to-the-database-add-user)
  - [Adding a car: `add-car`](#adding-a-car-add-car)
  - [Removing a user: `remove-user`](#removing-a-user-from-the-database-remove-user)
  - [Removing all users: `remove-all-users`](#removing-all-users-from-the-database-remove-all-users)
  - [Removing a car: `remove-car`](#removing-a-car-remove-car)
  - [Removing all cars: `remove-all-cars`](#removing-all-cars-remove-all-cars)
  - [Listing all users: `list-users`](#listing-all-cars-list-cars)
  - [Listing all cars: `list-cars`](#listing-all-cars-list-cars)
  - [Listing all rented-out cars: `list-rented`](#listing-all-rented-out-cars-list-rented)
  - [Listing all available cars: `list-available`](#listing-all-available-cars-list-available)
  - [Updating rental status of cars](#updating-rental-status-of-car)
  - [Adding a transaction: `add-tx`](#adding-a-transaction-add-tx)
  - [Removing a transaction: `remove-tx`](#removing-a-transaction-remove-tx)
  - [Removing all transactions: `remove-all-txs`](#removing-all-transactions-remove-all-txs)
  - [Listing all transactions: `list-txs`](#listing-all-transactions-list-txs)
  - [Listing all completed transactions: `list-txs-completed`](#listing-all-completed-transactions-list-txs-completed)
  - [Listing all uncompleted transactions: `list-txs-uncompleted`](#listing-all-uncompleted-transactions-list-txs-uncompleted)
  - [Marking a transaction as complete: `mark-tx`](#marking-a-transaction-as-complete-mark-tx)
  - [Unmarking a transaction as incomplete: `unmark-tx`](#unmarking-a-transaction-as-incomplete-unmark-tx)
  - [Finding transactions under a Customer: `find-txs-by-customer`](#find-transactions-under-a-customer-find-txs-by-customer)
  - [Displaying the help page: `help`](#displaying-the-help-page-help)
  - [Exiting the program: `exit`](#exiting-the-program-exit)
- [FAQ](#faq)
- [Command Summary](#command-summary)


## Quick Start

1. Ensure that you have Java 17 or above installed.
2. Download the latest version of `CliRental` from [here](https://github.com/AY2425S1-CS2113-T11-3/tp/releases).
3. Copy the jar file into an empty folder.
4. Open your terminal and navigate to the folder the jar file is placed in.
5. Run java -jar CliRental.jar and you can start using right away.

## Features

---
### File Saving

`Customer`, `Transaction`, and `Car` data will be saved in their respective files under the `data` directory.


#### `IMPORTANT NOTE / DISCLAIMER: `

* The file saving feature does not include the functionality of being able to add/edit data by editing the text files. 
Please add/edit data via the command line using the commands given.
* Users must ensure any additional data follows the correct format.

`If user do not follow instructions, following additional measures are placed.`
* Corrupted data will be flagged upon start of program , highlighting the rows of data which are wrong. Please correct 
them or the corrupted lines will be flushed from the data files upon the `first correct command` given by the user.


**Filenames:**

* `Car data`: `carData.txt`
* `Customer data`: `customerData.txt`
* `Transaction data`: `transactionData.txt`

Format : 
* `Car data` :  `CAR MODEL | LICENSE PLATE | PRICE | RENTED | EXPENSIVE`
* `Customer data` : `NAME | AGE | PHONE NUMBER`
* `Transaction data` : `TRANSACTION ID | LICENSE PLATE | CUSTOMER NAME | RENTAL DURATION(DAYS) | 
RENTAL START DATE | COMPLETED`

Types :
* `Car data` :  `STRING | STRING | DOUBLE | BOOLEAN | BOOLEAN`
* `Customer data` : `STRING | INT | STRING`
* `Transaction data` : `STRING | STRING | STRING | INT | LOCALDATE | BOOLEAN`

**Example:**

* `Car data` :  `Toyota Corolla | SGM4932K | 120.0 | false | false`
* `Customer data` : `John | 22 | 90907638`
* `Transaction data` : `TX1 | SGM4932K | John | 30 | 17-10-2024 | false`

Others : 

* Any string `not "true"` will be treated as `false` when it is placed in a BOOLEAN section.
* Loading of data follows constraints mentioned in the respective `add` commands.

---
## Features

### Adding a User to the Database: `add-user`

Adds a customer to the list of customers tracked by the car rental application.

**Format:** `add-user /u [CUSTOMER_NAME] /a [AGE] /c [CONTACT_NUMBER]`

* `CUSTOMER_NAME` : `STRING`.
* `AGE` : `INT`
  * age should be >= 18 and <= 100.
* `CONTACT_NUMBER` : `STRING`
  * `[8 DIGITS AND STARTS WITH 8 or 9]`
  * `No space between digits`
  * `E.g. 95382572`
* `/u` , `/a` , `/c` must be in sequence.

**Example of usage:**
`add-user /u John /a 18 /c 95382572`

**Sample Response:**
```
add-user /u John /a 18 /c 95382572
Customer added
Customer name : John
Age : 18
Contact Number : 95382572
```

### Removing a User from the Database: `remove-user`

Removes a customer from the customer list.

**Format:** `remove-user /u [CUSTOMER_NAME]`

* `CUSTOMER_NAME` : `STRING`.
* `CUSTOMER_NAME` must match an existing customer in the database.
* `CUSTOMER_NAME` is not case sensitive. 'John' and 'john' mean the same customer.

**Example of usage:**
`remove-user /u John`

**Sample Response:**
```
John has been removed from customer list
```
### Removing all Users from the Database: `remove-all-users`

Removes all customers from the customer list. 

**Format:** `remove-all-users`

**Example of usage:**
`remove-all-user`

**Sample Response:**
```
All customers removed!!!
```
### Listing All Users: `list-users`

Lists all customers in the customer list in this format for each customer:  
Customer Name | Age | Contact Number

**Format:** `list-users`

**Sample Output:**
```
Here are all the customers: 
1) John | 18 | 95382572
2) Alice | 25 | 81234567
```
If the list is **empty**:

``` 
Customer list is empty.

```
---
### Adding a Car: `add-car`

Adds a car to the car list.

**Format:** `add-car /n [CAR_MODEL] /c [LICENSE_PLATE_NUMBER] /p [PRICE]`

- `/n`, `/c` and `/p` identifiers **must be** in the correct order.
- `LICENSE_PLATE_NUMBER` **must be** in the following format: `SXX####X`, where
    - `X` is any letter from **A to Z**.
    - `####` is any number from **1 to 9999**, **without any leading zeroes**.
    - Starts with the letter **S**.
- `PRICE` must be a **non-negative, numeric value**.
- `PRICE` cannot exceed **10000**.
- `$` character not required for `PRICE`.

**Example:** 
`add-car /n Honda Civic /c SGE1234X /p 10000`

**Sample output:**
``` 
Car added to list
Car details:
Honda Civic | SGE1234X | $10000.00 | Available | Affordable | Median price: 10000.0
```
---
### Removing a Car: `remove-car`

Removes a car from the fleet based on the car's unique ID.

**Format:** `remove-car /i [LICENSE_PLATE_NUMBER]`

- `/i` identifier specifies the license plate number belonging to the car that is to be removed.
- `LICENSE_PLATE_NUMBER` must match an existing car in the database.

**Example:** 
`remove-car /i SGE1234X`

**Sample output:**
```
Car with license plate SGE1234X removed from list.
```

If the `LICENSE_PLATE_NUMBER` is not found:
```
No car found with license plate [SGE1234X]
```
---
### Removing all Cars: `remove-all-cars`

Removes all cars from the fleet.

**Format:** `remove-all-cars`

**Sample output:**
```
All cars removed!!!
```
### Listing All Cars: `list-cars`

Lists all the cars owned by the company, sorted according to the price of renting the car for a day.  
The format for the each car in the list is:  
Car Model | License PLate Number | Price of Rental (Per Day) | Availability (for Rental) | Price Category | Median Price of Cars in Fleet

**Format:** `list-cars`

**Sample Output:**
```
Here are the current cars in the company:
1) Honda Civic | SGE1234X | $1000.00 | Available | Affordable | Median price: 1000.0
2) Toyota Vios | SKP890C | $2000.00 | Available | Expensive | Median price: 1000.0
```
If the list is **empty**:

``` 
Oops!! Car list is empty...
Use command <add-car> to add a new car.
```
---
### Listing All Rented Out Cars: `list-rented`

Lists all the cars that are currently rented out in this format for each car:  
Car Model | License Plate Number | Price of Rental (Per day)

**Format:** `list-rented`

**Sample output:**
```
Here are all the rented-out cars:
1) Honda Civic | SGE1234X | $100.00
2) Toyota Camry | SKL4567M | $200.00
3) Nissan Latio | SFT1190A | $300.00
```

If the list is **empty**:
```
No cars currently rented out...
```
---
### Listing All Available Cars: `list-available`

Lists all available cars in the company in this format for each car:  
Car Model | License Plate Number | Price of Rental (Per day)

**Format:** `list-available`

**Sample output:**
```
Here are all the available cars:
1) Mitsubishi Attrage | SGP7877N | $1500.00
2) Honda Vezel | SLK9945F | $3400.00
```

If the list is **empty**:
```
There are no available cars at the moment...
```
---
### Updating Rental Status of Car

There is no need to manually update the rental status of a car. 
The status will be updated automatically when a transaction record is:

- Added
- Removed
- Marked as completed
- Marked as not completed

### Adding a Transaction: `add-tx`

Adds a new rental transaction to the system.

To add transaction bearing either an existing license plate number and/or customer name, all previous transactions containing either both or one of the parameter must be either marked as completed or be removed from the transaction list.
It means that when a customer and a car are in a transaction, either that customer or that car cannot involve in other transactions, including transactions with different time periods (start date - end date), unless that transaction is marked as completed or be removed from the transaction list.

**Format:** `add-tx /c [LICENSE_PLATE_NUMBER] /u [CUSTOMER_NAME] /d [DURATION] /s [START_DATE: dd-MM-yyyy]`

- `/c` identifier specifies the license plate number of the car the customer wants to rent.
- `LICENSE_PLATE_NUMBER` must match an existing car in the database. This is unique as the program will not allow 2 cars to have the same license plate number.


- `/u` identifier specifies the name of the customer. 
- `CUSTOMER_NAME` must match an existing customer in the database. This is unique as the program will not allow 2 customers to have the same name.


- `/d` identifier specifies the duration of the rental in days.
- `DURATION` must be an integer between 1 to 365 (inclusive). This allows the rental companies to handle rental transactions from 1 day to 365 days (a year). 


- `/s` identifier specifies the start date of the rental.
- `START_DATE` is in the format of [dd-MM-yyyy], accepting integers input only. It must be a valid date in the calender.

**Example:**  
`add-tx /c SZZ1579D /u John /d 15 /s 11-05-2025`

**Sample Response:**
```
Transaction added:
[ ] TX2 | SZZ1579D | John | 15 days
Start Date: 11-05-2025 | End Date: 26-05-2025
```
---
### Removing a Transaction: `remove-tx`

Removes a specific rental transaction from the system based on the transaction ID.

**Format:** `remove-tx /t [TRANSACTION_ID]`

- `/t` identifier specifies the transaction ID to be removed.
- `TRANSACTION_ID` must begin with "TX" and match an existing transaction in the system.

**Example:**  
`remove-tx /t TX1`

**Sample output:**

```
Transaction deleted: [ ] TX1 | SKL4567M | Alice | 7 days
Start Date: 15-12-2024 | End Date: 22-12-2024
```


If the `TRANSACTION_ID` is not found:
```
Transaction not found
```
---

### Removing All Transactions: `remove-all-txs`

Removes all transactions from the system.

**Format:** `remove-all-txs`

**Sample output:**
```
All transactions removed!!!
```

### Listing All Transactions: `list-txs`

Displays all transactions stored in the system in this format for each transaction:  
[ ] Transaction ID | License Plate Number | Customer Name | Duration of Rental (in day(s))  
Start Date | End Date  
where  
[ ] indicates that the transaction is ongoing or has been marked as uncompleted or  
[X] indicates that the transaction is completed or has been marked as completed

**Format:** `list-txs`

**Sample output:**
```
Here are all the transactions: 
1) [ ] TX1 | SKL4567M | Alice | 7 days
Start Date: 15-12-2024 | End Date: 22-12-2024
2) [X] TX2 | SZZ1579D | John | 15 days
Start Date: 11-05-2025 | End Date: 26-05-2025
```

If the list is **empty**:
```
No transaction available.
```

---
### Listing All Completed Transactions: `list-txs-completed`

Displays all transactions that are marked as completed list in this format for each transaction:  
[X] Transaction ID | License Plate Number | Customer Name | Duration of Rental (in day(s))  
Start Date | End Date  
where [X] indicates that the transaction is completed or has been marked as completed

**Format:** `list-txs-completed`

**Sample output:**
```
Here are all the transactions: 
1) [X] TX1 | SKL4567M | Alice | 7 days
Start Date: 15-12-2024 | End Date: 22-12-2024
2) [X] TX2 | SZZ1579D | John | 15 days
Start Date: 11-05-2025 | End Date: 26-05-2025
```

If the list is **empty**:
```
Here are all the completed transactions: 
No completed transaction available.
```
### Listing All Uncompleted Transactions: `list-txs-uncompleted`

Displays all transactions that are ongoing or are marked as uncompleted list in this format for each transaction:  
[ ] Transaction ID | License Plate Number | Customer Name | Duration of Rental (in day(s))  
Start Date | End Date  
where [ ] indicates that the transaction is ongoing or has been marked as uncompleted

**Format:** `list-txs-uncompleted`

**Sample output:**
```
Here are all the transactions: 
1) [ ] TX1 | SKL4567M | Alice | 7 days
Start Date: 15-12-2024 | End Date: 22-12-2024
2) [ ] TX2 | SZZ1579D | John | 15 days
Start Date: 11-05-2025 | End Date: 26-05-2025
```

If the list is **empty**:
```
Here are all the uncompleted transactions: 
No uncompleted transaction available.
```
### Marking a Transaction as Complete: `mark-tx`

Marks a rental transaction as completed, indicating that the transaction is finalized.

**Format:** `mark-tx /t [TRANSACTION_ID]`

- `/t` identifier specifies the transaction ID to be marked as completed.
- `TRANSACTION_ID` must match an existing transaction in the system.

**Example:**  
`mark-tx /t TX1`

**Sample output:**
```
Transaction completed: [X] TX1 | SKL4567M | Alice | 7 days
Start Date: 15-12-2024 | End Date: 22-12-2024
```

If the `TRANSACTION_ID` is not found:
```
Transaction not found
```
---
### Unmarking a Transaction as Incomplete: `unmark-tx`

Unmarks a rental transaction, indicating it is not yet completed.

**Format:** `unmark-tx /t [TRANSACTION_ID]`

- `/t` identifier specifies the transaction ID to be unmarked.
- `TRANSACTION_ID` must match an existing transaction in the system.

**Example:**  
`unmark-tx /t TX1`

**Sample output:**
```
Transaction set uncompleted: [ ] TX1 | SKL4567M | Alice | 7 days
Start Date: 15-12-2024 | End Date: 22-12-2024
```

If the `TRANSACTION_ID` is not found:
```
Transaction not found
```
### Find Transactions under a Customer: `find-txs-by-customer`

Finds all the transactions under a customer using the customer name as the search term.
The transactions are displayed in the same format as list-txs.

**Format:** `find-txs-by-customer /u [CUSTOMER_NAME]`

- `/u` identifier specifies the Customer Name.
- `CUSTOMER_NAME` must partially match an existing customer in the customer list. This is not case-sensitive, 'john' and 'John' is the same, and 'john' input can search for the user 'John Doe', which contains 'John'.

**Example:**  
`find-txs-by-customer /u John`

**Sample output:**
```
Transaction(s) by John found:
[X] TX2 | SZZ1579D | John | 15 days
Start Date: 11-05-2025 | End Date: 26-05-2025
[ ] TX3 | SFT1190A | John | 4 days
Start Date: 22-12-2024 | End Date: 26-12-2024
```

If the customer has no rental transaction:
```
Transaction(s) by John found:
None
```
### Displaying the help page: `help`

Displays a help page containing all the commands, together with
its respective format and description. 

Format: `help`

### Exiting the program: `exit`

Exits the program and saves all data to the respective data text files.

Format: `exit`

---
## FAQ

`No questions to answer for now!!!`

# Command Summary

**`Customer` related commands:**

|          Action           | Format                                                     |
|:-------------------------:|------------------------------------------------------------|
|     **Add** customer      | `add-user /u [CUSTOMER_NAME] /a [AGE] /c [CONTACT_NUMBER]` |
|    **Remove** customer    | `remove-user /u [CUSTOMER_NAME]`                           |
| **Remove all** customers  | `remove-all-users`                                         |
|  **List all** customers   | `list-users`                                               |

**`Car` related commands:**

|         Action          | Format                                                         |
|:-----------------------:|----------------------------------------------------------------|
|       **Add** car       | `add-car /n [CAR_MODEL] /c [LICENSE_PLATE_NUMBER] /p [PRICE]`  |
|     **Remove** car      | `remove-car /i [LICENSE_PLATE_NUMBER]`                         |
|   **Remove all** cars   | `remove-all-cars`                                              |
|    **List all** cars    | `list-cars`                                                    |
|  **List rented** cars   | `list-rented`                                                  |
| **List available** cars | `list-available`                                               |

**`Transaction` related commands:**

|                 Action                 | Format                                                                                          |
|:--------------------------------------:|-------------------------------------------------------------------------------------------------|
|          **Add** transaction           | `add-tx /c [LICENSE_PLATE_NUMBER] /u [CUSTOMER_NAME] /d [DURATION] /s [START_DATE: dd-MM-yyyy]` |
|         **Remove** transaction         | `remove-tx /t [TRANSACTION_ID]`                                                                 |
|      **Remove all** transactions       | `remove-all-txs`                                                                                |
|       **List all** transactions        | `list-txs`                                                                                      |
|  **Mark** transaction as **complete**  | `mark-tx /t [TRANSACTION_ID]`                                                                   |
| **Mark** transaction as **incomplete** | `unmark-tx /t [TRANSACTION_ID]`                                                                 |
|    **List completed** transactions     | `list-txs-completed`                                                                            |
|   **List uncompleted** transactions    | `list-txs-uncompleted`                                                                          |
| **Find** transactions **by customer**  | `find-txs-by-customer /u [CUSTOMER_NAME]`                                                       |

**Other useful commands:**

|       Action       | Format |
|:------------------:|--------|
| Show **help** page | `help` |
|  **Exit** program  | `exit` |
