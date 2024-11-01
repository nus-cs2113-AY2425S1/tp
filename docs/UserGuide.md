# User Guide

## Introduction

{TODO: Give a product intro}

## Quick Start

{TODO: Give steps to get started quickly}

1. Ensure that you have Java 17 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

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
{Give detailed description of each feature}

### Adding a todo: `todo`
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`

cp ps from Docs
