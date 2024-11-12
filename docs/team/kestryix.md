# Verity Lim - Project Portfolio Page

## Overview
Finance Buddy is a software that allows university students to log their daily expenditures to help manage their
budgets. Users can add, delete and edit expenditure logs into the app as well as list out all their logged
transactions. Users are also able to set a monthly budget for themselves in the app, and the app will notify them if
they have exceeded, or are close to exceeding, their budget.

### Summary of Contributions

- **New Feature:** AppUi Class
    - <ins> What it does: </ins> Handles user interactions for the application by displaying messages and prompts to the user. 
      It also facilitates input collection form the user through the console.
    - <ins> Justification: </ins> Centralizes user interaction logic, ensuring a separation of concerns with enhances maintainability
      and readability by isolating input/output operations from the application's core logic which allows other components to focus 
      on their responsibilities.

    
- **New Feature:** Input Parser
    - <ins> What it does: </ins> Responsible for parsing user input into a command and its associated arguments. Arguments 
    are validated against a predefined set to perform an initial argument validation. It also handles missing or invalid 
    inputs, and returns a structured HashMap containing the command and its arguments for further processing in other components. 
    - <ins> Justification: </ins> Separates the parsing and checking of user input from the main application logic, which makes 
      the code more organized, consistent and easier to manage.

    
- **New Feature:** Logic Class
    - <ins> What it does: </ins> Core of the application's functionality to help manage operations on the financial list where 
      it interacts with the various command classes to do so. It handles the processed user input from the input parser, validates 
      and processes the arguments so that the right commands are called. It also works with budget logic for managing the setting 
      and deleting of budget, and storage to ensure that data is stored properly in the application.
    - <ins> Justification: </ins> Helps ensure a clear separation of responsibilities by delegating specific tasks to command 
      classes, budget logic, and storage while integrating their functionalities. This enhances the maintainability, scalability, and
      testability by isolating business logic from user interface and storage components.

    
- **New Feature:** Budget setting
    - <ins> What it does: </ins> Allows user to set a budget
    - <ins> Justification: </ins> Users can better track their expenses and stay within a limit that they set for themselves, 
      which encourages better financial habits and supports users in managing their money more effectively
    
    
- **Extension of Feature:** Budget deletion
    - <ins> What it does: </ins> Allows user to delete their budget
    - <ins> Justification: </ins> Users can reset or remove their budget when it is no longer relevant for them. This flexibility 
      ensures that users can adapt their financial planning to changes in their goals or circumstances.
        
    
- **Code Contributed:** [RepoSense Link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=kestryix&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other)

- **Documentation:** 
- <ins>User Guide</ins>
  - Initial draft for setting/editing budget and command summary [#156](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/156)
  - Add command summary table [#203](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/203)
  - Add Set/Edit Budget summary [#205](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/205)
- <ins>Developer Guide</ins>
  - Initial draft for UI, Parser and Logic components [#129](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/129)
  - Add initial diagrams for UI, Parser and Logic components [#182](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/182)
  - Update diagrams and logic section [#202](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/202)
  
- **Project Management:**
  - Help coordinate meetings and tasks for the project