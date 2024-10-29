# Terence - Project Portfolio Page

## Overview

WheresMyMoney allows you to keep track of your spending habits and trends with various supporting tools and functionalities.

### Summary of Contributions

Given below are my contributions to the project.

* **New Feature**: Added Storage Capability.
    * What it does: This feature introduces the ability to load and store expense data for a group into a local file.
    * Justification: Storage capability is essential for preserving data between program executions. By storing data in files, users can resume their work and maintain a record of their expenses even after closing the application. This feature enhances the usability and practicality of the application.
    * Highlights: Uses the CSV file format for easy processing with other applications

<div style="page-break-after: always;"></div>

* **General Contributions**: Abstraction of Commands, Exceptions and Logging
    * What it does
        * *Commands Abstraction*: Encapsulates command execution logic into a base class `Command`, providing a standardized interface for all commands in the system.
        * *Exception Abstraction*: Centralizes exception handling logic, allowing for consistent error reporting and graceful error recovery across the application.
        * *Logging Abstraction*: Encapsulation of logging logic for consistent logging across the application.
    * Justification: Abstraction of commands, exceptions, and logging enhances the overall structure and maintainability of the codebase. It promotes separation of concerns, making it easier to manage and extend different aspects of the application independently.

* **Code Contributed**: [RepoSense Link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=hackin7&sort=groupTitle%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other)

* **Documentation**
    * User Guide
        * Updated commands to match v1.0. [#56](https://github.com/AY2425S1-CS2113-W12-3/tp/pull/56)
    * Developer Guide
        * Initial Outline of DG and implementation of Ui, Parser and Command. [#56](https://github.com/AY2425S1-CS2113-W12-3/tp/pull/56)

<div style="page-break-after: always;"></div>

* **Project Management**
    * Maintained issues and managed milestones.

* **Community**
    * PRs reviewed (with non-trivial review comments): [#68](https://github.com/AY2425S1-CS2113-W12-3/tp/pull/68)