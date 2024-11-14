# Ryan Fong's Project Portfolio Page

## Project: CLIRental

---
## Overview

**CliRental** is a **CLI-based application** designed to facilitate operations of car rental
companies. It **manages and keeps track of customers, cars, and rental transactions** all
in one place. Targeted specifically at **car rental managers**, it provides them with a
**user-friendly and effective tool** to execute these tasks efficiently, especially when
**handling large volumes of data.**

---
## Code Contribution

Click [here](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=ct9aryan&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=CT9ARyan&tabRepo=AY2425S1-CS2113-T11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
to view my code contributions for this project!! :)
---

## Features implemented

### Feature 1: Implemented `Car` related operations.
- `add-car`: **Adds** a car to the car database, specifying details like
  the car model, license plate number and the rental price.
- `remove-car`: **Removes** a car from the car database.
- `list-cars`: **Lists** out **all** cars in the database.
- `list-rented`: **Lists** out all currently **rented out** cars.
- `list-available`: **Lists** out all currently **available** cars.

<div style="page-break-after: always;"></div>

### Feature 2: Modelled the validity check of license plate number after the real world.

**<u>Background:</u>**

The standard vehicle registration number in Singapore follows the
following format: `SXX####X`, where **X** represents any letter excluding
**I** and **O** (to avoid confusion with numbers 1 and 0 respectively), and
**\####** represents any number from 1 to 9999 without leading zeroes. However, there are
also exceptions where some license plate numbers start with E and only contain 2 letter
prefixes, etc.

**<u>Actual Implementation:</u>**

In an attempt to make things simple and flexible yet still adhering closely to the
actual format, our application allowed **X** to be **ANY** letter, including **I** and **O**.
We also disregarded the other exceptions (e.g. license plate number starting with E). Other
than that, all other requirements of the standard license plate number format were
adhered to.

**Summary of implementation:**

Format: `SXX####X`, where **X** represents **ANY** letter and **\####** represents
any number from 1 to 9999, **without leading zeroes.**

This check prevents the user from entering any random undesirable or 
invalid strings as the license plate number. 

### Feature 3: Added the ability to automatically update the rental status of cars.

- All newly added cars will have a rental status of `Available`.
- The rental status of the car will be updated after any one of the following actions have
  been executed:
    - Transaction record **added**. (`Available` -> `Rented`)
    - Transaction record(s) **removed**. (`Rented` -> `Available`)
    - Transaction marked as **completed**. (`Rented` -> `Available`)
    - Transaction marked as **uncompleted**. (`Available` -> `Rented`)
- This feature omits the need for extra commands and implementation like `mark-rented`
and `mark-available` for instance, hence improving efficiency and effectiveness (E & E)
of the application.

___

<div style="page-break-after: always;"></div>

## Documentation

### User Guide (UG) Contributions
- Added documentation for features:
    - `add-car`
    - `list-cars`
    - `list-rented`
    - `list-available`
    - `list-tx`
    - `Updating rental status of car`
    - `help`
    - `exit`
- Created **Command Summary section** and **Command Summary Tables** and
  filled in tables based on assigned commands listed above.
- Added a **Summary of Contents** at the top of the UG which links to each section 
for easy navigation.

### Developer Guide (DG) Contributions
- Added implementation details for auto updating of car rental status feature.
- Added `removeCar` UML Sequence diagram.
- Added `addCar` UML Sequence diagram.
- Added `addTx` UML Sequence diagram.
- Added UML Class diagram for Car and Transaction classes.

___
## Community

### Team-Based Tasks Contributions
- General code and documentation enhancements, tidying up the format and
  neatness of the code. (e.g. adding divider lines after each output, adding summary of
contents for easier navigation in User Guide)
- Helping to review teammates' PR and give meaningful feedback and
  comments whenever possible.
(Pull requests [#54](https://github.com/AY2425S1-CS2113-T11-3/tp/pull/54),
[#64](https://github.com/AY2425S1-CS2113-T11-3/tp/pull/64),
[#110](https://github.com/AY2425S1-CS2113-T11-3/tp/pull/110))
- Helping to approve teammate's PR whenever necessary.
- Pointed out bugs and provided suggestions for improvements for
  other teams' DG during the peer review exercise.
(Pull request [#10](https://github.com/nus-cs2113-AY2425S1/tp/pull/10))

---
