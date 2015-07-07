package teammates.ui.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import teammates.common.datatransfer.AccountAttributes;
import teammates.common.datatransfer.CourseAttributes;
import teammates.common.datatransfer.FeedbackSessionAttributes;
import teammates.common.datatransfer.InstructorAttributes;
import teammates.common.exception.EntityDoesNotExistException;
import teammates.common.util.Assumption;
import teammates.common.util.Const;
import teammates.common.util.TimeHelper;
import teammates.ui.template.instructor.feedbacks.CopyFromModal;
import teammates.ui.template.instructor.feedbacks.FeedbackSessionsTable;
import teammates.ui.template.instructor.feedbacks.FeedbackSessionsTableRow;
import teammates.ui.template.instructor.feedbacks.FeedbackSessionActions;
import teammates.ui.template.instructor.shared.AdditionalSettingsFormSegment;
import teammates.ui.template.instructor.shared.FeedbackSessionsForm;
import teammates.ui.template.shared.ElementTag;

public class InstructorFeedbacksPageData extends PageData {
    
    private static final int MAX_CLOSED_SESSION_STATS = 5;
    

    // Flag for deciding if loading the sessions table, or the new sessions form.
    // if true -> loads the sessions table, else load the form
    private boolean isUsingAjax;
    
    private FeedbackSessionsTable fsList;
    private FeedbackSessionsForm newFsForm;
    private CopyFromModal copyFromModal;
    

    public InstructorFeedbacksPageData(AccountAttributes account) {
        super(account);
        
    }

    public boolean isUsingAjax() {
        return isUsingAjax;
    }


    /**
     * Initializes the PageData
     * @param courses                    courses that the user is an instructor of 
     * @param courseIdForNewSession      the course id to automatically select in the dropdown
     * @param existingFeedbackSessions   list of existing feedback sessions 
     * @param instructors                a map of courseId to the instructorAttributes for the current user
     * @param defaultFormValues          the feedback session which values are used as the default values in the form
     * @param feedbackSessionType        "TEAMEVALUATION" or "STANDARD"
     * @param highlightedFeedbackSession the feedback session to highlight in the sessions table
     * @param courseIdToSectionNameMap   map of course id to names of sections in the course 
     */
    public void init(List<CourseAttributes> courses, String courseIdForNewSession, 
                     List<FeedbackSessionAttributes> existingFeedbackSessions,
                     Map<String, InstructorAttributes> instructors,
                     FeedbackSessionAttributes defaultFormValues, String feedbackSessionType, 
                     String highlightedFeedbackSession, Map<String, List<String>> courseIdToSectionNameMap) {

        FeedbackSessionAttributes.sortFeedbackSessionsByCreationTimeDescending(existingFeedbackSessions);
        
        buildNewForm(courses, courseIdForNewSession, 
                     instructors, defaultFormValues, 
                     feedbackSessionType, highlightedFeedbackSession);
        
        
        buildFsList(courseIdForNewSession, existingFeedbackSessions, 
                    instructors, highlightedFeedbackSession,
                    courseIdToSectionNameMap);
        
        
        buildCopyFromModal(courses, courseIdForNewSession, existingFeedbackSessions, instructors,
                           defaultFormValues, highlightedFeedbackSession,
                           courseIdToSectionNameMap);
    }
    
    public void initWithoutHighlightedRow(List<CourseAttributes> courses, String courseIdForNewSession, 
                                          List<FeedbackSessionAttributes> existingFeedbackSessions,
                                          Map<String, InstructorAttributes> instructors,
                                          FeedbackSessionAttributes defaultFormValues, String feedbackSessionType, 
                                          Map<String, List<String>> courseIdToSectionNameMap) {

        init(courses, courseIdForNewSession, existingFeedbackSessions, instructors, defaultFormValues, feedbackSessionType, null, courseIdToSectionNameMap);
    }
    
    public void initWithoutDefaultFormValues(List<CourseAttributes> courses, String courseIdForNewSession, 
                                             List<FeedbackSessionAttributes> existingFeedbackSessions,
                                             Map<String, InstructorAttributes> instructors,
                                             String highlightedFeedbackSession, Map<String, List<String>> courseIdToSectionNameMap) {

         init(courses, courseIdForNewSession, existingFeedbackSessions, instructors, null, null, highlightedFeedbackSession, courseIdToSectionNameMap);
    }
    
    
    

