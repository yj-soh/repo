package teammates.ui.template.student.feedbackresults;

import java.util.List;

public class ResponseTable {
    private String recipientName;
    private List<Response> responses;

    public ResponseTable(String recipientName, List<Response> responses) {
        this.recipientName = recipientName;
        this.responses = responses;
    }

    public String getRecipientName() {
        return recipientName;
    }
    
    public List<Response> getResponses() {
        return responses;
    }
    
    public boolean isGiverNameYou() {
        return (!responses.isEmpty() && responses.get(0).getGiverName().equals("You"));
    }
}
