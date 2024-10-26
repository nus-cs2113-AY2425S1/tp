# User Guide

## Introduction

Your Mother's Favourite Cookbook (YMFC) is a desktop app for managing recipes, designed for use through the
Command Line Interface (CLI). YMFC can help you manage all your recipes with ease, using the simplicity of the CLI.
It can also keep track of what ingredients you have on hand, and recommend recipes based on what you can make.


## Quick Start

1. Ensure that you have Java 17 or above installed.
2. Download the latest version of `YMFC` from [INSERT RELEASE LINK HERE](https://github.com/AY2425S1-CS2113-W13-1/tp).
3. Open a command terminal window.
4. Navigate to the directory where YMFC.jar is saved at.
   - You can use the `cd` command to navigate to the proper directory.
5. Type in `java -jar YMFC.jar` in the command terminal window to launch YMFC.

## Features

### Usage
* YMFC keeps 2 separate lists. Their contents are accessed and modified using seperate commands.
  * The cookbook list stores your Recipes.
  * The inventory list stores your available Ingredients.
* YMFC stores your data locally, in 2 .txt files
  * Each .txt file stores each of your list (Recipes and Ingredients)
  * The .txt save files are located at `[JAR File Location]/data/recipes.txt` 
and `[JAR File Location]/data/ingredients.txt`
* Your recipes and ingredients are saved automatically every time they are added, edited or removed
* You recipes and ingredients are automatically loaded in every time you launch YMFC

### Input Formatting Guide
* If the command format is in uppercase letters, then it means it is to be substituted for the user's desired phrase
  * Example: In the parameter `n/NAME`, the user is to replace "NAME" with another word/phrase of their liking
such as `n/ABC Soup`
* If the command format is in lowercase letter, then it must be used as it is and cannot be substituted
  * Example: In the parameter `s/name`, the command must be typed exactly as shown.
  * The word "name" cannot be replaced with anything else.
* The [ ] in `[PARAMETER]` means that the parameter in question is optional to add in the command.
* The ... in `PARAMETER...` means that there can be multiple of that parameter.
  * There must be at least 1 parameter included.

### Getting Help:

Format: `help`

Lists out all the available commands and their proper format for use.

### Exiting YMFC

Format: `bye`

Will close the app

## Features and Command - CookBook for Recipes

### Adding a New Recipe

Format: `Format: add n/NAME i/INGREDIENTS... sn/STEPn... [c/CUISINE] [t/TIME]`

Adds a new recipe to YMFC.

* The `c/CUISINE` and `t/TIME` parameters are optional
* The `t/TIME` parameter should be a positive integer
* The `n` in `sn/STEP` is the step number. `n` starts from 1 and counts up by 1 for every additional step  

Example of usage: 

`add n/Ramen Eggs i/eggs i/soya sauce i/water s1/boil eggs for 6.5 min s2/cool eggs in ice bath c/Japanese t/4`

`add n/Grilled Cheese Sandwhich i/bread i/cheese slice i/butter s1/heat pan with butter s2/grill bread on pan,
and add cheese on top s3/remove from grill after 3 minutes`

### Listing out Existing Recipes

Format: `listR`

Lists out all the recipes currently stored in YMFC.

### Sorting Recipes by Recipe Name

Format: `sort s/name`

Sorts all the stored recipes alphabetically by name, from 'a' to 'z'

### Sorting Recipes by Time Taken

Format: `sort s/time`

Sorts all the stored recipes by time taken, from lowest to highest

### Searching for specific Recipes

Format: `find KEYWORDS`, `find i/KEYWORD`, `find ns/KEYWORD`, `find nis/KEYWORD`

Looks through the stored recipes to find those with matching names/steps/ingredients

Example of usage:

**TODO:** Give Examples of usage

### Editing an existing Recipe

Format: `edit e/NAME i/INGREDIENTS... sn/STEPn... [c/CUISINE] [t/TIME]`

Will find the recipe of the entered name and change its details to the newly entered parameters
* The name of the recipe cannot be changed, only it's details (ingredients, steps, cuisine, time)

**TODO:** Verify its use and give examples of its usage

### Deleting an existing Recipe

Format: `delete n/NAME`

Will find the recipe of the entered name and delete it from YMFC
* This action is not reversible

**TODO** Verify that the name does not have to be exactly matching, and can just be pieces

## Features and Command - Inventory for Ingredients

### Adding a New Ingredient

Format: `new n/INGREDIENT`

Adds a new ingredient to your inventory list
* Ingredients can only be added one at a time

### Listing out Existing Ingredients

Format: `listI`

Lists out all of your available ingredients in your inventory


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Copy the data folder containing recipes.txt and ingredients.txt,
and paste it in your other computer in the same directory where YMFC.jar is located

## YMFC.Command Summary

* `help`                              -  Lists out all the available commands and their usage.
* `bye`                               -  Ends the program
* `listR`                             -  Lists out all the existing recipes.
* `add n/NAME i/INGREDIENTS... sn/STEPn... [c/CUISINE] [t/TIME]`  -  Adds a new recipe.
* `sort s/name`                       -  Sorts the recipes alphabetically by name
* `sort s/time`                       -  Sorts the recipes by time, from least to most
* `find KEYWORDS`                     -  Looks for the KEYWORDS in
  * `find KEYWORDS`          = name
  * `find i/KEYWORDS`        = ingredients
  * `find ns/KEYWORDS`       = name or steps
  * `find nis/KEYWORDS`      = name or ingredients or steps
* `edit e/NAME i/INGREDIENTS... sn/STEPn... [c/CUISINE] [t/TIME]` -  Edit an existing recipe
* `delete n/NAME`                     - Deletes the recipe of that name
* `new n/INGREDIENT`                  - Add a new ingredient to your list
* `listI`                             - Lists out all your current ingredients
