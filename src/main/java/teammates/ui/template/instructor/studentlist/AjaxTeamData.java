package teammates.ui.template.instructor.studentlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import teammates.common.datatransfer.StudentAttributes;
import teammates.common.datatransfer.TeamDetailsBundle;
import teammates.common.util.Sanitizer;

public class AjaxTeamData {

    private String teamName;
    private List<AjaxStudentData> students;

    public AjaxTeamData(TeamDetailsBundle team, Map<String, String> emailPhotoUrlMapping,
                                             String googleId) {
        this.teamName = Sanitizer.sanitizeForHtml(team.name);
        List<AjaxStudentData> studentsDetails =
                                        new ArrayList<AjaxStudentData>();
        for (StudentAttributes student: team.students) {
            studentsDetails.add(new AjaxStudentData(googleId, student.name,
                                                                         student.email, student.course,
                                                             emailPhotoUrlMapping.get(student.email)));
        }
        this.students = studentsDetails;
    }

    public String getTeamName() {
        return teamName;
    }

    public List<AjaxStudentData> getStudents() {
        return students;
    }

}
