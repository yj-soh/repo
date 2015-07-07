package teammates.ui.template.instructor.search;

import java.util.List;

/**
 * A table contains details of students in a course whose student details contain the search
 * keyword entered by the instructor.
 */
public class StudentsTable {
    private String courseId;
    private List<StudentRow> studentRows;
    
    public StudentsTable(String courseId, List<StudentRow> studentRows) {
        this.courseId = courseId;
        this.studentRows = studentRows;
    }
    
    public String getCourseId() {
        return courseId;
    }
    
    public List<StudentRow> getStudentRows() {
        return studentRows;
    }
}
