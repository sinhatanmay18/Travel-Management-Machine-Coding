package org.travel.packagemanagement.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.travel.packagemanagement.exceptions.DestinationNotFoundException;
import org.travel.packagemanagement.model.Activity;
import org.travel.packagemanagement.model.Destination;
import org.travel.packagemanagement.repository.DestinationRepository;
import org.travel.packagemanagement.service.DestinationService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DestinationServiceImpl implements DestinationService {

    private final Logger LOG = LoggerFactory.getLogger(DestinationServiceImpl.class);
    private DestinationRepository destinationRepository;
    public DestinationServiceImpl(DestinationRepository travelManagementRepository){
        this.destinationRepository = travelManagementRepository;
    }

    @Override
    public void saveDestination(Destination destination) {
        this.destinationRepository.save(destination);
    }

    @Override
    public void saveAllDestinations(List<Destination> destinations) {
        this.destinationRepository.saveAll(destinations);
    }

    @Override
    public Destination findDestinationByName(String destinationName) throws DestinationNotFoundException {
        try {
            return this.destinationRepository.find(destinationName);
        } catch (Exception e) {
            throw new DestinationNotFoundException("No destination found with name" + destinationName);
        }
    }

    @Override
    public List<Destination> findAllDestination() {
        return this.destinationRepository.findAll();
    }

    @Override
    public Destination removeDestination(String destinationName) {
        return this.destinationRepository.remove(destinationName);
    }

    @Override
    public void addActivity(String destinationName, Activity activity) throws Exception {
        try{
            if(!activity.isAssignedToAnyDestination()) {
                Destination destination = destinationRepository.find(destinationName);
                List<Activity> activities = new ArrayList<>(destination.getActivities());
                activity.setAssignedToAnyDestination(true);
                activities.add(activity);
                destination.setActivities(activities);
                this.destinationRepository.save(destination);
            } else {
                LOG.info("Activity already assigned to some other destination");
            }
        } catch (Exception exception) {
            if(exception instanceof DestinationNotFoundException) {
                LOG.error("No Destination found with {}", destinationName);
            }

            throw exception;
        }
    }


    @Override
    public void removeActivity(String destinationName, Activity activity) throws Exception {
        try {
            Destination destination = this.destinationRepository.find(destinationName);
            boolean isActivityPartOfDestination = destination.getActivities()
                    .stream()
                    .anyMatch(currentActivity -> currentActivity.getActivityName().equals(activity.getActivityName()));

            if(isActivityPartOfDestination){
                List<Activity> activities = destination.getActivities()
                        .stream()
                        .filter(currentActivity -> !currentActivity.getActivityName().equals(activity.getActivityName()))
                        .collect(Collectors.toList());
                activity.setAssignedToAnyDestination(false);
                destination.setActivities(activities);
                this.destinationRepository.save(destination);
            } else{
                System.out.println("Activity Not Found in current destination");
            }
        } catch (DestinationNotFoundException ex) {
            LOG.error("No Destination found with {}", destinationName);
        }
    }

    /**
     * Functionality that ensures activity is available at a particular destination.
     * @param destinationName
     * @param activity
     * @return
     * @throws DestinationNotFoundException
     */
    @Override
    public boolean checkActivityExists(String destinationName, Activity activity) throws DestinationNotFoundException {
        try {
            Destination destination = this.destinationRepository.find(destinationName);
            return destination.getActivities()
                    .stream()
                    .anyMatch(currentActivity -> currentActivity.getActivityName().equals(activity.getActivityName()));
        } catch (Exception exception) {
            throw new DestinationNotFoundException("No Destination found with {}"+ destinationName);
        }
    }
}
