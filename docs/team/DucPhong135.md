# Nguyen Duc Phong - A0276035E - DucPhong135 - Project Portfolio Page (PPP)

## Project Overview: Inventra
Inventra is a CLI-based inventory management system designed for small and medium-sized businesses.
It provides users with tools to manage custom inventory fields, track records and generate insights.
This product was build with the contribution of T11 G4 team members, as part of CS2113 module.

## Summary of Contributions
### Code Contributions:
Please view all my contributions to the project codes [here](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=DucPhong135&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-09-20&tabOpen=true&tabType=authorship&tabAuthor=DucPhong135&tabRepo=AY2425S1-CS2113-T11-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false).

### Enhancements Implemented:
1. Implemented Core Functionalities:
* `view -f`: Enable user to search for record using keyword provided.
* `view`: Maintain the main execution of view command and add exception handling for 'view -a' and 'view <id>'
2. Exception Handling and Assertions:
* Custom exception were build on-top of existing code to handle invalid inputs:
    - InventraExcessArgsException
    - InventraLessArgsException
* Implement ViewCommandTest to check for exception handling of `view` command
* Address bug in PE-D
### Contributions to User Guide (UG)
1. Updated the following `Command` Sections:`view`, `exit`
    - Add command `view -f` to match with project development
    - Update `exit` command for clarification
### Contributions to Developer Guide (DG)
* Help to check for errors in Class Diagram and Sequence Diagrams for DG.
### Contributions to Team-Based Tasks
* Help to suggest new features for the product, include `view -f` and `delete -r`
* Support in writing user stories
* Arrange team meeting
### Summary
In this project `Inventra`, I have implemented some core functionalities,
ensured proper exception handling and data persistence.
  

