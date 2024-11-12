# Terence - Project Portfolio Page

## Overview

WheresMyMoney allows you to keep track of your spending habits and trends with various supporting tools and functionalities.

### Summary of Contributions

Given below are my contributions to the project.

* **New Feature**: Added Storage Capability.
    * What it does: This feature introduces the ability to load and store program data for a group into local files.
    * Justification: By storing data in files, users can resume their work and maintain a record of their expenses even after closing the application. This feature enhances the usability and practicality of the application.
    * Highlights: 
        * Uses the CSV file format for easy processing with other applications. CsvUtils are abstracted out for ease of use.
        * Saving/ loading is implemented in the related data classes, increasing cohesion and reducing bugs (eg. On future updates to the classes, updating saving is easier)  

* **General Contributions**: Abstraction of Ui, Parser, Commands, ArgumentsMap
    * What it does:
        * Ui handles the User interface of the application (Input, Output).
        * Parser handles parsing of commands, ArgumentsMap handles mapping of arguments to their values with appropriate validation.
        * Command provides a standardized interface for all commands in the system.
    * Justification:
        * This abstraction allows for standardisation and consistency in various aspects such as error messages, command format, etc.
        * It promotes separation of concerns, making it easier to manage and extend different aspects of the application independently.

* **General Contributions**: Abstraction of Exceptions and Logging
    * What it does: Centralizes exception handling logic, allowing for consistent error reporting and graceful error recovery across the application. Centralizes logging logic for consistent logging across the application.
    * Justification: Various classes of Exceptions enhances the overall structure and maintainability of the codebase.

* **Code Contributed**: [RepoSense Link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=hackin7&sort=groupTitle%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other)

* **Documentation**
    * User Guide
        * Updated commands to match v1.0. [#56](https://github.com/AY2425S1-CS2113-W12-3/tp/pull/56)
        * Update user guide about arguments. [#75](https://github.com/AY2425S1-CS2113-W12-3/tp/pull/75)
        * Added Storage Section. [#96](https://github.com/AY2425S1-CS2113-W12-3/tp/pull/96)
        * Added Expected output and Reorganised Storage Section. [#169](https://github.com/AY2425S1-CS2113-W12-3/tp/pull/169)
    * Developer Guide
        * Initial Outline of DG and implementation of Ui, Parser and Command. [#56](https://github.com/AY2425S1-CS2113-W12-3/tp/pull/56)
        * Updated Ui, Parser, Command, Exceptions, Logging with the Refactoring [#67](https://github.com/AY2425S1-CS2113-W12-3/tp/pull/67)
          * Added Class Diagrams for Command, Sequence Diagram for Command Execution Sequence.
        * Add Parser Design Considerations [#75](https://github.com/AY2425S1-CS2113-W12-3/tp/pull/75)
        * Update with Storage refactoring and improvements. [#96](https://github.com/AY2425S1-CS2113-W12-3/tp/pull/96)
        * Update with ArgumentsMap [#181](https://github.com/AY2425S1-CS2113-W12-3/tp/pull/181)

* **Project Management**
    * Created 29+ issues, maintained issues and managed milestones.
    * In charge of issues assignee allocation.
    * Conducted triaging of bugs during PE-D.

* **Community**
    * PRs reviewed (with non-trivial review comments): [#94](https://github.com/AY2425S1-CS2113-W12-3/tp/pull/94),[#77](https://github.com/AY2425S1-CS2113-W12-3/tp/pull/77), [#64](https://github.com/AY2425S1-CS2113-W12-3/tp/pull/64), [#40](https://github.com/AY2425S1-CS2113-W12-3/tp/pull/40), [#38](https://github.com/AY2425S1-CS2113-W12-3/tp/pull/38)
    * Reported bugs and suggestions for other teams in the class (example: [1](https://github.com/Hackin7/ped/issues/1), [3](https://github.com/Hackin7/ped/issues/3))