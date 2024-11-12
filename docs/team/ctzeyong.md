# Cheng Tze Yong - Project Portfolio Page

## Overview

FinanceBuddy is a software that allows university students to log their daily expenditures
to help manage their budgets. Users can add, delete and edit expenditure logs
into the app as well as list out all their logged transactions.

## Summary of Contributions

### Code Contributed

Click
[here](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=ctzeyong&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=ctzeyong&tabRepo=AY2425S1-CS2113-W14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
to see the RepoSense report of code contributed to this project.

### Enhancements Implemented

**New implementation**: Singleton `Log` class and `LogLevels` enumeration
- <ins>What it does</ins>: Central class that manages logging in the program 
- <ins>Justification</ins>: Single instance of a logging class allows for easier toggling of settings

**New implementation**:`AddEntryCommand` abstract class
- <ins>What it does</ins>: Class that holds common attributes of
`AddExpenseCommand` and `AddIncomeCommand`
- <ins>Justification</ins>: Increases level of abstraction

**New implementation**: `AddIncomeCommand` backbone implementation
- <ins>What it does</ins>: Class that adds `Income` to `financialList`
- <ins>Justification</ins>: Key feature of application

**Enhanced feature**: `SeeAllEntriesCommand` calculation of cashflow
- <ins>What it does</ins>: Calculates and displays net cashflow of entries listed
- <ins>Justification</ins>: Side feature that enhances user experience

**Enhanced feature**: `BudgetLogic` remaining balance feature
- <ins>What it does</ins>: Calculates and displays remaining balance
- <ins>Justification</ins>: Enables warning to user when budget is exceeded

**General contribution**: Enhanced exception handling
- <ins>What it does</ins>: Throws `FinanceBuddyException` with error message
- <ins>Justification</ins>: Errors can be displayed in a manner that
the user understands the warning

### Contributions to the [User Guide](https://ay2425s1-cs2113-w14-3.github.io/tp/UserGuide.html)

Sections contributed: Introduction, Quick Start, Help

### Contributions to the [Developer Guide](https://ay2425s1-cs2113-w14-3.github.io/tp/DeveloperGuide.html)

Sections contributed:
Budgeting, Commands, Adding Entries, Editing Entries, Exceptions and Logging

Contributed all UML diagrams in the sections listed above.

### Github Issues Contributions

Added issues
[#8](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/8),
[#9](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/9),
[#10](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/10),
[#13](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/13),
[#16](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/16),
[#18](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/18),
[#20](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/20),
[#21](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/21),
[#24](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/24),
[#25](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/25),
[#29](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/29),
[#30](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/30),
[#44](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/44),
[#66](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/66),
[#67](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/67),
[#215](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/215),
[#294](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/294),
[#295](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/295),
[#296](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/296),
[#297](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/297),
[#298](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/298),
[#300](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/300)

### Review Contributions

Reviewed PR
[#60](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/60), 
[#71](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/71),
[#85](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/85),
[#87](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/87),
[#92](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/92),
[#96](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/96),
[#98](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/98),
[#112](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/112),
[#119](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/119),
[#124](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/124),
[#154](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/154),
[#170](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/170),
[#177](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/177),
[#181](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/181),
[#187](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/187),
[#199](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/199),
[#283](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/283),
[#292](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/292),
[#302](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/302),
[#303](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/303),
[#307](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/307)