# Project Portfolio: Atul Teja Vellampalli

## Project: BuffBuddy
BuffBuddy is a workout and meal tracker that tracks your programmes, workouts, meals and water intake alongside tracking your calories and personal bests. The user interacts with it using a CLI.

## Summary of Contributions

### Code Contributed
- **Code Link**: [Click here to view my code on the tP Code Dashboard](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=Atulteja&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=Atulteja&tabRepo=AY2425S1-CS2113-W10-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancements Implemented

- **Meals Intake features**: Designed and wrote the code to add, delete and view meals.
    - **What it does**: This feature allows the user to add, delete or view meals to their daily record for that day. They are also able to add, delete and view meals from previous days.
    - **Justification**: The meals futures is essential in tracking ones calorie intake so that they can visualise the amount of food they are consuming to make changes to their diet to better reach their goals.
    - **Highlights**: This feature allows users to easily add, view, and delete meals for the current and previous days, supporting flexible and comprehensive meal tracking. 

- **Program Feature classes**: Designed and wrote the Exercise, Day, Programme, Programme List classes handling the programme features.
  - **What it does**: These classes form the foundational components for managing and supporting the creation, modification, and tracking of exercise programs. 
  - **Justification**: These classes are essential building blocks for a structured approach to managing exercise programs. By designing these classes, users can handle workout data modularly.
  - **Highlights**: The classes are implemented with robust error handling to ensure valid user inputs and prevent common issues like null inputs or out-of-bounds access.


### Contributions to the User Guide (UG)
- Added/edited the following sections:
    - **Added documentation for meal related features**
      - Provided comprehensive explanations and examples on how users can add, delete and view their meals.  
    - **Added documentation for the water related features**:
      - Provided comprehensive explanations and examples on how users can add, delete and view their water intake.
- Added all these functions to the summary table at the end if the UG for ease of reference.

### Contributions to the Developer Guide (DG)
- **Sections Contributed**: Addmeal feature, Meal, Meallist and water components
- **UML Diagrams**:
    - **Meal, MealList and water class diagrams**
      - Illustrates the structure of the meal, meallist and water classes within the system, listing out all its methods and parameters whilst depicting their accessibility.
    - **Addmeal, Deletemeal and Viewmeal sequence diagram**
      - Created a sequence diagram for the 3 commands, illustrating the step-by-step interaction between various classes and components when a user adds a meal.
    - **Addmeal activity diagram**
      - Created an activity diagram for the "Add Meal" command, detailing the workflow from user input to returning a success message. 

### Contributions to Team-Based Tasks
- Actively participated in team meetings and discussions
- Helped in designing the class and data structures for the programme meal and water components
- Participated in a collaborative debugging session to identify and resolve issues before the V2.0 release

### Review/Mentoring Contributions
- **Pull Request Reviews**:
  - [PR #31 - Add Create and Edit Command](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/31)
  - [PR #151 - Added WaterCommandFactory and ViewWaterCommand classes ](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/151)
  - [PR #159 - Fix History and Logging Issue](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/159)
  - [PR #163 - Shift building of string from PBCommands to History class](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/163)
  - [PR #214 - Java Docs for Storage, Water and FileManager](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/214)
  - [PR #218 - Polish History features ](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/218)
  - [PR #224 - Update Edit Programme User Guide](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/224)

### Contributions Beyond the Project Team
- Bug testing for other teams doing peer reviews

