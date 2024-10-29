# User Guide

## Introduction

SpendSwift is a simple budgeting tool designed for budget-conscious users. With intuitive text commands, you can effortlessly track expenses and manage your finances without the complexity of traditional tools.

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 17 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features
### Command Format Notes
1. Commands are case-insensitive: Command words can be typed in any combination of upper or lower case.

   Example: `add-expense` and `ADD-EXPENSE` are both valid.
2. Words in UPPER_CASE are parameters to be supplied by the user.

   Example: In `add n/NAME`, `NAME` is a parameter, which can be used as `add n/Udon`.

3. All parameters are required unless specified: Every parameter must be supplied unless marked optional (e.g., square brackets [ ]).

   Example: `add-expense n/Coffee a/5.50` is valid because `c/CATEGORY` is optional, while `add-expense` with no parameters will fail.

4. Order of parameters: Parameters can be provided in any order, unless otherwise specified.

   Example: `add-expense n/Coffee a/5.50 c/Food` is equivalent to `add-expense a/5.50 n/Coffee c/Food`.

5. `INDEX`: Refers to the number corresponding to an expense in the list of expenses displayed. Always an integer.

   Example: `delete-expense 2` deletes the second expense listed in the expense list.

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
