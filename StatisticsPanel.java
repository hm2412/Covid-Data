import javafx.scene.layout.Pane;
import javafx.scene.control.Label;

/**
 * The Statistics Panel designed to show various COVID-19 statistics.
 */
public class StatisticsPanel extends Panel {
    
    public StatisticsPanel() {
        super();
        this.panel = new Pane(); // Change the layout style for the panel here
        setupPanel();
    }
    
    @Override
    protected void setupPanel() {
        panel.getChildren().add(new Label("Statistics Panel")); // Test statement to show panel works
        // Implement the setup for the Map panel here
    }
}
