package org.travel.packagemanagement.passenger;

import lombok.Getter;
import lombok.Setter;
import org.travel.packagemanagement.exceptions.PackageNotFoundException;
import org.travel.packagemanagement.model.Activity;
import org.travel.packagemanagement.model.Destination;
import org.travel.packagemanagement.model.TravelPackage;
import org.travel.packagemanagement.service.ActivityService;
import org.travel.packagemanagement.service.DestinationService;
import org.travel.packagemanagement.service.TravelPackageService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Passenger {

    @Getter
    @Setter
    protected String name;

    @Getter
    @Setter
    protected int passengerNumber;

    protected List<Activity> enrolledActivities;

    protected DestinationService destinationService;
    protected TravelPackageService travelPackageService;
    protected ActivityService activityService;
    public Passenger(int passengerNumber,String name,DestinationService destinationService,TravelPackageService travelPackageService,ActivityService activityService) {
        this.passengerNumber = passengerNumber;
        this.name = name;
        this.travelPackageService = travelPackageService;
        this.destinationService = destinationService;
        this.activityService = activityService;
        this.enrolledActivities = new ArrayList<>();
    }

    /**
     * Allows user to subscribe to specific Travel Package.
     * A passenger cannot enroll into any activity before subscribing to a Travel Package.
     * @param packageName
     * @return boolean
     * @throws PackageNotFoundException
     */
    public boolean subscribeToPackage(String packageName) throws PackageNotFoundException {
        TravelPackage travelPackage = this.travelPackageService.findPackageByName(packageName);
        List<Passenger> passengers = new ArrayList<>(travelPackage.getPassengers());
        passengers.add(this);
        travelPackage.setPassengers(passengers);
        this.travelPackageService.savePackage(travelPackage);
        return true;
    }


    protected boolean hasPassengerSubscribedToPackage(String packageName) throws PackageNotFoundException {
        TravelPackage travelPackage = this.travelPackageService.findPackageByName(packageName);
        return travelPackage.getPassengers().stream().anyMatch(passenger -> passenger.getName().equals(this.name));
    }

    protected void updateAllInformation(Activity activity,Destination destination) throws PackageNotFoundException {
        List<Activity> activities = destination.getActivities().stream()
                .filter(actv -> !actv.getActivityName().equals(activity.getActivityName()))
                .collect(Collectors.toList());
        activities.add(activity);
        destination.setActivities(activities);
        TravelPackage travelPackage = this.travelPackageService.findPackageByName(destination.getPackageName());
        List<Destination> destinations = travelPackage
                .getItinerary().stream()
                .filter(dest -> !dest.getName().equals(destination.getName()))
                .collect(Collectors.toList());

        destinations.add(destination);
        travelPackage.setItinerary(destinations);
    }

    public abstract boolean enrollInActivity(String destinationName,String activityName) throws Exception;

    public abstract void printPassengerDetails();

    public abstract double checkPassengerBalance();

}
