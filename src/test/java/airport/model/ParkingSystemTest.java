package airport.model;

import airport.model.parking.ExpressParkingLot;
import airport.model.parking.ParkingLot;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Test for Parking System class
 * @author Adam Ross
 */
public class ParkingSystemTest {

    private ParkingSystem parkingSystem;
    private ParkingLot parkingLot;

    /**
     * Sets up for the tests
     */
    @Before
    public void setUp() {
        final String ParkingLot = "Express Parking Lot";
        final int CAP = 100;
        parkingSystem = new ParkingSystem();
        parkingLot = new ExpressParkingLot(ParkingLot, CAP);
    }

    /**
     * Tests the addLot() method
     */
    @Test
    public void addLot() {
        assertNull(parkingSystem.getParkingLot(parkingLot.toString()));
        parkingSystem.addLot(parkingLot);
        assertEquals(parkingLot, parkingSystem.getParkingLot(parkingLot.toString()));
    }

    /**
     * Tests the parkOptions() method
     */
    @Test
    public void parkOptions() {
        assertEquals(new HashSet<>(), parkingSystem.parkOptions(new HashSet<>()));
        HashSet<String> parkingLots = new HashSet<>(Collections.singletonList(parkingLot.toString()));
        parkingSystem.addLot(parkingLot);
        assertEquals(parkingLots, parkingSystem.parkOptions(new HashSet<>()));
    }

    /**
     * Tests the getParkingOption() method
     */
    @Test
    public void getParkingLot() {
        assertNull(parkingSystem.getParkingLot(parkingLot.toString()));
        parkingSystem.addLot(parkingLot);
        assertEquals(parkingLot, parkingSystem.getParkingLot(parkingLot.toString()));
        assertNull(parkingSystem.getParkingLot(null));
    }

    /**
     * Tests the vehicleFor() method
     */
    @Test
    public void vehicleFor() {
        Vehicle one = new Vehicle("ABC123");
        parkingLot.admit(one);
        assertEquals(one.regNo(), parkingSystem.vehicleFor(one.regNo()).regNo());
        assertEquals(new Vehicle("DEF456").regNo(), parkingSystem.vehicleFor("DEF456").regNo());
        assertNull(parkingSystem.vehicleFor(null).regNo());
    }
}