import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;

import java.util.List;
import java.util.ArrayList;

/**
 * The Map Panel displays a map visualizing COVID-19 data.
 */
public class MapPanel extends Panel {
    
    private Stage lastOpenedWindow = null;
    
    public MapPanel() {
        super();
        this.panel = new Pane(); // Change the layout style for the panel here
        setupPanel();
    }
    
    @Override
    protected void setupPanel() {
        panel.getChildren().add(new Label("Map Panel")); // Test statement to show panel works
        // Implement the setup for the Map panel here
        
        // Test button to show burough details
        Button testButton = new Button("Harrow");
        testButton.setOnAction(e -> showBoroughData("Harrow"));
        panel.getChildren().add(testButton);
    }
    
    /**
     * Filters the records for a specific borough.
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
