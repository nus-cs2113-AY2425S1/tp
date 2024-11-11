# User Guide

----------------------------------
## Table of Content
- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Features](#features)
  - [Recipes and Ingredients](#recipes-and-ingredients)
  - [Save-Load System](#save-load-system)
  - [Input Formatting Guide](#input-formatting-guide)
  - [Getting Help](#getting-helphelp)
  - [Exiting YMFC](#exiting-ymfc-bye)
- [Features and Command - CookBook for Recipes](#features-and-command---cookbook-for-recipes)
  - [Adding a New Recipe](#adding-a-new-recipe-add)
  - [Listing out Existing Recipes](#listing-out-existing-recipes)
  - [Sorting Recipes by Recipe Name](#sorting-recipes-by-recipe-name)
  - [Sorting Recipes by Time Taken](#sorting-recipes-by-time-taken)
  - [Searching for specific Recipes](#searching-for-specific-recipes)
  - [Editing an existing Recipe](#editing-an-existing-recipe)
  - [Deleting an existing Recipe](#deleting-an-existing-recipe)
- [Features and Command - Inventory for Ingredients](#features-and-command---inventory-for-ingredients)
  - [Adding a New Ingredient](#adding-a-new-ingredient)
  - [Listing out Existing Ingredients](#listing-out-existing-ingredients)
- [FAQ](#faq)
- [YMFC\.Command Summary](#ymfccommand-summary)

----------------------------------

## Introduction

Your Mother's Favourite Cookbook (YMFC) is a desktop app for managing recipes, designed for use through the
Command Line Interface (CLI). YMFC can help you manage all your recipes with ease, using the simplicity of the CLI.
It can also keep track of what ingredients you have on hand, and recommend recipes based on what you can make with them. 

## Quick Start

1. Ensure that you have Java 17 or above installed.
2. Download the latest version of `YMFC` from [here](https://github.com/AY2425S1-CS2113-W13-1/tp/releases).
3. Open a command line terminal (`Terminal` on Linux and macOS, `Command Prompt` on Windows).
4. Navigate to the directory where YMFC.jar is saved at.
   - You can use the `cd` command to navigate to the proper directory.
5. Type in `java -jar YMFC.jar` in the command line terminal to launch YMFC. 
6. Use commands listed in the [Features](#features).
7. Type `bye` to exit YMFC.

## Features

### Recipes and Ingredients
* There are 2 categories of data that YMFC will help you store: Recipes and Ingredients
* The **Recipes** are stored in the **cookbook** database while **Ingredients** are stored in the **inventory** database


* **Recipes** are what you would expect in a **cookbook** and contain details such as ingredients needed, steps to take,
cuisine of the meal and time taken to prepare the meal
  * They are essentially descriptions of a meal and steps on how to prepare those meals


* **Ingredients** in the **inventory** refer to ingredients that you as a cook have on hand at the moment
  * i.e. The inventory database of ingredients contains items that the user currently has available to cook with
* NOTE: These ingredients are **COMPLETELY DISTINCT** from the ingredients contained within recipes!!!
  * As such, when creating and adding recipes to the cookbook, the ingredients mentioned within them **WILL NOT** 
be automatically added to the ingredients inventory! 
  * Because the ingredients within recipes and ingredients within the inventory refer to 2 completely different things!
  * The only way to add ingredients to your inventory is through the `new` command, which will be described later

<!-- @@author 3CCLY -->

### Save-Load System
* YMFC keeps 2 separate databases, stored locally in 2 text files.
  * The cookbook database stores your Recipes, located at `[JAR File Location]/data/cookbook.txt`.
  * The inventory database stores your available Ingredients, located at `[JAR File Location]/data/inventory.txt`.
* YMFC automatically loads your recipes and ingredients from these 2 databases when launched
* If the databases don't yet exist, YMFC will create them automatically upon launch of the app
* Your recipes and ingredients are saved automatically every time they are added, edited, removed or modified in any way
  * i.e. There is no manual way nor need to initiate this save process, as it is taken care of by YMFC itself.

<!-- @@author -->

### Input Formatting Guide
* If the command format is in `UPPER_CASE`, then it means it is to be substituted for the user's desired phrase
  * Example: In the parameter `n/NAME`, the user is to replace "NAME" with another word/phrase of their liking
such as `n/ABC Soup`.
* If the command format is in `lowercase` letter, then it must be used as it is and cannot be substituted
  * Example: In the parameter `s/name`, the command must be typed exactly as shown.
  * The word "name" cannot be replaced with anything else.
* The [ ] in `[PARAMETER]` means that the parameter in question is optional to add in the command.
  * e.g. `n/NAME [t/TEST]` can be used as `n/Sanjith t/YAYYY` or as `n/Sanjith`
* The ... in `PARAMETER...` means that there can be multiple of that parameter.
  * NOTE: There must be **at least 1** parameter included.
* Any command that has no additional parameters will ignore anything after the command, and will perform as usual.
  * Example: `help me` would have the same results as `help`.

### Getting Help:`help`

Format: `help`

Lists out all the available commands and their proper format for use.

### Exiting YMFC: `bye`

Format: `bye`

Will end the program and close the app.

## Features and Command - CookBook for Recipes

### Adding a New Recipe: `add`

Format: `Format: add n/NAME i/INGREDIENTS... sn/STEPn... [c/CUISINE] [t/TIME]`

Adds a new recipe to YMFC's cookbook.

* The orders of parameters are crucial, changing order could result in an invalid command.
  * Example: `t/TIME` before `c/CUISINE`, `sn/STEPn` before `i/INGREDIENTS`,... will all be considered invalid.
* The `c/CUISINE` and `t/TIME` parameters are optional
* The `t/TIME` parameter should be a positive integer
* The `n` in `sn/STEP` is the step number. 
  * `n` starts from 1 and counts up by 1 for every additional step
  * Step numbers must be in strictly increasing order, without skipping any numbers.
  * Duplicate numbers, missing numbers, numbers in the wrong order will be **rejected**.

Example of usage: 

`add n/Ramen Eggs i/eggs i/soya sauce i/water s1/boil eggs for 6.5 min s2/cool eggs in ice bath c/Japanese t/4`

`add n/Grilled Cheese Sandwhich i/bread i/cheese slice i/butter s1/heat pan with butter s2/grill bread on pan,
and add cheese on top s3/remove from grill after 3 minutes`

Expected Outcome:
````
__________________________________________________________________________________
Okie dokie, one more recipe for me:
 Recipe: Ramen Eggs
  Ingredients: 
    - eggs
    - soya sauce
    - water
  Steps: 
    1. boil eggs for 6.5 min
    2. cool eggs in ice bath
  Cuisine: Japanese
  Time taken: 4
You currently have 1 recipe in your list.
__________________________________________________________________________________
````

````
__________________________________________________________________________________
Okie dokie, one more recipe for me:
 Recipe: Grilled Cheese Sandwhich
  Ingredients: 
    - bread
    - cheese slice
    - butter
  Steps: 
    1. heat pan with butter
    2. grill bread on pan, and add cheese on top
    3. remove from grill after 3 minutes
You currently have 2 recipes in your list.
__________________________________________________________________________________
````

### Listing out Existing Recipes

Format: `list`

Lists out all the recipes currently stored in YMFC's cookbook.

### Sorting Recipes by Recipe Name

Format: `sort s/name`

Sorts all the stored recipes alphabetically by name, from 'a' to 'z'
Entire command **MUST** be in **lowercase**
- i.e. sort s/NAME will be rejected

### Sorting Recipes by Time Taken

Format: `sort s/time`

Sorts all the stored recipes by time taken, from lowest to highest
Entire command **MUST** be in **lowercase**
- i.e. sort s/TIME will be rejected

### Searching for specific Recipes

Format: `find [OPTIONS/]KEYWORDS`

Looks through the stored recipes in the cookbook to find those with matching names/steps/ingredients.
* `KEYWORDS` can't be blank, and is case-sensitive.
* `OPTIONS` could be any combination of "n" (by recipe name), "i" (by ingredients) and "s" (by steps).
  * For example, if you want to find "egg" recipes based on recipe names and ingredients: `find ni/egg`
> [!TIP]
> If `OPTIONS` were not provided, the command will find recipes based on recipe name by default.
> * E.g: `find ramen`
* More examples:

  1) `find i/egg`: Find recipes which have the ingredients consist of "egg".

  2) `find ns/spaghetti`: Find recipes which have "spaghetti" in name or steps.

  3) `find sn/spaghetti`: Same results as (ii).

  4) `find nis/tomato`: Find recipes which have "tomato" anywhere.

  5) `find isn/tomato`: Same results as (iv).

Example of usage:

`find i/eggs`

Expected Outcome:
````
__________________________________________________________________________________
Here's everything that I've found so far:
1. Recipe: Ramen Eggs
  Ingredients: 
    - eggs
    - soya sauce
    - water
  Steps: 
    1. boil eggs for 6.5 min
    2. cool eggs in ice bath
  Cuisine: Japanese
  Time taken: 4
__________________________________________________________________________________
Total: 1 recipes found!
__________________________________________________________________________________
````

<!-- @@author 3CCLY -->

### Editing an existing Recipe

Format: `edit e/NAME [n/NAME] [i/INGREDIENTS...] [sn/STEPn...] [c/CUISINE] [t/TIME]`

Will find the recipe of the entered name and change its parameters to the newly entered parameters.
- All parameters are optional except for `e/NAME`, leaving any of the optional parameters out of the command means 
the existing parameters of that type is untouched 
  - (e.g. don't need to add `c/CUISINE` to your command if you 
wish to keep the cuisine parameter of the recipe unedited).
- However, leaving every single optional parameter out **will basically do nothing**, 
so that isn't really an edit now, is it? (You will get a reminder from YMFC for this)
- The `e/NAME` refers to the **current name** of the recipe **you wish to edit**
- The `n/NAME` refers to the **new name** you wish to **rename the recipe** to
- The `i/INGREDIENTS...` refers to the **new list of ingredients** for the recipe 
  - NOTE: The entire current list of ingredients for the recipe is **overwritten** with the new list inputted
- The `sn/STEPn...` refers to the new list of steps for the recipe
  - NOTE: The entire current list of steps for the recipe is **overwritten** with the new list inputted
- The `c/CUISINE` refers to the new cuisine you wish to edit the recipe to have
  - NOTE: Leaving CUISINE blank (by typing `c/ `) will **delete** the existing cuisine parameter of the recipe
- The `t/TIME` refers to the new time taken you wish to edit the recipe to have
  - NOTE: leaving TIME blank (by typing `t/ `) will **delete** the existing time parameter of the recipe
* **Parameters follow the same rule as adding a new recipe**
  * See the add recipe command section for the proper rules and format, and **follow it EXACTLY**
* Existing recipes cannot be renamed to a name already used by another recipe (this is case-insensitive)

Example of usage:

`edit e/Grilled Cheese Sandwhich i/bread i/mayonaise i/cheese slice i/butter s1/heat pan with butter 
s2/spread mayonaise on outside of bread s3/grill bread on pan, and add cheese on top 
s4/remove from grill after 3 minutes`

<div style="page-break-after: always;"></div>

Expected Outcome:
````
__________________________________________________________________________________
You got it boss, I have edited your recipe for Grilled Cheese Sandwhich to:
Recipe: Grilled Cheese Sandwhich
  Ingredients: 
    - bread
    - mayonaise
    - cheese slice
    - butter
  Steps: 
    1. heat pan with butter
    2. spread mayonaise on outside of bread
    3. grill bread on pan, and add cheese on top
    4. remove from grill after 3 minutes
__________________________________________________________________________________

````

<!-- @@author -->

### Deleting an existing Recipe

Format: `delete n/NAME`

Will find the recipe of the entered name and delete it from YMFC's cookbook, and the name must be exact

NOTE: This action is not reversible

Example of usage:

`delete n/Ramen Eggs`

Expected Outcome:
````
__________________________________________________________________________________
Aww, I shall begrudgingly let go of this recipe:
  Ramen Eggs
You currently have 1 recipe in your list.
__________________________________________________________________________________
````

## Features and Command - Inventory for Ingredients

### Adding a New Ingredient

Format: `new n/INGREDIENT`

Adds a new ingredient to your **inventory**

NOTE: Ingredients can only be added one at a time

Example of usage:

`new n/Eggs`

Expected Outcome:
````
__________________________________________________________________________________
I'll add that to your inventory:
 Eggs
You currently have 1 ingredient in your inventory.
__________________________________________________________________________________
````

### Listing out Existing Ingredients

Format: `listI`

Lists out all of your available ingredients in your inventory

<!-- @@author seanngja -->

### Finding Ingredients 

Format: `findI INGREDIENT`

Search through your inventory list based on the ingredient name given

<!-- @@author 3CCLY -->

### Deleting an Existing Ingredient

Format: `deleteI n/INGREDIENT`

Will find the ingredient of the entered name and delete it from YMFC's inventory, and the name must be exact

NOTE: This action is not reversible

### Sorting Ingredients by Ingredient Name

Format: `sortI`

Sorts all the stored ingredients within the inventory alphabetically by name, from 'a' to 'z'

## Features and Command - Getting Recipe Inspirations

### Displaying a Randomly-chosen Recipe

Format: `random`

Picks a recipe from the cookbook at random and displays it

<div style="page-break-after: always;"></div>

### Recommend Recipes Based on Available Ingredients

Format: `recommend`

Find recipes that share common ingredients with what is in your ingredients inventory. 
Recipes are then recommended from having the highest percentage of shared common ingredients 
to the least.

<!-- @@author -->

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Copy the data folder containing cookbook.txt and inventory.txt,
and paste it in your other computer in the same directory where YMFC.jar is located

**Q**: Can I edit the .txt files directly?

**A**: Yes, but you must ensure that the format of how recipes and ingredients are saved is followed.
If there are irregular lines in the save files, YMFC will discard them upon launch.

<div style="page-break-after: always;"></div>

## YMFC\.Command Summary

| Command                                                                      | Usage                                                                                                 |
|------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------|
| `help`                                                                       | List out all the available commands and their usage                                                   |
| `bye`                                                                        | Terminate the program                                                                                 |
| `list`                                                                       | List out all the existing recipes in the cookbook                                                     |
| `add n/NAME i/INGREDIENTS... sn/STEPn... [c/CUISINE] [t/TIME]`               | Add a new recipe to the cookbook                                                                      |
| `sort s/name`                                                                | Sort the recipes alphabetically by name. Entire command must be in lowercase.                         |
| `sort s/time`                                                                | Sort the recipes by time, from least to most. Entire command must be in lowercase.                    |
| `find [OPTIONS/]KEYWORDS`                                                    | Look for the KEYWORDS in recipes section (provided by `OPTIONS`)<br/>(Find in recipe name by default) |
| `edit e/NAME [n/NAME] [i/INGREDIENTS...] [sn/STEPn...] [c/CUISINE] [t/TIME]` | Edit an existing recipe in the cookbook                                                               |
| `delete n/NAME`                                                              | Delete the recipe of that name from the cookbook                                                      |
| `new n/INGREDIENT`                                                           | Add a new ingredient to your inventory                                                                |
| `listI`                                                                      | List out all your current ingredients in the inventory                                                |
| `findI INGREDIENT`                                                           | Search the inventory with input ingredient name                                                       |
| `deleteI n/INGREDIENT`                                                       | Delete the ingredient from inventory of that name                                                     |
| `sortI`                                                                      | Sort the ingredients in the inventory alphabetically by name                                          |
| `random`                                                                     | Display a randomly-chosen recipe from cookbook                                                        |
| `recommend`                                                                  | Display all recipes that share common ingredients with what's available in the inventory              |
