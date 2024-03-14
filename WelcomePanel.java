import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;

/**
 * The Welcome Panel containing introductory information for the user.
 */
public class WelcomePanel extends Panel {
    
    public WelcomePanel() {
        super();
        this.panel = new BorderPane(); // Change the layout style for the panel
        setupPanel();
    }
    
    @Override
    protected void setupPanel() {
        panel.getChildren().add(new Label("Welcome Panel")); // Test statement to show panel works
        // Implement the setup for the Welcome panel here
    }
}
