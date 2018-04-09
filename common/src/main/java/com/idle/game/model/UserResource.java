package com.idle.game.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
import javax.validation.ValidationException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author rafael
 */
@Document(collection = "userresource")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UserResource implements Serializable {

    @Id
    private String id;
    @Indexed
    private String userId;
    private Date lastTimeResourcesCollected = new Date();
    private Date lastTimeResourcesCollectedHour = new Date();
    private Date lastTimeResourcesCollectedDay = new Date();
    private List<Resource> resources = new ArrayList<>();

    public UserResource() {
    }

    public UserResource(String userId, List<Resource> resources) {
        this.userId = userId;
        this.resources = resources;
    }

    public List<Resource> getResources() {
        return resources;
    }

    private Stream<Resource> getNotTimeResources() {
        return resources.stream().filter((r) -> !r.getType().isTimeResource());
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public Resource getResource(ResourceType resourceType) {
        Optional<Resource> ret = this.getResources().stream().filter((t) -> {
            return t.getType().equals(resourceType);
        }).findFirst();

        if (ret.isPresent()) {
            return ret.get();
        } else {
            return null;
        }
    }

    public void useResources(List<Resource> resources) {
        changeResources(resources, Boolean.FALSE);

    }

    public void addResources(List<Resource> resources) {
        changeResources(resources, Boolean.TRUE);
    }

    private void changeResources(List<Resource> resources, Boolean add) {

        for (Resource resource : resources) {
            Resource compResource = this.getResource(resource.getType());
            if (compResource != null) {

                Long value = Math.abs(resource.getValue()) * (add ? 1 : -1);

                if ((compResource.getValue() + value) >= 0) {
                    compResource.setValue(compResource.getValue() + value);
                } else {
                    throw new ValidationException("using.more.resource.you.have");
                }

            } else if (add) {

                Long value = Math.abs(resource.getValue()) * 1;

                this.getResources().add(new Resource(resource.getType(), value));
            }
        }

    }

    public void computeResoucers(Long seconds) {

        this.getNotTimeResources().forEach((resource) -> {
            Resource resourcePerSecond = this.getResource(resource.getType().getResourcePerSecond());
            if (resourcePerSecond != null) {
                resource.setValue(resource.getValue() + (seconds * resourcePerSecond.getValue()));
            }

        });

    }

    public void computeHourResoucers(Long hours) {

        this.getNotTimeResources().forEach((resource) -> {
            Resource resourcePerHour = this.getResource(resource.getType().getResourcePerHour());
            if (resourcePerHour != null) {
                resource.setValue(resource.getValue() + (hours * resourcePerHour.getValue()));
            }

        });

    }

    public void computeDayResoucers(Long days) {

        this.getNotTimeResources().forEach((resource) -> {
            Resource resourcePerDay = this.getResource(resource.getType().getResourcePerDay());
            if (resourcePerDay != null) {
                resource.setValue(resource.getValue() + (days * resourcePerDay.getValue()));
            }

        });

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getLastTimeResourcesCollectedHour() {
        return lastTimeResourcesCollectedHour;
    }

    public void setLastTimeResourcesCollectedHour(Date lastTimeResourcesCollectedHour) {
        this.lastTimeResourcesCollectedHour = lastTimeResourcesCollectedHour;
    }

    public Date getLastTimeResourcesCollectedDay() {
        return lastTimeResourcesCollectedDay;
    }

    public void setLastTimeResourcesCollectedDay(Date lastTimeResourcesCollectedDay) {
        this.lastTimeResourcesCollectedDay = lastTimeResourcesCollectedDay;
    }

    public Date getLastTimeResourcesCollected() {
        return lastTimeResourcesCollected;
    }

    public void setLastTimeResourcesCollected(Date lastTimeResourcesCollected) {
        this.lastTimeResourcesCollected = lastTimeResourcesCollected;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73123 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserResource other = (UserResource) obj;
        return Objects.equals(this.id, other.id);
    }

}
