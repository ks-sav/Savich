package ru.sfedu.SchoolMeals.model.bean;

import java.util.List;

/**
 * Class Menu
 */
public class Menu {

  //
  // Fields
  //

  private List<FoodItem> FoodList;
  
  //
  // Constructors
  //
  public Menu () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of FoodList
   * @param newVar the new value of FoodList
   */
  public void setFoodList (List<FoodItem> newVar) {
    FoodList = newVar;
  }

  /**
   * Get the value of FoodList
   * @return the value of FoodList
   */
  public List<FoodItem> getFoodList () {
    return FoodList;
  }

  //
  // Other methods
  //

}
