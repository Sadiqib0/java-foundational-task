import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PersonProblemTest {

    private PersonProblem outer;

    @BeforeEach
    void setUp() {
        outer = new PersonProblem();
    }

    @Test
    void testProblemInitialization() {
        PersonProblem.Problem problem = new PersonProblem.Problem("Debt", PersonProblem.ProblemType.FINANCIAL);

        assertNotNull(problem);
        assertFalse(problem.isSolved(), "Problem should not be solved by default.");
    }

    @Test
    void testSolveMethod() {
        PersonProblem.Problem problem = new PersonProblem.Problem("Fix Server", PersonProblem.ProblemType.TECHNICAL);

        problem.solve();

        assertTrue(problem.isSolved(), "Problem should be marked as solved after calling solve().");
    }

    @Test
    void testToStringFormat() {
        PersonProblem.Problem problem = new PersonProblem.Problem("Study Java", PersonProblem.ProblemType.EDUCATION);
        String expected = "[EDUCATION] Study Java (Solved: false)";

        assertEquals(expected, problem.toString(), "The toString output should match the expected format.");
    }

    @Test
    void testSolvingUpdatesToString() {
        PersonProblem.Problem problem = new PersonProblem.Problem("Prayer", PersonProblem.ProblemType.SPIRITUAL);
        problem.solve();

        assertTrue(problem.toString().contains("Solved: true"));
    }
}