package course;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Author: Christina - JUnit tests for Conditions 1-4
class CourseConditions1to4Test {
    private Course course;

    // Fresh Course before every test so tests do not affect each other
    @BeforeEach
    void setUp() {
        course = new Course();
    }

    // Helper: everything valid except name, description and points
    private boolean create(String name, String desc, int points) {
        return course.createNewAssessment(name, desc, points, "pdf", false, "",
                "01/03/2026 09:00", "15/03/2026 23:59", "20/03/2026 23:59");
    }

    // TC1 - Condition 1: duplicate name rejected
    @Test
    void duplicateNameIsRejected() {
        assertTrue(create("quiz1", "first quiz description", 20));       // precondition: first one added
        assertEquals(1, course.getAssessments().size());                 // precondition: list size 1
        assertFalse(create("quiz1", "second quiz description", 20));     // duplicate rejected
        assertEquals(1, course.getAssessments().size());                 // list unchanged
    }

    // TC2 - Condition 2: blank name rejected
    @Test
    void blankNameIsRejected() {
        assertEquals(0, course.getAssessments().size());                 // precondition: empty list
        assertFalse(create("", "a valid description", 20));
        assertEquals(0, course.getAssessments().size());                 // nothing stored
    }

    // TC3 - Condition 3: 2 word description rejected (boundary below 3)
    @Test
    void twoWordDescriptionIsRejected() {
        assertEquals(0, course.getAssessments().size());                 // precondition
        assertFalse(create("quiz1", "two words", 20));
        assertEquals(0, course.getAssessments().size());
    }

    // TC4 - Condition 4: 0 points rejected (boundary)
    @Test
    void zeroPointsIsRejected() {
        assertEquals(0, course.getAssessments().size());                 // precondition
        assertFalse(create("quiz1", "a valid description", 0));
        assertEquals(0, course.getAssessments().size());
    }

    // Valid boundary input: exactly 3 words and exactly 1 point are accepted
    @Test
    void validBoundaryInputIsAccepted() {
        assertEquals(0, course.getAssessments().size());                 // precondition
        assertTrue(create("quiz1", "three word description", 1));
        assertEquals(1, course.getAssessments().size());                 // stored in list
        Assessment stored = course.getAssessments().get(0);
        assertEquals("quiz1", stored.getName());                         // attributes initialised
        assertEquals("three word description", stored.getDescription());
        assertEquals(1, stored.getPoints());
    }
}
