# WheresMyMoney Developer Guide


## Table of Contents
- [Developer Guide](#wheresmymoney-developer-guide)
    - [Table of Contents](#table-of-contents)
    - [Acknowledgements](#acknowledgements)
    - [Design \& Implementation](#design--implementation)
        - [UI and Parser](#ui-and-parser)
    - [User Stories](#user-stories)
    - [Product scope](#product-scope)
        - [Target user profile](#target-user-profile)
        - [Value proposition](#value-proposition)
    - [Non-Functional Requirements](#non-functional-requirements)
    - [Glossary](#glossary)
    - [Instructions for Testing](#instructions-for-testing)
        - [Manual Testing](#manual-testing)
        - [JUnit Testing](#junit-testing)

<div style="page-break-after: always;"></div>

## Acknowledgements

WheresMyMoney uses the following libraries

1. [OpenCSV](https://opencsv.sourceforge.net/) - Used for saving/ loading expenses

WheresMyMoney uses the following tools for development:

1. [JUnit](https://junit.org/junit5/) - Used for testing.
2. [Gradle](https://gradle.org/) - Used for build automation.

## Design & implementation

The UML diagram below provides an overview of the classes and their interactions within the WheresMyMoney application.

<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" contentStyleType="text/css" height="478px" preserveAspectRatio="none" style="width:167px;height:478px;background:#FFFFFF;" version="1.1" viewBox="0 0 167 478" width="167px" zoomAndPan="magnify"><defs/><g><!--class Main--><g id="elem_Main"><rect codeLine="4" fill="#F1F1F1" height="44.6211" id="Main" rx="2.5" ry="2.5" style="stroke:#181818;stroke-width:0.5;" width="36.3447" x="124.5" y="7"/><text fill="#000000" font-family="sans-serif" font-size="14" lengthAdjust="spacing" textLength="30.3447" x="127.5" y="27.1074">Main</text><line style="stroke:#181818;stroke-width:0.5;" x1="125.5" x2="159.8447" y1="35.6211" y2="35.6211"/><line style="stroke:#181818;stroke-width:0.5;" x1="125.5" x2="159.8447" y1="43.6211" y2="43.6211"/></g><!--class Parser--><g id="elem_Parser"><rect codeLine="5" fill="#F1F1F1" height="44.6211" id="Parser" rx="2.5" ry="2.5" style="stroke:#181818;stroke-width:0.5;" width="47.2344" x="7" y="217"/><text fill="#000000" font-family="sans-serif" font-size="14" lengthAdjust="spacing" textLength="41.2344" x="10" y="237.1074">Parser</text><line style="stroke:#181818;stroke-width:0.5;" x1="8" x2="53.2344" y1="245.6211" y2="245.6211"/><line style="stroke:#181818;stroke-width:0.5;" x1="8" x2="53.2344" y1="253.6211" y2="253.6211"/></g><!--class Ui--><g id="elem_Ui"><rect codeLine="6" fill="#F1F1F1" height="44.6211" id="Ui" rx="2.5" ry="2.5" style="stroke:#181818;stroke-width:0.5;" width="19.2207" x="98" y="112"/><text fill="#000000" font-family="sans-serif" font-size="14" lengthAdjust="spacing" textLength="13.2207" x="101" y="132.1074">Ui</text><line style="stroke:#181818;stroke-width:0.5;" x1="99" x2="116.2207" y1="140.6211" y2="140.6211"/><line style="stroke:#181818;stroke-width:0.5;" x1="99" x2="116.2207" y1="148.6211" y2="148.6211"/></g><!--class Expense--><g id="elem_Expense"><rect codeLine="7" fill="#F1F1F1" height="44.6211" id="Expense" rx="2.5" ry="2.5" style="stroke:#181818;stroke-width:0.5;" width="60.4824" x="77.5" y="427"/><text fill="#000000" font-family="sans-serif" font-size="14" lengthAdjust="spacing" textLength="54.4824" x="80.5" y="447.1074">Expense</text><line style="stroke:#181818;stroke-width:0.5;" x1="78.5" x2="136.9824" y1="455.6211" y2="455.6211"/><line style="stroke:#181818;stroke-width:0.5;" x1="78.5" x2="136.9824" y1="463.6211" y2="463.6211"/></g><!--class ExpenseList--><g id="elem_ExpenseList"><rect codeLine="8" fill="#F1F1F1" height="44.6211" id="ExpenseList" rx="2.5" ry="2.5" style="stroke:#181818;stroke-width:0.5;" width="82.2686" x="66.5" y="322"/><text fill="#000000" font-family="sans-serif" font-size="14" lengthAdjust="spacing" textLength="76.2686" x="69.5" y="342.1074">ExpenseList</text><line style="stroke:#181818;stroke-width:0.5;" x1="67.5" x2="147.7686" y1="350.6211" y2="350.6211"/><line style="stroke:#181818;stroke-width:0.5;" x1="67.5" x2="147.7686" y1="358.6211" y2="358.6211"/></g><!--link Main to Ui--><g id="link_Main_Ui"><path codeLine="10" d="M135.08,52.34 C129.14,69.82 122.7815,88.5394 116.8415,106.0094 " fill="none" id="Main-to-Ui" style="stroke:#181818;stroke-width:1.0;"/><polygon fill="#181818" points="114.91,111.69,121.5943,104.4567,116.5196,106.9562,114.0201,101.8814,114.91,111.69" style="stroke:#181818;stroke-width:1.0;"/><text fill="#000000" font-family="sans-serif" font-size="13" lengthAdjust="spacing" textLength="7.23" x="125.5415" y="73.6932">1</text><text fill="#000000" font-family="sans-serif" font-size="13" lengthAdjust="spacing" textLength="7.23" x="108.9131" y="100.781">1</text></g><!--link Main to ExpenseList--><g id="link_Main_ExpenseList"><path codeLine="11" d="M143.59,52.09 C145.23,93.27 146.89,185.74 133.5,262 C129.85,282.77 124.3472,299.9585 118.3072,316.1485 " fill="none" id="Main-to-ExpenseList" style="stroke:#181818;stroke-width:1.0;"/><polygon fill="#181818" points="116.21,321.77,123.1035,314.7358,117.9577,317.0854,115.6081,311.9395,116.21,321.77" style="stroke:#181818;stroke-width:1.0;"/><text fill="#000000" font-family="sans-serif" font-size="13" lengthAdjust="spacing" textLength="7.23" x="135.9886" y="73.7782">1</text><text fill="#000000" font-family="sans-serif" font-size="13" lengthAdjust="spacing" textLength="7.23" x="110.5401" y="310.8815">1</text></g><!--link Ui to Parser--><g id="link_Ui_Parser"><path codeLine="12" d="M97.75,148.55 C84.88,165.76 65.6407,191.483 50.2007,212.153 " fill="none" id="Ui-to-Parser" style="stroke:#181818;stroke-width:1.0;"/><polygon fill="#181818" points="46.61,216.96,55.2007,212.1433,49.6022,212.9542,48.7914,207.3558,46.61,216.96" style="stroke:#181818;stroke-width:1.0;"/><text fill="#000000" font-family="sans-serif" font-size="13" lengthAdjust="spacing" textLength="7.23" x="82.6372" y="149.1829">1</text><text fill="#000000" font-family="sans-serif" font-size="13" lengthAdjust="spacing" textLength="7.23" x="42.9983" y="205.7363">1</text></g><!--link ExpenseList to Expense--><g id="link_ExpenseList_Expense"><path codeLine="13" d="M107.5,367.34 C107.5,384.82 107.5,403.22 107.5,420.69 " fill="none" id="ExpenseList-to-Expense" style="stroke:#181818;stroke-width:1.0;"/><polygon fill="#181818" points="107.5,426.69,111.5,417.69,107.5,421.69,103.5,417.69,107.5,426.69" style="stroke:#181818;stroke-width:1.0;"/><text fill="#000000" font-family="sans-serif" font-size="13" lengthAdjust="spacing" textLength="7.23" x="99.6862" y="388.6932">1</text><text fill="#000000" font-family="sans-serif" font-size="13" lengthAdjust="spacing" textLength="5.0591" x="101.8051" y="415.781">*</text></g><!--link Ui to ExpenseList--><g id="link_Ui_ExpenseList"><path codeLine="15" d="M107.5,157.37 C107.5,197.42 107.5,276.09 107.5,315.93 " fill="none" id="Ui-to-ExpenseList" style="stroke:#181818;stroke-width:1.0;stroke-dasharray:7.0,7.0;"/><polygon fill="#181818" points="107.5,321.93,111.5,312.93,107.5,316.93,103.5,312.93,107.5,321.93" style="stroke:#181818;stroke-width:1.0;"/></g><!--link Parser to ExpenseList--><g id="link_Parser_ExpenseList"><path codeLine="16" d="M46.83,262.34 C59.9,279.82 74.5375,299.4144 87.5975,316.8844 " fill="none" id="Parser-to-ExpenseList" style="stroke:#181818;stroke-width:1.0;stroke-dasharray:7.0,7.0;"/><polygon fill="#181818" points="91.19,321.69,89.005,312.0866,88.1962,317.6853,82.5975,316.8766,91.19,321.69" style="stroke:#181818;stroke-width:1.0;"/></g><!--SRC=[AyxEp2j8B4hCLKZEIImkTYmfASfCAYr9zKpEpmlEh4fLCE3Ao4n9LKZEB4hEIUNY0gjIy4tCp8Co0nABYbEBe9pGJ2Z3jQ8WDQyu5PNdarbSmiK5qgkWPAYae9kYQmTc07LX2YBh2Sr4bOFQYQG4ILuBhfSBf54tEYMnE2Cr9HOeXsmcn0nSqW00]--></g></svg>

Design and Implementation has been broken down into various sections, each tagged for ease of reference:

- [UI and Parser](#ui-and-parser)

### UI and Parser

<u>Overview</u>

The Ui and Parser functionalities act as the interface between the user and the application. They are managed by the UI and InputHandler classes, respectively, with UI handling displaying messages to the user and reading user input, while InputHandler is responsible for parsing user input and returning the corresponding Command object.

<u>Class Structure</u>

The Ui class has the following static attributes:

SEPARATOR: A constant string representing a straight line to be printed to the console.
scanner: A Scanner object used for reading from System.in I/O.
The InputHandler class itself does not have any attributes.

<u>Methods</u>

The `Ui` class has the following key methods:

| Method              | Description                                                              |
|---------------------|--------------------------------------------------------------------------|
| `commandEntryLoop`  | Reads User Input, parses it using the Parser, and handles any Exceptions |

The Parser class has the following key method:

| Method            | Description                                                                           |
|-------------------|---------------------------------------------------------------------------------------|
| `commandMatching` | Matches the command to the corresponding action and executes the corresponding action |

<u>Design Considerations</u>

UI class is used as part of exception handling for displaying of error messages to the user for feedback.


## Product scope

### Target user profile

Our target user profile is frugal and tech-savvy university students.

### Value proposition

The application will track how much a user is spending and what they are spending it on. 
The application can provide summaries and statistical insights to spending habits, optimised for people who prefer a CLI.

## User Stories

| Version | As a ...    | I want to ...                                                                  | So that I can ...                                                  |
|---------|-------------|--------------------------------------------------------------------------------|--------------------------------------------------------------------|
| v1.0    | user        | add expenses                                                                   | track how much money I have spent so far                           |
| v1.0    | user        | delete expenses                                                                | clear wrong expenses to ensure expense tracking is accurate        |
| v1.0    | user        | edit expenses                                                                  | correct inaccurate expenses to ensure expense tracking is accurate |
| v1.0    | user        | list expenses                                                                  | track my spending                                                  |
| v1.0    | new user    | see usage instructions                                                         | refer to them when I forget how to use the application             |
| v2.0    | user        | save and load my expenses from a file                                          | retain memory of past expenses from past runs of the program       |
| v2.0    | frugal user | set spending limits for each category and month                                | control my spending                                                |
| v2.0    | frugal user | be alerted when I exceed spending limits for each category and month           | control my spending                                                |
| v2.0    | user        | visualise my spending in the form of graphs                                    | better conceptualise and understand spending trends and patterns   |
| v2.0    | user        | detailed statistical information about my spending (such as mean, median etc.) | better quantify and understand spending trends and patterns        |


## Non-Functional Requirements

1. Technical Requirements: Any mainstream OS, i.e. Windows, macOS or Linux, with Java 17 installed.
2. Project Scope Constraints: The application should only be used for tracking. It is not meant to be involved in any form of monetary transaction.
3. Project Scope Constraints: Data storage is only to be performed locally.
4. Quality Requirements: The application should be able to be used effectively by a novice with little experience with CLIs.

## Glossary

* *Expense* - Payment made for various purposes. It has a price, category and description.

## Instructions for Testing

## Manual Testing

View the [User Guide](UserGuide.md) for the full list of UI commands and their related use case and expected outcomes.

## JUnit Testing

JUnit tests are written in the subdirectory `test` and serve to test key methods part of the application.