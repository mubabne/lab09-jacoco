package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScholarshipCalculatorTest {

    private final ScholarshipCalculator calculator = new ScholarshipCalculator();

    @Test
    void letterGradeReturnsAForHighScore() {
        assertEquals("A", calculator.letterGrade(95));
    }

    @Test
    void letterGradeReturnsBForGoodScore() {
        assertEquals("B", calculator.letterGrade(85));
    }

    @Test
    void gpaAveragesLetterGradePoints() {
        assertEquals(3.5, calculator.gpa(new double[]{95, 85}));
    }

    @Test
    void scholarshipReturnsFullAwardForTopStudent() {
        assertEquals(100, calculator.scholarship(3.9, 16, false));
    }

    @Test
    void scholarshipReturnsZeroForDisciplinaryViolation() {
        assertEquals(0, calculator.scholarship(3.9, 16, true));
    }

    @Test
    void letterGradeRejectsScoreBelowZero() {
        assertThrows(IllegalArgumentException.class, () -> calculator.letterGrade(-0.01));
    }

    @Test
    void letterGradeRejectsScoreAboveOneHundred() {
        assertThrows(IllegalArgumentException.class, () -> calculator.letterGrade(100.01));
    }

    @Test
    void letterGradeReturnsCForAverageScore() {
        assertEquals("C", calculator.letterGrade(75));
    }

    @Test
    void letterGradeReturnsDForPassingScore() {
        assertEquals("D", calculator.letterGrade(65));
    }

    @Test
    void letterGradeReturnsFForLowScore() {
        assertEquals("F", calculator.letterGrade(50));
    }

    @Test
    void gpaRejectsNullScores() {
        assertThrows(IllegalArgumentException.class, () -> calculator.gpa(null));
    }

    @Test
    void gpaRejectsEmptyScores() {
        assertThrows(IllegalArgumentException.class, () -> calculator.gpa(new double[]{}));
    }

    @Test
    void gpaCoversCdfGradePoints() {
        assertEquals(1.0, calculator.gpa(new double[]{75, 65, 50}));
    }

    @Test
    void scholarshipRejectsNegativeGpa() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.scholarship(-0.1, 12, false));
    }

    @Test
    void scholarshipRejectsGpaAboveMaximum() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.scholarship(4.1, 12, false));
    }

    @Test
    void scholarshipRejectsNegativeCredits() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.scholarship(3.0, -1, false));
    }

    @Test
    void scholarshipReturnsSeventyWhenTopTierCreditsAreMissing() {
        assertEquals(70, calculator.scholarship(3.9, 14, false));
    }

    @Test
    void scholarshipReturnsFiftyWhenMiddleTierCreditsAreMissing() {
        assertEquals(50, calculator.scholarship(3.5, 11, false));
    }

    @Test
    void scholarshipReturnsZeroForLowGpa() {
        assertEquals(0, calculator.scholarship(2.9, 12, false));
    }
}
