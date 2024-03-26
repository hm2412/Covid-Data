import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.application.*;
import javafx.geometry.*;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;
import java.util.ArrayList;

/**
 * The Map Panel displays a map visualizing COVID-19 databetween the specified date range. 
 * This includes colour-coding on each borough to indicate its death rate, as well as
 * windows that open upon click which display detailed borough data.
 * 
 * @author Haleema Mohammed, Ahmet Taramis
 */
public class MapPanel extends Panel {
    
    private Stage lastOpenedWindow = null;
    
    private Button hillingdon, ealing, kensington, westminster, towerHamlets, newham, barking;
    private Button harrow, brent, camden, islington, hackney, redbridge, havering;
    private Button hounslow, hammersmith, wandsworth, city, greenwich, bexley;
    private Button barnet, haringey, walthamForest;
    private Button richmond, merton, lambeth, southwark, lewisham;
    private Button enfield;
    private Button kingston, sutton, croydon, bromley;
    private HBox row1, row2, row3, row4, row5, row6, row7; // Stores each row of boroughs
    
    public MapPanel() {
        super();
        panel.getStylesheets().add("map.css"); // Sets button styles
        setupPanel();
    }
    
    @Override
    protected void setupPanel() {
        setUpRow1();
        setUpRow2();
        setUpRow3();
        setUpRow4();
        setUpRow5();
        setUpRow6();
        setUpRow7();
        
        panel.getChildren().addAll(row1, row2, row3, row4, row5, row6, row7);
        
        positionRows(); 
    }

    /**
     * Positions the rows to arrange the buttons in a honeycomb-style map.
     * Each row is ultimately set in comparison to row1's position.
     */
    private void positionRows() {
        double centerX = 400; // Center of Scene
        double centerY = 300; // Center of Scene
        
        row1.setLayoutX(centerX);
        row1.setLayoutY(centerY);

        //Position row2 above row1
        row2.setLayoutX(row1.getLayoutX()+ 40);
        row2.setLayoutY(row1.getLayoutY()- 63);
        
        //Position row3 below row1
        row3.setLayoutX(row1.getLayoutX()+ 40);
        row3.setLayoutY(row1.getLayoutY()+ 63);
        
        //Position row4 above row2
        row4.setLayoutX(row2.getLayoutX()+ 120);
        row4.setLayoutY(row2.getLayoutY()- 63);
        
        //Position row5 below row3
        row5.setLayoutX(row3.getLayoutX()+40);
        row5.setLayoutY(row3.getLayoutY()+63);
        
        //Position row6 above row4
        row6.setLayoutX(row4.getLayoutX()+120);
        row6.setLayoutY(row4.getLayoutY()-63);
        
        //Position row7 below row5
        row7.setLayoutX(row5.getLayoutX()+40);
        row7.setLayoutY(row5.getLayoutY()+63);
    }
    
    /**
     * Sets up Hillingdon, Ealing, Kensington And Chelsea, Westminster, Tower Hamlets, Newham and Barking And Dagenham.
     */
    private void setUpRow1() {
        row1 = new HBox();
        
        hillingdon = new Button("HILL");
        setVisuals(hillingdon, "Hillingdon"); 
        
        ealing = new Button("EALI");
        setVisuals(ealing, "Ealing");
        
        kensington = new Button("KENS");
        setVisuals(kensington, "Kensington And Chelsea");
        
        westminster = new Button("WSTM");
        setVisuals(westminster, "Westminster");
        
        towerHamlets = new Button("TOWH");
        setVisuals(towerHamlets, "Tower Hamlets");
        
        newham = new Button("NEWH");
        setVisuals(newham, "Newham");
        
        barking = new Button("BARK");
        setVisuals(barking, "Barking And Dagenham");
        
        row1.getChildren().addAll(hillingdon, ealing, kensington, westminster, towerHamlets, newham, barking);
        row1.setAlignment(Pos.CENTER);
    }
    
    /**
     * Sets up Harrow, Brent, Camden, Islington, Hackney, Redbridge and Havering.
     */
    private void setUpRow2() {
        row2 = new HBox();
        
        harrow = new Button("HRRW");
        setVisuals(harrow, "Harrow");
        
        brent = new Button("BREN");
        setVisuals(brent, "Brent");
        
        camden = new Button("CAMD");
        setVisuals(camden, "Camden");
        
        islington = new Button("ISLI");
        setVisuals(islington, "Islington");
        
        hackney = new Button("HACK");
        setVisuals(hackney, "Hackney");
        
        redbridge = new Button("REDB");
        setVisuals(redbridge, "Redbridge");
        
        havering = new Button("HAVE");
        setVisuals(havering, "Havering");
        
        row2.getChildren().addAll(harrow, brent, camden, islington, hackney, redbridge, havering);
    }
    
