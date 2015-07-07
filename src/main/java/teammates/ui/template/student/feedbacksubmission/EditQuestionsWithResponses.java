package teammates.ui.template.student.feedbacksubmission;

import java.util.List;

public class EditQuestionsWithResponses {
    private EditQuestion question;
    private List<EditResponse> responses;
    private int numOfResponseBoxes;
    private int maxResponsesPossible;
    
    public EditQuestionsWithResponses(EditQuestion question,
                                    List<EditResponse> responses, int numOfResponseBoxes,
                                    int maxResponsesPossible) {
        this.question = question;
        this.responses = responses;
        this.numOfResponseBoxes = numOfResponseBoxes;
        this.maxResponsesPossible = maxResponsesPossible;
    }

    public EditQuestion getQuestion() {
        return question;
    }

    public List<EditResponse> getResponses() {
        return responses;
    }
    
    public int getNumOfResponseBoxes() {
        return numOfResponseBoxes;
    }
    
    public int getMaxResponsesPossible() {
        return maxResponsesPossible;
    }
}
