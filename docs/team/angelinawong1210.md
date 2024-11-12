# Nguyen Hoang Minh Ngoc - Project Portfolio Page

## Overview

**FlashBang** is a CLI app designed to provide students with a smart way of studying for their modules. The app will manage a limited number of flashcards for a small number of modules, optimized for users who prefer a CLI.

To me, this project is a precious opportunity to build a comprehensive CLI app in a team, covering different tasks such as programming, documentation and testing. I also learned how to use the Git for better code collaboration, as well as practice essential coding conventions. 

## Summary of Contributions

### Code Contributed
Here is the link to my [RepoSense report](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=angelinawong1210&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other). 

### Enhancements implemented
1. Implemeted the function of viewing all flashcards - **ViewCommand**, including creating the code and JUnit test cases. This function plays an essential role as it displays all the flashcards within a module.

2. Updated the **Ui** class with welcome message and list of available commands of the app. 

3. Error handling enhancement for the **AddCommand** to capture invalid module names, questions and answers. 
- For the module names, the app restricts users from creating flashcard set with empty module name. 
- For the questions and answers, the app restricts users from including delimiters in any questions or answers to avoid confusion in the storage process. 

### Contributions to the UG
- Updated the User Guide with **Target User Profile** and **Value Proposition** to provide an overview about the app for users. 
- Added the Command Summary for user's quick reference. 
- Updated proper examples for the **Edit** function.  

### Contributions to the DG:
- Created the class diagram and sequence diagram for the Ui class. 
- Based on the created diagrams, wrote the interpretations. 
- Fix minor cosmetics issue to enhance the readability. 

### Team-based tasks: 
- Updated the Javadoc for some parts of the code base to improve readability.
- Used the issue tracker to manage the project implementation. 

### Review/mentoring contributions: 
- Helped to review and merge the pull requests for other team members: [List of PRs reviewed by me](https://github.com/AY2425S1-CS2113-T11-2/tp/pulls?q=is%3Apr+reviewed-by%3A%40me+is%3Aclosed)
