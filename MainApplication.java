import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** 
 * The layout currently is a borderPane(root) with the centre being a stackPane(Panel Container) to display the panels,
 * but the panels themselves can be any layout style (e.g. borderPane)
 * 
 * I have also made the template for the report which we can fill out towards the end.
 */

/**
 * This class extends the Application class from JavaFX and sets up the primary stage (window) for the application.
 * 
 * It includes a date range selection feature at the top and naigation buttons at the bottom.
 * Users can navigate once a valid date range has been selected.
 * The navigation allows user to change between different panels:
 * - A Welcome Panel, which provides introductory information to the user.
 * - A Map Panel, intended to display a visual representation of COVID-19 data across London's boroughs for given dates.
 * - A Statistics Panel, designed to show various statistics based on the selected date range.
 */
public class MainApplication extends Application {

    private StackPane panelContainer; // To hold the panels
    private Button btnNext; // Next button
    private Button btnPrevious; // Previous button
    private DatePicker startDatePicker;
    private DatePicker endDatePicker;
    private int currentPanelIndex = 0; // To track the current panel
    private List<Panel> panels; // List to hold the panels

    /**
     * The main entry point for the application. Sets up the main stage and initializes the UI components.
     * @param mainStage The main stage for this application, onto which the application scene can be set.
     */
    @Override
    public void start(Stage mainStage) {
        BorderPane root = new BorderPane();

        // Initialize panel list
        panels = new ArrayList<>();
        panels.add(new WelcomePanel());
        panels.add(new MapPanel());
        panels.add(new StatisticsPanel());

        // Panel container
        panelContainer = new StackPane();
        panelContainer.getChildren().add(panels.get(currentPanelIndex).getPanel());

        root.setCenter(panelContainer);
        
        setupNavigationButtons();
        // Navigation Buttons Container
        HBox bottomContainer = new HBox();
        bottomContainer.getChildren().addAll(btnPrevious, btnNext);
        bottomContainer.setSpacing(10); // Set spacing between buttons
        root.setBottom(bottomContainer); // Set the HBox at the bottom of the BorderPane
        
        setupDatePickers();
        // Date Picker Container
        HBox topContainer = new HBox();
        topContainer.getChildren().addAll(startDatePicker, endDatePicker);
        topContainer.setSpacing(10); // Set spacing between buttons
        root.setTop(topContainer); // Set the HBox at the bottom of the BorderPane

        Scene scene = new Scene(root, 800, 600);
        mainStage.setTitle("London COVID-19 Statistics");
        mainStage.setScene(scene);
        mainStage.show();
    }

    /**
     * Initializes and configures the navigation buttons and their actions.
     */
    private void setupNavigationButtons() {
        btnNext = new Button("Next");
        btnPrevious = new Button("Previous");

        // Initially disable navigation buttons
        btnNext.setDisable(true);
        btnPrevious.setDisable(true);

        // Button actions
        btnNext.setOnAction(e -> navigateToNextPanel());
        btnPrevious.setOnAction(e -> navigateToPreviousPanel());
    }
    
    /**
     * Initializes and configures the date pickers for selecting the start and end dates.
     */
    private void setupDatePickers() {
        startDatePicker = new DatePicker();
        endDatePicker = new DatePicker();
        
        startDatePicker.setPromptText("Start Date");
        endDatePicker.setPromptText("End Date");
    
        // Handling the start date selection
        startDatePicker.setOnAction(event -> {
            LocalDate startDate = startDatePicker.getValue();
            LocalDate endDate = endDatePicker.getValue();
            if (endDate != null && startDate != null && startDate.isAfter(endDate)) {
                showAlert("Invalid Date Range", "Start date must be before end date.");
                startDatePicker.setValue(null); // Reset the start date picker
                btnNext.setDisable(true);
                btnPrevious.setDisable(true);
                currentPanelIndex = 0; // Reset panel back to Welcome Panel
                updatePanelInView();
            } else if (startDate != null && endDate != null) {
                btnNext.setDisable(false);
                btnPrevious.setDisable(false);
            }
        });
    
        // Handling the end date selection
        endDatePicker.setOnAction(event -> {
            LocalDate startDate = startDatePicker.getValue();
            LocalDate endDate = endDatePicker.getValue();
            if (startDate != null && endDate != null && endDate.isBefore(startDate)) {
                showAlert("Invalid Date Range", "End date must be after start date.");
                endDatePicker.setValue(null); // Reset the end date picker
                btnNext.setDisable(true);
                btnPrevious.setDisable(true);
                currentPanelIndex = 0; // Reset panel back to Welcome Panel
                updatePanelInView();
            } else if (endDate != null && startDate != null) {
                btnNext.setDisable(false);
                btnPrevious.setDisable(false);
            }
        });
    }

    /**
     * Changes to the next panel in the list of panels, wrapping around to the first panel if it reaches the end.
     */
    private void navigateToNextPanel() {
        currentPanelIndex = (currentPanelIndex + 1) % panels.size();
        updatePanelInView();
    }

    /**
     * Changes to the previous panel in the list of panels, wrapping around to the last panel if it reaches the begining.
     */
    private void navigateToPreviousPanel() {
        currentPanelIndex = (currentPanelIndex - 1 + panels.size()) % panels.size();
        updatePanelInView();
    }

    /**
     * Updates the panel container to display the current panel based on 'currentPanelIndex'.
     */
    private void updatePanelInView() {
        panelContainer.getChildren().setAll(panels.get(currentPanelIndex).getPanel());
    }
    
    /**
     * Displays an alert dialog with a specified title and content.
     * @param title The title of the alert dialog.
     * @param content The content message of the alert dialog.
     */
    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * The main method that launches the JavaFX application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
