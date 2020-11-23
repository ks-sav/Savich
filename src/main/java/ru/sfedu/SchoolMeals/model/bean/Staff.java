package ru.sfedu.SchoolMeals.model.bean;

/**
 * Class Staff
 */
public class Staff extends Customer {

  //
  // Fields
  //

  private Boolean unionMember;
  
  //
  // Constructors
  //
  public Staff () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of unionMember
   * @param newVar the new value of unionMember
   */
  public void setUnionMember (Boolean newVar) {
    unionMember = newVar;
  }

  /**
   * Get the value of unionMember
   * @return the value of unionMember
   */
  public Boolean getUnionMember () {
    return unionMember;
  }

  //
  // Other methods
  //

}
