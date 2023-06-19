package org.coolstore.catalog.model;

import java.math.BigDecimal;

public class Product{

    private String itemId;
    private String title;
    private String desc;
    private BigDecimal price;
    private int quantity;

    private String image;
    private String category;


    public Product() {

    }

    public Product(String itemId, String title, String desc, BigDecimal price, String category, String image) {
        super();
        this.itemId = itemId;
        this.title = title;
        this.desc = desc;
        this.price = price;
        this.category = category;
        this.image = image;
    }
    public String getItemId() {
        return itemId;
    }
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product [itemId=" + itemId + ", name=" + title + ", desc="
                + desc + ", price=" + price + ", quantity=" + quantity + ", category=" + category +", image=" + image + "]";
    }



}