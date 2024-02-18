package org.travel.packagemanagement.service.impl;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.travel.packagemanagement.exceptions.PackageNotFoundException;
import org.travel.packagemanagement.model.TravelPackage;
import org.travel.packagemanagement.repository.TravelPackageRepository;
import org.travel.packagemanagement.service.TravelPackageService;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TravelPackageServiceImpl implements TravelPackageService {
    private final Logger LOG = LoggerFactory.getLogger(TravelPackageServiceImpl.class);

    private TravelPackageRepository travelPackageRepository;

    public TravelPackageServiceImpl(TravelPackageRepository travelPackageRepository){
        this.travelPackageRepository = travelPackageRepository;
    }

    @Override
    public void savePackage(TravelPackage travelPackage) {
        this.travelPackageRepository.save(travelPackage);
    }

    @Override
    public void saveAllPackages(List<TravelPackage> travelPackages) {
        this.travelPackageRepository.saveAll(travelPackages);
    }

    @Override
    public TravelPackage findPackageByName(String packageName) throws PackageNotFoundException {
        try {
            return this.travelPackageRepository.find(packageName);
        } catch (Exception e) {
            throw new PackageNotFoundException("No Destination found with name " + packageName);
        }
    }

    @Override
    public List<TravelPackage> findAllPackages() {
        return this.travelPackageRepository.findAll();
    }

    @Override
    public TravelPackage removeTravelPackage(String packageName) {
        return this.travelPackageRepository.remove(packageName);
    }

    /**
     * Functionality to print itinerary of travel package including key details of the Destination.
     * @param packageName
     */
    @Override
    public void printTravelPackageItinerary(String packageName) {
        try {
            TravelPackage travelPackage = this.travelPackageRepository.find(packageName);
            LOG.info("---------------------------------------------------------------------");
            LOG.info("Travel package Name = {}, with following destination:",travelPackage.getPackageName());
            AtomicInteger counter = new AtomicInteger();
            LOG.info("---------x---------------------------x----------------------x--------");
            travelPackage.getItinerary().forEach(itinerary -> {
                LOG.info("Destination {} = {} and following are the activities available:",counter.getAndIncrement(),itinerary.getName());
                itinerary.getActivities().forEach(activity ->{
                    LOG.info("---------*---------------------------*----------------------*--------");
                    LOG.info("Activity Name: {}", activity.getActivityName());
                    LOG.info("Activity Cost: {}", activity.getActivityCost());
                    LOG.info("Activity Capacity: {}",activity.getActivityCapacity());
                    LOG.info("Activity Description {}",activity.getDescription());
                    LOG.info("---------*---------------------------*----------------------*--------");
                });
                LOG.info("---------x---------------------------x----------------------x--------");
            });
            LOG.info("---------------------------------------------------------------------");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Functionality to print passenger list of the Travel Package including key details like Package information & Passengers enrolled.
     * @param packageName
     */
    @Override
    public void printPassengersList(String packageName) {
        try {
            TravelPackage travelPackage = this.travelPackageRepository.find(packageName);
            LOG.info("---------------------------------------------------------------------");
            LOG.info("Travel package Name = {} ",travelPackage.getPackageName());
            LOG.info("Total Capacity of Travel package = {} ",travelPackage.getPackageCapacity());
            LOG.info("Total Passengers currently enrolled = {} ",travelPackage.getPassengers().size());

            travelPackage.getPassengers().forEach(passenger ->{
                LOG.info("---------------x---------------------x---------------------------x---");
                LOG.info("Name of the passenger {} and Passenger Number {}",passenger.getName(),passenger.getPassengerNumber());
                LOG.info("---------------x---------------------x---------------------------x---");
            } );
            LOG.info("---------------------------------------------------------------------");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Functionality to print the details of all the activities that still have spaces available with exact number of spaces available.
     * @param packageName
     */
    @Override
    public void showAvailableActivities(String packageName) {
        try {
            TravelPackage travelPackage = this.travelPackageRepository.find(packageName);
            LOG.info("---------------------------------------------------------------------");
            LOG.info("Travel package Name = {} ",travelPackage.getPackageName());
            travelPackage.getItinerary().forEach(itinerary -> {
                LOG.info("-----------------x-------------------x---------------------x---------");
                itinerary.getActivities().forEach(activity -> {
                    if(activity.getActivityCapacity() > 0) {
                        LOG.info("-----------------*******---------------*******-------------------*******--------");
                        LOG.info("Destination = {} Activity Name = {} ,", itinerary.getName(), activity.getActivityName());
                        LOG.info(" Activity slots available = {}", activity.getActivityCapacity());
                        LOG.info("---------------*******---------------*******-----------------*******-------");
                    }
                });
                LOG.info("-----------------x-------------------x---------------------x---------");
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
