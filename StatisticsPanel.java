import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import java.time.*;
import java.time.LocalDate;

import java.util.ArrayList;
import javafx.application.Platform;
import javafx.geometry.Pos;
import java.util.List;
import java.util.function.Function;

/**
 * The Statistics Panel designed to show various COVID-19 statistics.
 */
public class StatisticsPanel extends Panel {
    private VBox statistic1Container;
    private VBox statistic2Container;
    private VBox statistic3Container;
    private Button btnNextStat;
    private Button btnPrevStat;
    private int currentStatIndex = 0;
    private ArrayList<CovidData> covidDataList;
    
    public StatisticsPanel() {
        super(); 
        setupPanel();
        setupStatistics(currentStatIndex);
    }
    
    @Override
    protected void setupPanel() {
        BorderPane root = new BorderPane();
        
        statistic1Container = new VBox();
        statistic2Container = new VBox();
        statistic2Container.setStyle("-fx-background-color: white;");
        statistic3Container = new VBox();
        
        setupNavigationButtons();
        statistic1Container.getChildren().add(btnPrevStat);
        statistic3Container.getChildren().add(btnNextStat);
        
        root.setAlignment(statistic2Container, Pos.CENTER);
        root.setMargin(statistic2Container, new Insets(10));
        
        statistic1Container.setPadding(new Insets(10));
        statistic2Container.setPadding(new Insets(70)); // Adjust the inset value as needed
        
        statistic1Container.setAlignment(Pos.CENTER);
        statistic3Container.setAlignment(Pos.CENTER);


        // Add constraints to ensure statistic2Container fills the center
        root.setAlignment(statistic3Container, Pos.CENTER_RIGHT);
    
        // Add label for Statistics Panel at the top
        Label titleLabel = new Label("STATISTICS");
        titleLabel.setStyle("-fx-font-size: 50px;-fx-alignment : center;-fx-font-family: 'WingDings MS'"); // Set font size
        root.setTop(titleLabel);
        
        root.setLeft(statistic1Container);
        root.setCenter(statistic2Container);
        root.setRight(statistic3Container);
        
        setupStatistics(currentStatIndex);
        panel.getChildren().addAll(root); // Test statement to show panel works
    }
    
    private double calculateAverageTransitGMR() {
        double total = 0;
        for(CovidData data:filteredRecords){
            total += data.getTransitGMR();
        }
        return total/filteredRecords.size();
    }
    
    private double calculateAverageGroceryPharmacyGMR(){
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
    // adds functionality to navigation buttons
    private void setupStatistics(int currentStatIndex) {
        Platform.runLater(() -> {
            // Clear existing content
            statistic2Container.getChildren().clear();
    
            // Create a label for the statistic
            Label statisticLabel = new Label();
            statisticLabel.setStyle("-fx-font-size: 20px;"); // Set font size
            statisticLabel.setAlignment(Pos.CENTER); // Align the label to the center
            
            // Update statistics labels
            switch (currentStatIndex) {
                case 0:
                    // Statistic 1: Average transit GMR
                    statisticLabel.setText("Average Transit Google Mobility: \n" + calculateAverageTransitGMR());
                    break;
                case 1:
                    // Statistic 2: Average grocery/pharmacy GMR
                    statisticLabel.setText("Average Grocery/Pharmacy Google Mobility: \n" + calculateAverageGroceryPharmacyGMR());
                    break;
                case 2:
                    // Statistic 3: Total deaths
                    statisticLabel.setText("Total Deaths: \n" + calculateTotalDeaths());
                    break;
                case 3:
                    // Statistic 4: 
                    statisticLabel.setText("Average Cases Per Borough: \n" + calculateAverageTotalCases());
                    break;
                default:
                    break;
            }
            
            // Add the statistic label to container 2
            statisticLabel.setStyle("-fx-font-size: 60px; -fx-alignment : center;-fx-font-family: 'WingDings MS'; "); 
            statisticLabel.setPrefWidth(685);
            statisticLabel.setPrefHeight(700);
            statistic2Container.getChildren().add(statisticLabel);
        });
    }
    
    private void setupNavigationButtons(){
        btnNextStat = new Button("->");
        btnPrevStat = new Button("<-");
        
        btnNextStat.setPrefWidth(400); 
        btnNextStat.setPrefHeight(900); 
        btnPrevStat.setPrefWidth(400); 
        btnPrevStat.setPrefHeight(900); 
        
        btnNextStat.setStyle("-fx-font-size: 140px;"); 
        btnPrevStat.setStyle("-fx-font-size: 140px;"); 
    
        btnNextStat.setOnAction(e-> navigateToNextStatistic());
        btnPrevStat.setOnAction(e-> navigateToPreviousStatistic());
    }
    
    private void navigateToNextStatistic() {
        currentStatIndex = (currentStatIndex + 1) % 4;
        setupStatistics(currentStatIndex);
    }
    
    private void navigateToPreviousStatistic() {
        currentStatIndex = (currentStatIndex - 1 + 4) % 4;
        setupStatistics(currentStatIndex);
    }
    
    //override the set filtered records function so that it updates the statistics panel when the date pickers are interacted with
    @Override 
    protected void setFilteredRecords(List<CovidData> filteredRecords){
        super.setFilteredRecords(filteredRecords);
        setupStatistics(currentStatIndex);
    }
    
}