    /**
     * Sets up Hounslow, Hammersmith And Fulham, Wandsworth, City Of London, Greenwich and Bexley.
     */
    private void setUpRow3() {
        row3 = new HBox();
        
        hounslow = new Button("HOUN");
        setVisuals(hounslow, "Hounslow");
        
        hammersmith = new Button("HAMM");
        setVisuals(hammersmith, "Hammersmith And Fulham");
        
        wandsworth = new Button("WAND");
        setVisuals(wandsworth, "Wandsworth");
        
        city = new Button("CITY");
        setVisuals(city, "City Of London");
        
        greenwich = new Button("GWCH");
        setVisuals(greenwich, "Greenwich");
        
        bexley = new Button("BEXL");
        setVisuals(bexley, "Bexley");
        
        row3.getChildren().addAll(hounslow, hammersmith, wandsworth, city, greenwich, bexley);
    }
    
    /**
     * Sets up Barnet, Haringey and Waltham Forest.
     */
    private void setUpRow4() {
        row4 = new HBox();
        
        barnet = new Button("BARN");
        setVisuals(barnet, "Barnet");
        
        haringey = new Button("HRGY");
        setVisuals(haringey, "Haringey");
        
        walthamForest = new Button("WALT");
        setVisuals(walthamForest, "Waltham Forest");
        
        row4.getChildren().addAll(barnet, haringey, walthamForest);
    }
    
    /**
     * Sets up Richmond Upon Thames, Merton, Lambeth, Southwark and Lewisham.
     */
    private void setUpRow5() {
        row5 = new HBox();
        
        richmond = new Button("RICH");
        setVisuals(richmond, "Richmond Upon Thames");
        
        merton = new Button("MERT");
        setVisuals(merton, "Merton");
        
        lambeth = new Button("LAMB");
        setVisuals(lambeth, "Lambeth");
        
        southwark = new Button("STHW");
        setVisuals(southwark, "Southwark");
        
        lewisham = new Button("LEWS");
        setVisuals(lewisham,"Lewisham");
        
        row5.getChildren().addAll(richmond, merton, lambeth, southwark, lewisham);
    }
    
    /**
     * Sets up Enfield.
     */
    private void setUpRow6() {
        row6 = new HBox();
        
        enfield = new Button("ENFI");
        setVisuals(enfield, "Enfield");
        
        row6.getChildren().add(enfield);
    }
    
    /**
     * Sets up Kingston Upon Thames, Sutton, Croydon and Bromley.
     */
    private void setUpRow7() {
        row7 = new HBox();
        
        kingston = new Button("KING");
        setVisuals(kingston, "Kingston Upon Thames");
        
        sutton = new Button("SUTT");
        setVisuals(sutton, "Sutton");
        
        croydon = new Button("CROY");
        setVisuals(croydon, "Croydon");
        
        bromley = new Button("BROM");
        setVisuals(bromley, "Bromley");
        
        row7.getChildren().addAll(kingston, sutton, croydon, bromley);
    }
    
    /**
     * Sets up the button functionality and colour.
     * Each borough is coloured depending on the total deaths within the selected date range, in comparison
     * to the average death rate over all boroughs. Colours are delineated in the map.css file.
     * NOTE: Set the date range to a smaller one to see all colours
     * 
     * @param button The button to be altered.
     * @param boroughName The name of the borough to filter records.
     */
    private void setVisuals(Button button, String boroughName) {
        double totalDeaths = 0;
        button.setOnAction(e -> showBoroughData(boroughName));
        List<CovidData> boroughRecords = new ArrayList<>(filterBoroughData(boroughName));
        
        for (CovidData row : boroughRecords) {
            if (row.getNewDeaths() >= 0) {
                totalDeaths += row.getNewDeaths();
            }
        }
        double averageDeaths = calculateAverageDeaths();
        
        if (totalDeaths <= (averageDeaths*0.33)) {
            button.getStyleClass().add("green");
        } else if (totalDeaths <= (averageDeaths*0.66)) {
            button.getStyleClass().add("greenyellow");
        } else if (totalDeaths<= averageDeaths) {
            button.getStyleClass().add("yellow");
        } else if (totalDeaths <= (averageDeaths*1.33)) {
            button.getStyleClass().add("orange");
        } else if (totalDeaths<= (averageDeaths*1.66)) {
            button.getStyleClass().add("orangered");
        } else {
            button.getStyleClass().add("red");
        }
    }
    
    /**
     * Calculates the average death over all boroughs, by adding up the total deaths and dividing
     * it by 33 (the total number of boroughs).
     * 
     * @return The average death rate over all boroughs.
     */
    private double calculateAverageDeaths() {
        double totalDeaths = 0;
        for (CovidData data:this.filteredRecords) {
            totalDeaths += data.getNewDeaths();
        }
        return totalDeaths/33;
    }
    
    /**
     * Filters the records for a specific borough.
     * 
     * @param boroughName The name of the borough for which to filter the records.
     * @return A list of CovidData records for the specified borough.
     */
    private List<CovidData> filterBoroughData(String boroughName) {
        List<CovidData> boroughRecords = new ArrayList<>();
        for (CovidData row : this.filteredRecords) {
            if (row.getBorough().equals(boroughName)) {
                boroughRecords.add(row);
            }
        }
        return boroughRecords;
    }
    
