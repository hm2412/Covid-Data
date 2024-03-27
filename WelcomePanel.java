import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.geometry.Insets;


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
        VBox root = new VBox();
        //Adds title label to border pane
        Label titleLabel = new Label("London COVID-19 Statistics");
        titleLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
        
        //Adds an introduction to the program
        Label introLabel = new Label("This is an interactive application that visualises various COVID-19 statistics for London across a selected date range. This program is equipped with 3 panels to allow viewing of the data in various different forms:");
        introLabel.setStyle("-fx-font-size: 20px;");
        introLabel.setWrapText(true);
        
        //Adds brief descriptions for all panels
        Label mapLabel = new Label("Map Panel - Interactive Visualisation of COVID-19 death rates across boroughs");
        mapLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Label statsLabel = new Label("Statistics Panel - View different COVID-19 figures over a selected date range");
        statsLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Label graphLabel = new Label("Graph Panel - Displays a bar chart of new COVID-19 cases across London boroughs");
        graphLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        VBox labelVbox = new VBox(10);
        labelVbox.getChildren().addAll(mapLabel, statsLabel, graphLabel); 
        
        //Adds a label displaying our names and k numbers
        Label creditsLabel = new Label("Ahmet Taramis k22038914, Haleema Mohammed k23035982, Adam Jacobs k23049198, Khadija Hashim k23104223");
        creditsLabel.setStyle("-fx-font-size: 15px; -fx-font-style: italic;");
        
        root.getChildren().addAll(titleLabel, introLabel, labelVbox, creditsLabel);
        root.setPrefSize(800,600);
        root.setMargin(introLabel, new Insets(0, 0, 25, 0));
        root.setMargin(labelVbox, new Insets(0, 0, 250, 0));
        panel.getChildren().addAll(root);
    }
}