    private void buildCopyFromModal(List<CourseAttributes> courses, String courseIdForNewSession,
                                    List<FeedbackSessionAttributes> existingFeedbackSessions,
                                    Map<String, InstructorAttributes> instructors,
                                    FeedbackSessionAttributes newFeedbackSession,
                                    String feedbackSessionNameForSessionList,
                                    Map<String, List<String>> courseIdToSectionNameMap) {
        List<FeedbackSessionAttributes> filteredFeedbackSessions = new ArrayList<FeedbackSessionAttributes>();
        for (FeedbackSessionAttributes existingFeedbackSession : existingFeedbackSessions) {
            if (instructors.get(existingFeedbackSession.courseId)
                           .isAllowedForPrivilege(
                                  Const.ParamsNames.INSTRUCTOR_PERMISSION_MODIFY_SESSION)) {
                filteredFeedbackSessions.add(existingFeedbackSession);
            }
        }
            

        List<FeedbackSessionsTableRow> filteredFeedbackSessionsRow = convertFeedbackSessionAttributesToSessionRows(
                                                                        filteredFeedbackSessions,
                                                                        instructors, feedbackSessionNameForSessionList,
                                                                        courseIdForNewSession, courseIdToSectionNameMap);
        
        String fsName = newFeedbackSession != null ? newFeedbackSession.feedbackSessionName : "";
        copyFromModal = new CopyFromModal(filteredFeedbackSessionsRow, 
                                                          fsName, 
                                                          getCourseIdOptions(courses, courseIdForNewSession, 
                                                                             instructors, newFeedbackSession));
    }

    private void buildFsList(String courseIdForNewSession, List<FeedbackSessionAttributes> existingFeedbackSessions,
                             Map<String, InstructorAttributes> instructors, String feedbackSessionNameForSessionList, 
                             Map<String, List<String>> courseIdToSectionNameMap) {
        
        List<FeedbackSessionsTableRow> existingFeedbackSessionsRow = convertFeedbackSessionAttributesToSessionRows(
                                                                             existingFeedbackSessions, instructors, 
                                                                             feedbackSessionNameForSessionList, courseIdForNewSession,
                                                                             courseIdToSectionNameMap);
        fsList = new FeedbackSessionsTable(existingFeedbackSessionsRow);
    }

    private void buildNewForm(List<CourseAttributes> courses, String courseIdForNewSession,
                              Map<String, InstructorAttributes> instructors,
                              FeedbackSessionAttributes newFeedbackSession, String feedbackSessionType,
                              String feedbackSessionNameForSessionList) {
        
        List<String> courseIds = new ArrayList<String>();
        for (CourseAttributes course : courses) {
            courseIds.add(course.id);
        }
        
        
        newFsForm = buildBasicForm(courses, courseIdForNewSession, instructors, 
                                   newFeedbackSession, feedbackSessionType,
                                   feedbackSessionNameForSessionList, courseIds);
        
        AdditionalSettingsFormSegment additionalSettings = buildFormAdditionalSettings(newFeedbackSession);
        newFsForm.setAdditionalSettings(additionalSettings);                  
        
    }

    private FeedbackSessionsForm buildBasicForm(List<CourseAttributes> courses, String courseIdForNewSession,
                                                Map<String, InstructorAttributes> instructors,
                                                FeedbackSessionAttributes newFeedbackSession, String feedbackSessionType,
                                                String feedbackSessionNameForSessionList, List<String> courseIds) {
        FeedbackSessionsForm newFsForm = new FeedbackSessionsForm();
        newFsForm.setCourseIdForNewSession(courseIdForNewSession);
        
        newFsForm.setFsName(newFeedbackSession == null ? "" : newFeedbackSession.feedbackSessionName);
        
        newFsForm.setCourses(courseIds);
        
        newFsForm.setFeedbackSessionTypeOptions(getFeedbackSessionTypeOptions(feedbackSessionType));

        newFsForm.setFeedbackSessionNameForSessionList(feedbackSessionNameForSessionList);
        
        newFsForm.setCoursesSelectField(getCourseIdOptions(courses,  courseIdForNewSession, 
                                                           instructors, newFeedbackSession));
        
        newFsForm.setTimezoneSelectField(getTimeZoneOptionsAsElementTags(newFeedbackSession));
        
        
        newFsForm.setInstructions(newFeedbackSession == null ?
                                  "Please answer all the given questions." :
                                  sanitizeForHtml(newFeedbackSession.instructions.getValue()));
        
        newFsForm.setFsStartDate(newFeedbackSession == null ?
                                 TimeHelper.formatDate(TimeHelper.getNextHour()) :
                                 TimeHelper.formatDate(newFeedbackSession.startTime));
        
        Date date;
        date = newFeedbackSession == null ? null : newFeedbackSession.startTime;
        newFsForm.setFsStartTimeOptions(getTimeOptionsAsElementTags(date));
        
        newFsForm.setFsEndDate(newFeedbackSession == null ?
                               "" : 
                               TimeHelper.formatDate(newFeedbackSession.endTime));
        
        
        date = newFeedbackSession == null ? null : newFeedbackSession.endTime;
        newFsForm.setFsEndTimeOptions(getTimeOptionsAsElementTags(date));
        
        newFsForm.setGracePeriodOptions(getGracePeriodOptionsAsElementTags(newFeedbackSession));
        
        newFsForm.setSubmitButtonDisabled(courses.isEmpty());
        
        return newFsForm;
    }

