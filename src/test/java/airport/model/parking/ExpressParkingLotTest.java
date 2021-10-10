package airport.model.parking;

import org.joda.money.Money;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the Money calculator method in the Express Park parking lot class
 * Twenty-five tests methods for different time periods and charging of parking
 * @author Adam Ross
 */
public class ExpressParkingLotTest {

    private LocalDateTime arrival;
    private LocalDateTime departure;
    private ParkingLot expressPark;
    private Money chargedValue;
    private Money predictedValue;
    private int year = 2015;

    /**
     * Sets up for the tests
     */
    @Before
    public void setUp() {
        final String ParkingLot = "Express Parking Lot";
        final int CAP = 100; // Maximum capacity for the parking lot
        arrival = LocalDateTime.of(year, Month.JANUARY, 1, 0, 0, 0);
        expressPark = new ExpressParkingLot(ParkingLot, CAP);
    }

    /**
     * Tests the price charged for zero minutes, zero hours, and zero days
     */
    @Test
    public void computeChargeForZeroTime() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 0, 0, 0);
        chargedValue = expressPark.computeCharge(expressPark.getDuration(arrival, departure));
        predictedValue = Money.parse(ParkingLotEnum.DEFAULT_CHARGE.getValue());
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minutes, zero hours, and zero days
     */
    @Test
    public void computeChargeForOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 0, 1, 0);
        chargedValue = expressPark.computeCharge(expressPark.getDuration(arrival, departure));
        predictedValue = Money.parse(ParkingLotEnum.DEFAULT_CHARGE.getValue());
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 15 minutes, zero hours, and zero days
     */
    @Test
    public void computeChargeForFifteenMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 0, 15, 0);
        chargedValue = expressPark.computeCharge(expressPark.getDuration(arrival, departure));
        predictedValue = Money.parse(ParkingLotEnum.DEFAULT_CHARGE.getValue());
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 16 minutes, zero hours, and zero days
     */
    @Test
    public void computeChargeForSixteenMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 0, 16, 0);
        chargedValue = expressPark.computeCharge(expressPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 4.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 40 minutes, zero hours, and zero days
     */
    @Test
    public void computeChargeForFortyMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 0, 40, 0);
        chargedValue = expressPark.computeCharge(expressPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 4.00");
        assertEquals(chargedValue, predictedValue);
    }
    /**
     * Tests the price charged for 41 minutes, zero hours, and zero days
     */
    @Test
    public void computeChargeForFortyOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 0, 41, 0);
        chargedValue = expressPark.computeCharge(expressPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 8.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, 1 hour, and zero days
     */
    @Test
    public void computeChargeForOneHourZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 1, 0, 0);
        chargedValue = expressPark.computeCharge(expressPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 8.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, 1 hour, and zero days
     */
    @Test
    public void computeChargeForOneHourOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 1, 1, 0);
        chargedValue = expressPark.computeCharge(expressPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 16.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, 2 hours, and zero days
     */
    @Test
    public void computeChargeForTwoHoursZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 2, 0, 0);
        chargedValue = expressPark.computeCharge(expressPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 16.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, 2 hours, and zero days
     */
    @Test
    public void computeChargeForTwoHoursOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 2, 1, 0);
        chargedValue = expressPark.computeCharge(expressPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 24.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, 3 hours, and zero days
     */
    @Test
    public void computeChargeForThreeHoursZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 3, 0, 0);
        chargedValue = expressPark.computeCharge(expressPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 24.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, 3 hours, and zero days
     */
    @Test
    public void computeChargeForThreeHoursOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 3, 1, 0);
        chargedValue = expressPark.computeCharge(expressPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 32.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, 4 hours, and zero days
     */
    @Test
    public void computeChargeForFourHoursZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 4, 0, 0);
        chargedValue = expressPark.computeCharge(expressPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 32.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, 23 hours, and zero days
     */
    @Test
    public void computeChargeForTwentyThreeHoursOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 1, 23, 1, 0);
        chargedValue = expressPark.computeCharge(expressPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 32.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, zero hours, and 1 day
     */
    @Test
    public void computeChargeForOneDayZeroHoursZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 2, 0, 0, 0);
        chargedValue = expressPark.computeCharge(expressPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 32.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, zero hours, and 1 day
     */
    @Test
    public void computeChargeForOneDayZeroHoursOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 2, 0, 1, 0);
        chargedValue = expressPark.computeCharge(expressPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 40.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, 1 hour, and 2 days
     */
    @Test
    public void computeChargeForTwoDaysOneHourZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 3, 1, 0, 0);
        chargedValue = expressPark.computeCharge(expressPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 72.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, 1 hour, and 4 days
     */
    @Test
    public void computeChargeForFourDaysOneHourOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 5, 1, 1, 0);
        chargedValue = expressPark.computeCharge(expressPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 144.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, 2 hours, and 4 days
     */
    @Test
    public void computeChargeForFourDaysTwoHoursZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 5, 2, 0, 0);
        chargedValue = expressPark.computeCharge(expressPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 144.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, 2 hours, and 4 days
     */
    @Test
    public void computeChargeForFourDaysTwoHoursOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 5, 2, 1, 0);
        chargedValue = expressPark.computeCharge(expressPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 152.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, 3 hours, and 4 days
     */
    @Test
    public void computeChargeForFourDaysThreeHoursZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 5, 3, 0, 0);
        chargedValue = expressPark.computeCharge(expressPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 152.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, 3 hours, and 4 days
     */
    @Test
    public void computeChargeForFourDaysThreeHoursOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 5, 3, 1, 0);
        chargedValue = expressPark.computeCharge(expressPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 160.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for zero minutes, 23 hours, and 5 days
     */
    @Test
    public void computeChargeForFiveDaysTwentyThreeHoursZeroMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 6, 23, 0, 0);
        chargedValue = expressPark.computeCharge(expressPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 160.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, 23 hours, and 6 days
     */
    @Test
    public void computeChargeForSixDaysTwentyThreeHoursOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 7, 23, 1, 0);
        chargedValue = expressPark.computeCharge(expressPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 160.00");
        assertEquals(chargedValue, predictedValue);
    }

    /**
     * Tests the price charged for 1 minute, two hours, and 23 days
     */
    @Test
    public void computeChargeForTwentyThreeDaysTwoHoursOneMin() {
        departure = LocalDateTime.of(year, Month.JANUARY, 24, 2, 1, 0);
        chargedValue = expressPark.computeCharge(expressPark.getDuration(arrival, departure));
        predictedValue = Money.parse("NZD 568.00");
        assertEquals(chargedValue, predictedValue);
    }
}