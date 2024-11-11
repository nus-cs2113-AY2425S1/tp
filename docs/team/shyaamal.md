# Shyaamal Dwivedi - Project Portfolio Page

## Overview

WheresMyMoney is an expense tracking application with additional features such as support for recurring expenses, 
spending limit alerts and spending visualisation.

## Summary of Contributions

Given below are my contributions to the project.

* **New Feature: List command**
    * What it does: This feature introduces the ability to list the price, description, category and date added for 
  added expenses, filtered according to category and date added.
    * Justification: This is to allow the user to view added expenses. This is part of the basic CRUD functionality

* **New Feature: Stats command**
    * What it does: This feature gets the highest and lowest expenses, total expenses and mean price of added expenses, 
  filtered according to their category and date added.
    * Justification: This is to inform the user about their spending patterns in different categories and through
    * different time periods.

* **New Feature: Method-specific help commands**
  * What it does: This feature introduces the ability to display specific help commands according to the method
  specified by the user.
  * Justification: This is to allow the user to view a help command only for the method it is needed for.

* **Testing:**
    * Added appropriate JUnit tests for list command
* **Documentation:**
    - User Guide: Added sections detailing list command, stats command and help command; kept command summary updated
  with every new feature that was added.
    - Javadoc: Wrote Javadoc comments for most functions in `commands`.
