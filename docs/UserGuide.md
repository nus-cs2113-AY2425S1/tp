# User Guide

## Introduction

FindOurSEP is a Command Line Interface (CLI) tool designed for an admin handling the allocation of Student Exchange Program (SEP) locations for Computer Engineering (CEG) students at NUS. The app allows the administrator to efficiently manage the allocation process using automated workflows and data-driven decision-making.

## Table of Contents
- [Quick Start](#quick-start)
- [Pre-Usage Guidelines](#pre-usage-guidelines)
- [Features](#features)
  - [Uploading Information](#uploading-information)
    - [Manual Input](#manual-input)
    - [File Input](#file-input)
  - [Add Command](#add-student-application-add)
  - [Delete Command](#delete-student-application-delete)
  - [Help Command](#view-help-help)
  - [List Command](#print-current-student-list-list)
  - [Find Command](#find-student-find)
  - [Minimum Command](#set-minimum-gpa-criteria-minimum)
  - [Filter Command](#filter-student-filter)
  - [Stats Command](#view-allocation-statistics-stats)
  - [ViewQuota Command](#view-remaining-quota-viewquota)
  - [Allocate Command](#run-allocation-algorithm-allocate)
  - [Revert Command](#revert-allocation-outcome-revert)
  - [Generate Command](#generate-report-of-allocation-generate)
  - [Exit Command](#exit-program-bye-exit-quit)
- [FAQ](#faq)  
- [Glossary](#glossary)
- [Command Summary](#command-summary)
- [Accepted File Format](#accepted-file-format)

## Quick Start

1. Ensure that you have Java 17 or above installed. ([Installation Guide](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html))
2. Download the latest version v2.1 of `FindOurSEP` and the 3 test files from [here](https://github.com/AY2425S1-CS2113-W12-2/tp/releases).
3. **Copy the files:**
   - Move the downloaded `.jar` file into a designated folder on your computer.
   - Move the 3 test files (test.csv, test.json, test.txt) to the same folder as the `.jar` file.
4. **Run the `.jar` file:**
   - Open a terminal.
   - Navigate (`cd`) to the folder containing the `.jar` file.
   - Execute the `.jar` file using the following command:
     ```shell
     java -jar FindOurSEP.jar
     ```

## Pre-Usage Guidelines
Before using the program, please take note of the following <span style="color:red">IMPORTANT GUIDELINES</span>: 

### Language Recognition 
- **Only English language is recognized** in the program. Please ensure that all data inputs use English characters exclusively.

### Data Upload 
- Users can **ONLY upload data at the START of the program**. Please ensure that all necessary data is prepared and ready for upload before initiating the program.
- For further support, please take a look at [Accepted File Format](#accepted-file-format).

### Output Data 
- The `allocation_results` file located under the `data` folder is **NOT meant to be re-inputted into the program**. It is intended solely for external usage by the users. 

### Proper Exit Procedure
- Please exit the program using the `exit` command. Avoid using other methods to terminate the program to ensure a clean and safe shutdown.

Please adhere to these guidelines to ensure smooth and proper functionality of the program.


<div style="page-break-after: always;"></div>

## Features

### Uploading Information
Upon start-up, the user will see this interface:
```bash
--------------------------------------------------------------------------------
Good day to you!
Before we begin, would you like to:
1. Manually input students data
2. Upload a file (.csv, .txt, .json)
Please choose 1 or 2 or exit :)
--------------------------------------------------------------------------------
```
The program provides two methods for uploading student data, allowing flexibility in how you input and manage information. Below are the options:

It is **HIGHLY RECOMMENDED** to use file input for large datasets and manual input for minor adjustments.

#### Manual Input
By inputting `1`, users can directly enter student data into the program. This option is useful for adding individual records or performing quick updates without needing to upload an entire file.

Subsequently, the user will be met with the following:
```shell
--------------------------------------------------------------------------------
Hi! Welcome to FindOurSEP! Enter help for the list of commands.
--------------------------------------------------------------------------------
```
The user can then begin using the program by inputting [commands](#notes-about-the-command-format).

<div style="page-break-after: always;"></div>

#### File Input
If `2` is chosen instead, the users will be asked to upload files in CSV, JSON, or TXT format, containing multiple student records at once. This method is ideal for batch processing, allowing efficient loading of extensive datasets into the program. Each file type follows a specific format for student ID, GPA, and university preferences, ensuring consistent and structured data handling.

Then, the user will be prompted to input the file path to the file.
```shell
--------------------------------------------------------------------------------
Example: C:\Users\bob\OneDrive\Documents\tp\test.csv
Please enter the ABSOLUTE path to the file: 
--------------------------------------------------------------------------------
```

**NOTE:** If the test files are in the same folder as the `.jar` file, users can just enter the name of the test file. E.g, `test.json`.

If the file is uploaded successfully, the following will be displayed.
```shell
--------------------------------------------------------------------------------
File loaded successfully! Let's begin! Enter help for available commands.
--------------------------------------------------------------------------------
```
However, if the file has any abnormality, an error message will be displayed to the user. The user will still be allowed to continue using the program but with no data uploaded. 

For e.g, the following is the error message shown when the user's CSV file contains data with wrong formatting.
```shell
A1234567I,5, {12,61,43}, ]" is not in correct format! 
Please ensure that you only have 3 columns representing the ID, GPA and PREFERENCES.
--------------------------------------------------------------------------------
Process error! To re-upload a file, please restart the program by entering 'exit' 
followed by 'no' and ensure file is formatted correctly before retrying. 
Otherwise, you can continue to use the program. Enter help for available commands.
--------------------------------------------------------------------------------
```
Upon file upload failure, users can continue using the program without any data uploaded. 
To attempt to reload the data, exit and restart the program to try the upload again.

For further support, please take a look at [Accepted File Format](#accepted-file-format).

<div style="page-break-after: always;"></div>

### *Notes about the command format:*
- Words in UPPER_CASE are the parameters to be supplied by the user.
- Extraneous parameters for commands that do not take in parameters (such as help, list and exit) will be ignored.
  - e.g. if the command specifies `help 123`, it will be interpreted as help.
- If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple 
lines as space characters surrounding line-breaks may be omitted when copied over to the application.

<div style="page-break-after: always;"></div>

### View help: `help`
Shows a message explaining how to use the program. (Commands, etc.)  
Format: `help`
Expected output:

```shell
> help 
--------------------------------------------------------------------------------
Here is the list of possible commands:
    add         Adds a student with the specified ID, GPA, and preference rankings.
                Example: add id/A1234567I gpa/5.0 p/{13,61,43}
                Note: PREFERENCE_RANKINGS should be enclosed in curly braces

    delete      Deletes the student with the specified ID from the list.
                Example: delete A1234567I

    help        Displays this help message.

    list        Lists all students currently in the system.

    find        Finds the student with the keyword, returning either a list or report.
                Example: find list A1234567I

    minimum     Sets the minimum GPA to be considered for exchange.

    filter      Filters student data with a keyword, returning either a list or report.
                User can choose between ascending/descending id/gpa and allocation status.
                The format is: 'filter <list/report> <allocated/unallocated>',
                               or 'filter <list> <gpa/id> <ascending/descending>'
                Example: filter list gpa ascending

    stats       Displays GPA stats for students who have been allocated a uni.
                Usage:
                stats -avggpa <UNI_INDEX>  Displays the average GPA for the specified uni.
                stats -mingpa <UNI_INDEX>  Displays the minimum GPA for the specified uni.

    viewQuota   Displays the index, name, and remaining quota for the specified uni.
                Usage: viewQuota <UNI_INDEX>

    allocate    Allocates students to available slots based on their preferences.

    revert      Reverts the student list to original, pre-allocation state.

    generate    Generates a report of allocations and student data.

    exit        Exits the application.

--------------------------------------------------------------------------------

```

<div style="page-break-after: always;"></div>

### Add Student Application: `add`

Adds a student to the student list. `PREFERENCE_RANKINGS`  should be enclosed in curly braces. We allow students to have a preference of up to 3 universities, and they are ranked left to right, with the HIGHEST priority starting on the left.
Duplicate preference rankings would imply the same preferences for following rounds of allocation.

Format: `add id/STUDENT_ID gpa/GPA p/{PREFERENCE_RANKINGS}`
e.g.
`add id/A1234567I gpa/5.0 p/{13,61,43}`

### Delete Student Application: `delete` 

Deletes a student from the student list.

Format: `delete STUDENT_ID`
Deletes the student with the specified id.
The id to be deleted must match the id in the student records.  
e.g. `delete A1234567I`  
Example output: 
```shell
> delete A1234567I
--------------------------------------------------------------------------------
Removed student, 1 student(s) left
--------------------------------------------------------------------------------
```

### Print current student list: `list`
Outputs a list of all current students in the student list.  
Format: `list`  
Example output:  

```shell
> list
--------------------------------------------------------------------------------
Here is the list:
┌───────────────┬──────────┬─────────────────────────┐
│    Student    │   GPA    │   Preference Rankings   │
├───────────────┼──────────┼─────────────────────────┤
│   A1234567I   │   5.0    │        13,61,43         │
│   A2113113X   │   4.99   │           61            │
└───────────────┴──────────┴─────────────────────────┘
--------------------------------------------------------------------------------
```

<div style="page-break-after: always;"></div>

### Find Student: `find`
Finds the student with the keyword, returning either a list or report. Input is case-sensitive and 
full IDs are not required, but merely keyword.

Format: `find <list/report> STUDENT_ID`
e.g.
`find list A123`

Example output:
```shell
--------------------------------------------------------------------------------
Finding for students... student(s) found.
--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
Here is the list:
┌───────────────┬──────────┬─────────────────────────┐
│    Student    │   GPA    │   Preference Rankings   │
├───────────────┼──────────┼─────────────────────────┤
│   A1234567I   │   5.0    │        13,61,43         │
│   A2113113X   │   4.99   │           61            │
└───────────────┴──────────┴─────────────────────────┘
--------------------------------------------------------------------------------
```

### Set minimum GPA criteria: `minimum`

This sets the minimum GPA for the cohort.

Format: `minimum GPA`
The student must achieve the same or a higher GPA to be considered for exchange. Any student below this GPA will not be allocated to any universities.
e.g.
`minimum 4.0`


<div style="page-break-after: always;"></div>

### Filter Student: `filter`

Filters student data with a keyword, returning either a list or report.
User can choose between ascending/descending id/gpa and allocation status.

Format: `filter <list/report> <allocated/unallocated>`, 
        or `filter <list> <gpa/id> <ascending/descending>`
e.g. 
`filter list gpa ascending`

Example output:

```shell
--------------------------------------------------------------------------------
Here is the list:
┌───────────────┬──────────┬─────────────────────────┐
│    Student    │   GPA    │   Preference Rankings   │
├───────────────┼──────────┼─────────────────────────┤
│   A1237154B   │   4.50   │           64            │
│   A1234567I   │   5.0    │        13,61,43         │
└───────────────┴──────────┴─────────────────────────┘
--------------------------------------------------------------------------------
```

### View allocation statistics: ``stats``

Displays the average or minimum GPA for the students who have been allocated to the specified partner university.  
Format: `stats -avggpa UNI_INDEX` or `stats -mingpa UNI_INDEX`   
Example output:  

```bash
> stats -avggpa 36
--------------------------------------------------------------------------------
The average GPA for university index 36 (The University of Hong Kong) is: 3.80
--------------------------------------------------------------------------------
```

<div style="page-break-after: always;"></div>

### View remaining quota: ``viewQuota``

Displays the index, name, and remaining quota for the specified partner university. 
Format: `viewQuota UNI_INDEX`   
Example output:  

```bash
> viewQuota 58
--------------------------------------------------------------------------------
 Index: 58
 Name:  ETH Zurich
 Quota: 1
--------------------------------------------------------------------------------
```

### Run allocation algorithm: ``allocate``

Start the allocation algorithm. It will allocate universities to the current student list based on each student's preferences and GPA.  
Format: `allocate`  
Upon successful completion, the console should output:  
```shell
--------------------------------------------------------------------------------
Allocation algorithm complete! Run generate command to view.
--------------------------------------------------------------------------------
```
After which, the user can run the `generate` command to view the results, or `revert` command to undo the allocation.

### Revert allocation outcome: `revert`

Reverses the allocation algorithm. To be used when changes need to be made to the student list.  
Format: `revert`
Upon successful completion, the console should output:  
```shell
--------------------------------------------------------------------------------
Revert complete! Run the allocate command whenever you are ready.
--------------------------------------------------------------------------------
```

<div style="page-break-after: always;"></div>

### Generate report of allocation: `generate`
Outputs all students' ID and allocated university in an ASCII table. 
Used for viewing the results of the allocation process.

Format: `generate`

Example output:
```shell
Here is the allocation report:
┌───────────┬───────────────────────────────┐
│ Student   │ University Granted            │
├───────────┼───────────────────────────────┤
│ A1234567I │ Chongqing University          │
│ A2113113X │ National Tsing Hua University │
└───────────┴───────────────────────────────┘
```

### Exit Program: `bye`, `exit`, `quit`
When any of the exit commands (bye, exit, quit) are entered, the program will prompt the user (`Do you want to save your results?`) to save their allocation results. If the user choose `yes`, they will be prompted to enter the desired file format (CSV, JSON, TXT).
Based on their selection, the program will save the results in the specified format.

File Formats:
- CSV: Your data will be saved in a comma-separated values format, making it easy to open in spreadsheet applications.
- JSON: The data will be structured in a JSON format, suitable for data interchange and storage.
- TXT: Your results will be saved in a plain text format, which can be opened in any text editor.

Once the save operation is complete, the program will exit gracefully. 

If the user choose not to save the results, the program will end immediately without saving any data.

<div style="page-break-after: always;"></div>

Below is an example of an `exit` scenario.
```shell
> exit
--------------------------------------------------------------------------------
Do you want to save your results?
--------------------------------------------------------------------------------
> yes
--------------------------------------------------------------------------------
Please choose a file type (CSV, JSON, TXT) to save your results.
--------------------------------------------------------------------------------
> json
Allocation results saved to JSON file at data/allocation_results.json
--------------------------------------------------------------------------------
Adios, amigo!
--------------------------------------------------------------------------------
```
In the above case, the data will be stored in the relative path: `.../data/allocation_results.json`. 

**IMPORTANT**: Please note that if the file already exists, its contents will be overwritten.

<div style="page-break-after: always;"></div>

## FAQ

## Frequently Asked Questions (FAQs)

> **Q: I am facing trouble starting the application. Do you know what might be the issue?**
>
> A: Please ensure that you have Java 17 or above installed on your machine. You may find more instructions in the [Quick Start](#quick-start) section.

> **Q: How do I know whether the data entered is saved?**
>
> A: Your data is safely stored in the `data` folder located in the folder where your jar file is. The file will be named `allocation_results` with the extension you selected before the application closed.

> **Q: My application crashed. How do I report the problem to the developers?**
>
> A: We are sorry for the unpleasant experience with FindOurSEP, and we would be more than happy to solve the issue. You may file an issue on our [GitHub](https://github.com/AY2425S1-CS2113-W12-2/tp/issues) stating how you arrived at the problem, so that our developers can assist you with the issue.

> **Q: I am a developer. How can I find the source code and contribute to FindOurSEP?**
>
> A: FindOurSEP is an open-source application, and we welcome developers to share their ideas. You may find the source code on [GitHub](https://github.com/AY2425S1-CS2113-W12-2/tp).

> **Q: How does FindOurSEP decide who should be allocated first?**
>
> A: In FindOurSEP, students are prioritized based on their GPA, with higher GPAs sorted first. When two students share the same GPA, priority is given to the one listed earlier, following a first-come-first-serve basis.

<div style="page-break-after: always;"></div>

## Glossary

* *SEP* (Student Exchange Programme): NUS’s largest global exchange initiative, enabling students to study at over 300 partner universities in 40+ countries. *FindOurSEP* project is designed specifically to assist in the SEP allocation process for CEG (Computer Engineering) students.
* *GPA* (Grade Point Average): A numeric score ranging from 0.0 to 5.0, representing a student's academic performance, used for allocation.
* *CSV* (Comma-Separated Values): A file format used to store tabular data, such as student records.
* *JSON* (JavaScript Object Notation): A lightweight data-interchange format used for storing student and university data.
* *Allocator*: Class responsible for assigning students to universities based on GPA and preferences.
* *Command*: A specific action or function executed by the program, such as `add`, `delete`, or `allocate`.
* *Parser*: Class that interprets and processes user input commands.
* *StudentList*: Data structure containing records of all students in the SEP system.
* *UniversityRepository*: A repository containing information on partner universities available for SEP.
* *FindOurSEP*: The name of the project system designed to facilitate and optimize the SEP (Student Exchange Programme) allocation process specifically for Computer Engineering students at NUS.
* *Java 17*: A version of the Java programming language and runtime environment. It’s important to have this or a more recent version installed to run certain Java applications.
* *.jar file*: A Java ARchive file that contains Java classes and associated metadata and resources. It's used to distribute Java applications.
* *Terminal*: A text-based interface used to interact with the computer’s operating system, allowing you to execute commands.
* *Navigate (cd)*: A command used in the terminal to change the current directory to a different directory. cd stands for "change directory."

<div style="page-break-after: always;"></div>

## Command Summary

| Action     | Description                                                  | Format/Example                                               |
| ---------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Add        | Adds new student to list, if not already present             | `add id/STUDENT_ID gpa/GPA p/{PREFERENCE_RANKINGS}` <br> e.g. `add id/A1234567I gpa/5.0 p/{13,61,43}` |
| Delete     | Removes student with specified ID                            | `delete STUDENT_ID` <br> e.g. `delete A1234567I`             |
| Find       | Searches the student list for all matches to query           | `find <list/report> STUDENT_ID` <br> e.g. `find list A1234567I` |
| Filter     | Prints a sorted student list                                 | `filter <list/report> <allocated/unallocated>` or `filter <list> <gpa/id> <ascending/descending>`<br/> e.g. `filter list gpa ascending` |
| List       | Prints the current student list                              | `list`                                                       |
| Statistics | Shows statistics for a university                            | ``stats -avggpa UNI_INDEX``  or ``stats -mingpa UNI_INDEX`` <br> e.g. ``stats -mingpa 42`` |
| ViewQuota  | Shows spots available in a university                        | ``viewQuota UNI_INDEX`` <br> e.g. ``viewQuota 42``           |
| Allocate   | Runs the allocation algorithm                                | `allocate`                                                   |
| Minimum    | Sets the minimum GPA for student to be considered for allocation | `minimum GPA_CRITERIA`. <br> e.g. ``minimum 4.0``            |
| Revert     | Resets the allocation algorithm                              | `revert`                                                     |
| GetReport  | Returns the outcome of the allocation                        | `generate`                                                   |
| Exit       | Ends the current session. User will be prompted about the save file | `bye`, `exit`, `quit`                                        |
| Help       | Prints a message containing all available commands           | `help`                                                       |


## Accepted File Format
The program supports files input in `.JSON`, `.CSV`, and `.TXT` formats.

Please make sure your file matches one of these formats:
### JSON:
```json
{
  "students": [
    {
      "ID": "A1234567J",
      "GPA": "4.5",
      "PREFERENCES": "{1,2,3}"
    },
    {
      "ID": "A7654321K",
      "GPA": "3.8",
      "PREFERENCES": "{2,3,1}"
    },
    {
      "ID": "A1357913L",
      "GPA": "5.0",
      "PREFERENCES": "{3,1,2}"
    }
  ]
}
```

<div style="page-break-after: always;"></div>

### CSV:

| ID        | GPA | PREFERENCES |
|-----------|-----|-------------|
| A1234567J | 4.5 | {1,2,3}     |
| A1234567I | 5   | {12,61,43}  |
| A7654321K | 3.8 | {2,3,1}     |
| A1357913L | 4   | {3,1,2}     |



**IMPORTANT**: Please take note that the `.CSV` file is **ONLY** opened, created, and edited using Microsoft Excel. Do **NOT** use an IDE or any other application to modify this file.

### TXT:
```text
id/A1234567J, gpa/4.5, p/{1,2,3}
id/A7654321K, gpa/3.8, p/{2,3,1}
id/A1357913L, gpa/5.0, p/{3,1,2}
```

These examples are for viewing purposes only. Feel free to use the test.csv, test.json and test.txt files available in [v2.0](https://github.com/AY2425S1-CS2113-W12-2/tp/releases) for testing.
