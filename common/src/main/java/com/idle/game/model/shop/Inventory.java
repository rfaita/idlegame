package com.idle.game.model.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.ValidationException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author rafael
 */
@Document(collection = "inventory")
public class Inventory {

    @Id
    private String id;

    @Indexed
    private String userId;

    private final List<InventoryItem> items = new ArrayList<>();

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

    public List<InventoryItem> getItems() {
        return items;
    }

    public InventoryItem getItem(InventoryItem i) {
        return getItem(i.getItemClassName(), i.getItemId());
    }

    public InventoryItem getItem(String itemClassName, String itemId) {
        Optional<InventoryItem> ret = this.items
                .stream()
                .filter((item) -> item.getItemId().equals(itemId)
                && item.getItemClassName().equals(itemClassName))
                .findFirst();

        if (ret.isPresent()) {
            return ret.get();
        } else {
            return null;
        }
    }

    public void addItem(InventoryItem i) {
        changeItem(i, Boolean.TRUE);
    }

    public void removeItem(InventoryItem i) {
        changeItem(i, Boolean.FALSE);
    }

    public Boolean containsItem(InventoryItem i) {
        return getItem(i.getItemClassName(), i.getItemId()) != null;
    }

    public Boolean containsFormationItem(String itemId) {
        return containsItem(new FormationItem(itemId));
    }

    private void changeItem(InventoryItem i, Boolean add) {

        InventoryItem item = getItem(i);
        if (item != null) {

            if (!add && item.getAmount() - i.getAmount() < 0) {
                throw new ValidationException("you.can.not.remove.more.items.than.you.have");
            }

            item.setAmount(item.getAmount() + (i.getAmount() * (add ? 1 : -1)));
        } else {
            this.items.add(i);
        }
    }

}
