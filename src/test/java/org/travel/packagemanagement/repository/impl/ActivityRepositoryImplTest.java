package org.travel.packagemanagement.repository.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.travel.packagemanagement.model.Activity;
import org.travel.packagemanagement.repository.ActivityRepository;

import java.util.Arrays;

class ActivityRepositoryImplTest {

    private ActivityRepository activityRepository;

    @BeforeEach
    public void init(){
        this.activityRepository = new ActivityRepositoryImpl();
    }

    @Test
    void save() {
        this.activityRepository.save(Activity.builder()
                        .activityName("Biking")
                        .activityCapacity(2)
                        .activityCost(250.53)
                        .description("Do biking")
                        .isAssignedToAnyDestination(false)
                        .build());
        Assertions.assertTrue(this.activityRepository.findAll().size() == 1);
    }

    @Test
    void saveAll() {
        this.activityRepository.saveAll(Arrays.asList(Activity.builder()
                .activityName("Biking")
                .activityCapacity(2)
                .activityCost(250.53)
                .description("Do biking")
                .isAssignedToAnyDestination(false)
                .build(),
                Activity.builder()
                        .activityName("Training")
                        .activityCapacity(2)
                        .activityCost(200.53)
                        .description("Do Training")
                        .isAssignedToAnyDestination(false)
                        .build()));
        Assertions.assertTrue(this.activityRepository.findAll().size() == 2);
    }

    @Test
    void find() throws Exception {
        this.activityRepository.save(Activity.builder()
                .activityName("Biking")
                .activityCapacity(2)
                .activityCost(250.53)
                .description("Do biking")
                .isAssignedToAnyDestination(false)
                .build());
        Activity activity = this.activityRepository.find("Biking");
        Assertions.assertEquals("Biking",activity.getActivityName());
        Assertions.assertEquals(250.53,activity.getActivityCost());
    }

    @Test
    void remove() throws Exception {
        this.activityRepository.saveAll(Arrays.asList(Activity.builder()
                        .activityName("Biking")
                        .activityCapacity(2)
                        .activityCost(250.53)
                        .description("Do biking")
                        .isAssignedToAnyDestination(false)
                        .build(),
                Activity.builder()
                        .activityName("Training")
                        .activityCapacity(2)
                        .activityCost(200.53)
                        .description("Do Training")
                        .isAssignedToAnyDestination(false)
                        .build()));

        this.activityRepository.remove("Biking");
        Assertions.assertTrue(this.activityRepository.findAll().size() == 1);
        Assertions.assertEquals("Training",this.activityRepository.find("Training").getActivityName());
    }
}