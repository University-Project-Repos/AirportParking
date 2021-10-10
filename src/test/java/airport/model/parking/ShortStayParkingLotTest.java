package airport.model.parking;

import org.joda.money.Money;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the Money calculator method in the Short stay parking lot class
 * Sixteen tests methods for different time periods and charging of parking
 * @author Adam Ross
 */
public class ShortStayParkingLotTest {

    private LocalDateTime arrival;
    private LocalDateTime departure;
    private ParkingLot shortStay;
    private Money chargedValue;
    private Money predictedValue;
    private int year = 2015;

    /**
     * Sets up for the tests
     */
    @Before
    public void setUp() {
        final String ParkingLot = "Short Stay Parking Lot";
        final int CAP = 100; // Maximum capacity for the parking lot
        arrival = LocalDateTime.of(year, Month.JANUARY, 1, 0, 0, 0);
        shortStay = new ShortStayParkingLot(ParkingLot, CAP);
    }

    /**
     * Tests the price charged for zero minutes, zero hours, and zero days
     */
    @Test
    public void computeChargeForZeroTime() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 0, 0, 0);
        chargedValue = shortStay.computeCharge(shortStay.getDuration(arrival, departure));
        predictedValue = Money.parse(ParkingLotEnum.DEFAULT_CHARGE.getValue());
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minutes, 1 hour, and zero days
     */
    @Test
    public void computeChargeForOneHourOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 1, 1, 0);
        chargedValue = shortStay.computeCharge(shortStay.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 12.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, 2 hours, and zero days
     */
    @Test
    public void computeChargeForTwoHoursZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 2, 0, 0);
        chargedValue = shortStay.computeCharge(shortStay.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 12.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, 2 hours, and zero days
     */
    @Test
    public void computeChargeForTwoHoursOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 2, 1, 0);
        chargedValue = shortStay.computeCharge(shortStay.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 20.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, 3 hours, and zero days
     */
    @Test
    public void computeChargeForThreeHoursOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 3, 1, 0);
        chargedValue = shortStay.computeCharge(shortStay.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 25.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, 23 hours, and zero days
     */
    @Test
    public void computeChargeForTwentyThreeHoursOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 23, 1, 0);
        chargedValue = shortStay.computeCharge(shortStay.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 25.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, zero hours, and 1 day
     */
    @Test
    public void computeChargeForOneDayZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 2, 0, 0, 0);
        chargedValue = shortStay.computeCharge(shortStay.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 25.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, zero hours, and 1 day
     */
    @Test
    public void computeChargeForOneDayOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 2, 0, 1, 0);
        chargedValue = shortStay.computeCharge(shortStay.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 33.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, 1 hour, and 1 day
     */
    @Test
    public void computeChargeForOneDayOneHourZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 2, 1, 0, 0);
        chargedValue = shortStay.computeCharge(shortStay.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 33.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, 1 hour, and 3 days
     */
    @Test
    public void computeChargeForThreeDaysOneHourOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 4, 1, 1, 0);
        chargedValue = shortStay.computeCharge(shortStay.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 91.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, 2 hours, and 11 days
     */
    @Test
    public void computeChargeForElevenDaysTwoHoursZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 12, 2, 0, 0);
        chargedValue = shortStay.computeCharge(shortStay.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 291.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, 2 hours, and 20 days
     */
    @Test
    public void computeChargeForTwentyDaysTwoHoursOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 21, 2, 1, 0);
        chargedValue = shortStay.computeCharge(shortStay.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 524.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, 3 hours, and 21 days
     */
    @Test
    public void computeChargeForTwentyOneDaysThreeHoursZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 22, 3, 0, 0);
        chargedValue = shortStay.computeCharge(shortStay.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 549.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minutes, 3 hours, and 24 days
     */
    @Test
    public void computeChargeForTwentyFourDaysThreeHoursOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 25, 3, 1, 0);
        chargedValue = shortStay.computeCharge(shortStay.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 625.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, 23 hours, and 29 days
     */
    @Test
    public void computeChargeForTwentyNineDaysTwentyThreeHoursZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 30, 23, 0, 0);
        chargedValue = shortStay.computeCharge(shortStay.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 750.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, 23 hour, and 29 days
     */
    @Test
    public void computeChargeForTwentyNineDaysTwentyThreeHoursOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 31, 23, 1, 0);
        chargedValue = shortStay.computeCharge(shortStay.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 750.00");
        assertEquals(chargedValue, predictedValue);
    }
}