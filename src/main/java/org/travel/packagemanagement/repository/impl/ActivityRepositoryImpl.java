package org.travel.packagemanagement.repository.impl;

import lombok.Getter;
import lombok.SneakyThrows;
import org.travel.packagemanagement.exceptions.TravelManagementException;
import org.travel.packagemanagement.model.Activity;
import org.travel.packagemanagement.repository.ActivityRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ActivityRepositoryImpl implements ActivityRepository {
    private Map<String, Activity> activityMap = new HashMap<>();
    @Override
    public void save(Activity activity) {
        this.activityMap.put(activity.getActivityName(), activity);
    }

    @Override
    public void saveAll(List<Activity> activities) {
        this.activityMap.putAll(activities.stream().collect(Collectors.toMap(Activity::getActivityName, Function.identity())));
    }

    @Override
    @SneakyThrows
    public Activity find(String activityName) {
        if(this.activityMap.containsKey(activityName)){
            return this.activityMap.get(activityName);
        }
        throw new TravelManagementException("No Activity with name" + activityName +" was found");
    }

    @Override
    public List<Activity> findAll() {
        return new ArrayList<>(this.activityMap.values());
    }

    @Override
    public Activity remove(String activityName) {
        Activity activity = this.activityMap.get(activityName);
        this.activityMap.remove(activityName);
        return activity;
    }
}
