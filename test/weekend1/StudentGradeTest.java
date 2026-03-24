import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentGradeTest {

    private StudentGrade StudentGrade;

    @BeforeEach
    public void setUp() {
        StudentGrade = new StudentGrade(4, 3);
    }

    @Test
    public void testNegativeScoreThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            StudentGrade.addScore(0, 0, -5);
        });
        assertEquals("Score must be between 0 and 100", exception.getMessage());
    }

    @Test
    public void testScoreAboveHundredThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            StudentGrade.addScore(0, 0, 105);
        });
        assertEquals("Score must be between 0 and 100", exception.getMessage());
    }

    @Test
    public void testSingleStudentTotalAndAverage() {
        StudentGrade.addScore(0, 0, 67);
        StudentGrade.addScore(0, 1, 21);
        StudentGrade.addScore(0, 2, 49);

        assertEquals(137, StudentGrade.getStudentTotal(0));
        assertEquals(45.67, StudentGrade.getStudentAverage(0), 0.01);
    }

    @Test
    public void testSubjectPassesAndFails() {
        StudentGrade.addScore(0, 1, 21);
        StudentGrade.addScore(1, 1, 62);
        StudentGrade.addScore(2, 1, 34);
        StudentGrade.addScore(3, 1, 83);

        assertEquals(2, StudentGrade.getSubjectPasses(1));
        assertEquals(2, StudentGrade.getSubjectFails(1));
    }

    @Test
    public void testSubjectHighestAndLowest() {
        StudentGrade.addScore(0, 0, 67);
        StudentGrade.addScore(1, 0, 98);
        StudentGrade.addScore(2, 0, 93);
        StudentGrade.addScore(3, 0, 78);

        assertEquals(1, StudentGrade.getHighestScoringStudentForSubject(0)); // Student 2 (index 1)
        assertEquals(0, StudentGrade.getLowestScoringStudentForSubject(0));  // Student 1 (index 0)
    }

    @Test
    public void testOverallClassSummaries() {
        // Populate the entire sample table to test final summaries
        populateSampleData();

        assertEquals(734, StudentGrade.getClassTotalScore());
        assertEquals(183.50, StudentGrade.getClassAverageScore(), 0.01);

        // Best student is Student 4 (index 3) with 227 total
        assertEquals(3, StudentGrade.getBestGraduatingStudent());

        // Worst student is Student 1 (index 0) with 137 total
        assertEquals(0, StudentGrade.getWorstGraduatingStudent());

        // Hardest subject is Subject 2 (index 1) with 2 failures
        assertEquals(1, StudentGrade.getHardestSubject());
    }

    // --- HELPER METHOD ---

    private void populateSampleData() {
        // Student 1
        StudentGrade.addScore(0, 0, 67); StudentGrade.addScore(0, 1, 21); StudentGrade.addScore(0, 2, 49);
        // Student 2
        StudentGrade.addScore(1, 0, 98); StudentGrade.addScore(1, 1, 62); StudentGrade.addScore(1, 2, 56);
        // Student 3
        StudentGrade.addScore(2, 0, 93); StudentGrade.addScore(2, 1, 34); StudentGrade.addScore(2, 2, 27);
        // Student 4
        StudentGrade.addScore(3, 0, 78); StudentGrade.addScore(3, 1, 83); StudentGrade.addScore(3, 2, 66);
    }
}