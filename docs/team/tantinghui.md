# Tan Ting Hui - Project Portfolio Page

## Overview

EasInternship is a desktop tracking application used for tracking internship applications along the various stages
of an application. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

### Summary of Contributions

- **New Feature:** Added the ability to update fields of an internship entry.
  - What it does: allows the user to change the data that is reflected in any of the fields in an internship entry.
  - Justification: Internship applications often have changes in their details which the applicant has to keep track of
    as they are applying for that internship. Updating also allows the user to change erroneous data resulting from
    typos without removing the entire entry and restarting.
  - Highlights: This enhancement affects both the `InternshipList` and `Internship` class, requiring a careful analysis
    on the methods to implement in each class, as well as the `Exceptions` to be handled on each level. The
    implementation also has to be constantly updated when new fields are added to the internship entries.
<br><br>

- **New Feature:** Added the ability to remove fields of an internship entry.
  - What it does: allows the user to remove the data that is reflected in select fields in an internship entry.
  - Justification: This is a continuation of the previous `update` feature. It is implemented as a separate function
    call to keep the overall workflow for updating internship entries simple for the user to understand.
  - Highlights: This enhancement branches off from the `update` feature, requiring updates to the `InternshipList` and
    `Internship` classes to function properly.
<br><br>

- **New Feature:** Added the ability to structure the outputs of the application.
  - What it does: Structures the response of the application and prints it ina format that is easy to follow.
  - Justification: There are a myriad of responses the application can provide, and it is important to structure it
    so that is readable to the user.
  - Highlights: This enhancement involves creating several subclasses that need to referenced in the various other
    classes used in the application. The volume of responses result in large classes filled with methods to deal
    with the various responses.
<br><br>

- **New Feature:** Added the ability to add deadlines to an internship entry.
  - What it does: Allows the user to add or remove deadlines of different descriptions and dates to an internship entry.
  - Justification: Every internship application has a different set of deadlines the applicant has to follow. This
    feature aids users to track their deadlines.
  - Highlights: This enhancement was tough to implement as there were a lot of various implementations, and it was
    difficult to find one that keeps the syntax easy and simple for the user. A lot of simpler implementations
    compromised on functionality in favour of a simpler syntax.
  - Credits: jadenlimjc was a co-author that drafted several implementations.
<br><br>

- **Code Contributed:** [RepoSense Link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=ridiculouswifi&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
<br><br>

- **Project Management:**
  - Managed integration of code.
  - Aided in splitting work load of features.
<br><br>

- **Enhancement to Existing Features:**
  - Updated `update` and `remove` function ([#74]() for skills, [#86]() for deadlines).
<br><br>

- **Documentation:**
  - Developer Guide:
    - Added implementation details for `update` and `remove` feature.
  - User Guide:
    - Reformatted document to reduce excess information.
<br><br>

- **Community:**
  - PRs reviewed (with non-trivial review comments): [#28](), [#33](), [#49](), [#69]()
