package ru.sfedu.SchoolMeals.model.bean;

import java.util.Objects;

/**
 * Class FoodItem
 */
public class FoodItem implements WithId{

  //
  // Fields
  //

  private long id;
  private String itemName_;
  private double price;
  private String description;
  private long category_id;
  private Boolean inStock;
  
  //
  // Constructors
  //

  public FoodItem(long id, String itemName_, double price, String description, long category_id, Boolean inStock) {
    this.id = id;
    this.itemName_ = itemName_;
    this.price = price;
    this.description = description;
    this.category_id = category_id;
    this.inStock = inStock;
  }

  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of id
   * @param newVar the new value of id
   */
  public void setId (Integer newVar) {
    id = newVar;
  }

  /**
   * Get the value of id
   * @return the value of id
   */
  public long getId () {
    return id;
  }

  /**
   * Set the value of itemName_
   * @param newVar the new value of itemName_
   */
  public void setItemName_ (String newVar) {
    itemName_ = newVar;
  }

  /**
   * Get the value of itemName_
   * @return the value of itemName_
   */
  public String getItemName_ () {
    return itemName_;
  }

  /**
   * Set the value of price
   * @param newVar the new value of price
   */
  public void setPrice (Double newVar) {
    price = newVar;
  }

  /**
   * Get the value of price
   * @return the value of price
   */
  public Double getPrice () {
    return price;
  }

  /**
   * Set the value of description
   * @param newVar the new value of description
   */
  public void setDescription (String newVar) {
    description = newVar;
  }

  /**
   * Get the value of description
   * @return the value of description
   */
  public String getDescription () {
    return description;
  }

  /**
   * Set the value of category
   * @param newVar the new value of category
   */
  public void setCategory (long newVar) {
    category_id = newVar;
  }

  /**
   * Get the value of category
   * @return the value of category
   */
  public long getCategory () {
    return category_id;
  }

  /**
   * Set the value of inStock
   * @param newVar the new value of inStock
   */
  public void setInStock (Boolean newVar) {
    inStock = newVar;
  }

  /**
   * Get the value of inStock
   * @return the value of inStock
   */
  public Boolean getInStock () {
    return inStock;
  }


  //
  // Other methods
  //

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FoodItem foodItem = (FoodItem) o;
    return category_id == foodItem.category_id &&
            Objects.equals(id, foodItem.id) &&
            Objects.equals(itemName_, foodItem.itemName_) &&
            Objects.equals(price, foodItem.price) &&
            Objects.equals(description, foodItem.description) &&
            Objects.equals(inStock, foodItem.inStock);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, itemName_, price, description, category_id, inStock);
  }

  @Override
  public String toString() {
    return "FoodItem{" +
            "id=" + id +
            ", itemName_='" + itemName_ + '\'' +
            ", price=" + price +
            ", description='" + description + '\'' +
            ", category_id=" + category_id +
            ", inStock=" + inStock +
            '}';
  }
}
