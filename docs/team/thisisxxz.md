# Xiong Xinzhuang - Project Portfolio Page

## Overview
FindOurSEP is a Command Line Interface (CLI) tool designed for an admin handling the allocation of Student Exchange
Program (SEP) locations for Computer Engineering (CEG) students at NUS. The app allows the administrator to efficiently
manage the allocation process using automated workflows and data-driven decision-making.

### Summary of Contributions

[RepoSense (tP Code Dashboard)](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=thisisxxz&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

### v1.0 Enhancements: `Allocator` Class

- Allocation Logic Development

  - Developed the backbone of the ``Allocator`` class. Designed an $O(n^2)$ allocation algorithm in FindOurSEP 1.0 version. Wrote JUnit tests for the allocator.
  -  Added corresponding methods (copy constructors, helper methods) in ``Student`` and ``StudentList`` classes to serve the needs of the allocator.

- Allocate Command Bug Fix (run ``allocate`` multiple times lead to a stuck in program)



### v2.0 Enhancements: `Stats` and ``viewQuota`` commands

- Stats Command Implementation
  * Designed and implemented the ``StatCommand`` class to view statistics of the specified partner universities. In FindOurSEP 2.0 version, this refers to checking the minimum and average GPA of the university. Wrote JUnit tests for this command.
- ViewQuota Command Implementation
  * Designed and implemented the ``viewQuotaCommand`` class to check the remaining quota of the specified partner universities. Wrote JUnit tests for this command.
- Criteria Command Bug Fix (program crashes if nothing is supplied to ``minimum`` command)
- Allocator Bug Fix (run ``allocate`` multiple times will change the results)
- ``DeleteCommand`` Bug Fix (quota is not released)
- Enable Assertions in ``build.gradle`` File



### Documentation Contributions

Developer Guide:

* Class Diagram for ``Allocator``, with explanation
* Sequence Diagram for ``AllocateCommand``, with explanation
* Sequence Diagram for ``StatCommand``, with explanation
* Sequence Diagram for ``ViewQuotaCommand``, with explanation
* Contributions to Glossary Section
* Contributions to Non-Functional Requirements Section
* Some Minor Fixes

User Guide:

* Description of ``allocate`` Command
* Description of ``stats`` Command
* Description of ``viewQuota`` Command
* Contributions to FAQ Section
* Some Minor Fixes

JavaDoc:

* Self-Implemented Codes (Mostly focus on ``Allocator``, ``StatCommand``, ``ViewQuotaCommand`` and helper functions in ``StudentList``)







