package org.travel.packagemanagement.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Destination {
    private String name;
    private List<Activity> activities;
    private String packageName;
}
