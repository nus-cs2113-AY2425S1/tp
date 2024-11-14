# Dao Trong Khanh - Project Portfolio Page

## Project: CliRental

**CLiRental** is a Command Line Interface (CLI) application tailored for car rental businesses, 
providing an efficient solution for managing customers, cars, and rental transactions in a centralized, 
user-friendly platform. Targeted at car rental managers who often deal with large volumes of data, 
**CLiRental** replaces traditional, inefficient pen-and-paper methods with a fast, reliable, and straightforward desktop tool. 
Developed in Java with approximately 4,000 lines of code (LOC), this application is built to handle the core needs of a car rental operation, 
enabling managers to perform quick data entries and track essential business operations seamlessly.

## Summary of Contributions

### <u>Features additions and enhancements</u>

#### 1. Help Command Implementation
- **What it does**: Provides a user-friendly `help` command that displays a complete list of available commands along with descriptions.  
- **Justification**: This feature improves usability significantly by giving new users or those unfamiliar with the commands a quick reference guide, enhancing user efficiency and reducing the learning curve.  
- **Highlights**: This feature is foundational for user support, making the system intuitive and accessible without external documentation.

#### 2. Remove a Car
- **What it does**: Enables users to remove a specific car from the system, keeping the fleet information up-to-date and reflecting only available vehicles.  
- **Justification**: This feature is essential for inventory management, allowing the business to accurately represent current fleet availability and prevent outdated or unavailable cars from being rented.  
- **Highlights**: The feature was designed to streamline car management processes, particularly focusing on ease of use for front-desk employees who manage car listings daily.

#### 3. Add Car with Statistical Calculations
- **What it does**: Enhances the `add-car` functionality by calculating the median price of all available cars and categorizing them based on predefined price ranges (e.g., affordable, expensive).  
- **Justification**: This feature provides valuable insights into the pricing structure, allowing employees to offer budget-aligned options to customers and helping managers analyze pricing distribution.  
- **Highlights**: This enhancement required integrating statistical calculations and data filtering into the `add-car` feature, expanding its functionality while maintaining simplicity for end-users.

#### 4. Enhanced Transaction Constraints
- **What it does**: Modifies the Customer, Car, and Transaction classes to enforce a strict one-to-one relationship between customers and cars in each transaction. Prevents additional transactions involving the same customer or car unless the previous transaction is marked complete or deleted.  
- **Justification**: Ensuring each customer can only rent one car at a time prevents conflicts and errors in transaction management, improving data integrity and operational accuracy.  
- **Highlights**: This enhancement introduced transaction constraints across multiple classes, requiring in-depth changes to existing data handling and validation processes. This improvement affects future features that rely on transaction data consistency.

#### 5. Remove-All Commands
- **What it does**: Adds `remove-all` commands for clearing all customer, car, and transaction records, providing an efficient means for data reset or cleanup.  
- **Justification**: This feature enhances administrative control by allowing bulk deletion of outdated or unnecessary records, improving data management efficiency.  
- **Highlights**: Implementing `remove-all` commands required handling data dependencies and ensuring complete data clearance across related classes without impacting system stability.

#### 6. Comprehensive Test Coverage
- **What it does**: Expands test cases for all implemented features, aiming for high test coverage to validate feature reliability and accuracy under various conditions.  
- **Justification**: Comprehensive testing improves system stability, reduces bugs, and ensures that each feature performs as expected, even in edge cases.  
- **Highlights**: This required extensive testing and coverage analysis, ensuring the implemented features integrate seamlessly and handle diverse user inputs effectively.

Each of these contributions enhances the system's functionality, usability, and reliability, providing significant value to both users and administrators.

### <u>Code Contributions</u>

- Click [tkhahns Contributions](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=tkhahns&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-09-20&tabOpen=true&tabType=authorship&tabAuthor=tkhahns&tabRepo=AY2425S1-CS2113-T11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false) 
to view my code contributions!

### <u>Project management</u>
- Generate the project idea and come up with the initial project plan diagram
- Set up organization and team repository.
- Manage releases **v2.0** and **v2.1** on GitHub.
- Resolve internal GitHub issues and issues generated by other groups from the PE-DryRun.

### <u>Documentation:</u>
#### User Guide:
- Add documentation for multiple features, especially `add` and `remove` commands.
- Finetune and fix any existing error regarding the User Guide formatting.
- Complete the list of missing features for **v2.0**.

#### Developer Guide:
- Update the complete list of User Stories for the project.

### <u>Community:</u>
- Review PRs with trivial and non-trivial code comments, manage GitHub PRs and commits.
- Helping to check, approve, and merge teammate's PR whenever necessary.
- Reported bugs and suggestions for other teams in peer reviews.
- General code and documentation enhancements, tidying up the format and neatness of the code.
