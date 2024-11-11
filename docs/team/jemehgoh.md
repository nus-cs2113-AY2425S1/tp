# Goh Eng Hui, Jeremy - Project Portfolio Page

## Overview
EventManagerCLI is a desktop CLI application meant for managing small-scale events organised by one person. It is written in Java, and has around 4kLoC.

### Summary of Contributions
* **New feature**: Added a parser for user input.
  * What it does: parses user input into executable commands.
  * Justification: This feature improves the product significantly as it allows the user to enter executable commands.
  * Highlights: This feature was used and built upon by others as additional commands were implemented.

* **New feature**: Added the ability to mark/unmark events as done.
  * What it does: allows the user to mark events as done or not done.
  * Justification: This feature improves the product significantly as it allows the user to flag out completed or past events as done.
  * Highlights: This feature required the updating of multiple existing commands and methods to work.

* **New feature**: Added the ability to mark/unmark participants as present.
  * What it does: allows the user to mark event participants as present or absent.
  * Justification: This feature improves the product significantly as it allows the user to track attendance for his events.
  * Highlights: This feature required the creation of a new class for event participants, and significant changes were required to existing methods that modified the participant list.

* **New feature**: Added a list of items for each event.
  * What it does: allows the user to add, remove, and mark items in a list for a specified event.
  * Justification: This feature improves the product significantly as it allows the user to track what materials he needs for an event.
  * Highlights: This feature required the modification of multiple existing commands, and changes had to be made in several areas (like for the command parsing) to enable this feature.

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-09-20&tabOpen=true&tabType=authorship&tabAuthor=jemehgoh&tabRepo=AY2425S1-CS2113-W13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
* **Project management**:
  * Managed release `v1.0` (1 release) on GitHub

* **Enhancements to existing features**:
  * Added error handling for the `view` command (Pull request [#61](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/61))
  * Fixed duplicate entry bugs for the `EventList` and `Event` commands (Pull requests [#120](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/120),[#245](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/245))
  * Refactored parser methods to improve SLAP and reduce duplicate code (Pull requests [#163](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/163), [#253](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/253))

* **Documentation**
  * User Guide
    * Added documentation for `menu`, `list`, `exit`, `add`, `remove`, `view` and `mark` commands. (Pull requests [#63](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/63), [#104](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/104))
  * Developer Guide
    * Added design details for all components. (Pull requests [#109](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/109), [#137](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/137))
    * Added implementation details for command parsing and the `mark` feature. (Pull requests [#109](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/109), [#137](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/137))
    * Added instructions for manual testing. (Pull request [#158](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/158) ) 
* **Community**
  * PRs reviewed (with non-trivial review comments): [#47](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/47), [#53](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/53), [#115](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/115), [#131](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/131)
