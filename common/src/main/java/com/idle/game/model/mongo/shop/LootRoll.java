package com.idle.game.model.mongo.shop;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.idle.game.core.util.DiceUtil;
import com.idle.game.model.mongo.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.ValidationException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author rafael
 */
@Document(collection = "lootroll")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class LootRoll implements Serializable {

    @Id
    private String id;

    private final Map<String, List<LootRollValue>> rolls = new HashMap<>();
    private final List<Resource> cost = new ArrayList<>();
    private String name;
    private String desc;
    private LootRollType type;

    public LootRoll() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LootRollType getType() {
        return type;
    }

    public void setType(LootRollType type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String roll(Class clazz) {
        return roll(clazz.getSimpleName());
    }

    public String roll(String clazz) {

        Double percentageAccumulator = 0.0;

        percentageAccumulator = getRolls(clazz).stream().map(
                (lootRollValue) -> {
                    return lootRollValue.getRollPercentage();
                }).reduce(percentageAccumulator,
                        (accumulator, value) -> {
                            return accumulator + value;
                        });

        if (percentageAccumulator > 100.0) {
            throw new ValidationException("loot.roll.invalid");
        }

        percentageAccumulator = 0.0;

        Double random = DiceUtil.randomPercentage();

        for (LootRollValue lootRollValue : getRolls(clazz)) {
            if (random <= (lootRollValue.getRollPercentage() + percentageAccumulator)) {
                return lootRollValue.getRollType();
            } else {
                percentageAccumulator += lootRollValue.getRollPercentage();
            }
        }

        throw new ValidationException("invalid.roll.value");

    }

    public List<Resource> getCost() {
        return cost;
    }

    public void addCost(Resource resource) {
        this.cost.add(resource);
    }

    public List<LootRollValue> getRolls(String clazz) {
        return rolls.get(clazz);
    }

    public Map<String, List<LootRollValue>> getRolls() {
        return rolls;
    }

    public void addRoll(Class clazz, LootRollValue roll) {
        addRoll(clazz.getSimpleName(), roll);
    }

    public void addRoll(String clazz, LootRollValue roll) {
        if (this.getRolls(clazz) == null) {
            this.rolls.put(clazz, new ArrayList<>());
        }
        this.rolls.get(clazz).add(roll);
    }

}
