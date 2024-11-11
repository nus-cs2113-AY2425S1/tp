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

    - **Highlights**: This feature allows users to easily add, view, and delete meals for the current and previous days, supporting flexible and comprehensive meal tracking. Robust error handling ensures that all entries are complete and accurate, enhancing usability with clear prompts and messages. The meal tracking seamlessly integrates with daily records, giving users a holistic view of their dietary habits alongside other tracked health metrics. Efficient data handling ensures quick access to meal history without performance issues.


- **Program Feature classes**: Designed and wrote the Exercise, Day, Programme, Programme List classes handling the programme features.

  - **What it does**: These classes form the foundational components for managing and supporting the creation, modification, and tracking of exercise programs. The `Exercise` class represents individual exercises with customizable attributes like sets, reps, weight, and calories burned. The `Day` class aggregates exercises, allowing users to organize workouts for specific days. The `Programme` class encapsulates multiple Day instances to form comprehensive workout programs. The `ProgrammeList` class manages a collection of programs.
  - **Justification**: These classes are essential building blocks for a structured approach to managing exercise programs. By designing these classes, users can handle workout data modularly, which facilitates maintainability and future feature enhancements. The clear organization supports the creation of flexible and personalized workout routine
  - **Highlights**: The classes are implemented with robust error handling to ensure valid user inputs and prevent common issues like null inputs or out-of-bounds access. The `Exercise` class supports updating specific fields without affecting other data, allowing for flexible modifications. Logging is incorporated in all classes to track significant actions for debugging and transparency. These foundational classes provide a framework that ensures efficient data management, clear structure, and scalability for future integrations, such as detailed progress tracking or data analytics features.


### Contributions to the User Guide (UG)
- Added/edited the following sections:
    - **Added documentation for meal related features**
      - Provided comprehensive explanations and examples on how users can add, delete and view their meals. Gave examples of usages all the possible commands and clearly stated which flags were optional (if any). 
    - **Added documentation for the water related features**:
      - Provided comprehensive explanations and examples on how users can add, delete and view their water intake.
- Added all these functions to the summary table at the end if the UG for ease of reference.

### Contributions to the Developer Guide (DG)
- **Sections Contributed**: Addmeal feature, Meal, Meallist and water components
- **UML Diagrams**:
    - **Meal, MealList and water class diagrams**
      - Illustrates the structure of the meal, meallist and water classes within the system, listing out all its methods and parameters whilst depicting their accessibility.
    - **Addmeal sequence diagram**
      - Created a sequence diagram for the Add Meal command, illustrating the step-by-step interaction between various classes and components when a user adds a meal. The diagram includes user interaction with the Ui and the subsequent parsing and command execution handled by the Parser and AddMealCommand classes. It details data retrieval through History and DailyRecord objects and updates using the addMealToRecord() method, along with the creation of new Meal and DailyRecord objects as needed. This diagram is essential for visualizing the data and control flow during the "Add Meal" operation.
    - **Deletemeal sequence diagram**
      - Created a sub diagram of the delete meal command which excludes all the repeated actions from the add meal sequence diagram by parser, ui and buffbuddy classes.. This sequence diagram depicts the delete meal specific methods and actions.
    - **Viewmeal sequence diagram**
      - Similar to the delete meal diagram, the view meal sequence diagram also depicts the view meal specific methods and actions and excludes the repeated actions by parser, ui and buffbuddy classes.
    - **Addmeal activity diagram**
      - Created an activity diagram for the "Add Meal" command, detailing the workflow from user input to returning a success message. The diagram illustrates the sequence of operations including command parsing, object creation, interaction with History, and updating the MealList.

### Contributions to Team-Based Tasks
- Actively participated in team meetings and discussions
- Helped in designing the class and data structures for the programme and meal components
- Helped team members in debugging or solving bugs

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