    private AdditionalSettingsFormSegment buildFormAdditionalSettings(
                                                                    FeedbackSessionAttributes newFeedbackSession) {
        Date date;
        AdditionalSettingsFormSegment additionalSettings = new AdditionalSettingsFormSegment(); 
        boolean hasSessionVisibleDate = newFeedbackSession != null 
                                        && !TimeHelper.isSpecialTime(newFeedbackSession.sessionVisibleFromTime);
        additionalSettings.setSessionVisibleDateButtonChecked(hasSessionVisibleDate);
        additionalSettings.setSessionVisibleDateValue(hasSessionVisibleDate ? 
                                                      TimeHelper.formatDate(newFeedbackSession.sessionVisibleFromTime) :
                                                      "");
        additionalSettings.setSessionVisibleDateDisabled(!hasSessionVisibleDate);
        
        date = hasSessionVisibleDate ? newFeedbackSession.sessionVisibleFromTime : null;   
        additionalSettings.setSessionVisibleTimeOptions(getTimeOptionsAsElementTags(date));
        
        additionalSettings.setSessionVisibleAtOpenChecked(newFeedbackSession == null 
                                                           || Const.TIME_REPRESENTS_FOLLOW_OPENING.equals(
                                                                   newFeedbackSession.sessionVisibleFromTime));
        
        additionalSettings.setSessionVisiblePrivateChecked(newFeedbackSession != null 
                                                           && Const.TIME_REPRESENTS_NEVER.equals(
                                                               newFeedbackSession.sessionVisibleFromTime));
                        
        boolean hasResultVisibleDate = newFeedbackSession != null 
                                       && !TimeHelper.isSpecialTime(newFeedbackSession.resultsVisibleFromTime);
        
        additionalSettings.setResponseVisibleDateChecked(hasResultVisibleDate);
        
        additionalSettings.setResponseVisibleDateValue(hasResultVisibleDate ? 
                                                       TimeHelper.formatDate(newFeedbackSession.resultsVisibleFromTime) :
                                                       "");
        
        additionalSettings.setResponseVisibleDisabled(!hasResultVisibleDate);
        
        date = hasResultVisibleDate ? newFeedbackSession.resultsVisibleFromTime :  null;
        additionalSettings.setResponseVisibleTimeOptions(getTimeOptionsAsElementTags(date));
        
        additionalSettings.setResponseVisibleImmediatelyChecked((newFeedbackSession != null 
                                                                && Const.TIME_REPRESENTS_FOLLOW_VISIBLE.equals(newFeedbackSession.resultsVisibleFromTime)));
        
        additionalSettings.setResponseVisiblePublishManuallyChecked(
                                 (newFeedbackSession == null 
                                  || Const.TIME_REPRESENTS_LATER.equals(newFeedbackSession.resultsVisibleFromTime) 
                                  || Const.TIME_REPRESENTS_NOW.equals(newFeedbackSession.resultsVisibleFromTime)));
        
        additionalSettings.setResponseVisibleNeverChecked((newFeedbackSession != null  
                                                            && Const.TIME_REPRESENTS_NEVER.equals(newFeedbackSession.resultsVisibleFromTime)));
        return additionalSettings;
    }
    
    
    private List<FeedbackSessionsTableRow> convertFeedbackSessionAttributesToSessionRows(
                                         List<FeedbackSessionAttributes> sessions, 
                                         Map<String, InstructorAttributes> instructors, 
                                         String feedbackSessionNameForSessionList, String courseIdForNewSession,
                                         Map<String, List<String>> courseIdToSectionNameMap) {

        
        List<FeedbackSessionsTableRow> rows = new ArrayList<FeedbackSessionsTableRow>();
        int displayedStatsCount = 0;
        
        Map<String, List<String>> courseIdSectionNamesMap = new HashMap<String, List<String>>();
        try {
            courseIdSectionNamesMap = getCourseIdSectionNamesMap(sessions);
        } catch (EntityDoesNotExistException wonthappen) {
            /*
             * EDNEE is thrown if the courseId of any of the sessions is not valid.
             * However, the sessions passed to this method come from course objects which are
             * retrieved through database query, thus impossible for the courseId to be invalid.
             */
            Assumption.fail("Course that should exist is found to be non-existent");
        }

        for (FeedbackSessionAttributes session : sessions) {
            String courseId = session.courseId;
            String name = sanitizeForHtml(session.feedbackSessionName);
            String tooltip = getInstructorHoverMessageForFeedbackSession(session);
            String status = getInstructorStatusForFeedbackSession(session);
            String href = getFeedbackSessionStatsLink(session.courseId, session.feedbackSessionName);
            
            String recent = "";
            if (session.isOpened() || session.isWaitingToOpen()) {
                recent = " recent";
            } else if (displayedStatsCount < InstructorFeedbacksPageData.MAX_CLOSED_SESSION_STATS
                       && !TimeHelper.isOlderThanAYear(session.createdTime)) {
                recent = " recent";
                ++displayedStatsCount;
            }
            
            FeedbackSessionActions actions = getInstructorFeedbackSessionActions(session, false,
                                                                                           instructors.get(courseId),
                                                                                 courseIdSectionNamesMap.get(courseId));
            
            ElementTag elementAttributes ;
            if (session.courseId.equals(courseIdForNewSession) && session.feedbackSessionName.equals(feedbackSessionNameForSessionList)) {
                elementAttributes = new ElementTag("class", "sessionsRow warning");
            } else {
                elementAttributes = new ElementTag("class", "sessionsRow");
            }
            
            rows.add(new FeedbackSessionsTableRow(courseId, name, tooltip, status, href, recent, actions, elementAttributes));
        }
        
        return rows;
    }
    
