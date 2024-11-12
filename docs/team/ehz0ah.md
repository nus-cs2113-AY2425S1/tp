# Lee Hao Zhe - Project Portfolio Page

## Overview
FindOurSEP is a Command Line Interface (CLI) tool designed for an admin handling the allocation of Student Exchange
Program (SEP) locations for Computer Engineering (CEG) students at NUS. The app allows the administrator to efficiently
manage the allocation process using automated workflows and data-driven decision-making.


## Summary of contributions
Code Contributed: [RepoSense (tP Code Dashboard)](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=ehz0ah&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-09-20&tabOpen=true&tabType=authorship&tabAuthor=ehz0ah&tabRepo=AY2425S1-CS2113-W12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### v1.0 Enhancements: 

1. Parser Class ([#22](https://github.com/AY2425S1-CS2113-W12-2/tp/pull/22))
   - **Purpose**: To analyze and interpret user commands, ensuring that each input is accurately understood by the system.
   - **Justification**: Streamlines the command processing workflow by providing a unified approach to parsing user inputs, thus enhancing overall efficiency and reliability.
   - **Highlights**: 
     - Robust error handling for unrecognized commands
     - Modular design, allowing easy updates and maintenance.

2. Abstract Command Class ([#23](https://github.com/AY2425S1-CS2113-W12-2/tp/pull/23))
   - **Purpose**: To serve as a foundational template for all specific command classes, encapsulating shared functionalities and promoting code reusability.
   - **Justification**: Reduces code redundancy and facilitates the implementation of new commands by providing a consistent framework for command execution.
   - **Highlights**: 
     - Defined structure for command initialization and execution. 
     - Standardized error handling and validation mechanisms. 
     - Supports a wide range of command types with minimal code duplication.

3. XYZCommands Class ([#23](https://github.com/AY2425S1-CS2113-W12-2/tp/pull/23), [#37](https://github.com/AY2425S1-CS2113-W12-2/tp/pull/37))
   - **Purpose**: Implements specific commands that interact directly with core functionalities, driving the primary operations of the application.
   - **Justification**: Adds concrete functionality to the abstract command framework, ensuring that the application can execute essential operations efficiently.
   - **Highlights**: 
     - Clear separation of command logic from user interface considerations. 
     - Optimized execution paths for common operations.

### v2.0 Enhancements:

1. FileHandler Class ([#87](https://github.com/AY2425S1-CS2113-W12-2/tp/pull/87), [#83](https://github.com/AY2425S1-CS2113-W12-2/tp/pull/83), [#79](https://github.com/AY2425S1-CS2113-W12-2/tp/pull/79), [#61](https://github.com/AY2425S1-CS2113-W12-2/tp/pull/61))
   - **Purpose**: To handle the processing and management of file operations for different file types (.CSV, .JSON, .TXT).
   - **Justification**: Provides a unified approach to read and write data from various file formats, ensuring consistency and reliability in file handling.
   - **Highlights**: 
     - Processes .CSV, .JSON, and .TXT files from user inputs.
     - Saves allocation results and writes them to external files in .JSON and .TXT formats (.CSV is handled by my teammate, Anderson). 
     - Ensures seamless integration and management of file operations across the system.


### User Guide Contributions:

Added documentation for the following: ([#90](https://github.com/AY2425S1-CS2113-W12-2/tp/pull/90), [#122](https://github.com/AY2425S1-CS2113-W12-2/tp/pull/122), [#174](https://github.com/AY2425S1-CS2113-W12-2/tp/pull/174))
1. Quick Start
2. Pre-Usage Guidelines
3. Uploading Information
   - Manual Input
   - File Input
4. Exit Program
5. Frequently Asked Questions (FAQs)
6. Accepted File Format

### Developer Guide Contributions:

PR for 1-3: [#61](https://github.com/AY2425S1-CS2113-W12-2/tp/pull/61)

1. Table of Contents
2. Acknowledgements
3. Installation 
   - Prerequisites
   - Steps to run program
4. Commands [#99](https://github.com/AY2425S1-CS2113-W12-2/tp/pull/99)
   - Help Command
   - Exit Command
   - Unknown Command
5. Components
   - Parser [#67](https://github.com/AY2425S1-CS2113-W12-2/tp/pull/67)
   - FileHandler [#99](https://github.com/AY2425S1-CS2113-W12-2/tp/pull/99)
6. Product Scope [#122](https://github.com/AY2425S1-CS2113-W12-2/tp/pull/122)
   - Non-Functional Requirements
   - Instructions for Manual Testing
   - Target User Profile & Value Proposition

### Contributions to Team-Based Tasks
1. Set up team GitHub Organisation/Repository
2. Managed release `v1.0` & `v2.1` on GitHub ([v1.0](https://github.com/AY2425S1-CS2113-W12-2/tp/releases/tag/v1.0))
3. Maintained issue and project tracker
4. Incorporated third-party libraries (OpenCSV & Jackson)
5. Updated `gradle.yml` to ensure smooth running of GitHub Continuous Integration Actions ([#38](https://github.com/AY2425S1-CS2113-W12-2/tp/pull/38))
6. PRs reviewed: [#97](https://github.com/AY2425S1-CS2113-W12-2/tp/pull/97), [#70](https://github.com/AY2425S1-CS2113-W12-2/tp/pull/70), [#49](https://github.com/AY2425S1-CS2113-W12-2/tp/pull/49), [#40](https://github.com/AY2425S1-CS2113-W12-2/tp/pull/40), [#30](https://github.com/AY2425S1-CS2113-W12-2/tp/pull/30)
