package ru.sfedu.SchoolMeals.model.bean;

import java.util.List;

/**
 * Class ComboMeals
 */
public class ComboMeals {

  //
  // Fields
  //

  private String date;
  private List<FoodItem> menu;
  private Long price;
  
  //
  // Constructors
  //
  public ComboMeals () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of date
   * @param newVar the new value of date
   */
  public void setDate (String newVar) {
    date = newVar;
  }

  /**
   * Get the value of date
   * @return the value of date
   */
  public String getDate () {
    return date;
  }

  /**
   * Set the value of menu
   * @param newVar the new value of menu
   */
  public void setMenu (List<FoodItem> newVar) {
    menu = newVar;
  }

  /**
   * Get the value of menu
   * @return the value of menu
   */
  public List<FoodItem> getMenu () {
    return menu;
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

  //
  // Other methods
  //

}
