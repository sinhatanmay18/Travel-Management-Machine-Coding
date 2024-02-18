package org.travel.packagemanagement.passenger;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.travel.packagemanagement.model.Activity;
import org.travel.packagemanagement.model.Destination;
import org.travel.packagemanagement.service.ActivityService;
import org.travel.packagemanagement.service.DestinationService;
import org.travel.packagemanagement.service.TravelPackageService;

public class StandardPassenger extends Passenger{
    private final Logger LOG = LoggerFactory.getLogger(StandardPassenger.class);
    @Getter private double balance;

    public StandardPassenger(int passengerNumber, String name, double balance, ActivityService activityService, DestinationService destinationService, TravelPackageService travelPackageService) {
        super(passengerNumber,name,destinationService,travelPackageService,activityService);
        this.balance = balance;
    }

    /**
     * Allows Standard Passenger to enroll into an Activity at a particular Destination post subscribing to a Travel Package.
     * A Standard Passenger is not offered any discount.
     * @param destinationName
     * @param activityName
     * @return boolean
     */
    @Override
    public boolean enrollInActivity(String destinationName, String activityName) {
        try {
            Destination destination = this.destinationService.findDestinationByName(destinationName);
            Activity activity = this.activityService.findActivityByName(activityName);
            boolean hasSubscribedToPackage = this.subscribeToPackage(destination.getPackageName());
            if(hasSubscribedToPackage && this.destinationService.checkActivityExists(destinationName,activity)) {
                int currentActivityCapacity = activity.getActivityCapacity();
                if (this.balance - activity.getActivityCost() > 0 && currentActivityCapacity - 1 > 0) {
                    enrolledActivities.add(activity);
                    this.balance -= activity.getActivityCost();
                    activity.setActivityCapacity(currentActivityCapacity - 1);
                    this.updateAllInformation(activity,destination);
                    return true;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    /**
     * Functionality to display necessary Passenger Details such as Balance and Activities they have enrolled into.
     */
    @Override
    public void printPassengerDetails(){
        LOG.info("---------------------------------------------------------------------");
        LOG.info("Name of the passenger {}",this.getName());
        LOG.info("Passenger's number {}",this.getPassengerNumber());
        LOG.info("Current balance {}",this.balance);

        this.enrolledActivities.forEach(activity -> {
            LOG.info("-------------x---------------------------------x---------------------");
            LOG.info("Name of the activity {}",activity.getActivityName());
            LOG.info("Destination of the activity {}",activity.getAssignedToDestination());
            LOG.info("Price paid for the activity {}",activity.getActivityCost());
        });
    }

    @Override
    public double checkPassengerBalance() {
        return this.balance;
    }
}
