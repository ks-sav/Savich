package ru.sfedu.SchoolMeals.model.bean;

/**
 * Class FoodCategory
 */
public class FoodCategory implements WithId{

  //
  // Fields
  //
  private Integer id;
  private String categoryName;
  
  //
  // Constructors
  //
  public FoodCategory () { };

    public FoodCategory(long parseLong, String csvDatum) {
    }

    //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of categoryName
   * @param newVar the new value of categoryName
   */ /**
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
