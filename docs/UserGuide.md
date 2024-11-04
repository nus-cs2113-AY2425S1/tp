# Finance Buddy User Guide

## Contents
- [Introduction](#Introduction)
- [Quick Start](#Quick-Start)
- [Features](#Features)
  - [Help](#Help)
  - [Add Transaction](#Add-Transaction)
  - [Edit Transaction](#Edit-Transaction)
  - [Delete Transaction](#Delete-Transaction)
  - [List Entries](#List-Entries)
    - [List By Type](#List-By-Type)
    - [List By Date](#List-By-Date)
  - [Set/Edit Budget](#SetEdit-Budget)
- [FAQ](#FAQ)
- [Command Summary](#Command-Summary)

## Introduction

Finance Buddy is a command-line interface application that allows university students
to log their daily expenditures to help manage their budgets.

Users can add, delete and edit income and expenditure logs into the app, as well as
list out all their logged transactions. Users are also able to set a monthly budget
for themselves in the app, and the app will notify them if
they have exceeded, or are close to exceeding their budget.

The current progress of the logging will be saved after each successful command.
Progress is restored when FinanceBuddy is started up each time.

## Quick Start

1. Ensure that Java 17 or above is installed.
2. Download the latest version of `FinanceBuddy` [here](https://github.com/AY2425S1-CS2113-W14-3/tp/releases).
3. Download the `.jar` file and save it on the computer.
4. In the directory where the jar file is saved, open Terminal.
5. In Terminal, run `java -jar FinanceBuddy.jar`.
6. Happy logging!

## Features

{Give detailed description of each feature}

### Help

Lists out the full list of usable commands.

**Format**:
`help`

**Output**:

### Add Transaction

Adds an income or expense entry to your financial list.

**Format**:
- For Expense: `expense DESCRIPTION /a AMOUNT [/d DATE] [/c CATEGORY]`
- For Income: `income DESCRIPTION /a AMOUNT [/d DATE] [/c CATEGORY]`

**Examples**:
- `expense Lunch /a 10.50 /d 12/10/24 /c FOOD`
- `income Freelance Work /a 500 /d 15/10/24 /c SALARY`

### Edit Transaction

### Delete Transaction
Deletes an entry from your financial list.

**Format**: `delete INDEX`

**Example**:
- `delete 3` - Deletes the entry at index 3.

### List Entries

Lists out entries in your financial list for your perusal. Entries can be filtered by type (income/expense)
or restricted to a stipulated period. The app will display the total cashflow/expenditure/income 
during the stipulated period depending on the financial entry type selected to be listed, as well as the
category with the highest total expenditure/income.

**Format**: `list [expense|income] [/from START_DATE] [/to END_DATE]`

#### List by Type

User can command app to list out only expenses, only incomes or both expenses and incomes.

**Example Usage**:
``` java
//Lists out all expenses and incomes. 
//Displays total cashflow (income - expenditure), and shows categories with the highest total expenditure and income respectively
list

//Lists out all expenses. Displays total expenditure, and shows category with highest total expenditure.
list expense 

//Lists out all incomes. Displays total income, and shows category with highest total income.
list income 
```

#### List by Date

User can command app to only list out financial entries starting from a certain date using the `/from` flag, 
and/or up to a certain date using the `/to` flag.

Total cashflow/expenditure/income displayed will be restricted to the range of dates entered by the user.
Category with highest expenditure/income displayed will also be based on the entered date range.

**Example Usage**:
``` java
//Lists out all expenses and incomes with date equal to or after 03/10/24.
//Displays total cashflow (income - expenditure) during that period, and shows
//categories with the highest total expenditure and income during that period respectively.
list /from 03/10/24

//Lists out all expenses with dates before or equal to 03/10/24.
//Displays total expenditure + category with highest total expenditure during that period.
list expense /to 03/10/24

//Lists out all incomes with dates between 03/10/24 and 01/11/24 inclusive.
//Displays total income + category with highest total income during that period.
list income /from 03/10/24 /to 01/11/24
```

### Set/Edit Budget

User can set a monthly budget when app is initialized and budget is not set, or by using the budget command.
Budget command can also be used to edit budget after initial budget is set.

**Example Usage**:
``` java
// Initial budget setting option 
Would you like to set a budget? (yes/no)
yes
Please set your budget amount:
1000

// Budget modification option to change budget amount
budget
Your current budget is: 1000.0
Would you like to modify your budget? (yes/no)
yes
Please set your budget amount:
2000
```

### Saving Data

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

* Help Menu `help`
* List all transactions `list`
* List all expenses `list expenses`
* List all incomes `list income`
* List by date `list [/from DATE] [/to DATE]`
* Add expense `expense DESCRIPTION /a AMOUNT [/d DATE]`
* Add income `income DESCRIPTION /a AMOUNT [/d DATE]`
* Set budget `budget`
* Delete transaction `delete INDEX`
* Exit program `exit`
