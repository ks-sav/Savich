package ru.sfedu.SchoolMeals.model.bean;

/**
 * Class Puiple
 */
public class Puiple extends Customer{

  //
  // Fields
  //

  private String nclass;
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
  public void setNClass (String newVar) {
    nclass = newVar;
  }

  /**
   * Get the value of class
   * @return the value of class
   */
  public String getNClass () {
    return nclass;
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
