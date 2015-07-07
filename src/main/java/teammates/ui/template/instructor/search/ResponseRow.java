package teammates.ui.template.instructor.search;

import java.util.List;

import teammates.ui.template.shared.FeedbackResponseComment;

public class ResponseRow {
    private String giverName;
    private String recipientName;
    private String response;
    private List<FeedbackResponseComment> feedbackResponseComments;
    
    public ResponseRow(String giverName, String recipientName, 
                           String response, List<FeedbackResponseComment> feedbackResponseComments) {
        this.giverName = giverName;
        this.recipientName = recipientName;
        this.response = response;
        this.feedbackResponseComments = feedbackResponseComments;
    }
    
    public String getGiverName() {
        return giverName;
    }
    
    public String getRecipientName() {
        return recipientName;
    }
    
    public String getResponse() {
        return response;
    }
    
    public List<FeedbackResponseComment> getFeedbackResponseComments() {
        return feedbackResponseComments;
    }
}
