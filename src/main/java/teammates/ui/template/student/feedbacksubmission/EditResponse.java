package teammates.ui.template.student.feedbacksubmission;

import java.util.List;

public class EditResponse {
    private int responseIndx;
    private boolean isExistingResponse;
    private List<String> recipientOptionsForQuestion;
    private String submissionFormHtml;
    private String responseId;
    
    public EditResponse(int responseIndx, boolean isExistingResponse,
                                    List<String> recipientOptionsForQuestion, String submissionFormHtml, String responseId) {
        
        this.responseIndx = responseIndx;
        this.isExistingResponse = isExistingResponse;
        this.recipientOptionsForQuestion = recipientOptionsForQuestion;
        this.submissionFormHtml = submissionFormHtml;
        this.responseId = responseId;
    }
    
    public int getResponseIndx() {
        return responseIndx;
    }
    
    public boolean isExistingResponse() {
        return isExistingResponse;
    }
    
    public List<String> getRecipientOptionsForQuestion() {
        return recipientOptionsForQuestion;
    }
    
    public String getSubmissionFormHtml() {
        return submissionFormHtml;
    }
    
    public String getResponseId() {
        return responseId;
    }
}
