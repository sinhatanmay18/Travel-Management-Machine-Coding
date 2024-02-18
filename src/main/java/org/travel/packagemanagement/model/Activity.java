package org.travel.packagemanagement.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Activity {
    private String activityName;
    private String description;
    private double activityCost;
    private int activityCapacity;
    private boolean isAssignedToAnyDestination;
    private String assignedToDestination;
}
