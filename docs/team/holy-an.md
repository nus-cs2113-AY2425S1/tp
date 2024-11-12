# Anderson Lim - Project Portfolio Page

## Overview
FindOurSEP is a Command Line Interface (CLI) tool designed for an admin handling the allocation of Student Exchange
Program (SEP) locations for Computer Engineering (CEG) students at NUS. The app allows the administrator to efficiently
manage the allocation process using automated workflows and data-driven decision-making.

### Summary of Contributions
[RepoSense (tP Code Dashboard)](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=holy-an&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

### v1.0 Enhancements: `Exception` class, `Exception` helper methods
- `SEPException` Development
  - Developed the Exception classes and methods for the team project to throw whenever needed.
  - Also helped to test, as well as implement edge cases needed to be handled in methods for v1.0
  - Added helper methods to validate user inputs, such as `validateStudentId()` and others.

### v2.0 Enhancements: `saveCSV`,`find` and `filter` command
- `saveToCSV` Implementation
  - Designed and implemented `saveToCSV()` method to allow users to save their results via a CSV file.

- `find` Command Implementation
  - Designed and implemented the FindCommand class to allow users to search for student IDs,
    outputting either a list or a report.
  
- `filter` Command Implementation
  - Designed and implemented the FilterCommand class to allow users to filter for student IDs, GPAs,
    or allocation status, outputting either a list or a report.
  - Created more validation helper methods such as `validateFindFilterFormat()`.

### User Guide Contributions:
- Along with the other group members, we discussed the structure of our User Guide.

- Added `find` and `filter` command.

### Developer Guide Contributions:
- Along with the other group members, we drew the main high-level diagrams needed in our Developer guide.

- Added `find` and `filter` sequence and class diagrams, as well as any methods inside it, such as
  `printStudentList()` and `generateReport`.

- Explained the `find` and `filter` commands.

- Added `saveAllocationResults()` sequence diagram.

- Added `FindOurSEP` main class component.