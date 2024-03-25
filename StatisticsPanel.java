import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import java.time.*;
import javafx.application.Platform;
import java.util.function.Function;
import java.util.List;
import java.util.ArrayList;

/**
 * The Statistics Panel designed to show various COVID-19 statistics.
 * 
 * @author Adam Jacobs, Khadija Hashim
 */
public class StatisticsPanel extends Panel {
    
    // containers for buttons and statistics view
    private VBox statistic1Container;
    private VBox statistic2Container;
    private VBox statistic3Container;
    
    // buttons 
    private Button btnNextStat;
    private Button btnPrevStat;
    
    // counter to keep track of different stats
    private int currentStatIndex = 0;
    
    public StatisticsPanel() {
        super(); 
        setupPanel();
        setupStatistics(currentStatIndex);
    }
    
    @Override
    protected void setupPanel() {
        //making containers
        BorderPane root = new BorderPane();
        statistic1Container = new VBox();
        statistic2Container = new VBox();
        statistic2Container.setStyle("-fx-background-color: white;");
        statistic3Container = new VBox();
        
        setupNavigationButtons();
        
        //adding buttons to containers
        statistic1Container.getChildren().add(btnPrevStat);
        statistic3Container.getChildren().add(btnNextStat);
        
        //setting the components in the right place
        statistic1Container.setAlignment(Pos.CENTER);
        statistic2Container.setAlignment(Pos.CENTER);
        statistic3Container.setAlignment(Pos.CENTER);
        
        //add label for Statistics Panel at the top
        Label titleLabel = new Label("STATISTICS");
        titleLabel.setStyle("-fx-font-size: 30px;-fx-alignment : center;-fx-font-family: 'WingDings MS'"); // Set font size
        root.setAlignment(titleLabel, Pos.CENTER);
        root.setTop(titleLabel);
        
        //setting the different containers in different parts of the border pane
        root.setLeft(statistic1Container);
        root.setCenter(statistic2Container);
        root.setRight(statistic3Container);
        
        //setting it to fit the window
        root.setPrefSize(800,600);
        
        setupStatistics(currentStatIndex);
        
        // add the borderpane to the panel
        panel.getChildren().addAll(root);
    }
    
    // adds functionality to navigation buttons
    private void setupStatistics(int currentStatIndex) {
        Platform.runLater(() -> {
            // Clear existing content
            statistic2Container.getChildren().clear();
    
            // Create a label for the statistic
            Label statisticLabel = new Label();

            statisticLabel.setAlignment(Pos.CENTER); // Align the label to the center
            
            // Update statistics labels
            switch (currentStatIndex) {
                case 0:
                    // Statistic 1: Average transit GMR
                    statisticLabel.setText("Average Transit Google Mobility:\n" + calculateAverageTransitGMR());
                    break;
                case 1:
                    // Statistic 2: Average grocery/pharmacy GMR
                    statisticLabel.setText("Average Grocery Pharmacy \nGoogle Mobility:\n" + calculateAverageGroceryPharmacyGMR());
                    break;
                case 2:
                    // Statistic 3: Total deaths
                    statisticLabel.setText("Total Deaths:\n" + calculateTotalDeaths());
                    break;
                case 3:
                    // Statistic 4: 
                    statisticLabel.setText("Average Cases Per Borough:\n" + calculateAverageTotalCases());
                    break;
                default:
                    break;
            }
            
            // Add the statistic label to container 2 with specifications
            statisticLabel.setStyle("-fx-font-size: 30px; -fx-alignment : center;-fx-font-family: 'WingDings MS'; "); 
            statisticLabel.setPrefWidth(700);
            statisticLabel.setPrefHeight(300);
            statistic2Container.getChildren().add(statisticLabel);
        });
    }
    
    // STATISTICS FUNCTIONS USED
    private double calculateAverageTransitGMR() {
        double total = 0;
        for(CovidData data:filteredRecords){
            total += data.getTransitGMR();
        }
        return total/filteredRecords.size();
    }
    
    private double calculateAverageGroceryPharmacyGMR() {
        double total = 0;
        for(CovidData data:filteredRecords){
            total += data.getGroceryPharmacyGMR();
        }
        return total/filteredRecords.size();
    }
    
    private int calculateTotalDeaths() {
        int total = 0;
        for (CovidData data:filteredRecords) {
            total += data.getNewDeaths();
        }
        return total;
    }   
    
    private double calculateAverageTotalCases() {
        double total = 0;
        for (CovidData data:filteredRecords) {
            total += data.getNewCases();
        }
        return total/33; //Divided by 33 as that is the amount of boroughs, therefore giving average cases per borough
    }
    
    // setting up the next and previous buttons
    private void setupNavigationButtons() {
        btnNextStat = new Button("->");
        btnPrevStat = new Button("<-");
        
        btnNextStat.setPrefWidth(150); 
        btnNextStat.setPrefHeight(580); 
        btnPrevStat.setPrefWidth(150); 
        btnPrevStat.setPrefHeight(580); 
        
        btnNextStat.setStyle("-fx-font-size: 30px;"); 
        btnPrevStat.setStyle("-fx-font-size: 30px;"); 
    
        btnNextStat.setOnAction(e-> navigateToNextStatistic());
        btnPrevStat.setOnAction(e-> navigateToPreviousStatistic());
    }
    
    //functions used for the setupNavigationButtons() function
    private void navigateToNextStatistic() {
        currentStatIndex = (currentStatIndex + 1) % 4;
        setupStatistics(currentStatIndex);
    }
    
    private void navigateToPreviousStatistic() {
        currentStatIndex = (currentStatIndex - 1 + 4) % 4;
        setupStatistics(currentStatIndex);
    }
}
