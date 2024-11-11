# Chordia Laksh - A0310206B - (luxlucky7) - Project Portfolio Page (PPP)

## Project Overview: Inventra
Inventra is a CLI-based inventory management system designed for small and medium-sized businesses.
It provides users with tools to manage custom inventory fields, track records and generate insights.
This product was build with the contribution of T11 G4 team members, as part of CS2113 module.

## Summary of Contributions
### Code Contributions:
Please view all my contributions to the project codes [here](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=luxlucky7&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other).

### Enhancements Implemented:
1. Implemented Core Functionalities:
* `update -h`: Enable user to update field names.
* `update -d`: Enable user to update data by providing the Record ID and field name.

2. Exception Handling and Assertions:
* Custom exception were build on-top of existing code to handle invalid inputs:
    - InventraLessArgsException
    - InventraInvalidHeaderException
* Implemented assertions for defensive programming
* Additionally, perform Gradle test and CI Pipelines, which include:
    - performed checks to ensure program's sanity
### Contributions to User Guide (UG)
1. Contributed the `update` section of `Command`
    - Detailed annotation on individual command's: General format, flags, and command examples.

### Contributions to Developer Guide (DG)
* Provided detailed implementation descriptions for `UpdateCommand`:
    - Included method breakdown and descriptions of class, key methods and flags involved.
    - Documented rationale of method-used to implement `UpdateCommand`, and alternative considerations.

### Summary
Through my contributions to `Inventra`, I have implemented a core functionality,
and ensured consistency throughout the project with respect to exception handling and data persistence and 
user experience.