import javafx.scene.chart.*;
import java.util.Map;
import java.util.HashMap;

/**
 * Challenge Task
 * The Graph Panel displays a bar chart for new covid cases per burough for the selected date range.
 * 
 * @author Ahmet Taramis
 */
public class GraphPanel extends Panel {
    
    private BarChart<String, Number> casesBarChart;

    public GraphPanel() {
        super();
        setupPanel();
    }
    
    @Override
    protected void setupPanel() {
        // Creating the axes
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        // Creating the bar chart with the specified axes
        casesBarChart = new BarChart<>(xAxis, yAxis);
        casesBarChart.setTitle("New COVID Cases per Borough");

        // Bind the chart's size to the parent pane's size
        casesBarChart.prefWidthProperty().bind(panel.widthProperty());
        casesBarChart.prefHeightProperty().bind(panel.heightProperty());

        // Clear and add the chart to the panel
        panel.getChildren().clear();
        panel.getChildren().add(casesBarChart);
        
        updateCasesBarChart();
    }

    /**
     * Method to update the bar chart with new COVID cases data
     */
    protected void updateCasesBarChart() {
        // Clear existing data
        casesBarChart.getData().clear();

        // Automatically adjust axis range
        casesBarChart.getXAxis().setAutoRanging(true);
        casesBarChart.getYAxis().setAutoRanging(true);

        // Create a series for new COVID cases
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("New COVID Cases");

        // Temporary storage for borough-wise total new cases
        Map<String, Integer> totalCasesPerBorough = new HashMap<>();

        // Add new cases per borough to hash map
        for (CovidData data : filteredRecords) {
            int currentTotal = totalCasesPerBorough.getOrDefault(data.getBorough(), 0);
            totalCasesPerBorough.put(data.getBorough(), currentTotal + data.getNewCases());
        }

        // Add data from hash map to the series
        for (Map.Entry<String, Integer> entry : totalCasesPerBorough.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        // Add series to the bar chart
        casesBarChart.getData().add(series);
    }
}
