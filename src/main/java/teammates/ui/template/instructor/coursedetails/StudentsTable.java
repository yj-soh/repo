package teammates.ui.template.instructor.coursedetails;

import java.util.ArrayList;
import java.util.List;

public class StudentsTable {
    private List<StudentsTableRow> rows;
    
    public StudentsTable() {
        rows = new ArrayList<StudentsTableRow>();
    }
    
    public List<StudentsTableRow> getRows() {
        return rows;
    }

    public void setRows(List<StudentsTableRow> studentTableRows) {
        rows = studentTableRows;
        
    }
}
