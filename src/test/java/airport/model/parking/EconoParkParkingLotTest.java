package airport.model.parking;

import org.joda.money.Money;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the Money calculator method in the Econo Park parking lot class
 * Eighteen tests methods for different time periods and charging of parking
 * @author Adam Ross
 */
public class EconoParkParkingLotTest {

    private LocalDateTime arrival;
    private LocalDateTime departure;
    private ParkingLot econoPark;
    private Money chargedValue;
    private Money predictedValue;
    private int year = 2015;

    /**
     * Sets up for the tests
     */
    @Before
    public void setUp() {
        final String ParkingLot = "Econo Park Parking Lot";
        final int CAP = 100; // Maximum capacity for the parking lot
        arrival = LocalDateTime.of(year, Month.JANUARY, 1, 0, 0, 0);
        econoPark = new EconoParkParkingLot(ParkingLot, CAP);
    }

    /**
     * Tests the price charged for zero minutes, zero hours, and zero days
     */
    @Test
    public void computeChargeForZeroTime() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 0, 0, 0);
        chargedValue = econoPark.computeCharge(econoPark.getDuration(arrival, departure));
        predictedValue = Money.parse(ParkingLotEnum.DEFAULT_CHARGE.getValue());
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, 1 hour, and zero days
     */
    @Test
    public void computeChargeForOneHourZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 1, 0, 0);
        chargedValue = econoPark.computeCharge(econoPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 15.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, 2 hours, and zero days
     */
    @Test
    public void computeChargeForTwoHoursOneMinute() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 2, 1, 0);
        chargedValue = econoPark.computeCharge(econoPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 15.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, 23 hours, and zero days
     */
    @Test
    public void computeChargeForTwentyThreeHoursOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 23, 1, 0);
        chargedValue = econoPark.computeCharge(econoPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 15.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, 23 hours, and zero days
     */
    @Test
    public void computeChargeForTwentyThreeHoursZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 23, 0, 0);
        chargedValue = econoPark.computeCharge(econoPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 15.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, zero hours, and 1 day
     */
    @Test
    public void computeChargeForOneDayZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 2, 0, 0, 0);
        chargedValue = econoPark.computeCharge(econoPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 15.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, zero hours, and 1 day
     */
    @Test
    public void computeChargeForOneDayOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 2, 0, 1, 0);
        chargedValue = econoPark.computeCharge(econoPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 20.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, 1 hour, and 1 day
     */
    @Test
    public void computeChargeForOneDayOneHourZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 2, 1, 0, 0);
        chargedValue = econoPark.computeCharge(econoPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 20.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, 1 hour, and 1 day
     */
    @Test
    public void computeChargeForOneDayOneHourOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 2, 1, 1, 0);
        chargedValue = econoPark.computeCharge(econoPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 25.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, 2 hours, and 1 day
     */
    @Test
    public void computeChargeForOneDayTwoHoursZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 2, 2, 0, 0);
        chargedValue = econoPark.computeCharge(econoPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 25.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, 2 hours, and 1 day
     */
    @Test
    public void computeChargeForOneDayTwoHoursOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 2, 2, 1, 0);
        chargedValue = econoPark.computeCharge(econoPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 30.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, 23 hours, and 1 day
     */
    @Test
    public void computeChargeForOneDayTwentyThreeHoursZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 2, 23, 0, 0);
        chargedValue = econoPark.computeCharge(econoPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 30.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, 23 hours, and 1 day
     */
    @Test
    public void computeChargeForOneDayTwentyThreeHoursOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 2, 23, 1, 0);
        chargedValue = econoPark.computeCharge(econoPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 30.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, zero hours, and 4 days
     */
    @Test
    public void computeChargeForFourDaysZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 5, 0, 0, 0);
        chargedValue = econoPark.computeCharge(econoPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 60.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, zero hours, and 9 days
     */
    @Test
    public void computeChargeForNineDaysZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 10, 0, 1, 0);
        chargedValue = econoPark.computeCharge(econoPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 140.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, 1 hour, and 13 days
     */
    @Test
    public void computeChargeForThirteenDaysOneHourZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 14, 1, 0, 0);
        chargedValue = econoPark.computeCharge(econoPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 200.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, 2 hours, and 21 days
     */
    @Test
    public void computeChargeForTwentyOneDaysTwoHoursOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 22, 2, 1, 0);
        chargedValue = econoPark.computeCharge(econoPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 330.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, 23 hours, and 30 days
     */
    @Test
    public void computeChargeForThirtyDaysTwentyThreeHoursOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 31, 23, 1, 0);
        chargedValue = econoPark.computeCharge(econoPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 465.00");
        assertEquals(chargedValue, predictedValue);
    }
}
