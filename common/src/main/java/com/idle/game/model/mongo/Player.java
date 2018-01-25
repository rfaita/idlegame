package com.idle.game.model.mongo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author rafael
 */
@Document(collection = "player")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Player implements Serializable {

    @Id
    private String id;
    private Integer level;
    private String user;
    private String linkedUser;
    private Date lastTimeResourcesCollected;

    private List<Resource> resources = new ArrayList<>();

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public Resource getResource(ResourceType resourceType) {
        Optional<Resource> ret = this.getResources().stream().filter((t) -> {
            return t.getType().equals(resourceType);
        }).findFirst();

        try {
            return ret.get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void userResources(List<Resource> resources) {

        resources.stream().forEach((resource) -> {
            Resource compResource = this.getResource(resource.getType());
            if (compResource != null) {

                Long value = Math.abs(resource.getValue()) * -1;

                if ((compResource.getValue() + value) < 0) {
                    compResource.setValue(compResource.getValue() + value);
                } else {
                    compResource.setValue(0L);
                }

            }
        });

    }

    public void computeResoucers(Long seconds) {

        this.getResources().stream().forEach((resource) -> {
            Resource resourcePS = this.getResource(resource.getType().getResourcePS());
            if (resourcePS != null) {
                resource.setValue(resource.getValue() + (seconds * resourcePS.getValue()));
            }
        });

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLinkedUser() {
        return linkedUser;
    }

    public void setLinkedUser(String linkedUser) {
        this.linkedUser = linkedUser;
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
        hash = 73 * hash + Objects.hashCode(this.id);
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
        final Player other = (Player) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
