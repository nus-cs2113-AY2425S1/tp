package seedu.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.data.hospital.Hospital;
import seedu.duke.data.state.State;
import seedu.duke.data.state.StateType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PatientCommandTest {
    private Hospital hospital;
    private State state;

    @BeforeEach
    void setUp() {
        hospital = new Hospital();
        state = new State(StateType.MAIN_STATE);
    }

    private void addPatients(String... names) {
        for (String name : names) {
            hospital.addPatient(name);
        }
    }

    private CommandResult executeSelectCommand(int index) {
        SelectPatientCommand selectCommand = new SelectPatientCommand(index, state);
        selectCommand.setHospital(hospital);
        return selectCommand.execute();
    }

    @Test
    public void testAddPatientCommand_success_addsPatientSuccessfully() throws Hospital.PatientNotFoundException {
        AddPatientCommand addPatientCommand = new AddPatientCommand("Alice");
        addPatientCommand.setHospital(hospital);
        CommandResult result = addPatientCommand.execute();

        assertEquals("New patient added: Alice", result.getFeedbackToUser());
        assertEquals(1, hospital.getSize());
        assertEquals("Alice", hospital.getPatient(0).getName());
    }

    @Test
    public void testAddPatientCommand_duplicatePatient_returnsDuplicateMessage() {
        hospital.addPatient("Alice");

        AddPatientCommand addDuplicateCommand = new AddPatientCommand("Alice");
        addDuplicateCommand.setHospital(hospital);
        CommandResult result = addDuplicateCommand.execute();

        assertEquals("This patient already exists in the hospital", result.getFeedbackToUser());
        assertEquals(1, hospital.getSize());
    }

    @Test
    public void testAddPatientCommand_blankName_throwsAssertionError() {
        AddPatientCommand addPatientCommand = new AddPatientCommand("");
        addPatientCommand.setHospital(hospital);

        assertThrows(AssertionError.class, addPatientCommand::execute, "Patient name should not be null or empty");
        assertEquals(0, hospital.getSize());
    }

    @Test
    public void testFindPatientCommand_withMatch_returnsMatchingPatients() {
        hospital.addPatient("Alice");
        hospital.addPatient("Bob");

        FindPatientCommand findPatientCommand = new FindPatientCommand("alice");
        findPatientCommand.setHospital(hospital);
        CommandResult result = findPatientCommand.execute();

        String expectedOutput = "Here are the matching patients in your list: \n1. Alice\n";
        assertEquals(expectedOutput, result.getFeedbackToUser());
    }

    @Test
    public void testFindPatientCommand_noMatch_returnsNoMatchMessage() {
        hospital.addPatient("Alice");

        FindPatientCommand findPatientCommand = new FindPatientCommand("charlie");
        findPatientCommand.setHospital(hospital);
        CommandResult result = findPatientCommand.execute();

        assertEquals("There are no matching patients in your list.", result.getFeedbackToUser());
    }

    @Test
    public void testFindPatientCommand_caseInsensitive_returnsMatchingPatients() {
        hospital.addPatient("Alice");

        FindPatientCommand findPatientCommand = new FindPatientCommand("ALICE");
        findPatientCommand.setHospital(hospital);
        CommandResult result = findPatientCommand.execute();

        String expectedOutput = "Here are the matching patients in your list: \n1. Alice\n";
        assertEquals(expectedOutput, result.getFeedbackToUser());
    }

    @Test
    public void testListPatientCommand_withPatients_displaysPatientList() {
        hospital.addPatient("Alice");
        hospital.addPatient("Bob");

        ListPatientCommand listPatientCommand = new ListPatientCommand();
        listPatientCommand.setHospital(hospital);
        CommandResult result = listPatientCommand.execute();

        String expectedOutput = "Here are the patients in your list!";
        assertEquals(expectedOutput, result.getFeedbackToUser());
    }

    @Test
    public void testListPatientCommand_emptyList_displaysEmptyMessage() {
        ListPatientCommand listPatientCommand = new ListPatientCommand();
        listPatientCommand.setHospital(hospital);
        CommandResult result = listPatientCommand.execute();

        assertEquals("The patient list is currently empty!", result.getFeedbackToUser());
    }

    @Test
    public void testDeletePatientCommand_success_deletesPatient() {
        hospital.addPatient("Alice");

        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(1);
        deletePatientCommand.setHospital(hospital);
        CommandResult result = deletePatientCommand.execute();

        assertEquals("Deleted patient: Alice", result.getFeedbackToUser());
        assertEquals(0, hospital.getSize());
    }

    @Test
    public void testDeletePatientCommand_invalidIndex_returnsNotFoundMessage() {
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(1);
        deletePatientCommand.setHospital(hospital);
        CommandResult result = deletePatientCommand.execute();

        assertEquals("Patient not found in the list!", result.getFeedbackToUser());
    }

    @Test
    public void testDeletePatientCommand_outOfBoundsIndex_returnsNotFoundMessage() {
        hospital.addPatient("Alice");

        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(2);
        deletePatientCommand.setHospital(hospital);
        CommandResult result = deletePatientCommand.execute();

        assertEquals("Patient not found in the list!", result.getFeedbackToUser());
    }

    @Test
    public void testSelectPatientCommand_success_changesState() throws Hospital.PatientNotFoundException {
        addPatients("Alice", "Bob");

        CommandResult result = executeSelectCommand(1);

        assertEquals("Selected patient: Alice", result.getFeedbackToUser());
        assertEquals(StateType.TASK_STATE, state.getState());
    }

    @Test
    public void testSelectPatientCommand_selectLastPatient_changesState() throws Hospital.PatientNotFoundException {
        addPatients("Alice", "Bob", "Charlie");

        CommandResult result = executeSelectCommand(3);

        assertEquals("Selected patient: Charlie", result.getFeedbackToUser());
        assertEquals(StateType.TASK_STATE, state.getState());
    }

    @Test
    public void testSelectPatientCommand_invalidIndexOutOfBounds_returnsNotFoundMessage() {
        addPatients("Alice", "Bob", "Charlie");

        CommandResult result = executeSelectCommand(4);

        assertEquals("Patient not found in the list!", result.getFeedbackToUser());
        assertEquals(StateType.MAIN_STATE, state.getState());
    }

    @Test
    public void testSelectPatientCommand_invalidIndexNegative_throwsAssertionError() {
        SelectPatientCommand selectCommand = new SelectPatientCommand(-1, state);
        selectCommand.setHospital(hospital);

        AssertionError thrown = assertThrows(AssertionError.class, selectCommand::execute,
                "Expected execute() to throw an AssertionError for negative index.");
        assertTrue(thrown.getMessage().contains("Index should be non-negative"));
    }

    @Test
    public void testSelectPatientCommand_invalidIndexZero_throwsAssertionError() {
        addPatients("Alice");

        AssertionError thrown = assertThrows(AssertionError.class, () -> executeSelectCommand(0),
                "Expected execute() to throw an AssertionError for zero index.");

        assertTrue(thrown.getMessage().contains("Index should be non-negative"));
    }

    @Test
    public void testSelectPatientCommand_emptyHospital_returnsNotFoundMessage() {
        CommandResult result = executeSelectCommand(1);

        assertEquals("Patient not found in the list!", result.getFeedbackToUser());
        assertEquals(StateType.MAIN_STATE, state.getState());
    }

    @Test
    public void testSelectPatientCommand_nullState_throwsAssertionError() {
        addPatients("Alice");

        SelectPatientCommand selectCommand = new SelectPatientCommand(1, null);
        selectCommand.setHospital(hospital);

        AssertionError thrown = assertThrows(AssertionError.class, selectCommand::execute,
                "Expected execute() to throw an AssertionError for null state.");
        assertTrue(thrown.getMessage().contains("State object should not be null"));
    }
}
