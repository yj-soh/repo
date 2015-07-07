package teammates.ui.template.instructor.search;

import java.util.List;

/**
 * Contains feedback sessions which have comments containing the search keyword
 * entered by the instructor
 */
public class CommentsForResponsesTable {
    private List<FeedbackSessionRow> feedbackSessionRows;
    
    public CommentsForResponsesTable(List<FeedbackSessionRow> feedbackSessionRows) {
        this.feedbackSessionRows = feedbackSessionRows;
    }
    
    public List<FeedbackSessionRow> getFeedbackSessionRows() {
        return feedbackSessionRows;
    }

}
