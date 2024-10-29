# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 17 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}

1. add-goal: User can add a fitness goal to the the list
   of goals and attach a deadline to it in order to
   have clear targets to prepare for the NAPFA test.

Input Command:
add-goal (goal name) (deadline)

Example Usage:
add-goal run 12/12/2024 14:00:00

2. delete-goal: User can delete a fitness goal to the the list
   of goals to moderate a fitness goal.

Input Command:
delete-goal (goal index)

Example Usage:   
delete-goal 1

3. list-goal: View a list of all fitness goals and deadlines
   to keep track of progress in preparation for the NAPFA test

Input Command:
list-goal

Example Usage:
list-goal


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

| Command  | Format                                                       | Example        |
|----------|--------------------------------------------------------------|----------------|
| **help** | `help`                                                       | `help`         |
| **set**  | `set GENDER AGE`                                             | `set male 12`  |
| **add**  | `add SESSION_NAME`                                           | `add session1` |
| **list** | `list`                                                       | `list`         |
| **view** | `view SESSION_INDEX`                                         | `view 1`       |
| **edit** | `edit SESSION_INDEX EXERCISE_INDEX REPETITION/TIME DURATION` | `edit 1 2 45`  |
| **exit** | `exit`                                                       | `exit`         |

By following the guide, you can efficiently manage your tasks using the FitTrack application.

