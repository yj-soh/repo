package teammates.ui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import teammates.common.datatransfer.AccountAttributes;
import teammates.common.datatransfer.CourseAttributes;
import teammates.ui.datatransfer.InstructorStudentListPageCourseData;
import teammates.ui.template.instructor.studentlist.FilterBox;
import teammates.ui.template.instructor.studentlist.FilterCourse;
import teammates.ui.template.instructor.studentlist.SearchBox;
import teammates.ui.template.instructor.studentlist.StudentsTableCourse;

public class InstructorStudentListPageData extends PageData {

    private SearchBox searchBox;
    private FilterBox filterBox;
    private List<StudentsTableCourse> studentsTable;
    private int numOfCourses;
    
    public InstructorStudentListPageData(AccountAttributes account, String searchKey,
                                         boolean displayArchive,
                                         Map<String, String> numStudents,
                                         List<InstructorStudentListPageCourseData> coursesToDisplay) {
        super(account);
        this.searchBox = new SearchBox(getInstructorSearchLink(), searchKey, account.googleId);
        List<FilterCourse> coursesForFilter =
                                        new ArrayList<FilterCourse>();
        List<StudentsTableCourse> coursesForStudentsTable =
                                        new ArrayList<StudentsTableCourse>();
        for (InstructorStudentListPageCourseData islpcData : coursesToDisplay) {
            CourseAttributes course = islpcData.course;
            coursesForFilter.add(new FilterCourse(course.id, course.name));
            coursesForStudentsTable.add(
                                            new StudentsTableCourse(islpcData.isCourseArchived,
                                                                            course.id, course.name, account.googleId,
                                                                            numStudents.get(course.id),
                                                                            getInstructorCourseEnrollLink(course.id),
                                                                            islpcData.isInstructorAllowedToModify));
        }
        this.filterBox = new FilterBox(coursesForFilter, displayArchive);
        this.studentsTable = coursesForStudentsTable;
        this.numOfCourses = coursesForFilter.size();
    }
    
    public SearchBox getSearchBox() {
        return searchBox;
    }

    public FilterBox getFilterBox() {
        return filterBox;
    }
    
    public List<StudentsTableCourse> getStudentsTable() {
        return studentsTable;
    }

    public int getNumOfCourses() {
        return numOfCourses;
    }

}
