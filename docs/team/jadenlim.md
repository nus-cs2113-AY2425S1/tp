# Jaden Lim - Project Portfolio Page

## Overview

EasInternship is a desktop tracking application used for tracking internship applications along the various stages
of an application. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

### Summary of Contributions
- **New Feature:** Created the base Internship and InternshipList classes.
  - What it does: creates the baseline functionality for these classes as well as the getters and setters for each field within the class.
  - Justification: When applying for internships, there are often multiple vital fields to take into account, such as `Role`, `Company`, `Duration`, `Deadlines` amongst others. It is integral for these fields to recorded within each internship. Creating the getters and setters for these fields is the foundation for all functions built on top of it.
  - Highlights: The design of Internship and InternshipList focused on flexibility, allowing future additions to accommodate new fields without requiring major rewrites. Getters and setters enable controlled access to fields, maintaining encapsulation while ensuring that key data can be updated as needed. This setup provides the structural foundation for further functionality, such as filtering or sorting internships based on user-defined criteria.
<br><br>

- **New Feature:** Added the ability to add an internship entry.
  - What it does: Allows the user to add a new internship entry
  - Justification: Applicants might be applying to multiple companies and hence it can aid the user in tracking their multiple applications
  - Highlights: This functionality is fundamental for managing and tracking multiple internship applications in a single place, which allows for efficient updates and reviews of application statuses.
<br><br>

- **New Feature:** Added the ability to delete an internship entry.
  - What it does: Allows the user to delete a new internship entry
  - Justification: Applicants might be applying to multiple companies and hence it can aid the user in tracking their multiple applications by deleting internships that are no longer necessary to be maintained.
  - Highlights: This functionality is fundamental for managing and tracking multiple internship applications in a single place, which allows for efficient updates and reviews of application statuses.
<br><br>


- **Code Contributed:** [RepoSense Link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=jadenlimjc&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
<br><br>

- **Project Management:**
  - Head in splitting work load of features.
  - Managed expectations and milestones to be met for v1.0 and v2.0.
<br><br>

- **Enhancement to Existing Features:**
  - Updated `Internship` class to include the `deadline` field.
  - What it does: Allows the user to monitor a list of deadlines within each internship. Each deadline has a different description and date tagged to it.
  - Justification: Every internship application has a different set of deadlines the applicant has to follow. This
    feature aids users to track their deadlines.
  - Highlights: This feature was tough to implement using a format that corresponded to our existing command structure, as our structure specialised in parsing commands with only one flag. Adding deadlines would require multiple flags due to the nested nature of each deadline within each internship, making it difficult to find an implementation that met both our functionality and syntax goals.
  - Credits: ridiculouswifi was a co-author that completed the final implementation and syntax format for the deadline class.
  <br><br>
  - 
- **Documentation:**
  - Developer Guide:
    - Added implementation details for `add` and `delete` features.
    - Added implementation details for `Internship` ,`InternshipList`, and `Deadline` classes as well as how they interact with one another.
  - User Guide:
    - Created the entire skeleton of the UserGuide with all base functions. (Add, Delete, Update, Sort Filter, Help)
<br><br>

- **Community:**
  - PRs reviewed (with non-trivial review comments): [#35](), [#37](), [#122]()
