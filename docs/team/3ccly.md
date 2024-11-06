
# 3CCLY - Project Portfolio Page

## Overview
Your Mother's Favourite Cookbook (YMFC) is a desktop app for managing recipes, designed for use through the
Command Line Interface (CLI). YMFC can help you manage all your recipes with ease, using the simplicity of the CLI, and
keep track of what ingredients you have on hand.


### Summary of Contributions

#### Code Contributed
[RepoSense Link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=3ccly&breakdown=true)

#### Features Implemented
- Created the base Ui class (#PR 36)
  - Ui class handles user interaction, by taking in user input and displaying output messages
  - Improved Ui to re-prompt user for input if user input is empty (#PR 64)
- Created Edit Command for editing recipes (#PR 69)
  - Allow users to edit a pre-existing recipe with new parameters (e.g. different list of ingredients)
- Created Random Command for showing user a random recipe (#PR 134)
  - Choose a random recipe from the recipe database to display
- Created Recommend Command to show user recipes based on their available ingredients (#PR 134)
  - Check the ingredient database for available ingredients, then find available recipes with common ingredients
  - Only recipes from the database with common ingredients from the ingredient database are displayed
  - Recipes are listed in descending order of percentage of the recipe's ingredients that match the ingredient database
  - Percent match of the ingredients and missing ingredients are also shown to the user

#### Enhancements Implemented
- Created JUnit tests for features implemented
  - JUnit test for Ui class (#PR 46)
  - JUnit test for Edit command (#PR 69)
  - JUnit test for Random command (#PR 144)
  - JUnit test for Recommend command (#PR 144)


#### Contributions to the UG
- Enhanced the skeleton draft of the UG, and add more descriptions to features list and quick start section (PR #95)


#### Contributions to the DG
- Created a sequence diagram for Editing Recipes (PR #103)
- Added descriptions to the Ui, Recipe and RecipeList classes



#### Contributions to Team-Based Tasks
- Assisted with fixing formatting inconsistencies for the team (#PR 103)
- Add JavaDoc to multiple classes and methods
- Fix spelling error in the code


#### Review/Mentoring Contributions
- Regularly discuss implementation of new features with group-mates