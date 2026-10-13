package course;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CourseTest {

    @Test
    void C11acceptsValidDatesInOrder() {
        Course course = new Course();

        assertTrue(course.dateOrderValidator(
            "23/11/2026 12:02",  // hide
            "23/11/2026 12:01",  // due
            "23/11/2026 12:00"   // publish
        ));
    }

    @Test
    void C10rejectsInvalidCalendarDate() {
        Course course = new Course();

        // 2026 is not a leap year.
        assertFalse(course.dateOrderValidator(
            "02/03/2026 12:00",
            "01/03/2026 12:00",
            "29/02/2026 12:00"
        ));
    }

    @Test
    void C10rejectsInvalidTime() {
        Course course = new Course();

        // Hours must be between 00 and 23.
        assertFalse(course.dateOrderValidator(
            "25/11/2026 12:00",
            "24/11/2026 24:00",
            "23/11/2026 12:00"
        ));
    }

    @Test
    void C10rejectsIncorrectFormat() {
        Course course = new Course();

        // The required date separator is a slash. 
        assertFalse(course.dateOrderValidator(
            "25/11/2026 12:00",
            "24/11/2026 12:00",
            "23-11-2026 12:00"
        ));
    }

    @Test
    void C11rejectsDueEqualToPublish() {
        Course course = new Course();

        assertFalse(course.dateOrderValidator(
            "23/11/2026 12:01",
            "23/11/2026 12:00",
            "23/11/2026 12:00"
        ));
    }

    @Test
    void C11rejectsHideEqualToDue() {
        Course course = new Course();

        assertFalse(course.dateOrderValidator(
            "23/11/2026 12:01",
            "23/11/2026 12:01",
            "23/11/2026 12:00"
        ));
    }
}