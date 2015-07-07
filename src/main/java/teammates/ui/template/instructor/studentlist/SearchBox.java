package teammates.ui.template.instructor.studentlist;

import teammates.common.util.Sanitizer;

public class SearchBox {

    private String instructorSearchLink;
    private String searchKey;
    private String googleId;

    public SearchBox(String instructorSearchLink, String searchKey, String googleId) {
        this.instructorSearchLink = instructorSearchLink;
        this.searchKey = searchKey == null ? "" : Sanitizer.sanitizeForHtml(searchKey);
        this.googleId = googleId;
    }

    public String getInstructorSearchLink() {
        return instructorSearchLink;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public String getGoogleId() {
        return googleId;
    }

}
