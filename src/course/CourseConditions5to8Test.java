package course;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Author: Harsha - JUnit tests for Conditions 5-8
class CourseConditions5to8Test {
    private Course course;

    // Fresh Course before every test so tests do not affect each other
    @BeforeEach
    void setUp() {
        course = new Course();
    }

    // Helper: everything valid except points, submissionType, group and groupingName
    // (name/description/dates held constant and already known-valid from Conditions 1-4)
    private boolean create(int points, String submissionType, boolean group, String groupingName) {
        return course.createNewAssessment("quiz1", "a valid description", points,
                submissionType, group, groupingName,
                "01/03/2026 09:00", "15/03/2026 23:59", "20/03/2026 23:59");
    }

    // Overload for Condition 6, where a second assessment needs a distinct name
    // (so Condition 1's uniqueness check does not also reject it)
    private boolean create(String name, int points) {
        return course.createNewAssessment(name, "a valid description", points,
                "pdf", false, "",
                "01/03/2026 09:00", "15/03/2026 23:59", "20/03/2026 23:59");
    }

    // TC5 - Condition 5: a single assessment over 50 points is rejected (boundary: 51)
    @Test
    void assessmentOver50PointsIsRejected() {
        assertEquals(0, course.getAssessments().size());                 // precondition: empty list
        assertFalse(create(51, "pdf", false, ""));
        assertEquals(0, course.getAssessments().size());                 // nothing stored
    }

    // Condition 5 valid boundary: exactly 50 points is accepted
    @Test
    void assessmentAt50PointsIsAccepted() {
        assertEquals(0, course.getAssessments().size());                 // precondition
        assertTrue(create(50, "pdf", false, ""));
        assertEquals(1, course.getAssessments().size());                 // stored
        assertEquals(50, course.getAssessments().get(0).getPoints());    // attribute initialised
    }

    // TC6 - Condition 6: total points across all assessments over 100 is rejected
    // (boundary: 50 + 51 = 101)
    @Test
    void totalPointsOver100IsRejected() {
        assertEquals(0, course.getAssessments().size());                 // precondition: empty list
        assertTrue(create("quiz1", 50));                                 // precondition: first assessment stored, total = 50
        assertEquals(1, course.getAssessments().size());
        assertFalse(create("quiz2", 51));                                // would bring total to 101
        assertEquals(1, course.getAssessments().size());                 // second assessment not stored
    }

    // Condition 6 valid boundary: total points landing exactly on 100 is accepted
    @Test
    void totalPointsAt100IsAccepted() {
        assertEquals(0, course.getAssessments().size());                 // precondition
        assertTrue(create("quiz1", 50));                                 // precondition: total = 50
        assertEquals(1, course.getAssessments().size());
        assertTrue(create("quiz2", 50));                                 // total = 100, exactly at the limit
        assertEquals(2, course.getAssessments().size());                 // both stored
    }

    // TC7 - Condition 7: a submission type outside pdf/zip/doc is rejected
    @Test
    void invalidSubmissionTypeIsRejected() {
        assertEquals(0, course.getAssessments().size());                 // precondition
        assertFalse(create(20, "docx", false, ""));
        assertEquals(0, course.getAssessments().size());
    }

    // TC8 - Condition 8: a group assessment with no grouping name is rejected
    @Test
    void groupAssessmentWithBlankGroupingNameIsRejected() {
        assertEquals(0, course.getAssessments().size());                 // precondition
        assertFalse(create(20, "pdf", true, ""));
        assertEquals(0, course.getAssessments().size());
    }

    // Condition 8 valid case: a group assessment with a grouping name is accepted
    @Test
    void groupAssessmentWithGroupingNameIsAccepted() {
        assertEquals(0, course.getAssessments().size());                 // precondition
        assertTrue(create(20, "pdf", true, "team-alpha"));
        assertEquals(1, course.getAssessments().size());                 // stored
        Assessment stored = course.getAssessments().get(0);
        assertTrue(stored.isGroup());                                    // attributes initialised
        assertEquals("team-alpha", stored.getGroupingName());
    }

    // Valid input covering Conditions 5-8 together, with 3+ distinct data combinations.
    // Points are kept small enough that the running total (Condition 6) never exceeds
    // 100 across the whole test: 20 + 15 + 10 = 45, well under the limit.
    @Test
    void validInputAcrossConditions5to8IsAccepted() {
        assertEquals(0, course.getAssessments().size());                 // precondition

        assertTrue(create(20, "pdf", false, ""));                        // individual, pdf
        // Direct call to exercise zip + group combination explicitly
        assertTrue(course.createNewAssessment("quiz2", "a valid description", 15,
                "zip", true, "team-beta",
                "01/03/2026 09:00", "15/03/2026 23:59", "20/03/2026 23:59"));
        // Direct call to exercise doc + a fresh individual assessment
        assertTrue(course.createNewAssessment("quiz3", "a valid description", 10,
                "doc", false, "",
                "01/03/2026 09:00", "15/03/2026 23:59", "20/03/2026 23:59"));

        assertEquals(3, course.getAssessments().size());                 // all three stored
    }
}