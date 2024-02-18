package org.travel.packagemanagement.repository.impl;

import org.travel.packagemanagement.exceptions.DestinationNotFoundException;
import org.travel.packagemanagement.model.Destination;
import org.travel.packagemanagement.repository.DestinationRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DestinationRepositoryImpl implements DestinationRepository {
    private Map<String, Destination> destinationMap = new HashMap<>();
    @Override
    public void save(Destination destination) {
        this.destinationMap.put(destination.getName(), destination);
    }

    @Override
    public void saveAll(List<Destination> destinations) {
        this.destinationMap.putAll(destinations.stream().collect(Collectors.toMap(Destination::getName, Function.identity())));
    }

    @Override
    public Destination find(String destinationName) throws Exception {
        if(this.destinationMap.containsKey(destinationName)){
            return this.destinationMap.get(destinationName);
        }
        throw new DestinationNotFoundException("No Destination with name" + destinationName +" was found");
    }

    @Override
    public List<Destination> findAll() {
        return new ArrayList<>(this.destinationMap.values());
    }

    @Override
    public Destination remove(String destinationName) {
        Destination destination = this.destinationMap.get(destinationName);
        this.destinationMap.remove(destinationName);
        return destination;
    }
}
