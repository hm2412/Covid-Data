import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

/**
 * The Welcome Panel containing introductory information for the user.
 * 
 * @author Adam Jacobs, Khadija Hashim
 */
public class WelcomePanel extends Panel {
    
    public WelcomePanel() {
        super();
        setupPanel();
    }
    
    @Override
    protected void setupPanel() {
        panel.getChildren().clear();
        
        BorderPane root = new BorderPane();
        VBox contentBox = new VBox();
        
        // Adds title label
        Label titleLabel = new Label("London COVID-19 Statistics");
        titleLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
        titleLabel.setWrapText(true);
        
        // Adds an introduction to the program
        Label introLabel = new Label("This is an interactive application that visualises various COVID-19 statistics " + 
                                    "for London across a selected date range. This program is equipped with 3 panels " + 
                                    "to allow viewing of the data in various different forms:");
        introLabel.setStyle("-fx-font-size: 20px;");
        introLabel.setWrapText(true);
        
        // Adds brief descriptions for all panels
        Label mapLabel = new Label("Map Panel - Interactive Visualisation of COVID-19 death rates across boroughs");
        mapLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        mapLabel.setWrapText(true);
        Label statsLabel = new Label("Statistics Panel - View different COVID-19 figures over a selected date range");
        statsLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        statsLabel.setWrapText(true);
        Label graphLabel = new Label("Graph Panel - Displays a bar chart of new COVID-19 cases across London boroughs");
        graphLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        graphLabel.setWrapText(true);
        
        // Shows dates selected when valid range is selected, deleted one invalid date range is selceted
        Label dateLabel = new Label("");
        if (this.startDate != null && this.endDate != null) {
            dateLabel.setText("You have selected dates between: " + startDate.toString() + " - " + endDate.toString() + 
                                "\n Please use the navigation buttons below to switch between panels");
            dateLabel.setStyle("-fx-font-size: 20px;");
            dateLabel.setWrapText(true);
        }
        
        contentBox.getChildren().addAll(titleLabel, introLabel, mapLabel, statsLabel, graphLabel, dateLabel);
        
        // Adds a label displaying our names and k-numbers
        Label creditsLabel = new Label("Ahmet Taramis K22038914, Haleema Mohammed K23035982, Adam Jacobs K23049198, Khadija Hashim K23104223");
        creditsLabel.setStyle("-fx-font-size: 15px; -fx-font-style: italic;");
        creditsLabel.setWrapText(true);
        
        // Adjust layout and allow for resizability
        contentBox.setMargin(introLabel, new Insets(0, 0, 50, 0));
        contentBox.setMargin(dateLabel, new Insets(50, 0, 0, 0));
        root.setCenter(contentBox);
        root.setBottom(creditsLabel);
        root.setAlignment(creditsLabel, Pos.CENTER);
        root.setMargin(creditsLabel, new Insets(10, 0, 10, 0));
        
        root.prefWidthProperty().bind(panel.widthProperty());
        root.prefHeightProperty().bind(panel.heightProperty());
        panel.getChildren().addAll(root);
    }
}