    public FeedbackSessionsTable getFsList() {
        return fsList;
    }
    
    public FeedbackSessionsForm getNewFsForm() {
        return newFsForm;
    }
    
    public CopyFromModal getCopyFromModal() {
        return copyFromModal;
    }

    private ArrayList<ElementTag> getTimeZoneOptionsAsElementTags(FeedbackSessionAttributes fs){
        return getTimeZoneOptionsAsElementTags(fs == null ? 
                                               Const.DOUBLE_UNINITIALIZED : 
                                               fs.timeZone);
    }

    
    private ArrayList<ElementTag> getGracePeriodOptionsAsElementTags(FeedbackSessionAttributes fs) {
        return getGracePeriodOptionsAsElementTags(fs == null ? 
                                                  Const.INT_UNINITIALIZED : 
                                                  fs.gracePeriod);
    }
    
    /**
     * Creates a list of options (STANDARD and TEAMEVALUATION). If defaultSessionType is null,
     *     TEAMEVALUATION is selected by default.
     * @param defaultSessionType  either STANDARD or TEAMEVALUATION, the option that is selected on page load
     */
    private ArrayList<ElementTag> getFeedbackSessionTypeOptions(String defaultSessionType) {
        ArrayList<ElementTag> result = new ArrayList<ElementTag>();
        
        ElementTag standardFeedbackSession = createOption("Session with your own questions", "STANDARD", 
                                                          defaultSessionType != null && defaultSessionType.equals("STANDARD"));
        ElementTag evaluationFeedbackSession = createOption("Team peer evaluation session", "TEAMEVALUATION", 
                                                            defaultSessionType == null || defaultSessionType.equals("TEAMEVALUATION")); 
        
        result.add(standardFeedbackSession);
        result.add(evaluationFeedbackSession);
        
        return result;
    }

    private ArrayList<ElementTag> getCourseIdOptions(List<CourseAttributes> courses, String  courseIdForNewSession,
                                                     Map<String, InstructorAttributes> instructors,
                                                     FeedbackSessionAttributes newFeedbackSession) {
        ArrayList<ElementTag> result = new ArrayList<ElementTag>();

        for (CourseAttributes course : courses) {
            
            // True if this is a submission of the filled 'new session' form
            // for this course:
            boolean isFilledFormForSessionInThisCourse =
                    newFeedbackSession != null && course.id.equals(newFeedbackSession.courseId);

            // True if this is for displaying an empty form for creating a
            // session for this course:
            boolean isEmptyFormForSessionInThisCourse =
                    courseIdForNewSession != null && course.id.equals(courseIdForNewSession);
            

            if (instructors.get(course.id).isAllowedForPrivilege(
                    Const.ParamsNames.INSTRUCTOR_PERMISSION_MODIFY_SESSION)) {
                ElementTag option = createOption(course.id, course.id,  
                                                 (isFilledFormForSessionInThisCourse || isEmptyFormForSessionInThisCourse));
                result.add(option);
            }
        }
        
        // Add "No active courses" option if there are no active courses
        if (result.isEmpty()) {
            ElementTag blankOption = createOption("No active courses!", "", true);
            result.add(blankOption);
        }
        
        return result;
    }
    

    public void setUsingAjax(boolean isUsingAjax) {
        this.isUsingAjax = isUsingAjax;
    }


}
