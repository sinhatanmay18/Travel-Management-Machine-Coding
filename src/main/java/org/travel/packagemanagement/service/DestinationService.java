package org.travel.packagemanagement.service;

import org.travel.packagemanagement.exceptions.DestinationNotFoundException;
import org.travel.packagemanagement.model.Activity;
import org.travel.packagemanagement.model.Destination;

import java.util.List;

public interface DestinationService {

    void saveDestination(Destination destination);

    void saveAllDestinations(List<Destination> destinations);

    Destination findDestinationByName(String destinationName) throws DestinationNotFoundException;

    List<Destination> findAllDestination();

    Destination removeDestination(String destinationName);
    void addActivity(String destinationName, Activity activity) throws Exception;

    void removeActivity(String destinationName, Activity activity) throws Exception;

    boolean checkActivityExists(String destinationName, Activity activity) throws Exception;
}
