package com.spaceX.Space.X.Rocket.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "spacex_launches")
public class LaunchEntity {

    @Id
    private String id;

    private String name;
    private String dateUtc;
    private String rocketId;
    private String launchpad;
    private Boolean success;
    private String details;

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDateUtc() { return dateUtc; }
    public void setDateUtc(String dateUtc) { this.dateUtc = dateUtc; }

    public String getRocketId() { return rocketId; }
    public void setRocketId(String rocketId) { this.rocketId = rocketId; }

    public String getLaunchpad() { return launchpad; }
    public void setLaunchpad(String launchpad) { this.launchpad = launchpad; }

    public Boolean getSuccess() { return success; }
    public void setSuccess(Boolean success) { this.success = success; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
}