package teammates.ui.template.student.feedbackresults;

import java.util.List;

public class QuestionWithResponses {
    private QuestionDetails questionDetails;
    private List<ResponseTable> responseTables;
    
    public QuestionWithResponses(
                                    QuestionDetails questionDetails, 
                                    List<ResponseTable> responseTables) {
        this.questionDetails = questionDetails;
        this.responseTables = responseTables;
    }

    public QuestionDetails getQuestionDetails() {
        return questionDetails;
    }

    public List<ResponseTable> getResponseTables() {
        return responseTables;
    }

}
