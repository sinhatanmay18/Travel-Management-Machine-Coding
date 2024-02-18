package org.travel.packagemanagement.service;

import org.travel.packagemanagement.exceptions.PackageNotFoundException;
import org.travel.packagemanagement.model.TravelPackage;

import java.util.List;

public interface TravelPackageService {
    void savePackage(TravelPackage travelPackage);

    void saveAllPackages(List<TravelPackage> travelPackages);

    TravelPackage findPackageByName(String packageName) throws PackageNotFoundException;

    List<TravelPackage> findAllPackages();

    TravelPackage removeTravelPackage(String packageName);

    void printTravelPackageItinerary(String packageName);

    void printPassengersList(String packageName);

    void showAvailableActivities(String packageName);
}
