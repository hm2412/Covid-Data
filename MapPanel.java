import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import java.util.List;
import java.util.ArrayList;

/**
 * The Map Panel displays a map visualizing COVID-19 data.
 */
public class MapPanel extends Panel {
    
    private Stage lastOpenedWindow = null;
    
    public MapPanel() {
        super();
        this.panel = new Pane(); // Change the layout style for the panel here
        setupPanel();
    }
    
    @Override
    protected void setupPanel() {
        panel.getChildren().add(new Label("Map Panel")); // Test statement to show panel works
        // Implement the setup for the Map panel here
        
        // Test button to show burough details
        Button testButton = new Button("Harrow");
        testButton.setOnAction(e -> showBoroughData("Harrow"));
        panel.getChildren().add(testButton);
    }
    
    /**
     * Filters the records for a specific borough.
     * @param boroughName The name of the borough for which to filter the records.
     * @return A list of CovidData records for the specified borough.
     */
    private List<CovidData> filterBoroughData(String boroughName) {
        List<CovidData> boroughRecords = new ArrayList<>();
        for (CovidData row : this.filteredRecords) {
            if (row.getBorough().equals(boroughName)) {
                boroughRecords.add(row);
            }
        }
        return boroughRecords;
    }
    
    /**
     * Opens a new window displaying data for the specified borough.
     * If a window is already open, it closes before the new one opens.
     * @param boroughName The name of the borough for which to display data.
     */
    private void showBoroughData(String boroughName) {
        // Close the last opened window if it exists and is showing
        if (lastOpenedWindow != null && lastOpenedWindow.isShowing()) {
            lastOpenedWindow.close();
        }
        
        // Gets filtered data for the borough
        List<CovidData> boroughRecords = new ArrayList<>(filterBoroughData(boroughName));
        
        // New window to show borough details
        Stage detailsStage = new Stage();
        detailsStage.setTitle(boroughName);
        
        Pane layout = new Pane();
        Scene scene = new Scene(layout, 400, 400);
                
        detailsStage.setScene(scene);
        lastOpenedWindow = detailsStage;
        detailsStage.show();
    }
}
