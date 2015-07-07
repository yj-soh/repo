package teammates.ui.template.instructor.shared;

import teammates.common.datatransfer.CommentAttributes;
import teammates.ui.template.shared.ElementTag;

public class CommentRow {
    protected String giverDetails;
    protected CommentAttributes comment;
    protected String recipientDetails;
    protected String creationTime;
    protected ElementTag editButton;
   
    public CommentRow() {
        
    }
    
    public CommentRow(String giverDetails, CommentAttributes comment,
                      String recipientDetails, String creationTime, ElementTag editButton) {
        this.giverDetails = giverDetails;
        this.comment = comment;
        this.recipientDetails = recipientDetails;
        this.creationTime = creationTime;
        this.editButton = editButton;
    }
    
    public String getGiverDetails() {
        return giverDetails;
    }
    
    public CommentAttributes getComment() {
        return comment;
    }
    
    public String getRecipientDetails() {
        return recipientDetails;
    }
    
    public String getCreationTime() {
        return creationTime;
    }
    
    public ElementTag getEditButton() {
        return editButton;
    }
}
