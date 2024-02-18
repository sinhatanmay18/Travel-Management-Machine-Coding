package org.travel.packagemanagement.util;

import org.travel.packagemanagement.model.Activity;
import org.travel.packagemanagement.model.Destination;
import org.travel.packagemanagement.model.TravelPackage;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestData {
    public static List<Activity> createActivities(){
        return Arrays.asList(Activity.builder()
                        .activityName("kayak")
                        .description("Do kayak")
                        .activityCost(250.49)
                        .activityCapacity(3)
                        .assignedToDestination("kullu")
                        .isAssignedToAnyDestination(false)
                        .build(),
                Activity.builder()
                        .activityName("kayak1")
                        .description("Do kayak")
                        .activityCost(251.49)
                        .activityCapacity(3)
                        .assignedToDestination("kullu1")
                        .isAssignedToAnyDestination(false)
                        .build(),
                Activity.builder()
                        .activityName("kayak2")
                        .description("Do kayak2")
                        .activityCost(252.49)
                        .activityCapacity(3)
                        .isAssignedToAnyDestination(false)
                        .build(),
                Activity.builder()
                        .activityName("kayak3")
                        .description("Do kayak3")
                        .activityCost(253.49)
                        .activityCapacity(3)
                        .isAssignedToAnyDestination(false)
                        .build(),
                Activity.builder()
                        .activityName("kayak4")
                        .description("Do kayak4")
                        .activityCost(254.49)
                        .activityCapacity(3)
                        .isAssignedToAnyDestination(false)
                        .build());
    }

    public static List<Destination> createDestinations(){
        return Arrays.asList(Destination.builder()
                        .name("kullu")
                        .packageName("Hill Station")
                        .activities(Arrays.asList(Activity.builder()
                                .activityName("kayak")
                                .description("Do kayak")
                                .activityCost(250.49)
                                .activityCapacity(3)
                                .isAssignedToAnyDestination(false)
                                .build(),
                                Activity.builder()
                                        .activityName("kayak2")
                                        .description("Do kayak2")
                                        .activityCost(250.49)
                                        .activityCapacity(3)
                                        .isAssignedToAnyDestination(false)
                                        .assignedToDestination("kullu")
                                        .build()))
                .build(),
                Destination.builder()
                        .name("kullu1")
                        .packageName("Hill Station")
                        .activities(Arrays.asList(Activity.builder()
                                .activityName("kayak1")
                                .description("Do kayak")
                                .activityCost(251.49)
                                .activityCapacity(3)
                                .assignedToDestination("kullu1")
                                .isAssignedToAnyDestination(false)
                                .build()))
                        .build());
    }

    public static List<TravelPackage> createTravelPackages(){
        return Arrays.asList(TravelPackage.builder()
                .packageName("Hill Station")
                .itinerary(createDestinations())
                .packageCapacity(4)
                .passengers(Collections.emptyList())
                .build());
    }
}
