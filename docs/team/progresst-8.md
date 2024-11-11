# Kylan - Project Portfolio Page

## Overview

WheresMyMoney is an expense tracking application with additional features such as support for recurring expenses, spending limit alerts and spending visualisation.

### Summary of Contributions

Given below are my contributions to the project.

- **Code Contributions**: [RepoSense Link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=AY2425S1-CS2113-T10-2%2Ftp%5Bmaster%5D&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-09-20&tabOpen=true&tabType=authorship&tabAuthor=Progresst-8&tabRepo=AY2425S1-CS2113-W12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false&viewRepoTags=true)
  - Code Written
    - Primary contributor to almost all the code in:
      - `ExpenseList` and `Expense` classes
        - Utility methods such as constructors, getters and setters
      - `ExpenseListTest` and `ExpenseTest` classes' JUnit tests
        - Tests for the aforementioned utility methods
      - `DateUtils` class
      - `CategoryTracker`, `CategoryData`, `CategoryFilter`, `CategoryFacade` and `CategoryStorage` classes
      - `CategoryTracker` and `CategoryData` classes' JUnit tests
      - Assertions and guard clauses for `Expense` and `CategoryData` setters
        - Checks for: Null, Blank, Non-Positive
    - Partial contributor to some of the code in:
      - `ExpenseList` and `Expense` classes
        - CRUD methods such as add, edit and delete
      - `ExpenseListTest` and `ExpenseTest` classes' JUnit tests
        - Updated tests for the aforementioned CRUD methods
      - `AddCommand`, `EditCommand`, `DeleteCommand` classes 
        - Integrate ExpenseList CRUD methods
        - Integrate my category CRUD methods 
      - `SetCommand` classes 
        - Integrate my category set methods
- **Documentation Contributions**
  - User Guide 
    - Created section about setting spending limits for a category 
    - Add FAQs about:
      - CRUD operations for spending limits
      - Total expense resetting every month
      - Floats used as the type for prices
  - Developer Guide
    - Created `Expense` and `ExpenseList` section
    - Created category package section
      - Includes details about classes `CategoryTracker`, `CategoryData`, `CategoryFilter`, `CategoryFacade` and `CategoryStorage`
    - Created `DateUtils` section
    - Created UML Diagrams:
      - 2 Class UMLs:
        - One for `Expense` and `ExpenseList`
        - Another for classes in the category package
      - 6 Sequence UMLs for key methods in `CategoryFacade` 
- **Project Management**
  - Resolved issues found by QA testers during PE-D.
  - Maintained code quality
- **Review & Mentoring Contributions:** 
  - Perform product testing before release of v2.0 and v2.1.
  - Provide suggestions for designing various components of the software.
  - Provide suggestions for how architecture diagram should look like
- **Community**
  - Reported bugs and suggestions for other teams in the class
