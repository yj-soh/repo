package teammates.ui.template.instructor.studentlist;

public class FilterCourse {

    private String courseId;
    private String courseName;

    public FilterCourse(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

}
