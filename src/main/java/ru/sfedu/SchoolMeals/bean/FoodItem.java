package ru.sfedu.SchoolMeals.bean;

/**
 * Class FoodItem
 */
public class FoodItem {

  //
  // Fields
  //

  private Integer id;
  private String itemName_;
  private Long price;
  private String description;
  private FoodCategory category;
  private Boolean inStock;
  private Boolean inCombo;
  
  //
  // Constructors
  //
  public FoodItem () { };
  
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
  public Integer getId () {
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
  public void setPrice (Long newVar) {
    price = newVar;
  }

  /**
   * Get the value of price
   * @return the value of price
   */
  public Long getPrice () {
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
  public void setCategory (FoodCategory newVar) {
    category = newVar;
  }

  /**
   * Get the value of category
   * @return the value of category
   */
  public FoodCategory getCategory () {
    return category;
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

  /**
   * Set the value of inCombo
   * @param newVar the new value of inCombo
   */
  public void setInCombo (Boolean newVar) {
    inCombo = newVar;
  }

  /**
   * Get the value of inCombo
   * @return the value of inCombo
   */
  public Boolean getInCombo () {
    return inCombo;
  }

  //
  // Other methods
  //

}
