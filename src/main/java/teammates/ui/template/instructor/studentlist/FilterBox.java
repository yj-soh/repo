package teammates.ui.template.instructor.studentlist;

import java.util.List;

public class FilterBox {

    private List<FilterCourse> courses;
    private boolean displayArchive;

    public FilterBox(List<FilterCourse> courses,
                                          boolean displayArchive) {
        this.courses = courses;
        this.displayArchive = displayArchive;
    }

    public List<FilterCourse> getCourses() {
        return courses;
    }

    public boolean isDisplayArchive() {
        return displayArchive;
    }

}
