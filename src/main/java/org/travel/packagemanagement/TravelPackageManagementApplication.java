package org.travel.packagemanagement;

import org.travel.packagemanagement.passenger.GoldPassenger;
import org.travel.packagemanagement.passenger.Passenger;
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

public class TravelPackageManagementApplication {
  public static void main(String[] args) throws Exception {
    
    /*
    Note: For this application there are following assumptions made.
    1) There are 3 types of passengers Standard, Gold , Premium.
    2) A passenger need to first subscribe to travel package in order to enroll in activity.
    3) Passenger can subscribe to package using subscribeToPackage method and then enroll in activity using enrollInActivity.
    4) One can use methods present in TravelPackageService.java and Passenger.java class to print details.
       5) Added additional fields in model classes to facilitate certain functionalities:
        a. Activity.java :
            I. 'assignedToDestination' is used to identify the Destination to which Activity is assigned to.
            II. 'isAssignedToAnyDestination' is used to identify if an Activity already belongs to a Destination. If set then Activity cannot be part of other Destination.

        b. Destination.java :
            I. 'PackageName' : is used to identify the Travel Package to which Destination is assigned to.

     6) For further information, refer to README.md.
     */


    // Open below comment line to run a sample or use Passenger test class which contains most of scenario based testing.
    //sampleRun();
  }

  public static void sampleRun() throws Exception {
    ActivityRepository activityRepository = new ActivityRepositoryImpl();
    DestinationRepository destinationRepository = new DestinationRepositoryImpl();
    TravelPackageRepository travelPackageRepository = new TravelPackageRepositoryImpl();

    ActivityService activityService = new ActivityServiceImpl(activityRepository);
    DestinationService destinationService = new DestinationServiceImpl(destinationRepository);
    TravelPackageService travelPackageService = new TravelPackageServiceImpl(travelPackageRepository);

    activityService.saveAllActivity(TestData.createActivities());
    destinationService.saveAllDestinations(TestData.createDestinations());
    travelPackageService.saveAllPackages(TestData.createTravelPackages());

    travelPackageService.printTravelPackageItinerary("Hill Station");

    Passenger passenger = new GoldPassenger(1,"Mohan",400,activityService,destinationService,travelPackageService);
    passenger.subscribeToPackage("Hill Station");
    passenger.enrollInActivity("kullu1","kayak1");

    travelPackageService.printPassengersList("Hill Station");
    passenger.printPassengerDetails();

    travelPackageService.showAvailableActivities("Hill Station");
  }
}
