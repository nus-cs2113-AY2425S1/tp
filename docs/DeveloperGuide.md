# Developer Guide

## Acknowledgements

- This project was inspired by [AddressBook-Level3 (AB3)](https://github.com/se-edu/addressbook-level3), and several code structures were adapted for the MediTask application.
- JSON serialization and deserialization functionality uses the [Jackson library](https://github.com/FasterXML/jackson).
- Logging setup was referenced from open-source Java projects using `Logger`.

---

## Setting up, getting started
Refer to the guide for 'Setting up and getting started.'


---

## Design

{This section can be used to discuss overall system architecture or any relevant design decisions. Since you're focusing on the implementation, this section can be omitted or kept brief.}

---

## Implementation

### **AddPatientCommand**

#### Implementation

The add patient feature allows users to add a new patient to the hospital system. This is facilitated by the `AddPatientCommand`, which handles the logic of adding a patient and updating the system’s state and storage.

1. **User Input**: The user enters the `addPatient` command followed by patient details (e.g., name).
2. **Command Parsing**: The `Parser` parses the input and creates an `AddPatientCommand` object.
3. **Execution**: The `AddPatientCommand` checks if the patient already exists in the `Hospital`. If not, it adds the patient.
4. **Storage Update**: The updated hospital data, now containing the new patient, is saved to storage.

#### Sequence Diagram

The following sequence diagram illustrates how the `AddPatientCommand` is executed:

![AddPatientCommand Sequence Diagram](https://github.com/AY2425S1-CS2113-T11-1/tp/raw/master/docs/images/AddPatientSequenceDiagram.png)

- **If Condition**: The command checks if the patient already exists before adding the patient.
- **Logging**: If a duplicate is detected, an error is logged.

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

## User Stories

| Version | As a ... | I want to ... | So that I can ... |
|---------|----------|---------------|-------------------|
| v1.0    | nurse    | add tasks with specific details (e.g., patient illness, precautions) | ensure all safety and medical steps are followed for each patient |
| v1.0    | nurse    | delete tasks that are no longer relevant | keep my task list up to date and avoid unnecessary clutter |
| v1.0    | nurse    | mark tasks as completed | stay organized and ensure all tasks are done during my shift |
| v1.0    | nurse    | unmark tasks that were incorrectly marked as completed | quickly correct errors and keep an accurate account of ongoing tasks |
| v1.0    | nurse    | save my tasks | access and view them after closing and reopening the interface |
| v1.0    | nurse    | list my tasks | see all my tasks that I have currently |


## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
