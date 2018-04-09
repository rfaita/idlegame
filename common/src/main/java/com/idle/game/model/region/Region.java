package com.idle.game.model.region;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author rafael
 */
@Document(collection = "region")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Region implements Serializable {

    @Id
    private String id;
    
    private String battleFieldId;
    
    
}
