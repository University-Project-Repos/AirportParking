package airport.model.parking;

import airport.model.Vehicle;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Test for Parking lot abstract class
 * @author Adam Ross
 */
public class ParkingLotTest {

    private final int CAP = 100;
    private LocalDateTime arrival;
    private LocalDateTime departure;
    private ParkingLot carPark;
    private Vehicle vehicle;

    /**
     * Sets up the test
     */
    @Before
    public void setUp() {
        final String ParkingLot = "Express Parking Lot";
        vehicle = new Vehicle("ABC123");
        carPark = new ExpressParkingLot(ParkingLot, CAP);
        arrival = LocalDateTime.of(2014, Month.JANUARY, 3, 7, 0, 0);
        departure = LocalDateTime.of(2015, Month.JANUARY, 1, 0, 0, 0);
    }

    /**
     * Tests the getDuration() method
     */
    @Test
    public void getDuration() {
        HashMap<String, Long> time = carPark.getDuration(arrival, departure);
        assertEquals(362, time.get("days").longValue());
        assertEquals(8705, time.get("hours").longValue());
        assertEquals(522300, time.get("minutes").longValue());
        assertEquals(31338000, time.get("seconds").longValue());
    }

    /**
     * Tests the admit() method
     */
    @Test
    public void admit() {
        assertEquals(0, carPark.occupancy());
        carPark.admit(vehicle);
        assertEquals(1, carPark.occupancy());
        Collection<Vehicle> occupants = carPark.occupants();
        for (Vehicle v : occupants) {
            assertEquals(vehicle, v);
        }
    }

    /**
     * Tests the release() method
     */
    @Test
    public void release() {
        assertEquals(0, carPark.occupancy());
        carPark.admit(vehicle);
        assertEquals(1, carPark.occupancy());
        Collection<Vehicle> occupants = carPark.occupants();
        for (Vehicle v : occupants) {
            assertEquals(vehicle, v);
        }
        carPark.release(vehicle);
        assertEquals(0, carPark.occupancy());
    }

    /**
     * Tests the occupancy() method
     */
    @Test
    public void occupancy() {
        assertEquals(0, carPark.occupancy());
        carPark.admit(vehicle);
        assertEquals(1, carPark.occupancy());
        carPark.release(vehicle);
        assertEquals(0, carPark.occupancy());
    }

    /**
     * Tests the capacity() method
     */
    @Test
    public void capacity() {
        assertEquals(CAP, carPark.capacity());
    }

    /**
     * Tests the availability() method
     */
    @Test
    public void availability() {
        assertEquals(CAP, carPark.availability());
        carPark.admit(vehicle);
        assertEquals(CAP - 1, carPark.availability());
        carPark.release(vehicle);
        assertEquals(CAP, carPark.availability());
    }

    /**
     * Tests the occupants() method
     */
    @Test
    public void occupants() {
        assertEquals(0, carPark.occupants().size());
        carPark.admit(vehicle);
        assertEquals(1, carPark.occupants().size());
        Collection<Vehicle> occupants = carPark.occupants();
        for (Vehicle v : occupants) {
            assertEquals(vehicle, v);
        }
        carPark.release(vehicle);
        assertEquals(0, carPark.occupants().size());
    }
}