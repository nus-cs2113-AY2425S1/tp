# Huynh Le Dan Linh - Project Portfolio Page

## Project Overview: uNivUSaver

uNivUSaver is a CLI-based software designed to help students develop better money management habits, aiming to prevent users from running out of money before the end of the month. It is written in Java.

### Summary of Contributions

#### Code contributed
* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=DanLinhHuynh&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-09-20)

#### MILESTONE 1.0
* **New component**: Added an abstract Command class to provide a template for commands and test [PR#26](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/26).
* **New component**: Added the command parser and test to parse the input command [PR#28](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/28).
  * Highlights: This feature improves the code quality and scalability so that the developer can easily add a new command without the need to add a new argument extraction method.

* **New component**: Added the main class [PR#29](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/29).
* **New feature**: Added `help` command and test [PR#30](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/30).
* **New feature**: Added `view-category` command and test [PR#36](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/36).
* **New component**: Added DateTime utilities [PR#38](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/38).
* **New feature**: Added `view-expense` command and test [PR#48](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/48).
* **New feature**: Added `view-income` command and test [PR#49](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/49).
* **New feature**: Added `history` command and test [PR#50](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/50).
* **New feature**: Added `bye` command and test [PR#52](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/52).
* **Testing**:  Added system test [PR#53](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/53).

#### MILESTONE 2.0  
* **Code quality**: Refactoring code (split nested commands into different functions) as per the SRP [PR#81](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/81).
* **Code quality**: Split UI from Main to another class to enhance abstraction [PR#82](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/82).
* **New component**: Added messages utilities to provide standard, static messages to respond to different events [PR#113](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/113).
* **Enhancement for existing features**: Added follow-up question for delete category [PR#126](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/126).
* **Code quality**: Added custom exception classes for better debugging and exception handling [PR#130](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/130).
* **Enhancement for existing features**: Update search command for partial match [PR#154](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/154).
* **Testing**:  Updated system test [PR#139](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/139).

#### MILESTONE 2.1
* **Debug**: Fixed indexing bugs of TransactionList to reduce confusion [PR#203](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/203).
* **Debug**: Added ignore case comparing between categories [PR#203](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/203).
* **Enhancement for existing features**: Handle invalid start date - end date period of viewing history [PR#203](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/203).  
  
#### Documentation
* **User Guide**:
  * Added documentation for the features in v1.0
* **Developer Guide**:
  * Added implementation details of the `Parser` and `Command` class.

#### Contributions to Team-based Tasks

#### Community
* **Review/Mentoring Contributions**:

* **Contributions Beyond the Project Team**:
