package airport.model.parking;

import org.joda.money.Money;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the Money calculator method in the Long stay parking lot class
 * Seventeen tests methods for different time periods and charging of parking
 * @author Adam Ross
 */
public class LongStayParkingLotTest {

    private LocalDateTime arrival;
    private LocalDateTime departure;
    private ParkingLot longStay;
    private Money chargedValue;
    private Money predictedValue;
    private int year = 2015;

    /**
     * Sets up for the tests
     */
    @Before
    public void setUp() {
        final String ParkingLot = "Long Stay Parking Lot";
        final int CAP = 100; // Maximum capacity for the parking lot
        arrival = LocalDateTime.of(year, Month.JANUARY, 1, 0, 0, 0);
        longStay = new LongStayParkingLot(ParkingLot, CAP);
    }

    /**
     * Tests the price charged for zero minutes, zero hours, and zero days
     */
    @Test
    public void computeChargeForZeroTime() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 0, 0, 0);
        chargedValue = longStay.computeCharge(longStay.getDuration(arrival, departure));
        predictedValue = Money.parse(ParkingLotEnum.DEFAULT_CHARGE.getValue());
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minutes, 1 hour, and zero days
     */
    @Test
    public void computeChargeForOneHourOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 1, 1, 0);
        chargedValue = longStay.computeCharge(longStay.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 25.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, 2 hours, and zero days
     */
    @Test
    public void computeChargeForTwoHoursZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 2, 0, 0);
        chargedValue = longStay.computeCharge(longStay.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 25.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, 2 hours, and zero days
     */
    @Test
    public void computeChargeForTwoHoursOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 2, 1, 0);
        chargedValue = longStay.computeCharge(longStay.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 25.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, 3 hours, and zero days
     */
    @Test
    public void computeChargeForThreeHoursOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 3, 1, 0);
        chargedValue = longStay.computeCharge(longStay.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 25.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, 23 hours, and zero days
     */
    @Test
    public void computeChargeForTwentyThreeHoursOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 23, 1, 0);
        chargedValue = longStay.computeCharge(longStay.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 25.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, zero hours, and 1 day
     */
    @Test
    public void computeChargeForOneDayZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 2, 0, 0, 0);
        chargedValue = longStay.computeCharge(longStay.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 25.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, zero hours, and 1 day
     */
    @Test
    public void computeChargeForOneDayOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 2, 0, 1, 0);
        chargedValue = longStay.computeCharge(longStay.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 33.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, 1 hour, and 2 days
     */
    @Test
    public void computeChargeForOneDayOneHourZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 3, 1, 0, 0);
        chargedValue = longStay.computeCharge(longStay.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 58.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, 1 hour, and 3 days
     */
    @Test
    public void computeChargeForThreeDaysOneHourOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 4, 1, 1, 0);
        chargedValue = longStay.computeCharge(longStay.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 91.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, 2 hours, and 4 days
     */
    @Test
    public void computeChargeForFourDaysTwoHoursOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 5, 2, 1, 0);
        chargedValue = longStay.computeCharge(longStay.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 124.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, 3 hours, and 5 days
     */
    @Test
    public void computeChargeForFiveDaysThreeHoursOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 6, 3, 1, 0);
        chargedValue = longStay.computeCharge(longStay.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 125.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, 23 hours, and 6 days
     */
    @Test
    public void computeChargeForSixDaysTwentyThreeHoursOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 7, 23, 1, 0);
        chargedValue = longStay.computeCharge(longStay.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 125.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, zero hours, and 7 days
     */
    @Test
    public void computeChargeForSevenDaysZeroHoursZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 8, 0, 0, 0);
        chargedValue = longStay.computeCharge(longStay.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 125.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, zero hours, and 7 days
     */
    @Test
    public void computeChargeForSevenDaysZeroHoursOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 8, 0, 1, 0);
        chargedValue = longStay.computeCharge(longStay.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 133.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, 1 hour, and 7 days
     */
    @Test
    public void computeChargeForSevenDaysOneHourZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 8, 1, 0, 0);
        chargedValue = longStay.computeCharge(longStay.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 133.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, 3 hours, and 14 days
     */
    @Test
    public void computeChargeForFourteenDaysThreeHoursZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 15, 3, 0, 0);
        chargedValue = longStay.computeCharge(longStay.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 274.00");
        assertEquals(chargedValue, predictedValue);
    }
}