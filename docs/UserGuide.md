# User Guide

## Introduction

Introducing ExchangeCourseMapper, the perfect assistant in your planning for your SEP in Oceania!

Using ExchangeCourseMapper, you can search and plan your course mapping by listing the universities of interest, 
along with the specific courses and subject codes offered by each school. You can quickly filter by NUS-coded modules 
or by partner universities (PU) when you want to view the relevant options. For any course mappings you are interested in,
you can save it in the Personal Tracker provided by ExchangeCourseMapper!

- [Quick Start](#quick-start)
- [Features](#features)
    - [List all commands: `commands`](#list-all-commands-commands)
    - [Help Command: `help`](#help-command-help)
    - [List out all the possible schools from the options: `list schools`](#list-out-all-the-possible-schools-from-the-options-list-schools)
    - [List courses provided by the partner university: `set`](#list-courses-provided-by-the-partner-university-set)
    - [Obtain contacts from the list of universities: `obtain`](#obtain-contacts-from-the-list-of-universities-obtain)
    - [Filter possible mappings: `filter`](#filtering-possible-mappings-filter)
    - [Add a course mapping: `add`](#adding-a-course-mapping-add)
    - [Delete course mapping plans from Personal Tracker: `delete`](#delete-course-mapping-plans-from-personal-tracker-delete)
    - [List Personal Tracker courses: `list mapped`](#list-personal-tracker-courses-list-mapped)
    - [Compare mapped courses between two universities: `compare`](#compare-mapped-courses-between-two-universities-compare)
    - [Find courses in personalised tracker `find`](#find-courses-in-personalised-tracker-find)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Quick Start

1. Ensure that you have Java 17 or above installed.
2. Download the latest version of `ExchangeCourseMapper` from [here](https://github.com/AY2425S1-CS2113-W10-2/tp/releases/).
3. Download the JAR file and save it on your computer.
4. Copy the absolute path of where the jar file is saved.
5. In Terminal, run java -jar /path/to/ExchangeCourseMapper.jar
6. Time to start your planning!

## Features 

> **NOTE:** Your stored course mapping is located in the `myList.json` file, found in the `data` folder at the 
> same file path where you executed the JAR file.
>
> **NOTE:** Ensure that all the commands given are placed in **one line**. 
> Do not separate the command into multiple lines in the terminal.
> 
> **NOTE:** For all command flags, ensure that your input is placed a single space after the flag.
> 
> **NOTE:** If the file is **corrupted**, please restore it to its **original state** before proceeding with the program.
> 
>You may try one of the following:
>- Remove the corrupted line (delete one course mapping),
>- Remove any empty lines in the file.
>
> **This list is non-exhaustive.**
> 
> **TIP:** You may use the partner universities' **official abbreviation** instead of typing the full name into the CLI, 
> as shown in the chart below:
> - The University of Melbourne - unimelb
> - The Australian National University - anu
> - Victoria University of Wellington - wgtn
> - The University of Western Australia - uwa

### List all commands: `commands`
Displays a list of all available commands, along with a brief description of each.

Format: `commands`

Example of usage: commands

Expected output:

![ListCommandsCommand message](images/ListCommandsCommandMessage.png)


### Help Command: `help`
This feature allows users to ask for help when unsure of how the commands work or how to use the commands.
It provides users with a detailed explanation of what the command does and the format to utilise the command.

Format:
`help [COMMAND]`
* Users can use the commands feature to list out all the available commands

Example of usage:
* help filter
* help set

Expected outcome:
```
Detail explanation
Format to use the feature
Example
```

### List out all the possible schools from the options: `list schools`
List out all the schools users could possibly go for their SEP.

Format: `list schools`

Expected Output:
```
-----------------------------------------------------
The University of Melbourne
The Australian National University
Victoria University of Wellington
The University of Western Australia
-----------------------------------------------------
```


### List courses provided by the partner university: `set`
This feature allows users to list out the available mappable course that are provided by a specific partner university.

The information that would be listed out are:
- PU course code and PU course name
- NUS course code and NUS course name

Format:
`set [PARTNER_UNIVERISTY_NAME]`

The available partner universities are:
* The University of Western Australia
* The University of Melbourne
* The Australian National University
* Victoria University of Wellington

Example of usage:
* set the university of western australia
* set victoria university of wellington

Expected output:
```
PU_COURSE_CODE: PU_COURSE_NAME
NUS_COURSE_CODE: NUS_COURSE_NAME
```
* Note that the output would be a list of mappable course of the format above


### Obtain contacts from the list of universities `obtain`
Obtain the contact details of the university of interest from the list of schools available.

Format: `obtain PARTNER_UNIVERSITY_NAME /CONTACT_TYPE`

* The `PARTNER_UNIVERSITY_NAME` is the name of the partner university from the list of schools.
* The `CONTACT_TYPE` is the type of contact either number or email

Example: `obtain victoria university of wellington /number`

Expected Output:
```
Phone number for Victoria University of Wellington: +64 4 472 1000
```

Example: `obtain the university of western australia /email`

Expected Output:
```
Email for The University of Western Australia: uwa-albany@uwa.edu.au
```

Example: `obtain the australian national university /number`

Expected Output:
```
Phone number for The Australian National University: +61 2 6125 7257
```

### Filtering possible mappings: `filter`
Filters out all possible PU courses that can be mapped to a user specified NUS course.

Format: `filter NUS_COURSE_CODE`

* The `NUS_COURSE_CODE` is in NUS course code format.

Example: `filter cs3241`

Expected Output:

```
-----------------------------------------------------
Filter results for cs3241:
-----------------------------------------------------
Partner University: The University of Melbourne
Partner University Course Code: COMP30019
-----------------------------------------------------
Partner University: The Australian National University
Partner University Course Code: COMP4610
-----------------------------------------------------
-----------------------------------------------------
End of filter results
-----------------------------------------------------
```

Example: `filter ee2026`

Output:

```
-----------------------------------------------------
Filter results for ee2026:
-----------------------------------------------------
-----------------------------------------------------
No mappable courses found for the given course code.
-----------------------------------------------------
```

### Adding a course mapping: `add`
Adds a new course mapping into `myList.json` file for storage. Course mapping is subject to validation
to ensure that the course mapping is valid and that the university provided is an Oceania university.

Format: `add NUS_COURSE_CODE /pu PARTNER_UNIVERSITY_NAME /coursepu PU_COURSE_CODE`

* All 3 parameters `NUS_COURSE_CODE`, `PARTNER_UNIVERISTY_NAME` and `PU_COURSE_CODE` are case-insensitive.
* Do not add punctuation to the above three parameters.
* Do not switch the order of parameters. Keyword `/pu` must come before `/coursepu` keyword.
* Indicate the full name of the partner university for `PARTNER_UNIVERISTY_NAME`. For example, indicate
  `The Australian National University` instead of `Australian National University` or `ANU`.

Example of usage (lowercase):

`add cs2102 /pu the university of melbourne /coursepu info20003`

Expected output:

![Add Courses Normal Case Output](images/AddCoursesCommandLowercaseScreenshot.png)

Example of usage (normal case):

`add CS3244 /pu The Australian National University /coursepu COMP3670`

Expected output:

![Add Courses Lowercase Output](images/AddCoursesCommandNormalCaseScreenshot.png)


### Delete course mapping plans from Personal Tracker: `delete`
Delete a course mapping plan that was initially saved into the Personal Tracker.

Format: `delete LIST_INDEX`

* The `LIST_INDEX` is the integer list index of the course mapping plan to be deleted.

Example: `delete 1` when there are plans stored in the Personal Tracker.

Expected Output:

![Successful delete message](images/SuccessfulDeleteMessage.png)

Example: `delete 0`

Expected Output:
```
Please provide a valid index of the course plan you would like to delete.
```

### List Personal Tracker courses: `list mapped`
Lists all the courses saved in your Personal Tracker.

Format: `list mapped`

Example of usage:
```
list mapped
```

Expected output:
```
Mapped Modules:
-----------------------------------------------------
1. cs2102 | The University of Melbourne | INFO20003
2. cs3244 | The Australian National University | COMP3670
-----------------------------------------------------
```

### Compare mapped courses between two universities: `compare`
Compares the mapped courses between two partner universities, listing common and unique mappings.

**Format**: `compare pu/UNIVERSITY_1 pu/UNIVERSITY_2`

**Example of usage**:
```plaintext
compare pu/the university of melbourne pu/the australian national university
```

**Expected output**:
```plaintext
Comparison Results for The University of Melbourne and The Australian National University:
Common Mappings:
-----------------------------------------------------
CS3244 | The University of Melbourne | COMP30027
CS3244 | The Australian National University | COMP3670
-----------------------------------------------------

Unique Mappings - The University of Melbourne:
-----------------------------------------------------
CS2102 | The University of Melbourne | INFO20003
-----------------------------------------------------

Unique Mappings - The Australian National University:
-----------------------------------------------------
No unique mappings for The Australian National University.
-----------------------------------------------------
```

### Find courses in personalised tracker `find`
This feature allows users to search for NUS courses in their course mappings.

Format: `find [NUS_COURSE_CODE]`
* Note that this feature is searching within the personalised tracker

Example: `find cs2040`

Expected output:
```
cs2040 | the university of western australia | cits2200
-----------------------------------------------------
```

## FAQ

**Q**: Are the commands case-sensitive?

**A**: No they are not.

**Q**: Can I `filter` multiple courses at the same time?

**A**: No, you are only allowed to filter one course at a time. 

**Q**: Can I `set` multiple schools at the same time?

**A**: No, you are only allowed to list out all available course mappings by one partner university at a time. 

**Q**: Can I `add` multiple courses and/or multiple schools at the same time?

**A**: No you cannot, you are only allowed to add one course mapping to one partner university at one time. 

**Q**: Can I `add` schools not in the list of schools and/or add courses not in the list?

**A**: No you cannot, you are only allowed to add partner universities in Oceania. 

**Q**: Can I `add` a course mapping to a particular partner university that is not in the database?

**A**: No you cannot, you are only allowed to add course mappings in our list to ensure the course mapping is valid. 

**Q**: Can I `obtain` multiple schools/contact types at the same time?

**A**: No, you are only allowed to obtain the contact information of one partner university at a time. 

**Q**: Can I `obtain` contacts of schools not in the list?

**A**: No, you are only allowed to obtain contact information of the schools within our list.

**Q**: Can I `delete` multiple indices at the same time?

**A**: No, you can only delete one course mapping (one index) at a time. 

**Q**: Can I `find` multiple courses at the same time?

**A**: No, you can only find one course at a time.

**Q**: Can I `compare` multiple schools at the same time?

**A**: No, you can only compare 2 schools at a time.


## Command Summary

| Action           | Format                                                                           |
|------------------|----------------------------------------------------------------------------------|
| **Commands**     | `commands`                                                                       |
| **Help**         | `help [COMMAND]`                                                                 |
| **List Schools** | `list schools`                                                                   |
| **Set**          | `set [PARTNER_UNIVERISTY_NAME]`                                                  |
| **Obtain**       | `obtain [PARTNER_UNIVERSITY_NAME] /[CONTACT_TYPE]`                               |
| **Filter**       | `filter [NUS_COURSE_CODE]`                                                       |
| **Add**          | `add [NUS_COURSE_CODE] /pu [PARTNER_UNIVERSITY_NAME] /coursepu [PU_COURSE_CODE]` |
| **Delete**       | `delete [LIST_INDEX]`                                                            |
| **List Mapped**  | `list mapped`                                                                    |
| **Compare**      | `compare pu/ [PARTNER_UNIVERISTY_NAME_1] pu/ [PARTNER_UNIVERISTY_NAME_2]`        |
| **Find**         | `find [NUS_COURSE_CODE]`                                                         |
| **Bye**          | `bye`                                                                            |

