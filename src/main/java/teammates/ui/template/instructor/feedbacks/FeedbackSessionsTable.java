package teammates.ui.template.instructor.feedbacks;

import java.util.List;

public class FeedbackSessionsTable {
    
    private List<FeedbackSessionsTableRow> existingFeedbackSessions;
    
    public FeedbackSessionsTable(List<FeedbackSessionsTableRow> existingFeedbackSessions) {
        this.existingFeedbackSessions = existingFeedbackSessions;
    }
    
    public List<FeedbackSessionsTableRow> getExistingFeedbackSessions() {
        return existingFeedbackSessions;
    }
    
}
