package org.travel.packagemanagement.service.impl;

import org.travel.packagemanagement.exceptions.ActivityNotFoundException;
import org.travel.packagemanagement.model.Activity;
import org.travel.packagemanagement.repository.ActivityRepository;
import org.travel.packagemanagement.service.ActivityService;

import java.util.List;

public class ActivityServiceImpl implements ActivityService {

    private ActivityRepository activityRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository){
        this.activityRepository = activityRepository;
    }

    @Override
    public void saveActivity(Activity activity) {
        this.activityRepository.save(activity);
    }

    @Override
    public void saveAllActivity(List<Activity> activities) {
        this.activityRepository.saveAll(activities);
    }

    @Override
    public Activity findActivityByName(String activityName) throws ActivityNotFoundException {
        try {
            return this.activityRepository.find(activityName);
        } catch (Exception e) {
            throw new ActivityNotFoundException("No activity found with name " + activityName);
        }
    }

    @Override
    public List<Activity> findAllActivity() {
        return this.activityRepository.findAll();
    }

    @Override
    public Activity removeActivity(String activityName) {
        return this.activityRepository.remove(activityName);
    }
}
