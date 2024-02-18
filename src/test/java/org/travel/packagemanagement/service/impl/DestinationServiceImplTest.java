package org.travel.packagemanagement.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.travel.packagemanagement.model.Activity;
import org.travel.packagemanagement.model.Destination;
import org.travel.packagemanagement.repository.ActivityRepository;
import org.travel.packagemanagement.repository.DestinationRepository;
import org.travel.packagemanagement.repository.impl.ActivityRepositoryImpl;
import org.travel.packagemanagement.repository.impl.DestinationRepositoryImpl;
import org.travel.packagemanagement.service.DestinationService;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class DestinationServiceImplTest {

    private DestinationService destinationService;
    private DestinationRepository destinationRepository;

    private ActivityRepository activityRepository;

    @BeforeEach
    public void init(){
        this.destinationRepository = new DestinationRepositoryImpl();
        this.activityRepository = new ActivityRepositoryImpl();
        this.destinationService = new DestinationServiceImpl(this.destinationRepository);
    }


    @Test
    void addActivity() throws Exception {
        Activity activity = Activity.builder()
                .activityName("Biking")
                .activityCapacity(2)
                .activityCost(250.53)
                .description("Do biking")
                .isAssignedToAnyDestination(false)
                .build();
        this.activityRepository.save(activity);
        this.destinationService.saveDestination(Destination.builder()
                .name("Lucknow")
                .activities(Collections.singletonList(activity))
                .build());
        this.destinationService.addActivity("Lucknow",activity);
        Assertions.assertTrue(this.activityRepository.find("Biking").isAssignedToAnyDestination());
    }

    @Test
    void removeActivity() throws Exception {
        Activity activity = Activity.builder()
                .activityName("Biking")
                .activityCapacity(2)
                .activityCost(250.53)
                .description("Do biking")
                .isAssignedToAnyDestination(false)
                .build();
        this.activityRepository.save(activity);
        this.destinationService.saveDestination(Destination.builder()
                .name("Lucknow")
                .activities(Collections.singletonList(activity))
                .build());
        this.destinationService.removeActivity("Lucknow",activity);
        Assertions.assertTrue(this.destinationRepository.find("Lucknow").getActivities().size() == 0);

    }
}