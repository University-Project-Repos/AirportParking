package airport.model.parking;

import org.joda.money.Money;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the Money calculator method in the Craddocks Indoor Park parking lot class
 * Fourteen tests methods for different time periods and charging of parking
 * @author Adam Ross
 */
public class CraddocksIndoorParkingLotTest {

    private LocalDateTime arrival;
    private LocalDateTime departure;
    private ParkingLot craddocksIndoor;
    private Money chargedValue;
    private Money predictedValue;
    private int year = 2015;

    /**
     * Sets up for the tests
     */
    @Before
    public void setUp() {
        final String ParkingLot = "Craddocks Indoor Parking Lot";
        final int CAP = 100; // Maximum capacity for the parking lot
        arrival = LocalDateTime.of(year, Month.JANUARY, 1, 0, 0, 0);
        craddocksIndoor = new CraddocksIndoorParkingLot(ParkingLot, CAP);
    }

    /**
     * Tests the price charged for zero minutes, zero hours, and zero days
     */
    @Test
    public void computeChargeForZeroTime() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 0, 0, 0);
        chargedValue = craddocksIndoor.computeCharge(craddocksIndoor.getDuration(arrival, departure));
        predictedValue = Money.parse(ParkingLotEnum.DEFAULT_CHARGE.getValue());
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, zero hours, and zero days
     */
    @Test
    public void computeChargeForOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 0, 1, 0);
        chargedValue = craddocksIndoor.computeCharge(craddocksIndoor.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 30.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, 23 hours, and zero days
     */
    @Test
    public void computeChargeForTwentyThreeHoursOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 23, 1, 0);
        chargedValue = craddocksIndoor.computeCharge(craddocksIndoor.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 30.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, zero hours, and 1 day
     */
    @Test
    public void computeChargeForOneDayZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 2, 0, 0, 0);
        chargedValue = craddocksIndoor.computeCharge(craddocksIndoor.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 30.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, zero hours, and 1 day
     */
    @Test
    public void computeChargeForOneDayOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 2, 0, 1, 0);
        chargedValue = craddocksIndoor.computeCharge(craddocksIndoor.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 60.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, 7 hours, and 1 day
     */
    @Test
    public void computeChargeForOneDaySevenHoursZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 2, 7, 0, 0);
        chargedValue = craddocksIndoor.computeCharge(craddocksIndoor.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 60.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, zero hours, and 2 days
     */
    @Test
    public void computeChargeForTwoDaysZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 3, 0, 0, 0);
        chargedValue = craddocksIndoor.computeCharge(craddocksIndoor.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 60.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, zero hours, and 2 days
     */
    @Test
    public void computeChargeForTwoDaysOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 3, 0, 1, 0);
        chargedValue = craddocksIndoor.computeCharge(craddocksIndoor.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 90.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, 16 hours, and 2 days
     */
    @Test
    public void computeChargeForTwoDaysSixteenHoursOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 3, 16, 1, 0);
        chargedValue = craddocksIndoor.computeCharge(craddocksIndoor.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 90.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, zero hours, and 3 days
     */
    @Test
    public void computeChargeForThreeDaysZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 4, 0, 0, 0);
        chargedValue = craddocksIndoor.computeCharge(craddocksIndoor.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 90.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, zero hours, and 3 days
     */
    @Test
    public void computeChargeForThreeDaysOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 4, 0, 1, 0);
        chargedValue = craddocksIndoor.computeCharge(craddocksIndoor.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 100.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, 23 hours, and 5 days
     */
    @Test
    public void computeChargeForFiveDaysTwentyThreeHoursOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 6, 23, 1, 0);
        chargedValue = craddocksIndoor.computeCharge(craddocksIndoor.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 120.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minute, zero hours, and 8 days
     */
    @Test
    public void computeChargeForEightDaysZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 9, 0, 0, 0);
        chargedValue = craddocksIndoor.computeCharge(craddocksIndoor.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 140.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, 13 hours, and 25 days
     */
    @Test
    public void computeChargeForTwentyFiveDaysThirteenHoursOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 26, 13, 1, 0);
        chargedValue = craddocksIndoor.computeCharge(craddocksIndoor.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 320.00");
        assertEquals(chargedValue, predictedValue);
    }
}