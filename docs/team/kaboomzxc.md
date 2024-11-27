# Ong JunZheng - Project Portfolio Page

## Overview
BookBob is a Command Line Interface (CLI) desktop application used to assist in the efficient management of patient information and appointment scheduling.
Clinicians, Doctors, and Medical Professionals are welcomed to use BookBob. <br>

Below are my contributions to the Team Project.

---

## Summary of Contributions
- <b>Code contributed:</b> [RepoSense link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=kaboomzxc&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-09-20)
  - I have contributed over 3.3k LoC spread among functional code, test code and documentation writings.

- <b>Core Features implemented:</b>
    - <b>`CommandHandler` class, `Main` class </b> <br>
          <br>
        - >`find` command
        - "find" Finds patient details such as Name, NRIC, Phone number, Home Address, DOB, Allergy, Sex, Medical history, Diagnoses, Medications.
        - "find" command is Case-insensitive and Trims both leading and trailing spaces.
        - Partial matching is supported and allowed. Multiple search parameters are allowed. 
        - Parameters entered in the input can be in any order. Very flexible search criteria.
        - Complete with extensive user-input validation, robust comprehensive error handling, logging.
        - High Complexity from search logic handling multiple criteria simultaneously, and clean parameter extraction using regex and string manipulation, and using Streams to filter results. <br>
          <br>
        - >`addVisit` command
        - "addVisit" Adds a new visit record for an existing patient. Checks for and validates existing patient.
        - Parameters entered in the input can be in any order.
        - Supports inputs of multiple Diagnoses and/or Medications(seperating by commas).
        - Mandatory field checks and validation of "NRIC" and "Visit Date&Time".
        - Date and Time format validation in : dd-MM-yyyy HH:mm .
        - Validates and compares local system Date&Time and prevents addition of Visits in a Future Date&Time. 
        - Guards against duplicate visits of the same patient at the same exact Date&time.
        - Complete with extensive validation layers, robust comprehensive error handling, logging. 
        - Integrated with Complete Data File Persistence(saving and loading). <br>
          <br>
- <b>Enhancements implemented:</b>
   - Implemented JUnit Tests for my "find" command and "addVisit" command. Implement Tests in the aspiration for higher quality and reliability of our product.
   - Contributed testing of Parser class, ParserTest.java and achieved 51 tests case passed, 100% class, 100% method, 70% line, 92% branch coverage. [PR#375](https://github.com/AY2425S1-CS2113-T10-2/tp/pull/375)
   - I enhanced the appearance of all our team organisation's Github Pages by using Jekyll and HTML+CSS. (Which you can currently see now at all our team's website github pages and in this PDF you are currently reading) 
   - Contributed to “list” command to display Visits information.
   - Added guard clause to prevent future dates for “add” and “addVisit” command. (Thanks teammate Glendon for advising this)
   - Included the “Enter command : ” in the user input line, enhancing user interface, to improve guidance of user input on where to type, instead of just a blank space for user to type. <br>
     <br>
- <b>Contributions to the User Guide(UG):</b><br>
    - I made extensive contributions to the UG.
    - For UG v1.0, I contributed a near-complete full draft.
    - For UG v2.0 and v2.1, added the following sections : "Quick Start", "Finding a Patient Record", "Adding a Visit Record", "FAQ", "Appendix A: Miscellaneous". 
    - I made Partial contributions to the following sections : “Notes under the Features Section”, "Listing All Patient Records".
    - I also contributed to many other portions throughout the UG, e.g. all the colored info boxes with Notes. Updated "help" and "Command Summary Reference" Sections, as and when it comes.
    - I also contributed the implementation of the fine line dividers between sections, enhancing the appearance of the whole UG. 
      And Proofreading, Formatting, cleaning up & Typo fixing.
    - I also contributed to the "ReadME" file in ./tp/docs, which fronts the main landing page of our tP Github pages.<br>
     <br>
- <b>Contributions to the Developer Guide(DG):</b><br>   
    - I made extensive contributions to the DG.
    - I added the following sections : "Table of Contents", "Appendix E: Instructions for Manual Testing". 
    - I also contributed to many other portions throughout the DG e.g. adding the fine line dividers between sections, formatting, proofreading, etc. Updated "Command Summary Reference" Section, as and when it comes.
    - I did almost all the (revision v2) UML Diagrams seen in the DG. (Note, our v1 Diagrams were shared equally, 
      for v2 the team worked on other tasks e.g. refactoring the codebase, while I worked on updating all the v2 UML Diagrams) <br>
    <br>
- <b>Contributions to Team-Based Tasks</b>
    - Setting up of GitHub team Org and Repo.
    - Fix Continuous Integration(CI) and Checkstyle issues throughout. Including helping to update teammate's JUnit as and when it comes.
    - Generating UG and DG PDF files and sending to group.
    - Integrated Mockito into our project to augment testing our product using Mocks.
    - Fixing Bugs, e.g. Fixing compile error [Issue#140](https://github.com/AY2425S1-CS2113-T10-2/tp/issues/140) 
    - Discovered and Fixed a _very tricky elusive_ bug; whereby prefix "a/" (allergy) is a substring of "ha/" (home address), thereby causing
      wrong information to be parsed and being outputted. <u>Solved</u> by changing **"a/"** to become **"al/"**. <br>
    - Fixed bugs identified by other students after the Practical Exam Dry-Run.
    - Maintained the issue tracker.
    - Helped to manage and craft responses to address bug reports after Practical Exam(PE), during PE Phase 2 – “Developer Response” Phase  <br>
    <br>
- <b>Review/Mentoring Contributions</b>
    - I always try my best to be a meaningful and helpful teammate, being very active and responsive in tP throughout, engaging in discussions, suggesting solutions and improvements,
      responding to questions, giving reminders, asking TA and Prof and conveying to our Team, finding and screenshotting info from CS2113 website to provide easy reference and readability to team, etc. 
    - Advised teammates on the two lines of PlantUML code to hide class circle (C) symbols and colored visibility icons of Java Fields&Methods, in order to adhere to CS2113’s notation on UML Diagrams.
    - Advised teammates on logging by checking CS2113 website. (Thanks teammate Yitao for implementing it)<br>
    <br>
- <b>Contributions Beyond the Project Team</b>
    - Evidence of helping others : 
      - Helped to spot bugs during Practical Exam Dry-Run(PE-D). Link to the repository's GitHub Issues : [(I am Tester E)](https://github.com/AY2425S1-CS2113-T11-3/tp/issues?q=tester+E)
      - PRs reviewed(with non-trivial review comments) : [W12-1](https://github.com/nus-cs2113-AY2425S1/tp/pull/1) <br>
      <br>