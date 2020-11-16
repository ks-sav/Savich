package ru.sfedu.SchoolMeals.bean;

/**
 * Class Puiple
 */
public class Puiple {

  //
  // Fields
  //

  private String class;
  private Boolean freeMeals;
  
  //
  // Constructors
  //
  public Puiple () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of class
   * @param newVar the new value of class
   */
  public void setClass (String newVar) {
    class = newVar;
  }

  /**
   * Get the value of class
   * @return the value of class
   */
  public String getClass () {
    return class;
  }

  /**
   * Set the value of freeMeals
   * @param newVar the new value of freeMeals
   */
  public void setFreeMeals (Boolean newVar) {
    freeMeals = newVar;
  }

  /**
   * Get the value of freeMeals
   * @return the value of freeMeals
   */
  public Boolean getFreeMeals () {
    return freeMeals;
  }

  //
  // Other methods
  //

}
