package ru.sfedu.SchoolMeals.model.bean;

/**
 * Class FoodCategory
 */
public class FoodCategory {

  //
  // Fields
  //

  private String categoryName;
  
  //
  // Constructors
  //
  public FoodCategory () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of categoryName
   * @param newVar the new value of categoryName
   */
  public void setCategoryName (String newVar) {
    categoryName = newVar;
  }

  /**
   * Get the value of categoryName
   * @return the value of categoryName
   */
  public String getCategoryName () {
    return categoryName;
  }

  //
  // Other methods
  //

}
