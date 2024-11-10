# Huynh Le Dan Linh - Project Portfolio Page

## Project Overview: uNivUSaver

uNivUSaver is a CLI-based software designed to help students develop better money management habits, aiming to prevent users from running out of money before the end of the month. It is written in Java.

### Summary of Contributions

#### Code contributed
* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=DanLinhHuynh&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-09-20)

#### MILESTONE 1.0
* **New component**: Added an abstract Command class and test [PR#26](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/26).
  * What it does: Provides common fields that ensure consistency across all concrete command classes that extend Command, provides a template for commands by the abstract methods (e.g. execute(), etc.), the class enables developers to add new commands by simply extending the base class and implementing the specific behavior for each command, provides some common methods (e.g. isArgumentsValid(), setArguments(), getArguments(), etc.) across child classes.
  * Highlights: The Command class structure defines several static variables (e.g. COMMAND_MANDATORY_KEYWORDS, COMMAND_EXTRA_KEYWORDS) that help factor flexible parsing and enable the developer to change command keywords with minimal modification to the code.

* **New component**: Added the command parser and test [PR#28](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/28).
  * What it does: Allows the system to parse the input command to correct `Command` object, and extract the command arguments into a map that the `Command` object can retrieve.
  * Highlights: This feature improves the code quality and scalability so that the developer can easily add a new command without the need to add a new argument extraction method.

* **New component**: Added the main class [PR#29](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/29).
  * What it does: Provides an entry point for the user to access the uNivUSaver's features.

* **New feature**: Added `help` command and test [PR#30](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/30).
  * What it does: Shows existing commands in the system.

* **New feature**: Added `view-category` command and test [PR#36](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/36).
  * What it does: Shows existing categories in the current budget.

* **New component**: Added DateTime utilities [PR#38](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/38).
  * What it does:  Provides a standard method for DateTime parsing and error handling.

* **New feature**: Added `view-expense` command and test [PR#48](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/48).
  * What it does:  Shows existing expenses with optional date and category filters in the current transaction list.

* **New feature**: Added `view-income` command and test [PR#49](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/49).
  * What it does:  Shows existing income with optional date filters in the current transaction list.

* **New feature**: Added `history` command and test [PR#50](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/50).
  * What it does:  Shows the transaction list with optional date filters.

* **New feature**: Added `bye` command and test [PR#52](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/52).
  * What it does:  Enables the user to leave the session peacefully.

* **Testing**:  Added system test [PR#53](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/53).
  * What it does: Tests the existing features.

#### MILESTONE 2.0  
* **Code quality**: Refactoring code (split nested commands into different functions) as per the SRP [PR#81](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/81).

* **Code quality**: Split UI from Main to another class to enhance abstraction [PR#82](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/82).

* **New component**: Added messages utilities [PR#113](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/113).
  * What it does: Provides standard, static messages to respond to different events, and reduces the burden of code refactoring when changing message content.

* **Enhancement for existing features**: Added follow-up question for delete category [PR#126](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/126).
  * Reasoning: Upon deleting a category, the expenses in that category should either be re-categorized/cleaned up in the category field.

* **Code quality**: Added custom exception classes for better debugging and exception handling [PR#130](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/130).

* **Enhancement for existing features**: Update search command for partial match [PR#154](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/154).
  * What it does: The user can search for transactions with partially matched keywords.

* **Testing**:  Updated system test [PR#139](https://github.com/AY2425S1-CS2113-W10-4/tp/pull/139).
  * What it does: Tests the existing features.

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
