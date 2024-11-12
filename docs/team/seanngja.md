
# seanngja - Project Portfolio Page

## Overview
Your Mother's Favourite Cookbook (YMFC) is a desktop app for managing recipes, designed for use through the
Command Line Interface (CLI). YMFC can help you manage all your recipes with ease, using the simplicity of the CLI, and
keep track of what ingredients you have on hand.


### Summary of Contributions

#### Code Contributed
[RepoSense Link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=w13-1&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-09-20&tabOpen=true&tabType=authorship&tabAuthor=seanngja&tabRepo=AY2425S1-CS2113-W13-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

#### Features Implemented
- Set up the main application control in YMFC.java ([PR #54](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/54))
- Created the base Recipe Class ([PR #37](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/37))
  - Created the overloaded `to_string()` method in Recipe for ease of displaying the recipe's name, ingredients and steps coherently
  - Added the optional attributes (time and cuisine) and updated the parser logic to accept inputs with and without them ([PR #63](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/63))
- Implemented the findIngredient Command ([PR #94](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/94))
  - The findIngredient command allows users to search their ingredient list for ingredients 
  - Updated the parser code to check for and execute the findIngredient command


#### Enhancements Implemented
- Added delete recipe by name method to the delete recipe command ([PR #54](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/54))
  - Added robustness to delete and deleteI commands by ignoring leading and trailing spaces ([PR #214](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/214))
- Added JUnit tests for Recipe class (PR #44), RecipeList class (PR #55) and findIngredient command ([PR #94](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/94))


#### Contributions to the UG
- Added some use-case examples for the addRecipe command ([PR #89](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/89))
- Added to findI command section for ingredients ([PR #124](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/124))


#### Contributions to the DG 
- Created the section on Architecture design ([PR #104](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/104))
  - Created the overarching architecture diagram to showcase the main structure of the application
  - Created a sequence diagram for Application Start Up
- Contributed to findIngredient command description ([PR #151](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/151))
- Contributed to the user stories section ([PR #151](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/151))
  - Added user stories for v2.0 


#### Contributions to Team-Based Tasks
- Set up the build.gradle file to ensure correct builds ([PR #37](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/37))
- Modified naming of findIngredientCommand class to standardise command names with other Ingredient commands
- Reviewed PR's pushed by teammates regularly to comment or approve them

#### Review/Mentoring Contributions
- Gave non-trivial review comments to PRs, helping catch bugs in UML diagram labels ([PR #227](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/227))
- Had fruitful discussions while doing reviews ([PR #134](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/134), [PR #214](https://github.com/AY2425S1-CS2113-W13-1/tp/pull/214))
