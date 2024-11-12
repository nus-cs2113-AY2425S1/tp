# Ang Jia Yu, Zilia - Project Portfolio Page

## Overview
BudgetBuddy is a desktop application for managing finances and related tasks, optimised for use via a Command Line Interface (CLI). It is developed in Java and contains approximately 8,000 lines of code (LoC).

---

## Summary of Contributions

### Code Contribution
List of code contributed: [Reposense Code Link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=ziliaajy&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

---

### Feature Implemented
Here is the list of features implemented:

#### 1. Add Budget Feature
- **What It Does**: Allows users to add a new budget entry by specifying an amount, date, and category. Default date and category used are current month and 'OTHERS'.
- **Justification**: Adding budget entries is fundamental for tracking and managing finances effectively.

#### 2. Deduct Budget Feature
- **What It Does**: Deducts an amount from an existing budget of a specified category, updating the remaining balance for the month.
- **Justification**: Essential for adjusting budgets accurately.

#### 3. Listing Budget Feature
- **What It Does**: Displays a list of up to 12 latest budgets, categorised by month, showing specific categories, amounts, and dates. 
Users can also request to view a specific month’s budget.
- **Justification**: Provides users a summary of budget allocations, with a manageable view by limiting to the latest 12 months.

#### 4. Display Income Spent Feature
- **What It Does**: Calculates and displays the percentage of income spent for a specific month, based on recorded expenses and income.
- **Justification**: Offers a quick overview of spending habits, helping users assess if they are staying within their income.

---

### Enhancements Implemented

#### 1. Improved Input Validation for Budget Commands
- **Details**: Enhanced validation for budget-related commands, ensuring unexpected text or invalid formats trigger clear error messages.
- **Impact**: Reduces user error and improves guidance, making the application more intuitive.

#### 2. Refined UI Feedback for Budget Listings
- **Details**: Adjusted the budget listing feature to display each category's budget more clearly.
- **Impact**: Helps users quickly identify budgets by category.

#### 3. Common Tests Across Budget-related and Display Income Spent Features
- **Input Validation**: Ensures invalid inputs (e.g., incorrect formats) trigger errors.
- **Boundary and Edge Case Tests**: Verifies handling of zero, negative values, and edge cases.
- **Error Handling**: Confirms appropriate error messages for invalid inputs.
- **Data Retrieval**: Tests retrieval of specific data (e.g., budgets for a specific month).


- **Relevant Pull Requests**:
    - [PR #29](https://github.com/AY2425S1-CS2113-W10-1/tp/pull/29)
    - [PR #131](https://github.com/AY2425S1-CS2113-W10-1/tp/pull/131)
    - [PR #151](https://github.com/AY2425S1-CS2113-W10-1/tp/pull/151)
      
---

### Contribution to UG
Documented the following portions:
- `Add Budget`
- `Deduct Budget`
- `List Budgets`
- `Display Income Spent` 
- Command Summary

**Relevant Pull Request**: [PR #141](https://github.com/AY2425S1-CS2113-W10-1/tp/pull/141)

---

### Contribution to DG

- **Documented Features in Section 4:**
   - Section 4.3.1 `Add Budget`
   - Section 4.3.2 `Deduct Budget`
   - Section 4.3.3 `List Budget`
   - Section 4.5.1 `Display Income Spent`

- **Manual Testing Instructions in Section 5:**
   - Instructions for the following features:
      - Section 5.5.2.10 `Add Budget`
      - Section 5.5.2.11 `Deduct Budget`
      - Section 5.5.2.12 `List Budget`
      - Section 5.5.2.13 `Display Income Spent`

- **UML Sequence Diagrams:**
   - Created diagrams for the following features:
     - Section 4.3.1 `Add Budget`
     - Section 4.3.2 `Deduct Budget`
     - Section 4.3.3 `List Budget`
     - Section 4.5.1 `Display Income Spent`

**Relevant Pull Request**: [PR #135](https://github.com/AY2425S1-CS2113-W10-1/tp/pull/135)

---

### Contributions to Team-Based Tasks
- **Project Management:** Contributed to planning discussions, helped filter and shape the project's features and structure.
- **Release Management:** Managed the `v2.0` release.

---
   
### Community
- Reported bugs and suggestions for other groups in the class. ([Bug Report](https://github.com/ZiliaAJY/ped/issues))
- Provided feedback on pull requests for other groups. ([PR Feedback](https://github.com/nus-cs2113-AY2425S1/tp/pull/25))
