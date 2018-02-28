package com.idle.game.model.mongo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import javax.validation.ValidationException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author rafael
 */
@Document(collection = "playerresource")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PlayerResource implements Serializable {

    @Id
    private String id;
    @Indexed
    private String player;
    private Date lastTimeResourcesCollected = new Date();
    private List<Resource> resources = new ArrayList<>();

    public PlayerResource() {
    }

    public PlayerResource(String player, List<Resource> resources) {
        this.player = player;
        this.resources = resources;
    }

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

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
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
        final PlayerResource other = (PlayerResource) obj;
        return Objects.equals(this.id, other.id);
    }

}
