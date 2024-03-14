import javafx.scene.layout.Pane;

/**
 * Write a description of class Panel here.
 * 
 * 
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
