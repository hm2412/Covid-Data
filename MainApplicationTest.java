import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.*;

/**
 * Unit testing for the Main Application class
 *
 * @author Haleema Mohammed
 */
public class MainApplicationTest {
    
    MainApplication main;
    
    /**
     * Default constructor for test class MainApplicationTest
     */
    public MainApplicationTest() {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp() {
        main = new MainApplication();
    }
    
    @Test
    public void testValidFilteredRecord() {
        LocalDate startDate = LocalDate.parse("2021-01-01");
        LocalDate endDate = LocalDate.parse("2022-01-01");
        assertNotNull(main.getFilteredRecords(startDate, endDate));
    }
    
    @Test
    public void testInvalidFilteredRecord() {
        LocalDate startDate = LocalDate.parse("2022-01-01");
        LocalDate endDate = LocalDate.parse("2021-01-01");
        assertTrue(main.getFilteredRecords(startDate, endDate).isEmpty());
    }
    
    @Test
    public void testFilteredRecordOutOfBounds() {
        LocalDate startDate = LocalDate.parse("2024-01-01");
        LocalDate endDate = LocalDate.parse("2024-03-31");
        assertTrue(main.getFilteredRecords(startDate, endDate).isEmpty());
    }
    
    @Test
    public void testSingleDayFilteredRecord() {
        LocalDate startDate = LocalDate.parse("2021-12-30");
        LocalDate endDate = LocalDate.parse("2021-12-30");
        assertNotNull(main.getFilteredRecords(startDate, endDate));
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown() {
    }
}
