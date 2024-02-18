package org.travel.packagemanagement.service;

import org.travel.packagemanagement.exceptions.ActivityNotFoundException;
import org.travel.packagemanagement.model.Activity;

import java.util.List;

public interface ActivityService {

    void saveActivity(Activity activity);

    void saveAllActivity(List<Activity> activities);

    Activity findActivityByName(String activityName) throws ActivityNotFoundException;


    List<Activity> findAllActivity();

    Activity removeActivity(String activityName);

}
