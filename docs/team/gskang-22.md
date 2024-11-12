# Sze Kang's - Project Portfolio Page

## Overview
Your Mother's Favourite Cookbook (YMFC) is a desktop app for managing recipes, designed for use through the
Command Line Interface (CLI). YMFC can help you manage all your recipes with ease, using the simplicity of the CLI, and
keep track of what ingredients you have on hand.


### Summary of Contributions

#### Code Contributed
[RepoSense Link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=gskang-22&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

#### Features Implemented
- Created command classes, inheriting from the base Command class
  - Command classes deal with all the possible commands accepted by YMFC, with each command representing a specific
  - Classes created: 
    - addRecipeCommand: to add recipes to recipeList ([PR #43](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/43))
    - byeCommand: for the user to exit from YMFC ([PR #43](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/43))
    - deleteCommand: for the user to remove a recipe from the recipeList  ([PR #43](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/43))
    - listCommand: for the user to receive a list of all the recipes in the recipeList ([PR #43](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/43))
    - AddIngredientCommand: for the user to add an ingredient to the inventory list ([PR #74](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/74))
    - ListIngredientsCommand: for the user to obtain a list of all the ingredients in the inventory list ([PR #74](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/74))
    recognised user input
- Created the Ingredient class, as well as its corresponding adding, deleting and listing logic 
  - The Ingredient class represents the ingredients owned by the user. 
  - Wrote code to allow users to add new ingredients to their inventory list. ([PR #74](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/74))
  - Wrote code to allow users to obtain a list of all the ingredients. ([PR #74](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/74))
  - Wrote code to allow users to delete/remove ingredients from their inventory list. ([PR #131](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/131))
  - Wrote code to allow YMFC to save and load Ingredients using a local .txt file. ([PR #74](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/74))
- Implemented validator for step parameters in Parser when adding recipes ([PR #131](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/131), [PR #225](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/225))
  - Prevents duplicate, missing step numbers, as well as numbers in a wrong order (i.e. not strictly increasing order)
- Wrote code to handle exceptions (print to CLI) 
  - Including use case error messages specific to each command

#### Enhancements Implemented
- Created tests to ensure the proper functioning of command Classes
- Improved on the usage example messages given during incorrect user input

#### Contributions to the UG
- Made instructions clearer by adding more details. ([PR #226](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/226))

#### Contributions to the DG
- Created a sequence diagram for the use of AddRecipeCommand
- Added descriptions for the section on `Command` class and its various subclasses ([PR #105](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/105))
- Added more user stories ([PR #224](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/224))
- Created the table of contents ([PR #224](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/224))

#### Contributions to Team-Based Tasks
- Helped routinely add new issues to work on and close issues that have been resolved
- Added JavaDoc comments to command classes and their methods
- Created and established the GitHub repository and organization for this project
- Fixed various bugs in the code 

#### Review/Mentoring Contributions
- Helped review and close teammates' PRs
- Gave non-trivial review comments to PRs (PR #69, #89, #94)
