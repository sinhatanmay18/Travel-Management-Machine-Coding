package org.travel.packagemanagement.repository.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.travel.packagemanagement.model.TravelPackage;
import org.travel.packagemanagement.repository.TravelPackageRepository;
import org.travel.packagemanagement.util.TestData;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class TravelPackageRepositoryImplTest {

    private TravelPackageRepository travelPackageRepository;

    @BeforeEach
    public void init(){
        this.travelPackageRepository = new TravelPackageRepositoryImpl();
    }

    @Test
    void findAll() {
        this.travelPackageRepository.saveAll(Arrays.asList(TravelPackage.builder()
                .packageName("Hill Station")
                .itinerary(TestData.createDestinations())
                .packageCapacity(4)
                .passengers(Collections.emptyList())
                .build(),
                TravelPackage.builder()
                        .packageName("Beach Dreams")
                        .itinerary(TestData.createDestinations())
                        .packageCapacity(4)
                        .passengers(Collections.emptyList())
                        .build()));

        Assertions.assertEquals(2,this.travelPackageRepository.findAll().size());
    }

    @Test
    void remove() throws Exception {
        this.travelPackageRepository.saveAll(Arrays.asList(TravelPackage.builder()
                        .packageName("Hill Station")
                        .itinerary(TestData.createDestinations())
                        .packageCapacity(4)
                        .passengers(Collections.emptyList())
                        .build(),
                TravelPackage.builder()
                        .packageName("Beach Dreams")
                        .itinerary(TestData.createDestinations())
                        .packageCapacity(4)
                        .passengers(Collections.emptyList())
                        .build()));
        this.travelPackageRepository.remove("Beach Dreams");
        Assertions.assertEquals(1,this.travelPackageRepository.findAll().size());
        Assertions.assertEquals("Hill Station",this.travelPackageRepository.find("Hill Station").getPackageName());
    }
}