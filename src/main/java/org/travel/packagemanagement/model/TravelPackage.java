package org.travel.packagemanagement.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.travel.packagemanagement.passenger.Passenger;

import java.util.List;

@Getter
@Setter
@Builder
public class TravelPackage {
    private String packageName;
    private int packageCapacity;
    private List<Destination> itinerary;
    private List<Passenger> passengers;
}
