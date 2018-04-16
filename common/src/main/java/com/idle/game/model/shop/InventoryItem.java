package com.idle.game.model.shop;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 *
 * @author rafael
 */
public class InventoryItem implements Serializable {

    @NotNull(message = "item.class.name.can.not.be.null")
    private String itemClassName;
    @NotNull(message = "item.id.name.can.not.be.null")
    private String itemId;
    //TODO: ItemCategoryEnum
    @Positive(message = "item.ammout.must.be.greater.than.zero")
    private Long amount;

    public InventoryItem(String itemClassName, String itemId) {
        this.itemClassName = itemClassName;
        this.itemId = itemId;
    }

    public InventoryItem() {
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemClassName() {
        return itemClassName;
    }

    public void setItemClassName(String itemClassName) {
        this.itemClassName = itemClassName;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

}
