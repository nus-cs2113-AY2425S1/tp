# User Guide

## Introduction


## Quick Start

1. Ensure that you have Java 17 or above installed.
2. Download the latest version of `ExchangeCourseMapper` from [here](http://link.to/duke).
3. 

## Features 

### Adding a course mapping: `add`
Adds a new course mapping into `myList.json` file for storage.Course mapping is subject to validation 
to ensure that the course mapping is valid and that the university provided is an Oceania university. 

Format: `add NUS_COURSE_CODE /pu PARTNER_UNIVERSITY_NAME /coursepu PU_COURSE_CODE`

* All 3 parameters `NUS_COURSE_CODE`, `PARTNER_UNIVERISTY_NAME` and `PU_COURSE_CODE` are case-insensitive.
* Do not add punctuation to the above three parameters
* Indicate the full name of the partner university for `PARTNER_UNIVERISTY_NAME`. For example, indicate
`The Australian National University` instead of `Australian National University` or `ANU`. 

Example of usage: 
`add cs2102 /pu the university of melbourne /coursepu info20003`

Expected output:
```
You have successfully added the course: cs2102 | the university of melbourne | info20003
```
Example of usage:
`add CS3244 /pu The Australian National University /coursepu COMP3670`

Expected output:
```
You have successfully added the course: cs3244 | the australian national university | comp3670
```

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`

cp ps from Docs
