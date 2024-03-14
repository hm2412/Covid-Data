import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;

/**
 * The Map Panel intended to display a map visualizing COVID-19 data.
 */
public class MapPanel extends Panel {
    
    public MapPanel() {
        super();
        this.panel = new BorderPane(); // Change the layout style for the panel
        setupPanel();
    }
    
    @Override
    protected void setupPanel() {
        panel.getChildren().add(new Label("Map Panel")); // Test statement to show panel works
        // Implement the setup for the Map panel here
    }
}
