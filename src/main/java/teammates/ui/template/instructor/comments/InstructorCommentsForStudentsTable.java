package teammates.ui.template.instructor.comments;

import java.util.List;

import teammates.ui.template.instructor.shared.CommentRow;

public class InstructorCommentsForStudentsTable {
    private String giverEmail;
    private String giverName;
    private List<CommentRow> rows;
    
    public InstructorCommentsForStudentsTable(String giverEmail, String giverName, List<CommentRow> rows) {
        this.giverEmail = giverEmail;
        this.giverName = giverName;
        this.rows = rows;
    }
    
    public String getGiverEmail() {
        return giverEmail;
    }
    
    public String getGiverName() {
        return giverName;
    }
    
    public List<CommentRow> getRows() {
        return rows;
    }
}
