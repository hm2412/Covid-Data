import javafx.scene.control.Label;

/**
 * The Map Panel intended to display a map visualizing COVID-19 data.
 */
public class MapPanel extends Panel {
    @Override
    protected void setupPanel() {
        panel.getChildren().add(new Label("Map Panel")); // Test statement to show panel works
        // Implement the setup for the Map panel here
    }
}
