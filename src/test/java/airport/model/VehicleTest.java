package airport.model;

import airport.model.parking.ExpressParkingLot;
import airport.model.parking.ParkingLot;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Test for the Vehicle class
 * @author Adam Ross
 */
public class VehicleTest {

    private Vehicle vehicle;
    private LocalDateTime dateTime;
    private BillableEntity vehicleOwner;
    private ParkingLot parkingLot;

    /**
     * Sets up for the tests
     */
    @Before
    public void setUp() {
        final String ParkingLot = "Express Parking Lot";
        final int CAP = 100;
        vehicle = new Vehicle("ABC123");
        dateTime = LocalDateTime.of(2099, Month.JANUARY, 1, 0, 0, 0);
        vehicleOwner = new BillableEntity("Frank", "Home", 123);
        parkingLot = new ExpressParkingLot(ParkingLot, CAP);
    }

    /**
     * Tests the setArrival() method
     */
    @Test
    public void setArrival() {
        assertNull(vehicle.getArrival());
        vehicle.setArrival(dateTime);
        assertEquals(dateTime, vehicle.getArrival());
    }

    /**
     * Tests the setDeparture() method
     */
    @Test
    public void setDeparture() {
        assertNull(vehicle.getDeparture());
        vehicle.setDeparture(dateTime);
        assertEquals(dateTime, vehicle.getDeparture());
    }

    /**
     * Tests the getArrival() method
     */
    @Test
    public void getArrival() {
        assertNull(vehicle.getArrival());
        vehicle.setArrival(dateTime);
        assertEquals(dateTime, vehicle.getArrival());
    }

    /**
     * Tests the getDeparture() method
     */
    @Test
    public void getDeparture() {
        assertNull(vehicle.getDeparture());
        vehicle.setDeparture(dateTime);
        assertEquals(dateTime, vehicle.getDeparture());
    }

    /**
     * Tests the setOwner() method
     */
    @Test
    public void setOwner() {
        assertNull(vehicle.getOwner());
        vehicle.setOwner(vehicleOwner);
        assertEquals(vehicleOwner, vehicle.getOwner());
    }

    /**
     * Tests the getOwner() method
     */
    @Test
    public void getOwner() {
        assertNull(vehicle.getOwner());
        vehicle.setOwner(vehicleOwner);
        assertEquals(vehicleOwner, vehicle.getOwner());
    }

    /**
     * Tests the setParkingLot() method
     */
    @Test
    public void setParkingLot() {
        assertNull(vehicle.getParkingLot());
        vehicle.setParkingLot(parkingLot);
        assertEquals(parkingLot, vehicle.getParkingLot());
    }

    /**
     * Tests the getParkingLot() method
     */
    @Test
    public void getParkingLot() {
        assertNull(vehicle.getParkingLot());
        vehicle.setParkingLot(parkingLot);
        assertEquals(parkingLot, vehicle.getParkingLot());
    }

    /**
     * Tests the regNo() method
     */
    @Test
    public void regNo() {
        assertEquals("ABC123", vehicle.regNo());
    }

    /**
     * Tests the identityCrisis() method
     */
    @Test
    public void identityCrisis() {
        assertTrue(vehicle.identityCrisis("ABC123"));
        assertFalse(vehicle.identityCrisis("DEF456"));
    }
}