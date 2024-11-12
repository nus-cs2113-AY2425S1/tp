# Alfred Goh - Project Portfolio Page

## Overview

---

BudgetBuddy is a desktop application for managing finance and anything related to it, optimized for
use via a Command Line Interface (CLI). It is written in Java and has around 8kLoC.

## Summary of Contributions

---

### Code Contributed

Code Contribution to the project: [Reposense Page](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-09-20&tabOpen=true&tabType=authorship&tabAuthor=Alfred-Goh02&tabRepo=AY2425S1-CS2113-W10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Features Implemented
Here is the list of feature implemented:

#### 1. Storage Feature

- **What It Does**: User data entered during each session is now automatically saved upon exiting the application and loaded each time the application starts.
- **Justification**: Users benefit from having their data stored automatically, avoiding the need to re-enter information each time they open the application.

#### 2. Display Monthly Expenses for a Specific Year

- **What It Does**: Summarizes and displays users' monthly expenses over the course of a year on an XY-chart. The X-axis represents each month, and the Y-axis shows the total expenses for each month.
- **Justification**: The chart provides users with an accessible, visual comparison of their monthly expenses, allowing them to make more informed spending decisions.

#### 3. Display Expenses for a Month with Categories

- **What It Does**: Shows a breakdown of users' expenses for a specific month using a pie chart. Each slice of the pie chart represents a category, with the percentage of each slice shown on the chart and its actual value displayed in the legend.
- **Justification**: This feature enables users to easily compare their spending across different categories for a given month, helping them identify areas for improved financial management.

---

### Enhancements Implemented

#### 1. Unit Tests Across Storage and Expense-Related Classes
- **Input Validation**: Test cases validate inputs and ensure that error messages are returned for invalid entries.
- **Boundary and Edge Case Tests**: Test cases check for negative values, null values, and other edge cases to ensure proper handling.
- **Error Handling**: Test cases confirm that appropriate error messages are displayed when specific errors occur.
- **Data Storage and Loading**: Test cases verify that the save file is created correctly and that data is properly saved and loaded.

#### 2. Improved Input Validation for Graph Commands
- **Details**: Ensures that different input errors in graph commands yield specific error messages relevant to each type of error.
- **Impact**: This improvement allows users to identify input mistakes immediately and correct them when re-entering commands.

---

### Contributions to User Guide

- **Documented Sections:**
  - `Display Monthly Expenses Chart`
  - `Display Expenses for the Month with Categories Chart`
  - Command Summary

**Relevant Pull Request:** [PR #138](https://github.com/AY2425S1-CS2113-W10-1/tp/pull/138/files) 

---

### Contributions to Developer Guide

#### Documented Sections
- **Documentation in Section 3**:
  - Section 3.1 `Architecture`
  - Section 3.2. `Parser Class`
  - Section 3.3. `UI Class`
  - Section 3.4 `Command Class`
  - Section 3.5 `Validator Classes`

- **Documentation in Section 4**:
  - Section 4.1.5 `Display Monthly Expenses`
  - Section 4.1.6 `Display Expenses for the Month with Categories`

- **Documentation in Section 5**:
  - Section 5.5.1.1 `Launch`
  - Section 5.5.2.7 `Display Monthly Expenses Chart`
  - Section 5.5.2.14 `Display Expenses for the Month with Categories`
  - Section 5.5.2.15 `Exit BudgetBuddy`

#### UML Sequence Diagrams
- Section 3.1: `Architecture`
- Section 3.5: `Validator Classes`
- Section 4.1.5: `Display Monthly Expenses`
- Section 4.1.6: `Display Expenses for the Month with Categories`

**Relevant Pull Request:** [PR #89](https://github.com/AY2425S1-CS2113-W10-1/tp/pull/89/files)

---

### Contributions to Team-Based Tasks
- **Release Management:** Managed the `v1.0` release.
- **UG and DG Updates:**
  - Added Table of Contents to both the User Guide and Developer Guide.
  - Added a FAQ section to the User Guide.
  - Reformatted the User Guide and Developer Guide with more specific subsections.
- **Useful Libraries:**
  - Incorporated XChart into Gradle dependencies, ensuring all dependencies are correctly installed.
  - Utilized JFrame to prevent closing XChart windows from ending the application.
  - Ensured XChart libraries are correctly built and included in the JAR file.

---

### Contributions Beyond the Project Team
- **Bug Finding and Suggestions:** [Bug Report](https://github.com/Alfred-Goh02/ped/issues)
- **PR Review for Other Teams:** [Pull Request](https://github.com/nus-cs2113-AY2425S1/tp/pull/2/files/b7aac5a22c63db81288d14b15b5d4cb9ecf65418)
