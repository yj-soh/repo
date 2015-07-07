package teammates.ui.template.student.feedbackresults;

import java.util.List;

import teammates.ui.template.shared.FeedbackResponseComment;

public class Response {
    private String giverName;
    private String answer;
    private List<FeedbackResponseComment> comments;
    
    public Response(String giverName, String answer,
                                    List<FeedbackResponseComment> comments) {
        this.giverName = giverName;
        this.answer = answer;
        this.comments = comments;
    }

    public String getGiverName() {
        return giverName;
    }

    public String getAnswer() {
        return answer;
    }

    public List<FeedbackResponseComment> getComments() {
        return comments;
    }   
}
