# User Guide

## Introduction

Introducing ExchangeCourseMapper, the perfect assistant in your planning for your SEP in Australia!

Using ExchangeCourseMapper, you can plan your course mapping by listing the universities of interest, 
along with the specific courses and subject codes offered by each school. You can quickly filter by NUS-coded modules 
or by partner universities (PU) when you want to view the relevant options. For any course mappings you are interested in,
you can save it in the Personal Tracker provided by ExchangeCourseMapper!

## Quick Start

1. Ensure that you have Java 17 or above installed.
2. Down the latest version of `ExchangeCourseMapper` from [here](http://link.to/duke).
3. Download the JAR file and save it on your computer.
4. Copy the absolute path of where the jar file is saved.
5. In Terminal, run java -jar /path/to/ExchangeCourseMapper.jar
6. Time to start your planning!

## Features 

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

{Give detailed description of each feature}

### Adding a todo: `todo`
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.

Example of usage:

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`


### Filtering possible mappings: `filter`
Filters out all possible PU courses that can be mapped to a user specified NUS course.

Format: `filter NUS_COURSE_CODE`

* The `NUS_COURSE_CODE` is in NUS course code format.

Example: `filter cs3241`

Expected Output:
   
```agsl
Partner University: The University of Melbourne
Partner University Course Code: COMP30019
-----------------------------------------------------
Partner University: The Australian National University
Partner University Course Code: COMP4610
-----------------------------------------------------
```

Example: `filter ee2026`
    
Output:

```
No courses found for the given course code.
```

### Delete course mapping plans from Personal Tracker: `delete`
Delete a course mapping plan that was initially saved into the Personal Tracker.

Format: `delete LIST_INDEX`

* The `LIST_INDEX` is the list index of the course mapping plan to be deleted.

Example: `delete 1` when there are plans stored in the Personal Tracker.

Expected Output:
```
You have deleted the course from your plan: cs2102 | the university of melbourne | info20003
```

Example: `delete 0`

Expected Output:
```
Please provide a valid index of the course plan you would like to delete.
```

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`

For Louis to do for the UG: 

cp ps from Docs
{TODO: Obtain Contacts}
{TODO: List schools}
{TODO: Commands}
{TODO: Miscellaneous}