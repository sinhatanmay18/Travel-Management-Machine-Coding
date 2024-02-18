package org.travel.packagemanagement.passenger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.travel.packagemanagement.exceptions.ActivityNotFoundException;
import org.travel.packagemanagement.exceptions.DestinationNotFoundException;
import org.travel.packagemanagement.exceptions.PackageNotFoundException;
import org.travel.packagemanagement.model.TravelPackage;
import org.travel.packagemanagement.repository.ActivityRepository;
import org.travel.packagemanagement.repository.DestinationRepository;
import org.travel.packagemanagement.repository.TravelPackageRepository;
import org.travel.packagemanagement.repository.impl.ActivityRepositoryImpl;
import org.travel.packagemanagement.repository.impl.DestinationRepositoryImpl;
import org.travel.packagemanagement.repository.impl.TravelPackageRepositoryImpl;
import org.travel.packagemanagement.service.ActivityService;
import org.travel.packagemanagement.service.DestinationService;
import org.travel.packagemanagement.service.TravelPackageService;
import org.travel.packagemanagement.service.impl.ActivityServiceImpl;
import org.travel.packagemanagement.service.impl.DestinationServiceImpl;
import org.travel.packagemanagement.service.impl.TravelPackageServiceImpl;
import org.travel.packagemanagement.util.TestData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PassengerTest {

    private ActivityRepository activityRepository;
    private DestinationRepository destinationRepository;
    private TravelPackageRepository travelPackageRepository;
    private ActivityService activityService;
    private DestinationService destinationService;
    private TravelPackageService travelPackageService;

    @BeforeEach
    public void init() {
        this.activityRepository = new ActivityRepositoryImpl();
        this.destinationRepository = new DestinationRepositoryImpl();
        this.travelPackageRepository = new TravelPackageRepositoryImpl();

        this.activityService = new ActivityServiceImpl(this.activityRepository);
        this.destinationService = new DestinationServiceImpl(this.destinationRepository);
        this.travelPackageService = new TravelPackageServiceImpl(this.travelPackageRepository);


        this.activityService.saveAllActivity(TestData.createActivities());
        this.destinationService.saveAllDestinations(TestData.createDestinations());
        this.travelPackageService.saveAllPackages(TestData.createTravelPackages());
    }


    @Test
    public void testSuccessfulEnrollmentOfGoldPassenger() throws Exception {
        Passenger goldPassenger = new GoldPassenger(1, "Mohan", 472.90, this.activityService, this.destinationService, this.travelPackageService);
        goldPassenger.subscribeToPackage("Hill Station");
        TravelPackage travelPackage = this.travelPackageService.findPackageByName("Hill Station");

        assertTrue(travelPackage.getPassengers().size() == 1);
        assertEquals("Mohan", travelPackage.getPassengers().get(0).getName());
        assertEquals(472.90, travelPackage.getPassengers().get(0).checkPassengerBalance());

        goldPassenger.enrollInActivity("kullu1", "kayak1");
        assertEquals(246, Math.floor(travelPackage.getPassengers().get(0).checkPassengerBalance()));
        assertEquals(2, this.activityService.findActivityByName("kayak1").getActivityCapacity());
    }

    @Test
    public void testSuccessfulEnrollmentOfStanderedPassenger() throws Exception {
        Passenger StanderedPassenger = new StandardPassenger(1, "Mohan", 450.90, this.activityService, this.destinationService, this.travelPackageService);
        StanderedPassenger.subscribeToPackage("Hill Station");
        TravelPackage travelPackage = this.travelPackageService.findPackageByName("Hill Station");

        assertTrue(travelPackage.getPassengers().size() == 1);
        assertEquals("Mohan", travelPackage.getPassengers().get(0).getName());
        assertEquals(450.90, travelPackage.getPassengers().get(0).checkPassengerBalance());

        StanderedPassenger.enrollInActivity("kullu1", "kayak1");
        assertEquals(450.90 - 251.49, travelPackage.getPassengers().get(0).checkPassengerBalance());
        assertEquals(2, this.activityService.findActivityByName("kayak1").getActivityCapacity());
    }

    @Test
    public void testSuccessfulEnrollmentOfPremimimPassenger() throws Exception {
        Passenger premimumPassenger = new PremiumPassenger(1, "Mohan", 500.90, this.activityService, this.destinationService, this.travelPackageService);
        premimumPassenger.subscribeToPackage("Hill Station");
        TravelPackage travelPackage = this.travelPackageService.findPackageByName("Hill Station");

        assertTrue(travelPackage.getPassengers().size() == 1);
        assertEquals("Mohan", travelPackage.getPassengers().get(0).getName());
        assertEquals(500.90, travelPackage.getPassengers().get(0).checkPassengerBalance());

        premimumPassenger.enrollInActivity("kullu1", "kayak1");
        assertEquals(500.90, travelPackage.getPassengers().get(0).checkPassengerBalance());
        assertEquals(2, this.activityService.findActivityByName("kayak1").getActivityCapacity());
    }

    @Test
    public void testPackageNotFound() {
        Passenger goldPassenger = new PremiumPassenger(1, "Mohan", 500.90, this.activityService, this.destinationService, this.travelPackageService);
        assertThrows(PackageNotFoundException.class, () -> goldPassenger.subscribeToPackage("Beaches Haven"));
    }

    @Test
    public void testDestinationNotFound() throws Exception {
        Passenger goldPassenger = new PremiumPassenger(1, "Mohan", 500.90, this.activityService, this.destinationService, this.travelPackageService);
        goldPassenger.subscribeToPackage("Hill Station");
        assertThrows(DestinationNotFoundException.class, () -> goldPassenger.enrollInActivity("kullu12", "kayak1"));
    }

    @Test
    public void testActivityNotFound() throws Exception {
        Passenger goldPassenger = new PremiumPassenger(1, "Mohan", 500.90, this.activityService, this.destinationService, this.travelPackageService);
        goldPassenger.subscribeToPackage("Hill Station");
        assertThrows(ActivityNotFoundException.class, () -> goldPassenger.enrollInActivity("kullu", "kayak123"));
    }

}