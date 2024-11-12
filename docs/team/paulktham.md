# Paul Tham - Project Portfolio Page

## Overview
FindOurSEP is a Command Line Interface (CLI) tool designed for an admin handling the allocation of Student Exchange
Program (SEP) locations for Computer Engineering (CEG) students at NUS. The app allows the administrator to efficiently
manage the allocation process using automated workflows and data-driven decision-making.


## Summary of contributions
[RepoSense (tP Code Dashboard)](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=paulktham&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-09-20)

### v1.0 Enhancements: `Student` Class, `University` Class and `UniversityRepository` Class `PrintStudentList` Method Design Improvement
- Student (`Student`) Development
  - Created the class to represent one student applying for SEP, ensuring that it has the right information for the allocator to fairly decide which student is allocated to which University. I also had to think ahead with my team members to decide what helper functions they require so that we can implement other features seamlessly.

- University (`University`) Development
  - Created the University Class which holds the relevant information for each University available to NUS CEG students for exchange. Using this, I populated a class UniversityRepository which has a static HashMap which holds the information of all the Universities.

### v2.0 Enhancements: `Minimum` command
- Minimum Command Implementation
  - Designed and implemented the CriteriaCommand class to allow the admin handling these allocations to set a minimum GPA every student should attain before they can be considered to be eligible to be allocated to a university. This was done by changing the necessary code in `UI` and `allocator` class.

### User Guide Contributions:
- Along with the other group members we discuss the structure of our User Guide. These were the areas which I was in charge of: (#85)
  1. Content Table.
  2. Explained how to run our application through Quick Start.
  3. Add Command
  4. Minimum Command.

### Developer Guide Contributions:
- Along with the other group members we drew the different diagrams needed in our Developer guide. These were the areas which I was in charge of:
  1. Architecture diagram (#70)
  2. Add Command (#175)
  3. University and UniversityRepository (#119)
  4. Student and StudentList (#119)
  5. Criteria Command (#88)
  6. Delete Command (#179)

### Contribution to Team-Based Tasks
1. Maintained Issue Tracker
2. Constant feedback given through comments of issues and PRs
3. [PRs reviewed](https://github.com/AY2425S1-CS2113-W12-2/tp/pulls?q=is%3Apr+reviewed-by%3A%40me+is%3Aclosed)