package org.travel.packagemanagement.repository.impl;

import org.travel.packagemanagement.exceptions.PackageNotFoundException;
import org.travel.packagemanagement.model.Destination;
import org.travel.packagemanagement.model.TravelPackage;
import org.travel.packagemanagement.repository.TravelPackageRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TravelPackageRepositoryImpl implements TravelPackageRepository {
    private Map<String, TravelPackage> travelPackageMap = new HashMap<>();
    @Override
    public void save(TravelPackage travelPackage) {
        this.travelPackageMap.put(travelPackage.getPackageName(), travelPackage);
    }

    @Override
    public void saveAll(List<TravelPackage> travelPackages) {
        this.travelPackageMap.putAll(travelPackages.stream().collect(Collectors.toMap(TravelPackage::getPackageName, Function.identity())));
    }

    @Override
    public TravelPackage find(String packageName) throws Exception {
        if(this.travelPackageMap.containsKey(packageName)) {
            return this.travelPackageMap.get(packageName);
        }
        throw new PackageNotFoundException("No Package with name" + packageName +" was found");
    }

    @Override
    public List<TravelPackage> findAll() {
        return new ArrayList<>(this.travelPackageMap.values());
    }

    @Override
    public TravelPackage remove(String packageName) {
        TravelPackage travelPackage = this.travelPackageMap.get(packageName);
        this.travelPackageMap.remove(packageName);
        return travelPackage;
    }
}
