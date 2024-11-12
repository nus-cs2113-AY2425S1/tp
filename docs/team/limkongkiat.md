# Lim Kong Kiat - Project Portfolio Page

## Overview

Finance Buddy is a software that allows university students to log their daily expenditures to help manage their
budgets. Users can add, delete and edit expenditure logs into the app as well as list out all their logged 
transactions. Users are also able to set a monthly budget for themselves in the app, and the app will notify them if
they have exceeded, or are close to exceeding, their budget.

## Summary of Contributions

- **New Feature:** Add Help Command
  - <ins> What it does:</ins> Displays a summary of available commands and their respective formats for the users' 
    reference
  - <ins> Justification:</ins> Provides a quick and easy avenue for new users to learn how to use the app, 
    or for users to refer to in case they forget the command formats.

- **New Feature:** List Entries by date
  - <ins>What it does:</ins> Expand upon the list entries feature to allow users to limit the
    entries listed to those within a stipulated date range.
  - <ins>Justification:</ins> Allow users to see expenses/incomes within a specific time period of interest.

- **Extension of Feature:** Sort Entries by date upon recording entry.
  - <ins>What it does:</ins> Whenever a user adds an entry using the app, the entry is automatically inserted into the list
    in ascending order of date.
  - <ins>Justification:</ins> This allows users to see the log of their transactions in chronological order even if they did
    not key in their entries in that order.

- **Extension of Feature:** Edit/Delete command acts on last amended entry if no index is specified.
  - <ins>What it does:</ins> If the `edit` or `delete` command is entered with no index specified, the app
    will edit/delete the last amended entry according to the other arguments specified.
  - <ins>Justification:</ins> This allows users to quickly and conveniently edit/delete their last
  amended (added/edited) entry should they realise that they made a mistake.

- **General Contribution:** Refactoring of repeated messages into Commons class
  - <ins> What it does:</ins> Refactors strings that are printed multiple times (e.g. line separator, error messages)
    into a Commons class. 
  - <ins> Justification:</ins> Reduces the use of magic strings.
  
- **Code Contributed:** [RepoSense Link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=limkongkiat&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other) 

- **Documentation:** 
  - <ins>User Guide</ins>
    - Added Contents Page and Section Headers [#137](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/137)
    - Updated List Transactions section [#151](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/151)
    - Did overall formatting for v2.0 UG [#209](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/209)
  - <ins>Developer Guide</ins>
    - Added overview of Design & Implementation [#126](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/126)
    - Added section on List Entries feature [#109](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/109), 
    [#110](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/110), 
    [#153](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/153)
    - Added Product Scope, User Stories and Future Enhancements section [#110](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/110), 
      [#322](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/322)
    - Did overall formatting for v2.0 DG [#214](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/214)

- **Project Management**
  - Maintained issues and managed milestones
  - Led discussions on project scope and implementation
