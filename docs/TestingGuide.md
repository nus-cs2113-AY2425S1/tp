# Testing Guide

This guide provides instructions on how to run tests for the project, as well as details on the types of tests included and best practices for writing additional tests.

## Running Tests

There are two main ways to run tests in this project.

### Method 1: Using IntelliJ JUnit Test Runner
- To run all tests, right-click on the `src/test/java` folder and choose **Run 'All Tests'**.
- To run a specific test or subset of tests, right-click on a test package, test class, or individual test, and select **Run 'TestName'**.

### Method 2: Using Gradle
- Open a terminal and navigate to the project root directory.
- Run the following command to clean and execute all tests:

    ```bash
    ./gradlew clean test
    ```

  > Note: On Windows, use `gradlew.bat clean test` instead.

- **:link: Link**: For more details on using Gradle for testing, refer to the [Gradle Tutorial](https://github.com/se-edu/guides) from `se-edu/guides`.

## Types of Tests

This project uses several types of tests to ensure comprehensive coverage of functionality and integration.

### 1. Unit Tests
- **Description**: Tests the smallest units of code, typically individual methods or classes.
- **Purpose**: Verifies that each method or class works as expected in isolation.
- **Examples**:
    - `AddMealCommandTest` to test the `AddMealCommand` functionality.
    - `DeleteMealCommandTest` to validate the `DeleteMealCommand` execution and error handling.

### 2. Integration Tests
- **Description**: Tests the interaction between multiple classes or modules.
- **Purpose**: Ensures that different components work together correctly.
- **Examples**:
    - `MealCommandTest` to validate the execution of meal-related commands with dependencies on `History` and `ProgrammeList`.
    - `CommandFactoryTest` to check that different command factories produce the correct commands based on input strings.

### 3. Hybrid Tests (Unit and Integration)
- **Description**: Tests multiple units together and verifies their connection and interaction.
- **Purpose**: Combines unit and integration testing to validate functionality at a higher level.
- **Examples**:
    - `ViewMealCommandTest` to test `ViewMealCommand` interactions with `DailyRecord` and `MealList`.

## Testing Conventions and Examples

The following conventions are recommended for writing and organizing tests:

### Test Naming Conventions
- Use descriptive names to clearly state what each test is validating. For example:
    - `testExecuteHappyPath` to indicate a test that checks successful execution.
    - `testExecuteEdgeCaseNullDailyRecord` to indicate a test for a specific edge case.

### Structure and Assertions
- **Arrange, Act, Assert**: Organize tests using this structure:
    1. **Arrange**: Set up the necessary objects and mocks.
    2. **Act**: Call the method under test.
    3. **Assert**: Verify the expected outcome using assertions like `assertEquals` and `assertThrows`.
- **Assertions**: Ensure that you use meaningful assertions to validate expected outcomes. Examples include:
    - `assertEquals(expected, actual)` for comparing results.
    - `assertThrows(Exception.class, () -> { ... })` for checking exceptions.

### Mocking and Verification
- **Mockito** is used for creating mock objects and verifying interactions. Examples include:
    - **Mock Initialization**: Use `@Mock` annotations and initialize with `MockitoAnnotations.openMocks(this)`.
    - **Behavior Setup**: Define behavior for mocks, such as `when(mockHistory.getRecordByDate(date)).thenReturn(mockDailyRecord)`.
    - **Verification**: Confirm that certain methods were called, such as `verify(mockDailyRecord).addMealToRecord(sampleMeal)`.

## Common Test Scenarios

- **Happy Path**: Verify that commands and methods execute as expected in normal conditions.
- **Edge Cases**: Handle cases that may cause errors, such as `null` values, invalid indices, or out-of-bound IDs.
- **Constructor Tests**: Ensure constructors handle both valid and invalid inputs properly.
- **Command Execution**: For each command, ensure that:
    - Expected interactions with dependencies (e.g., `History`, `DailyRecord`) occur.
    - Correct responses or `CommandResult` messages are returned.

## Example Tests Breakdown

### AddMealCommandTest
- **Happy Path**: `testExecuteHappyPath` verifies that `AddMealCommand` adds a meal and returns the correct success message.
- **Edge Case**: `testExecuteEdgeCaseNullDailyRecord` checks that an `AssertionError` is thrown if `DailyRecord` is `null`.

### DeleteMealCommandTest
- **Happy Path**: `testExecuteHappyPath` confirms that `DeleteMealCommand` removes the meal and returns the correct success message.
- **Edge Case**: `testExecuteEdgeCaseInvalidIndex` checks for `IndexOutOfBoundsException` when an invalid index is used.

### CommandFactoryTest
- **Factory Pattern**: Tests that `CommandFactory` returns the correct `Command` type based on the input command string.
- **Happy Path**: Validates each command creation, such as `createExitCommand` returning an `ExitCommand`.
- **Invalid Input**: `testCreateInvalidCommand` ensures that an unknown command returns an `InvalidCommand`.

## Best Practices for Writing Tests

1. **Write Clear and Isolated Tests**: Each test should focus on a single functionality or scenario.
2. **Test Edge Cases**: Include tests for common edge cases to ensure robustness, such as invalid inputs or `null` values.
3. **Use Descriptive Assertions**: Ensure that assertions provide clear, readable results and messages.
4. **Keep Tests Independent**: Avoid dependencies between tests to allow each test to run independently without side effects.
5. **Run Tests Regularly**: Run all tests frequently to catch potential issues early.

This testing guide will help you structure, organize, and expand your test cases effectively for comprehensive project coverage.
