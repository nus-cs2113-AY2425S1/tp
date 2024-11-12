# Isaac Saw - Project Portfolio Page

## Overview
FindOurSEP is a Command Line Interface (CLI) tool designed for an admin handling the allocation of Student Exchange
Program (SEP) locations for Computer Engineering (CEG) students at NUS. The app allows the administrator to efficiently
manage the allocation process using automated workflows and data-driven decision-making.


## Summary of contributions
[RepoSense (tP Code Dashboard)](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=isaacsaw25&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

### v1.0 Enhancements: `UI` Class, `PrintStudentList` Method Design Improvement
- User Interface (`UI`) Development
    - Developed the UI class to obtain user inputs and output responses from the program. Adopt a facade pattern
      between the logical backend elements (commands, student allocation logic, etc.) and the user interface. Created a
      enum `Messages`.

- `PrintStudentList()` Method Design Improvement
    - Identified and fixed a design flaw in the `printStudentList()` method that caused strange order of method calls 
  between classes. 

### v2.0 Enhancements: `Revert` command
- Revert Command Implementation
    - Designed and implemented the RevertCommand class to allow for undoing allocations,
      resetting each student’s allocation status and university index. Integrated logging and
      assertions to ensure system reliability. 

### User Guide Contributions:

### Developer Guide Contributions:
