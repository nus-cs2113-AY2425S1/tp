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
to log their daily expenditures to help manage their budgets. Users can
add, delete and edit income and expenditure logs into the app, as well as
list out all their logged transactions. Users are also able to set a monthly budget
for themselves in the app, and the app will notify them if
they have exceeded, or are close to exceeding their budget.

The current progress of the logging will be saved after each successful command.

## Quick Start

{Give steps to get started quickly}

1. Ensure that Java 17 or above is installed.
2. Download the latest version of `FinanceBuddy` from [here](https://github.com/AY2425S1-CS2113-W14-3/tp/releases).
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

#### List by Type

#### List by Date

### Set/Edit Budget

### Saving Data

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
