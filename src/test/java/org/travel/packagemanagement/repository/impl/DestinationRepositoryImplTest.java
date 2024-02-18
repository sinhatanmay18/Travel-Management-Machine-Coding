package org.travel.packagemanagement.repository.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.travel.packagemanagement.model.Activity;
import org.travel.packagemanagement.model.Destination;
import org.travel.packagemanagement.repository.DestinationRepository;

import java.util.Arrays;

class DestinationRepositoryImplTest {

    private DestinationRepository destinationRepository;

    @BeforeEach
    public void init(){
        this.destinationRepository = new DestinationRepositoryImpl();
    }

    @Test
    void save() {
        this.destinationRepository.save(Destination.builder()
                        .name("Lucknow")
                        .activities(Arrays.asList(Activity.builder()
                                .activityName("Biking")
                                .activityCapacity(2)
                                .activityCost(250.53)
                                .description("Do biking")
                                .isAssignedToAnyDestination(false)
                                .build()))
                        .build());
        Assertions.assertTrue(this.destinationRepository.findAll().size() == 1);
    }

    @Test
    void find() throws Exception {
        this.destinationRepository.save(Destination.builder()
                .name("Lucknow")
                .activities(Arrays.asList(Activity.builder()
                        .activityName("Biking")
                        .activityCapacity(2)
                        .activityCost(250.53)
                        .description("Do biking")
                        .isAssignedToAnyDestination(false)
                        .build()))
                .build());
        Destination destination = this.destinationRepository.find("Lucknow");
        Assertions.assertEquals("Lucknow",destination.getName());
    }

    @Test
    void remove() {
    }
}