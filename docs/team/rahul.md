# Jai Vinod Kumar Rahul - Project Portfolio Page

## Overview

EasInternship is a desktop tracking application used for tracking internship applications along the various stages
of an application. The user interacts with it using a CLI and the application is written in Java.

Given below are my contributions to the project.

### Summary of Contributions

- **New Feature:** Added the ability to filter internship entries - `FilterCommand`
    - What it does: Allows the user to filter internship entries by various fields such as role name, company name, internship duration, and favorite status. Users can specify multiple filter flags simultaneously, with case-insensitive search for role, company name and favourite status
    - Justification: Users often need to view only specific internship applications matching particular criteria, such as role type, company, or timeframe. This feature provides flexibility in managing and narrowing down the list of internships based on user preferences.
    - Highlights: This feature required a detailed understanding of handling multiple parameters and developing an intuitive syntax for users. It involved implementing logical checks for multiple filtering conditions and managing default values for fields if no specific values are provided.
      <br><br>

- **New Feature:** Added the ability to mark internships as favorites - `FavouriteCommand`
    - What it does: Enables users to mark specific internships as a "Favorite" by entering the internship ID. This helps users easily identify important or preferred internships.
    - Justification: Users may want to flag certain internships to easily return to them, especially if they are in later stages of the application process or hold particular interest. Favoriting helps users keep track of high-priority applications without searching through the entire list.
    - Highlights: The implementation involved managing user input for single or multiple IDs in a single command, ensuring flexibility in the favorite marking process. Plans are in place to expand functionality to allow removal of the favorite status in future versions.
      <br><br>

- **New Feature:** Developed a class to interpret and process user commands - `Parser`
    - What it does: The Parser class acts as the intermediary between the user's raw input and the application's command execution framework. It parses the input strings entered by the user, identifies the command type (e.g., add, delete, update, filter, etc.), and extracts any accompanying arguments or flags. It then returns the appropriate Command object ready for execution.
    - Justification: A robust parser is essential for any command-line application to function effectively. By accurately interpreting user commands, the Parser ensures that the application responds correctly to user input, enhancing the overall user experience. It also centralizes the parsing logic, making it easier to maintain and extend the application with new commands and features without altering the core input handling mechanism.
    - Highlights: Implementing the Parser involved designing a flexible and extensible parsing mechanism capable of handling various command formats and potential user input errors. Modularisation by extracting common methods, so that new commands can be accommodated easily when they are added later on, and extensive testing were some of the key elements that went into this process.
- **Code Contributed:** [RepoSense Link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=rahuljai-05&breakdown=true)
  <br><br>

- **Project Management:**
    - Managed issue tracking and milestones.
    - Aided in extensive testing and debugging of all features.
      <br><br>

- **Enhancement to Existing Features:**
    - Modified the exceptions classes and the way exceptions were thrown in many places. This helped better handle invalid inputs by responding with the suitable error message for each, guiding the user to the correct syntax [#131](https://github.com/AY2425S1-CS2113-T10-1/tp/pull/131)
    - Handled invalid date inputs for AddCommand [#140](https://github.com/AY2425S1-CS2113-T10-1/tp/pull/140)
- **Documentation:**
    - Developer Guide:
        - Added implementation details for `filter` feature.
        - Added implementation details for `favourite` feature.
    - User Guide:
        - Rewrote the Product Description to make it more accurate and detailed
        - Added `filter` command details
        - Added `favourite` command details
          <br><br>

- **Community:**
    - PRs reviewed (with non-trivial review comments): [#116](https://github.com/AY2425S1-CS2113-T10-1/tp/pull/116), [#23](https://github.com/AY2425S1-CS2113-T10-1/tp/pull/23), [#26](https://github.com/AY2425S1-CS2113-T10-1/tp/pull/26), [#31](https://github.com/AY2425S1-CS2113-T10-1/tp/pull/31)
