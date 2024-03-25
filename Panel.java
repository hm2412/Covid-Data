import javafx.scene.layout.Pane;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base class representing a generic panel in the application.
 * Subclasses extend and implement the setupPanel() method.
 * 
 * @author Ahmet Taramis
 */
public abstract class Panel {
    
    protected Pane panel; // The primary container for UI components in the panel.
    protected LocalDate startDate = null; // Start date for filtering data.
    protected LocalDate endDate = null; // End date for filtering data.
    protected List<CovidData> filteredRecords = new ArrayList<>(); // List of CovidData records filtered by date.
    
    /**
     * Constructor for objects of class Panel
     */
    public Panel() {
        this.panel = new Pane(); // Default pane, can be changed to other types if needed in subclasses.
        setupPanel();
    }
    
    /**
     * Abstract method that subclasses must implement to set up their specific panel.
     */
    protected abstract void setupPanel();
    
    /**
     * Sets start & end dates used in panel.
     * @param startDate
     * @param endDate
     */
    protected void setDates(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    /**
     * Sets the filtered list to the panel's filteredRecords list.
     * @param filteredRecords List with filtered dates.
     */
    protected void setFilteredRecords(List<CovidData> filteredRecords) {
        this.filteredRecords = filteredRecords;
    }
    
    /**
     * This method allows external classes to access and manipulate the panel's UI components.
     * @return The Pane containing the panel's UI components.
     */
    public Pane getPanel() {
        return panel;
    }
}
