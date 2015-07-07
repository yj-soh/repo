package teammates.ui.template.instructor.feedbacks;

import java.util.List;

import teammates.ui.template.shared.ElementTag;

/**
 * Data model for the modal that can be found on the feedbacks page, 
 * which allows the user to copy a feedback session from an existing session.
 * Contains the options for course to copy to,
 * the name of feedback session to create,
 * and the list of feedback sessions that can be copied from. 
 * 
 */
public class CopyFromModal {
    
    private List<FeedbackSessionsTableRow> existingFeedbackSessions;
    private String fsName;
    private List<ElementTag> coursesSelectField;

    public CopyFromModal(List<FeedbackSessionsTableRow> existingFeedbackSessions, 
                                         String fsName, 
                                         List<ElementTag> coursesSelectField) {
        this.existingFeedbackSessions = existingFeedbackSessions;
        this.fsName = fsName;
        this.coursesSelectField = coursesSelectField;
    }
    
    public List<FeedbackSessionsTableRow> getExistingFeedbackSessions() {
        return existingFeedbackSessions;
    }
    
    public String getFsName() {
        return fsName;
    }

    public List<ElementTag> getCoursesSelectField() {
        return coursesSelectField;
    }
}
