package teammates.ui.template.instructor.courseenroll;

import java.util.List;

import teammates.common.datatransfer.StudentAttributes;

public class ResultPanel {
    private String panelClass;
    private String messageForEnrollmentStatus;
    private List<StudentAttributes> studentList;
    
    public ResultPanel(String panelClass, String messageForEnrollmentStatus,
                                    List<StudentAttributes> studentList) {
        this.panelClass = panelClass;
        this.messageForEnrollmentStatus = messageForEnrollmentStatus;
        this.studentList = studentList;
    }
    
    public String getPanelClass() {
        return panelClass;
    }
    
    public String getMessageForEnrollmentStatus() {
        return messageForEnrollmentStatus;
    }
    
    public List<StudentAttributes> getStudentList() {
        return studentList;
    }
}
