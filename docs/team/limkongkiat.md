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

## Github Issues Contribution

Added issues [#11](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/11),
[#32](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/32),
[#33](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/33),
[#51](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/51),
[#80](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/80),
[#81](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/81),
[#88](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/88),
[#97](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/97),
[#101](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/101),
[#127](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/127),
[#158](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/158),
[#159](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/159),
[#160](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/160),
[#161](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/161),
[#162](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/162),
[#163](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/163),
[#164](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/164),
[#165](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/165),
[#166](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/166),
[#167](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/167),
[#174](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/174),
[#212](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/212),
[#286](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/286),
[#290](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/290),
[#299](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/299),
[#306](https://github.com/AY2425S1-CS2113-W14-3/tp/issues/306)

## Review Contributions
Reviewed the following PRs: 
[#56](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/56),
[#59](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/59),
[#63](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/63),
[#65](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/65),
[#69](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/69),
[#74](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/74),
[#75](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/75),
[#77](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/77),
[#89](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/89),
[#90](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/90),
[#98](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/98),
[#99](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/99),
[#103](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/103),
[#111](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/111),
[#114](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/114),
[#117](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/117),
[#118](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/118),
[#119](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/119),
[#120](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/120),
[#121](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/121),
[#128](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/128),
[#129](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/129),
[#133](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/133),
[#149](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/149),
[#152](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/152),
[#154](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/154),
[#157](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/157),
[#171](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/171),
[#177](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/177),
[#180](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/180),
[#181](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/181),
[#184](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/184),
[#187](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/187),
[#196](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/196),
[#204](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/204),
[#208](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/208),
[#273](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/273),
[#277](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/277),
[#278](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/278),
[#279](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/279),
[#281](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/281),
[#285](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/285),
[#313](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/313),
[#316](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/316),
[#323](https://github.com/AY2425S1-CS2113-W14-3/tp/pull/323)