    /**
     * Opens a new window displaying data for the specified borough.
     * If a window is already open, it closes before the new one opens.
     * 
     * @param boroughName The name of the borough for which to display data.
     */
    private void showBoroughData(String boroughName) {
        // Close the last opened window if it exists and is showing
        if (lastOpenedWindow != null && lastOpenedWindow.isShowing()) {
            lastOpenedWindow.close();
        }
        
        // Gets filtered data for the borough
        List<CovidData> boroughRecords = new ArrayList<>(filterBoroughData(boroughName));
        
        // New window to show borough details
        Stage detailsStage = new Stage();
        detailsStage.setTitle(boroughName);
        
        // Setting up the TableView and TableColumn
        TableView<CovidData> table = new TableView<>();
        TableColumn<CovidData, String> dateColumn = new TableColumn<>("Date");
        TableColumn<CovidData, Integer> retailRecreationGMRColumn = new TableColumn<>("Retail Recreation GMR");
        TableColumn<CovidData, Integer> groceryPharmacyGMRColumn = new TableColumn<>("Grocery Pharmacy GMR");
        TableColumn<CovidData, Integer> parksGMRColumn = new TableColumn<>("Parks GMR");
        TableColumn<CovidData, Integer> transitGMRColumn = new TableColumn<>("Transit GMR");
        TableColumn<CovidData, Integer> workplacesGMRColumn = new TableColumn<>("Workplaces GMR");
        TableColumn<CovidData, Integer> residentialGMRColumn = new TableColumn<>("Residential GMR");
        TableColumn<CovidData, Integer> newCasesColumn = new TableColumn<>("New COVID Cases");
        TableColumn<CovidData, Integer> totalCasesColumn = new TableColumn<>("Total COVID Cases");
        TableColumn<CovidData, Integer> newDeathsColumn = new TableColumn<>("New COVID Deaths");
        
        // Setting up Cell Value Factories
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        retailRecreationGMRColumn.setCellValueFactory(new PropertyValueFactory<>("retailRecreationGMR"));
        groceryPharmacyGMRColumn.setCellValueFactory(new PropertyValueFactory<>("groceryPharmacyGMR"));
        parksGMRColumn.setCellValueFactory(new PropertyValueFactory<>("parksGMR"));
        transitGMRColumn.setCellValueFactory(new PropertyValueFactory<>("transitGMR"));
        workplacesGMRColumn.setCellValueFactory(new PropertyValueFactory<>("workplacesGMR"));
        residentialGMRColumn.setCellValueFactory(new PropertyValueFactory<>("residentialGMR"));
        newCasesColumn.setCellValueFactory(new PropertyValueFactory<>("newCases"));
        totalCasesColumn.setCellValueFactory(new PropertyValueFactory<>("totalCases"));
        newDeathsColumn.setCellValueFactory(new PropertyValueFactory<>("newDeaths"));
        
        // Add columns to table
        table.getColumns().addAll(dateColumn, retailRecreationGMRColumn, groceryPharmacyGMRColumn, parksGMRColumn,
                                    transitGMRColumn, workplacesGMRColumn, residentialGMRColumn, newCasesColumn,
                                    totalCasesColumn, newDeathsColumn);
        
        // Populate the table
        table.setItems(FXCollections.observableArrayList(boroughRecords));
        
        // Sorting feature
        ComboBox<String> sortComboBox = new ComboBox<>();
        sortComboBox.setItems(FXCollections.observableArrayList("Date", "Retail Recreation GMR", "Grocery Pharmacy GMR",
                                                        "Parks GMR", "Transit GMR", "Workplaces GMR", "Residential GMR",
                                                        "New COVID Cases", "Total COVID Cases", "New COVID Deaths"));
        sortComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            table.getSortOrder().clear();
            switch (newValue) {
                case "Date":
                    table.getSortOrder().add(dateColumn);
                    break;
                case "Retail Recreation GMR":
                    table.getSortOrder().add(retailRecreationGMRColumn);
                    break;
                case "Grocery Pharmacy GMR":
                    table.getSortOrder().add(groceryPharmacyGMRColumn);
                    break;
                case "Parks GMR":
                    table.getSortOrder().add(parksGMRColumn);
                    break;
                case "Transit GMR":
                    table.getSortOrder().add(transitGMRColumn);
                    break;
                case "Workplaces GMR":
                    table.getSortOrder().add(workplacesGMRColumn);
                    break;
                case "Residential GMR":
                    table.getSortOrder().add(residentialGMRColumn);
                    break;
                case "New COVID Cases":
                    table.getSortOrder().add(newCasesColumn);
                    break;
                case "Total COVID Cases":
                    table.getSortOrder().add(totalCasesColumn);
                    break;
                case "New COVID Deaths":
                    table.getSortOrder().add(newDeathsColumn);
                    break;
            }
        });
        
        // Displaying table
        Pane layout = new VBox(10);
        layout.getChildren().addAll(sortComboBox, table);
        Scene scene = new Scene(layout);
        detailsStage.setScene(scene);
        lastOpenedWindow = detailsStage;
        detailsStage.show();
    }
}
