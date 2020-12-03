package ru.sfedu.SchoolMeals.model.bean;

import java.util.Objects;

/**
 * Class FoodCategory
 */
public class FoodCategory implements WithId{

  //
  // Fields
  //
  private long id;
  private String categoryName;

  public FoodCategory(long id, String categoryName) {
    this.id = id;
    this.categoryName = categoryName;
  }

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
   */ /**
   * Set the value of id
   * @param newVar the new value of id
   */
  public void setId (long newVar) {
    id = newVar;
  }

  /**
   * Get the value of id
   * @return the value of id
   */
  public long getId () {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FoodCategory that = (FoodCategory) o;
    return Objects.equals(id, that.id) &&
            Objects.equals(categoryName, that.categoryName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, categoryName);
  }

  @Override
  public String toString() {
    return "FoodCategory{" +
            "id=" + id +
            ", categoryName='" + categoryName + '\'' +
            '}';
  }
}
