package org.travel.packagemanagement.passenger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.travel.packagemanagement.model.Activity;
import org.travel.packagemanagement.model.Destination;
import org.travel.packagemanagement.service.ActivityService;
import org.travel.packagemanagement.service.DestinationService;
import org.travel.packagemanagement.service.TravelPackageService;

public class PremiumPassenger extends Passenger {
    private final Logger LOG = LoggerFactory.getLogger(PremiumPassenger.class);
    private double balance;

    public PremiumPassenger(int passengerNumber, String name, double balance, ActivityService activityService, DestinationService destinationService, TravelPackageService travelPackageService) {
        super(passengerNumber, name, destinationService, travelPackageService, activityService);
        this.balance = balance;
    }
    /**
     * Allows Premium Passenger to enroll into an Activity at a particular Destination post subscribing to a Travel Package.
     * A Premium Passenger can enroll into any Activity free of cost.
     * @param destinationName
     * @param activityName
     * @return boolean
     */
    @Override
    public boolean enrollInActivity(String destinationName, String activityName) throws Exception {
        Destination destination = this.destinationService.findDestinationByName(destinationName);
        Activity activity = this.activityService.findActivityByName(activityName);
        boolean hasSubscribedToPackage = this.hasPassengerSubscribedToPackage(destination.getPackageName());
        if (hasSubscribedToPackage && this.destinationService.checkActivityExists(destinationName, activity)) {
            int currentActivityCapacity = activity.getActivityCapacity();

            if (currentActivityCapacity - 1 > 0) {
                enrolledActivities.add(activity);
                activity.setActivityCapacity(currentActivityCapacity - 1);
                this.updateAllInformation(activity, destination);
                return true;
            }
        }

        return false;
    }
    /**
     * Functionality to display necessary Passenger Details such as Balance and Activities they have enrolled into.
     */
    @Override
    public void printPassengerDetails() {
        LOG.info("---------------------------------------------------------------------");
        LOG.info("Name of the passenger {}", this.getName());
        LOG.info("Passenger's number {}", this.getPassengerNumber());
        LOG.info("Current balance {}", this.balance);

        this.enrolledActivities.forEach(activity -> {
            LOG.info("-------------x---------------------------------x---------------------");
            LOG.info("Name of the activity {}", activity.getActivityName());
            LOG.info("Destination of the activity {}", activity.getAssignedToDestination());
            LOG.info("Price paid for the activity {}", 0);
        });
    }

    @Override
    public double checkPassengerBalance() {
        return this.balance;
    }
}
