---
title: Ajay Shanker
parent: About Us
# nav_order: 5
nav_enabled: false
---

# Ajay Shanker's Project Portfolio Page

## Project: MediTask

MediTask is a desktop application designed to help nurses efficiently manage and track their daily tasks via a Command Line Interface (CLI). This CLI tool streamlines task management, allowing nurses to quickly organize, monitor, and complete tasks, ensuring no important steps are missed.

---

Given below are my contributions to the project.

#### **New Feature:** `Storage` Functionality
  - **What it does**: Provides data persistence for patients and tasks information, allowing the system to save and load data from disk with use of [Jackson Library](https://github.com/FasterXML/jackson) to improve scalability and reliability in mind.
  - **Justification**: Enables nurses to store patient and task data across sessions, ensuring data integrity and continuity.
  - **Highlights**: Implemented `Storage` classes for patients and tasks, including methods for reading and writing data to disk. The system supports automatic data loading on startup and error handling for file I/O operations, ensuring robust data management.

#### **New Feature: `Json Utility`**
  - **What it does**: Coverts `Hospital` object <-> JSON for seamless data persistence that follows the API formate.
  - **Justification**: This will allow the system to save and load data from disk in JSON format.

#### **New Feature: `Storage Backup`**
  - **What it does**: Provides a backup mechanism for the system's data, allowing nurses to restore data in case of accidental deletion or corruption without overwritting important data.
  - **Justification**: Ensures data integrity and reliability by providing a safety net for critical information.

#### **New Feature: `Storage Deserialization`**
  - **What it does**: Ensure no duplication and invalid data is loaded into the system.
  - **Justification**: Prevents data corruption and ensures data consistency by validating loaded data before updating the system.
  - **Highlights**: Implemented data validation checks during deserialization to prevent duplication and invalid data from being loaded into the system. Use Jackson Library to deserialize with buildin `DeserializationFeature` like `FAIL_ON_UNKNOWN_PROPERTIES` to ensure data integrity.

#### **New Feature: State Management with `StateManager` Class**
  - **What it does**: Centralizes state management for the system, allowing for easy state transitions and command execution based on the current state.
  - **Justification**: Simplifies command execution and improves system organization by enforcing state-dependent operations.

#### **New Feature: Added `HelpCommand` & `ExitCommand`**
  - **What it does**: Provides a help command to display available commands and their usage, and an exit command to gracefully terminate the application.
  - **Justification**: Enhances user experience by providing guidance on command usage and ensuring a smooth application exit process.

#### **New Feature: `Colors` class**
  - **What it does**: Adds color-coded output to the CLI for improved readability and visual distinction between different types of information.
  - **Justification**: Enhances user experience by making the CLI output more visually appealing and easier to interpret.

#### **New Feature: `Messages` class**
  - **What it does**: Centralizes system messages improving maintainability.
  - **Justification**: Ensures consistent messaging across the application, making it easier to update and manage system messages.

#### **Code contributed**: [Code Dashboard](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=ajay&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=NCF3535&tabRepo=AY2425S1-CS2113-T11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

#### **Project management**:
  - Encouraged team members to adhere to coding standards and best practices, ensuring code quality and maintainability.
  - Encouraged the use of GitHub features like issues, pull requests, and project boards to streamline development processes and improve collaboration.
  - Enhancements to existing features:
    - Improved CLI UI by adding color-coded output for better readability and visual distinction between different types of information.
    - Improved Project website by adding a UI preview image and jekyll theme that elevate reading exprience.

#### **Team Based Tasks**:
  - Setup GitHub Organization and Repository for the project.
  - Setup Issue Tracker and Project Board for the project.
  - Help out in maintaining the project website.
  - Help out in maintaining issue tracker.
  - Intergrate ImgBot and CodeCov for the project.

#### **Documentation**:

##### **README**:
  - Added a UI preview image and updated the project description for better understanding.
  - Linked the User Guide and Developer Guide for easy access to detailed information.

##### **User Guide**:
  - Improve page format ([PR #97](https://github.com/AY2425S1-CS2113-T11-1/tp/pull/97)).

##### **Developer Guide**:
  - Added Architecture Diagram to illustrate MediTask's overall project structure ([PR #97](https://github.com/AY2425S1-CS2113-T11-1/tp/pull/97)).
  - Added Class diagram for Storage functionality ([PR #97](https://github.com/AY2425S1-CS2113-T11-1/tp/pull/97), [PR #213](https://github.com/AY2425S1-CS2113-T11-1/tp/pull/213)).

#### **Community and Contributions**:
  - **PRs Reviewed (with non-trivial comments)**: [PR #98](https://github.com/AY2425S1-CS2113-T11-1/tp/pull/98), [PR #92](https://github.com/AY2425S1-CS2113-T11-1/tp/pull/92), [PR #77](https://github.com/AY2425S1-CS2113-T11-1/tp/pull/77)
  - Provided detailed feedback on logic improvements and error handling, ensuring robust and maintainable code.
  - Contributed to design discussions on project structure and best practices.
