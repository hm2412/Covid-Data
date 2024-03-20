import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import java.util.List;
import java.util.ArrayList;

/**
 * The Welcome Panel containing introductory information for the user.
 */
public class WelcomePanel extends Panel {
    
    public WelcomePanel() {
        super();
        this.panel = new Pane(); // Change the layout style for the panel here
        setupPanel();
    }
    
    @Override
    protected void setupPanel() {
        panel.getChildren().add(new Label("Welcome Panel")); // Test statement to show panel works
        // Implement the setup for the Welcome panel here
    }
}
