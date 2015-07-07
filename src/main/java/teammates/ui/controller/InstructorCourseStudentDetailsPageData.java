package teammates.ui.controller;

import teammates.common.datatransfer.AccountAttributes;
import teammates.common.datatransfer.StudentAttributes;
import teammates.common.datatransfer.StudentProfileAttributes;
import teammates.ui.template.instructor.coursestudentdetails.InfoTable;
import teammates.ui.template.instructor.coursestudentdetails.Profile;

public class InstructorCourseStudentDetailsPageData extends PageData {
    
    private Profile studentProfile;
    private InfoTable studentInfoTable;
    private String commentRecipient;

    public InstructorCourseStudentDetailsPageData(AccountAttributes account, StudentAttributes student,
            StudentProfileAttributes studentProfile, boolean isAbleToAddComment, boolean hasSection,
            String commentRecipient) {
        super(account);
        if (studentProfile != null) {
            String pictureUrl = getPictureUrl(studentProfile.pictureKey);
            this.studentProfile = new Profile(student.name, studentProfile, pictureUrl);
        }
        this.studentInfoTable = new InfoTable(student, isAbleToAddComment, hasSection);
        this.commentRecipient = commentRecipient;
    }
    
    public Profile getStudentProfile() {
        return studentProfile;
    }
    
    public InfoTable getStudentInfoTable() {
        return studentInfoTable;
    }
    
    public String getCommentRecipient() {
        return commentRecipient;
    }
    
    public boolean isCommentBoxShown() {
        return commentRecipient != null && (commentRecipient.equals("student")
                                            || commentRecipient.equals("team")
                                            || commentRecipient.equals("section"));
    }
}
