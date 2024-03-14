import javafx.scene.layout.Pane;

/**
 * Abstract base class representing a generic panel in the application.
 * Subclasses extend and implement the setupPanel() method.
 */
public abstract class Panel {
    
    protected Pane panel;

    /**
     * Constructor for objects of class Panel
     */
    public Panel() {
        this.panel = new Pane(); // Default pane, you can change it to other types if needed in subclasses
        setupPanel();
    }
    
    protected abstract void setupPanel();
    
    public Pane getPanel() {
        return panel;
    }
}
