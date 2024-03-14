import javafx.scene.control.Label;

/**
 * The Welcome Panel containing introductory information for the user.
 */
public class WelcomePanel extends Panel {
    @Override
    protected void setupPanel() {
        panel.getChildren().add(new Label("Welcome Panel")); // Test statement to show panel works
        // Implement the setup for the Welcome panel here
    }
}
